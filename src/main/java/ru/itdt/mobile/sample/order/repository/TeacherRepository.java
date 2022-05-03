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
    public void save(Teacher teacher) {
        if (teacher == null) {
            throw new IllegalArgumentException("Передан null вместо сущности");
        }
        persist(teacher);
    }

    public List<Teacher> findAllUserOrders(long userId) {
        return list("user_id = ?1", userId);
    }
}
