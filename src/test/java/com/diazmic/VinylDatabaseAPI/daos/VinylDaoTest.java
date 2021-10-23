package com.diazmic.VinylDatabaseAPI.daos;

import com.diazmic.VinylDatabaseAPI.db.dao.impl.VinylDao;
import com.diazmic.VinylDatabaseAPI.db.repo.VinylRepository;
import com.diazmic.VinylDatabaseAPI.model.*;
import com.diazmic.VinylDatabaseAPI.model.factory.VinylFactory;
import com.diazmic.VinylDatabaseAPI.model.validator.impl.VinylValidator;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;

@SpringBootTest
public class VinylDaoTest {

    @Autowired
    @InjectMocks
    private VinylDao vinylDao;

    @Autowired
    private VinylRepository vinylRepository;

    @Mock
    private VinylValidator vinylValidator;

    @Mock
    private Vinyl mockVinyl;

    private VinylFactory vinylFactory = VinylFactory.vinylFactory;


    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        if(vinylRepository.count() > 0 ){
            vinylRepository.deleteAll();
        }
    }

    @Test
    public void contextLoads() {
        assertNotNull(vinylDao);
        assertNotNull(vinylRepository);
        assertNotNull(vinylValidator);
        vinylRepository.deleteAll();
    }

    @Test
    public void Test_1_1_Create_When_Create_Invalid_Model_Throw_Exception() throws InvocationTargetException, IllegalAccessException {
        Mockito.when(vinylValidator.validate(Mockito.any(Vinyl.class))).thenReturn(false);
        Exception e = assertThrows(IllegalAccessException.class,() ->vinylDao.create(mockVinyl));
        String expectedMsg = "Cannot create vinyl. Model is not valid by Vinyl Validator....";
        assertEquals(e.getMessage(),expectedMsg);
    }

    @Test
    public void Test_1_2_Create_When_Creates_Valid_Model() throws InvocationTargetException, IllegalAccessException {
        Vinyl testVinyl = vinylFactory.makeTestVinyl();

        vinylRepository.save(testVinyl);
        Mockito.when(vinylValidator.validate(Mockito.any(Vinyl.class))).thenReturn(true);
        assertEquals(testVinyl,vinylDao.create(testVinyl));

    }

    @Test
    public void Test_1_3_Create_When_Vinyl_Exist() throws InvocationTargetException, IllegalAccessException {
        Vinyl testVinyl = vinylFactory.makeTestVinyl();
        Mockito.when(vinylValidator.validate(Mockito.any(Vinyl.class))).thenReturn(true);
        assertEquals(testVinyl,vinylDao.create(testVinyl));
    }

    @Test
    public void Test_2_1_Read_When_Vinyl_Not_There() throws Exception {
        assertTrue(vinylDao.read("0000").isEmpty());
    }

    @Test
    public void Test_2_2_Read_When_Vinyl_Is_There() throws Exception {
        vinylRepository.save(vinylFactory.makeTestVinyl());
        assertTrue(vinylDao.read("00000000").isPresent());
    }

    @Test
    public void Test_2_3_Read_Throw_Exception_When_Catalog_Number_is_Blank(){
        Exception e = assertThrows(Exception.class, ()-> vinylDao.read(" "));
        assertEquals("Catalog Number is blank", e.getMessage());
    }

    @Test
    public void Test_2_4_Read_Throw_Exception_When_Catalog_Number_is_Null(){
        Exception e = assertThrows(Exception.class, ()-> vinylDao.read(null));
        assertEquals("Catalog Number is null. Please check logs", e.getMessage());
    }

    @Test
    public void Test_2_5_Read_Throw_Exception_When_Catalog_Number_is_Empty(){
        Exception e = assertThrows(Exception.class, ()-> vinylDao.read(""));
        assertEquals("Catalog Number is empty", e.getMessage());
    }


}
