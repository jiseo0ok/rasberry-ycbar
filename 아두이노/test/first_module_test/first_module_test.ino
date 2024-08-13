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
long signed int last = 0, now;
void loop() {
  HCMotor.DutyCycle(0, rail_speed);
  HCMotor.Direction(0, REVERSE);
  delay(21877);
  HCMotor.DutyCycle(0, rail_stop);
  digitalWrite(ac_up, HIGH);
  digitalWrite(ac_down, LOW);
  delay(5500);
  digitalWrite(ac_up, LOW);
  digitalWrite(ac_down, LOW);
  delay(3000);
  digitalWrite(ac_up, LOW);
  digitalWrite(ac_down, HIGH);
  delay(5500);
  HCMotor.DutyCycle(0, rail_speed);
  HCMotor.Direction(0, FORWARD);
  delay(21877);
  HCMotor.DutyCycle(0, rail_stop);
  delay(10000000000000000000);
}
