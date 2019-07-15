package com.thoughtworks.tdd;

import com.thoughtworks.exception.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;

public class ParkingManagerTest {
    @Test
    public void should_add_parking_boy_to_list_when_add_parking_boy() {
        //given
        ParkingLot[] parkingLots = {new ParkingLot(), new ParkingLot()};
        ParkingManager parkingManager = new ParkingManager(parkingLots);

        ParkingBoy parkingBoy = new ParkingBoy();

        //when
        parkingManager.addParkingBoy(parkingBoy);
        ParkingBoy actualParkingBoy = parkingManager.getParkingBoy(0);

        //then
        assertSame(parkingBoy, actualParkingBoy);
    }

    @Test
    public void should_bark_boy_park_or_fetch_car_from_parking_lots_when_parking_manager_specify_parking_boy() throws CarIsNullException, ParkingLotNotPositionException, CarHasBeenPartedException, WrongTicketException, TicketIsUsedException {
        //given
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        ParkingLot thirdParkingLot = new ParkingLot();

        ParkingLot[] parkingManagerParkingLots = {firstParkingLot, secondParkingLot};
        ParkingManager parkingManager = new ParkingManager(parkingManagerParkingLots);

        ParkingLot[] parkingBoyFirstParkingLots = {firstParkingLot};

        ParkingBoy parkingFirstBoy = new ParkingBoy(parkingBoyFirstParkingLots);
        Car firstCar = new Car();

        //when
        parkingManager.addParkingBoy(parkingFirstBoy);
        ParkingBoy firstParkingBoy = parkingManager.getParkingBoy(0);
        Ticket firstTicket = firstParkingBoy.park(firstCar);
        Car fetchedFirstCar = firstParkingBoy.fetch(firstTicket);

        //then
        assertSame(firstCar, fetchedFirstCar);
    }

    @Test
    public void should_bark_manager_park_or_fetch_car_when_parking_manager_park_and_fetch() throws CarIsNullException, ParkingLotNotPositionException, CarHasBeenPartedException, WrongTicketException, TicketIsUsedException {
        //given
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        ParkingLot[] parkingManagerParkingLots = {firstParkingLot, secondParkingLot};

        ParkingManager parkingManager = new ParkingManager(parkingManagerParkingLots);
        Car car = new Car();

        //when
        Ticket ticket = parkingManager.park(car);
        Car fetchedCar = parkingManager.fetch(ticket);

        //then
        assertSame(car, fetchedCar);
    }

    @Test
    public void should_show_error_message_when_parking_boy_not_fetch_and_park() throws CarIsNullException, ParkingLotNotPositionException, CarHasBeenPartedException, WrongTicketException, TicketIsUsedException {
        //given
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        ParkingLot thirdParkingLot = new ParkingLot();
        ParkingLot[] parkingManagerParkingLots = {firstParkingLot, secondParkingLot};
        ParkingLot[] parkingBoyFirstParkingLots = {firstParkingLot};
        ParkingLot[] parkingBoySecondParkingLots = {thirdParkingLot};

        ParkingManager parkingManager = new ParkingManager(parkingManagerParkingLots);
        ParkingBoy parkingFirstBoy = new ParkingBoy(parkingBoyFirstParkingLots);
        ParkingBoy parkingSecondBoy = new ParkingBoy(parkingBoySecondParkingLots);
        Car firstCar = new Car();

        //when
        parkingManager.addParkingBoy(parkingFirstBoy);
        parkingManager.addParkingBoy(parkingSecondBoy);
        ParkingBoy firstParkingBoy = parkingManager.getParkingBoy(0);
        parkingManager.park(firstCar);

        Exception wrongTicketException = Assertions.assertThrows(WrongTicketException.class,()->firstParkingBoy.fetch(null));

        //then
        assertSame("Please provide your parking ticket.", wrongTicketException.getMessage());
    }

}


