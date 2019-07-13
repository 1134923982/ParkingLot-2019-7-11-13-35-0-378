package com.thoughtworks.tdd;


import com.thoughtworks.exception.TicketIsUsedException;
import com.thoughtworks.exception.WrongTicketException;

import java.util.HashMap;

public class ParkingLot {
    private HashMap<Ticket, Car> parkingCarTicket;

    public ParkingLot() {
        this.parkingCarTicket = new HashMap<>();
    }

    public Ticket park(Car car) {
        Ticket ticket = new Ticket();
        parkingCarTicket.put(ticket, car);
        return ticket;
    }

    public Car getCar(Ticket ticket) throws Exception {
        if (parkingCarTicket.containsKey(ticket)) {
            if (parkingCarTicket.get(ticket) != null) {
                Car car = parkingCarTicket.get(ticket);
                parkingCarTicket.put(ticket,null);
                return car;
            } else {
                throw new TicketIsUsedException();
            }
        } else {
            throw new WrongTicketException();
        }

    }
}
