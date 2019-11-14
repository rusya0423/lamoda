package kz.lamoda.lamoda.repositories;

import kz.lamoda.lamoda.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    List<Client> findAllByActiveIsTrue();
    Optional<Client> findUserByActiveIsTrueAndUsername(String username);


}
