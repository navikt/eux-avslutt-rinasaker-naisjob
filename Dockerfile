FROM cgr.dev/chainguard/jre:openjdk-25
COPY target/eux-avslutt-rinasaker-naisjob.jar /app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
