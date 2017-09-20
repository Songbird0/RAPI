package fr.songbird.rapi.ref;

import java.util.Objects;

/**
 * Represents a mutable/immutable reference to borrow.
 * <pre>{@code
 * final ReferenceHandler<String> ref = new ReferenceHandler<>("Hello there!");
 * modify(ref); // `ref` has been modified
 * System.out.println(ref); // prints 'Hello world!'
 *
 * public void modify(final ReferenceHandler<String> ref) {
 *     ref.set("Hello world!"); // modify the same object.
 * }
 * }</pre>
 * <strong>Note</strong>: {@code ReferenceHandler} is a safe container, it do <em>NOT</em> accept any null reference.
 * @since 19/09/17
 */
public class ReferenceHandler<T> {

    /**
     * The current contained value.
     */
    private T value;

    /**
     * Initialize a {@code ReferenceHandler} object with {@code `value`}.
     * @param value The object which must be borrowed.
     * @throws NullPointerException If {@code value} is null.
     */
    public ReferenceHandler(T value) {
        this.value = Objects.requireNonNull(value, "`value` cannot be null.");
    }

    /**
     * Binds {@code newValue} as contained value.
     * @param newValue The new value to bind.
     * @throws NullPointerException If {@code newValue} is null.
     */
    public void set(T newValue) {
        this.value = Objects.requireNonNull(newValue, "`newValue` cannot be null.");
    }

    /**
     * Returns the contained value.
     * @return The contained value.
     */
    public T get() {
        return this.value;
    }

}
