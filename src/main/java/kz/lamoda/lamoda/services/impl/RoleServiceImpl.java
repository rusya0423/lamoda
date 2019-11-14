package kz.lamoda.lamoda.services.impl;

import kz.lamoda.lamoda.models.Role;
import kz.lamoda.lamoda.repositories.RoleRepository;
import kz.lamoda.lamoda.services.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {
    private RoleRepository roleRepository;

    @Override
    public Role findById(Long id) {
        return roleRepository.findById(id).orElse(null);
    }
}
