package com.thoughtworks.tdd;

import com.thoughtworks.exception.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertSame;

public class ParkingBoyTest {
    @Test
    public void should_return_car_when_park_car_to_parking_lot_then_get_it_back() throws CarIsNullException, ParkingLotNotPositionException, CarHasBeenPartedException, WrongTicketException, TicketIsUsedException {
        //given
        Car car = new Car();

        ParkingLot parkingLot = new ParkingLot();
        ParkingLot[] parkingLots = {parkingLot};
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

        //when
        Ticket ticket = parkingBoy.park(car);

        Car fetchedCar = parkingBoy.fetch(ticket);

        //then
        assertSame(car, fetchedCar);
    }

    @Test
    public void should_mutiple_cars_when_park_to_parking_lot_then_get_them_back() throws CarIsNullException, ParkingLotNotPositionException, CarHasBeenPartedException, WrongTicketException, TicketIsUsedException {
        //give
        Car firstCar = new Car();
        Car secondCar = new Car();

        ParkingLot parkingLot = new ParkingLot();
        ParkingLot[] parkingLots = {parkingLot};
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

        //when
        Ticket firstTicket = parkingBoy.park(firstCar);
        Car fetchedFirstCar = parkingBoy.fetch(firstTicket);

        Ticket secondTicket = parkingBoy.park(secondCar);
        Car fetchedSecondCar = parkingBoy.fetch(secondTicket);

        //then
        assertSame(firstCar, fetchedFirstCar);
        assertSame(secondCar, fetchedSecondCar);
    }

    @Test
    public void should_not_fetch_car_when_ticket_is_wrong() throws CarIsNullException, ParkingLotNotPositionException, CarHasBeenPartedException, WrongTicketException, TicketIsUsedException {
        //given
        Car car = new Car();

        ParkingLot parkingLot = new ParkingLot();
        ParkingLot[] parkingLots = {parkingLot};
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        Ticket wrongTicket = new Ticket();

        //when
        parkingBoy.park(car);

        //then
        Assertions.assertThrows(WrongTicketException.class, ()->parkingBoy.fetch(wrongTicket));
        Assertions.assertThrows(WrongTicketException.class, ()->parkingBoy.fetch(null));
    }

    @Test
    public void should_not_fetch_car_when_ticket_is_used() throws CarIsNullException, ParkingLotNotPositionException, CarHasBeenPartedException, WrongTicketException, TicketIsUsedException {
        //given
        Car car = new Car();

        ParkingLot parkingLot = new ParkingLot();
        ParkingLot[] parkingLots = {parkingLot};
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

        //when
        Ticket ticket = parkingBoy.park(car);
        parkingBoy.fetch(ticket);

        //then
        Assertions.assertThrows(TicketIsUsedException.class, ()->parkingBoy.fetch(ticket));
    }

    @Test
    public void should_not_return_ticket_when_parkingLot_is_not_position() throws CarIsNullException, ParkingLotNotPositionException, CarHasBeenPartedException {
        //given
        ArrayList<Car> cars = new ArrayList<>();
        for (int i = 0; i < 11; i++) {
            cars.add(new Car());
        }

        ParkingLot[] parkingLots = {new ParkingLot()};
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

        //when
        for (int i = 0; i < cars.size() - 1; i++) {
            parkingBoy.park(cars.get(i));
        }

        //then
        Assertions.assertThrows(ParkingLotNotPositionException.class,()->parkingBoy.park(cars.get(cars.size() - 1)));
    }

    @Test
    public void should_not_park_car_when_car_is_parked() throws CarIsNullException, ParkingLotNotPositionException, CarHasBeenPartedException {
        //given
        Car car = new Car();

        ParkingLot parkingLot = new ParkingLot();
        ParkingLot[] parkingLots = {parkingLot};
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

        //when
        parkingBoy.park(car);
        //then
        Assertions.assertThrows(CarHasBeenPartedException.class,()->parkingBoy.park(car));
    }

    @Test
    public void should_not_park_car_when_car_is_null() throws CarIsNullException, ParkingLotNotPositionException, CarHasBeenPartedException {
        //given
        ParkingLot parkingLot = new ParkingLot();
        ParkingLot[] parkingLots = {parkingLot};
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

        //when
        //then
        Assertions.assertThrows(CarIsNullException.class,()->parkingBoy.park(null));
    }

    @Test
    public void should_get_error_message_when_ticket_is_wrong() throws CarIsNullException, ParkingLotNotPositionException, CarHasBeenPartedException, WrongTicketException, TicketIsUsedException {
        //given
        Car car = new Car();

        ParkingLot parkingLot = new ParkingLot();
        ParkingLot[] parkingLots = {parkingLot};
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        Ticket wrongTicket = new Ticket();

        //when
        Ticket ticket = parkingBoy.park(car);
        parkingBoy.fetch(ticket);
        //then
        Exception wrongTicketExceprion = Assertions.assertThrows(WrongTicketException.class,()->parkingBoy.fetch(wrongTicket));
        Exception usedTicketExceprion = Assertions.assertThrows(WrongTicketException.class,()->parkingBoy.fetch(wrongTicket));
        assertSame("Unrecognized parking ticket.", wrongTicketExceprion.getMessage());
        assertSame("Unrecognized parking ticket.", usedTicketExceprion.getMessage());
    }

    @Test
    public void should_get_error_message_when_ticket_is_null() throws WrongTicketException, TicketIsUsedException {
        //given
        Car car = new Car();

        ParkingLot parkingLot = new ParkingLot();
        ParkingLot[] parkingLots = {parkingLot};
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

        //when
        Exception wrongTicketExceprion = Assertions.assertThrows(WrongTicketException.class,()->parkingBoy.fetch(null));

        //then
        assertSame("Please provide your parking ticket.", wrongTicketExceprion.getMessage());
    }

    @Test
    public void should_get_not_enough_position_when_parkingLot_not_position() throws CarIsNullException, ParkingLotNotPositionException, CarHasBeenPartedException {
        //given
        ArrayList<Car> cars = new ArrayList<>();
        for (int i = 0; i < 11; i++) {
            cars.add(new Car());
        }

        ParkingLot[] parkingLots = {new ParkingLot()};
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

        //when
        for (int i = 0; i < cars.size() - 1; i++) {
            parkingBoy.park(cars.get(i));
        }

        Exception parkingLotNotPositionException = Assertions.assertThrows(ParkingLotNotPositionException.class,()->parkingBoy.park(cars.get(cars.size() - 1)));

        //then
        assertSame("Not enough position.", parkingLotNotPositionException.getMessage());
    }

    @Test
    public void should_park_in_second_parkingLot_when_first_parkingLot_not_position() throws CarIsNullException, ParkingLotNotPositionException, CarHasBeenPartedException {
        //given
        ArrayList<Car> cars = new ArrayList<>();
        for (int i = 0; i <= 10; i++) {
            cars.add(new Car());
        }

        ParkingLot[] parkingLots = {new ParkingLot(), new ParkingLot()};
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

        //when
        for (int i = 0; i < cars.size(); i++) {
            parkingBoy.park(cars.get(i));
        }

        //then
        assertSame(0, parkingLots[0].getCapacity());
        assertSame(9, parkingLots[1].getCapacity());
    }



}


