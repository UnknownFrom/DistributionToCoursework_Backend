package ru.vyatsu.pavel.distribution.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import ru.vyatsu.pavel.distribution.domain.Preference;
import ru.vyatsu.pavel.distribution.exception.SaveException;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@ApplicationScoped
public class PreferenceRepository implements PanacheRepository<Preference> {

    @Transactional
    public Preference save(Preference preference) {
        if (preference == null) {
            throw new SaveException("Передан null вместо сущности");
        }
        persist(preference);
        return preference;
    }
}
