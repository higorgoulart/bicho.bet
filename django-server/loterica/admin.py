from django.contrib import admin
from loterica.models import *

@admin.register(Jogo)
@admin.display(description="Jogo")
class JogoAdmin(admin.ModelAdmin):
    pass

@admin.register(Funcionario)
@admin.display(description="Funcionário")
class FuncionarioAdmin(admin.ModelAdmin):
    list_display = ["nome", "telefone", "num_cadastro"]

