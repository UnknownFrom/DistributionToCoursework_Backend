package ru.itdt.mobile.sample.order.bean.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itdt.mobile.sample.order.bean.PairStudentCoursework;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DistributionResponse {
    private List<PairStudentCoursework> distribution;
}
