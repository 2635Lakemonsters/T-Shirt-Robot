#include <Adafruit_NeoPixel.h>
#include <Wire.h>
#define PIN 6

// Parameter 1 = number of pixels in strip
// Parameter 2 = pin number (most are valid)
// Parameter 3 = pixel type flags, add together as needed:
//   NEO_KHZ800  800 KHz bitstream (most NeoPixel products w/WS2812 LEDs)
//   NEO_KHZ400  400 KHz (classic 'v1' (not v2) FLORA pixels, WS2811 drivers)
//   NEO_GRB     Pixels are wired for GRB bitstream (most NeoPixel products)
//   NEO_RGB     Pixels are wired for RGB bitstream (v1 FLORA pixels, not v2)
Adafruit_NeoPixel strip = Adafruit_NeoPixel(60, PIN, NEO_GRB + NEO_KHZ800);

//Based heavily off of 3574's LED Arduino code, modified and appended by 2635

void setup()
{
  strip.begin();
  strip.show();
  Wire.begin(84);
  Wire.onReceive(receiveEvent);
  pinMode(13, OUTPUT);
  Serial.begin(9600);

}

int showPattern = -1;
long watchDog = -1;
uint32_t colorRed = strip.Color(255, 0, 0);

void loop()
{
  //int showPattern = Wire.read();
  //digitalWrite(13, HIGH);
  Serial.print("In loop  "); Serial.println(showPattern);
  switch (showPattern)
  {
    case 0: Serial.println("ooh.  a rainbow"); rainbowCycle(5);  break;
    case 1: colorWipe(colorRed, 10);  break;
    case 2: marchRedWhiteBlue();  break;
    default:    solidColor(strip.Color(0, 180, 100));
      strip.show(); break;
  }

  if (millis() - watchDog > 200)
  {
    showPattern = -1;
  }

}


//Wipe a color LED by LED through the strip, then clear it. Wait is delay between LED setting.
void colorWipe(uint32_t c, uint8_t wait) {
  for (uint16_t i = 0; i < strip.numPixels(); i++) {
    if (showPattern != 1)
    {
      return;
    }
    if (millis() - watchDog > 200)
    {
      showPattern = -1;
      return;
    }
    strip.setPixelColor(i, c);
    strip.show();
    delay(wait);
  }

  for (uint16_t i = 0; i < strip.numPixels(); i++)
  {
    if (showPattern != 1)
    {
      return;
    }
    if (millis() - watchDog > 200)
    {
      showPattern = -1;
      return;
    }
    strip.setPixelColor(i, strip.Color(0, 0, 0));
    strip.show();
    delay(wait);
  }
}


void marchRedWhiteBlue()
{
  uint32_t colors[] = { strip.Color(255, 0, 0), strip.Color(0, 0, 255), strip.Color(155, 155, 155) };
  // Turn everything black
  solidColor(strip.Color(0, 0, 0));

  int frame = 0;

  while ( frame < 50 ) {
    for (int index = 0; index < strip.numPixels(); index++)
    {
      //strip.setPixelColor((index  + (frame % 15)) % 60, colors[ ((index)/5 + frame) % 3 ]);
      strip.setPixelColor((index + frame) % 60 , colors[ ((index) / 5) % 3 ]);
    }
    if (showPattern != 2)
    {
      return;
    }
    strip.show();
    delay(50);
    frame++;
  }

}

// Slightly different, this makes the rainbow equally distributed throughout
void rainbowCycle(uint8_t wait) {
  uint16_t i, j;



  for (j = 0; j < 256 * 5; j++) { // 5 cycles of all colors on wheel
    if (showPattern != 0)
    {
      return;
    }

    if (millis() - watchDog > 200)
    {
      showPattern = -1;
      return;
    }

    for (i = 0; i < strip.numPixels(); i++) {
      strip.setPixelColor(i, Wheel(((i * 256 / strip.numPixels()) + j) & 255));
    }
    strip.show();
    delay(wait);
  }
}



//UTIL METHODS. Call them if you want. See if I care.

void solidColor(uint32_t c) {
  for (uint16_t i = 0; i < strip.numPixels(); i++) {
    strip.setPixelColor(i, c);

  }
}

void receiveEvent(int howMany)
{
  digitalWrite(13, HIGH);
  //static uint8_t ct = 0;
  int x = Wire.read();
  showPattern = x;
  watchDog = millis();
  Serial.println(showPattern);
}

// Input a value 0 to 255 to get a color value.
// The colours are a transition r - g - b - back to r.
uint32_t Wheel(byte WheelPos) {
  if (WheelPos < 85) {
    return strip.Color(WheelPos * 3, 255 - WheelPos * 3, 0);
  } else if (WheelPos < 170) {
    WheelPos -= 85;
    return strip.Color(255 - WheelPos * 3, 0, WheelPos * 3);
  } else {
    WheelPos -= 170;
    return strip.Color(0, WheelPos * 3, 255 - WheelPos * 3);
  }
}

// Run the length of the strip one light at a time
void colorSwipe(uint32_t color, uint8_t wait) {
  for (int i = 0; i <= strip.numPixels(); i++) {
    // going out of bounds does not break things, so checks removed for readability
    // clean up behind it
    strip.setPixelColor(i - 1, 0);
    //if (i < strip.numPixels())
    strip.setPixelColor(i, color);
    strip.show();
    delay(wait);
  }
}


