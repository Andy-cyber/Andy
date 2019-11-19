package services;

import models.Airport;
import models.Flight;

import java.util.ArrayList;

public interface AirportService {

    static void addAirport(Airport airport) {

    }

    static void editAirport(Airport airport) {

    }

    static void deleteAirport(int idAirport) {

    }

    static void addFlight(int idAirport, Flight flight) {

    }

    void editFlight(int idAirport, Flight flight);
    void deleteFlight(int idAirport, int idFlight);
    ArrayList<Airport> getAirportList();

    static Airport getAirport(int idAirport) {
        return null;
    }
}