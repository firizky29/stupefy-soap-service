FROM quay.io/wildfly/wildfly:latest-jdk17
ARG ADMIN_USER
ARG ADMIN_PASSWORD
RUN /opt/jboss/wildfly/bin/add-user.sh $ADMIN_USER $ADMIN_PASSWORD
COPY target/stupefy-subscription.war /opt/jboss/wildfly/standalone/deployments/
CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]
