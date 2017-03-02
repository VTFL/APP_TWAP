/*
Donc, le Gpio 2 pi4j correspond au 27 gpio (7 vers le bas en partant et comptant le carré) #MettreDansLeBonSens
*/

#define led 9
#define led2 8
#define button 2
#include <stdio.h>
#include <time.h>

volatile int tabLed[] = {50, 52};
volatile int choixPassager=0;
volatile int tailleTab=0;

// the setup routine runs once when you press reset:
void setup() {
  // declare pin 9 to be an output:
  Serial.begin(9600);

  tailleTab = (sizeof(tabLed)/sizeof(int));

  for(int i=0; i < tailleTab; i++) {
    pinMode(tabLed[i], OUTPUT);   
  }
  
}

// the loop routine runs over and over again forever:
void loop() {
  // set the brightness of pin 9:
  choixPassager = random(tailleTab);
  Serial.println(choixPassager);
  digitalWrite(tabLed[choixPassager],HIGH);
  delay(800);
  digitalWrite(tabLed[choixPassager],LOW);
  delay(800);
  
}


