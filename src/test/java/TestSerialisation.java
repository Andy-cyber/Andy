import models.Airport;
import models.AirportBuilder;
import models.Flight;
import org.testng.annotations.BeforeTest;
import serialisation.JsonSerializeImpl;
import serialisation.Serialisation;
import serialisation.XmlSerializeImpl;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class TestSerialisation {
    File jsonFile = new File("jsonFile.json");
    File xmlFile = new File("xmlFile.xml");
    ArrayList<Airport> airports;// =new ArrayList<>();;
    ArrayList<Flight> flights;//=new ArrayList<>();

    private boolean checkFlight(List<Flight> flightList, List<Flight> expected) {
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
    private boolean checkAirport(ArrayList<Airport> arrayList, ArrayList<Airport> expected) {
        for(int i = 0;i<arrayList.size();i++){
            if(arrayList.get(i).getId() != expected.get(i).getId()) return false;
            if(!arrayList.get(i).getName().equals(expected.get(i).getName())) return false;
            if(!checkFlight(arrayList.get(i).getFlights(),expected.get(i).getFlights())) return false;
        }
        return true;
    }
    @BeforeTest
    public void initialize(){
        airports = new ArrayList<>();
        flights = new ArrayList<>();
        Airport airport = new AirportBuilder().setId().setName("MAO").build();
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
            flights.add(flight);
            airport.setList(flights);
            airports.add(airport);
        } catch (RuntimeException e) {
            System.out.println(e);
        }
    }
    @org.testng.annotations.Test//(dataProvider = "jsonSerialisation")
    public void jsonTest() throws Exception {
        Serialisation<Airport> json = new JsonSerializeImpl<Airport>(Airport.class);
        ArrayList<Airport> actual  = json.fromFile(jsonFile);
        System.out.println(actual);
        assertTrue(this.checkAirport(actual,airports));
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @org.testng.annotations.Test//(dataProvider = "xmlSerialisation")
    public void xmlTest() throws Exception {
        Serialisation<Airport> xml = new XmlSerializeImpl<Airport>(Airport.class);
        xml.toFile(airports.get(0), xmlFile);
        ArrayList<Airport> actual = xml.fromFile(xmlFile);
        System.out.println(actual);
        assertTrue(this.checkAirport(actual, airports));
    }

}