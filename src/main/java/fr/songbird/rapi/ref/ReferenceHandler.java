package fr.songbird.rapi.ref;

/**
 * @since 19/09/17
 */
public class ReferenceHandler<T> {

    private T value;

    public ReferenceHandler(T value) {
        this.value = value;
    }

    public void set(T newValue) {
        this.value = newValue;
    }

    public T get() {
        return this.value;
    }

}
