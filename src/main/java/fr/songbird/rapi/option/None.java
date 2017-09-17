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

import java.util.Objects;
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
    public boolean equals(Option<T> option) {
        Objects.requireNonNull(option, "option cannot be null.");
        return option.isNone();
    }
}
