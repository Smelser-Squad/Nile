package com.tp.Nile.services;

import com.tp.Nile.exceptions.InvalidSpecIdException;
import com.tp.Nile.exceptions.NullSpecIdException;
import com.tp.Nile.models.Specification;
import com.tp.Nile.models.Type;

import java.util.List;

public interface SpecificationService {
    List<Specification> getAllSpecs();
    List<Specification> getSpecsByType(Type type);
    Specification getSpecById(Integer specId) throws NullSpecIdException, InvalidSpecIdException;
    Specification addSpec(Specification newSpec);
    Specification updateSpec(Integer specId, Specification updatedSpec) throws NullSpecIdException, InvalidSpecIdException;
    boolean deleteSpec(Integer specId) throws NullSpecIdException;
}
