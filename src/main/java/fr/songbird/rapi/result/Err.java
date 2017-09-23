package fr.songbird.rapi.result;

import java.util.Objects;

/**
 * @since 23/09/17
 */
public class Err<T, E> implements Result<T, E> {

    private final E error;

    public Err(E error) {
        this.error = Objects.requireNonNull(error, "error type cannot be null.");
    }

    @Override
    public boolean isOk() {
        return false;
    }

    @Override
    public boolean isErr() {
        return true;
    }
}
