package kz.lamoda.lamoda.services;

import kz.lamoda.lamoda.models.Category;
import kz.lamoda.lamoda.models.Dress;

import java.util.List;

public interface DressService {
    Dress save(Dress dress);
    Dress update(Dress dress);
    List<Dress> findAll();
    Dress findById(Long id);
    void deleteById(Long id);
}
