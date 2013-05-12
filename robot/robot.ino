#include <Servo.h>

const int DELAY = 500;

const int SERVO = 2;
const int CC = 3;
const int CC_REVERSE = 4;
const int SPEAKER = 5;

const int FORWARD = 49;//comando "1"
const int BACKWARD = 50;//comando "2"
const int RIGHT = 51;//comando "3"
const int LEFT = 52;//comando "4"

const int ANGLE_FRONT = 90;
const int ANGLE_RIGHT = 135;
const int ANGLE_LEFT = 45;

const int DIR_FRONT = 0;
const int DIR_LEFT = 1;
const int DIR_RIGHT = 2;

int dir = DIR_FRONT;

Servo servoMotor;

void setup() {
  Serial.begin(9600);
  pinMode(SPEAKER, OUTPUT);
  pinMode(CC, OUTPUT);
  pinMode(CC_REVERSE, OUTPUT);
  servoMotor.attach(SERVO);      // conecta o motor ao pino de controle
  servoMotor.write(ANGLE_FRONT);      // gira o servo ate o ponto do meio
}

int incoming = 0;

void loop() {
  if (Serial.available() > 0) {
    incoming = Serial.read();
    
    switch (incoming) {
      case FORWARD:
        digitalWrite(CC_REVERSE, LOW);
        digitalWrite(CC, HIGH);
        delay(DELAY);
      break;
      
      case BACKWARD:
        digitalWrite(CC, LOW);
        digitalWrite(CC_REVERSE, HIGH);
        delay(DELAY/2);
        digitalWrite(SPEAKER, HIGH);
        delay(DELAY/2);
        digitalWrite(SPEAKER, LOW);
      break;
      
      case RIGHT:
        if (dir == DIR_FRONT) {
          servoMotor.write(ANGLE_RIGHT);
          dir = DIR_RIGHT;
        } else if (dir == DIR_LEFT) {
          servoMotor.write(ANGLE_FRONT);
          dir = DIR_FRONT;
        }
        delay(DELAY);
      break;
      
      case LEFT:
        if (dir == DIR_FRONT) {
          servoMotor.write(ANGLE_LEFT);
          dir = DIR_LEFT;
        } else if (dir == DIR_RIGHT) {
          servoMotor.write(ANGLE_FRONT);
          dir = DIR_FRONT;
        }
        delay(DELAY);
      break;
      
    }
        
    digitalWrite(CC, LOW);
    digitalWrite(CC_REVERSE, LOW);
    
  }

}




