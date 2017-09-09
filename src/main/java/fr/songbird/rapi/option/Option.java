package fr.songbird.rapi.option;

/**
 * @since 09/09/17
 */
public interface Option<T> {

    /**
     * @return {@code true} if the option is a {@code Some} object, {@code false} otherwise.
     */
    boolean isSome();

    /**
     * @return {@code true} if the option is a {@code None} object, {@code false} otherwise.
     */
    boolean isNone();


    /**
     * @param customErrorMessage Your custom error message.
     * @return The contained value if the {@code Option} is {@code Some}. Throws
     * a {@link RuntimeException} displaying your custom error message, otherwise.
     * @exception RuntimeException If {@code Option} objet is {@code None}.
     * @exception NullPointerException If {@code customErrorMessage} is null. No worry, this NPE
     * is thrown with a relevant error message to help you.
     */
    T expect(String customErrorMessage);
}
