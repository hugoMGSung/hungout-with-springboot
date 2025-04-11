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

#### 첫번째 구현
- main.py 작성 후
- 콘솔에서 아래와 같이 명령어 입력

    ```shell
    > uvicorn main:app --reload
    ```

    <img src="../images/sb0007.png" width="700">

- 실행결과

    <img src="../images/sb0008.png" width="700">