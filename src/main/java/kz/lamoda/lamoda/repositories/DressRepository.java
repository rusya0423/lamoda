package kz.lamoda.lamoda.repositories;

import kz.lamoda.lamoda.models.Dress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DressRepository extends JpaRepository<Dress, Long> {
    List<Dress> findAllByActiveIsTrue();
    Optional<Dress> findByActiveIsTrueAndId(Long id);
}
