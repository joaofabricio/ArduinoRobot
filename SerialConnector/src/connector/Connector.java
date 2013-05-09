package connector;


import static gnu.io.SerialPort.DATABITS_8;
import static gnu.io.SerialPort.PARITY_NONE;
import static gnu.io.SerialPort.STOPBITS_1;
import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Connector implements SerialPortEventListener {

	private static final int TIME_OUT = 2000;

	private static final int DATA_RATE = 9600;

	private static final String PORT_NAME = "/dev/ttyUSB0";
	
	private SerialPort serialPort;
	
	private BufferedReader input;
	
	private List<ConnectorListener> listeners = new ArrayList<ConnectorListener>();
	
	public void openConnection(){
		try {
			CommPortIdentifier portId = CommPortIdentifier.getPortIdentifier(PORT_NAME);
			try {
				serialPort = (SerialPort) portId.open(this.getClass().getName(), TIME_OUT);
				serialPort.setSerialPortParams(DATA_RATE, 
					 		       DATABITS_8, 
							       STOPBITS_1, 
							       PARITY_NONE);

				input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));

				serialPort.addEventListener(this);
				serialPort.notifyOnDataAvailable(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (NoSuchPortException e) {
			System.err.println("Falha ao acessar " + PORT_NAME);
		}
	}
	
	public void closeConnection(){
		if (serialPort!=null){
			serialPort.removeEventListener();
			serialPort.close();
		}
	}
	
	@Override
	public synchronized void serialEvent(SerialPortEvent event) {
		if (event.getEventType() == SerialPortEvent.DATA_AVAILABLE){
			try {
				notifyListeners(Integer.parseInt(input.readLine()));
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}
	}

	public void addConnectorListener(ConnectorListener listener){
		if (!listeners.contains(listener)){
			listeners.add(listener);
		}
	}

	public void removeConnectorListener(ConnectorListener listener){
		if (listeners.contains(listener)){
			listeners.remove(listener);
		}
	}

	private void notifyListeners(int value){
		for (ConnectorListener listener : listeners){
			listener.fireEvent(new ConnectorEvent(value));
		}
	}

}
