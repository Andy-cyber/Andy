package services;

import models.Airport;
import models.Flight;

import java.util.ArrayList;

public interface AirportService {

    void addAirport(Airport airport);
    void editAirport(Airport airport);
    void deleteAirport(int idAirport);
    void addFlight(int idAirport, Flight flight);

    void editFlight(int idAirport, Flight flight);
    void deleteFlight(int idAirport, int idFlight);
    ArrayList<Airport> getAirportList();
    Airport getAirport(int idAirport);
}