#include <SoftwareSerial.h>

SoftwareSerial mySoftWareSerial(A0, 3);

void setup() {
  Serial.begin(9600);          //serial포트의 시리얼통신속도 지정, 데스크탑 컴퓨터와 시리얼 통신용
  mySoftWareSerial.begin(9600);         // Serial1포트의 시리얼통신속도 지정, 라즈베리파이와의 통신용
  Serial.println("start");
}

int module[6];
String value = "";
void loop() {
  if(mySoftWareSerial.available() > 0) { 
    value = mySoftWareSerial.readString();
    
    Serial.println(value);
    
    Serial.println("change start");
    for(int i = 0; i < 6; i++) {
      module[i] = value[i] - '0';
    }
    
    for(int i = 0; i < 6; i++) {
      Serial.print((String)"module " + i + " : " + module[i] + "   ");
    }
    Serial.println();
    
    mySoftWareSerial.println("end");
    
    value = "";
  }
}
