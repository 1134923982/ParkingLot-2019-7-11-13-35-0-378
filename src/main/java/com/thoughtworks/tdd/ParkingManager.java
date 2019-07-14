package com.thoughtworks.tdd;

import java.util.ArrayList;
import java.util.List;

public class ParkingManager extends ParkingBoy{

    private ArrayList<ParkingBoy> parkingBoys = new ArrayList<>();

    public ParkingManager(ParkingLot[] parkingLots) {
        super(parkingLots);
    }

    public void addParkingBoy(ParkingBoy parkingBoy){
        parkingBoys.add(parkingBoy);
    }

    public ParkingBoy getParkingBoy(int x) {
        return parkingBoys.get(x);
    }
}
