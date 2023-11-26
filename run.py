import time
import subprocess

def main():
    print("Iniciando Postgres...")
    subprocess.call("docker-compose up -d postgres", shell=True)

    print("Aguardando Postgres...")
    time.sleep(20)

    print("Iniciando Spring API...")
    subprocess.call("docker-compose up -d spring-api", shell=True)
    print("Aguardando Spring API")
    time.sleep(20)

    print("Iniciando Django Server...")
    subprocess.call("docker-compose up -d django-server", shell=True)
    print("Aguardando Django-Server")
    time.sleep(20)

    print("Conectando ao contêiner do Django")
    subprocess.call("docker exec -it django-server /bin/bash", shell=True)
    print("Para realizar as migrações copie e cole: python manage.py migrate")
    print("Para iniciar o processo de criação de um super-usuário copie e cole: export DJANGO_SUPERUSER_PASSWORD={sua senha}")
    print("Para finalizar o processo de criação de um super-usuário copie e cole: python manage.py createsuperuser --username {seu usuario} --email {seu email} --noinput")
    print("Para sair pressione: CTRL + D")

    print("Iniciando Go Scheduler...")
    subprocess.call("docker-compose up -d go-scheduler", shell=True)
    print("Aguardando Go-Scheduler")
    time.sleep(20)

    print("Iniciando Frontend...")
    subprocess.call("docker-compose up -d frontend", shell=True)

if __name__ == "__main__":
    main()