package kz.lamoda.lamoda.repositories;

import kz.lamoda.lamoda.models.DressOrders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DressOrdersRepository extends JpaRepository<DressOrders, Long> {
}
