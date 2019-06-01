#define relayPin 2

void setup() {
  Serial.begin(9600);
  pinMode(relayPin, OUTPUT);
  Serial.println("[Program Started]");
}

void loop() {
  digitalWrite(relayPin, HIGH);
  delay(2000);
  digitalWrite(relayPin, LOW);
  delay(2000);
}
