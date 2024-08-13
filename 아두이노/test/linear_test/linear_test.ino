#include <HCMotor.h>

int DIR = 8;  // 방향
int CLK = 9;  // 회전

HCMotor HCMotor;

int Speed = 5;

void setup() 
{
  /* 라이브러리 초기화 */
  HCMotor.Init();

  /* 모터0을 스텝모터로 설정하고 연결된 핀을 지정 */
  HCMotor.attach(0, STEPPER, CLK, DIR);

  /* 모터를 연속동작모드로 설정*/
  HCMotor.Steps(0,CONTINUOUS);
  /* 속도설정 */
  HCMotor.DutyCycle(0, Speed);
  
  Serial.begin(9600);  
}

void loop() 
{
  HCMotor.Direction(0, FORWARD);
  delay(5000);
  HCMotor.DutyCycle(0, 0);
  delay(5000);
  HCMotor.DutyCycle(0, Speed);
  HCMotor.Direction(0, REVERSE);
  delay(5000);
  HCMotor.DutyCycle(0, 0);
  delay(5000);
  HCMotor.DutyCycle(0, Speed);
}
