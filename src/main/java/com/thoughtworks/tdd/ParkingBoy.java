package com.thoughtworks.tdd;


import com.thoughtworks.exception.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParkingBoy {


    private ParkingLot[] parkingLots;

    public ParkingBoy() {
    }

    public ParkingBoy(ParkingLot[] parkingLots) {
        this.parkingLots = parkingLots;
    }

    public Ticket park(Car car) throws CarIsNullException, CarHasBeenPartedException, ParkingLotNotPositionException {
        Ticket ticket;
        for (int i = 0; i < parkingLots.length; i++) {
            if (parkingLots[i].getCapacity() > 0) {
                ticket = parkingLots[i].park(car);
                return ticket;
            }
        }
        throw new ParkingLotNotPositionException("Not enough position.");
    }

    public Car fetch(Ticket ticket) throws WrongTicketException, TicketIsUsedException {
        for (int i = 0; i < parkingLots.length; i++) {
            Car car = parkingLots[i].getCar(ticket);
            if (car != null)
                return car;
        }
        throw new WrongTicketException("Unrecognized parking ticket.");
    }

}
