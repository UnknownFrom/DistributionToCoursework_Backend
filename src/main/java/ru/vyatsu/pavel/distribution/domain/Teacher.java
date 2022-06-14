package ru.vyatsu.pavel.distribution.domain;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "teacher", uniqueConstraints = {@UniqueConstraint(columnNames = {"login"})})
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {
    @Id
    @SequenceGenerator(name = "teacher_id_seq", sequenceName = "teacher_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "teacher_id_seq")
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "login", nullable = false)
    private String login;
    @Column(name = "password", nullable = false)
    private String password;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Teacher that = (Teacher) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
