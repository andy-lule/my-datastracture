package my.datastructure.app.problems.string;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.assertj.core.api.Assertions.*;

public class URLifyTest {

    @ParameterizedTest
    @MethodSource("provideURLifiedTestData")
    public void urlify_whenStringHasSpace_thenStringShouldBeEncodedAsExpected(String input, String expectedEncoded) {
        String actualEncoded = URLify.urlify(input);

        assertThat(actualEncoded).isEqualTo(expectedEncoded);
    }

    private static Stream<Arguments> provideURLifiedTestData() {
        return Stream.of(
                arguments("Hello world!", "Hello%world!"),
                arguments("space space", "space%space"),
                arguments("Who I am  ", "Who%I%am"),
                arguments(" Talaha ", "%Talaha")
        );
    }
}
