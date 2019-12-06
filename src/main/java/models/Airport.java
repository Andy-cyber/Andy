package models;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;


public class Airport {

    public int id;
    public Object name;
    public Object flights;

    public Airport() {super();}

    public Airport(AirportBuilder airportBuilder){
        this.id = airportBuilder.getId();
        this.name = airportBuilder.getName();
        this.flights = airportBuilder.getFlights();
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return (String) name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Flight> getFlights() {
        return (ArrayList<Flight>) flights;
    }

    public void setFlights(ArrayList<Flight> flights) {
        this.flights = flights;
    }

    public void setList(ArrayList<Flight> list) {
        this.flights = list;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Airport airport = (Airport) o;

        if (id != airport.id) return false;
        if (name != null ? !name.equals(airport.name) : airport.name != null) return false;
        return flights != null ? flights.equals(airport.flights) : airport.flights == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (flights != null ? flights.hashCode() : 0);
        return result;
    }
    @Override
    public String toString() {
        return "\nAirport{" +
                "id : " + id +
                ", name : " + name +
                ", flights : " + flights +
                "}\n"+"------------------------------" +
                "------------------------------" +
                "------------------------------" +
                "------------------------------";
    };
}




