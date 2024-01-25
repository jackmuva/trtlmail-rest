## Trtlmail-rest
API's responsible for handling REST processes for trtlmail-web

### To run docker:
1) Run `mvn clean package`
2) Run `docker build -t trtlmail-rest:latest .`
3) Run `docker run --env-file ./.env -p 5000:5000 trtlmail-rest:latest`
 - You can also run `docker run --env-file ./.env -p 5000:5000  514832027284.dkr.ecr.us-east-1.amazonaws.com/trtmail-rest:latest` to run the aws image

### To deploy to ECS:
1) mvn clean package
2) Open Dockerhub
3) Run the deploy.sh script
4) Check that service in ECS has at least 1 desirable task

### Setting up AWS Infrastructure
1) Create Load Balancer
2) Create target group of type IP address and HTTP protocol port 5000
3) Under Load Balancer Listener HTTP:80
4) Create Fargate cluster
5) Auto assign IP and use the previously created load balancer (listening on port 5000) and target group
6) In Route 53, use an A record to direct domain to our Load Balancer
7) Request a certificate in Certificate Manager
8) In our Load Balancer, go back to the Listeners tab and add port 443 HTTPS with our cert as the default certificate
9) Setup RDS if not done already, import env variable file in private S3