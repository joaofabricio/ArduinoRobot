package br.uem.din.joaofabricio.robot.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.uem.din.joaofabricio.robot.engine.Car;
import br.uem.din.joaofabricio.robot.engine.CarImpl;

@WebServlet("/CarServlet")
public class CarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String FORWARD = "fw";

	private static final String BACKWARD = "bw";

	private static final String LEFT = "left";

	private static final String RIGHT = "right";	
	
	private Car car = new CarImpl("/dev/ttyUSB0");

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String comm = request.getParameter("comm");

		switch (comm) {
		case FORWARD:
			car.goForward(4);
			break;
		case BACKWARD:
			car.goBackward(4);
			break;
		case LEFT:
			car.turnLeft();
			break;
		case RIGHT:
			car.turnRight();
			break;
		}
		
		response.sendRedirect("./");
	}

}
