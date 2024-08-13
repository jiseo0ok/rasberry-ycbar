#include<Servo.h>
Servo servo_left;
Servo servo_right;

void setup() {
  Serial.begin(9600);
}

void loop() {
  servo_left.attach(12);
  servo_right.attach(13);
  
  servo_left.write(0);
  servo_right.write(180);
  delay(1000);

  for(int i = 0; i < 181; i += 10) {
    servo_left.write(i);
    servo_right.write(180 - i);
    delay(500);
  }
  
  servo_left.detach();
  servo_right.detach();
  delay(3000);
}
