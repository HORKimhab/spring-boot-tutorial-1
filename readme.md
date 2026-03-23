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

### TODO

- Learn next: https://youtu.be/5rNk7m_zlAg?t=5936

### Resources

- https://chatgpt.com/share/69c0e9ac-c678-800b-baa3-1168d9b5131d
