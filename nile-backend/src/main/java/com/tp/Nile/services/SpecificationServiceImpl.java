package com.tp.Nile.services;

import com.tp.Nile.exceptions.*;
import com.tp.Nile.models.Specification;
import com.tp.Nile.models.Type;
import com.tp.Nile.repositories.SpecificationRepository;
import com.tp.Nile.repositories.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SpecificationServiceImpl implements SpecificationService {

    @Autowired
    SpecificationRepository repo;

    @Autowired
    TypeServiceImpl typeService;

    @Override
    public List<Specification> getAllSpecs() {
        return repo.findAll();
    }

    @Override
    public List<Specification> getSpecsByType(Type type) {
        return repo.getSpecsByType(type);
    }

    @Override
    public Specification getSpecById(Integer specId) throws NullSpecIdException, InvalidSpecIdException {
        if (specId == null) {
            throw new NullSpecIdException("Cannot get specification with null id");
        }
        Specification retrieved = null;
        Optional<Specification> spec = repo.findById(specId);
        if (spec.isPresent()) {
            retrieved = spec.get();
            return retrieved;
        } else {
            throw new InvalidSpecIdException("Specification with id " + specId + " does not exist");
        }
    }

    @Override
    public Specification addSpec(Specification newSpec) throws NullTypeIdException, InvalidTypeIdException, NullTypeNameException, EmptyTypeNameException {
        if (newSpec.getType() != null && newSpec.getType().getTypeId() != null) {
            newSpec.setType(typeService.getTypeById(newSpec.getType().getTypeId()));
        } else {
            typeService.addType(newSpec.getType());
        }
        return repo.saveAndFlush(newSpec);
    }

    @Override
    public Specification updateSpec(Integer specId, Specification updatedSpec) throws NullSpecIdException, InvalidSpecIdException {
        Specification toUpdate = getSpecById(specId);
        updatedSpec.setSpecId(specId);
        return repo.saveAndFlush(updatedSpec);
    }

    @Override
    public boolean deleteSpec(Integer specId) throws NullSpecIdException {
        if (specId == null) {
            throw new NullSpecIdException("Cannot get specification with null id");
        }
        Specification retrieved = repo.findById(specId).get();
        if (retrieved != null) {
            repo.delete(retrieved);
        }
        return retrieved != null;
    }
}
