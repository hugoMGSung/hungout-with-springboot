from fastapi import FastAPI     # FastAPI 웹 프레임워크에서 핵심 객체와 타입 로드
from pydantic import BaseModel  # Pydantic은 데이터 검증과 직렬화를 위한 라이브러리
# BaseModel은 클라이언트 요청 데이터의 스키마를 정의할 때 사용

app = FastAPI()                 # FastAPI 앱 인스턴스를 생성

class Item(BaseModel):          # 클라이언트가 전송할 데이터 형식을 정의
    name: str
    description: str = None
    price: float
    tax: float = None

@app.post('/items/')                # HTTP POST 요청을 '/items/' 경로에서 처리하는 API 엔드포인트를 정의
async def create_item(item: Item):  # 클라이언트가 JSON 형식으로 데이터를 전송하면, 해당 데이터를 Item 모델로 파싱
    return item                     # 수신된 데이터를 그대로 JSON 형태로 응답