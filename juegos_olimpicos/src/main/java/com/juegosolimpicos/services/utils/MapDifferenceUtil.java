package com.juegosolimpicos.services.utils;

import com.google.common.collect.Sets;
import com.juegosolimpicos.dto.ValueDifferenceDTO;

import javax.validation.constraints.NotNull;
import java.util.*;

public class MapDifferenceUtil {

    public static Map difference(@NotNull Map left, @NotNull Map right) {
        final Map result = new LinkedHashMap();
        final Set<String> keys = Sets.union(
                UtilidadesModel.getOrDef(left.keySet(), new LinkedHashSet<>()),
                UtilidadesModel.getOrDef(right.keySet(), new LinkedHashSet<>()));

        for (String key : keys) {
            if (isOf(left, right, key, String.class)) {
                compareString(result, key, (String) left.get(key), (String) right.get(key));
            } else if (isOf(left, right, key, List.class)) {
                compareList(result, key, (List) left.get(key), (List) right.get(key));
            } else if (isOf(left, right, key, Map.class)) {
                compareMap(result, key, (Map) left.get(key), (Map) right.get(key));
            } else if (isOf(left, right, key, Object.class)) {
                compareObjects(result, key, left.get(key), right.get(key));
            }
        }

        return result;
    }
    /**
     * Return if map is instance of
     * @param left
     * @param right
     * @param key
     * @param o
     * @return
     */
    private static boolean isOf(Map left, Map right, String key, Class<?> o) {
        return left.containsKey(key)
                ? o.isInstance(left.get(key)) : o.isInstance(right.get(key));
    }

    /**
     * Add string if its different
     * @param result
     * @param key
     * @param left
     * @param right
     */
    private static void compareString(Map result, String key, String left, String right) {
        if (UtilidadesModel.isStringDistinct(left, right)) {
            result.put(key, ValueDifferenceDTO.build(left, right));
        }
    }

    private static void compareObjects(Map result, String key, Object left, Object right) {
        if (Boolean.FALSE.equals(Objects.equals(left, right))) {
            result.put(key, ValueDifferenceDTO.build(left, right));
        }
    }

    private static void compareList(Map result, String key, List left, List right) {
        if (UtilidadesModel.isListDistinct(left, right)) {
            result.put(key, ValueDifferenceDTO.build(left, right));
        }
    }

    private static void compareMap(Map result, String key, Map left, Map right) {
        final Map diff = difference(
                UtilidadesModel.getOrDef(left, new HashMap()),
                UtilidadesModel.getOrDef(right, new HashMap()));
        if (UtilidadesModel.isNotEmpty(diff)) {
            result.put(key, diff);
        }
    }

}
