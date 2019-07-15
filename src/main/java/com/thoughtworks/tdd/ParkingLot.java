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
        CarIsNull(car);
        CarHasBeenParted(car);
        ParkingLotNotPosition();
        return getTicket(car);

    }

    private Ticket getTicket(Car car) {
        Ticket ticket = new Ticket();
        parkingCarTicket.put(ticket, car);
        this.capacity--;
        return ticket;
    }

    private boolean ParkingLotNotPosition() throws ParkingLotNotPositionException {
        if (this.capacity == 0) {
            throw new ParkingLotNotPositionException("Not enough position.");
        }
        return false;
    }

    private boolean CarHasBeenParted(Car car) throws CarHasBeenPartedException {
        if (parkingCarTicket.containsValue(car)) {
            throw new CarHasBeenPartedException("Unrecognized parking ticket.");
        }
        return false;
    }

    private boolean CarIsNull(Car car) throws CarIsNullException {
        if (car == null) {
            throw new CarIsNullException("Unrecognized parking ticket.");
        }
        return false;
    }


    public Car getCar(Ticket ticket) throws TicketIsUsedException, WrongTicketException {
        if (parkingCarTicket.containsKey(ticket)) {
            if (parkingCarTicket.get(ticket) != null) {
                return fetchCar(ticket);
            } else {
                throw new TicketIsUsedException("Unrecognized parking ticket.");
            }
        }
        return isWrongTicket(ticket);
    }

    private Car isWrongTicket(Ticket ticket) throws WrongTicketException {
        if (ticket == null) {
            throw new WrongTicketException("Please provide your parking ticket.");
        }
        throw new WrongTicketException("Unrecognized parking ticket.");
    }

    private Car fetchCar(Ticket ticket) {
        Car car = parkingCarTicket.get(ticket);
        parkingCarTicket.put(ticket, null);
        this.capacity++;
        return car;
    }
}
