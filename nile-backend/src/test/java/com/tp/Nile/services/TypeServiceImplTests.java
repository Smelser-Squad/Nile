package com.tp.Nile.services;

import com.tp.Nile.exceptions.*;
import com.tp.Nile.models.Type;
import com.tp.Nile.repositories.TypeRepository;
import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TypeServiceImplTests {

    @Mock
    private TypeRepository repo;

    @InjectMocks
    private TypeServiceImpl service;

    @Test
    public void testGetAllTypesGoldenPath() {
        when(repo.findAll()).thenReturn(List.of(new Type(1, "test name", new ArrayList<>())));
        List<Type> types = service.getAllTypes();

        assertThat(types)
                .isNotNull()
                .isNotEmpty()
                .allSatisfy(type -> {
                    assertThat(type.getTypeId()).isEqualTo(1);
                    assertThat(type.getTypeName()).isEqualTo("test name");
                    assertThat(type.getProducts().size()).isEqualTo(0);
                });
    }

    @Test
    public void testGetTypeByIdGoldenPath() {
        when(repo.findById(1)).thenReturn(Optional.of(new Type(1, "test name", new ArrayList<>())));
        Type type = null;
        try {
            type = service.getTypeById(1);
        } catch (NullTypeIdException | InvalidTypeIdException ex) {
            fail("Exception thrown");
        }
        assertThat(type)
                .isNotNull()
                .isInstanceOf(Type.class)
                .hasFieldOrPropertyWithValue("typeId", 1)
                .hasFieldOrPropertyWithValue("typeName", "test name");
        assertThat(type.getProducts())
                .isNotNull()
                .isEmpty();
    }

    @Test
    public void testGetTypeByIdNullTypeId() {
        try {
            Type type = service.getTypeById(null);
            failBecauseExceptionWasNotThrown(NullTypeIdException.class);
        } catch (InvalidTypeIdException ex) {
            fail("Wrong exception thrown");
        } catch (NullTypeIdException ex) {

        }
    }

    @Test
    public void testGetTypeByIdInvalidTypeId() {
        when(repo.findById(-1)).thenReturn(Optional.empty());
        try {
            Type type = service.getTypeById(-1);
            failBecauseExceptionWasNotThrown(InvalidTypeIdException.class);
        } catch (NullTypeIdException ex) {
            fail("Wrong exception thrown");
        } catch (InvalidTypeIdException ex) {

        }
    }

    @Test
    public void testAddTypeGoldenPath() {
        int id = 1;
        Type newType = new Type();
        newType.setTypeName("new type");
        when(repo.saveAndFlush(newType)).thenReturn(newType);
        Type addedType = null;
        try {
            addedType = service.addType(newType);
        } catch (NullTypeNameException | EmptyTypeNameException ex) {
            fail("Exception thrown");
        }
        addedType.setTypeId(id);
        assertThat(addedType)
                .isNotNull()
                .isInstanceOf(Type.class)
                .hasFieldOrPropertyWithValue("typeId", 1)
                .hasFieldOrPropertyWithValue("typeName", "new type");
        assertThat(addedType.getProducts())
                .isNotNull()
                .isEmpty();
    }

    @Test
    public void testAddTypeNullTypeName() {
        Type type = new Type();
        try {
            service.addType(type);
            failBecauseExceptionWasNotThrown(NullTypeNameException.class);
        } catch (EmptyTypeNameException ex) {
            fail("Wrong exception thrown");
        } catch (NullTypeNameException ex) {

        }
    }

    @Test
    public void testAddTypeEmptyTypeName() {
        Type type = new Type();
        type.setTypeName("");
        try {
            service.addType(type);
            failBecauseExceptionWasNotThrown(EmptyTypeNameException.class);
        } catch (NullTypeNameException ex) {
            fail("Wrong exception thrown");
        } catch (EmptyTypeNameException ex) {

        }
    }
}
