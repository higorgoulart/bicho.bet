FROM python:3.10

RUN apt-get update && apt-get install -y \
    build-essential \
    python3-dev \
    python3-pip \
    && pip install --upgrade pip

WORKDIR /app

COPY . /app

RUN pip install -r requirements.txt

RUN python manage.py migrate

ENV DJANGO_SUPERUSER_PASSWORD=123

RUN python manage.py createsuperuser --username bichobet --email bichobet.satc@gmail.com --noinput

EXPOSE 8000

CMD ["python", "manage.py", "runserver", "0.0.0.0:8000"]
