FROM openjdk:8-jre-alpine

RUN apk add --update \
    curl bash \
    && rm -rf /var/cache/apk/*

ADD target/k8s-explorer-1.0-SNAPSHOT-shaded.jar /usr/share/k8s-explorer.jar

EXPOSE 7000

ENTRYPOINT ["/usr/bin/java", "-jar", "/usr/share/k8s-explorer.jar"]