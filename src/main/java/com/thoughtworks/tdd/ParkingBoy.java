package com.thoughtworks.tdd;


import com.thoughtworks.exception.ParkingLotNotPositionException;

public class ParkingBoy {
    private ParkingLot parkingLot;
    public ParkingBoy(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public Ticket park(Car car) throws ParkingLotNotPositionException {
        Ticket ticket = parkingLot.park(car);
        return ticket;
    }

    public Car fetch(Ticket ticket) throws Exception {
        return parkingLot.getCar(ticket);
    }
}
