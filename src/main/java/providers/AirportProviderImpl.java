package providers;

import models.Airport;
import models.Flight;

import java.util.ArrayList;
import java.util.List;

public class AirportProviderImpl implements AirportProvider {

    public boolean checkId(int id){
        for (Airport airport : airportList)
            if (airport.getId()==id)
                return false;
        return true;
    }

    public List<Airport> airportList = new ArrayList<>();

   /* @Override
    public Airport getAirport(int idAirport){
        return airportList.stream().filter((p)->p.getId() == idAirport).findAny().orElse(null);
    }*/

    @Override
    public void addAirport(Airport airport) {
        if (this.checkId(airport.getId()))
            airportList.add(airport);
        else
            throw new IllegalArgumentException();
    }

    public AirportProviderImpl(List<Airport> airportList) {
        super();
        this.airportList = airportList;
    }

    @Override
    public void editAirport(Airport airport) {

        if (!(this.checkId(airport.getId()))) {
            Airport editAirport = getAirport(airport.getId());
            editAirport.setName(airport.getName());
            editAirport.setList(airport.getFlights());
        }
        else throw new IllegalArgumentException();
    }

    @Override
    public void deleteAirport(int idAirport) {

        if(!(this.checkId(idAirport))){
            airportList.remove(getAirport(idAirport));
        }
        else throw new IllegalArgumentException();
    }

    @Override
    public void addFlight(int idAirport, Flight flight){
        Airport airport = getAirport(idAirport);
        FlightProviderImpl flightProvider = new FlightProviderImpl(airport.getFlights());
        flightProvider.addFlight(flight);
    }
    @Override
    public void editFlight(int idAirport, Flight flight) {
        Airport airport = getAirport(idAirport);
        FlightProviderImpl flightProvider = new FlightProviderImpl(airport.getFlights());
        flightProvider.editFlight(flight);
    }
    @Override
    public void deleteFlight(int idAirport, int idFlight){
        Airport airport = getAirport(idAirport);
        FlightProviderImpl flightProvider = new FlightProviderImpl(airport.getFlights());
        flightProvider.deleteFlight(idFlight);
    }

    @Override
    public ArrayList<Airport> getAirportList() {
        return (ArrayList<Airport>) airportList;
    }

    @Override
    public void setAirportList(ArrayList<Airport> airportList) {
        this.airportList = airportList;
    }
}