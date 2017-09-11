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
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

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

    }

    @Test
    public void mapOrTest() {

    }

    @Test
    public void mapOrElse() {

    }

    @Test
    public void okOrTest() {

    }

    @Test
    public void okOrElseTest() {

    }

    @Test
    public void iterTest() {

    }

    @Test
    public void andTest() {

    }

    @Test
    public void andThenTest() {

    }

    @Test
    public void orTest() {

    }

    @Test
    public void orElseTest() {

    }

    @Test
    public void getOrInsertTest() {

    }

    @Test
    public void getOrInsertWithTest() {

    }

    @Test
    public void takeTest() {

    }

    @Test
    public void cloneTest() {

    }
}
