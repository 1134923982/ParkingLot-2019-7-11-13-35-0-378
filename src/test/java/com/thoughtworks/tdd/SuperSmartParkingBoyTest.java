package com.thoughtworks.tdd;

import com.thoughtworks.exception.CarHasBeenPartedException;
import com.thoughtworks.exception.CarIsNullException;
import com.thoughtworks.exception.ParkingLotNotPositionException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;

public class SuperSmartParkingBoyTest {
    @Test
    public void should_park_in_larger_available_positions_parkingLot_when_have_multiple_parkingLot() throws ParkingLotNotPositionException, CarIsNullException, CarHasBeenPartedException {
        //given
        Car firstCar = new Car();
        Car secondCar = new Car();
        Car thirdCar = new Car();

        ParkingLot[] parkingLots = {new ParkingLot(5), new ParkingLot()};
        SuperSmartParkingBoy parkingBoy = new SuperSmartParkingBoy(parkingLots);

        //when
        parkingBoy.park(firstCar);
        parkingBoy.park(secondCar);
        parkingBoy.park(thirdCar);

        //then
        assertSame(4, parkingLots[0].getCapacity());
        assertSame(8, parkingLots[1].getCapacity());
    }
}


