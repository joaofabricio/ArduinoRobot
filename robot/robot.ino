#include <Servo.h>

const int SERVO = 2;
const int CC = 3;
const int CC_REVERSE = 4;

const int FRENTE = 49;//comando
const int RE = 50;//comando
const int DIREITA = 51;//comando
const int ESQUERDA = 52;//comando

const int ANGULO_MEIO = 90;
const int ANGULO_DIREITA = 135;
const int ANGULO_ESQUERDA = 45;

const int DIR_MEIO = 0;
const int DIR_ESQUERDA = 1;
const int DIR_DIREITA = 2;

int direcao = DIR_MEIO;

Servo servoMotor;

void setup() {
  Serial.begin(9600);
  pinMode(CC, OUTPUT);
  pinMode(CC_REVERSE, OUTPUT);
  servoMotor.attach(SERVO);      // conecta o motor ao pino de controle
  servoMotor.write(ANGULO_MEIO);      // gira o servo ate o ponto do meio
}

int incoming = 0;

void loop() {
  if (Serial.available() > 0) {
    incoming = Serial.read();
    
    switch (incoming) {
      case FRENTE:
        digitalWrite(CC_REVERSE, LOW);
        digitalWrite(CC, HIGH);
      break;
      
      case RE:
        digitalWrite(CC, LOW);
        digitalWrite(CC_REVERSE, HIGH);
      break;
      
      case DIREITA:
        if (direcao == DIR_MEIO) {
          servoMotor.write(ANGULO_DIREITA);
          direcao = DIR_DIREITA;
        } else if (direcao == DIR_ESQUERDA) {
          servoMotor.write(ANGULO_MEIO);
          direcao = DIR_MEIO;
        }
      break;
      
      case ESQUERDA:
        if (direcao == DIR_MEIO) {
          servoMotor.write(ANGULO_ESQUERDA);
          direcao = DIR_ESQUERDA;
        } else if (direcao == DIR_DIREITA) {
          servoMotor.write(ANGULO_MEIO);
          direcao = DIR_MEIO;
        }
      break;
      
    }
    
    delay(500);
    
    digitalWrite(CC, LOW);
    digitalWrite(CC_REVERSE, LOW);
    
  }

}




