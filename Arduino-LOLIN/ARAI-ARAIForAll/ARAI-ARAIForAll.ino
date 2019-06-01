#include <ESP8266WiFi.h>
#include <FirebaseArduino.h>
#include <Adafruit_NeoPixel.h>
#define FIREBASE_HOST "luna-ai-secretary.firebaseio.com"
#define FIREBASE_AUTH "sAR8yXUhP0KTmV8FMTfYfnmPwb1KjAvNSbCHNnza"
#define WIFI_SSID "Angelhackathon"
#define WIFI_PASSWORD "angel1234"
#define neopixel_1 5
#define neopixel_2 6
#define neopixel_3 7

Adafruit_NeoPixel strip = Adafruit_NeoPixel(3, neopixel_1, NEO_GRB + NEO_KHZ800);
Adafruit_NeoPixel strip = Adafruit_NeoPixel(3, neopixel_2, NEO_GRB + NEO_KHZ800);
Adafruit_NeoPixel strip = Adafruit_NeoPixel(3, neopixel_3, NEO_GRB + NEO_KHZ800);

void setup() {
  Serial.begin(9600);
  pinMode(relayPin, OUTPUT);
  // connect to wifi.
  WiFi.begin(WIFI_SSID, WIFI_PASSWORD);
  Serial.print("connecting");
  while (WiFi.status() != WL_CONNECTED) {
    Serial.print(".");
    delay(500);
  }
  Serial.println();
  Serial.print("connected: ");
  Serial.println(WiFi.localIP());
  
  Firebase.begin(FIREBASE_HOST, FIREBASE_AUTH);
}

void loop() {
  String path = "";
  String data = "";
  if(Firebase.available()) {
    FirebaseObject event = Firebase.readEvent();
    String eventType = event.getString("type");
    eventType.toLowerCase();
    if(eventType == "put") {
      path = event.getString("path");
      data = event.getString("data");
      Serial.print("DATA : ");
      Serial.println(data);
      Serial.print("PATH : ");
      Serial.println(path);
    }
  }
}
void removeData(String path) {
  Firebase.remove("/queue" + path);
}
