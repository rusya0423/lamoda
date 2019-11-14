package kz.lamoda.lamoda.services;

import kz.lamoda.lamoda.models.Client;
import org.hibernate.sql.Update;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ClientService {
    Client save(Client client);
    Client update(Client client);
    List<Client> findAll();
    Client findById(Long id);
    Client findByUserName(String username);
    Client register(String firstName,
                    String lastName,
                    String username,
                    String password,
                    String repassword);

}
