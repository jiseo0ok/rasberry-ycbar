void setup() {
  Serial.begin(9600);
}

int module[6];
String value = "";
void loop() {
  while(value.length() != 7) {
    value = Serial.readString();
  }
  Serial.println("start");
  for(int i = 0; i < 6; i++) {
    module[i] = value[i] - '0';
  }
  for(int i = 0; i < 6; i++) {
    Serial.println(module[i]);
  }
  Serial.println("end");
  
  value = "";
}
