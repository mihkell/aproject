Environment variables:
JAVA_HOME set to java 1.7 folder.
PATH must have java 1.7 bin also 
ANT_HOME set to ant folder
PATH must have PostgreSQL bin folder set, needs to use psql in cmd.
DATABASE_URL set to postgresql://postgres:tere123@localhost:5433/etsydb (used in servlet-context.xml)

You must have DB named etsydb with port 5433 on localhost. (used in pom.xml(${jdbc-url}) and maven-build.xml)

Run application:
You can use Maven or Ant to run the application.
Maven(if path set installed) command :mvn install
ANT command: ant


For running the generated war:
java -jar target/dependency/webapp-runner.jar --port 11555 target/app-1.0.0-BUILD-SNAPSHOT.war
