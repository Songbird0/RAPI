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

import java.util.function.Function;
import java.util.function.Supplier;

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

    /**
     * @return The contained value if the {@code Option} is {@code Some}, throws
     * a {@link RuntimeException} otherwise. Usually, this method shouldn't used. Prefer to use
     * another {@code Option} service to handle the {@code None} case.
     */
    T unwrap();

    /**
     * @param defaultValue The default value to return.
     * @return The contained value if the {@code Option} is {@code Some}, the default value otherwise.
     * @exception NullPointerException If {@code defaultValue} is null.
     */
    T unwrapOr(T defaultValue);

    /**
     * Returns the contained value or computes the lambda {@code expression}.
     * @param expression Lambda to compute.
     * @return The contained value or the lambda result.
     * @exception NullPointerException If {@code expression} is null.
     * @exception NullPointerException If the {@code expression} result is null.<br>
     */
    T unwrapOrElse(Supplier<T> expression);

    /**
     * Maps an {@code `Option<T>`} to {@code `Option<U>`} by applying a function to a contained value.
     * <p><strong>Note</strong>: If {@code Option<T>} is {@code None},
     * {@code map()} will return {@code None} ({@code Option<U>}).</p>
     * @param appliedFunction Function to apply.
     * @param <U> The {@code `Option`} returned type.
     * @return A new {@code `Option`} type.
     * @exception NullPointerException If {@code appliedFunction} is null.
     * @exception NullPointerException If the {@code appliedFunction} result is null.
     */
    <U> Option<U> map(Function<T, U> appliedFunction);
}
