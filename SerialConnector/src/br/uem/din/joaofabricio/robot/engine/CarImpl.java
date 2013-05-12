package br.uem.din.joaofabricio.robot.engine;

import br.uem.din.joaofabricio.robot.connector.SerialConnector;


public class CarImpl implements Car {
	
	private SerialConnector conn;
	
	private static final String GO_FORWARD = "1";
	private static final String GO_BACKWARD = "2";
	private static final String TURN_LEFT = "3";
	private static final String TURN_RIGHT = "4";

	public CarImpl(String serialPortName) {
		conn = new SerialConnector(serialPortName);
		conn.openConnection();
	}

	private void sendCommand(String command) {
		conn.write(command);
	}

	@Override
	public void goForward(int acelerate) {
		String command = acelerate(acelerate, GO_FORWARD);
		sendCommand(command);
	}

	private String acelerate(int acelerate, String in) {
		String command = "";
		for (int i = 1; i < acelerate; i++) {
			command += in;
		}
		return command;
	}

	@Override
	public void goBackward(int acelerate) {
		String command = acelerate(acelerate, GO_BACKWARD);
		sendCommand(command);
		
	}

	@Override
	public void turnLeft() {
		sendCommand(TURN_LEFT);
	}

	@Override
	public void turnRight() {
		sendCommand(TURN_RIGHT);
	}

	@Override
	protected void finalize() throws Throwable {
		conn.closeConnection();
		super.finalize();
	}
	
}
