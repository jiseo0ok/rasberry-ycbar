#include <SoftwareSerial.h> // 통신용 라즈베리파이 시리얼로 사용
#include <HCMotor.h>  // 리니어 레일 사용 위한 헤더파일
#include <Servo.h>  // 컵홀더 서보모터 사용 위한 헤더파일

SoftwareSerial raspberry_serial(A0, 3);  // 통신용 라즈베리파이 시리얼 선언
HCMotor HCMotor;  // 리니어 레일 사용 선언

Servo servo_left; // 컵홀더 왼쪽 서보모터
Servo servo_right;  // 컵홀더 오른쪽 서보모터

// 리니어 레일 핀
#define rail_clk 4
#define rail_dir 5

// 리니어 액츄에이터 핀
#define ac_up 6
#define ac_down 7

// 동작 관련 필요 값
int module[6];
String value = "";
int distance[6] = {10638, 10390, 10120, 9379, 10670, 8653};

int rail_speed = 5;
int rail_stop = 0;

int module_sum = 0;

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
    Serial.println();
    Serial.println("communication completed");
    Serial.println();
    

    // 초기 리니어 액츄에이터 위치 -> 올라간 상태
    // 동작 시작 시 올라간 상태의 리니어 액츄에이터 위치를 내림
    digitalWrite(ac_up, LOW);
    digitalWrite(ac_down, HIGH);
    delay(6500);
    digitalWrite(ac_up, LOW);
    digitalWrite(ac_down, LOW);
    Serial.println("ready");

    // 컵 디스펜서 작동
    cup();
    Serial.println("the cup is ready");

    // 총 음료 횟수 계산
    for(int i = 0; i < 6; i++) module_sum += module[i];
    
    // 음료 제작
    if(module_sum != 0) make(module, module_sum);
    Serial.println("beverage production is complete");

    // 제작 종료
    // 리니어 액츄에이터 위치를 위쪽으로 보냄
    digitalWrite(ac_up, HIGH);
    digitalWrite(ac_down, LOW);
    delay(6500);
    digitalWrite(ac_up, LOW);
    digitalWrite(ac_down, LOW);
    Serial.println("operation complete");

    // 통신용 라즈베리파이로 동작 종료 알리고 기존 값 초기화
    raspberry_serial.println("end");
    Serial.println("notification completed");
    
    module_sum = 0;
    value = "";
    Serial.println("clear");
    Serial.println();
  }
}

// 컵 디스펜서에서 컵 홀더로 컵을 빼는 함수
void cup() { 
  // 컵홀더 서보모터 설정
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

  // 컵홀더 동작 종료 시 연결 종료
  servo_left.detach();
  servo_right.detach();
}

// 칵테일 제조 함수
void make(int module[6], int module_sum) { 
  // 동작에 필요한 변수 정의
  int now_module_sum = 0;
  long now_distance = 0;

  for(int i = 0; i < 6; i++) {
    // i번째 모듈까지 이동 후 이동한 거리 저장
    rail_right(distance[i]);
    now_distance += distance[i];

    // i번째 모듈이 들어간다면 들어가는 만큼 리니어 액츄에이터 동작
    if(module[i] != 0) {
      for(int j = 0; j < module[i]; j++) ac_updown();
      now_module_sum += module[i];
    }

    // 총 모듈 횟수만큼 들어갔다면 종료
    if(now_module_sum == module_sum) break;
  }

  // 시작 위치로 이동
  rail_left(now_distance);
}

// 리니어 레일을 오른쪽으로 second 밀리초만큼 보내는 함수
void rail_right(int second) {
  HCMotor.DutyCycle(0, rail_speed);
  HCMotor.Direction(0, REVERSE);
  delay(second);

  HCMotor.DutyCycle(0, rail_stop);
}

// 리니어 레일을 왼쪽으로 second 밀리초만큼 보내는 함수
void rail_left(long second) {
  HCMotor.DutyCycle(0, rail_speed);
  HCMotor.Direction(0, FORWARD);
  delay(second);

  HCMotor.DutyCycle(0, rail_stop);
}

// 리니어 액츄에이터가 위로 올라갔다가 내려오도록 하는 함수
void ac_updown() {
  digitalWrite(ac_up, HIGH);
  digitalWrite(ac_down, LOW);
  delay(6500);
  digitalWrite(ac_up, LOW);
  digitalWrite(ac_down, LOW);
  delay(3000);
  digitalWrite(ac_up, LOW);
  digitalWrite(ac_down, HIGH);
  delay(6500);
  digitalWrite(ac_up, LOW);
  digitalWrite(ac_down, LOW);
  delay(2000);
}
