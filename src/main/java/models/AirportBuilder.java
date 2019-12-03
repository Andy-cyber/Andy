package models;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class AirportBuilder {

    private AtomicLong idAirport = new AtomicLong(1);

    private int id;
    private String name;
    private List<Flight> flights;

    private ValidatorServiceImpl validator;

    public AirportBuilder() {
        flights = new ArrayList<>();
        validator = new ValidatorServiceImpl();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public AirportBuilder setId() {
        this.id = (int) idAirport.getAndIncrement();
        return this;
    }

    public AirportBuilder setIdentification(int id) {
        this.id = id;
        return this;
    }

    public AirportBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public AirportBuilder setFlights(ArrayList<Flight> flights) {
        this.flights = flights;
        return this;
    }

    public Airport build() {

        StringBuilder sb = new StringBuilder();
        Airport obj = new Airport(this);
        if (!validator.isValidName(this.name)) {
            sb.append("\nIncorrect name\n");
        }
        if (sb.length() == 0)
            return obj;
        else
            throw new IllegalArgumentException(sb.toString());
    }

    private class ValidatorServiceImpl {
        public boolean isValidName(String name) {
            return true;
        }
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
    }
}
