cd /opt/jboss/wildfly/standalone/deployments/
jar -xvf stupefy-subscription.war
cd WEB-INF/
java -cp "lib/mysql-connector-j-8.0.31.jar:classes/." com.stupefy.stupefysubscription.database.Migrate