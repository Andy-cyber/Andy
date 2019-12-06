package providers;

import models.Airport;
import models.Flight;

import java.util.ArrayList;

public interface AirportProvider {

    ArrayList<Airport> getAirportList();

    Airport getAirport(int idAirport);

    void addAirport(Airport airport);
    void editAirport(Airport airport);
    void deleteAirport(int idAirport);

    void addFlight(int idAirport, Flight flight);
    void editFlight(int idAirport, Flight flight);
    void deleteFlight(int idAirport, int idFlight);

    Airport getAirportById(int idAirport);
    ArrayList<Airport> getAirportById();
    void setAirports(ArrayList<Airport> airportsList);
}
