#include <Servo.h>

const int SERVO = 4;
const int CC = 5;

const int FRENTE = 49;
const int DIREITA = 50;
const int ESQUERDA = 51;
const int RE = 52;



Servo servoMotor;              // declara um objeto tipo Servo

int angulo = 45;               // controla a posicao do eixo

void setup() {
  Serial.begin(9600);
  pinMode(cc, OUTPUT);
  servoMotor.attach(servo);
  servoMotor.write(angulo);
}

int motor-esquerdo = LOW;
int motor-direito = LOW;

int incoming = 0;

void loop() {
  if (Serial.available() > 0) {
    incoming = Serial.read();
    
    if (incoming == FRENTE) {
      digitalWrite(CC, HIGH);
    }
    if (incoming == FRENTE) {
      digitalWrite(CC, HIGH);
    }
    if (incoming == FRENTE) {
      digitalWrite(CC, HIGH);
    }
    if (incoming == FRENTE) {
      digitalWrite(CC, HIGH);
    }
  }

}




