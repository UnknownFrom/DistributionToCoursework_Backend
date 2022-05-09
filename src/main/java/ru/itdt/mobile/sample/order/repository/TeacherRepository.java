package ru.itdt.mobile.sample.order.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import ru.itdt.mobile.sample.order.domain.Teacher;
import ru.itdt.mobile.sample.order.exception.AuthException;
import ru.itdt.mobile.sample.order.exception.SaveException;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.Optional;

@ApplicationScoped
public class TeacherRepository implements PanacheRepository<Teacher> {

    @Transactional
    public Teacher save(Teacher teacher) {
        if (teacher == null) {
            throw new SaveException("Передан null вместо сущности");
        }
        persist(teacher);
        return teacher;
    }

    public Teacher findByLoginAndPassword(String login, String password) {
        Optional<Teacher> result = find("login = ?1 and password = ?2", login, password).firstResultOptional();
        if (result.isEmpty()){
            throw new AuthException("Учителя с такими данными не существует");
        }
        return result.get();
    }
}
