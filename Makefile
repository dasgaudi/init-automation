boot/run:
	./gradlew formatKotlin
	export SPRING_DATASOURCE_USERNAME="karibou_user"
	export SPRING_DATASOURCE_PASSWORD="devsecret"
	export SPRING_DATASOURCE_URL="jdbc:mysql://localhost:13306/karibou_local?allowPublicKeyRetrieval=true&useSSL=false"
	./gradlew bootRun

clean:
	./gradlew clean

build:
	./gradlew formatKotlin
	./gradlew build

check:
	./gradlew check

lint:
	./gradlew lintKotlin

format:
	./gradlew formatKotlin

dependencies:
	./gradlew -q dependencies infrastructure:dependencies

db/start:
	docker-compose -f docker/docker-compose.yml up -d

db/stop:
	docker-compose -f docker/docker-compose.yml down -v

db/migrate:
	./gradlew infrastructure:update