package ru.itdt.mobile.sample.order.domain;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "preference")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Preference {
    @Id
    @SequenceGenerator(name = "preference_id_seq", sequenceName = "preference_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "preference_id_seq")
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "coursework_id_fk"), name = "coursework_id")
    private Coursework coursework;
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "student_id_fk"), name = "student_id")
    private Student student;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Preference that = (Preference) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
