package com.tp.Nile.repositories;


import com.tp.Nile.models.*;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Profile({ "dev", "test" })
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("select c from Cart c where c.user.userId=:user_id")
    List<Cart> getCartByUserId(@Param("user_id") Integer user_id);

    @Query("select a from Answer a where a.user.userId=:user_id")
    List<Answer> getAnswersByUserId(@Param("user_id")Integer user_id);

}
