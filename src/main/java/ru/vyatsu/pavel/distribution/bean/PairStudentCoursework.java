package ru.vyatsu.pavel.distribution.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.vyatsu.pavel.distribution.bean.response.StudentResponse;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PairStudentCoursework {
    private StudentResponse student;
    private CourseworkShort coursework;
    private Integer score;
}
