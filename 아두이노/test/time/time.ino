#include <HCMotor.h>
HCMotor HCMotor;

#define rail_clk 4
#define rail_dir 5

int rail_speed = 5;
int rail_stop = 0;

void setup() {
  Serial.begin(9600);

    
  HCMotor.Init();
  HCMotor.attach(0, STEPPER, rail_clk, rail_dir);
  HCMotor.Steps(0, CONTINUOUS);
  HCMotor.DutyCycle(0, rail_stop);
  
}

int mode = -1;
long signed int last = 0, now;
void loop() {
  if(Serial.available() > 0) {
    mode = Serial.parseInt();
  }
  if(mode == 1) {
    Serial.println(millis());
    HCMotor.DutyCycle(0, rail_speed);
    HCMotor.Direction(0, REVERSE);
  }
  if(mode == 2) {
    Serial.println(millis());
    HCMotor.DutyCycle(0, rail_speed);
    HCMotor.Direction(0, FORWARD);
  }
  if(mode == 3) {
    HCMotor.DutyCycle(0, rail_stop);
    Serial.println(millis());
    Serial.println();
  }
}
