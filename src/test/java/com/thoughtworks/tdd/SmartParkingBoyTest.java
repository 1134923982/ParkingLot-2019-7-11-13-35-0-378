package com.thoughtworks.tdd;

import com.thoughtworks.exception.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertSame;

public class SmartParkingBoyTest {
    @Test
    public void should_park_in_more_positions_parkingLot_when_have_multiple_parkingLot() throws CarIsNullException, ParkingLotNotPositionException, CarHasBeenPartedException {
        //given
        Car firstCar = new Car();
        Car secondCar = new Car();

        ParkingLot[] parkingLots = {new ParkingLot(), new ParkingLot()};
        SmartParkingBoy parkingBoy = new SmartParkingBoy(parkingLots);

        //when
        parkingBoy.park(firstCar);
        parkingBoy.park(secondCar);

        //then
        assertSame(9, parkingLots[0].getCapacity());
        assertSame(9, parkingLots[1].getCapacity());
    }
}


