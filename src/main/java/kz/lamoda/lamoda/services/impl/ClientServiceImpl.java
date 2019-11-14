package kz.lamoda.lamoda.services.impl;

import kz.lamoda.lamoda.models.Client;
import kz.lamoda.lamoda.models.Role;
import kz.lamoda.lamoda.repositories.ClientRepository;
import kz.lamoda.lamoda.services.ClientService;
import kz.lamoda.lamoda.services.RoleService;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService, UserDetailsService {
    private ClientRepository clientRepository;
    private RoleService roleService;
    private BCryptPasswordEncoder passwordEncoder;
//    private SessionFactory sessionFactory;


    @Override
    public Client save(Client client) {
        if(client.getId()==null){
            return clientRepository.save(client);
        }
        return null;
    }

    @Override
    public Client findByUserName(String username) {
        return clientRepository.findUserByActiveIsTrueAndUsername(username).orElse(null);
    }

    @Override
    public Client register(String firstName, String lastName, String username, String password, String repassword) {
        Client savedClient = null;

        if(password.equals(repassword) && this.findByUserName(username) == null){
            Role role = roleService.findById(2L);
            if(role == null){
                throw new RuntimeException("Role does not exist");
            }
                Client client = Client.builder()
                        .firstName(firstName)
                        .lastName(lastName)
                        .username(username)
                        .password(passwordEncoder.encode(password))
                        .role(role)
                        .build();
                savedClient = save(client);
        }
        return savedClient;
    }

    @Override
    public Client update(Client client) {
        if(client.getId()!=null){
            return clientRepository.save(client);
        }
        return null;
    }

    @Override
    public List<Client> findAll() {

        return clientRepository.findAllByActiveIsTrue();
    }

    @Override
    public Client findById(Long id) {

        return clientRepository.findById(id).orElse(null);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       Client client = findByUserName(username);
       if(client!=null){
           return new User(client.getUsername(), client.getPassword(), Collections.singletonList(client.getRole()));
       }
        return null;
    }

//    public Client findClient(String username){
//        Session session = this.sessionFactory.getCurrentSession();
//        return session.find(Client.class, username);
//    }


}
