package providers;

import models.Flight;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.MINUTES;

public class FlightProviderImpl implements FlightProvider {

    private List<Flight> flightList = new ArrayList<>();
    public FlightProviderImpl(ArrayList<Flight> flightList) {
        super();
        this.flightList = flightList;
    }

    public ArrayList<Flight> getFlightList() {
        return (ArrayList<Flight>) flightList;
    }
    public void setFlightList(ArrayList<Flight> flightList) {
        this.flightList = flightList;
    }

    public boolean checkId(int id){
        for (Flight flight: flightList) {
            if(flight.getId() == id) return false;
        }
        return true;
    }

    @Override
    public Flight getFlight(int idFlight){
        return flightList.stream().filter((p)->p.getId() == idFlight).findAny().orElse(null);
    }

    public void addFlight(Flight flight){

        if (this.checkId(flight.getId()))
            flightList.add(flight);
        else
            throw new IllegalArgumentException();
    }

    @Override
    public void addFlight(int idAirport, Flight flight) {

    }

    @Override
    public void editFlight(Flight flight) {

        if (!(this.checkId(flight.getId()))) {
            Flight editFlight = getFlight(flight.getId());
            editFlight.setCompanyName(flight.getCompanyName());
            editFlight.setDateOfFlight(flight.getDateOfFlight());
            editFlight.setTimeInFlight(flight.getTimeInFlight());
            editFlight.setTimeTakeOff(flight.getTimeTakeOff());
            editFlight.setPrice(flight.getPrice());
            editFlight.setFrom(flight.getFrom());
            editFlight.setTo(flight.getTo());
        }
        else throw new IllegalArgumentException();
    }

    @Override
    public void deleteFlight(int idFlight) {
        if(!(this.checkId(idFlight))){
            flightList.remove(getFlight(idFlight));
        }
        else throw new IllegalArgumentException();
    }

    @Override
    public ArrayList<Flight> getFlightFrom(Flight.City from) {
        return flightList.stream().filter((p)->p.getFrom() == from).collect(Collectors
                .toCollection(ArrayList::new));
    }

    @Override
    public ArrayList<Flight> getFlightTo(Flight.City to) {
        return flightList.stream().filter((p)->p.getTo() == to).collect(Collectors.toCollection(ArrayList::new));
    }
    /**
     *
     * @param from - city from which you go away
     * @param to - city which you want to get to
     * @param time - time between time of landing and time of takeoff
     * @return
     */
    @Override
    public ArrayList<Flight> searchPath(Flight.City from, Flight.City to, int time) {
        ArrayList<int[]> massOfIndex = new ArrayList<>();
        ArrayList<Flight> arrayList = new ArrayList<>();
        for(Flight obj:flightList){
            if(obj.getFrom() == from && obj.getTo() == to){
                arrayList.add(obj);
                return arrayList;
            }
        }
        for (Flight obFrom:flightList) {
            if(obFrom.getFrom() == from){
                for (Flight obTo:flightList) {
                    if(obFrom.getTo() == obTo.getFrom()){
                        for (Flight o:flightList) {
                            if(obFrom.getTo() == o.getFrom() && o.getTo() == to){
                                massOfIndex.add(new int[]{flightList.indexOf(obFrom),flightList.indexOf(o)});
                            }
                        }
                    }
                }
            }
        }

        long keepMinimum = 10000;
        long currentTime;
        int i=0,j=0;
        String str="";

        for (int[] mass:massOfIndex) {

            currentTime =(MINUTES.between(this.getTimeOfLanding(flightList.get(mass[0])),this.getTimeOfLanding
                    (flightList.get(mass[1]))));

            if(currentTime > 0 && keepMinimum > currentTime && currentTime <= time){
                keepMinimum = currentTime;
                i=mass[0];
                j=mass[1];
            }
        }
        arrayList.add(flightList.get(i));
        arrayList.add(flightList.get(j));
        return arrayList;
    }

    @Override
    public LocalTime getTimeOfLanding(Flight obj){
        return obj.getTimeTakeOff().plusMinutes(obj.getTimeInFlight());
    }
}