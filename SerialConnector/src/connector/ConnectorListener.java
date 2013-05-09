package connector;

import connector.ConnectorEvent;

public interface ConnectorListener {

	void fireEvent(ConnectorEvent e);	

}
