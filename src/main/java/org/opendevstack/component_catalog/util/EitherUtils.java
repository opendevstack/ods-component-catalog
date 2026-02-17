package org.opendevstack.component_catalog.util;

import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Utility class to handle the result of a computation that can either return a value or throw an exception.
 * On both cases, an Either object is returned, which contains either the value or the exception.
 */
@SuppressWarnings({"java:S112", "java:S1452"})
public class EitherUtils {

    @FunctionalInterface
    public interface ThrowingFunction<T, R, E extends Exception> {
        R apply(T t) throws E;
    }

    @FunctionalInterface
    public interface ThrowingBiFunction<T1, T2, R, E extends Exception> {
        R apply(T1 t, T2 t2) throws E;
    }

    @FunctionalInterface
    public interface ThrowingSupplier<R, E extends Exception> {
        R get() throws E;
    }

    private EitherUtils() {
        // Avoid instantiation
    }

    public static <T, R, E extends Exception> Function<T, R>  uncheckedFrom(ThrowingFunction<T, R, E> fun) {
        return t -> uncheckedFrom(() -> fun.apply(t)).get();
    }

    public static <T1, T2, R, E extends Exception> BiFunction<T1, T2, R> uncheckedFrom(ThrowingBiFunction<T1, T2, R, E> fun) {
        return (t1, t2) -> uncheckedFrom(() -> fun.apply(t1, t2)).get();
    }

    public static <R, E extends Exception> Supplier<R> uncheckedFrom(ThrowingSupplier<R, E> sup) {
        return () -> {
            try {
                return sup.get();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

    public static <V, E extends Exception> Either<V, E> eitherOf(V value) {
        return new Either<>(value, null);
    }

    public static <V, E extends Exception> Either<V, E> eitherOf(E error) {
        return new Either<>(null, error);
    }

    public static <T, R> Function<T, Either<R, ? extends Exception>> eitherFrom(Function<T, R> fun) {
        return t -> either(fun, t);
    }

    public static <T1, T2, R> BiFunction<T1, T2, Either<R, ? extends Exception>> eitherFrom(BiFunction<T1, T2, R> bifun) {
        return (t1, t2) -> either(bifun, t1, t2);
    }

    public static <R> Supplier<Either<R, ? extends Exception>> eitherFrom(Supplier<R> sup) {
        return () -> either(sup);
    }

    public static <T, R> Either<R, ? extends Exception> either(Function<T, R> fun, T t) {
        return either(() -> fun.apply(t));
    }

    public static <T1, T2, R> Either<R, ? extends Exception> either(BiFunction<T1, T2, R> bifun, T1 t1, T2 t2) {
        return either(() -> bifun.apply(t1, t2));
    }

    public static <R> Either<R, ? extends Exception> either(Supplier<R> sup) {
        try {
            return eitherOf(sup.get());
        } catch (Exception e) {
            return eitherOf(e);
        }
    }

    public static <T, R> Function<T, Optional<R>> maybeValueFrom(Function<T, R> fun) {
        return t -> maybeValue(fun, t);
    }

    public static <T1, T2, R> BiFunction<T1, T2, Optional<R>> maybeValueFrom(BiFunction<T1, T2, R> bifun) {
        return (t1, t2) -> maybeValue(bifun, t1, t2);
    }

    public static <R> Supplier<Optional<R>> maybeValueFrom(Supplier<R> sup) {
        return () -> maybeValue(sup);
    }

    public static <T, R> Optional<R> maybeValue(Function<T, R> fun, T t) {
        return maybeValue(() -> fun.apply(t));
    }

    public static <T1, T2, R> Optional<R> maybeValue(BiFunction<T1, T2, R> bifun, T1 t1, T2 t2) {
        return maybeValue(() -> bifun.apply(t1, t2));
    }

    public static <R> Optional<R> maybeValue(Supplier<R> sup) {
        return Optional.ofNullable(either(sup).getValue());
    }

    public static <T, R> Function<T, Optional<? extends Exception>> maybeErrorFrom(Function<T, R> fun) {
        return t -> maybeError(fun, t);
    }

    public static <T1, T2, R> BiFunction<T1, T2, Optional<? extends Exception>> maybeErrorFrom(BiFunction<T1, T2, R> bifun) {
        return (t1, t2) -> maybeError(bifun, t1, t2);
    }

    public static <R> Supplier<Optional<? extends Exception>> maybeErrorFrom(Supplier<R> sup) {
        return () -> maybeError(sup);
    }

    public static <T, R> Optional<? extends Exception> maybeError(Function<T, R> fun, T t) {
        return maybeError(() -> fun.apply(t));
    }

    public static <T1, T2, R> Optional<? extends Exception> maybeError(BiFunction<T1, T2, R> bifun, T1 t1, T2 t2) {
        return maybeError(() -> bifun.apply(t1, t2));
    }

    public static <R> Optional<? extends Exception> maybeError(Supplier<R> sup) {
        return Optional.ofNullable(either(sup).getError());
    }

}
