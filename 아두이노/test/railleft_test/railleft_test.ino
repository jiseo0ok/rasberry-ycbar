#include <HCMotor.h>  // 리니어 레일 사용 위한 헤더파일
HCMotor HCMotor;

// 리니어 레일 핀
#define rail_clk 4
#define rail_dir 5

int rail_speed = 5;
int rail_stop = 0;

void setup() {
  // 리니어 레일 초기화
  HCMotor.Init();
  HCMotor.attach(0, STEPPER, rail_clk, rail_dir);
  HCMotor.Steps(0, CONTINUOUS);
  HCMotor.DutyCycle(0, rail_stop);
}

void loop() {
  HCMotor.DutyCycle(0, rail_speed);
  HCMotor.Direction(0, FORWARD);
  delay(59850);

  HCMotor.DutyCycle(0, rail_stop);
  delay(1000000000000000000);
}
