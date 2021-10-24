FROM openjdk:8-jdk-alpine
LABEL maintainer="naahfeet@gmail.com"
LABEL build_date="2021-10-23"
VOLUME /tmp

COPY /target/invoiceMS.jar invoiceMS.jar
ENTRYPOINT ["java", "-jar", "invoiceMS.jar"]
ENV TIMEZONE Asia/Colombo
RUN ln -snf /usr/share/zoneinfo/$TIMEZONE /etc/localtime
EXPOSE 8084
