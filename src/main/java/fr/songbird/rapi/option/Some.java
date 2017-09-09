package fr.songbird.rapi.option;

import java.util.Objects;

/**
 * @since 09/09/17
 */
public class Some<T> implements Option<T> {

    private final T value;

    public Some(T value) {
        Objects.requireNonNull(value, "value cannot be null.");
        this.value = value;
    }

    @Override
    public boolean isSome() {
        return true;
    }

    @Override
    public boolean isNone() {
        return false;
    }

    @Override
    public T expect(String customErrorMessage) {
        return value;
    }

    @Override
    public T unwrap() {
        return value;
    }

    @Override
    public T unwrapOr(T defaultValue) {
        Objects.requireNonNull(defaultValue, "defaultValue cannot be null.");
        return value;
    }
}
