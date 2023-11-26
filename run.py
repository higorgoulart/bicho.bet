import time
import subprocess

def main():
    print("Iniciando Postgres...")
    subprocess.call("docker-compose up -d postgres", shell=True)

    print("Aguardando o Postgres finalizar as configurações e estar disponível para conexões...")
    time.sleep(20)

    print("Iniciando Spring API...")
    subprocess.call("docker-compose up -d spring-api", shell=True)
    print("Aguardando Spring API...")
    time.sleep(20)

    print("Iniciando Django Server...")
    subprocess.call("docker-compose up -d django-server", shell=True)
    print("Aguardando Django-Server...")
    time.sleep(20)

    print("Conectando ao contêiner do Django")
    print("Para realizar as migrações digite: python manage.py migrate")
    print("Para iniciar o processo de criação de um super-usuário digite: export DJANGO_SUPERUSER_PASSWORD={sua senha sem as chaves}")
    print("Para finalizar o processo de criação de um super-usuário digite: python manage.py createsuperuser --username {seu usuario} --email {seu email} --noinput")
    print("Após digitar os comandos, pressione CTRL+D para sair.")
    subprocess.call("docker exec -it django-server /bin/bash", shell=True)
    print("Super-usuário criado com sucesso.")

    print("Iniciando Go Scheduler...")
    subprocess.call("docker-compose up -d go-scheduler", shell=True)
    print("Aguardando Go-Scheduler...")
    time.sleep(20)

    print("Iniciando Frontend...")
    subprocess.call("docker-compose up -d frontend", shell=True)

if __name__ == "__main__":
    main()