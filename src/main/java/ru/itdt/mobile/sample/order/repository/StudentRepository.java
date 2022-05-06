package ru.itdt.mobile.sample.order.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import ru.itdt.mobile.sample.order.domain.Preference;
import ru.itdt.mobile.sample.order.domain.Student;
import ru.itdt.mobile.sample.order.domain.Teacher;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class StudentRepository implements PanacheRepository<Student> {

    @Transactional
    public Student save(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Передан null вместо сущности");
        }
        persist(student);
        return student;
    }

    @Transactional
    public void updateStudentPreferredTeacher(long studentId, Teacher teacher) {
        Student student = findById(studentId);
        if (student == null) {
            throw new IllegalArgumentException(String.format("Студент с id=%d не существует", studentId));
        }
        student.setTeacher(teacher);
        persist(student);
    }

    @Transactional
    public void updateStudentPreferences(long studentId, List<Preference> preferences) {
        Student student = findById(studentId);
        if (student == null) {
            throw new IllegalArgumentException(String.format("Студент с id=%d не существует", studentId));
        }
        List<Preference> preferenceList = student.getPreferences();
        preferenceList.addAll(preferences);
        student.setPreferences(preferences);
        persist(student);
    }

    public List<Student> findAllUserOrders(long userId) {
        return list("user_id = ?1", userId);
    }
}
