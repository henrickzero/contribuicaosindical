FROM node:16 AS buildAngula
WORKDIR /app
COPY . .
WORKDIR /app/web
RUN npm install
RUN node_modules/.bin/ng build

# Usar uma imagem oficial do Gradle para construir nossa aplicação
FROM gradle:jdk17 AS build
# Definir o diretório de trabalho no container
WORKDIR /app
# Copiar os arquivos do projeto para o container
COPY --from=buildAngula /app/ /app/
# Construir a aplicação usando Gradle
RUN gradle clean build --no-daemon

# Usar uma imagem do OpenJDK para executar nossa aplicação
FROM openjdk:17.0-jdk
# Definir o diretório de trabalho no container
WORKDIR /app
# Copiar o arquivo JAR gerado na etapa de construção para esta nova imagem
COPY --from=build /app/build/libs/youtube-0.0.1-SNAPSHOT.jar /app/app.jar
# Comando para executar a aplicação
ENTRYPOINT ["java", "-Duser.timezone=America/Recife", "-jar", "app.jar"]