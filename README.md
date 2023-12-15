## Trtlmail-rest
API's responsible for handling REST processes for trtlmail-web

### To run docker:
1) Run `mvn clean package`
2) Run `docker build -t trtlmail-rest:latest .`
3) Run `docker run --env-file ./.env -p 5000:5000 trtlmail-rest:latest`
