package servlet;
import dataBase.JDBC;
import models.Flight;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FlightHandler extends HttpServlet {
    private static String INSERT_JSP = "/insertFl.jsp";
    private static String EDIT_JSP = "/editFl.jsp";
    private static String SHOWALL_JSP = "/flightList.jsp";
    private JDBC jdbc;
    public FlightHandler() throws SQLException, ClassNotFoundException {
        super();
        jdbc = new JDBC();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String redirect="";
        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("getAllFlights")){
            redirect = SHOWALL_JSP;
            try {
                request.setAttribute("flights", jdbc.getAllFlights());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else if (action.equalsIgnoreCase("simpleFlightList")){
            redirect = "simpleFlightList.jsp";
            try {
                request.setAttribute("flights", jdbc.getAllFlights());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else if (action.equalsIgnoreCase("delete")){
            redirect = "index.jsp";
            int flightId = Integer.parseInt(request.getParameter("flightId"));
            try {
                jdbc.deleteFlight(flightId);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } else if (action.equalsIgnoreCase("edit")){
            redirect = EDIT_JSP;
            Flight flight = null;
            int flightId = Integer.parseInt(request.getParameter("flightId"));
            try {
                flight = jdbc.getFlight(flightId);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            request.setAttribute("flight", flight);
            try {
                System.out.println(jdbc.getAirportByFlight(flightId));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                request.setAttribute("airportId",jdbc.getAirportByFlight(flightId));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (action.equalsIgnoreCase("flightList")){
            redirect = SHOWALL_JSP;
            int airportId = Integer.parseInt(request.getParameter("airportId"));
            try {
                request.setAttribute("flights", jdbc.getAllFlightsFromAirport(airportId));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else {
            redirect = INSERT_JSP;
            request.setAttribute("airportId",request.getParameter("airportId"));
        }
        RequestDispatcher view = request.getRequestDispatcher(redirect);
        view.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Flight flight = new Flight();

        flight.setCompanyName(request.getParameter("flightCompanyName"));
        flight.setDateOfFlight(LocalDate.parse(request.getParameter("flightDateOfFlight")));
        flight.setTimeInFlight(Integer.parseInt(request.getParameter("flightTimeInFlight")));
        flight.setTimeTakeOff(LocalTime.parse(request.getParameter("flightTimeTakeOff")));
        flight.setPrice(Double.parseDouble(request.getParameter("flightPrice")));
        flight.setFrom(Flight.City.valueOf(request.getParameter("cityFrom")));
        flight.setTo(Flight.City.valueOf(request.getParameter("cityTo")));

        String flightId = request.getParameter("flightId");
        System.out.println("a "+request.getParameter("airportId"));
        int airportId = 0;
        if(flightId == null || flightId.isEmpty()) {
            airportId = Integer.parseInt(request.getParameter("airportId"));
            try {
                jdbc.addFlight(flight, airportId);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else {
            try {
                airportId = jdbc.getAirportByFlight(Integer.parseInt(flightId));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            flight.setId(Integer.parseInt(flightId));
            try {
                jdbc.updateFlight(flight);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
//
        RequestDispatcher view = request.getRequestDispatcher("index.jsp");
        view.forward(request, response);
    }
}