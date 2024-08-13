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

void loop() {
  // put your main code here, to run repeatedly:

}
