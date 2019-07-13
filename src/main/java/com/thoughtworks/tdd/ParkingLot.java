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

    public int getCapacity() {
        return this.capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Ticket park(Car car) throws CarHasBeenPartedException, CarIsNullException {
        if (car == null) {
            throw new CarIsNullException();
        }
        if (parkingCarTicket.containsValue(car)) {
            throw new CarHasBeenPartedException();
        }

        Ticket ticket = new Ticket();
        parkingCarTicket.put(ticket, car);
        this.capacity--;
        return ticket;

    }

    public Car getCar(Ticket ticket) throws TicketIsUsedException {
        if (parkingCarTicket.containsKey(ticket)) {
            if (parkingCarTicket.get(ticket) != null) {
                Car car = parkingCarTicket.get(ticket);
                parkingCarTicket.put(ticket, null);
                this.capacity++;
                return car;
            } else {
                throw new TicketIsUsedException();
            }
        }
        return null;
    }
}
