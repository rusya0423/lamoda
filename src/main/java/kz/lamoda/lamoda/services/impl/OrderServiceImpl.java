package kz.lamoda.lamoda.services.impl;

import kz.lamoda.lamoda.models.Order;
import kz.lamoda.lamoda.repositories.OrderRepository;
import kz.lamoda.lamoda.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order save(Order order) {
        if(order.getId()==null){
            return orderRepository.save(order);
        }
        return null;
    }

    @Override
    public Order update(Order order) {
        if(order.getId()!=null){
           return orderRepository.save(order);
        }
        return null;
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAllByActiveIsTrue();
    }

    @Override
    public void delete(Long id) {
        Order order = findById(id);
        if(order!=null){
            order.setActive(false);
        }
        update(order);
    }

    @Override
    public Order findById(Long id) {
        return orderRepository.findByActiveIsTrueAndId(id).orElse(null);
    }
}
