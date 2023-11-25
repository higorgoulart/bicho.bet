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

    print("Conectando ao contêiner do Django para realizar o migrate e criar admin")
    subprocess.call("docker exec -it django-server /bin/bash", shell=True)
    subprocess.call("python manage.py migrate", shell=True)
    subprocess.call("export DJANGO_SUPERUSER_PASSWORD=123", shell=True)
    subprocess.call("python manage.py createsuperuser --username bichobet --email bichobet.satc@gmail.com --noinput", shell=True)
    subprocess.call("exit", shell=True)

    print("Iniciando Go Scheduler...")
    subprocess.call("docker-compose up -d go-scheduler", shell=True)
    print("Aguardando Go-Scheduler")
    time.sleep(20)

    print("Iniciando Frontend...")
    subprocess.call("docker-compose up -d frontend", shell=True)

if __name__ == "__main__":
    main()