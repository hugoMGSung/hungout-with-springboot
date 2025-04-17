import io, base64
from PIL import Image
import numpy as np
from ultralytics import YOLO
import cv2
import json
import paho.mqtt.client as mqtt

model = YOLO('yolov8n.pt')

broker = 'localhost'
# port = 1883
port = 9001  # WebSocket 포트
topic = '/camera/objects'

# MQTT 클라이언트
client = mqtt.Client(transport="websockets", protocol=mqtt.MQTTv5)

def on_connect(client, userdata, flags, reason_code, properties=None):
    print(f"[MQTT] 연결됨, reason={reason_code}")

client.on_connect = on_connect
client.connect(broker, port, 60)  # 포트는 WebSocket용 포트로 설정
client.loop_start()

# 클래스 라벨별 색상 설정 함수
def get_colors(num_colors):
    np.random.seed(0)
    colors = [tuple(np.random.randint(0, 255, 3).tolist()) for _ in range(num_colors)]
    return colors

class_names = model.names
num_classes = len(class_names)
colors = get_colors(num_classes)

def detect_objects(image: np.array):
    results = model(image, verbose=False)   # 객체 탐지
    class_name = model.names    # 클래스 이름

    for result in results:
        boxes = result.boxes.xyxy
        confidences = result.boxes.conf  # 신뢰도
        class_ids = result.boxes.cls

        for box, confidence, class_id in zip(boxes, confidences, class_ids):
            x1, y1, x2, y2 = map(int, box) # 좌표를 정수로 변환
            label = class_name[int(class_id)]
            cv2.rectangle(image, (x1,y1), (x2,y2), (255,0,0), 2)
            cv2.putText(image, f'{label} {confidence:.2f}', (x1,y1), cv2.FONT_HERSHEY_SIMPLEX, 0.9, (255,0,0), 2)
            
    return image

# 카메라 프레임 캡쳐
# cap = cv2.VideoCapture('../../../../data/traffic_test.mp4')
cap = cv2.VideoCapture(0)

while cap.isOpened():
    ret, frame = cap.read()
    if not ret: break

    result_image = detect_objects(frame)

    # 이미지 결과를 base64 인코딩
    _, buffer = cv2.imencode('.jpg', result_image)
    jpg_as_text = base64.b64encode(buffer).decode('utf-8')

    # 객체 탐지 이미지 전송
    payload = json.dumps({'image': jpg_as_text})
    client.publish(topic, payload)

    # 프레임을 화면에 표시
    cv2.imshow('Frame', np.array(result_image))

    if cv2.waitKey(1) & 0xFF == ord('q'): break

# 리소스 해제
cap.release()
cv2.destroyAllWindows()
client.disconnect()