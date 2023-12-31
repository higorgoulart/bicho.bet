# This is an auto-generated Django model module.
# You'll have to do the following manually to clean this up:
#   * Rearrange models' order
#   * Make sure each model has one field with primary_key=True
#   * Make sure each ForeignKey and OneToOneField has `on_delete` set to the desired behavior
#   * Remove `managed = False` lines if you wish to allow Django to create, modify, and delete the table
# Feel free to rename the models, but don't rename db_table values or field names.
from django.db import models


class Aposta(models.Model):
    id = models.BigIntegerField(primary_key=True)
    data = models.DateTimeField(blank=True, null=True)
    numeros = models.JSONField(blank=True, null=True)
    premio = models.FloatField(blank=True, null=True)
    tipo = models.CharField(max_length=255, blank=True, null=True)
    valor = models.FloatField(blank=True, null=True)
    apostador = models.ForeignKey('Apostador', models.DO_NOTHING, blank=True, null=True)
    jogo = models.ForeignKey('Jogo', models.DO_NOTHING, blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'aposta'


class Apostador(models.Model):
    id = models.BigIntegerField(primary_key=True)
    nome = models.CharField(max_length=255, blank=True, null=True)
    saldo = models.FloatField(blank=True, null=True)
    cpf = models.CharField(max_length=255, blank=True, null=True)
    depositado = models.FloatField(blank=True, null=True)
    divida = models.FloatField(blank=True, null=True)
    email = models.CharField(unique=True, max_length=50, blank=True, null=True)
    limite = models.FloatField(blank=True, null=True)
    password = models.CharField(max_length=120, blank=True, null=True)
    username = models.CharField(unique=True, max_length=20, blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'apostador'


class Bicho(models.Model):
    id = models.BigIntegerField(primary_key=True)
    nome = models.CharField(max_length=255)
    numeros = models.JSONField(blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'bicho'


class Jogo(models.Model):
    id = models.BigIntegerField(primary_key=True)
    dt_fim = models.DateTimeField(blank=True, null=True)
    dt_inicio = models.DateTimeField(blank=True, null=True)
    status = models.CharField(max_length=255, blank=True, null=True)
    loterica = models.ForeignKey('Loterica', models.DO_NOTHING, blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'jogo'


class Loterica(models.Model):
    id = models.BigIntegerField(primary_key=True)
    nome = models.CharField(max_length=255, blank=True, null=True)
    saldo = models.FloatField(blank=True, null=True)
    cnpj = models.CharField(max_length=255, blank=True, null=True)
    telefone = models.CharField(max_length=255, blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'loterica'


class Refreshtoken(models.Model):
    id = models.BigIntegerField(primary_key=True)
    expiry_date = models.DateTimeField()
    token = models.CharField(unique=True, max_length=255)
    user = models.ForeignKey(Apostador, models.DO_NOTHING, blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'refreshtoken'


class Resultado(models.Model):
    id = models.BigIntegerField(primary_key=True)
    data = models.DateTimeField(blank=True, null=True)
    numeros = models.JSONField(blank=True, null=True)
    jogo = models.ForeignKey(Jogo, models.DO_NOTHING, blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'resultado'


class Roles(models.Model):
    id = models.BigAutoField(primary_key=True)
    name = models.CharField(max_length=20, blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'roles'


class UserRoles(models.Model):
    user = models.OneToOneField(Apostador, models.DO_NOTHING, primary_key=True)  # The composite primary key (user_id, role_id) found, that is not supported. The first column is selected.
    role = models.ForeignKey(Roles, models.DO_NOTHING)

    class Meta:
        managed = False
        db_table = 'user_roles'
        unique_together = (('user', 'role'),)
