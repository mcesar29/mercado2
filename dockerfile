# Estágio 1: Construção do JAR usando Maven
FROM maven:3.8.4-openjdk-17 AS builder

WORKDIR /mercado2

# Copia apenas o arquivo POM para obter as dependências
COPY pom.xml .

# Baixa as dependências do Maven
RUN mvn dependency:go-offline

# Copia o resto do código e realiza o build
COPY src src
RUN mvn package -DskipTests

# Estágio 2: Criação da imagem final com o JAR construído
FROM openjdk:17-jdk-alpine

WORKDIR /mercado2

# Copia apenas o JAR construído do estágio anterior
COPY --from=builder /mercado2/target/mercado-0.0.1-SNAPSHOT.jar .

# Expondo a porta 8080
EXPOSE 8080
EXPOSE 5432
EXPOSE 5433

# Comando para executar a aplicação quando o contêiner for iniciado
CMD ["java", "-jar", "mercado-0.0.1-SNAPSHOT.jar"]