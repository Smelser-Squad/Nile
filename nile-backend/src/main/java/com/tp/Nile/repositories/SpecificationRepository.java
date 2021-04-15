package com.tp.Nile.repositories;

import com.tp.Nile.models.Specification;
import com.tp.Nile.models.Type;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Profile({ "dev", "test" })
public interface SpecificationRepository extends JpaRepository<Specification, Integer> {
    @Query("select s from Specification s where s.type=:type")
    List<Specification> getSpecsByType(@Param("type") Type type);
}
