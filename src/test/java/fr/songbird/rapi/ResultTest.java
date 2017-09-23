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

import fr.songbird.rapi.result.Err;
import fr.songbird.rapi.result.Ok;
import fr.songbird.rapi.result.Result;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;

/**
 * @since 08/09/17
 */
public class ResultTest {

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Test
    public void isOkTest() {
        final Result<String, String> result = new Ok<>("It's ok!");
        assertThat(result.isOk(), is(true));
        final Result<String, String> anotherResult = new Err<>("OH NO!");
        assertThat(anotherResult.isOk(), is(not(true)));
    }

    @Test
    public void isErrTest() {

    }

    @Test
    public void okTest() {

    }

    @Test
    public void errTest() {

    }

    @Test
    public void mapTest() {

    }

    @Test
    public void mapErrTest() {

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
    public void unwrapOrTest() {

    }

    @Test
    public void unwrapOrElseTest() {

    }

    @Test
    public void unwrapTest() {

    }

    @Test
    public void expectTest() {

    }

    @Test
    public void unwrapErrTest() {

    }

    @Test
    public void expectErrTest() {

    }

    @Test
    public void cloneTest() {

    }
}
