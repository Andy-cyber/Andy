package models;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.concurrent.atomic.AtomicLong;

public class Flight {

    private  int id;

    private String companyName;

    private LocalDate dateOfFlight;

    private int timeInFlight;

    private LocalTime timeTakeOff;

    private double price;

    private City from;

    private City to;

    public Flight(FlightBuilder flightBuilder){super();}

    public Flight(Flight builder) {
        this.id = builder.getId();
        this.companyName = builder.getCompanyName();
        this.dateOfFlight = builder.getDateOfFlight();
        this.timeInFlight = builder.getTimeInFlight();
        this.timeTakeOff = builder.getTimeTakeOff();
        this.price = builder.getPrice();
        this.from = builder.getFrom();
        this.to = builder.getTo();
    }

    public void setId(int id){this.id = id;}

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setDateOfFlight(LocalDate dateOfFlight) {
        this.dateOfFlight = dateOfFlight;
    }

    public void setTimeInFlight(int timeInFlight) {
        this.timeInFlight = timeInFlight;
    }

    public void setTimeTakeOff(LocalTime timeTakeOff) {
        this.timeTakeOff = timeTakeOff;
    }

    public void setPrice(double price) {
        this.price= price;
    }

    public void setFrom(City from) {
        this.from = from;
    }

    public void setTo(City to) { this.to = to; }

    public String getCompanyName() {
        return companyName;
    }

    public LocalDate getDateOfFlight() {
        return dateOfFlight;
    }

    public int getTimeInFlight() {
        return timeInFlight;
    }

    public LocalTime getTimeTakeOff() {
        return timeTakeOff;
    }

    public double getPrice() {
        return price;
    }

    public City getFrom() {
        return from;
    }

    public City getTo() {
        return to;
    }

    public int getId() {
        return id;
    }

    public enum City {WASHINGTON, KIEV, KHARKIV, BERLIN;
        public static final City MADRID = null;
        public static final City MINSK = null;
        public static final City TOKIO = null ;
        public static final City LONDON = null;
        public static final City OTTAWA =null ;
        public static final City LISSABON =null ;
    }

    public static class FlightBuilder<ValidatorServiceImpl> {

        private static AtomicLong idCounter = new AtomicLong(1);

        public int id;
        public String companyName;
        public LocalDate dateOfFlight;
        public int timeInFlight;
        public LocalTime timeTakeOff;
        public double price;
        public Flight.City from;
        public Flight.City to;

        private ValidatorServiceImpl validator;

        public FlightBuilder() { validator = new ValidatorServiceImpl(); }

        public int getId() {
            return id;
        }

        public FlightBuilder setIdentification(int id){
            this.id = id;
            return  this;
        }
        public FlightBuilder setId() {
            this.id = (int) idCounter.getAndIncrement();
            return this;
        }
        public FlightBuilder setCompanyName(String companyName) {
            this.companyName = companyName;
            return this;
        }
        public FlightBuilder setDateOfFlight(LocalDate dateOfFlight) {
            this.dateOfFlight = dateOfFlight;
            return this;
        }
        public FlightBuilder setTimeInFlight(int timeInFlight) {
            this.timeInFlight = timeInFlight;
            return this;
        }
        public FlightBuilder setTimeTakeOff(LocalTime timeTakeOff) {
            this.timeTakeOff = timeTakeOff;
            return this;
        }
        public FlightBuilder setPrice(double price) {
            this.price = price;
            return this;
        }
        public FlightBuilder setFrom(Flight.City from) {
            this.from = from;
            return this;
        }
        public FlightBuilder setTo(Flight.City to) {
            this.to = to;
            return this;
        }

        public String getCompanyName() {
            return companyName;
        }
        public LocalDate getDateOfFlight() {
            return dateOfFlight;
        }
        public int getTimeInFlight() {
            return timeInFlight;
        }
        public LocalTime getTimeTakeOff() {
            return timeTakeOff;
        }
        public double getPrice() {
            return price;
        }
        public Flight.City getFrom() {
            return from;
        }
        public Flight.City getTo() {
            return to;
        }

        public Flight build(){

            StringBuilder sb = new StringBuilder();
            Flight obj = new Flight(this);
            if(!validator.isValidId(this.id)){
                sb.append("\nIncorrect id\n");
            }
            if(!validator.isValidName(this.companyName)){
                sb.append("\nIncorrect name\n");
            }
            if(!validator.isValidPrice(this.price)){
                sb.append("Incorrect price\n");
            }
            if(!validator.isValidTimeInFlight(this.timeInFlight)){
                sb.append("Incorrect timeInFlight\n");
            }
            if(sb.length() == 0)
                return obj;
            else
                throw new IllegalArgumentException(sb.toString());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Flight flight = (Flight) o;

        if (id != flight.id) return false;
        if (timeInFlight != flight.timeInFlight) return false;
        if (Double.compare(flight.price, price) != 0) return false;
        if (companyName != null ? !companyName.equals(flight.companyName) : flight.companyName != null) return false;
        if (dateOfFlight != null ? !dateOfFlight.equals(flight.dateOfFlight) : flight.dateOfFlight != null)
            return false;
        if (timeTakeOff != null ? !timeTakeOff.equals(flight.timeTakeOff) : flight.timeTakeOff != null) return false;
        if (from != flight.from) return false;
        return to == flight.to;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (companyName != null ? companyName.hashCode() : 0);
        result = 31 * result + (dateOfFlight != null ? dateOfFlight.hashCode() : 0);
        result = 31 * result + timeInFlight;
        result = 31 * result + (timeTakeOff != null ? timeTakeOff.hashCode() : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (from != null ? from.hashCode() : 0);
        result = 31 * result + (to != null ? to.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "\nFlight{" +
                "id : " + id +
                ",company : " + companyName  +
                ",date : " + dateOfFlight +
                ",timeIn : " + timeInFlight +
                ",timeTakeOff : " + timeTakeOff +
                ",price : " + price +
                ",from : " + from +
                ",to : " + to +
                "}\n";
    }
}
