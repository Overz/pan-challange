@ECHO OFF

echo "Building and generating version..."
mvn build-helper:parse-version help:effective-pom
