package org.opendevstack.component_catalog.util;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@SuppressWarnings({"java:S112"})
public class Either<R, E extends Exception> {
    private final R value;
    protected final E error;
    protected final boolean ok;
    protected final boolean failed;

    Either(R value, E error) {
        this.error = error;
        this.failed = error != null;
        this.ok = !this.failed;
        this.value = value;
    }

    public void throwError() {
        if (this.error != null) {
            throw new RuntimeException(this.error);
        } else {
            throw new IllegalStateException("No error to throw");
        }
    }
}
