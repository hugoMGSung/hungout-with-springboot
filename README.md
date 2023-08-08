# hungout-with-springboot
Spring Boot 가지고 놀기 

## Java EE 맛보기
- 개발환경 
    - Visual Studio Code
    - JDK 17
    - Gradle 8.1
    - Tomcat Server

### Gradle 설치
- https://gradle.org/releases/ 에서 다운로드 설치하기(OS별 확인)
- 현재 기준 8.1.1 사용 
- 다운로드 후 압축 풀기 (C:\DEV\TOOLS\Gradle)
- 시스템 경로에 등록(sysdm.cpl)

<img src="https://raw.githubusercontent.com/hugoMGSung/hungout-with-springboot/main/images/sb0001.png" width="500">

### Tomcat 플러그인 설치
- Community Server Connectors 설치
- 탐색기 하단 SERVERS에 추가확인
- Create New Server... 클릭
- 설치 위치는 설치된 서버에서 Edit Server에서 확인가능
- Edit Server의 json 파일에서 
    - "server.http.port": "8080" 확인
- 서버 한글깨지는 경우
    - C:\Users\xxxx\.rsp\redhat-community-server-connector\runtimes\installations\tomcat-9.0.41\apache-tomcat-9.0.41\conf
    - logging.properties 의 utf-8을 모두 euc-kr로 변경

<img src="https://raw.githubusercontent.com/hugoMGSung/hungout-with-springboot/main/images/sb0002.png" width="500">

### Gradle 설정
- 콘솔창
    - gradle init 
        - 2.application 
        - 3.Java
        - multiple subprojects - no
        - 1.Groovy
        - 4.JUnit Jupiter
        - Project name - default
        - source package - com.hugo83.basicjee
        - Java verison 17
        - Generate build using new APIs and behavior - no

- 프로젝트 
    - webapp 폴더 추가
    - build.gradle 수정

```
plugins {
    // Apply the application plugin to add support for building a CLI application in Java.
    id 'application'
}

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
}

dependencies {
    // Use JUnit Jupiter for testing.
    testImplementation 'org.junit.jupiter:junit-jupiter:5.9.1'
    testRuntimeOnly 'org.junit.jupiter:junit-jupiter-engine:5.9.1'

    compileOnly 'javax.servlet:javax.servlet-api:4.0.1'
    compileOnly 'javax.servlet.jsp:javax.servlet.jsp-api:2.3.3'

    // This dependency is used by the application.
    implementation 'com.google.guava:guava:31.1-jre'
    implementation 'org.glassfish:javax.json:1.1.4'
    implementation 'org.apache.logging.log4j:log4j-api:2.20.0'
    implementation 'org.apache.logging.log4j:log4j-core:2.20.0'

    annotationProcessor 'org.apache.logging.log4j:log4j-api:2.20.0'
    annotationProcessor 'org.apache.logging.log4j:log4j-core:2.20.0'
}
```

- 프로젝트 
    - gradle로 생성한 폴더에서 아래 명령실행, 결과는  BUILD SUCCESSFUL
    - 경량 웹서버 gretty를 사용하고 싶었으나, **빌드 오류**로 제거


```shell
> .\gradlew tasks
```

### Tomcat 서버 설정
- Community Server Connector 
    - Server Action
        - Edit Configuration File...
        - config/server.xml open

```xml
      <Host name="localhost"  appBase="webapps"
            unpackWARs="true" autoDeploy="true">
          <Context path="" docBase="D:/.../src/main/webapp"></Context>
            ...
      </Host>
    </Engine>
  </Service>
</Server>
```

### index.jsp
```html
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html lang="ko">
    <head>
        <title>VS CODE에서 JSP 동작시키기</title>
    </head>
    <body>
        <h1>첫 페이지입니다.</h1>
    </body>
</html>
```

### Tomcat 서버 실행

실행결과

<img src="https://raw.githubusercontent.com/hugoMGSung/hungout-with-springboot/main/images/sb0003.png" width="500">