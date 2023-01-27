package com.employee.management.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.management.model.UserRole;
import com.employee.management.repository.IRoleRepository;
import com.employee.management.service.IRoleService;

import lombok.RequiredArgsConstructor;


@Service
public class RoleService implements IRoleService {

	@Autowired
    private IRoleRepository repo;

    @Override
    public UserRole saveRole(UserRole Role) {
        return repo.save(Role);
    }

    @Override
    public UserRole getRole(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public UserRole updateRole(UserRole Role) {
        return repo.save(Role);
    }

    @Override
    public void deleteRole(Long id) {
        repo.deleteById(id);
    }

    @Override
    public List<UserRole> getAllRoles() {
        return repo.findAll();
    }

    @Override
    public UserRole findRoleByName(String name) {
        return repo.findByName(name);
    }
}
