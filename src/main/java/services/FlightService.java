package services;

import models.Flight;

import java.time.LocalTime;
import java.util.ArrayList;

public interface FlightService {


    void addFlight(Flight flight);
    void editFlight(Flight flight);
    void deleteFlight(int idFlight);

    LocalTime getTimeOfLanding(Flight obj);
    ArrayList<Flight> getFlightFrom(Flight.City from);
    ArrayList<Flight> getFlightTo(Flight.City to);
    ArrayList<Flight> searchPath(Flight.City from, Flight.City to, int time);

    Flight getFlight(int idFlight);
}