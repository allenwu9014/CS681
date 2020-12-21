package edu.umb.cs681.hw19;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;


public class CarTest {
    Car[] Arraycar = { new Car("Mercedes", "CLA250", 51000, 2016, 20000.0f),
            new Car("Mercedes", "E300", 21000, 2018, 12000.0f),
            new Car("Mercedes", "C200", 68000, 2015, 40000.0f)};
    ArrayList<Car> cars = new ArrayList<Car>(Arrays.asList(Arraycar));
    @Test
    public void minTest() {
        String expected = "12000.0";
        float price = cars.stream()
                .parallel()
                .map((Car car) -> car.getPrice())
                .reduce((float) 0, (result, carPrice) -> {
                    if (result == 0) return carPrice;
                    else if (carPrice < result) return carPrice;
                    else return result;
                },(finalResult, interMediateResult)->

                     (finalResult < interMediateResult)? finalResult:interMediateResult
                );
        String actual = String.valueOf(price);
         assertEquals(expected, actual);
    }
    @Test
    public void maxTest() {
        String expected = "40000.0";
        float price = cars.stream()
                .parallel()
                .map((Car car) -> car.getPrice())
                .reduce(0.0f, (result, carPrice) -> {
                    if (result == 0) return carPrice;
                    else if (carPrice > result) return carPrice;
                    else return result;
                },
                        (finalResult, interMediateResult)->
                                (finalResult > interMediateResult)? finalResult:interMediateResult
                           );
        String actual = String.valueOf(price);
        assertEquals(expected, actual);
    }

    @Test
    public void countTest() {
        String expected = "3";
        Integer num = cars.stream()
                .parallel()
                .map((Car car) -> car.getModel())
                .reduce(0, (result, car) -> {
                            if (car != null)
                                ++result;
                            return result;
                        },
                        (finalResult, intermediateResult) ->  finalResult + intermediateResult
                );
        String actual = String.valueOf(num);
        assertEquals(expected, actual);
    }


}
