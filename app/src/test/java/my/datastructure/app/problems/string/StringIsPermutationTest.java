package my.datastructure.app.problems.string;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class StringIsPermutationTest {

    @ParameterizedTest
    @MethodSource("providePermutationValues")
    public void isPermutation_whenTwoStringIsPermutation_ThenReturnsTrue(String a1, String a2) {
        final boolean isPermutation = StringIsPermutation.isPermutation(a1, a2);

        assertThat(isPermutation).isTrue();
    }

    private static Stream<Arguments> providePermutationValues() {
        return Stream.of(
                arguments("abc", "cba"),
                arguments("tla", "cbaweraltfadsf"),
                arguments("poop", "poop"),
                arguments("poopf", "aafpoop"),
                arguments("f", "aapoopf"),
                arguments("ff", "aaffpoopff"),
                arguments("ffa", "aaffpoopffllffa"),
                arguments("popo", "opop"),
                arguments("janny", "ynnaj"),
                arguments("ynnajpooqwerqwer", "janny"),
                arguments("talahanatayama", "atana")
        );
    }
}
