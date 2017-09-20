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

    /**
     * Applies a function to the contained value (if any), returns
     * a default value otherwise.
     * @param defaultValue The returned default value if there is no contained
     *                     value.
     * @param appliedFunction The function to apply to the contained value.
     * @param <U> The default value type.
     * @return The {@code appliedFunction} result if the contained value is present,
     * {@code defaultValue} otherwise.
     * @throws NullPointerException If {@code defaultValue} is null.
     * @throws NullPointerException If {@code appliedFunction} is null.
     * @throws NullPointerException If the {@code appliedFunction} result is null.
     */
    <U> U mapOr(U defaultValue, Function<T, U> appliedFunction);

    /**
     * Applies a function to the contained value (if any) or runs a default
     * function otherwise. Example:
     * <pre>{@code
     * final Option<String> s = new Some<>("Hello ");
     * final Supplier<String> defaultAction = () -> "is away!";
     * final Function<String, String> isSomeAction = string -> string + "world!";
     * assertThat(s.mapOrElse(defaultAction, isSomeAction), is("Hello world!"));
     * final Option<String> anotherString = new None<>();
     * assertThat(anotherString.mapOrElse(defaultAction, isSomeAction), is("is away!"));
     * }</pre>
     * @param defaultAction The default function.
     * @param isSomeAction The applied function if there's a contained value.
     * @param <U> The returned type.
     * @return The functions result.
     * @throws NullPointerException If either {@code defaultAction} or {@code isSomeAction}
     * is null.
     * @throws NullPointerException If either {@code defaultAction} or {@code isSomeAction} result
     * is null.
     */
    <U> U mapOrElse(Supplier<U> defaultAction, Function<T, U> isSomeAction);

    /**
     * Returns {@code opt} if there's a contained value, {@code None} otherwise.
     * @param opt The optional container to return if there's a contained value.
     * @param <U> The returned value.
     * @return {@code opt} if there's a contained value, {@code None} otherwise.
     * @throws NullPointerException If {@code opt} is null.
     */
    <U> Option<U> and(Option<U> opt);

    /**
     * Performs {@code function} over the contained value (if any) and returns the result.
     * Returns {@code None} otherwise.
     * @param function Function to apply.
     * @param <U> The returned type.
     * @return The {@code function} result if there's a contained value, {@code None} otherwise.
     * @throws NullPointerException If {@code function} is null.
     * @throws NullPointerException If the {@code function} result is null.
     */
    <U> Option<U> andThen(Function<T, Option<U>> function);

    /**
     * @param option The default option.
     * @return The current container if there's a contained value, {@code option} otherwise.
     * @throws NullPointerException If {@code option} is null.
     */
    Option<T> or(Option<T> option);

    /**
     * @param function The function to apply.
     * @return The option if it contains a value, calls {@code function} and returns the result otherwise.
     * @throws NullPointerException If {@code function} is null.
     * @throws NullPointerException If the {@code function} result is null.
     */
    Option<T> orElse(Supplier<Option<T>> function);

    /**
     * Inserts {@code value} into the option if it's {@link None}, then returns a reference to the contained
     * value.
     * @param option The option to convert to {@code Some}.
     * @param value The value to insert into {@code option}.
     * @return The inserted value in {@code option}.
     */
    T getOrInsert(ReferenceHandler<Option<T>> option, T value);

    /**
     * Inserts a value computed from {@code function} into the option if it's {@link None} then returns a reference to
     * the contained value.
     * @param option The option to convert to {@code Some}.
     * @param function The function from which we get the computed value.
     * @return The new contained value if container is {@link None}, the current contained value otherwise.
     */
    T getOrInsertWith(ReferenceHandler<Option<T>> option, Supplier<T> function);
}
