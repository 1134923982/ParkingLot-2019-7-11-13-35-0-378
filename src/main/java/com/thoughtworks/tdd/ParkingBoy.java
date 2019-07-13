package com.thoughtworks.tdd;


import com.thoughtworks.exception.*;

public class ParkingBoy {
    private ParkingLot parkingLot;
    public ParkingBoy(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public Ticket park(Car car) throws ParkingLotNotPositionException, CarHasBeenPardedException, CarIsNullException {
        Ticket ticket = parkingLot.park(car);
        return ticket;
    }

    public FetchCarResult fetch(Ticket ticket){
        FetchCarResult fetchCarResult= new FetchCarResult();
        try{
            fetchCarResult.setCar(parkingLot.getCar(ticket));
        }catch (TicketIsUsedException ticketIsUsedException){
            fetchCarResult.setCar(null);
            fetchCarResult.setMessage("Unrecognized parking ticket.");
        }catch (WrongTicketException wrongTicketException){
            fetchCarResult.setCar(null);
            fetchCarResult.setMessage("Unrecognized parking ticket.");
        }finally {
            return fetchCarResult;
        }

    }
}
