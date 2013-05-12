package connector;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import engine.Car;
import engine.CarImpl;

public class Main {

	public static void main(String[] args) {
		Car c = new CarImpl();

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String in = null;
		try {

			while (true) {
				in = br.readLine();

				if (in.equals("1")) {
					c.goForward(4);
				}
				if (in.equals("2")) {
					c.goBackward(4);
				}
				if (in.equals("3")) {
					c.turnLeft();
				}
				if (in.equals("4")) {
					c.turnRight();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
