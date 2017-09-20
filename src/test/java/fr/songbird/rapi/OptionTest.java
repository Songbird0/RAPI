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
package fr.songbird.rapi;

import fr.songbird.rapi.option.None;
import fr.songbird.rapi.option.Option;
import fr.songbird.rapi.option.Some;
import fr.songbird.rapi.ref.ReferenceHandler;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.function.Function;
import java.util.function.Supplier;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * @since 08/09/17
 */
public class OptionTest {

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Test
    public void isSomeTest() {
        final Option<Integer> option = new Some<>(117);
        assertThat(option.isSome(), is(true));
        final Option<Integer> option1 = new None<>();
        assertThat(option1.isSome(), is(false));
    }

    @Test
    public void isNoneTest() {
        final Option<Integer> option = new Some<>(117);
        assertThat(option.isNone(), is(false));
        final Option<Integer> option1 = new None<>();
        assertThat(option1.isNone(), is(true));
    }

    @Test
    public void expectTest() {
        expectedException.expect(RuntimeException.class);
        expectedException.expectMessage("OH NO!");
        final Option<Integer> option = new Some<>(117);
        assertThat(option.expect("OH NO!"), is(117));
        final Option<Integer> option1 = new None<>();
        option1.expect("OH NO!");
    }

    @Test
    public void unwrapTest() {
        expectedException.expect(RuntimeException.class);
        expectedException.expectMessage("called `"
                + Option.class.getName() + ".unwrap()` on a `None` object");
        final Option<Integer> option = new Some<>(117);
        assertThat(option.unwrap(), is(117));
        final Option<Integer> option1 = new None<>();
        option1.unwrap();
    }

    @Test
    public void unwrapOrTest() {
        final Option<Integer> option = new Some<>(117);
        assertThat(option.unwrapOr(259), is(117));
        final Option<Integer> option1 = new None<>();
        assertThat(option1.unwrapOr(259), is(259));
    }

    @Test
    public void unwrapOrElseTest() {
        final Option<Integer> option = new Some<>(117);
        final Integer defaultValue = 2;
        assertThat(option.unwrapOrElse(() -> defaultValue * 2), is(117));
        final Option<Integer> option1 = new None<>();
        assertThat(option1.unwrapOrElse(() -> defaultValue * 2), is(4));
    }

    @Test
    public void mapTest() {
        expectedException.expect(RuntimeException.class);
        expectedException.expectMessage("called `"
                + Option.class.getName() + ".unwrap()` on a `None` object");
        final Option<String> soption = new Some<>("Hello there!");
        final Option<Integer> ioption = soption.map(String::length);
        assertThat(ioption instanceof Some, is(true));
        assertThat(ioption.unwrap(), is(12));
        final Option<String> soption1 = new None<>();
        final Option<Integer> ioption1 = soption1.map(String::length);
        assertThat(ioption1 instanceof None, is(true));
        ioption1.unwrap();
    }

    @Test
    public void mapOrTest() {
        final Option<String> option = new Some<>("Java is awesome!");
        final String containedValue = option.mapOr("I'm the default value!", yourString -> yourString + "\nI agree! :D");
        assertThat(containedValue, is("Java is awesome!\nI agree! :D"));
        final Option<String> option1 = new None<>();
        final String returnedValue = option1.mapOr("I'm the default value!", yourString -> yourString + "\nI agree! :D");
        assertThat(returnedValue, is("I'm the default value!"));
    }

    @Test
    public void mapOrElse() {
        final Option<String> s = new Some<>("Hello ");
        final Supplier<String> defaultAction = () -> "is away!";
        final Function<String, String> isSomeAction = string -> string + "world!";
        assertThat(s.mapOrElse(defaultAction, isSomeAction), is("Hello world!"));
        final Option<String> anotherString = new None<>();
        assertThat(anotherString.mapOrElse(defaultAction, isSomeAction), is("is away!"));
    }

    @Test
    public void okOrTest() {
        // FIXME Result class not yet implemented.
    }

    @Test
    public void okOrElseTest() {
        // FIXME Result class not yet implemented.
    }

    @Test
    public void andTest() {
        final String CONST = "There was a contained value!";
        final Option<Integer> i = new None<>();
        final Option<String> newOpt = i.and(new Some<>(CONST));
        assertThat(newOpt instanceof None, is(true));
        final Option<Integer> anotherInteger = new Some<>(99);
        final Option<String> _newOpt = anotherInteger.and(new Some<>(CONST));
        assertThat(_newOpt instanceof Some, is(true));
    }

    @Test
    public void andThenTest() {
        final Function<Integer, Option<Integer>> square = integer -> new Some<>(integer * integer);
        final Function<Integer, Option<Integer>> nope = integer -> new None<>();

        assertThat(new Some<>(2).andThen(square).andThen(square), is(equalTo(new Some<>(16))));
        assertThat(new Some<>(2).andThen(square).andThen(nope), is(equalTo(new None<>())));
        assertThat(new Some<>(2).andThen(nope).andThen(square), is(equalTo(new None<>())));
        assertThat(new None<Integer>().andThen(square).andThen(square), is(equalTo(new None<>())));
        assertThat(new Some<>(2).andThen(square).andThen(integer -> new Some<>("New type!")), is(equalTo(new Some<>("New type!"))));
    }

    @Test
    public void orTest() {
        final Option<String> a = new Some<>("a");
        final Option<String> b = new None<>();
        assertThat(a.or(b), is(equalTo(new Some<>("a"))));

        final Option<String> c = new Some<>("c");
        final Option<String> d = new Some<>("d");
        assertThat(c.or(d), is(equalTo(new Some<>("c"))));

        final Option<String> e = new None<>();
        final Option<String> f = new Some<>("f");
        assertThat(e.or(f), is(equalTo(new Some<>("f"))));
    }

    @Test
    public void orElseTest() {
        final Supplier<Option<String>> nobody = None::new;
        final Supplier<Option<String>> leon = () -> new Some<>("Leon");

        assertThat(new Some<>("August").orElse(leon), is(equalTo(new Some<>("August"))));
        assertThat(new None<String>().orElse(leon), is(equalTo(new Some<>("Leon"))));
        assertThat(new None<String>().orElse(nobody), is(equalTo(new None<String>())));
    }

    @Test
    public void getOrInsertTest() {
        ReferenceHandler<Option<String>> noneOption = new ReferenceHandler<>(new None<>());
        final String foo = noneOption.get().getOrInsert(noneOption, "foo");
        assertThat(foo, is(equalTo("foo")));
        assertThat(noneOption.get().getClass(), is(equalTo(Some.class)));
    }

    @Test
    public void getOrInsertWithTest() {
        final ReferenceHandler<Option<String>> noneOption = new ReferenceHandler<>(new None<>());
        final String foo = noneOption.get().getOrInsertWith(noneOption, () -> "foo");
        assertThat(foo, is(equalTo("foo")));
        assertThat(noneOption.get().getClass(), is(equalTo(Some.class)));
    }

    @Test
    public void takeTest() {
        final ReferenceHandler<Option<Integer>> integer = new ReferenceHandler<>(new Some<>(117));
        final Option<Integer> anotherInteger = integer.get().take(integer);
        assertThat(integer.get(), is(equalTo(new None<>())));
        assertThat(anotherInteger, is(equalTo(new Some<>(117))));
        final ReferenceHandler<Option<Integer>> x = new ReferenceHandler<>(new None<>());
        assertThat(x.get().take(x), is(equalTo(new None<>())));
        assertThat(x.get(), is(equalTo(new None<>())));
    }

    @Test
    public void cloneTest() {
        expectedException.expect(RuntimeException.class);
        expectedException.expectMessage("`option` is none. It does not contain value to copy.");
        final Option<String> foo = new Some<>("foo");
        final Option<String> fooBrother = new Some<>(foo);
        assertThat(foo != fooBrother, is(true));
        assertThat(foo, is(equalTo(fooBrother)));
        final Option<String> bar = new None<>();
        final Option<String> barBrother = new Some<>(bar); // error
    }

    @Test
    public void useCase1() {
        Integer foo = new Some<>(42)
                .map(x -> x * 2)
                .or(new Some<>(68))
                .map(x -> x + 10)
                .unwrap();
        assertThat(foo, is(equalTo(94)));
    }

    @Test
    public void useCase2() {
        Integer foo = new None<Integer>()
                .map(x -> x * 2)
                .or(new Some<>(68))
                .map(x -> x + 10)
                .unwrap();
        assertThat(foo, is(equalTo(78)));
    }
}
