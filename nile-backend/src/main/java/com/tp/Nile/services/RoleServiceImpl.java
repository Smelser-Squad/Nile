package com.tp.Nile.services;

import com.tp.Nile.exceptions.*;
import com.tp.Nile.models.Answer;
import com.tp.Nile.models.Cart;
import com.tp.Nile.models.Role;
import com.tp.Nile.models.User;
import com.tp.Nile.repositories.RoleRepository;
import com.tp.Nile.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository repo;

    @Override
    public List<Role> getAllRoles() throws NullRoleIdException {

        List<Role> roles = repo.findAll();

        for (int i = 0; i < roles.size(); i++)
        {
            if (roles.get(i).getId() == null)
                throw new NullRoleIdException("Role id can not be null");
        }

        return roles;
    }

    @Override
    public Role getRoleById(Long roleId) throws InvalidRoleIdException, NullRoleIdException {
        if (roleId == null)
        {
            throw new NullRoleIdException("Role id can not be null");
        }

        Role retrieved = null;

        Optional<Role> role = repo.findById(roleId);

        if(role.isPresent()){
            retrieved=role.get();
            return retrieved;
        }else{
            throw new InvalidRoleIdException("Role with that id does not exist");
        }

    }

    @Override
    public Role addRole(Role role) throws NullRoleException {
        if (role == null) {
            throw new NullRoleException("User can not be null");
        }
        else
            return repo.saveAndFlush(role);

    }

    @Override
    public Role updateRole(Role role) throws InvalidRoleIdException, NullRoleIdException, NullRoleException {

        if (role.getId() == null)
            throw new NullRoleIdException("Role id can not be null");
        else if (role.getId() < 0)
            throw new InvalidRoleIdException("Role id can not be invalid");
        Role edited = repo.findById((long) Math.toIntExact(role.getId())).get();

        if (edited != null) {
            edited.setId(role.getId());
        }
        else
            throw new NullRoleException("Role can not be null");

        return repo.saveAndFlush(role);

    }

    @Override
    public boolean deleteRole(Long roleId) throws NullRoleIdException, InvalidRoleIdException {
        if(roleId==null){
            throw new NullRoleIdException("Cannot delete role with null id");
        }
        else if (roleId < 0)
            throw new InvalidRoleIdException("Cannot delete role with invalid id");
        Role retrieved = repo.findById(roleId).get();

        if(retrieved!=null){
            repo.delete(retrieved);
            return true;
        }else{
            throw new InvalidRoleIdException("Role with that id does not exist");
        }
    }

}
