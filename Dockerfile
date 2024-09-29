FROM azul/zulu-openjdk:21-latest as build

WORKDIR /app

COPY . .

RUN apt-get update
RUN apt-get install git=1:2.34.1-1ubuntu1.11 -y --no-install-recommends

RUN ./gradlew clean build

FROM azul/zulu-openjdk-alpine:21-jre-headless-latest

ARG GIT_REV=0000
ARG IMAGE_VERSION=0.0.1

LABEL com.seek.service.name=sgonz
LABEL com.e2x.kata.version=${IMAGE_VERSION}
LABEL com.e2x.kata.gitrev=${GIT_REV}
LABEL com.e2x.kata.maintainer=dom.selvon@applydigital.com
LABEL com.e2x.kata.author=dom.selvon@applydigital.com

ARG KATA_UID=1000
ARG KATA_USER=kata
ARG KATA_GID=1000
ARG KATA_GROUP=kata

COPY --from=build /app/build/libs/test-sgonzales-service-${IMAGE_VERSION}.jar /challenge/test-sgonzales-service.jar

RUN addgroup --gid ${KATA_GID} ${KATA_GROUP} && \
    adduser --shell /bin/sh --uid ${KATA_UID} --disabled-password --ingroup ${KATA_GROUP} ${KATA_USER} && \
    chown -R ${KATA_USER}:${KATA_GROUP} /challenge

WORKDIR /challenge
USER ${KATA_USER}

EXPOSE 8080

CMD java -jar test-sgonzales-service.jar --server.servlet.context-path=/