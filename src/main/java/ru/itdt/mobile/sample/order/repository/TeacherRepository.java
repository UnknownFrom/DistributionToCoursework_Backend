package ru.itdt.mobile.sample.order.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import ru.itdt.mobile.sample.order.domain.Student;
import ru.itdt.mobile.sample.order.domain.Teacher;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class TeacherRepository implements PanacheRepository<Teacher> {

    @Transactional
    public Teacher save(Teacher teacher) {
        if (teacher == null) {
            throw new IllegalArgumentException("Передан null вместо сущности");
        }
        persist(teacher);
        return teacher;
    }

    /*@Transactional
    public void updateTeacher(Student student, long teacherId) {
        Teacher teacher = findById(teacherId);
        if (teacher == null) {
            throw new IllegalArgumentException(String.format("Учителя с id=%d не существует", teacherId));
        }
        teacher.setStudent(student);
        persist(teacher);
    }*/

    public List<Teacher> findAllUserOrders(long userId) {
        return list("user_id = ?1", userId);
    }
}
