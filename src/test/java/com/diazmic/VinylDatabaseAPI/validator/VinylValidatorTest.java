package com.diazmic.VinylDatabaseAPI.validator;

import com.diazmic.VinylDatabaseAPI.model.Artist;
import com.diazmic.VinylDatabaseAPI.model.Vinyl;
import com.diazmic.VinylDatabaseAPI.model.VinylInventory;
import com.diazmic.VinylDatabaseAPI.model.factory.VinylFactory;
import com.diazmic.VinylDatabaseAPI.model.validator.impl.VinylValidator;
import com.mongodb.assertions.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

@SpringBootTest
public class VinylValidatorTest {
    private final Vinyl testVinyl = VinylFactory.makeVinyl("test","test",new Artist(), Vinyl.Type.EP, Vinyl.Size.SEVEN_INCH, Vinyl.RPM.SEVENTY_EIGHT,new ArrayList<>(),VinylInventory.builder().build());

    @Test
    public void Test_1_1_validate_works() throws IllegalAccessException, InvocationTargetException {
        Assertions.assertTrue(VinylValidator.getVinylValidator().validate(testVinyl));
    }
    @Test
    public void Test_1_2_validate_works_for_catching_null() throws IllegalAccessException, InvocationTargetException {
        Vinyl mockVinyl = Mockito.mock(Vinyl.class);
        Assertions.assertFalse(VinylValidator.getVinylValidator().validate(mockVinyl));
    }
}
