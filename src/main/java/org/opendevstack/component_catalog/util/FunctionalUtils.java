package org.opendevstack.component_catalog.util;

import org.opendevstack.component_catalog.server.model.SortOrder;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

public class FunctionalUtils {

    public enum JoinType {
        INNER, LEFT, RIGHT, FULL
    }

    public static <T, U extends Comparable<? super U>> Comparator<T> fieldSorter(Function<T, ? extends U> fieldGetter,
                                                                                 SortOrder order) {
        return switch (order) {
            case ASC -> comparing(fieldGetter);
            case DESC -> comparing(fieldGetter).reversed();
        };
    }

    public static <T, K> List<T> sortBy(List<T> inputList, Function<T, K> keyGetter, List<K> keysOrder) {
        inputList = Optional.ofNullable(inputList)
                .orElse(new ArrayList<>());

        // Sort according to the order of the item's key on keysOrder.
        // Keys should not null and be found in keysOrder,
        // if not the result is not guaranteed to be correct.
        Comparator<T> comparator = (a, b) -> {
            var indexA = keysOrder.indexOf(keyGetter.apply(a));
            var indexB = keysOrder.indexOf(keyGetter.apply(b));

            return Integer.compare(indexA, indexB);
        };

        return inputList.stream()
                .sorted(comparator)
                .toList();

    }

    public static <S, T, K> List<Pair<S, T>> fullJoin(List<S> left, Function<S, K> keyLeft,
                                                      List<T> right, Function<T, K> keyRight) {
        return join(left, keyLeft, right, keyRight, JoinType.FULL);
    }

    public static <S, T, K> List<Pair<S, T>> join(List<S> left, Function<S, K> keyLeft,
                                                  List<T> right, Function<T, K> keyRight,
                                                  JoinType joinType) {

        left = Optional.ofNullable(left).orElse(new ArrayList<>());
        right = Optional.ofNullable(right).orElse(new ArrayList<>());

        var leftKeys = left.stream()
                .map(keyLeft)
                .toList();

        var rightKeys = right.stream()
                .map(keyRight)
                .toList();

        var leftByKeys = zipToMap(leftKeys, left);
        var rightByKeys = zipToMap(rightKeys, right);

        // These keys will be used to determine both which left, right values to include in the result, and also sorting order
        var streamKeys = switch (joinType) {
            case INNER -> leftKeys.stream()
                    .filter(rightKeys::contains)
                    .toList();
            case LEFT -> leftKeys;
            case RIGHT -> rightKeys;
            case FULL -> {
                var notOnLeft = new HashSet<>(rightKeys);
                leftKeys.forEach(notOnLeft::remove);

                var fullKeys = new ArrayList<>(leftKeys);
                fullKeys.addAll(notOnLeft);

                yield fullKeys;
            }
        };

        return streamKeys.stream()
                .map(k -> Pair.of(leftByKeys.get(k), rightByKeys.get(k)))
                .toList();
    }

    public static <T> Pair<List<T>, List<T>> splitBy(List<T> list, Predicate<T> criteria) {
        if (CollectionUtils.isEmpty(list)) {
            return Pair.of(new ArrayList<>(), new ArrayList<>()); // Return empty lists if input is empty
        }

        var partitioned = list.stream()
                .collect(Collectors.partitioningBy(criteria));

        // Return mutable lists to avoid issues with immutability
        return Pair.of(
                new ArrayList<>(partitioned.get(true)),
                new ArrayList<>(partitioned.get(false))
        );
    }

    public static <T, S> List<S> select(List<T> list, Function<T, S> selector) {
        return mapList(list, selector);
    }

    public static <T, S> List<S> mapList(List<T> list, Function<T, S> mapper) {
        if (CollectionUtils.isEmpty(list)) {
            return new ArrayList<>(); // Return empty list if input is empty
        }

        return list.stream()
                .map(mapper)
                .toList();
    }

    public static <K, V> Map<K, V> zipToMap(List<K> keys, List<V> values) {
        Map<K, V> result = new HashMap<>();
        int size = Math.min(keys.size(), values.size());

        for (int i = 0; i < size; i++) {
            result.put(keys.get(i), values.get(i));
        }

        return result;
    }

}


