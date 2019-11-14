package kz.lamoda.lamoda.services;

import kz.lamoda.lamoda.models.Category;

import java.util.List;

public interface CategoryService {
    Category findById(Long id);
    List<Category> findAll();
    Category save(Category category);
    Category update(Category category);
    void detete(Long id);
}
