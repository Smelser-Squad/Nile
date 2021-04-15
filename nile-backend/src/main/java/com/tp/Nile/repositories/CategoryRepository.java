package com.tp.Nile.repositories;

import com.tp.Nile.models.Category;
import com.tp.Nile.models.Product;
import com.tp.Nile.models.Type;
import com.tp.Nile.models.Vendor;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Profile({ "dev", "test" })
public interface CategoryRepository extends JpaRepository<Category,Integer> {



}
