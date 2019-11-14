package kz.lamoda.lamoda.services;

import kz.lamoda.lamoda.models.Order;
import kz.lamoda.lamoda.repositories.OrderRepository;
import lombok.AllArgsConstructor;

import java.util.List;


public interface OrderService{
    Order save(Order order);
    Order update(Order order);
    List<Order> findAll();
    void delete(Long id);
    Order findById(Long id);
}
