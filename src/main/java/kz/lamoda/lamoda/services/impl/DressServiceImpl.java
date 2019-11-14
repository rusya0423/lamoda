package kz.lamoda.lamoda.services.impl;

import kz.lamoda.lamoda.models.Category;
import kz.lamoda.lamoda.models.Dress;
import kz.lamoda.lamoda.repositories.DressRepository;
import kz.lamoda.lamoda.services.CategoryService;
import kz.lamoda.lamoda.services.DressService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
@AllArgsConstructor
@Transactional
public class DressServiceImpl implements DressService {
    private DressRepository dressRepository;
    private CategoryService categoryService;

    @Override
    public Dress save(Dress dress) {
        if (dress.getId() == null) {
            dressRepository.save(dress);
        }
        return null;
    }

    @Override
    public Dress update(Dress dress) {
        if (dress.getId() != null) {
            return dressRepository.save(dress);
        }
        return null;
    }

    @Override
    public List<Dress> findAll() {

        return dressRepository.findAllByActiveIsTrue();
    }

    @Override
    public Dress findById(Long id) {

        return dressRepository.findByActiveIsTrueAndId(id).orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        Dress dress = findById(id);
        if (dress != null) {
            dress.setActive(false);
        }
        update(dress);
    }

}
