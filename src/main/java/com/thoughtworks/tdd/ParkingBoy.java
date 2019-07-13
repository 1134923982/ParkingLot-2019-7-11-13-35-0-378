package com.thoughtworks.tdd;


import com.thoughtworks.exception.*;

import java.util.Arrays;

public class ParkingBoy {
    private ParkingLot[] parkingLots;

    public ParkingBoy(ParkingLot[] parkingLots) {
        this.parkingLots = parkingLots;
    }

    public ParkCarResult park(Car car) {
        ParkCarResult parkCarResult = new ParkCarResult();

        for(int i=0; i<parkingLots.length; i++){
            if(parkingLots[i].getCapacity()>0){
                try {
                    parkCarResult.setTicket(parkingLots[i].park(car));
                }catch (CarIsNullException carIsNullException) {
                    parkCarResult.setTicket(null);
                }catch (CarHasBeenPartedException carIsNullException) {
                    parkCarResult.setTicket(null);
                } finally {
                    parkCarResult.setParkingLots(parkingLots);
                    return parkCarResult;
                }
            }
            if(i==parkingLots.length-1){
                parkCarResult.setMessage("Not enough position.");
                parkCarResult.setTicket(null);
            }
        }
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
