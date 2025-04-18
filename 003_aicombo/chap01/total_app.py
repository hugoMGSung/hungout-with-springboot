# FastAPI 웹 프레임워크와 예외처리를 위한 HTTPException 클래스 임포트
from fastapi import FastAPI, HTTPException
# 요청 바디 검증을 위한 Pydantic 모델 클래스 임포트
from pydantic import BaseModel

# FastAPI 애플리케이션 인스턴스 생성
app = FastAPI()

# 클라이언트가 전송할 데이터 구조를 정의하는 Pydantic 모델
class Item(BaseModel):
    name: str
    description: str = None
    price: float
    tax: float = None

# 임시로 데이터를 저장할 딕셔너리 형태의 "가짜 DB"
items = {}

# 루트 경로 ('/')에 GET 요청이 오면 간단한 인사 메시지를 반환
@app.get('/')
async def read_root():
    return { 'Hello' : 'FastAPI' }


# 특정 아이템 ID에 대해 GET 요청이 오면 해당 아이템을 반환
@app.get('/items/{item_id}')
async def read_item(item_id: int):
    # 해당 item_id가 items 딕셔너리에 없으면 404 오류 반환
    if item_id not in items:
        raise HTTPException(status_code=404, detail='Item not found')

    return items[item_id]

# 새 아이템을 생성하는 POST 요청을 처리
@app.post('/items/')
async def create_item(item: Item):
    # 현재 저장된 아이템 개수 + 1을 새로운 ID로 사용
    item_id = len(items) + 1
    # 새 아이템을 딕셔너리에 저장
    items[item_id] = item

    # 저장된 아이템 ID와 아이템 데이터를 합쳐 반환
    # 파이썬의 딕셔너리 언패킹(dictionary unpacking) 문법
    # **는 딕셔너리를 "키=값 쌍" 형태로 풀어서 함수에 전달하거나, 새로운 딕셔너리를 만들 때 안의 내용을 펼쳐 넣을 때
    return { 'item_id' : item_id, **item.model_dump() }

    # *args 위치 인자를 튜플로 받음
    # **kwargs 키워드 인자를 딕셔너리로 받음