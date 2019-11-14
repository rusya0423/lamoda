package kz.lamoda.lamoda.repositories;

import kz.lamoda.lamoda.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByActiveIsTrue();
    Optional<Order> findByActiveIsTrueAndId(Long id);
}
