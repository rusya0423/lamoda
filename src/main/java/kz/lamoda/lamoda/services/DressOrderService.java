package kz.lamoda.lamoda.services;

import kz.lamoda.lamoda.models.DressOrders;

import java.util.List;

public interface DressOrderService {
    DressOrders save(DressOrders dressOrders);
    List<DressOrders> findAll();
}
