package com.tp.Nile.services;

import com.tp.Nile.exceptions.*;
import com.tp.Nile.models.Answer;
import com.tp.Nile.models.Cart;
import com.tp.Nile.models.Role;
import com.tp.Nile.models.User;

import java.util.List;

public interface RoleService {

    List<Role> getAllRoles()throws NullRoleIdException, InvalidRoleIdException;

    Role getRoleById(Long roleId)throws NullRoleIdException, InvalidRoleIdException;

    Role addRole(Role role) throws NullRoleIdException, InvalidRoleIdException, NullRoleException;

    Role updateRole(Role role) throws NullRoleIdException, InvalidRoleIdException, NullRoleException;

    boolean deleteRole(Long roleId) throws NullRoleIdException, InvalidRoleIdException;


}
