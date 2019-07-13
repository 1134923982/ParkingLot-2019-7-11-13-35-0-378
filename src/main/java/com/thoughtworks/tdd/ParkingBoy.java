package com.thoughtworks.tdd;


import com.thoughtworks.exception.*;

public class ParkingBoy {
    private ParkingLot parkingLot;

    public ParkingBoy(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public ParkCarResult park(Car car) {
        ParkCarResult parkCarResult = new ParkCarResult();
        try {
            parkCarResult.setTicket(parkingLot.park(car));
        } catch (ParkingLotNotPositionException parkingLotNotPositionException) {
            parkCarResult.setTicket(null);
            parkCarResult.setMessage("Not enough position.");
        } catch (CarIsNullException carIsNullException) {
            parkCarResult.setTicket(null);
        }catch (CarHasBeenPardedException carIsNullException) {
            parkCarResult.setTicket(null);
        } finally {
            return parkCarResult;
        }
    }

    public FetchCarResult fetch(Ticket ticket) {
        FetchCarResult fetchCarResult = new FetchCarResult();
        try {
            fetchCarResult.setCar(parkingLot.getCar(ticket));
        } catch (TicketIsUsedException ticketIsUsedException) {
            fetchCarResult.setCar(null);
            fetchCarResult.setMessage("Unrecognized parking ticket.");
        } catch (WrongTicketException wrongTicketException) {
            if (ticket == null) {
                fetchCarResult.setMessage("Please provide your parking ticket.");
            } else {
                fetchCarResult.setMessage("Unrecognized parking ticket.");
            }
        } finally {
            return fetchCarResult;
        }

    }
}
