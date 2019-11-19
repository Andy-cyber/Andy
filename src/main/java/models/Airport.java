package models;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;


public class Airport {
        /*
         *This method has fields such as:
         *airport number (id), name and flights
         */
        private int id;

        private String name;

        private List<Flight> flights;


        public Airport(AirportBuilder airportBuilder) {}
        /*
         *This is constryctors
         */
        public Airport(){
            AirportBuilder airport = null;
            this.id = airport.getId();
            this.name = airport.getName();
            this.flights = airport.getFlights();
        }
        /*
         *This method is used to obtain
         * the Id value
         */
        public int getId() {
            return id;
        }
        /*
         *This method is used to receiving
         * the Id value
         */
        public void setId(int id) {
            this.id = id;
        }
        /*
         *This method is used to obtain
         *the name
         */
        public String getName() {
            return name;
        }
        /*
         *This method is used to provide
         *Name
         */
        public void setName(String name) {
            this.name = name;
        }

        public ArrayList<Flight> getFlights() {
            return (ArrayList<Flight>) flights;
        }

        public void setFlights(ArrayList<Flight> flights) {
            this.flights = flights;
        }

    public void setList(ArrayList<Flight> flights) {
    }

    public static class AirportBuilder {

            private AtomicLong idAirport = new AtomicLong(1);

            private int id;
            private String name;
            private List<Flight> flights;

            private  ValidatorServiceImpl validator;

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
            public AirportBuilder setIdentification(int id){
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

            public Airport build(){

                StringBuilder sb = new StringBuilder();
                Airport obj = new Airport(this);
                if(!validator.isValidName(this.name)){
                    sb.append("\nIncorrect name\n");
                }
                if(sb.length() == 0)
                    return obj;
                else
                    throw new IllegalArgumentException(sb.toString());
            }

            private class ValidatorServiceImpl {
                public boolean isValidName(String name) {
                    return false;
                }
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

