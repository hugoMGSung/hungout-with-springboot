from fastapi import FastAPI
from starlette.middleware.base import BaseHTTPMiddleware
import logging

logging.basicConfig(
    filename='log.txt',
    level=logging.INFO,
    format="%(asctime)s - %(levelname)s - %(message)s"
)

app = FastAPI()

class LoggingMiddleware(BaseHTTPMiddleware):
    async def dispatch(self, request, call_next):
        logging.info(f'Req: {request.method} {request.url}')
        response = await call_next(request)
        logging.info(f'Status code: {response.status_code}')
        return response
    
app.add_middleware(LoggingMiddleware)

@app.get('/items/{item_id}')
async def read_item(item_id: int):
    return { 'item_id': item_id }