#define ac_up 6
#define ac_down 7

void setup() {
  pinMode(ac_up, OUTPUT);
  pinMode(ac_down, OUTPUT);

}

void loop() {
  digitalWrite(ac_up, LOW);
  digitalWrite(ac_down, HIGH);
  delay(5500);
  digitalWrite(ac_up, LOW);
  digitalWrite(ac_down, LOW);
}
