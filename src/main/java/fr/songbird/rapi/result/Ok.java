package fr.songbird.rapi.result;

import java.util.Objects;

/**
 * @since 23/09/17
 */
public class Ok<T, E> implements Result<T, E> {

    private final T value;

    public Ok(T value) {
        this.value = Objects.requireNonNull(value, "value cannot be empty.");
    }

    @Override
    public boolean isOk() {
        return true;
    }
}
