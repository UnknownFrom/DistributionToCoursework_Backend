package ru.itdt.mobile.sample.order.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import ru.itdt.mobile.sample.order.domain.Coursework;
import ru.itdt.mobile.sample.order.domain.Preference;
import ru.itdt.mobile.sample.order.domain.Student;
import ru.itdt.mobile.sample.order.domain.Teacher;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class CourseworkRepository implements PanacheRepository<Coursework> {

    @Transactional
    public Coursework save(Coursework coursework) {
        if (coursework == null) {
            throw new IllegalArgumentException("Передан null вместо сущности");
        }
        persist(coursework);
        return coursework;
    }

    @Transactional
    public void updateCourseworkPreferences(long courseworkId, List<Preference> preferences) {
        Coursework coursework = findById(courseworkId);
        if (coursework == null) {
            throw new IllegalArgumentException(String.format("Курсовая с id=%d не существует", courseworkId));
        }
        List<Preference> preferenceList = coursework.getPreferences();
        preferenceList.addAll(preferences);
        coursework.setPreferences(preferences);
        persist(coursework);
    }
}
