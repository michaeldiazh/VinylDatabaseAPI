package com.diazmic.VinylDatabaseAPI.daos;

import com.diazmic.VinylDatabaseAPI.db.dao.impl.VinylDao;
import com.diazmic.VinylDatabaseAPI.db.repo.VinylRepository;
import com.diazmic.VinylDatabaseAPI.model.Vinyl;
import com.diazmic.VinylDatabaseAPI.model.validator.impl.VinylValidator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class VinylDaoTest {

    @InjectMocks
    private VinylDao vinylDao;

    @Mock
    private VinylRepository vinylRepository;

    @Mock
    private VinylValidator vinylValidator;

    @Mock
    private Vinyl mockVinyl1;

    @Mock
    private Vinyl mockVinyl2;

    @Mock
    private Vinyl mockVinyl3;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void contextLoads() {
        Assertions.assertThat(vinylDao).isNotNull();
        Assertions.assertThat(vinylRepository).isNotNull();
        Assertions.assertThat(vinylValidator).isNotNull();
    }



}
