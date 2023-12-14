package com.apartment.apart.domain.schedule;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ScheduleForm {
    private String title;
    private String start;
    private String end;
}
