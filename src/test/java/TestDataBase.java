import dataBase.JDBC;
import models.Airport;
import models.AirportBuilder;
import models.Flight;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;


public class TestDataBase{
    Flight flight1=null;
    Flight flight2=null;
    Flight flight3=null;
    Flight flight4=null;
    Flight flight5=null;
    Flight flight6=null;
    Airport firstAirport = null;
    Airport secondAirport = null;
    Airport thirdAirport = null;
    JDBC jdbc = null;
    @BeforeTest
    public void initJDBC() throws SQLException, ClassNotFoundException {
        jdbc = new JDBC();
    }
    public void initialize() throws SQLException, ClassNotFoundException {

        jdbc.clearDataBase();

        firstAirport = new AirportBuilder().setIdentification(4).setName("MAO").build();
        secondAirport = new AirportBuilder().setId().setName("New Line").build();
        thirdAirport = new AirportBuilder().setId().setName("TestAirport").build();

        try {
            flight1 = new Flight.FlightBuilder()
                    .setId()
                    .setCompanyName("Eclipse")
                    .setDateOfFlight(LocalDate.of(2013, Month.SEPTEMBER, 25))
                    .setTimeTakeOff(LocalTime.of(8, 30))
                    .setTimeInFlight(120)
                    .setPrice(120.99)
                    .setFrom(Flight.City.KHARKIV)
                    .setTo(Flight.City.WASHINGTON)
                    .build();
            jdbc.addFlight(flight1,firstAirport.getId());
        } catch (RuntimeException e) {
            System.out.println(e);
        }

        try {
            flight2 = new Flight.FlightBuilder()
                    .setId()
                    .setCompanyName("West Side")
                    .setDateOfFlight(LocalDate.of(2017, Month.SEPTEMBER, 25))
                    .setTimeTakeOff(LocalTime.of(8, 30))
                    .setTimeInFlight(120)
                    .setPrice(120.99)
                    .setFrom(Flight.City.KIEV)
                    .setTo(Flight.City.KHARKIV)
                    .build();
            jdbc.addFlight(flight2,secondAirport.getId());
        } catch (RuntimeException e) {
            System.out.println(e);
        }
        try {
            flight3 = new Flight.FlightBuilder()
                    .setId()
                    .setCompanyName("West")
                    .setDateOfFlight(LocalDate.of(2011, Month.SEPTEMBER, 25))
                    .setTimeTakeOff(LocalTime.of(10, 30))
                    .setTimeInFlight(120)
                    .setPrice(120.99)
                    .setFrom(Flight.City.MADRID)
                    .setTo(Flight.City.KIEV)
                    .build();
            jdbc.addFlight(flight3,secondAirport.getId());
        } catch (RuntimeException e) {
            System.out.println(e);
        }
        try {
            flight4 = new Flight.FlightBuilder()
                    .setId()
                    .setCompanyName("Eclipce")
                    .setDateOfFlight(LocalDate.of(2019, Month.SEPTEMBER, 25))
                    .setTimeTakeOff(LocalTime.of(14, 30))
                    .setTimeInFlight(120)
                    .setPrice(120.99)
                    .setFrom(Flight.City.MADRID)
                    .setTo(Flight.City.MINSK)
                    .build();
            jdbc.addFlight(flight4,firstAirport.getId());
        } catch (RuntimeException e) {
            System.out.println(e);
        }
        try {
            flight5 = new Flight.FlightBuilder()
                    .setId()
                    .setCompanyName("Fdea")
                    .setDateOfFlight(LocalDate.of(2015, Month.SEPTEMBER, 25))
                    .setTimeTakeOff(LocalTime.of(14, 30))
                    .setTimeInFlight(120).setPrice(120.99)
                    .setFrom(Flight.City.BERLIN)
                    .setTo(Flight.City.TOKIO)
                    .build();
            jdbc.addFlight(flight5,firstAirport.getId());
        } catch (RuntimeException e) {
            System.out.println(e);
        }

        try {
            flight6 = new Flight.FlightBuilder()
                    .setId()
                    .setCompanyName("TestFlight")
                    .setDateOfFlight(LocalDate.of(2015, Month.SEPTEMBER, 25))
                    .setTimeTakeOff(LocalTime.of(14, 30))
                    .setTimeInFlight(120).setPrice(120.99)
                    .setFrom(Flight.City.BERLIN)
                    .setTo(Flight.City.TOKIO)
                    .build();
            jdbc.addFlight(flight6,thirdAirport.getId());
        } catch (RuntimeException e) {
            System.out.println(e);
        }
        jdbc.addAirport(firstAirport);
        jdbc.addAirport(secondAirport);
        jdbc.addAirport(thirdAirport);
    }
    private boolean equalsFlightList(List<Flight> flightList, List<Flight> expected){
        for (int i=0; i<flightList.size();i++){
            if(!flightList.get(i).getCompanyName().equals(expected.get(i).getCompanyName())) return false;
            if(flightList.get(i).getTimeInFlight() != expected.get(i).getTimeInFlight()) return false;
            if((flightList.get(i).getTimeTakeOff().compareTo(expected.get(i).getTimeTakeOff()))!= 0)return false;
            if(flightList.get(i).getFrom() != expected.get(i).getFrom()) return false;
            if(flightList.get(i).getTo() != expected.get(i).getTo()) return false;
            if(flightList.get(i).getPrice() != expected.get(i).getPrice()) return false;
            if(flightList.get(i).getDateOfFlight().compareTo(expected.get(i).getDateOfFlight())!=0) return false;
        }
        return true;
    }
    private boolean equalsAirportList(ArrayList<Airport> airportList, ArrayList<Airport> expected) {
        for(int i = 0;i<airportList.size();i++){
            if(airportList.get(i).getId() != expected.get(i).getId()) return false;
            if(!airportList.get(i).getName().equals(expected.get(i).getName())) return false;
//            if(!equalsFlightList(airportList.get(i).getFlights(),expected.get(i).getFlights())) return false;
        }
        return true;
    }

    private boolean equalsFlight(Flight flight, Flight expected){
        if(!flight.getCompanyName().equals(expected.getCompanyName())) return false;
        if(flight.getTimeInFlight() != expected.getTimeInFlight()) return false;
        if((flight.getTimeTakeOff().compareTo(expected.getTimeTakeOff()))!= 0)return false;
        if(flight.getFrom() != expected.getFrom()) return false;
        if(flight.getTo() != expected.getTo()) return false;
        if(flight.getPrice() != expected.getPrice()) return false;
        if(flight.getDateOfFlight().compareTo(expected.getDateOfFlight())!=0) return false;
        return true;
    }
    private boolean equalsAirport(Airport airport, Airport expected){
        if(!airport.getName().equals(expected.getName())) return false;
        return true;
    }
    private boolean equalsByNameOfAirport(ArrayList<Airport> airportList, ArrayList<String> nameOfCompanyList) {
        for(int i=0; i<airportList.size() ;i++){
            if(!airportList.get(i).getName().equals(nameOfCompanyList.get(i))) return false;
        }
        return true;
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @org.testng.annotations.Test(dataProvider = "addAirport")
    public void addAirportTest(int firstCount, int secondCount){
        assertNotEquals(firstCount,secondCount);
    }

    @DataProvider
    public Object[][] addAirport() throws SQLException {
        Airport airport = new AirportBuilder().setIdentification(5).setName("NAME").build();
        int firstCount = jdbc.getCountOfAirports();
        jdbc.addAirport(airport);
        int secondCount = jdbc.getCountOfAirports();
        return new Object[][]{{firstCount,secondCount}};
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @org.testng.annotations.Test(dataProvider = "addFlight")
    public void addFlightTest(int firstCount, int secondCount){
        assertNotEquals(firstCount,secondCount);
    }

    @DataProvider
    public Object[][] addFlight() throws SQLException {
        int firstCount = jdbc.getCountOfFlights();
        Flight flight = new Flight.FlightBuilder()
                .setId()
                .setCompanyName("Fdea")
                .setDateOfFlight(LocalDate.of(2015, Month.SEPTEMBER, 25))
                .setTimeTakeOff(LocalTime.of(14, 30))
                .setTimeInFlight(120).setPrice(120.99)
                .setFrom(Flight.City.BERLIN)
                .setTo(Flight.City.TOKIO)
                .build();
        jdbc.addFlight(flight,firstAirport.getId());
        int secondCount = jdbc.getCountOfFlights();
        return new Object[][]{{firstCount,secondCount}};
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @org.testng.annotations.Test(dataProvider = "getAirports")
    public void getAirportsTest(ArrayList<Airport> airportList, ArrayList<String> nameOfCompanyList){
        assertTrue(equalsByNameOfAirport(airportList,nameOfCompanyList));
    }

    @DataProvider
    public Object[][] getAirports() throws SQLException {

        List<String> nameOfCompanyList = new ArrayList<String>();
        nameOfCompanyList.add("New Line");
        nameOfCompanyList.add("MAO");

        List<Airport> list = new ArrayList<Airport>();
        list = jdbc.getAllAirports();
        return new Object[][]{{list,nameOfCompanyList}};
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @org.testng.annotations.Test(dataProvider = "getFlightsFromAirport")
    public void getAllFlightsFromAirportTest(ArrayList<Flight> flightsList, ArrayList<Flight> expectedList){
        assertTrue(equalsFlightList(flightsList,expectedList));
    }

    @DataProvider
    public Object[][] getFlightsFromAirport() throws SQLException {
        List<Flight> expectedFlights = new ArrayList<Flight>();
        expectedFlights.add(flight3);
        expectedFlights.add(flight2);


        List<Flight> flights = new ArrayList<Flight>();
        flights = (ArrayList<Flight>) jdbc.getAllFlightsFromAirport(secondAirport.getId());
        return new Object[][]{{flights,expectedFlights}};
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @org.testng.annotations.Test(dataProvider = "getAirport")
    public void getAirportTest(Airport airport, Airport expected){
        assertTrue(equalsAirport(airport,expected));
    }

    @DataProvider
    public Object[][] getAirport() throws SQLException {
        Airport expectedAirport = new AirportBuilder().setIdentification(1).setName("MAO").build();
        Airport airport = jdbc.getAirport(firstAirport.getId());
        return new Object[][]{{airport,expectedAirport}};
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @org.testng.annotations.Test(dataProvider = "getFlight")
    public void getFlightTest(Flight airport, Flight expected){
        assertTrue(equalsFlight(airport,expected));
    }

    @DataProvider
    public Object[][] getFlight() throws SQLException {
        Flight expected = new Flight.FlightBuilder()
                .setId()
                .setCompanyName("Eclipse")
                .setDateOfFlight(LocalDate.of(2013, Month.SEPTEMBER, 25))
                .setTimeTakeOff(LocalTime.of(8, 30))
                .setTimeInFlight(120)
                .setPrice(120.99)
                .setFrom(Flight.City.KHARKIV)
                .setTo(Flight.City.WASHINGTON)
                .build();

        Flight flight = jdbc.getFlight(273);
        return new Object[][]{{flight,expected}};
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @org.testng.annotations.Test(dataProvider = "deleteFlight")
    public void deleteFlightTest(int firstCount, int secondCount){
        assertNotEquals(firstCount,secondCount);
    }

    @DataProvider
    public Object[][] deleteFlight() throws SQLException, ClassNotFoundException {

        int firstCount = jdbc.getCountOfFlights();
        jdbc.deleteFlight(278);
        int secondCount = jdbc.getCountOfFlights();
        return new Object[][]{{firstCount,secondCount}};
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @org.testng.annotations.Test(dataProvider = "deleteFlightByPeriod")
    public void deleteFlightByPeriodTest(int firstCount){
        assertNotEquals(firstCount,3);
    }

    @DataProvider
    public Object[][] deleteFlightByPeriod() throws SQLException, ClassNotFoundException {

        int firstCount = jdbc.getCountOfFlights();
        LocalDate stDate = LocalDate.of(2010,9,25);
        LocalDate enDate = LocalDate.of(2017,9,25);
        jdbc.deleteFlightByPeriod(1,stDate,enDate);
        return new Object[][]{{firstCount}};
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @org.testng.annotations.Test(dataProvider = "updateAirport")
    public void updateAirportTest(Airport airport, Airport expected){
        assertEquals(airport.getName(),expected.getName());
    }

    @DataProvider
    public Object[][] updateAirport() throws SQLException, ClassNotFoundException {
        Airport expected = new AirportBuilder().setIdentification(2).setName("Turkish Airline").build();
        jdbc.updateAirport(expected);
        Airport airport = new Airport();
        airport = jdbc.getAirport(expected.getId());
        return new Object[][]{{airport,expected}};
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @org.testng.annotations.Test(dataProvider = "updateFlight")
    public void updateFlightTest(Flight flight, Flight expected){
        assertTrue(equalsFlight(flight,expected));
    }

    @DataProvider
    public Object[][] updateFlight() throws SQLException, ClassNotFoundException {
        Flight expected = new Flight.FlightBuilder()
                .setIdentification(276)
                .setCompanyName("West")
                .setDateOfFlight(LocalDate.of(2011, Month.SEPTEMBER, 25))
                .setTimeTakeOff(LocalTime.of(10, 30))
                .setTimeInFlight(120)
                .setPrice(120.99)
                .setFrom(Flight.City.MADRID)
                .setTo(Flight.City.KIEV)
                .build();
        jdbc.updateFlight(expected);
        Flight flight = jdbc.getFlight(276);
        return new Object[][]{{flight,expected}};
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @org.testng.annotations.Test(dataProvider = "moveFlightsToAirport")
    public void moveFlightsToAirportTest(int flightCount,int expected){
        assertEquals(flightCount,expected);
    }


    @DataProvider
    public Object[][] moveFlightsToAirport() throws SQLException, ClassNotFoundException {
        int oldId=4;
        int newId=5;
        jdbc.moveFlightsToAirport(oldId,newId);
        ArrayList<Flight> flights = (ArrayList<Flight>) jdbc.getAllFlightsFromAirport(5);
        return new Object[][]{{flights.size(),3}};
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @org.testng.annotations.Test(dataProvider = "getFlightToCity")
    public void getFlightToCityTest(int flightCount,int expected){
        assertEquals(flightCount,expected);
    }


    @DataProvider
    public Object[][] getFlightToCity() throws SQLException, ClassNotFoundException {
        ArrayList<Flight> flights = null;
        flights = (ArrayList<Flight>) jdbc.getFlightToCity("K");
        return new Object[][]{{flights.size(),1}};
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @org.testng.annotations.Test(dataProvider = "deleteAllFlights")
    public void deleteAllFlightsTest(int flightCount,int expected){
        assertEquals(flightCount,expected);
    }


    @DataProvider
    public Object[][] deleteAllFlights() throws SQLException, ClassNotFoundException {
        jdbc.deleteAllFlights();
        ArrayList<Flight> flights = jdbc.getAllFlights();
        return new Object[][]{{flights.size(),0}};
    }

}