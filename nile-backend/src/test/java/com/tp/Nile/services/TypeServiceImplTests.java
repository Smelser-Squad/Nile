package com.tp.Nile.services;

import com.tp.Nile.models.Type;
import com.tp.Nile.repositories.TypeRepository;
import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashSet;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TypeServiceImplTests {

    @Mock
    private TypeRepository repo;

    @InjectMocks
    private TypeServiceImpl service;

    @BeforeEach
    public void setup() {
        Type type = new Type(1, "type name", new HashSet<>());
        repo.save(type);
    }
}
