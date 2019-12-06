import models.Airport;
import models.AirportBuilder;
import models.Flight;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import providers.AirportProvider;
import providers.AirportProviderImpl;
import providers.FlightProvider;
import providers.FlightProviderImpl;
import services.AirportService;
import services.AirportServiceImpl;
import services.FlightService;
import services.FlightServiceImpl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

public class TestAirport {

    Flight flight;
    Airport myAirport;
    ArrayList<Airport> airports;
    ArrayList<Flight> flights;
    AirportProvider airportProvider;
    AirportService airportService;
    FlightService flightService;
    FlightProvider flightProvider;
    @BeforeTest
    public void initialize(){

        airports = new ArrayList<Airport>();
        flights = new ArrayList<Flight>();
        airportProvider = new AirportProviderImpl(airports);
        airportService = new AirportServiceImpl(airportProvider);
        flightProvider = new FlightProviderImpl(flights);
        flightService = new FlightServiceImpl(flightProvider);

        myAirport = new AirportBuilder().setId().setName("Enterprise").build();
        airportService.addAirport(myAirport);

        Airport secondAirport = new AirportBuilder().setId().setName("New Line").build();
        airportService.addAirport(secondAirport);

        try {
            Flight flight = new Flight.FlightBuilder()
                    .setId()
                    .setCompanyName("West Side")
                    .setDateOfFlight(LocalDate.of(2017, Month.SEPTEMBER, 25))
                    .setTimeTakeOff(LocalTime.of(8, 30))
                    .setTimeInFlight(120)
                    .setPrice(120.99)
                    .setFrom(Flight.City.KIEV)
                    .setTo(Flight.City.LONDON)
                    .build();
            airportService.addFlight(myAirport.getId(), flight);
            flightService.addFlight(flight);
        } catch (RuntimeException e) {
            System.out.println(e);
        }

        try {
            Flight flight = new Flight.FlightBuilder()
                    .setId()
                    .setCompanyName("West Side")
                    .setDateOfFlight(LocalDate.of(2017, Month.SEPTEMBER, 25))
                    .setTimeTakeOff(LocalTime.of(8, 30))
                    .setTimeInFlight(120)
                    .setPrice(120.99)
                    .setFrom(Flight.City.KIEV)
                    .setTo(Flight.City.OTTAWA)
                    .build();
            airportService.addFlight(myAirport.getId(), flight);
            flightService.addFlight(flight);
        } catch (RuntimeException e) {
            System.out.println(e);
        }

        try {
            Flight flight = new Flight.FlightBuilder()
                    .setId()
                    .setCompanyName("West")
                    .setDateOfFlight(LocalDate.of(2017, Month.SEPTEMBER, 25))
                    .setTimeTakeOff(LocalTime.of(10, 30))
                    .setTimeInFlight(120)
                    .setPrice(120.99)
                    .setFrom(Flight.City.KIEV)
                    .setTo(Flight.City.LISSABON)
                    .build();
            airportService.addFlight(myAirport.getId(), flight);
            flightService.addFlight(flight);
        } catch (RuntimeException e) {
            System.out.println(e);
        }

        try {
            Flight flight = new Flight.FlightBuilder()
                    .setId()
                    .setCompanyName("West")
                    .setDateOfFlight(LocalDate.of(2017, Month.SEPTEMBER, 25))
                    .setTimeTakeOff(LocalTime.of(14, 30))
                    .setTimeInFlight(120)
                    .setPrice(120.99)
                    .setFrom(Flight.City.LISSABON)
                    .setTo(Flight.City.OTTAWA)
                    .build();
            airportService.addFlight(secondAirport.getId(), flight);
            flightService.addFlight(flight);
        } catch (RuntimeException e) {
            System.out.println(e);
        }

        try {
            Flight flight = new Flight.FlightBuilder()
                    .setId()
                    .setCompanyName("West")
                    .setDateOfFlight(LocalDate.of(2017, Month.SEPTEMBER, 25))
                    .setTimeTakeOff(LocalTime.of(14, 30))
                    .setTimeInFlight(120).setPrice(120.99)
                    .setFrom(Flight.City.LONDON)
                    .setTo(Flight.City.OTTAWA)
                    .build();
            airportService.addFlight(secondAirport.getId(), flight);
            flightService.addFlight(flight);
        } catch (RuntimeException e) {
            System.out.println(e);
        }
        System.out.println();
    }

    private boolean check(List<Flight> path, List<Flight> expected) {
        for (int i=0; i<path.size();i++){
            if(path.get(i).getCompanyName() != expected.get(i).getCompanyName()) return false;
            if(path.get(i).getTimeInFlight() != expected.get(i).getTimeInFlight()) return false;
            if((path.get(i).getTimeTakeOff().compareTo(expected.get(i).getTimeTakeOff()))!= 0)return false;
            if(path.get(i).getFrom() != expected.get(i).getFrom()) return false;
            if(path.get(i).getTo() != expected.get(i).getTo()) return false;
            if(path.get(i).getPrice() != expected.get(i).getPrice()) return false;
            if(path.get(i).getDateOfFlight().compareTo(expected.get(i).getDateOfFlight())!=0) return false;
        }
        return true;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @org.testng.annotations.Test(dataProvider = "airportEditTest")
    public void airportEditTest(Airport editAirport, Airport expected){
        assertNotEquals(editAirport.getName(),expected.getName());
    }

    @DataProvider
    public Object[][] airportEditTest(){
        int id = 1;
        Airport airport = new Airport();
        airport.setId(id);
        airport.setName(airports.get(id).getName());
        airport.setList(airports.get(id).getFlights());

        Airport editAirport = new AirportBuilder().setIdentification(id).setName("Enterprise").build();
        airportService.editAirport(editAirport);
        return new Object[][]{{airportService.getAirport(id),airport}};
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////



    @org.testng.annotations.Test(dataProvider = "airportAddTest")
    public void airportAddTest(Airport addAirport, Airport expected){
        assertEquals(addAirport,expected);
    }

    @DataProvider
    public Object[][] airportAddTest(){
        int id = 3;
        Airport addAirport = new AirportBuilder().setIdentification(id).setName("Enterprise").build();
        Airport expectedAirport = new AirportBuilder().setIdentification(id).setName("Enterprise").build();
        airportService.addAirport(addAirport);
        return new Object[][]{{airportService.getAirport(id),expectedAirport}};
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @org.testng.annotations.Test(dataProvider = "airportDeleteTest")
    public void airportDeleteTest(Airport deletedAirport, Airport expected){
        assertNotEquals(deletedAirport,expected);
    }

    @DataProvider
    public Object[][] airportDeleteTest(){
        int id = 4;
        Airport airport = new AirportBuilder().setIdentification(id).setName("MAO").build();
        airportService.addAirport(airport);

        Airport air = new Airport();
        air.setId(airportService.getAirport(id).getId());
        air.setName(airportService.getAirport(id).getName());
        air.setList(airportService.getAirport(id).getFlights());

        airportService.deleteAirport(id);
        return new Object[][]{{airportService.getAirport(id),airport}};
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @org.testng.annotations.Test(expectedExceptions = IllegalArgumentException.class)
    public void addAirportTestException(){
        int id = 1;
        Airport addAirport = new AirportBuilder().setIdentification(id).setName("Enterprise").build();
        airportService.addAirport(addAirport);
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @org.testng.annotations.Test(expectedExceptions = IllegalArgumentException.class)
    public void editAirportTestException(){
        int id = 10;
        Airport airport = new Airport();
        airport.setId(id);
        airport.setName("MAO");

        airportService.editAirport(airport);
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @org.testng.annotations.Test(expectedExceptions = IllegalArgumentException.class)
    public void deleteAirportTestException(){
        airportService.deleteAirport(10);
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////

}