FROM nexusrepository.bradesco.com.br:8500/redhat-openjdk-18/openjdk18-openshift:1.8-30.1626872912

ENV DEBUG="false"
ENV WORKDIR="/app"
#ENV JAVA_OPTS="-Xms256m -Xmx768m -XX:MaxMetaspaceSize=128m"
#ENV JAR_ARGS="-Dspring.profiles.active=prod"
ENV MC_SRV_LOGS="$WORKDIR/logs"
ENV MC_SRV_PORT="8080"
ENV MC_SRV_NAME="@project.build.finalName@.@project.packaging@"

WORKDIR $WORKDIR
ADD target/$MC_SRV_NAME $WORKDIR/$MC_SRV_NAME

USER root

EXPOSE $MC_SRV_PORT

# java [opts] -jar [args] app.jar
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar $WORKDIR/$MC_SRV_NAME" ]
