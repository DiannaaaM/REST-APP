package ru.hogwarts.school.REST_APP.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InfoService {

    public int calculateSum(List<Integer> numbers) {
        return numbers.stream()
                .reduce(0, Integer::sum);
    }
}
