## Trtlmail-rest
API's responsible for handling REST processes for trtlmail-web

### To run docker:
1) Run `mvn clean package`
2) Run `docker build -t trtlmail-rest:latest .`
3) Run `docker run --env-file ./.env -p 5000:5000 trtlmail-rest:latest`
 - You can also run `docker run --env-file ./.env -p 5000:5000  514832027284.dkr.ecr.us-east-1.amazonaws.com/trtmail-rest:latest` to run the aws image
