# Week 3 — Spring REST using Spring Boot 3 (`spring-learn`)

All six **mandatory** Java FSE hands-on for Week 3, implemented in one Spring Boot 3 project.

| # | Mandatory hands-on (doc) | Where it is |
|---|--------------------------|-------------|
| 1 | Create a Spring Web Project using Maven | `pom.xml`, `SpringLearnApplication.java` |
| 2 | Spring Core – load Country from Spring XML (+ logging) | `Country.java`, `country.xml`, `date-format.xml`, `application.properties`, the `displayDate/displayCountry/displayCountries` methods |
| 3 | Hello World RESTful Web Service | `controller/HelloController.java` |
| 4 | REST – Country Web Service (`/country`) | `controller/CountryController.java#getCountryIndia` |
| 5 | REST – Get country by code (`/country/{code}` + 404) | `CountryController#getCountry`, `service/CountryService.java`, `service/exception/CountryNotFoundException.java` |
| 6 | Create authentication service that returns JWT (`/authenticate`) | `controller/AuthenticationController.java`, `security/SecurityConfig.java`, `security/JwtAuthorizationFilter.java`, `security/SecurityConstants.java` |

Also included from the same docs: `GET /countries` (all countries) and the MockMVC tests
(`SpringLearnApplicationTests.java`).

## Prerequisites

- JDK 17 or newer
- Maven 3.9+ (or generate a wrapper with `mvn -N wrapper:wrapper`)

## Build & run

```bash
cd spring-learn
mvn clean package          # compiles, runs the MockMVC tests, builds the jar
mvn spring-boot:run        # starts on http://localhost:8083
```

On the Cognizant network, add the proxy args from the hands-on to the mvn command, e.g.
`-Dhttp.proxyHost=proxy.cognizant.com -Dhttp.proxyPort=6050 ...`.

## Testing each hands-on

**Hands-on 3 — Hello World** (open, no auth):
```bash
curl -i http://localhost:8083/hello            # -> Hello World!!
```

**Hands-on 6 — get a JWT** (Basic credentials: user/pwd or admin/pwd):
```bash
curl -s -u user:pwd http://localhost:8083/authenticate
# -> {"token":"eyJhbGciOiJIUzI1NiJ9...."}
```

**Hands-on 4 & 5 — country services** (secured now that JWT is enabled; pass the token):
```bash
TOKEN=$(curl -s -u user:pwd http://localhost:8083/authenticate | sed 's/.*"token":"//;s/".*//')

curl -s -H "Authorization: Bearer $TOKEN" http://localhost:8083/country        # India
curl -s -H "Authorization: Bearer $TOKEN" http://localhost:8083/countries      # all four
curl -s -H "Authorization: Bearer $TOKEN" http://localhost:8083/country/in     # India by code
curl -i -H "Authorization: Bearer $TOKEN" http://localhost:8083/country/az     # 404 Country not found
```

(Tampering with the token yields `401 Unauthorized`.)

**MockMVC tests:**
```bash
mvn test
```

## Logging

Per the hands-on rule, **no `System.out.println`** anywhere — everything uses SLF4J with the
custom console pattern in `application.properties`. Every method logs START/END, and the
`Country` constructor/getters/setters log at debug so you can watch bean creation and
singleton scope (constructor logged once) in the console.

## Spring Boot 3 modernizations (vs the older doc)

The hands-on PDFs were written for Spring Boot 2 / Spring Security 5. To compile and run on
**Spring Boot 3 / Spring Security 6 / Java 17+**, these equivalents are used — same behavior:

| Doc (Boot 2) | This project (Boot 3) |
|---|---|
| `WebSecurityConfigurerAdapter` + `configure(...)` | `SecurityFilterChain` + `UserDetailsService` / `AuthenticationManager` beans |
| `javax.servlet.*` | `jakarta.servlet.*` |
| `jjwt 0.9.0`, secret `"secretkey"` | `jjwt 0.11.5`; ≥256-bit key (HS256 spec is now enforced) |
| `antMatchers(...)` | `requestMatchers(...)` |
| `Jwts.parser().setSigningKey(str)` | `Jwts.parserBuilder().setSigningKey(key).build()` |

## Notes

- Ports: the country/hello docs use `8083`, the JWT doc's curl examples use `8090`. This project
  uses **8083** (change `server.port` if you prefer 8090).
- The Java package is `com.cognizant.springlearn` (the doc's `com.cognizant.spring-learn` isn't a
  valid Java identifier — hyphens aren't allowed).
- Faithful to the teaching style, the Spring Core methods load `country.xml` via
  `ClassPathXmlApplicationContext`. That re-reads the file per call; fine for the exercise, but in
  real code you'd load it once.
