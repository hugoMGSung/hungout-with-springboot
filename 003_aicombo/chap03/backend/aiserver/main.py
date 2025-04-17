from fastapi import FastAPI, UploadFile, File, Form
from pydantic import BaseModel
import io, base64
import uvicorn
from PIL import Image
import numpy as np
from ultralytics import YOLO
import cv2

app = FastAPI()

model = YOLO('yolov8n.pt')

# 데이터 모델 정의
class DetectionResult(BaseModel):
    message: str
    image: str


def detect_objects(image: Image.Image):
    img = np.array(image)  # 이미지 넘파이 배열로 변환
    results = model(img)   # 객체 탐지
    class_name = model.names    # 클래스 이름

    for result in results:
        boxes = result.boxes.xyxy
        confidences = result.boxes.conf  # 신뢰도
        class_ids = result.boxes.cls

        for box, confidence, class_id in zip(boxes, confidences, class_ids):
            x1, y1, x2, y2 = map(int, box) # 좌표를 정수로 변환
            label = class_name[int(class_id)]
            cv2.rectangle(img, (x1,y1), (x2,y2), (255,0,0), 2)
            cv2.putText(img, f'{label} {confidence:.2f}', (x1,y1), cv2.FONT_HERSHEY_SIMPLEX, 0.9, (255,0,0), 2)
            
    result_image = Image.fromarray(img) # 결과이미지를 PIL로 변환
    return result_image

@app.get('/')
async def read_root():
    return { 'message' : 'Hello FastAPI' }

@app.post('/detect', response_model=DetectionResult)
async def detect_service(message: str = Form(...), file: UploadFile = File(...)):
    # 이미지 읽어서 PIL 이미지로 변환
    image = Image.open(io.BytesIO(await file.read()))

    # 알파채널 제거 후 RGB로 변환
    if image.mode != 'RGB':
        image = image.convert('RGB')

    # 객체 탐지 수행
    result_image = detect_objects(image)

    # 이미지 결과를 base64로 인코딩
    buffered = io.BytesIO()
    result_image.save(buffered, format='JPEG')

    img_str = base64.b64encode(buffered.getvalue()).decode('utf-8')

    return DetectionResult(message=message, image=img_str)


if __name__ == '__main__':
    uvicorn.run(app, host='0.0.0.0', port=8000)