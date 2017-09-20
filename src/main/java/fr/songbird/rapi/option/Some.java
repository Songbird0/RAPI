/*
    A useful results API to release all of the functional programming power!
    Copyright (C) 2017  Anthony Defranceschi
    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.
    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.
    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
package fr.songbird.rapi.option;

import fr.songbird.rapi.ref.ReferenceHandler;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @since 09/09/17
 */
public class Some<T> implements Option<T> {

    private final T value;

    /**
     * Initializes a new {@code Some} container.
     * @param value The value to wrap.
     * @throws NullPointerException If {@code value} is null.
     */
    public Some(T value) {
        Objects.requireNonNull(value, "value cannot be null.");
        this.value = value;
    }

    /**
     * Copies another {@link Option}.
     * <p>
     * <strong>Note</strong>: This constructor performs a shallow copy, you should copy your resource beforehand.
     * <pre>{@code
     * final Option<String> foo = new Some<>("foo");
     * final Option<String> fooBrother = new Some<>(foo);
     * assertThat(foo != fooBrother, is(true));
     * assertThat(foo, is(equalTo(fooBrother)));
     * assertThat(foo.unwrap() == fooBrother.unwrap(), is(true)); // same object
     * }</pre>
     * @param option The option to copy.
     * @throws IllegalArgumentException If {@code option} isn't {@link Some}.
     */
    public Some(Option<T> option) {
        if (option.isSome()) {
            final Some<T> nonEmptyContainer = (Some<T>)option;
            this.value = nonEmptyContainer.value;
        }
        else {
            throw new IllegalArgumentException("`option` is none. It does not contain value to copy.");
        }
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
        Objects.requireNonNull(customErrorMessage, "customErrorMessage cannot be null.");
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

    @Override
    public T unwrapOrElse(Supplier<T> expression) {
        Objects.requireNonNull(expression, "`expression` cannot be null");
        Objects.requireNonNull(expression.get(), "The `expression` result cannot be null.");
        return value;
    }

    @Override
    public <U> Option<U> map(Function<T, U> appliedFunction) {
        Objects.requireNonNull(appliedFunction, "`appliedFunction` cannot be null.");
        final U newValue = appliedFunction.apply(this.value);
        Objects.requireNonNull(newValue, "`U` value cannot be null.");
        return new Some<>(newValue);
    }

    @Override
    public <U> U mapOr(U defaultValue, Function<T, U> appliedFunction) {
        Objects.requireNonNull(defaultValue, "defaultValue cannot be null.");
        Objects.requireNonNull(appliedFunction, "appliedFunction cannot be null.");
        final U returnedValue = appliedFunction.apply(this.value);
        Objects.requireNonNull(returnedValue, "returnedValue cannot be null.");
        return returnedValue;
    }

    @Override
    public <U> U mapOrElse(Supplier<U> defaultAction, Function<T, U> isSomeAction) {
        Objects.requireNonNull(defaultAction, "defaultAction cannot be null.");
        Objects.requireNonNull(isSomeAction, "isSomeAction cannot be null.");

        final U defaultActionResult = defaultAction.get();
        Objects.requireNonNull(defaultActionResult, "defaultActionResult cannot be null.");
        final U isSomeActionResult = isSomeAction.apply(this.value);
        Objects.requireNonNull(isSomeActionResult, "isSomeActionResult cannot be null.");
        return isSomeActionResult;
    }

    @Override
    public <U> Option<U> and(Option<U> opt) {
        Objects.requireNonNull(opt, "opt cannot be null");
        return opt;
    }

    @Override
    public <U> Option<U> andThen(Function<T, Option<U>> function) {
        Objects.requireNonNull(function, "function cannot be null.");
        return function.apply(this.value);
    }

    @Override
    public Option<T> or(Option<T> option) {
        Objects.requireNonNull(option, "option cannot be null.");
        return this;
    }

    @Override
    public Option<T> orElse(Supplier<Option<T>> function) {
        Objects.requireNonNull(function, "function cannot be null.");
        return this;
    }

    @Override
    public T getOrInsert(ReferenceHandler<Option<T>> option, T value) {
        Objects.requireNonNull(option, "`option` cannot be null.");
        Objects.requireNonNull(value, "`value` cannot be null.");
        return value;
    }

    @Override
    public T getOrInsertWith(ReferenceHandler<Option<T>> option, Supplier<T> function) {
        Objects.requireNonNull(option, "`option` cannot be null.");
        Objects.requireNonNull(function, "`function` cannot be null.");
        Objects.requireNonNull(function.get(), "`function` result cannot be null.");
        return this.value;
    }

    @Override
    public Option<T> take(ReferenceHandler<Option<T>> option) {
        Objects.requireNonNull(option, "`option` cannot be null.");
        final T containedValue = option.get().unwrap();
        option.set(new None<>());
        return new Some<>(containedValue);
    }

    /**
     * Compares the contained values only.
     * <pre>{@code
     * new Some<>("Hello").equals(new Some<>("Hello")); // true
     * new Some<>("Hello").equals(new Some<>("World!")); // false
     * }</pre>
     * @param anObject The other option to compare.
     * @return {@code true} if {@code option} is equal to the current object, {@code false} otherwise.
     */
    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(Object anObject) {
        if (anObject instanceof Option) {
            final Option<T> option = (Option<T>) anObject;
            return option.isSome() && Objects.equals(this.value, ((Some) option).value);
        }
        return false;
    }
}
