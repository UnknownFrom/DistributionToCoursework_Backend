package ru.itdt.mobile.sample.order.domain;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

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
    @ToString.Exclude
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Preference> preferences;
    @ToString.Exclude
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "teacher_id_fk"), name = "teacher_id")
    private Teacher teacher;
}
