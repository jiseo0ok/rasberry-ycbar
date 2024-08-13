void setup() {
 pinMode(6, OUTPUT);
 pinMode(7, OUTPUT);
 Serial.begin(9600);
}

void loop() {
  Serial.println("6high");
  digitalWrite(6, HIGH);
  digitalWrite(7, LOW);
  delay(7000);
  
  Serial.println("7high");
  digitalWrite(7, HIGH);
  digitalWrite(6, LOW);
  delay(7000);
}
