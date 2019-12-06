import models.Airport;
import models.Flight;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import providers.AirportProvider;
import providers.FlightProvider;
import providers.FlightProviderImpl;
import services.AirportService;
import services.FlightService;
import services.FlightServiceImpl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

    public class TestFlights {

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

            flights = new ArrayList<Flight>();
            flightProvider = new FlightProviderImpl(flights);
            flightService = new FlightServiceImpl(flightProvider);

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


        @org.testng.annotations.Test(dataProvider = "flightAddTest")
        public void addFlightTest(Flight addFlight, Flight expected){
            assertEquals(addFlight,expected);
        }

        @DataProvider
        public Object[][] flightAddTest(){
            int id = 7;
            try {
                flight = new Flight.FlightBuilder()
                        .setIdentification(id)
                        .setCompanyName("New Line")
                        .setDateOfFlight(LocalDate.of(2015, Month.OCTOBER, 12))
                        .setTimeTakeOff(LocalTime.of(3, 20))
                        .setTimeInFlight(60)
                        .setPrice(250)
                        .setFrom(Flight.City.CHICAGO)
                        .setTo(Flight.City.PARIS)
                        .build();
                flightService.addFlight(flight);
            } catch (RuntimeException e) {
                System.out.println(e);
            }
            return new Object[][]{{flightService.getFlight(id),flight}};
        }
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////

        @org.testng.annotations.Test(expectedExceptions = IllegalArgumentException.class)
        public void addFlightTestException(){
            flightService.addFlight(new Flight.FlightBuilder()
                    .setIdentification(5)
                    .setCompanyName("West Side")
                    .setDateOfFlight(LocalDate.of(2017, Month.SEPTEMBER, 25))
                    .setTimeTakeOff(LocalTime.of(8, 30))
                    .setTimeInFlight(120)
                    .setPrice(120.99)
                    .setFrom(Flight.City.KIEV)
                    .setTo(Flight.City.LONDON)
                    .build());
        }
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////


        @org.testng.annotations.Test(expectedExceptions = IllegalArgumentException.class)
        public void editFlightTestException(){
            Flight flight = new Flight.FlightBuilder()
                    .setIdentification(10)
                    .setCompanyName("West Side")
                    .setDateOfFlight(LocalDate.of(2017, Month.SEPTEMBER, 25))
                    .setTimeTakeOff(LocalTime.of(8, 30))
                    .setTimeInFlight(120)
                    .setPrice(120.99)
                    .setFrom(Flight.City.KIEV)
                    .setTo(Flight.City.LONDON)
                    .build();
            flightService.editFlight(flight);
        }
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////

        @org.testng.annotations.Test(expectedExceptions = IllegalArgumentException.class)
        public void deleteFlightTestException(){
            flightService.deleteFlight(10);
        }
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////

        @org.testng.annotations.Test(dataProvider = "flightEditTest")
        public void editFlightTest(Flight editFlight, Flight expected){
            assertNotEquals(editFlight,expected);
        }

        @DataProvider
        public Object[][] flightEditTest(){
            int id = 1;
            Flight editFlight = null;
            try {
                flight = new Flight.FlightBuilder()
                        .setIdentification(id)
                        .setCompanyName("New Line")
                        .setDateOfFlight(LocalDate.of(2015, Month.OCTOBER, 12))
                        .setTimeTakeOff(LocalTime.of(3, 20))
                        .setTimeInFlight(60)
                        .setPrice(250)
                        .setFrom(Flight.City.CHICAGO)
                        .setTo(Flight.City.PARIS)
                        .build();
                flightService.addFlight(flight);
            } catch (RuntimeException e) {
                System.out.println(e);
            }

            Flight fl = new Flight();
            fl.setId(flight.getId());
            fl.setCompanyName(flight.getCompanyName());
            fl.setDateOfFlight(flight.getDateOfFlight());
            fl.setTimeInFlight(flight.getTimeInFlight());
            fl.setTimeTakeOff(flight.getTimeTakeOff());
            fl.setPrice(flight.getPrice());
            fl.setTo(flight.getTo());
            fl.setFrom(flight.getFrom());

            try {
                editFlight = new Flight.FlightBuilder()
                        .setIdentification(id)
                        .setCompanyName("North Line")
                        .setDateOfFlight(LocalDate.of(2015, Month.OCTOBER, 12))
                        .setTimeTakeOff(LocalTime.of(3, 20))
                        .setTimeInFlight(50)
                        .setPrice(150)
                        .setFrom(Flight.City.WARSAW)
                        .setTo(Flight.City.MADRID)
                        .build();
            } catch (RuntimeException e) {
                System.out.println(e);
            }
            flightService.editFlight(editFlight);

            return new Object[][]{{flightService.getFlight(id),fl}};
        }

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////

        @org.testng.annotations.Test(dataProvider = "flightDeleteTest")
        public void deleteFlightTest(Flight deletedFlight, Flight expected){
            assertNotEquals(deletedFlight,expected);
        }

        @DataProvider
        public Object[][] flightDeleteTest(){
            int id = 8;
            try {
                flight = new Flight.FlightBuilder()
                        .setIdentification(id)
                        .setCompanyName("New Line")
                        .setDateOfFlight(LocalDate.of(2015, Month.OCTOBER, 12))
                        .setTimeTakeOff(LocalTime.of(3, 20))
                        .setTimeInFlight(60)
                        .setPrice(250)
                        .setFrom(Flight.City.CHICAGO)
                        .setTo(Flight.City.PARIS)
                        .build();
                flightService.addFlight(flight);
            } catch (RuntimeException e) {
                System.out.println(e);
            }

            Flight fl = new Flight();
            fl.setId(flight.getId());
            fl.setCompanyName(flight.getCompanyName());
            fl.setDateOfFlight(flight.getDateOfFlight());
            fl.setTimeInFlight(flight.getTimeInFlight());
            fl.setTimeTakeOff(flight.getTimeTakeOff());
            fl.setPrice(flight.getPrice());
            fl.setTo(flight.getTo());
            fl.setFrom(flight.getFrom());

            flightService.deleteFlight(id);
            return new Object[][]{{flightService.getFlight(id),fl}};
        }

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////


        @org.testng.annotations.Test(dataProvider = "searchPath")
        public void searchTest(List<Flight> path, List<Flight> expected){
            assertTrue(check(path,expected));
        }

        @DataProvider
        public Object[][] searchPath(){
            initialize();
            int id = 1;
            List <Flight> expected = new ArrayList<>();
            try {
                flight = new Flight.FlightBuilder()
                        .setId()
                        .setCompanyName("West Side")
                        .setDateOfFlight(LocalDate.of(2017, Month.SEPTEMBER, 25))
                        .setTimeTakeOff(LocalTime.of(8, 30))
                        .setTimeInFlight(120)
                        .setPrice(120.99)
                        .setFrom(Flight.City.KIEV)
                        .setTo(Flight.City.OTTAWA)
                        .build();
                expected.add(flight);
            } catch (RuntimeException e) {
                System.out.println(e);
            }
            List <Flight> path = new ArrayList<Flight>();
            path = flightService.searchPath(Flight.City.KIEV, Flight.City.OTTAWA,2);
            return new Object[][]{{path,expected}};
        }

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////

        @org.testng.annotations.Test(dataProvider = "fromList")
        public void fromTest(List<Flight> from, List<Flight> expected){
            assertTrue(check(from,expected));
        }

        @DataProvider
        public Object[][] fromList(){

            List <Flight> expected = new ArrayList<>();
            try {
                flight = new Flight.FlightBuilder()
                        .setId()
                        .setCompanyName("West")
                        .setDateOfFlight(LocalDate.of(2017, Month.SEPTEMBER, 25))
                        .setTimeTakeOff(LocalTime.of(14, 30))
                        .setTimeInFlight(120)
                        .setPrice(120.99)
                        .setFrom(Flight.City.LONDON)
                        .setTo(Flight.City.OTTAWA)
                        .build();
                expected.add(flight);
            } catch (RuntimeException e) {
                System.out.println(e);
            }
            List <Flight> fromList = new ArrayList<>();
            fromList = flightService.getFlightFrom(Flight.City.LONDON);
            return new Object[][]{{fromList,expected}};
        }

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////

        @org.testng.annotations.Test(dataProvider = "toList")
        public void toTest(List<Flight> to, List<Flight> expected){
            assertTrue(check(to,expected));
        }
        @DataProvider
        public Object[][] toList(){

            List <Flight> expected = new ArrayList<>();
            try {
                flight = new Flight.FlightBuilder()
                        .setId()
                        .setCompanyName("West Side")
                        .setDateOfFlight(LocalDate.of(2017, Month.SEPTEMBER, 25))
                        .setTimeTakeOff(LocalTime.of(8, 30))
                        .setTimeInFlight(120)
                        .setPrice(120.99)
                        .setFrom(Flight.City.KIEV)
                        .setTo(Flight.City.LONDON)
                        .build();
                expected.add(flight);
            } catch (RuntimeException e) {
                System.out.println(e);
            }
            List <Flight> toList = new ArrayList<Flight>();
            toList = flightService.getFlightTo(Flight.City.LONDON);

            return new Object[][]{{toList,expected}};
        }

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////

        @org.testng.annotations.Test(dataProvider = "getTime")
        public void timeTest(LocalTime timeOfLanding,LocalTime expected){
            assertTrue(timeOfLanding.compareTo(expected) == 0);
        }
        @DataProvider
        public Object[][] getTime(){

            try {
                flight = new Flight.FlightBuilder()
                        .setId()
                        .setCompanyName("West Side")
                        .setDateOfFlight(LocalDate.of(2017, Month.SEPTEMBER, 25))
                        .setTimeTakeOff(LocalTime.of(8, 30))
                        .setTimeInFlight(120)
                        .setPrice(120.99)
                        .setFrom(Flight.City.KIEV)
                        .setTo(Flight.City.LONDON)
                        .build();
            } catch (RuntimeException e) {
                System.out.println(e);
            }
            LocalTime expectedTime = LocalTime.of(10,30);
            LocalTime timeOfLanding = flightService.getTimeOfLanding(flight);
            return new Object[][]{{timeOfLanding,expectedTime}};
        }

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    }

