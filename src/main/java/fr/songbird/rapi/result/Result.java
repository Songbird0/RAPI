package fr.songbird.rapi.result;

/**
 * @since 23/09/17
 */
public interface Result<T, E> {

    /**
     * Returns {@code true} if the result is an {@code Ok} object,
     * {@code false} otherwise.
     * @return {@code true} if the result is an {@code Ok} object,
     * {@code false} otherwise.
     */
    boolean isOk();
}
