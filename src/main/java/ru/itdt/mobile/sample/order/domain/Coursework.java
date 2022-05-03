package ru.itdt.mobile.sample.order.domain;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "coursework")
public class Coursework {
    @Id
    @SequenceGenerator(name = "coursework_id_seq", sequenceName = "coursework_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "coursework_id_seq")
    private Long id;
    @Column(name = "name")
    private String name;
}
