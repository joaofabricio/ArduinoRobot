package engine;

import connector.Connector;

public class CarImpl implements Car {
	
	private Connector conn = new Connector("/dev/ttyUSB0");
	
	private static final String GO_FORWARD = "1";
	private static final String GO_BACKWARD = "2";
	private static final String TURN_LEFT = "3";
	private static final String TURN_RIGHT = "4";

	private void sendCommand(String command) {
		try {
			conn.openConnection();
			conn.write(command);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.closeConnection();
		}
		
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

}
