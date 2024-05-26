package com.jlear;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import com.jlear.User.User;

public class AppTest {

    @Test
    public void testCalculateAge() {
        int result = User.calculateAge(LocalDate.of(2000, 9, 13));
        
    }

    @Test
    public void testSomething() {
        //given

        //when
        //then
    }
}