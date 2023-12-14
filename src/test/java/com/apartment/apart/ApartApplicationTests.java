package com.apartment.apart;

import com.apartment.apart.schedule.Schedule;
import com.apartment.apart.schedule.ScheduleRepository;
import com.apartment.apart.user.SiteUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
class ApartApplicationTests {


    @Autowired
    private ScheduleRepository scheduleRepository;

    @Test
    void contextLoads() {

        SiteUser siteUser1 = new SiteUser();
        siteUser1.setUsername("1234");
        siteUser1.setNickname("1234");
        siteUser1.setPassword("1234");
        siteUser1.setPhone("01012341234");
        siteUser1.setEmail("1234@1234.1234");
        siteUser1.setApartDong(102);

        Schedule schedule = new Schedule();
        schedule.setContent("테스트1");
        schedule.setUser(null);
        schedule.setTargetDong(siteUser1.getApartDong());
        schedule.setStartDate(LocalDate.parse("2023-12-18"));
        schedule.setEndDate(LocalDate.parse("2023-12-22"));

        this.scheduleRepository.save(schedule);
    }
}


