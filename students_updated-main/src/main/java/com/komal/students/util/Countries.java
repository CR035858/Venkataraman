package com.komal.students.util;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
@Component
public class Countries {
    public List<String> getCountries() {
        return  Arrays.asList("Mexico","Russia","USA", "Germany", "Canada", "India");
    }
}
