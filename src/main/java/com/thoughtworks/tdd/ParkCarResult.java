package com.thoughtworks.tdd;

public class ParkCarResult {
    private Ticket ticket;
    private String message;
    private ParkingLot[] parkingLots;

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public ParkingLot[] getParkingLots() {
        return parkingLots;
    }

    public void setParkingLots(ParkingLot[] parkingLot) {
        this.parkingLots = parkingLot;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
