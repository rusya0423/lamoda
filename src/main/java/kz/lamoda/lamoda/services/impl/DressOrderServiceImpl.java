package kz.lamoda.lamoda.services.impl;

import kz.lamoda.lamoda.models.DressOrders;
import kz.lamoda.lamoda.repositories.DressOrdersRepository;
import kz.lamoda.lamoda.services.DressOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DressOrderServiceImpl implements DressOrderService {
    private DressOrdersRepository dressOrdersRepository;
    @Autowired
    public DressOrderServiceImpl(DressOrdersRepository dressOrdersRepository) {
        this.dressOrdersRepository = dressOrdersRepository;
    }

    @Override
    public DressOrders save(DressOrders dressOrders) {
        if(dressOrders.getId()==null){
            return  dressOrdersRepository.save(dressOrders);
        }
        return null;
    }

    @Override
    public List<DressOrders> findAll() {
        return dressOrdersRepository.findAll();
    }
}
