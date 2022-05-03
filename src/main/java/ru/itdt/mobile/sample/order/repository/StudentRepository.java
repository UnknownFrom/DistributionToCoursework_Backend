package ru.itdt.mobile.sample.order.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import ru.itdt.mobile.sample.order.domain.Student;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class StudentRepository implements PanacheRepository<Student> {

    @Transactional
    public void save(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Передан null вместо сущности");
        }
        persist(student);
    }

    public List<Student> findAllUserOrders(long userId) {
        return list("user_id = ?1", userId);
    }
}
