package fr.songbird.rapi.option;

/**
 * @since 09/09/17
 */
public class Some<T> implements Option<Integer> {
    public Some(T value) {

    }

    @Override
    public boolean isSome() {
        return true;
    }

    @Override
    public boolean isNone() {
        return false;
    }
}
