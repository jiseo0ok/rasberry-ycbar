#include <SoftwareSerial.h>

SoftwareSerial mySoftWareSerial(2, 3);

int a;

void setup() {
  Serial.begin(9600);          //serial포트의 시리얼통신속도 지정, 데스크탑 컴퓨터와 시리얼 통신용
  mySoftWareSerial.begin(9600);         // Serial1포트의 시리얼통신속도 지정, 라즈베리파이와의 통신용
}

void loop() {
  if(mySoftWareSerial.available()>0){         // serial1 포트에 들어온 데이터가 있으면
     while(mySoftWareSerial.available()>0){            
        a = mySoftWareSerial.parseInt();        // 값을 읽어들여 숫자로 변환한 후 변수에  할당
        Serial.println(a);
        mySoftWareSerial.println("seccess");
      }         // serial 포트로 각도값을 씀(컴퓨터에서 확인용)
 }
}
