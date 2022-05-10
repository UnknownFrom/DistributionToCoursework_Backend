package ru.itdt.mobile.sample.order.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PairStudentCoursework {
    private StudentShort student;
    private CourseworkShort coursework;
    private Integer score;
}
