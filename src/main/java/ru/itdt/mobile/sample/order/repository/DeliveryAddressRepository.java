package ru.itdt.mobile.sample.order.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import ru.itdt.mobile.sample.order.domain.Teacher;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DeliveryAddressRepository implements PanacheRepository<Teacher> {
}
