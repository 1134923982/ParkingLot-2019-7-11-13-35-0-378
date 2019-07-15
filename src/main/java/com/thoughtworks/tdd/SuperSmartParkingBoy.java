package com.thoughtworks.tdd;


import com.thoughtworks.exception.CarHasBeenPartedException;
import com.thoughtworks.exception.CarIsNullException;
import com.thoughtworks.exception.ParkingLotNotPositionException;
import com.thoughtworks.exception.TicketIsUsedException;

import java.util.Arrays;
import java.util.Comparator;

public class SuperSmartParkingBoy extends ParkingBoy{
    private ParkingLot[] parkingLots;

    public SuperSmartParkingBoy(ParkingLot[] parkingLots) {
        super(parkingLots);
        this.parkingLots = parkingLots;
    }

    public Ticket park(Car car) throws ParkingLotNotPositionException, CarIsNullException, CarHasBeenPartedException {
        ParkingLot parkingLot = Arrays.stream(parkingLots).max(Comparator.comparing(p -> {
            return p.getCapacity()/(double)(p.getMaxCapacity());
        })).get();
        if(parkingLot.getCapacity()>0){
            return parkingLot.park(car);
        }
        throw new ParkingLotNotPositionException("Not enough position.");
    }

}
