### Description

- Spring boot by @boualiali
- Url: https://www.youtube.com/watch?v=5rNk7m_zlAg

### Run 

```bash
  # Run in vscode (git-bash, bash)
  ./mvnw spring-boot:run
```

### Note

``` bash
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

- Learn next: https://youtu.be/5rNk7m_zlAg?t=4632