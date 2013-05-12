package br.uem.din.joaofabricio.robot.connector;


import static gnu.io.SerialPort.DATABITS_8;
import static gnu.io.SerialPort.PARITY_NONE;
import static gnu.io.SerialPort.STOPBITS_1;
import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class SerialConnector implements SerialPortEventListener {

	private static final int TIME_OUT = 2000;

	private static final int DATA_RATE = 9600;

	private SerialPort serialPort;
	
	private BufferedReader input;

	private BufferedWriter output;

	private String serialPortName;
	
	private List<ConnectorListener> listeners = new ArrayList<ConnectorListener>();
	
	public SerialConnector(String serialPortName) {
		this.serialPortName = serialPortName;
	}

	public void openConnection(){
		try {
			CommPortIdentifier portId = CommPortIdentifier.getPortIdentifier(serialPortName);
			try {
				serialPort = (SerialPort) portId.open(this.getClass().getName(), TIME_OUT);
				serialPort.setSerialPortParams(DATA_RATE, 
					 		       DATABITS_8, 
							       STOPBITS_1, 
							       PARITY_NONE);

				input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
				output = new BufferedWriter(new OutputStreamWriter(serialPort.getOutputStream()));

				serialPort.addEventListener(this);
				serialPort.notifyOnDataAvailable(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (NoSuchPortException e) {
			System.err.println("Falha ao acessar " + serialPortName);
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

	public void write(String string) {
		try {
			output.write(string);
			output.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
