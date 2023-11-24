FROM golang:1.21.1-alpine

RUN apk add --update --no-cache git

WORKDIR /app

COPY go.mod go.sum ./

RUN go mod download

COPY . .

RUN go build -o main

CMD ["./main"]