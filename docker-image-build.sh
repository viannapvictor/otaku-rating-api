for f in $(find . -name Dockerfile); do
  name=$(basename $(dirname "$f"))
  docker build -t otakurating/$name:latest -f "$f" .
done