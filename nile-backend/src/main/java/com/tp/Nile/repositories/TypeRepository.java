package com.tp.Nile.repositories;

import com.tp.Nile.models.Type;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
@Profile({ "dev", "test" })
public interface TypeRepository extends JpaRepository<Type,Integer> {
}
