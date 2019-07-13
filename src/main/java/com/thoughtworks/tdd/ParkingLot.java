package com.thoughtworks.tdd;


import com.thoughtworks.exception.*;

import java.util.HashMap;

public class ParkingLot {
    private HashMap<Ticket, Car> parkingCarTicket;
    private int capacity;

    public ParkingLot() {
        this.capacity = 10;
        this.parkingCarTicket = new HashMap<>();
    }

    public Ticket park(Car car) throws ParkingLotNotPositionException, CarHasBeenPardedException, CarIsNullException {
        if(car == null){
            throw new CarIsNullException();
        }
        if(parkingCarTicket.containsValue(car)){
            throw new CarHasBeenPardedException();
        }
        if(capacity>0){
            Ticket ticket = new Ticket();
            parkingCarTicket.put(ticket, car);
            this.capacity--;
            return ticket;
        }else {
            throw new ParkingLotNotPositionException();
        }
    }

    public Car getCar(Ticket ticket) throws Exception {
        if (parkingCarTicket.containsKey(ticket)) {
            if (parkingCarTicket.get(ticket) != null) {
                Car car = parkingCarTicket.get(ticket);
                parkingCarTicket.put(ticket,null);
                this.capacity++;
                return car;
            } else {
                throw new TicketIsUsedException();
            }
        } else {
            throw new WrongTicketException();
        }

    }
}
