package kz.lamoda.lamoda.services.impl;

import kz.lamoda.lamoda.models.Category;
import kz.lamoda.lamoda.repositories.CategoryRepository;
import kz.lamoda.lamoda.services.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository;
    @Override
    public Category findById(Long id) {
        return categoryRepository.findByActiveIsTrueAndId(id).orElse(null);
    }

    @Override
    public List<Category> findAll() {

        return categoryRepository.findAllByActiveIsTrue();
    }

    @Override
    public Category save(Category category) {
        if(category.getId()==null){
            return categoryRepository.save(category);
        }
        return null;
    }

    @Override
    public Category update(Category category) {
        if(category.getId()!=null){
            return categoryRepository.save(category);
        }
        return null;
    }

    @Override
    public void detete(Long id) {
        Category category = findById(id);
        if(category!=null){
            category.setActive(false);
            update(category);
        }
    }
}
