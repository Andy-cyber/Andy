package providers;

import models.Airport;
import models.Flight;

import java.util.ArrayList;

public interface AirportProvider {

    abstract Airport getAirport(int idAirport);

    void addAirport(Airport airport);
    void editAirport(Airport airport);

    static void deleteAirport(int idAirport) {

    }

    void deleteAirport(int idAirport);

    static void addFlight(int idAirport, Flight flight) {

    }

    void addFlight(int idAirport, Flight flight);

    static void editFlight(int idAirport, Flight flight) {

    }

    void editFlight(int idAirport, Flight flight);

    static void deleteFlight(int idAirport, int idFlight) {

    }

    static Airport getAirport(int idAirport) {
        return null;
    }

    void deleteFlight(int idAirport, int idFlight);

    static ArrayList<Airport> getAirportList() {
        return null;
    }

    ArrayList<Airport> getAirportList();

    void setAirportList(ArrayList<Airport> airportList);
}