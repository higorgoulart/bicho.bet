FROM python:3.10

RUN apt-get update && apt-get install -y \
    build-essential \
    python3-dev \
    python3-pip \
    && pip install --upgrade pip

WORKDIR /app

COPY . /app

RUN pip install -r requirements.txt

EXPOSE 8000

CMD ["python", "manage.py", "runserver", "0.0.0.0:8000"]
