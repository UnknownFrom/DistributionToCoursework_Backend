package ru.vyatsu.pavel.distribution.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import ru.vyatsu.pavel.distribution.domain.Coursework;
import ru.vyatsu.pavel.distribution.domain.Preference;
import ru.vyatsu.pavel.distribution.exception.SaveException;
import ru.vyatsu.pavel.distribution.exception.UpdateException;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class CourseworkRepository implements PanacheRepository<Coursework> {

    @Transactional
    public Coursework save(Coursework coursework) {
        if (coursework == null) {
            throw new SaveException("Передан null вместо сущности");
        }
        persist(coursework);
        return coursework;
    }

    @Transactional
    public void updateCourseworkPreferences(long courseworkId, List<Preference> preferences) {
        Coursework coursework = findById(courseworkId);
        if (coursework == null) {
            throw new UpdateException(String.format("Курсовая с id=%d не существует", courseworkId));
        }
        List<Preference> preferenceList = coursework.getPreferences();
        preferenceList.addAll(preferences);
        coursework.setPreferences(preferences);
        persist(coursework);
    }

    public List<Coursework> getCourseworkList(List<Long> courseworkId) {
        return courseworkId
                .stream()
                .map(this::findById)
                .collect(Collectors.toList());
    }

    public List<Coursework> getCourseworkList(long teacherId) {
        return list("teacher_id = ?1", teacherId);
    }
}
