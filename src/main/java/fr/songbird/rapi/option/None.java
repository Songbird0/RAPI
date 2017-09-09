package fr.songbird.rapi.option;

import java.util.Objects;

/**
 * @since 09/09/17
 */
public class None<T> implements Option<T> {

    public None() {

    }

    @Override
    public boolean isSome() {
        return false;
    }

    @Override
    public boolean isNone() {
        return true;
    }

    @Override
    public T expect(String customErrorMessage) {
        Objects.requireNonNull(customErrorMessage, "customErrorMessage cannot be null.");
        throw new RuntimeException(customErrorMessage);
    }

    @Override
    public T unwrap() {
        throw new RuntimeException("called `"
                + Option.class.getName() + ".unwrap()` on a `None` object");
    }
}
