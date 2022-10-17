# build the app
FROM openjdk:17 as builder

ENV MVN_VERSION="3.8.6"
ENV MVN_FILE="apache-maven-${MVN_VERSION}"
ENV MVN_TAR="${MVN_FILE}.tar.gz"
ENV MVN_URL="https://dlcdn.apache.org/maven/maven-3/${MVN_VERSION}/binaries/${MVN_FILE}-bin.tar.gz"
ENV M2_DIR="/usr/local/apache-maven"
ENV M2_HOME="${M2_DIR}/${MVN_FILE}"
ENV M2_BIN="${M2_HOME}/bin"
ENV PATH_DEF="/usr/java/openjdk-17/bin:/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin";

WORKDIR /app

ADD . .

RUN curl ${MVN_URL} -o ${MVN_TAR} \
    && mkdir -p ${M2_HOME} \
    && tar xvf ./${MVN_TAR} -C ${M2_DIR} \
    && export M2_HOME=${M2_BIN} \
    && export PATH=${PATH}:${M2_BIN} \
    && rm -rvf ./${MVN_TAR} \
    && mvn clean install

CMD [ "export PATH=${PATH_DEF}:${M2_BIN}" ]

# run the app
FROM openjdk:17-alpine as final
