from fastapi import FastAPI
# Starlette의 BaseHTTPMiddleware를 불러와 사용자 정의 미들웨어를 만들기 위해 사용
from starlette.middleware.base import BaseHTTPMiddleware
# 로그를 남기기 위한 파이썬 표준 로깅 모듈
import logging

# 로그 설정:
# - 'log.txt' 파일에 로그 저장
# - 로그 레벨은 INFO 이상만 기록됨
# - 로그 메시지 포맷: 시간, 로그레벨, 메시지 순서로 구성
logging.basicConfig(
    filename='log.txt',
    level=logging.INFO,
    format="%(asctime)s - %(levelname)s - %(message)s"
)

app = FastAPI()         # FastAPI 앱 인스턴스 생성

# 사용자 정의 미들웨어 클래스 정의
# 모든 HTTP 요청과 응답을 가로채서 로그를 기록
class LoggingMiddleware(BaseHTTPMiddleware):
    async def dispatch(self, request, call_next):
        # 요청 정보 로그 (요청 메서드와 URL)
        logging.info(f'Req: {request.method} {request.url}')
         # 다음 처리 함수 호출 (요청 처리를 계속 진행)
        response = await call_next(request)
        # 응답 상태코드 로그
        logging.info(f'Status code: {response.status_code}')
        # 클라이언트에게 반환할 응답 객체 반환
        return response
    
# 앱에 미들웨어 추가 등록 (위에서 정의한 LoggingMiddleware 사용)
app.add_middleware(LoggingMiddleware)

@app.get('/items/{item_id}')
async def read_item(item_id: int):
    # 클라이언트에게 JSON 형식으로 item_id를 반환
    return { 'item_id': item_id }