package fr.songbird.rapi.option;

/**
 * @since 09/09/17
 */
public class None implements Option<Integer> {

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
}
