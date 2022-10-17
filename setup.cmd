@ECHO OFF

echo "Preparing..."

mvn
git config core.hooksPath ./scripts/hooks
