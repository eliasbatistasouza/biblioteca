#!/bin/bash

# Define o diretório onde as classes compiladas serão salvas dentro do contêiner
CLASSES_DIR="/app/classes"
# Define o diretório das bibliotecas dentro do contêiner
LIB_DIR="/app/lib"
# Define o diretório do código-fonte dentro do contêiner
SRC_DIR="/app/src"

echo "Removendo classes antigas..."
rm -rf "$CLASSES_DIR"
mkdir -p "$CLASSES_DIR"

echo "Compilando arquivos Java..."
# Encontra todos os arquivos .java e os compila para o CLASSES_DIR
# O classpath inclui as bibliotecas e também o próprio diretório de classes (se houver dependências internas)
find "$SRC_DIR" -name "*.java" -print0 | xargs -0 javac -d "$CLASSES_DIR" -cp "$LIB_DIR/*:$CLASSES_DIR"

if [ $? -ne 0 ]; then
    echo "Erro de compilação. Abortando execução."
    exit 1
fi

echo "Executando a aplicação Main..."
# Executa a aplicação, apontando para o diretório de classes e o diretório de libs
java -cp "$CLASSES_DIR:$LIB_DIR/*" Main