# Demo observability

Demo project to apply observability tools with open telemetry

## Requirements 
- Docker 

## Stacks 

- Java 21
- Spring Boot
- OpenTelemtry
- Grafana
- Jaeger
- Zipkin
- Prometheus
- Docker


## Usage/Examples

```bash
1) go to the root of the project

2) run this command: docker compose up -d

3) check api with curl 

3.1) curl --request POST \
  --url http://localhost:8080/enterprises \
  --header 'Content-Type: application/json' \
  --data '{ "cnpj": "123" }'

3.1.1) curl --request GET \
  --url 'http://localhost:8080/enterprises?cnpj=123' \
  --header 'Content-Type: application/json'

3.2) curl --request POST \
  --url http://localhost:8080/enterprises/${ID}/addresses \
  --header 'Content-Type: application/json' \
  --data '{
	"country": "BRASIL",
	"city": "SP",
	"street": "RUA SC",
	"number": 1029
}'

3.2.1) curl --request GET \
  --url http://localhost:8080/enterprises/${ID}/addresses \
  --header 'Content-Type: application/json'

3.3) curl --request POST \
  --url http://localhost:8080/enterprises/${ID}/transports \
  --header 'Content-Type: application/json' \
  --data '{
	"type": "CAR",
	"price": 10.000,
	"description": "A new car with 10.000 km"
}'

3.3.1) curl --request GET \
  --url http://localhost:8080/enterprises/${ID}/transports \
  --header 'Content-Type: application/json'

```

## Check observability by browser
- [Jaeger trace](http://localhost:16686)
- [Zipkin trace](http://localhost:9411)
- [Garana metrics](http://localhost:3000) - use default user
- [Prometheus metrics](http://localhost:9090)
- [h2](http://localhost:8080/h2-console/) - use (jdbc:h2:mem:mydb)


## Provider Infos

 - [OpenTelemtry](https://opentelemetry.io/docs/zero-code/java/spring-boot-starter/)
 - [Jaeger](https://www.jaegertracing.io/)
 - [Zipkin](https://zipkin.io/)
 - [Grafana](https://grafana.com/docs/grafana/)
 - [Prometheus](https://prometheus.io/)
 
## Authors

- [@Rafael Narbutis](https://github.com/rafaelnarbutis)

