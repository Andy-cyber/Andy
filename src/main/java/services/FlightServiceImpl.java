package services;

import models.Flight;
import providers.FlightProvider;

import java.time.LocalTime;
import java.util.ArrayList;


public class FlightServiceImpl implements FlightService {

    private FlightProvider flightProvider;

    public FlightServiceImpl(FlightProvider flightProvider){
        this.flightProvider = flightProvider;
    }

    @Override
    public void addFlight(Flight flight) {
        flightProvider.addFlight(flight);
    }

    @Override
    public void editFlight(Flight flight) {
        flightProvider.editFlight(flight);
    }

    @Override
    public void deleteFlight(int idFlight) {
        flightProvider.deleteFlight(idFlight);
    }

    @Override
    public LocalTime getTimeOfLanding(Flight obj) {
        return flightProvider.getTimeOfLanding(obj);
    }

    @Override
    public ArrayList<Flight> getFlightFrom(Flight.City from) {
        return flightProvider.getFlightFrom(from);
    }

    @Override
    public ArrayList<Flight> getFlightTo(Flight.City to) {
        return flightProvider.getFlightTo(to);
    }

    @Override
    public ArrayList<Flight> searchPath(Flight.City from, Flight.City to, int time) {
        return flightProvider.searchPath(from,to,time);
    }
    @Override
    public Flight getFlight(int idFlight){
        return flightProvider.getFlight(idFlight);
    }
}