package services;

import models.Airport;
import models.Flight;
import providers.AirportProvider;
import providers.FlightProviderImpl;

import java.util.ArrayList;

public class AirportServiceImpl implements AirportService {

    FlightProviderImpl flightProvider;
    FlightServiceImpl flightService;

    private AirportProvider airportProvider;

    public AirportServiceImpl(AirportProvider airportProvider){
        this.airportProvider = airportProvider;
    }

    @Override
    public void addAirport(Airport airport) {
        airportProvider.addAirport(airport);
    }
    @Override
    public void editAirport(Airport airport) {
        airportProvider.editAirport(airport);
    }
    @Override
    public void deleteAirport(int idAirport) {
        airportProvider.deleteAirport(idAirport);
    }

    @Override
    public void addFlight( int idAirport, Flight flight) {
        airportProvider.addFlight(idAirport,flight);
    }
    @Override
    public void editFlight(int idAirport, Flight flight) {
        airportProvider.editFlight(idAirport,flight);
    }
    @Override
    public void deleteFlight(int idAirport, int idFlight) {
        airportProvider.deleteFlight(idAirport,idFlight);
    }

    @Override
    public ArrayList<Airport> getAirportList(){
        return airportProvider.getAirportList();
    }
    @Override
    public Airport getAirport(int idAirport){
        return airportProvider.getAirportById(idAirport);
    }
}