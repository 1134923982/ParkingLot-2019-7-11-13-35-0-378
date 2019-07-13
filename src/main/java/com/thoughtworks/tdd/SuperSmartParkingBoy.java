package com.thoughtworks.tdd;


import com.thoughtworks.exception.CarHasBeenPartedException;
import com.thoughtworks.exception.CarIsNullException;
import com.thoughtworks.exception.TicketIsUsedException;

import java.util.Arrays;
import java.util.Comparator;

public class SuperSmartParkingBoy extends ParkingBoy{
    private ParkingLot[] parkingLots;

    public SuperSmartParkingBoy(ParkingLot[] parkingLots) {
        super(parkingLots);
        this.parkingLots = parkingLots;
    }

    public ParkCarResult park(Car car) {
        ParkCarResult parkCarResult = new ParkCarResult();
        ParkingLot parkingLot = Arrays.stream(parkingLots).max(Comparator.comparing(p -> {
            return p.getCapacity()/(double)(p.getCapacity()+p.getParkingCarTicket().size());
        })).get();
        if(parkingLot.getCapacity()>0){
            try {
                parkCarResult.setTicket(parkingLot.park(car));
            }catch (CarIsNullException carIsNullException) {
                parkCarResult.setTicket(null);
            }catch (CarHasBeenPartedException carIsNullException) {
                parkCarResult.setTicket(null);
            } finally {
                parkCarResult.setParkingLots(parkingLots);
                return parkCarResult;
            }
        }
        parkCarResult.setMessage("Not enough position.");
        parkCarResult.setTicket(null);
        return parkCarResult;
    }

    public FetchCarResult fetch(Ticket ticket) {
        FetchCarResult fetchCarResult = new FetchCarResult();
        for(int i=0; i<parkingLots.length; i++){
            try {
                fetchCarResult.setCar(parkingLots[i].getCar(ticket));
                fetchCarResult.setMessage(null);
                if(fetchCarResult.getCar()!=null)
                    return fetchCarResult;
            } catch (TicketIsUsedException ticketIsUsedException) {
                fetchCarResult.setCar(null);
                fetchCarResult.setMessage("Unrecognized parking ticket.");
                return fetchCarResult;
            }
            if(i==parkingLots.length-1){
                fetchCarResult.setCar(null);
                fetchCarResult.setMessage("Unrecognized parking ticket.");
            }
        }
        if (ticket == null) {
            fetchCarResult.setMessage("Please provide your parking ticket.");
        }
        return fetchCarResult;
    }
}
