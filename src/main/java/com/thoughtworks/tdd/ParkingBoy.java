package com.thoughtworks.tdd;


import com.thoughtworks.exception.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParkingBoy {


    private ParkingLot[] parkingLots;

    public ParkingBoy() {
    }

    public ParkingBoy(ParkingLot[] parkingLots) {
        this.parkingLots = parkingLots;
    }

    public ParkingLot[] getParkingLots() {
        return parkingLots;
    }

    public Ticket park(Car car) throws CarIsNullException, CarHasBeenPartedException, ParkingLotNotPositionException {
        ParkCarResult parkCarResult = new ParkCarResult();
        Ticket ticket;
        for (int i = 0; i < parkingLots.length; i++) {
            if (parkingLots[i].getCapacity() > 0) {
                ticket = parkingLots[i].park(car);
                return ticket;
//                try {
//                    parkCarResult.setTicket(parkingLots[i].park(car));
//                }catch (CarIsNullException carIsNullException) {
//                    parkCarResult.setTicket(null);
//                }catch (CarHasBeenPartedException carIsNullException) {
//                    parkCarResult.setTicket(null);
//                } finally {
//                    parkCarResult.setParkingLots(parkingLots);
//                    return parkCarResult;
//                }
            }
//            if(i==parkingLots.length-1){
//                parkCarResult.setMessage("Not enough position.");
//                parkCarResult.setTicket(null);
//            }
        }
        throw new ParkingLotNotPositionException("Not enough position.");
    }

    public Car fetch(Ticket ticket) throws WrongTicketException, TicketIsUsedException {
        FetchCarResult fetchCarResult = new FetchCarResult();
        for (int i = 0; i < parkingLots.length; i++) {
            Car car = parkingLots[i].getCar(ticket);
            if (car != null)
                return car;
//            try {
//                fetchCarResult.setCar(parkingLots[i].getCar(ticket));
//                fetchCarResult.setMessage(null);
//                if(fetchCarResult.getCar()!=null)
//                    return fetchCarResult;
//            } catch (TicketIsUsedException ticketIsUsedException) {
//                fetchCarResult.setCar(null);
//                fetchCarResult.setMessage("Unrecognized parking ticket.");
//                return fetchCarResult;
//            }
//            if(i==parkingLots.length-1){
//                fetchCarResult.setCar(null);
//                fetchCarResult.setMessage("Unrecognized parking ticket.");
//            }
        }
        if(ticket==null){
            throw new WrongTicketException("Unrecognized parking ticket.");
        }
        throw new WrongTicketException("Unrecognized parking ticket.");
    }

}
