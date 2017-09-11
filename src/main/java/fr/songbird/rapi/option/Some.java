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
}
