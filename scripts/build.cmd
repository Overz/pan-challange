@ECHO OFF

echo "Building and generating version..."
@REM mvn build-helper:parse-version help:effective-pom

START ./templates.cmd
mvn package
