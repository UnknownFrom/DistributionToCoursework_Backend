package ru.itdt.mobile.sample.order.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import ru.itdt.mobile.sample.order.domain.Preference;
import ru.itdt.mobile.sample.order.domain.Student;
import ru.itdt.mobile.sample.order.domain.Teacher;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class PreferenceRepository implements PanacheRepository<Preference> {

    @Transactional
    public Preference save(Preference preference) {
        if (preference == null) {
            throw new IllegalArgumentException("Передан null вместо сущности");
        }
        persist(preference);
        return preference;
    }
}
