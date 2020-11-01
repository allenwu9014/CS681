package edu.umb.cs681.hw02;


import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class ComparatorTest {
    Car[] Arraycar = { new Car("Mercedes", "CLA250", 51000, 2016, 19998.0f),
            new Car("Mercedes", "E300", 21000, 2018, 12000.0f),
            new Car("Mercedes", "C200", 68000, 2015, 15998.0f)};
    ArrayList<Car> cars = new ArrayList<Car>(Arrays.asList(Arraycar));
    @Test
    public void MileageCompareTest() {
        String[] expected = {"21000", "51000", "68000"};
        Collections.sort(cars, Comparator.comparing((Car car) -> car.getMileage()));
        String[] actual = {String.valueOf(cars.get(0).getMileage()),
                String.valueOf(cars.get(1).getMileage()),
                String.valueOf(cars.get(2).getMileage())};
        assertArrayEquals(expected, actual );

    }
     @Test
    public void PriceCompareTest() {
        String[] expected = {"12000.0", "15998.0", "19998.0"};
         Collections.sort(cars, Comparator.comparing(Car::getPrice));
        String[] actual = {String.valueOf(cars.get(0).getPrice()),
                String.valueOf(cars.get(1).getPrice()),
                String.valueOf(cars.get(2).getPrice())};
        assertArrayEquals(expected, actual );
    }
    @Test
    public void YearCompareTest() {
        String[] expected = {"2018", "2016", "2015"};
        Collections.sort(cars, (Car car1, Car car2) -> car2.getYear() - car1.getYear());
        String[] actual = {String.valueOf(cars.get(0).getYear()),
                String.valueOf(cars.get(1).getYear()),
                String.valueOf(cars.get(2).getYear())};
        assertArrayEquals(expected, actual );
    }
    @Test
    public void ParetoCompareTest() {
        ArrayList<Car> cars2 = new ArrayList<Car>(Arrays.asList(Arraycar));
        for(int i = 0; i< cars.size(); i++) {
            cars.get(i).setDominationCount(cars2);
        }
        String[] expected = {"E300", "CLA250", "C200"};
        Collections.sort(cars, Comparator.comparing((Car car) -> car.getDominationCount(), Comparator.reverseOrder()));
        String[] actual = {String.valueOf(cars.get(0).getModel()),
                String.valueOf(cars.get(1).getModel()),
                String.valueOf(cars.get(2).getModel())};
        assertArrayEquals(expected, actual );
    }


    @Test
    public void MileageStreamCompareTest() {
        String[] expected = {"68000", "51000","21000" };
        List<Car> carList = cars.stream()
                .sorted((Car car1, Car car2) -> car2.getMileage()- car1.getMileage())
                .collect(Collectors.toList());
        String[] actual = {String.valueOf(carList.get(0).getMileage()),
                String.valueOf(carList.get(1).getMileage()),
                String.valueOf(carList.get(2).getMileage())};
        assertArrayEquals(expected, actual);

    }
    @Test
    public void PriceStreamCompareTest() {
        String[] expected = {"12000.0", "15998.0", "19998.0"};
        List<Car> carList = cars.stream()
                .sorted(Comparator.comparing(Car::getPrice))
                .collect(Collectors.toList());
        String[] actual = {String.valueOf(carList.get(0).getPrice()),
                String.valueOf(carList.get(1).getPrice()),
                String.valueOf(carList.get(2).getPrice())};
        assertArrayEquals(expected, actual );
    }
    @Test
    public void YearStreamCompareTest() {
        String[] expected = {"2018", "2016", "2015"};
        List<Car> carList = cars.stream()
                .sorted((Car car1, Car car2) -> car2.getYear() - car1.getYear())
                .collect(Collectors.toList());
        String[] actual = {String.valueOf(carList.get(0).getYear()),
                String.valueOf(carList.get(1).getYear()),
                String.valueOf(carList.get(2).getYear())};
        assertArrayEquals(expected, actual );
    }

    @Test
    public void ThreeYearStreamCompareTest() {
        String[] expected = {"2018", "2016", "2015"};
        List<Car> carList = cars.stream()
                .sorted((Car car1, Car car2) -> car2.getYear() - car1.getYear())
                .collect(Collectors.toList());
        String[] actual = {String.valueOf(carList.get(0).getYear()),
                String.valueOf(carList.get(1).getYear()),
                String.valueOf(carList.get(2).getYear())};
        assertArrayEquals(expected, actual );
    }

    @Test
    public void ParetoStreamCompareTest() {
        ArrayList<Car> cars2 = new ArrayList<Car>(Arrays.asList(Arraycar));
        for(int i = 0; i< cars.size(); i++) {
            cars.get(i).setDominationCount(cars2);
        }
        String[] expected = {"E300", "CLA250", "C200"};
        List<Car> carList = cars.stream()
                .sorted(Comparator.comparing((Car car) -> car.getDominationCount(), Comparator.reverseOrder()))
                .collect(Collectors.toList());
        String[] actual = {String.valueOf(carList.get(0).getModel()),
                String.valueOf(carList.get(1).getModel()),
                String.valueOf(carList.get(2).getModel())};
        assertArrayEquals(expected, actual );
    }

    }
