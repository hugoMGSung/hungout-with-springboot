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

- 실행
    1. 기본 실행 후
    2. PostMan에서 Post로 데이터 입력
    3. 브라우저에서 Get으로 확인

    <img src="../images/sb0013.png" width="700">

    <img src="../images/sb0012.png" width="700">