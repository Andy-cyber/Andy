package main;

import dataBase.JDBC;
import models.Airport;
import models.AirportBuilder;
import models.Flight;
import providers.AirportProvider;
import providers.AirportProviderImpl;
import services.AirportService;
import services.AirportServiceImpl;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        Airport airport = new AirportBuilder().setIdentification(4).setName("MAO").build();
        List<Airport> airports = new ArrayList<Airport>();
        AirportProvider airportProvider = new AirportProviderImpl(airports);
        AirportService airportService = new AirportServiceImpl(airportProvider);

        Airport myAirport = new AirportBuilder().setIdentification(4).setName("Enterprise").build();
        airportService.addAirport(myAirport);

        Airport secondAirport = new AirportBuilder().setId().setName("New Line").build();
        airportService.addAirport(secondAirport);

        try {
            Flight flight = new Flight.FlightBuilder()
                    .setId()
                    .setCompanyName("Eclipse")
                    .setDateOfFlight(LocalDate.of(2013, Month.SEPTEMBER, 25))
                    .setTimeTakeOff(LocalTime.of(8, 30))
                    .setTimeInFlight(120)
                    .setPrice(120.99)
                    .setFrom(Flight.City.KHARKIV)
                    .setTo(Flight.City.WASHINGTON)
                    .build();
            airportService.addFlight(myAirport.getId(), flight);

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
                    .setTo(Flight.City.KHARKIV)
                    .build();
            airportService.addFlight(secondAirport.getId(), flight);
        } catch (RuntimeException e) {
            System.out.println(e);
        }

        try {
            Flight flight = new Flight.FlightBuilder()
                    .setId()
                    .setCompanyName("West")
                    .setDateOfFlight(LocalDate.of(2011, Month.SEPTEMBER, 25))
                    .setTimeTakeOff(LocalTime.of(10, 30))
                    .setTimeInFlight(120)
                    .setPrice(120.99)
                    .setFrom(Flight.City.MADRID)
                    .setTo(Flight.City.KIEV)
                    .build();
            airportService.addFlight(secondAirport.getId(), flight);
        } catch (RuntimeException e) {
            System.out.println(e);
        }

        try {
            Flight flight = new Flight.FlightBuilder()
                    .setId()
                    .setCompanyName("Eclipce")
                    .setDateOfFlight(LocalDate.of(2019, Month.SEPTEMBER, 25))
                    .setTimeTakeOff(LocalTime.of(14, 30))
                    .setTimeInFlight(120)
                    .setPrice(120.99)
                    .setFrom(Flight.City.MADRID)
                    .setTo(Flight.City.MINSK)
                    .build();
            airportService.addFlight(myAirport.getId(), flight);
        } catch (RuntimeException e) {
            System.out.println(e);
        }
        Flight flight = null;
        try {
            flight = new Flight.FlightBuilder()
                    .setId()
                    .setCompanyName("Fdea")
                    .setDateOfFlight(LocalDate.of(2015, Month.SEPTEMBER, 25))
                    .setTimeTakeOff(LocalTime.of(14, 30))
                    .setTimeInFlight(120).setPrice(120.99)
                    .setFrom(Flight.City.BERLIN)
                    .setTo(Flight.City.TOKIO)
                    .build();
            airportService.addFlight(myAirport.getId(), flight);
        } catch (RuntimeException e) {
            System.out.println(e);
        }
        System.out.println();
        JDBC jdbc = null;
        try {
            jdbc = new JDBC();
            jdbc.clearDataBase();
            jdbc.addAirport(myAirport);
            for (int i=0;i<myAirport.getFlights().size();i++){
                jdbc.addFlight(myAirport.getFlights().get(i),myAirport.getId());
            }
            jdbc.addAirport(secondAirport);
            for (int i=0;i<secondAirport.getFlights().size();i++){
                jdbc.addFlight(secondAirport.getFlights().get(i),secondAirport.getId());
            }
            System.out.println(jdbc.getAirportByFlight(2));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}