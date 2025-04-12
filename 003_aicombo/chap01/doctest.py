from fastapi import FastAPI

app = FastAPI(
    title='My API',
    description='This is a sample API',
    version='1.0.0',
    docs_url=None,  # Swagger UI 비활성화
    redoc_url='/documentation'  # ReDoc 
)

@app.get('/items/{item_id}')
async def read_item(item_id: int):
    return {'item_id': item_id}

