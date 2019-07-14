package com.thoughtworks.tdd;

import com.thoughtworks.exception.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertSame;

public class ParkingBoyTest {
    @Test
    public void should_return_car_when_park_car_to_parking_lot_then_get_it_back() {
        //given
        Car car = new Car();

        ParkingLot parkingLot = new ParkingLot();
        ParkingLot[] parkingLots = {parkingLot};
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

        //when
        Ticket ticket = parkingBoy.park(car).getTicket();

        Car fetchedCar = parkingBoy.fetch(ticket).getCar();

        //then
        assertSame(car, fetchedCar);
    }

    @Test
    public void should_mutiple_cars_when_park_to_parking_lot_then_get_them_back() {
        //give
        Car firstCar = new Car();
        Car secondCar = new Car();

        ParkingLot parkingLot = new ParkingLot();
        ParkingLot[] parkingLots = {parkingLot};
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

        //when
        Ticket firstTicket = parkingBoy.park(firstCar).getTicket();
        Car fetchedFirstCar = parkingBoy.fetch(firstTicket).getCar();

        Ticket secondTicket = parkingBoy.park(secondCar).getTicket();
        Car fetchedSecondCar = parkingBoy.fetch(secondTicket).getCar();

        //then
        assertSame(firstCar, fetchedFirstCar);
        assertSame(secondCar, fetchedSecondCar);
    }

    @Test
    public void should_not_fetch_car_when_ticket_is_wrong() {
        //given
        Car car = new Car();

        ParkingLot parkingLot = new ParkingLot();
        ParkingLot[] parkingLots = {parkingLot};
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        Ticket wrongTicket = new Ticket();

        //when
        parkingBoy.park(car);
        FetchCarResult fetchCarByWrongTicket = parkingBoy.fetch(wrongTicket);
        FetchCarResult fetchCarByNull = parkingBoy.fetch(null);

        //then
        assertSame(null, fetchCarByWrongTicket.getCar());
        assertSame(null, fetchCarByWrongTicket.getCar());
    }

    @Test
    public void should_not_fetch_car_when_ticket_is_used() {
        //given
        Car car = new Car();

        ParkingLot parkingLot = new ParkingLot();
        ParkingLot[] parkingLots = {parkingLot};
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

        //when
        Ticket ticket = parkingBoy.park(car).getTicket();
        parkingBoy.fetch(ticket);
        FetchCarResult fetchCarResult = parkingBoy.fetch(ticket);

        //then
        assertSame(null, fetchCarResult.getCar());
    }

    @Test
    public void should_not_return_ticket_when_parkingLot_is_not_position() {
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
        Ticket ticket = parkingBoy.park(cars.get(cars.size() - 1)).getTicket();

        //then
        assertSame(null, ticket);
    }

    @Test
    public void should_not_park_car_when_car_is_parked() {
        //given
        Car car = new Car();

        ParkingLot parkingLot = new ParkingLot();
        ParkingLot[] parkingLots = {parkingLot};
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

        //when
        parkingBoy.park(car);
        Ticket ticket = parkingBoy.park(car).getTicket();

        //then
        assertSame(null, ticket);
    }

    @Test
    public void should_not_park_car_when_car_is_null() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        ParkingLot[] parkingLots = {parkingLot};
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

        //when
        Ticket ticket = parkingBoy.park(null).getTicket();
        //then
        assertSame(null, ticket);
    }

    @Test
    public void should_get_error_message_when_ticket_is_wrong() {
        //given
        Car car = new Car();

        ParkingLot parkingLot = new ParkingLot();
        ParkingLot[] parkingLots = {parkingLot};
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
//        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Ticket wrongTicket = new Ticket();

        //when
        Ticket ticket = parkingBoy.park(car).getTicket();
        FetchCarResult fetchCarByWrongTicket = parkingBoy.fetch(wrongTicket);
        FetchCarResult fetchCarResult = parkingBoy.fetch(ticket);
        FetchCarResult fetchCarByUsedTicket = parkingBoy.fetch(ticket);
        //then
        assertSame("Unrecognized parking ticket.", fetchCarByWrongTicket.getMessage());
        assertSame(null, fetchCarResult.getMessage());
        assertSame("Unrecognized parking ticket.", fetchCarByUsedTicket.getMessage());
    }

    @Test
    public void should_get_error_message_when_ticket_is_null() {
        //given
        Car car = new Car();

        ParkingLot parkingLot = new ParkingLot();
        ParkingLot[] parkingLots = {parkingLot};
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

        //when
        FetchCarResult fetchCarByNull = parkingBoy.fetch(null);

        //then
        assertSame("Please provide your parking ticket.", fetchCarByNull.getMessage());
    }

    @Test
    public void should_get_not_enough_position_when_parkingLot_not_position() {
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
        String message = parkingBoy.park(cars.get(cars.size() - 1)).getMessage();

        //then
        assertSame("Not enough position.", message);
    }

    @Test
    public void should_park_in_second_parkingLot_when_first_parkingLot_not_position() {
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

    @Test
    public void should_park_in_more_positions_parkingLot_when_have_multiple_parkingLot() {
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

    @Test
    public void should_park_in_larger_available_positions_parkingLot_when_have_multiple_parkingLot() {
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
    public void should_bark_boy_park_or_fetch_car_from_parking_lots_when_parking_manager_specify_parking_boy() {
        //given
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        ParkingLot thirdParkingLot = new ParkingLot();

        ParkingLot[] parkingManagerParkingLots = {firstParkingLot, secondParkingLot};
        ParkingManager parkingManager = new ParkingManager(parkingManagerParkingLots);

        ParkingLot[] parkingBoyFirstParkingLots = {firstParkingLot};
        ParkingLot[] parkingBoySecondParkingLots = {thirdParkingLot};

        ParkingBoy parkingFirstBoy = new ParkingBoy(parkingBoyFirstParkingLots);
        ParkingBoy parkingSecondBoy = new ParkingBoy(parkingBoySecondParkingLots);
        Car firstCar = new Car();

        //when
        parkingManager.addParkingBoy(parkingFirstBoy);
        parkingManager.addParkingBoy(parkingSecondBoy);
        ParkingBoy firstParkingBoy = parkingManager.getParkingBoy(0);
        ParkingBoy secondParkingBoy = parkingManager.getParkingBoy(1);
        Ticket firstTicket = parkingManager.park(firstCar).getTicket();
        Ticket secondTicket = parkingManager.park(firstCar).getTicket();
        Car fetchedFirstCar = firstParkingBoy.fetch(firstTicket).getCar();
        Car fetchedSecondCar = secondParkingBoy.fetch(secondTicket).getCar();

        //then
        assertSame(firstCar, fetchedFirstCar);
        assertSame(null, fetchedSecondCar);
    }

    @Test
    public void should_bark_manager_park_or_fetch_car_when_parking_manager_park_and_fetch() {
        //given
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        ParkingLot[] parkingManagerParkingLots = {firstParkingLot, secondParkingLot};

        ParkingManager parkingManager = new ParkingManager(parkingManagerParkingLots);
        Car car = new Car();

        //when
        Ticket ticket = parkingManager.park(car).getTicket();
        Car fetchedCar = parkingManager.fetch(ticket).getCar();

        //then
        assertSame(car, fetchedCar);
    }

    @Test
    public void should_show_error_message_when_parking_boy_not_fetch_and_park() {
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
        ParkingBoy secondParkingBoy = parkingManager.getParkingBoy(1);
        Ticket firstTicket = parkingManager.park(firstCar).getTicket();
        Ticket secondTicket = parkingManager.park(firstCar).getTicket();
        String fetchedFirstCarMessage = firstParkingBoy.fetch(firstTicket).getMessage();
        String fetchedSecondCarMessage = secondParkingBoy.fetch(secondTicket).getMessage();

        //then
        assertSame(null, fetchedFirstCarMessage);
        assertSame("Please provide your parking ticket.", fetchedSecondCarMessage);
    }

}


