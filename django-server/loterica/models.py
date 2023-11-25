from django.db import models

class Loterica(models.Model):
    def __str__(self):
        return 'Lotérica: ' + self.nome
    
    id = models.BigIntegerField(primary_key=True)
    nome = models.CharField(max_length=255, blank=True, null=True)
    saldo = models.FloatField(blank=True, null=True)
    cnpj = models.CharField(max_length=255, blank=True, null=True)
    telefone = models.CharField(max_length=255, blank=True, null=True)
    class Meta:
        managed = False
        db_table = 'loterica'
        verbose_name = 'Lotérica'

class Funcionario(models.Model):
    def __str__(self):
        return self.nome + ' Número de Cadastro: ' + self.num_cadastro
    id = models.AutoField(primary_key=True)
    nome = models.CharField(max_length=255, blank=True, null=True)
    telefone = models.CharField(max_length=255, blank=True, null=True)
    num_cadastro = models.CharField(max_length=30, blank=False, null=False, unique=True, verbose_name='Número de Cadastro')
    loterica = models.ForeignKey(Loterica, models.DO_NOTHING, blank=True, null=True, verbose_name='Lotérica')
    class Meta:
        managed = True
        db_table = 'funcionario'
        verbose_name = 'Funcionário'



STATUS_JOGO = [("ABERTO", "Aberto"),("FECHADO", "Fechado")]
class Jogo(models.Model):
    def __str__(self):
        return 'Jogo N.º ' + str(self.id)
    id = models.BigIntegerField(primary_key=True, verbose_name='Código do Jogo')
    dt_inicio = models.DateTimeField(blank=True, null=True, verbose_name='Data de Início')
    dt_fim = models.DateTimeField(blank=True, null=True, verbose_name='Data de Encerramento')
    status = models.CharField(max_length=255, blank=True, null=True, default='ABERTO', choices=STATUS_JOGO, verbose_name='Status')
    loterica = models.ForeignKey(Loterica, on_delete=models.CASCADE, blank=True, null=True, verbose_name='Lotérica')

    class Meta:
        managed = False
        db_table = 'jogo'


class Aposta(models.Model):
    id = models.BigIntegerField(primary_key=True)
    data = models.DateTimeField(blank=True, null=True)
    numeros = models.JSONField(blank=True, null=True)
    premio = models.FloatField(blank=True, null=True)
    tipo = models.CharField(max_length=255, blank=True, null=True)
    valor = models.FloatField(blank=True, null=True)
    jogo = models.ForeignKey(Jogo, models.CASCADE, blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'aposta'

class Resultado(models.Model):
    id = models.BigIntegerField(primary_key=True)
    data = models.DateTimeField(blank=True, null=True)
    numeros = models.JSONField(blank=True, null=True)
    jogo = models.ForeignKey(Jogo, models.CASCADE, blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'resultado'