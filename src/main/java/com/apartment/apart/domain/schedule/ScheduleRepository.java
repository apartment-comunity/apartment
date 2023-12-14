package com.apartment.apart.domain.schedule;

import com.apartment.apart.user.SiteUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule,Long> {
    List<Schedule> findByUser(SiteUser siteUser);

    List<Schedule> findByTargetDong(int targetDong);
}
