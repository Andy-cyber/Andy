package providers;

import models.Flight;

import java.time.LocalTime;
import java.util.ArrayList;

public interface FlightProvider {

    void addFlight(Flight flight);
    void editFlight(Flight flight);
    void deleteFlight(int idFlight);

    ArrayList<Flight> searchPath(Flight.City from, Flight.City to, int time);
    LocalTime getTimeOfLanding(Flight obj);
    ArrayList<Flight> getFlightFrom(Flight.City from);
    Flight getFlight(int idFlight);
    ArrayList<Flight> getFlightTo(Flight.City to);

}