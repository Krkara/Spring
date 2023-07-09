package com.kristjan.calculator.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class CalculatorController {
    List<Integer> integers = new ArrayList<>(Arrays.asList(31, 52, 24));

    @GetMapping("numbers")
    public List<Integer> getNumbers() {
        return integers;
    }
    @GetMapping("add")
    public void addNumber(@RequestParam Integer number) {
        integers.add(number);
    }

    @GetMapping("delete")
    public void deleteNumber() {
        integers.remove(integers.size() - 1);
    }

    @GetMapping("deleteAll")
    public void deleteAllNumbers() {
        integers.clear();
    }

    @GetMapping("sum")
    public Integer sumOfNumbers() {
        Integer sum = 0;
        for (Integer i : integers) {
            sum += i;
        }
        return sum;
    }

    @GetMapping("average")
    public Integer averageOfNumbers() {
        Integer sum = 0;
        for (Integer i : integers) {
            sum += i;
        }
        return sum / integers.size();
    }

    @GetMapping("total")
    public Integer totalOfNumbers() {
        return integers.size();
    }

    @GetMapping("incrementByOne")
    public void incrementNumbersByOne() {
        for (int i = 0; i < integers.size(); i++) {
            integers.set(i, integers.get(i) + 1);
        }
    }

    @GetMapping("increment")
    public void incrementNumbers(@RequestParam Integer number) {
        for (int i = 0; i < integers.size(); i++) {
            integers.set(i, integers.get(i) + number);
        }
    }

    @GetMapping("divideByTen")
    public void divideByTen() {
        for (int i = 0; i < integers.size(); i++) {
            integers.set(i, integers.get(i) / 10);
        }
    }

    @GetMapping("divide")
    public void divideNumbers(@RequestParam Integer number) {
        for (int i = 0; i < integers.size(); i++) {
            integers.set(i, integers.get(i) / number);
        }
    }
}
