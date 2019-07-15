package com.thoughtworks.tdd;


import com.thoughtworks.exception.CarHasBeenPartedException;
import com.thoughtworks.exception.CarIsNullException;
import com.thoughtworks.exception.ParkingLotNotPositionException;
import com.thoughtworks.exception.TicketIsUsedException;

import java.util.Arrays;
import java.util.Comparator;

public class SmartParkingBoy extends ParkingBoy{
    private ParkingLot[] parkingLots;

    public SmartParkingBoy(ParkingLot[] parkingLots) {
        super(parkingLots);
        this.parkingLots = parkingLots;
    }

    public Ticket park(Car car) throws CarIsNullException, CarHasBeenPartedException, ParkingLotNotPositionException {
        ParkingLot parkingLot = getMorePositionParkingLot();
        if(parkingLot.getCapacity()>0){
            return  parkingLot.park(car);
        }
        throw new ParkingLotNotPositionException("Not enough position.");
    }

    private ParkingLot getMorePositionParkingLot() {
        return (ParkingLot) Arrays.stream(parkingLots).max(Comparator.comparing(ParkingLot::getCapacity)).get();
    }

}
