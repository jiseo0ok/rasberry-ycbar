#include <Stepper.h>

const int stepsPerRevolution = 2048;
Stepper myStepper(stepsPerRevolution, 11, 9, 10, 8);

void setup() {
  Serial.begin(9600);
  myStepper.setSpeed(14);
}

int mode = -1;

void loop() {
  if(Serial.available() > 0) {
    while(true) {
      mode = Serial.parseInt(); 
      if(mode == 1) {
        Serial.println(millis());
        myStepper.step(-stepsPerRevolution);
        myStepper.setSpeed(14);
      }
      if(mode == 2) {
        Serial.println(millis());
        myStepper.step(stepsPerRevolution);
        myStepper.setSpeed(14);
      }
      if(mode == 3) {
        Serial.println(millis());
        Serial.println();
      }
    }
  }
}
