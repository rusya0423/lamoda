package kz.lamoda.lamoda.repositories;

import kz.lamoda.lamoda.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    Optional<Image> findByDress_Id(Long id);
}
