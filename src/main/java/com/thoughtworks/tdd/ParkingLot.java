package com.thoughtworks.tdd;


import java.util.HashMap;

public class ParkingLot {
    private HashMap<Ticket, Car> parkingCarTicket;

    public ParkingLot(){
        this.parkingCarTicket = new HashMap<>();
    }

    public Ticket park(Car car) {
        Ticket ticket = new Ticket();
        parkingCarTicket.put(ticket, car);
        return ticket;
    }

    public Car getCar(Ticket ticket){
        return parkingCarTicket.get(ticket);
    }
}
