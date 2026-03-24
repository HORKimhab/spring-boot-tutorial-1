### Description

- Spring boot by @boualiali
- Url: https://www.youtube.com/watch?v=5rNk7m_zlAg

### Run

```bash
  # Run in vscode (git-bash, bash)
  ./mvnw spring-boot:run

  bash run
  # Run with debug or stracktrace -e
  bash run -X
  bash run clean
```

### Environment Variables

Spring Boot can load project root [`.env`](/Users/hkimhab25/learning/spring-boot-tutorial-1/.env) without extra dependencies by using:

```properties
spring.config.import=optional:file:.env[.properties]
```

Expected `.env` format:

```properties
APP_SECURITY_USER_NAME=admin
APP_SECURITY_USER_PASSWORD=admin123
APP_SECURITY_USER_ROLES=USER
```

Notes:

- keep `.env` in the project root
- use plain `KEY=VALUE` lines, not `export KEY=VALUE`
- restart the app after changing `.env`

### Note

```bash
--- @Bean ---
@Bean method
     ↓
create object
     ↓
Spring container stores it
     ↓
anywhere → getBean() / @Autowired
     ↓
reuse same object
---        ---
```

- add hot reload to disabled

```bash
     # application.properties
     spring.devtools.restart.enabled=false
     spring.devtools.livereload.enabled=false
```

```bash
--- @componet ---

@Component is an annotation that allows Spring to detect our custom beans automatically.

In other words, without having to write any explicit code, Spring will:

Scan our application for classes annotated with @Component
Instantiate them and inject any specified dependencies into them
Inject them wherever needed
However, most developers prefer to use more specialized stereotype annotations to serve this function.
Ref: https://www.baeldung.com/spring-component-annotation

--- @componet ---
```

### Swagger

- http://localhost:8081/v3/api-docs
- http://localhost:8081/swagger-ui/index.html
- http://localhost:8081/swagger-ui.html

### TODO

- Learn next: https://youtu.be/5rNk7m_zlAg?t=17081
- Fix this: '{"ok":false,"error_code":404,"description":"Not Found"}'

### Resources

- https://chatgpt.com/share/69c0e9ac-c678-800b-baa3-1168d9b5131d
- Pacakages: https://mvnrepository.com
- Connect DB with Postgres: 
     - https://claude.ai/share/495484eb-0b77-457b-a54b-d63eee7273fb
     - https://chatgpt.com/share/69c22e8f-ad4c-800b-9ea1-6705924bcd0c
- Login to postgres: psql -U root_spring -d spring_db_demo
