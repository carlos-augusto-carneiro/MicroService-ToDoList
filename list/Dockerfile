# Etapa de build
FROM maven:3.6.3-openjdk-17 as build
WORKDIR /usr/src/app
COPY . .
RUN mvn clean package -DskipTests

# Etapa de execução
FROM openjdk:17-slim
RUN apt-get update && apt-get install -y netcat-openbsd
COPY --from=build /usr/src/app/target/*.jar /app/app.jar
COPY wait-for-it.sh /app/wait-for-it.sh
RUN chmod +x /app/wait-for-it.sh
EXPOSE 8080
CMD ["sh", "-c", "ls -l /app && /app/wait-for-it.sh mysql:3306 && java -jar /app/app.jar"]