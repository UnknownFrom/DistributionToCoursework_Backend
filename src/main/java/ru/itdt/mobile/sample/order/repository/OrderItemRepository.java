package ru.itdt.mobile.sample.order.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import ru.itdt.mobile.sample.order.domain.Coursework;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class OrderItemRepository implements PanacheRepository<Coursework> {

    public List<Coursework> findAllOrderItemsByShopOrderId(long orderId) {
        return list("shop_order_id=?1", orderId);
    }

    public Optional<Coursework> findOrderItemByShopOrderId(long orderId, long productId) {
        return find("shop_order_id=?1 and id=?2", orderId, productId).firstResultOptional();
    }
}
