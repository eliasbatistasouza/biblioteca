# Usa uma imagem leve com Java 25
FROM openjdk:25-jdk-slim

WORKDIR /app

COPY ./src/lib /app/lib

CMD ["bash"]