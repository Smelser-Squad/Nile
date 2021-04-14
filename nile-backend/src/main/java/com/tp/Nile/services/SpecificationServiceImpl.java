package com.tp.Nile.services;

import com.tp.Nile.exceptions.InvalidSpecIdException;
import com.tp.Nile.exceptions.NullSpecIdException;
import com.tp.Nile.models.Specification;
import com.tp.Nile.models.Type;
import com.tp.Nile.repositories.SpecificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SpecificationServiceImpl implements SpecificationService {

    @Autowired
    SpecificationRepository repo;

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
    public Specification addSpec(Specification newSpec) {
        return repo.saveAndFlush(newSpec);
    }

    @Override
    public Specification updateSpec(Specification updatedSpec) {
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
