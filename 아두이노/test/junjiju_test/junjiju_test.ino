#include <HCMotor.h>
HCMotor HCMotor;

#define rail_clk 4
#define rail_dir 5

#define ac_up 6
#define ac_down 7

int rail_speed = 5;
int rail_stop = 0;

void setup() {
  Serial.begin(9600);

  HCMotor.Init();
  HCMotor.attach(0, STEPPER, rail_clk, rail_dir);
  HCMotor.Steps(0, CONTINUOUS);
  HCMotor.DutyCycle(0, rail_stop);

  pinMode(ac_up, OUTPUT);
  pinMode(ac_down, OUTPUT);
}

int mode = -1;
void loop() {
  if(Serial.available() > 0) {
    mode = (int)Serial.read();
  }
  while(Serial.available() == 0) {
    if(mode == 0) { // 전부 정지
      HCMotor.DutyCycle(0, rail_stop);
      digitalWrite(ac_up, LOW);
      digitalWrite(ac_down, LOW);
    }
    else if(mode == 10) { // 리니어 레일 정지
      HCMotor.DutyCycle(0, rail_stop);
    }
    else if(mode == 20) { // 액츄에이터 정지
      digitalWrite(ac_up, LOW);
      digitalWrite(ac_down, LOW);
    }
    else if(mode == 1) {  // 레일 오른쪽 이동
      HCMotor.DutyCycle(0, rail_speed);
      HCMotor.Direction(0, REVERSE);
    }
    else if(mode == 2) {  // 레일 왼쪽 이동
      HCMotor.DutyCycle(0, rail_speed);
      HCMotor.Direction(0, FORWARD);
    }
    else if(mode == 3) {  // 액츄에이터 위로
      digitalWrite(ac_up, HIGH);
      digitalWrite(ac_down, LOW);
    }
    else if(mode == 4) {  // 액츄에이터 아래로
      digitalWrite(ac_up, LOW);
      digitalWrite(ac_down, HIGH);
    }
  }
}
