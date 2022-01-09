FROM maven:3.8.4-jdk-11-slim
WORKDIR /app
COPY src ./src
COPY pom.xml ./
COPY run-tests.sh ./
RUN chmod +x run-tests.sh
ENTRYPOINT [ "./run-tests.sh" ]