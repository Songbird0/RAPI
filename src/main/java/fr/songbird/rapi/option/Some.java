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
public class Some<T> implements Option<T> {

    private final T value;

    public Some(T value) {
        Objects.requireNonNull(value, "value cannot be null.");
        this.value = value;
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
    public boolean equals(Option<T> option) {
        Objects.requireNonNull(option, "option cannot be null.");
        return option.isSome() && Objects.equals(this.value, ((Some) option).value);
    }
}
