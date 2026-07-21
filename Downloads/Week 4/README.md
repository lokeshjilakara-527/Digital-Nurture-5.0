# Java FSE — Week 4 Mandatory Hands-on
## Microservices with Spring Boot 3 and Spring Cloud

Mandatory hands-on (from the master sheet): **Creating microservices for account and loan**,
plus **create a Eureka discovery server and register the microservices**. This project adds an
**API Gateway** so all services are reachable through one entry point — the standard pattern.

> Note on "Code Quality & SonarQube": it's listed as a Week-4 *skill* but has **no coding
> exercise** in the mandatory sheet (it's a tool you run against code). SonarQube commands are
> in the last section so you're covered.

## What's inside

```
java-fse-week4/
├── eureka-server/     # Service registry (port 8761)   — @EnableEurekaServer
├── account-service/   # Account microservice (8081)    — GET /accounts, /accounts/{no}
├── loan-service/      # Loan microservice (8082)        — GET /loans, /loans/{no}
└── api-gateway/       # Spring Cloud Gateway (8080)      — routes /accounts/** and /loans/**
```

Each service is an independent Spring Boot 3.2.5 app (Java 17, Spring Cloud 2023.0.1).
Account/Loan use **in-memory data**, so there is **no database to install** — they run as-is.

## Prerequisites

- **JDK 17+**  → `java -version`
- **Maven 3.9+** → `mvn -version`  (or use the VS Code "Spring Boot Dashboard" instead)
- **Internet on first build** (Maven downloads Spring Boot & Spring Cloud once).

## How to run — order matters

Open **four terminals**. Start Eureka first, then the two services, then the gateway.
The command is the same on Windows (PowerShell), macOS, and Linux.

```bash
# Terminal 1 — discovery server (wait until it says "Started EurekaServerApplication")
cd eureka-server
mvn spring-boot:run

# Terminal 2 — account service
cd account-service
mvn spring-boot:run

# Terminal 3 — loan service
cd loan-service
mvn spring-boot:run

# Terminal 4 — API gateway
cd api-gateway
mvn spring-boot:run
```

To build a runnable jar instead: `mvn clean package` then `java -jar target/<name>-1.0.0.jar`.

## How to see the output

**1. Eureka dashboard** — open in a browser:
```
http://localhost:8761
```
Under **"Instances currently registered with Eureka"** you should see **ACCOUNT-SERVICE**
and **LOAN-SERVICE** listed with status **UP**. That proves discovery + registration work.

**2. Call the services directly** (PowerShell: use `curl.exe`; or just open the URL in a browser):
```bash
curl http://localhost:8081/accounts
curl http://localhost:8082/loans
```

**3. Call them through the gateway** (single entry point on 8080 — this is the key result):
```bash
curl http://localhost:8080/accounts
curl http://localhost:8080/accounts/A1001
curl http://localhost:8080/loans
curl http://localhost:8080/loans/L2001
```

### Expected responses

`GET http://localhost:8080/accounts`
```json
[
  {"number":"A1001","type":"SAVINGS","balance":52000.0,"customerName":"Alger Wilson"},
  {"number":"A1002","type":"CURRENT","balance":118500.0,"customerName":"Klein Moretti"},
  {"number":"A1003","type":"SAVINGS","balance":9800.0,"customerName":"Audrey Hall"}
]
```

`GET http://localhost:8080/loans/L2001`
```json
{"number":"L2001","type":"HOME","amount":2500000.0,"interestRate":8.5,"customerName":"Alger Wilson"}
```

A request to a missing id (e.g. `/accounts/A9999`) returns **404 Not Found**.

## Flow (what happens on a gateway call)

```
client → API Gateway (8080) → looks up ACCOUNT-SERVICE in Eureka (8761)
       → forwards to account-service (8081) → JSON flows back to the client
```

## Bonus — Code Quality & SonarQube

Not a mandatory exercise, but part of the Week-4 skill. Two easy ways to analyse any service:

**Option A — SonarQube in Docker + Maven plugin**
```bash
# 1) start a local SonarQube server (http://localhost:9000, login admin/admin)
docker run -d --name sonarqube -p 9000:9000 sonarqube:lts-community

# 2) create a token in the SonarQube UI (My Account → Security), then in a service folder:
mvn clean verify sonar:sonar \
  -Dsonar.host.url=http://localhost:9000 \
  -Dsonar.login=YOUR_TOKEN \
  -Dsonar.projectKey=account-service
```
Open http://localhost:9000 to see bugs, code smells, coverage, and the quality gate.

**Option B — SonarCloud** (free for public repos): sign in at sonarcloud.io with GitHub and
point the same `sonar:sonar` command at `https://sonarcloud.io`.

## Honest status

- **Statically verified here:** all four `pom.xml` are valid XML, all `application.yml` are valid
  YAML, and the Java is structurally sound.
- **NOT run in the build sandbox:** building Spring Cloud needs Maven Central, which is blocked
  in my environment — so I could not execute these here (same situation as Week 3).
  The real proof is `mvn spring-boot:run` on your machine with internet. If any service throws
  an error on startup, paste it and I'll fix it immediately.
