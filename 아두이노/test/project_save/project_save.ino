#include <SoftwareSerial.h> // 통신용 라즈베리파이 시리얼로 사용
#include <HCMotor.h>  // 리니어 레일 사용 위한 헤더파일

SoftwareSerial raspberry_serial(2, 3);  // 통신용 라즈베리파이 시리얼 선언
HCMotor HCMotor;  // 리니어 레일 사용 선언

// 리니어 레일 핀
#define rail_clk 4
#define rail_dir 5

// 리니어 액츄에이터 핀
#define ac_up 6
#define ac_down 7

// 동작 관련 필요 값
int module[6];
String value = "";

int rail_speed = 5;
int rail_stop = 0;

void setup() {
  // 통신 관련 초기화
  Serial.begin(9600);
  raspberry_serial.begin(9600);

  // 리니어 레일 초기화
  HCMotor.Init();
  HCMotor.attach(0, STEPPER, rail_clk, rail_dir);
  HCMotor.Steps(0, CONTINUOUS);
  HCMotor.DutyCycle(0, rail_stop);

  // 리니어 액츄에이터 초기화
  pinMode(ac_up, OUTPUT);
  pinMode(ac_down, OUTPUT);
}

void loop() {
  // 통신용 라즈베리파이와 통신이 될 시 동작 시작
  if(raspberry_serial.available() > 0) {
    // 통신용 라즈베리파이에서 각 모듈 값을 받아옴
    value = raspberry_serial.readString();
    Serial.println("value : " + value);

    for(int i = 0; i < 6; i++) module[i] = value[i] - '0';
    for(int i = 0; i < 6; i++) Serial.print((String)"module " + i + " : " + module[i] + "   ");
    Serial.println("communication completed");
    Serial.println();
    

    // 초기 리니어 액츄에이터 위치 -> 올라간 상태
    // 동작 시작 시 올라간 상태의 리니어 액츄에이터 위치를 내림
    digitalWrite(ac_up, LOW);
    digitalWrite(ac_down, HIGH);
    delay(5500);
    digitalWrite(ac_up, LOW);
    digitalWrite(ac_down, LOW);
    Serial.println("ready");
    Serial.println();

    // 컵 디스펜서 작동
    cup();
    Serial.println("the cup is ready");
    
    // 음료 제작
    make(module);
    Serial.println("beverage production is complete");

    // 제작 종료
    // 리니어 액츄에이터 위치를 위쪽으로 보냄
    digitalWrite(ac_up, HIGH);
    digitalWrite(ac_down, LOW);
    delay(5500);
    digitalWrite(ac_up, LOW);
    digitalWrite(ac_down, LOW);
    Serial.println("operation complete");
    Serial.println();

    // 통신용 라즈베리파이로 동작 종료 알리고 기존 값 초기화
    raspberry_serial.println("end");
    Serial.println("operation end notification completed");
    Serial.println();
    
    value = "";
  }
}

// 컵 디스펜서에서 컵 홀더로 컵을 빼는 함수
void cup() { 
}

// 칵테일 제조 함수
void make(int module[6]) { 
}

// 리니어 레일을 오른쪽으로 second 밀리초만큼 보내는 함수
void rail_right(int second) {
  HCMotor.DutyCycle(0, rail_speed);
  HCMotor.Direction(0, REVERSE);
  delay(second);

  HCMotor.DutyCycle(0, rail_stop);
}

// 리니어 레일을 왼쪽으로 second 밀리초만큼 보내는 함수
void rail_left(int second) {
  HCMotor.DutyCycle(0, rail_speed);
  HCMotor.Direction(0, FORWARD);
  delay(second);

  HCMotor.DutyCycle(0, rail_stop);
}

// 리니어 액츄에이터가 위로 올라갔다가 내려오도록 하는 함수
void ac_updown() {
  digitalWrite(ac_up, HIGH);
  digitalWrite(ac_down, LOW);
  delay(5500);
  
  digitalWrite(ac_up, LOW);
  digitalWrite(ac_down, LOW);
  delay(3000);
  
  digitalWrite(ac_up, LOW);
  digitalWrite(ac_down, HIGH);
  delay(5500);
  
  digitalWrite(ac_up, LOW);
  digitalWrite(ac_down, LOW);
  delay(2000);
}
