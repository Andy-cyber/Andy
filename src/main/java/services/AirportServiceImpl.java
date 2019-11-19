package services;

import models.Airport;
import models.Flight;
import providers.AirportProvider;
import providers.FlightProviderImpl;
//import providers.FlightProviderImpl;

import java.util.ArrayList;

public class AirportServiceImpl implements AirportService {

    FlightProviderImpl flightProvider;
    FlightServiceImpl flightService;

    private AirportProvider airportProvider;
     public AirportServiceImpl(AirportProvider airportProvider){
        this.airportProvider = airportProvider;
    }

     public AirportServiceImpl() {

    }

    @Override
    public void addAirport(Airport airport) {
        AirportServiceImpl airportProvider = new AirportServiceImpl();
        airportProvider.addAirport(airport);
    }
    @Override
    public void editAirport(Airport airport) {
        AirportServiceImpl airportProvider = new AirportServiceImpl();
        airportProvider.editAirport(airport);
    }
    @Override
    public void deleteAirport(int idAirport) {
        AirportProvider.deleteAirport(idAirport);
    }

    @Override
    public void addFlight( int idAirport, Flight flight) {
        AirportProvider.addFlight(idAirport,flight);
    }
    @Override
    public void editFlight(int idAirport, Flight flight) {
        AirportProvider.editFlight(idAirport,flight);
    }
    @Override
    public void deleteFlight(int idAirport, int idFlight) {
        AirportProvider.deleteFlight(idAirport,idFlight);
    }

    @Override
    public ArrayList<Airport> getAirportList(){
        return  AirportProvider.getAirportList();
    }
    @Override
    public Airport getAirport(int idAirport){
        return  AirportProvider.getAirport(idAirport);
    }
}