package com.diazmic.VinylDatabaseAPI.service;

import com.diazmic.VinylDatabaseAPI.db.dao.impl.VinylDao;
import com.diazmic.VinylDatabaseAPI.model.Artist;
import com.diazmic.VinylDatabaseAPI.model.Label;
import com.diazmic.VinylDatabaseAPI.model.Vinyl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class VinylServiceTest {
    @Mock
    private VinylDao mockVinylDao;

    @InjectMocks
    private VinylService vinylService;

    @Mock
    private Vinyl mockVinyl1;

    @Mock
    private Vinyl mockVinyl2;

    @Mock
    private Vinyl mockVinyl3;

    private Collection<Vinyl> vinylDatabase;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        vinylDatabase = new ArrayList<>();
        setUpDaoCreate();
        setUpDaoRead();
        setUpDaoUpdate();
        setUpDaoDelete();
        setUpVinylNameQueue();
        setUpMockVinyls();
    }

    @Test
    public void contextLoads() {
        Assertions.assertThat(vinylService).isNotNull();
    }

    @Test
    public void Test_1_1_getAllVinylsFromDatabase_WhenDatabaseIsEmpty(){
        Mockito.when(mockVinylDao.readAll()).thenReturn(List.of());
        assertTrue(vinylService.getAllVinyls().isEmpty());
    }

    @Test
    public void Test_1_2_getAllVinylsFromDatabase_WhenDatabaseHasOne(){
        Mockito.when(mockVinylDao.readAll()).thenReturn(Collections.singletonList(mockVinyl1));
        Collection<Vinyl>testDB = vinylService.getAllVinyls();
        assertEquals(1, testDB.size());
    }

    @Test
    public void Test_1_3_getAllVinylsFromDatabase_WhenDatabaseHasMany(){
        Mockito.when(mockVinylDao.readAll()).thenReturn(Arrays.asList(mockVinyl1,mockVinyl2,mockVinyl3));
        Collection<Vinyl>testDB = vinylService.getAllVinyls();
        assertEquals(3, testDB.size());
    }

    @Test
    public void Test_2_1_saveVinyl_WhenVinylIsSatisfied_ReturnsVinyl() throws Exception {
        Mockito.when(mockVinyl1.getCatalogNumber()).thenReturn("XXXX-XXXX");
        assertEquals(vinylService.saveVinyl(mockVinyl1),mockVinyl1);
        assertTrue(vinylDatabase.contains(mockVinyl1));

    }

    @Test
    public void Test_2_2_saveVinyl_WhenVinylIsNull_ThrowsException(){
        Exception actualException = assertThrows(Exception.class
                ,()->vinylService.saveVinyl(null));
        String expectedMessage = "Vinyl is null or Catalog Number is blank";
        assertEquals(expectedMessage,actualException.getMessage());
    }

    @Test
    public void Test_2_3_saveVinyl_WhenVinylCatalogNumberIsEmpty_ThrowsException(){
        Mockito.when(mockVinyl1.getCatalogNumber()).thenReturn("");
        Exception actualException = assertThrows(Exception.class
                ,()->vinylService.saveVinyl(mockVinyl1));
        String expectedMessage = "Vinyl is null or Catalog Number is blank";
        assertEquals(expectedMessage,actualException.getMessage());
    }

    @Test
    public void Test_2_4_saveVinyl_WhenVinylCatalogNumberIsBlank_ThrowsException(){
        Mockito.when(mockVinyl1.getCatalogNumber()).thenReturn(" ");
        Exception actualException = assertThrows(Exception.class
                ,()->vinylService.saveVinyl(mockVinyl1));
        String expectedMessage = "Vinyl is null or Catalog Number is blank";
        assertEquals(expectedMessage,actualException.getMessage());
    }

    @Test
    public void Test_3_1_getVinylFromCatalogNumber_WhereVinylIsInDB_ReturnTargetVinyl() throws Exception {
        // Set Up
        vinylDatabase = List.of(mockVinyl1,mockVinyl2,mockVinyl3);
        Mockito.when(mockVinylDao.readAll()).thenReturn(List.copyOf(vinylDatabase));

        // Execute
        Vinyl vinyl = vinylService.getVinyl("testCatalogNumber2");

        //Assert
        assertEquals(mockVinyl2,vinyl);
    }

    @Test
    public void Test_3_2_getVinylFromCatalogNumber_InputIsNull_ReturnException(){
        Exception exception = assertThrows(Exception.class,()->vinylService.getVinyl(null));
        String expectedMsg = "Vinyl does not exist in Database";
        assertNotNull(exception);
        assertEquals(expectedMsg,exception.getMessage());
    }

    @Test
    public void Test_3_3_getVinylFromCatalogNumber_TargetVinylNotInDB_ReturnException(){
        Exception exception = assertThrows(Exception.class,()->vinylService.getVinyl("YYYY-YYYY"));
        String expectedMsg = "Vinyl does not exist in Database";
        assertNotNull(exception);
        assertEquals(expectedMsg,exception.getMessage());
    }

    @Test
    public void Test_4_1_vinylNameQuery_InputIsValid_ReturnsList() throws Exception {
        vinylDatabase = List.of(mockVinyl1,mockVinyl2,mockVinyl3);
        Collection<Vinyl> actualList = vinylService.vinylNameQuery("test");
        assertEquals(3,actualList.size());
        vinylDatabase.forEach(vinyl -> assertTrue(actualList.contains(vinyl)));
    }

    @Test
    public void Test_4_2_vinylNameQuery_InputIsNotValid_ThrowsMongoDBException(){
        Exception exception = assertThrows(Exception.class, ()->vinylService.vinylNameQuery(null));
        String expectedMsg = "Search input is null.";
        assertNotNull(exception);
        assertEquals(expectedMsg,exception.getMessage());
    }

    @Test
    public void Test_4_3_vinylNameQuery_InputIsBlank_ReturnEmptyCollection() throws Exception {
        assertEquals(Collections.emptyList(), vinylService.vinylNameQuery(""));
    }

    @Test
    public void Test_4_4_vinylNameQuery_InputIsValidButNotInDB_ReturnsEmptyList() throws Exception {
        vinylDatabase = List.of(mockVinyl1,mockVinyl2,mockVinyl3);
        Collection<Vinyl> actualList = vinylService.vinylNameQuery("test4");
        assertEquals(0,actualList.size());
    }

    @Test
    public void Test_5_1_editVinyl_InputWithValidCatalogNumberAndVinyl_ReturnVinyl() throws Exception {
        vinylDatabase = List.of(mockVinyl1,mockVinyl2,mockVinyl3);
        String initCatalogNumber = mockVinyl1.getCatalogNumber();
        assertEquals(mockVinyl1.getCatalogNumber(), vinylService.editVinyl(initCatalogNumber,mockVinyl1).getCatalogNumber());
    }

    @Test
    public void Test_5_2_editVinyl_InputWithInValidCatalogNumberAndVinyl_Throws_Exception() throws Exception {
        setUpDaoRead();
        vinylDatabase = List.of(mockVinyl1,mockVinyl2,mockVinyl3);
        String initCatalogNumber = "testFakeCatalogNumberNotInDB";
        Exception actualException = assertThrows(Exception.class
                ,()->vinylService.editVinyl(initCatalogNumber,mockVinyl3));
        String expectedMsg = "Vinyl doesn't exists. Can not edit non-existent Vinyl.";
        assertEquals(expectedMsg,actualException.getMessage());
    }

    @Test
    public void Test_5_3_editVinyl_NotAbleToUpdate_Throws_Exception() {
        vinylDatabase = List.of(mockVinyl1,mockVinyl2,mockVinyl3);
        Vinyl mockTestVinyl = Mockito.mock(Vinyl.class);
        Mockito.when(mockTestVinyl.getCatalogNumber()).thenReturn("testCatalogNumber1");
        Mockito.when(mockTestVinyl.getTitle()).thenReturn("");
        String initCatalogNumber = mockVinyl1.getCatalogNumber();
        Exception actualException = assertThrows(Exception.class
                ,()->vinylService.editVinyl(initCatalogNumber,mockTestVinyl));
        String expectedMsg = "Vinyl can not be edited.";
        assertEquals(expectedMsg,actualException.getMessage());
    }

    @Test
    public void Test_6_1_deleteVinyl_AbleToDelete_ReturnDeletedVinyl() throws Exception {
        vinylDatabase.addAll(Arrays.asList(mockVinyl1,mockVinyl2,mockVinyl3));
        String inputCatalogNumber= mockVinyl1.getCatalogNumber();
        Vinyl actualVinyl = vinylService.deleteVinyl(inputCatalogNumber);
        Vinyl expectedVinyl = mockVinyl1;
        assertEquals(expectedVinyl.getCatalogNumber(),actualVinyl.getCatalogNumber());
        assertFalse(vinylDatabase.contains(actualVinyl));
    }

    @Test
    public void Test_6_2_deleteVinyl_NotAbleToDelete_InvalidCatalogNumber_ReturnDeletedVinyl() throws Exception{
        vinylDatabase.addAll(Arrays.asList(mockVinyl1,mockVinyl2,mockVinyl3));
        Exception outputException = assertThrows(Exception.class,
                () -> vinylService.deleteVinyl(""));
        String expectedMsg = "Can not remove non existent vinyl";
        assertEquals(expectedMsg,outputException.getMessage());
    }

    @Test
    public void Test_6_3_deleteVinyl_NotAbleToDelete_VinylNotInDatabase_ReturnDeletedVinyl() throws Exception{
        vinylDatabase.addAll(Arrays.asList(mockVinyl1,mockVinyl2,mockVinyl3));
        Exception outputException = assertThrows(Exception.class,
                () -> vinylService.deleteVinyl("testCatalogNumber4"));
        String expectedMsg = "Can not remove non existent vinyl";
        assertEquals(expectedMsg,outputException.getMessage());
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~[(0.1),(setUp())]

    private void setUpDaoCreate() throws InvocationTargetException, IllegalAccessException {
        //Create Method
        Mockito.when(mockVinylDao.create(Mockito.any(Vinyl.class)))
                .thenAnswer(invocation -> {
                    vinylDatabase.add(invocation.getArgument(0));
                    return invocation.getArgument(0);
                });
    }

    private void setUpDaoRead() throws Exception {
        // Retrieve Method
        Mockito.when(mockVinylDao.read(Mockito.anyString()))
                .thenAnswer(invocation -> {
                    for (Vinyl vinyl : vinylDatabase) {
                        if (vinyl.getCatalogNumber().equals(invocation.getArgument(0)))
                            return Optional.of(vinyl);
                    }
                    return Optional.empty();
                });

    }

    private void setUpDaoUpdate() throws InvocationTargetException, IllegalAccessException {
        // Update Method
        Mockito.when(mockVinylDao.update(Mockito.any(Vinyl.class),Mockito.anyString()))
                .thenAnswer(invocation -> {
                    Vinyl updatedVinyl = invocation.getArgument(0);
                    String targetId = invocation.getArgument(1);
                    for (Vinyl vinyl : vinylDatabase) {
                        if(vinyl.getCatalogNumber().equals(targetId) && !updatedVinyl.getTitle().isEmpty()){
                            return true;
                        }
                    }
                    return false;
                });

    }

    private void setUpDaoDelete(){
        // Delete Method
        Mockito.when(mockVinylDao.delete(Mockito.anyString()))
                .thenAnswer(invocation -> {
                    String inputString = invocation.getArgument(0);
                    Vinyl targetVinyl = mockVinylDao.read(inputString).get();
                    return vinylDatabase.remove(targetVinyl);
                });
    }

    private void setUpVinylNameQueue(){
        Mockito.when(mockVinylDao.vinylNameQueue(Mockito.anyString())).thenAnswer((invocation)->{
            List<Vinyl> collection= new ArrayList<>(List.of());
            String targetName = invocation.getArgument(0);
            vinylDatabase.forEach((vinyl) ->{
                if(vinyl.getTitle().matches("(.*)"+targetName+"(.*)"))
                    collection.add(vinyl);
            });
            return collection;
        });
    }

    private void setUpMockVinyls(){
        Mockito.when(mockVinyl1.getCatalogNumber()).thenReturn("testCatalogNumber1");
        Mockito.when(mockVinyl2.getCatalogNumber()).thenReturn("testCatalogNumber2");
        Mockito.when(mockVinyl3.getCatalogNumber()).thenReturn("testCatalogNumber3");
        Mockito.when(mockVinyl1.getTitle()).thenReturn("test1");
        Mockito.when(mockVinyl2.getTitle()).thenReturn("test2");
        Mockito.when(mockVinyl3.getTitle()).thenReturn("test3");
    }

}

