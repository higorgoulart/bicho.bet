from django.db import models

class Loterica(models.Model):
    def __str__(self):
        return 'Lotérica: ' + self.nome
    id = models.BigAutoField(primary_key=True)
    nome = models.CharField(max_length=255, blank=True, null=True)
    saldo = models.FloatField(blank=True, null=True)
    telefone = models.CharField(max_length=255, blank=True, null=True)
    cnpj = models.CharField(max_length=255, blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'loterica'
        verbose_name = 'Lotérica'

class Funcionario(models.Model):
    def __str__(self):
        return 'Funcionário: ' + self.num_cadastro
    id = models.BigAutoField(primary_key=True)
    nome = models.CharField(max_length=255, blank=True, null=True)
    telefone = models.CharField(max_length=255, blank=True, null=True)
    num_cadastro = models.CharField(max_length=30, blank=False, null=False, verbose_name='Número de Cadastro')
    loterica = models.ForeignKey(Loterica, models.DO_NOTHING, blank=True, null=True, verbose_name='Lotérica')
    class Meta:
        managed = True
        db_table = 'funcionario'
        verbose_name = 'Funcionário'

class Jogo(models.Model):
    def __str__(self):
        return 'Jogo: ' + self.id
    id = models.AutoField(primary_key=True)
    dt_inicio = models.DateTimeField(blank=True, null=True, verbose_name='Data de Início')
    dt_fim = models.DateTimeField(blank=True, null=True, verbose_name='Data de Término')
    status = models.CharField(max_length=255, blank=True, null=True)
    valor_acumulado = models.FloatField(blank=True, null=True, verbose_name='Valor Acumulado')
    loterica = models.ForeignKey(Loterica, models.DO_NOTHING, blank=True, null=True, verbose_name='Lotérica')

    class Meta:
        managed = False
        db_table = 'jogo'
