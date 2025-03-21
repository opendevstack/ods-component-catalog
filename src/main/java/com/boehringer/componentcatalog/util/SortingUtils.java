package com.boehringer.componentcatalog.util;

import com.boehringer.componentcatalog.server.model.SortOrder;

import java.util.Comparator;
import java.util.function.Function;

import static java.util.Comparator.comparing;

public class SortingUtils {

    private SortingUtils() {
        // Hide the implicit public constructor
    }

    public static <T, U extends Comparable<? super U>> Comparator<T> fieldSorter(Function<T, ? extends U> fieldGetter,
                                                                                 SortOrder order) {
        return switch (order) {
            case ASC -> comparing(fieldGetter);
            case DESC -> comparing(fieldGetter).reversed();
        };
    }

}
