FROM java:7
MAINTAINER mgldrz@gmail.com

RUN echo "192.168.33.10		medicall" >> /etc/hosts
RUN echo "192.168.33.10		eureka.peer.one" >> /etc/hosts
RUN echo "192.168.33.10		eureka.peer.two" >> /etc/hosts
RUN echo "192.168.33.11		mgdb" >> /etc/hosts
RUN echo "192.168.33.12		elk" >> /etc/hosts
RUN echo "192.168.33.13		docker" >> /etc/hosts

EXPOSE 8088
VOLUME /tmp
ADD /maven/${project.build.finalName}-spring-boot.jar app.jar
ENV JAVA_OPTS=""
#ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar 