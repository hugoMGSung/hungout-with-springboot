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