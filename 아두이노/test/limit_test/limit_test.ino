void setup() {
  // put your setup code here, to run once:
  pinMode(2, INPUT);
  pinMode(3, INPUT);
  Serial.begin(9600);
}

void loop() {
  // put your main code here, to run repeatedly:
  if(digitalRead(2) == HIGH) {
    Serial.println("2");
    delay(300);
  }
  if(digitalRead(3) == HIGH) {
    Serial.println("3");
    delay(300);
  }
}
