#include <Adafruit_NeoPixel.h>

Adafruit_NeoPixel strip = Adafruit_NeoPixel(59, 6, NEO_GRB + NEO_KHZ800);

void setup() {
  strip.begin();
  strip.show();
  strip.setBrightness(50);
  Serial.begin(9600);
}

int val = 0;

void updateValue(){
  while(Serial.available()>0){
    byte b = Serial.read();
    for(int i=0;i<8;i++){
      if(bitRead(b, i)){
        val = i; 
      }
    } 
  }
}

void loop() {
  updateValue();
  switch (val) {
    case 1:
      theaterChase(strip.Color(127,127,127), 1);
      break;
    case 2:
      rainbowCycle(2);
      break;
    case 3:
      theaterChase(strip.Color(127, 127, 0), 3);
      break;
    case 4:
      theaterChase(strip.Color(0, 0, 127), 4);
      break;
    case 5:
      theaterChase(strip.Color(127, 0, 0), 5);
      break;
  }
}

void rainbowCycle(int value) {
  uint8_t wait = 20;
  uint16_t i, j;

  for(j=0; j<256*5; j++) { // 5 cycles of all colors on wheel
    for(i=0; i< strip.numPixels(); i++) {
      strip.setPixelColor(i, Wheel(((i * 256 / strip.numPixels()) + j) & 255));
    }
    strip.show();
    delay(wait);

    updateValue();
    if(value!=val){
      return;
    }
  }
}

void theaterChase(uint32_t c, int value) {
  uint8_t wait = 50;
  for (int j=0; j<10; j++) {  //do 10 cycles of chasing
    for (int q=0; q < 3; q++) {
      for (int i=0; i < strip.numPixels(); i=i+3) {
        strip.setPixelColor(i+q, c);    //turn every third pixel on
      }
      strip.show();

      delay(wait);

      for (int i=0; i < strip.numPixels(); i=i+3) {
        strip.setPixelColor(i+q, 0);        //turn every third pixel off
      }

      updateValue();
      if(value!=val){
        return;
      }
    }
  }
}

// Input a value 0 to 255 to get a color value.
// The colours are a transition r - g - b - back to r.
uint32_t Wheel(byte WheelPos) {
  WheelPos = 255 - WheelPos;
  if(WheelPos < 85) {
    return strip.Color(255 - WheelPos * 3, 0, WheelPos * 3);
  }
  if(WheelPos < 170) {
    WheelPos -= 85;
    return strip.Color(0, WheelPos * 3, 255 - WheelPos * 3);
  }
  WheelPos -= 170;
  return strip.Color(WheelPos * 3, 255 - WheelPos * 3, 0);
}
