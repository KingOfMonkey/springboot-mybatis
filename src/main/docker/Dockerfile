## 任何Dockerfile中的第一条指令必须为FROM ,并且，如果在同一个Dockerfile中创建多个镜像，可以使用多个FROM指令（每个镜像一次）
FROM openjdk:8-jdk-alpine
## 指定维护者信息，格式为：MAINTAINER<name>
MAINTAINER baibing@sit.com.cn
## 创建数据卷挂载点
VOLUME /tmp
## 复制springboot-mybatis-1.0.jar 并且重命名为app.jar
ADD springboot-mybatis-1.0.jar app.jar
## 指定镜像的默认入口
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]