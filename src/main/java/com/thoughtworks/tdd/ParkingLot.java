package com.thoughtworks.tdd;


import com.thoughtworks.exception.*;

import java.util.HashMap;

public class ParkingLot {
    private HashMap<Ticket, Car> parkingCarTicket;
    private int capacity;
    private int maxCapacity;

    public ParkingLot() {
        this.capacity = 10;
        this.maxCapacity = this.capacity;
        this.parkingCarTicket = new HashMap<>();
    }

    public ParkingLot(int capacity) {
        this.capacity = capacity;
        this.maxCapacity = this.capacity;
        this.parkingCarTicket = new HashMap<>();
    }

    public int getCapacity() {
        return this.capacity;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public Ticket park(Car car) throws CarHasBeenPartedException, CarIsNullException, ParkingLotNotPositionException {
        if (car == null) {
            throw new CarIsNullException("Unrecognized parking ticket.");
        }
        if (parkingCarTicket.containsValue(car)) {
            throw new CarHasBeenPartedException("Unrecognized parking ticket.");
        }
        if (this.capacity==0) {
            throw new ParkingLotNotPositionException("Not enough position.");
        }

        Ticket ticket = new Ticket();
        parkingCarTicket.put(ticket, car);
        this.capacity--;
        return ticket;

    }


    public Car getCar(Ticket ticket) throws TicketIsUsedException, WrongTicketException {
        if (parkingCarTicket.containsKey(ticket)) {
            if (parkingCarTicket.get(ticket) != null) {
                Car car = parkingCarTicket.get(ticket);
                parkingCarTicket.put(ticket, null);
                this.capacity++;
                return car;
            } else {
                throw new TicketIsUsedException("Unrecognized parking ticket.");
            }
        }
        if(ticket==null){
            throw new WrongTicketException("Please provide your parking ticket.");
        }
        throw new WrongTicketException("Unrecognized parking ticket.");
    }
}
