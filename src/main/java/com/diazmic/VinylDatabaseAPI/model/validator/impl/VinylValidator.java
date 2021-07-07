package com.diazmic.VinylDatabaseAPI.model.validator.impl;

import com.diazmic.VinylDatabaseAPI.model.Vinyl;
import com.diazmic.VinylDatabaseAPI.model.validator.Validator;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

public class VinylValidator implements Validator<Vinyl> {
    private static final String CATALOG_NUMBER = "catalogNumber";
    private static final String TITLE = "title";
    private static final String ARTIST = "artist";
    private static final String TYPE = "type";
    private static final String SIZE = "size";
    private static final String TRACKS = "tracks";
    private static final String VINYL_INVENTORY = "vinylInventory";
    private static VinylValidator vinylValidator = new VinylValidator();

    public boolean validate(Vinyl targetModel) throws IllegalAccessException, InvocationTargetException {
        List<String> nonNull = List.of(CATALOG_NUMBER,TITLE,ARTIST,TYPE
                                        ,SIZE,TRACKS,VINYL_INVENTORY);
        Field[] fields = Vinyl.class.getDeclaredFields();
        for(Field field : fields){
            PropertyDescriptor pd;
            try{
                pd = new PropertyDescriptor(field.getName(),targetModel.getClass());
            } catch (IntrospectionException e) {
                return false;
            }

            if(nonNull.contains(field.getName()) && pd.getReadMethod().invoke(targetModel)== null)
                return false;
        }
        return true;
    }

    public static VinylValidator getVinylValidator(){
        return vinylValidator;
    }

}
