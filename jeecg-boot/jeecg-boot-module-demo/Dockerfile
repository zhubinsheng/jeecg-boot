FROM anapsix/alpine-java:8_server-jre_unlimited

MAINTAINER jeecgos@163.com

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

RUN mkdir -p /jeecg-boot-module-demo

WORKDIR /jeecg-boot-module-demo

EXPOSE 7002

ADD ./target/jeecg-boot-module-demo-2.4.3.jar ./

CMD sleep 30;java -Dfile.encoding=utf-8 -Djava.security.egd=file:/dev/./urandom -jar jeecg-boot-module-demo-2.4.3.jar