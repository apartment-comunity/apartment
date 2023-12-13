package com.apartment.apart;

import com.apartment.apart.schedule.Schedule;
import com.apartment.apart.schedule.ScheduleRepository;
import com.apartment.apart.user.SiteUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
		siteUser1.setApartment("201동");

		Schedule schedule = new Schedule();
		schedule.setContent("테스트1");
		schedule.setUser(null);
		schedule.setTargetDong(siteUser1.getApartment());
		schedule.setStartDate(LocalDate.parse("2023-12-13"));
		schedule.setEndDate(LocalDate.parse("2023-12-15"));

		this.scheduleRepository.save(schedule);
	}

}
