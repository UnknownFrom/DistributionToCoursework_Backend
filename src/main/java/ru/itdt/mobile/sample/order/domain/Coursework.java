package ru.itdt.mobile.sample.order.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "coursework", uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})})
public class Coursework {
    @Id
    @SequenceGenerator(name = "coursework_id_seq", sequenceName = "coursework_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "coursework_id_seq")
    private Long id;
    @Column(name = "name")
    private String name;
    @ManyToMany
    @JoinTable(name = "coursework_preference",
            joinColumns = {@JoinColumn(name = "coursework_id")},
            inverseJoinColumns = {@JoinColumn(name = "preference_id")})
    private List<Preference> preferences;
    @ToString.Exclude
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "teacher_id_fk"), name = "teacher_id")
    private Teacher teacher;
}
