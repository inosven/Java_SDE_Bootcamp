#export JAVA_OPTS="$JAVA_OPTS -Ddatabase.databaseName=training_db"
#export JAVA_OPTS="$JAVA_OPTS -Ddatabase.portNumber=5431"
#export JAVA_OPTS="$JAVA_OPTS -Ddatabase.user=admin"
#export JAVA_OPTS="$JAVA_OPTS -Ddatabase.password=Training123!"
#export JAVA_OPTS="$JAVA_OPTS -Ddatabase.url=jdbc:postgresql://localhost:5431/training_db"
#export JAVA_OPTS="$JAVA_OPTS -Dserver.port=8080"
#export JAVA_OPTS="$JAVA_OPTS -Dspring.profiles.active=dev"

#export CATALINA_OPTS="$CATALINA_OPTS -Daws.region=us-east-1"
#export JAVA_OPTS="$JAVA_OPTS -Ddatabase.driver=org.postgresql.Driver"
#export JAVA_OPTS="$JAVA_OPTS -Ddatabase.dialect=org.hibernate.dialect.PostgreSQL9Dialect"
#export JAVA_OPTS="$JAVA_OPTS -Ddatabase.portNumber=5432"
#export JAVA_OPTS="$JAVA_OPTS -Ddatabase.databaseName=training_db"
#export JAVA_OPTS="$JAVA_OPTS -Ddatabase.url=jdbc:postgresql://172.17.0.2:5432/training_db"
#export JAVA_OPTS="$JAVA_OPTS -Ddatabase.user=admin"
#export JAVA_OPTS="$JAVA_OPTS -Ddatabase.password=Training123!"
#export JAVA_OPTS="$JAVA_OPTS -Dorg.slf4j.simpleLogger.defaultLogLevel=info"
#export JAVA_OPTS="$JAVA_OPTS -Dserver.port=8080"
#export JAVA_OPTS="$JAVA_OPTS -Dspring.profiles.active=dev"
#export JAVA_OPTS="$JAVA_OPTS -Djwt.secret.key=my_key"



#=======================================================================#
export CATALINA_OPTS="$CATALINA_OPTS -Daws.region=us-east-1"
export JAVA_OPTS="$JAVA_OPTS -Ddatabase.driver=org.postgresql.Driver"
export JAVA_OPTS="$JAVA_OPTS -Ddatabase.dialect=org.hibernate.dialect.PostgreSQL9Dialect"
export JAVA_OPTS="$JAVA_OPTS -Dspring.profiles.active=${PROFILE}"
export JAVA_OPTS="$JAVA_OPTS -Daws.region=${REGION}"
export JAVA_OPTS="$JAVA_OPTS -Djms.queue.name=${QUEUE_NAME}"
export JAVA_OPTS="$JAVA_OPTS -Ddatabase.databaseName=${DB_NAME}"
export JAVA_OPTS="$JAVA_OPTS -Ddatabase.portNumber=${DB_PORT}"
export JAVA_OPTS="$JAVA_OPTS -Ddatabase.user=${DB_USERNAME}"
export JAVA_OPTS="$JAVA_OPTS -Ddatabase.password=${DB_PW}"
export JAVA_OPTS="$JAVA_OPTS -Ddatabase.url=jdbc:postgresql://${DB_URL}:${DB_PORT}/${DB_NAME}"
export JAVA_OPTS="$JAVA_OPTS -Dserver.port=8080"
export JAVA_OPTS="$JAVA_OPTS -Dorg.slf4j.simpleLogger.defaultLogLevel=info"
export JAVA_OPTS="$JAVA_OPTS -Djwt.secret.key=my_key"
