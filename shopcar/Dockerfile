FROM amazoncorretto:21-alpine

RUN apk add maven

WORKDIR /usr/app

COPY . /usr/app/

RUN mvn clean install

EXPOSE 8080

CMD [ "java", "-jar", "/usr/app/target/shopcar-0.0.1-SNAPSHOT.jar" ]