FROM openjdk
MAINTAINER Dipankar Chatterjee <dipankar.c@hcl.com>
ADD target/LOGAggregationELKStack-1.0.jar LOGAggregationELKStack-1.0.jar
ENTRYPOINT exec java -jar /LOGAggregationELKStack-1.0.jar spring-boot-elk-service
EXPOSE 12001