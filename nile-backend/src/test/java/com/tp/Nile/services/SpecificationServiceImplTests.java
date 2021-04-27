package com.tp.Nile.services;

import com.tp.Nile.exceptions.InvalidSpecIdException;
import com.tp.Nile.exceptions.NullSpecIdException;
import com.tp.Nile.models.Specification;
import com.tp.Nile.models.Type;
import com.tp.Nile.repositories.SpecificationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class SpecificationServiceImplTests {

    @Mock
    private SpecificationRepository repo;

    @InjectMocks
    private SpecificationServiceImpl service;

    @Test
    public void testGetAllSpecsGoldenPath() {
        when(repo.findAll()).thenReturn(List.of(new Specification(1, "test spec", new Type(1, "test type", new ArrayList<>()))));
        List<Specification> specs = service.getAllSpecs();

        assertThat(specs)
                .isNotNull()
                .isNotEmpty()
                .allSatisfy(spec -> {
                    assertThat(spec.getSpecId()).isEqualTo(1);
                    assertThat(spec.getSpecName()).isEqualTo("test spec");
                    Type type = spec.getType();
                    assertThat(type).isNotNull();
                    assertThat(type.getTypeId()).isEqualTo(1);
                    assertThat(type.getTypeName()).isEqualTo("test type");
                    assertThat(type.getProducts().size()).isEqualTo(0);
                });
    }

    @Test
    public void testGetSpecByIdGoldenPath() {
        when(repo.findById(1)).thenReturn(Optional.of(new Specification(1, "test spec", new Type(1, "test type", new ArrayList<>()))));
        Specification spec = null;
        try {
            spec = service.getSpecById(1);
        } catch (NullSpecIdException | InvalidSpecIdException ex) {
            fail("Exception thrown");
        }
        assertThat(spec)
                .isNotNull()
                .isInstanceOf(Specification.class)
                .hasFieldOrPropertyWithValue("specId", 1)
                .hasFieldOrPropertyWithValue("specName", "test spec");
        assertThat(spec.getType())
                .isNotNull()
                .hasFieldOrPropertyWithValue("typeId", 1)
                .hasFieldOrPropertyWithValue("typeName", "test type");
        assertThat(spec.getType().getProducts())
                .isNotNull()
                .isEmpty();
    }

    @Test
    public void testGetSpecByIdNullSpecId() {
        try {
            Specification spec = service.getSpecById(null);
            failBecauseExceptionWasNotThrown(NullSpecIdException.class);
        } catch (InvalidSpecIdException ex) {
            fail("Wrong exception thrown");
        } catch (NullSpecIdException ex) {

        }
    }

    @Test
    public void testGetSpecByIdInvalidSpecId() {
        when(repo.findById(-1)).thenReturn(Optional.empty());
        try {
            Specification spec = service.getSpecById(-1);
            failBecauseExceptionWasNotThrown(InvalidSpecIdException.class);
        } catch (NullSpecIdException ex) {
            fail("Wrong exception thrown");
        } catch (InvalidSpecIdException ex) {

        }
    }
}
