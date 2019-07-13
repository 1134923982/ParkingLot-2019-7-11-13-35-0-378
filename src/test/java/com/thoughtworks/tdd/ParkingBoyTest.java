package com.thoughtworks.tdd;

import com.thoughtworks.exception.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertSame;

public class ParkingBoyTest {
    @Test
    public void should_return_car_when_park_car_to_parking_lot_then_get_it_back() throws Exception {
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
    public void should_mutiple_cars_when_park_to_parking_lot_then_get_them_back() throws Exception {
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
    public void should_not_fetch_car_when_ticket_is_wrong() throws ParkingLotNotPositionException, CarHasBeenPartedException, CarIsNullException {
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
        assertSame(null,fetchCarByWrongTicket.getCar());
        assertSame(null,fetchCarByWrongTicket.getCar());
    }

    @Test
    public void should_not_fetch_car_when_ticket_is_used() throws Exception {
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
        assertSame(null,fetchCarResult.getCar());
    }

    @Test
    public void should_not_return_ticket_when_parkingLot_is_not_position() throws Exception {
        //given
        Car firstCar = new Car();
        Car secondCar = new Car();
        Car thirdCar = new Car();
        Car fourthCar = new Car();
        Car fifthCar = new Car();
        Car sixthCar = new Car();
        Car seventhCar = new Car();
        Car eighthCar = new Car();
        Car ninthCar = new Car();
        Car tenthCar = new Car();
        Car elevenCar = new Car();

        ParkingLot parkingLot = new ParkingLot();
        ParkingLot[] parkingLots = {parkingLot};
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

        //when
        parkingBoy.park(firstCar);
        parkingBoy.park(secondCar);
        parkingBoy.park(thirdCar);
        parkingBoy.park(fourthCar);
        parkingBoy.park(fifthCar);
        parkingBoy.park(sixthCar);
        parkingBoy.park(seventhCar);
        parkingBoy.park(eighthCar);
        parkingBoy.park(ninthCar);
        parkingBoy.park(tenthCar);

        //then
        assertSame(null,parkingBoy.park(elevenCar).getTicket());
    }

    @Test
    public void should_not_park_car_when_car_is_parked() throws Exception {
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
    public void should_not_park_car_when_car_is_null() throws Exception {
        //given
        ParkingLot parkingLot = new ParkingLot();
        ParkingLot[] parkingLots = {parkingLot};
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

        //when
        Ticket ticket = parkingBoy.park(null).getTicket();
        //then
        assertSame(null,ticket);
    }

    @Test
    public void should_get_error_message_when_ticket_is_wrong() throws CarHasBeenPartedException, ParkingLotNotPositionException, CarIsNullException {
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
        assertSame("Unrecognized parking ticket.",fetchCarByWrongTicket.getMessage());
        assertSame(null,fetchCarResult.getMessage());
        assertSame("Unrecognized parking ticket.",fetchCarByUsedTicket.getMessage());
    }

    @Test
    public void should_get_error_message_when_ticket_is_null() throws CarHasBeenPartedException, ParkingLotNotPositionException, CarIsNullException {
        //given
        Car car = new Car();

        ParkingLot parkingLot = new ParkingLot();
        ParkingLot[] parkingLots = {parkingLot};
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

        //when
        FetchCarResult fetchCarByNull = parkingBoy.fetch(null);

        //then
        assertSame("Please provide your parking ticket.",fetchCarByNull.getMessage());
    }

    @Test
    public void should_get_not_enough_position_when_parkingLot_not_position(){
        //given
        Car firstCar = new Car();
        Car secondCar = new Car();
        Car thirdCar = new Car();
        Car fourthCar = new Car();
        Car fifthCar = new Car();
        Car sixthCar = new Car();
        Car seventhCar = new Car();
        Car eighthCar = new Car();
        Car ninthCar = new Car();
        Car tenthCar = new Car();
        Car elevenCar = new Car();

        ParkingLot[] parkingLots ={new ParkingLot()};
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

        //when
        parkingBoy.park(firstCar);
        parkingBoy.park(secondCar);
        parkingBoy.park(thirdCar);
        parkingBoy.park(fourthCar);
        parkingBoy.park(fifthCar);
        parkingBoy.park(sixthCar);
        parkingBoy.park(seventhCar);
        parkingBoy.park(eighthCar);
        parkingBoy.park(ninthCar);
        parkingBoy.park(tenthCar);

        //then
        assertSame("Not enough position.",parkingBoy.park(elevenCar).getMessage());
    }

    @Test
    public void should_park_in_second_parkingLot_when_first_parkingLot_not_position(){
        //given
        Car firstCar = new Car();
        Car secondCar = new Car();
        Car thirdCar = new Car();
        Car fourthCar = new Car();
        Car fifthCar = new Car();
        Car sixthCar = new Car();
        Car seventhCar = new Car();
        Car eighthCar = new Car();
        Car ninthCar = new Car();
        Car tenthCar = new Car();
        Car elevenCar = new Car();

        ParkingLot[] parkingLots ={new ParkingLot(),new ParkingLot()};
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

        //when
        parkingBoy.park(firstCar);
        parkingBoy.park(secondCar);
        parkingBoy.park(thirdCar);
        parkingBoy.park(fourthCar);
        parkingBoy.park(fifthCar);
        parkingBoy.park(sixthCar);
        parkingBoy.park(seventhCar);
        parkingBoy.park(eighthCar);
        parkingBoy.park(ninthCar);
        parkingBoy.park(tenthCar);
        ParkCarResult parkCarResult = parkingBoy.park(elevenCar);

        //then
        assertSame(0, parkCarResult.getParkingLots()[0].getCapacity());
        assertSame(9, parkCarResult.getParkingLots()[1].getCapacity());
    }

    @Test
    public void should_park_in_more_positions_parkingLot_when_have_multiple_parkingLot(){
        //given
        Car firstCar = new Car();
        Car secondCar = new Car();

        ParkingLot[] parkingLots ={new ParkingLot(),new ParkingLot()};
        SmartParkingBoy parkingBoy = new SmartParkingBoy(parkingLots);

        //when
        parkingBoy.park(firstCar);
        parkingBoy.park(secondCar);

        //then
        assertSame(9, parkingLots[0].getCapacity());
        assertSame(9, parkingLots[1].getCapacity());
    }

    @Test
    public void should_park_in_larger_available_positions_parkingLot_when_have_multiple_parkingLot(){
        //given
        Car firstCar = new Car();
        Car secondCar = new Car();
        Car thirdCar = new Car();

        ParkingLot[] parkingLots ={new ParkingLot(),new ParkingLot()};
        parkingLots[0].setCapacity(5);
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
    public void should_add_parking_boy_to_list_when_add_parking_boy(){
        //given
        ParkingLot[] parkingLots ={new ParkingLot(),new ParkingLot()};
        ParkingManager parkingManager = new ParkingManager(parkingLots);

        ParkingBoy parkingBoy = new ParkingBoy();

        //when
        parkingManager.addParkingBoy(parkingBoy);
        ArrayList<ParkingBoy> actualParkingBoys = parkingManager.getParkingBoys();

        //then
        assertSame(true, actualParkingBoys.contains(parkingBoy));
    }

    @Test
    public void should_bark_boy_park_or_fetch_car_from_parking_lots_when_parking_manager_specify_parking_boy(){
        //given
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        ParkingLot thirdParkingLot = new ParkingLot();
        ParkingLot[] parkingManagerParkingLots ={firstParkingLot,secondParkingLot};
        ParkingManager parkingManager = new ParkingManager(parkingManagerParkingLots);
        ParkingLot[] parkingBoyFirstParkingLots ={firstParkingLot};
        ParkingLot[] parkingBoySecondParkingLots ={thirdParkingLot};
        ParkingBoy parkingFirstBoy = new ParkingBoy(parkingBoyFirstParkingLots);
        ParkingBoy parkingSecondBoy = new ParkingBoy(parkingBoySecondParkingLots);
        Car firstCar = new Car();
        Car secondCar = new Car();

        //when
        parkingManager.addParkingBoy(parkingFirstBoy);
        parkingManager.addParkingBoy(parkingSecondBoy);
        ArrayList<ParkingBoy> parkingBoys = parkingManager.getParkingBoys();
        Ticket firstTicket = parkingManager.park(firstCar).getTicket();
        Ticket secondTicket = parkingManager.park(firstCar).getTicket();
        Car fetchedFirstCar = parkingBoys.get(parkingBoys.size()-2).fetch(firstTicket).getCar();
        Car fetchedSecondCar = parkingBoys.get(parkingBoys.size()-1).fetch(secondTicket).getCar();

        //then
        assertSame(firstCar, fetchedFirstCar);
        assertSame(null, fetchedSecondCar);
    }

}


