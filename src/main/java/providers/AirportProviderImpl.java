package providers;

import models.Airport;
import models.Flight;

import java.util.ArrayList;
import java.util.List;

public class AirportProviderImpl implements AirportProvider {

    public List<Airport> airports = new ArrayList<>();

    public boolean checkId(int id){
        for (Airport airport : airports)
            if (airport.getId()==id)
                return false;
        return true;
    }


   /* @Override
    public Airport getAirport(int idAirport){
        return airportList.stream().filter((p)->p.getId() == idAirport).findAny().orElse(null);
    }*/

    @Override
    public Airport getAirportById(int idAirport) {
        return null;
    }


    @Override
    public ArrayList<Airport> getAirportList() {
        return null;
    }

    @Override
    public void addAirport(Airport airport) {
        if (this.checkId(airport.getId()))
            airports.add(airport);
        else
            throw new IllegalArgumentException();
    }

    public AirportProviderImpl(List<Airport> airportList) {
        super();
        this.airports = airportList;
    }

    @Override
    public void editAirport(Airport airport) {

        if (!(this.checkId(airport.getId()))) {
            Airport editAirport = getAirportById(airport.getId());
            editAirport.setName(airport.getName());
            editAirport.setList(airport.getFlights());
        }
        else throw new IllegalArgumentException();
    }

    @Override
    public void deleteAirport(int idAirport) {

        if(!(this.checkId(idAirport))){
            airports.remove(getAirportById(idAirport));
        }
        else throw new IllegalArgumentException();
    }

    @Override
    public void addFlight(int idAirport, Flight flight){
        Airport airport = getAirportById(idAirport);
        FlightProviderImpl flightProvider = new FlightProviderImpl(airport.getFlights());
        flightProvider.addFlight(flight);
    }
    @Override
    public void editFlight(int idAirport, Flight flight) {
        Airport airport = getAirportById(idAirport);
        FlightProviderImpl flightProvider = new FlightProviderImpl(airport.getFlights());
        flightProvider.editFlight(flight);
    }
    @Override
    public void deleteFlight(int idAirport, int idFlight){
        Airport airport = getAirportById(idAirport);
        FlightProviderImpl flightProvider = new FlightProviderImpl(airport.getFlights());
        flightProvider.deleteFlight(idFlight);
    }

    @Override
    public ArrayList<Airport> getAirportById() {
        return (ArrayList<Airport>) airports;
    }

    @Override
    public void setAirports(ArrayList<Airport> airports) {
        this.airports = airports;
    }
}