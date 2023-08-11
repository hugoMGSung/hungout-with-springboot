# hungout-with-springboot
스프링부트 - Spring Initializr

## Spring Boot 시작하기
- VS Code Plugin
    - Extension Pack for Java
    - Debugger for Java
    - Gradle for Java
    - Maven for Java
    - Lombok Annotations Support for VS Code
    - Project Manager for Java
    - Spring Boot Extension Pack
    - Spring Boot Tools
    - Test Runner for Java 
    - etc...

- Visual Studio Code
    - Ctrl + Shift + P - Spring Initializr: Create a Gradle Project... 선택
    - Specify Spring Boot version - 3.1.2 선택
    - Specify project language - Java
    - Input Group Id - com.hugo83
    - Input Artifact Id - sb-start
    - Specify packaging type - Jar
    - Specify Java version - 17
    - Choose dependencies
        - Spring Boot DevTools
        - Lombok
        - Spring Web
        - Thymeleaf
        - Spring Data JPA
        - MySQL Driver
    - Add to Workspace

### Spring Boot Dashboard 에서 시작
- Apps 아래
    - sb-start Run - 실패
        - Spring Data JPA에 해당하는 DB 설정부재
    - application.yml (또는 .properties) 설정

```tex
spring:
    datasource:
        driver-class-name: com.mysql.cj.jdbc.Driver
        url: jdbc:mysql://localhost:3306/springboot-new?useSSL=false&serverTimezone=Asia/Seoul&characterEncoding=UTF-8
        username: root
        password: password

    jpa:
        open-in-view: true
        hibernate:
            ddl-auto: validate # create, validate
        naming:
            physical-strategy: org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl
        use-new-id-generator-mappings: false
        show-sql: true
        properties:
            hibernate.format_sql: true
            dialect: org.hibernate.dialect.MySQL8InnoDBDialect

logging:
    level:
        org.hibernate.SQL: debug
```

- Apps 아래
    - sb-start Run... 성공함
    - Lombok 테스트 - build.gradle 추가
```tex
	// Lombok을 테스트 환경에도 사용
	testCompileOnly 'org.projectlombok:lombok'
	testAnnotationProcessor 'org.projectlombok:lombok'
}
```

- 추가 설정
    - 로그레벨 설정 - build.gradle 변경(확인요)

```tex
logging:
    level:
        org:
            hibernate:
                SQL: debug
            springframework: info
        com:
            hugo83: debug
        
```

- 의존성 주입 테스트
    - DataSourceTests 생성

```java
@SpringBootTest
@Log4j2
public class DataSourceTests {
    @Autowired
    private DataSource dataSource;

    @Test
    public void testConnection() throws SQLException {
        @Cleanup
        Connection conn = dataSource.getConnection();
        log.info(conn);

        Assertions.assertNotNull(conn);
    } 
}
```

- 의존성 주입 테스트 
    - 실행결과

<img src="https://raw.githubusercontent.com/hugoMGSung/hungout-with-springboot/main/images/sb0004.png" width="400">