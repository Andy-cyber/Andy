package servlet;

import dataBase.JDBC;
import models.Airport;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class AirportHandler extends HttpServlet {
    private static String INSERT_JSP = "/insertAl.jsp";
    private static String EDIT_JSP = "/editAl.jsp";
    private static String SHOWALL_JSP = "/airportList.jsp";
    private JDBC jdbc;
    public AirportHandler() throws SQLException, ClassNotFoundException {
        super();
        jdbc = new JDBC();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String redirect="";

        String action = request.getParameter("action");
        if (action.equalsIgnoreCase("delete")){
            redirect = SHOWALL_JSP;
            int airportId = Integer.parseInt(request.getParameter("airportId"));
            try {
                jdbc.deleteAirport(airportId);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                request.setAttribute("airports", jdbc.getAllAirports());
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } else if (action.equalsIgnoreCase("edit")){
            Airport airport = null;
            redirect = EDIT_JSP;
            int airportId = Integer.parseInt(request.getParameter("airportId"));
            try {
                airport = jdbc.getAirport(airportId);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            request.setAttribute("airport", airport);
        } else if (action.equalsIgnoreCase("airportList")){
            redirect = SHOWALL_JSP;
            try {
                request.setAttribute("airports", jdbc.getAllAirports());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            redirect = INSERT_JSP;
        }
        RequestDispatcher view = request.getRequestDispatcher(redirect);
        view.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Airport airport = new Airport();
        airport.setName(request.getParameter("airportName"));
        String airportId = request.getParameter("airportId");
        if(airportId == null || airportId.isEmpty()) {
            try {
                jdbc.addAirport(airport);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else {
            try {
                airport.setId(Integer.parseInt(airportId));
                jdbc.updateAirport(airport);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        RequestDispatcher view = request.getRequestDispatcher(SHOWALL_JSP);
        try {
            request.setAttribute("airports", jdbc.getAllAirports());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        view.forward(request, response);
    }
}
