package com.filelibrary.gabrielmello.util;

import java.lang.reflect.Constructor;

/**
 *
 * @author Gabriel Mello de Oliveira
 */
public class CreateObject {
    
    public static <E> E getInstance(Class<E> interf, Object[] params) throws Exception {
        Class<?>[] constructorsTypes = new Class<?>[params.length];
        for (int i = 0; i < constructorsTypes.length; i++) {
            constructorsTypes[i] = params[i].getClass();
        }
        Constructor<?> c = interf.getConstructor(constructorsTypes);
        
        return (E) c.newInstance(params);
    }
    
}
