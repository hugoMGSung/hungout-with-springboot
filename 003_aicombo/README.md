## 스프링부트와 파이썬AI 연동

자바 스프링부트 프로젝트와 파이썬 AI 프로젝트 연결하기(부크크/허진경) 참조

### FastAPI
- Python으로 작성된 웹 프레임워크

#### 설치
- 설치 명령어

    ```shell
    > pip install fastapi uvicorn
    ```

##### ASGI
- Asynchronous Server Gateway Interface

#### 첫번째 심플테스트
- main.py 작성 후
- 콘솔에서 아래와 같이 명령어 입력

    ```shell
    > uvicorn main:app --reload
    ```

    <img src="../images/sb0007.png" width="700">

- 실행결과

    <img src="../images/sb0008.png" width="700">

#### 두번째 데이터모델링
- index.py 작성
- 데이터 모델링 테스트
- Pydantic 모듈
    - 데이터 모델링을 쉽게 처리해주는 라이브러리

- 작성 후 아래와 같이 실행

    ```shell
    > uvicorn index:app --reload
    ```

- POSTMan에서 테스트 결과

    <img src="../images/sb0009.png" width="600">

#### 세번째 문서화
- FastAPI는 자동으로  API 문서를 생성함

- 실행결과

    <img src="../images/sb0010.png" width="700">

#### 네번째 FastAPI 미들웨어
- Request, Response 사이에 특정 작업 수행에 사용되는 함수와 클래스
- 로그파일 남기게 처리

- 실행결과

    <img src="../images/sb0011.png" width="700">

#### 다섯번째 종합
- 실행포트 변경하는 방법

```shell
> uvicorn total_app:app --host 0.0.0.0 --port 8080
```

- 설정
    - Chrome 확장 프로그램중 JSON Viewer 설치

- 실행
    1. 기본 실행 후
    2. PostMan에서 Post로 데이터 입력
    3. 브라우저에서 Get으로 확인

    <img src="../images/sb0013.png" width="700">

    <img src="../images/sb0012.png" width="700">

    4. JSON Viewer 적용 후

    <img src="../images/sb0014.png" width="700">



### Spring Boot 웹앱

#### JDK 설치 
- 생략

#### Spring Boot 프로젝트 생성
1. Spring Initializr
    - Spring Boot Version : 3.3.10
    - Project Lang : Java
    - Group Id : com.hugo83
    - Artificial Id : aiapp
    - packaing type : War
    - Java verison : 21 (현재 설치버전)
    - Dependencies
        - Spring Boot DevTools
        - Thymeleaf
        - Spring Web
        - Spring Reactive Web

2. resources/application.properties
    - spring.output.ansi.enabled=always 추가
    - spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration

3. HomeController 클래스 추가

4. resources/templates/index.html 추가

5. 실행

    <img src="../images/sb0015.png" width="700">


### 파이썬 FastAPI + Spring Boot

<img src="../images/sb0101.png" width="700">

#### backend
1. Spring Boot 프로젝트 만들기
    - chap03/backend/myapp
2. Python FastAPI 프로젝트 
    - cahp03/backend/aiserver/main.py
    - [버전소스](https://github.com/hugoMGSung/hungout-with-springboot/blob/Python_FastAPI_0.0.0.1/003_aicombo/chap03/backend/aiserver/main.py)
3. 이미지 객체 탐지서비스 구현
    - cahp03/backend/aiserver/main.py
    - [버전소스](https://github.com/hugoMGSung/hungout-with-springboot/blob/Python_FastAPI_0.0.0.3/003_aicombo/chap03/backend/aiserver/main.py)

4. 비동기 요청 테스트
    - cahp03/backend/aiserver/test.py
5. 실행 결과

    - 서버 로그

    <img src="../images/sb0016.png" width="700">

    - 파이썬 로그

    <img src="../images/sb0017.png" width="700">

6. Spring Boot 작업
    - HomeController, index.html 작업
        - controller/HomeController.java
        - resources/template/index.html
    - WebClient 빈 설정
        - config/WebClientConfig.java
    - 요청 컨트롤러 RestReqController 작업
        - controller/RestReqController.java
    - 비동기 요청을 위한 HTML 페이지
        - resources/template/index.html 수정
    - 실행결과

    <img src="../images/sb0018.png" width="700">

7. 프론트엔드 디자인 변경 요


### MQTT로 실시간 객체 탐지 영상전송/수신

<img src="../images/sb0102.png" width="700">

#### Total

1. camera.py 작성
2. Spring Boot 수정
    - config/WebConfig.java 작성

3. ...

4. Mosquitto 서버 설정

    - mosquitto.conf 설정

    ```txt
    listener 9001
    protocol websockets
    ```


9. 실행결과

    <img src="../images/sb0019.png" width="700">

- 정리 필요!