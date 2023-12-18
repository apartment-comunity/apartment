package com.apartment.apart.domain.schedule;

import com.apartment.apart.domain.user.SiteUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public List<Schedule> findAll() {
        return this.scheduleRepository.findAll();
    }

    public void save(ScheduleForm scheduleForm,SiteUser siteUser) {
        Schedule schedule =  Schedule.builder()
                .user(siteUser)
                .title(scheduleForm.getTitle())
                .targetDong(siteUser.getApartDong())
                .startDate(LocalDate.parse(scheduleForm.getStart()))
                .endDate(LocalDate.parse(scheduleForm.getEnd()))
                .build();

        this.scheduleRepository.save(schedule);
    }

    public List<Schedule> findByUser(SiteUser siteUser) {
        return this.scheduleRepository.findByUser(siteUser);
    }

    public Schedule getSchedule(Long id) {
        Optional<Schedule> os = this.scheduleRepository.findById(id);
        return os.get();
    }

    public void delete(Schedule schedule) {
        this.scheduleRepository.delete(schedule);
    }

    public void modify(Schedule schedule, String content, String start, String end) {
        Schedule modifySchedule = Schedule.builder()
                .id(schedule.getId())
                .user(schedule.getUser())
                .title(content)
                .startDate(LocalDate.parse(start))
                .endDate(LocalDate.parse(end))
                .targetDong(schedule.getTargetDong())
                .build();
        this.scheduleRepository.save(modifySchedule);
    }

    public List<Schedule> findByTargetDong(int targetDong) {
        return this.scheduleRepository.findByTargetDong(targetDong);
    }
}
