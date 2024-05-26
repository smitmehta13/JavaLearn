package com.jlear.User;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.junit.jupiter.api.Test;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private TestRepo underTest;

    @Test
    void testFindByAge() {

        //given
        LocalDate dob = LocalDate.of(2011, 2, 2);
        int age = Period.between(dob,LocalDate.now()).getYears();
        User user = new User("","test","test","test",dob);

        underTest.save(user);

        //when
        List<User> exists = underTest.findByAge(age);

        //then
        assertThat(exists).isNotEmpty();


    }

    @Test
    public void testFindByUsername() {

    }
}
