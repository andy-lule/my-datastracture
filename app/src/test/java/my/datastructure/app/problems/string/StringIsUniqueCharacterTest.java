package my.datastructure.app.problems.string;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class StringIsUniqueCharacterTest {

    @ParameterizedTest
    @MethodSource("provideDuplicatedCharactersStrings")
    public void isUniqueWithMap_whenStringHasDuplicatedCharacter_thenReturnFalse(String input) {
        final boolean isUnique = StringIsUniqueCharacter.isUniqueWithMap(input);

        assertThat(isUnique).isFalse();
    }

    @ParameterizedTest
    @MethodSource("provideUniqueCharactersStrings")
    public void isUniqueWithMap_whenStringHasUniqueCharacter_thenReturnTrue(String input) {
        final boolean isUnique = StringIsUniqueCharacter.isUniqueWithMap(input);

        assertThat(isUnique).isTrue();
    }

    @ParameterizedTest
    @MethodSource("provideDuplicatedCharactersStrings")
    public void isUniqueWithoutDataStructure_whenStringHasDuplicatedCharacter_thenReturnFalse(String input) {
        final boolean isUnique = StringIsUniqueCharacter.isUniqueWithoutDataStructure(input);

        assertThat(isUnique).isFalse();
    }

    @ParameterizedTest
    @MethodSource("provideUniqueCharactersStrings")
    public void isUniqueWithoutDataStructure_whenStringHasUniqueCharacter_thenReturnTrue(String input) {
        final boolean isUnique = StringIsUniqueCharacter.isUniqueWithoutDataStructure(input);

        assertThat(isUnique).isTrue();
    }

    private static Stream<String> provideDuplicatedCharactersStrings() {
        return Stream.of("aa", "cdfac", "blttt", "dddd", "abcdefghjklmnzca", "qwertyuiopasdfq");
    }

    private static Stream<String> provideUniqueCharactersStrings() {
        return Stream.of("a", "x", "abc", "qwert", "pozxcvbnm", "qwertyuiop", "zaqwdcrfvtgb", "mnbvcz", "lkha");
    }
}
