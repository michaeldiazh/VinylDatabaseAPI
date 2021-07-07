package com.diazmic.VinylDatabaseAPI.model.validator;

import com.diazmic.VinylDatabaseAPI.model.Genre;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

public interface Validator<Model> {
    boolean validate(Model targetModel) throws NoSuchFieldException, IllegalAccessException, InvocationTargetException;
}
