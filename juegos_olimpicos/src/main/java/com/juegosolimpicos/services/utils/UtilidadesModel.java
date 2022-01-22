package com.juegosolimpicos.services.utils;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class UtilidadesModel {

    public static <T> T getOrDef(final T val, final T def) {
        return val != null ? val : def;
    }

    public static boolean isNotEmpty(final Map<?, ?> value) {
        return value != null && !value.isEmpty();
    }

    public static <T> boolean isListDistinct(final List<T> list1, final List<T> list2) {
        return new HashSet<>(list1).equals(new HashSet<>(list2));
    }

    public static boolean isStringDistinct(final String left, final String right){
        return !left.equals(right);
    }
}
