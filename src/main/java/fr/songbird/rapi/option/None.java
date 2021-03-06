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
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;
import java.util.function.Supplier;

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

    @Override
    public T unwrapOr(T defaultValue) {
        Objects.requireNonNull(defaultValue, "defaultValue cannot be null.");
        return defaultValue;
    }

    @Override
    public T unwrapOrElse(Supplier<T> expression) {
        Objects.requireNonNull(expression, "`expression` cannot be null");
        final T expressionResult = expression.get();
        Objects.requireNonNull(expressionResult, "The `expression` result cannot be null.");
        return expressionResult;
    }

    @Override
    public <U> Option<U> map(Function<T, U> appliedFunction) {
        Objects.requireNonNull(appliedFunction, "`appliedFunction` cannot be null.");
        return new None<>();
    }

    @Override
    public <U> U mapOr(U defaultValue, Function<T, U> appliedFunction) {
        Objects.requireNonNull(defaultValue, "defaultValue cannot be null.");
        Objects.requireNonNull(appliedFunction, "appliedFunction cannot be null.");
        return defaultValue;
    }

    @Override
    public <U> U mapOrElse(Supplier<U> defaultAction, Function<T, U> isSomeAction) {
        Objects.requireNonNull(defaultAction, "defaultAction cannot be null.");
        Objects.requireNonNull(isSomeAction, "isSomeAction cannot be null.");

        final U defaultActionResult = defaultAction.get();
        Objects.requireNonNull(defaultActionResult, "result cannot be null.");
        return defaultActionResult;
    }

    @Override
    public <U> Option<U> and(Option<U> opt) {
        Objects.requireNonNull(opt, "opt cannot be null.");
        return new None<>();
    }

    @Override
    public <U> Option<U> andThen(Function<T, Option<U>> function) {
        Objects.requireNonNull(function, "function cannot be null.");
        return new None<>();
    }

    @Override
    public Option<T> or(Option<T> option) {
        return Objects.requireNonNull(option, "option cannot be null.");
    }

    @Override
    public Option<T> orElse(Supplier<Option<T>> function) {
        Objects.requireNonNull(function, "function cannot be null.");
        return function.get();
    }

    @Override
    public T getOrInsert(ReferenceHandler<Option<T>> option, T value) {
        Objects.requireNonNull(option, "`option` cannot be null.");
        Objects.requireNonNull(value, "`value` cannot be null.");
        option.set(new Some<>(value));
        return value;
    }

    @Override
    public T getOrInsert(AtomicReference<Option<T>> option, T value) {
        Objects.requireNonNull(option, "`option` cannot be null.");
        Objects.requireNonNull(value, "`value` cannot be null.");
        option.set(new Some<>(value));
        return value;
    }

    @Override
    public T getOrInsertWith(ReferenceHandler<Option<T>> option, Supplier<T> function) {
        Objects.requireNonNull(option, "`option` cannot be null.");
        Objects.requireNonNull(function, "`function` cannot be null.");
        final T functionResult = function.get();
        Objects.requireNonNull(functionResult, "`function` result cannot be null.");
        option.set(new Some<>(functionResult));
        return functionResult;
    }

    @Override
    public T getOrInsertWith(AtomicReference<Option<T>> option, Supplier<T> function) {
        Objects.requireNonNull(option, "`option` cannot be null.");
        Objects.requireNonNull(function, "`function` cannot be null.");
        final T functionResult = function.get();
        Objects.requireNonNull(functionResult, "`function` result cannot be null.");
        option.set(new Some<>(functionResult));
        return functionResult;
    }

    @Override
    public Option<T> take(ReferenceHandler<Option<T>> option) {
        Objects.requireNonNull(option, "`option` cannot be null.");
        return this; // `option` should be `this`, so we return `this` immediately.
    }

    @Override
    public Option<T> take(AtomicReference<Option<T>> option) {
        Objects.requireNonNull(option, "`option` cannot be null.");
        return this; // `option` should be `this`, so we return `this` immediately.
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
            return option.isNone();
        }
        return false;
    }
}
