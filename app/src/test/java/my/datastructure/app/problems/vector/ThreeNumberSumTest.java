package my.datastructure.app.problems.vector;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class ThreeNumberSumTest {

    @ParameterizedTest
    @MethodSource("provideThreeNumberTestCases")
    public void threeNumberSum_ReturnsCorrectedOutput(Integer[] input, int targetSum, List<Integer[]> expectedOutput) {
        final List<Integer[]> actualOutput = ThreeNumberSum.threeNumberSum(input, targetSum);

        assertThat(actualOutput.size()).isEqualTo(expectedOutput.size());
        for (int i = 0; i < expectedOutput.size(); i++) {
            final Integer[] expectedItem = expectedOutput.get(i);
            final Integer[] actualItem = actualOutput.get(i);
            assertThat(expectedItem).containsSequence(actualItem);
        }
    }

    private static Stream<Arguments> provideThreeNumberTestCases() {
        return Stream.of(
                arguments(
                        new Integer[]{-8, -6, 1, 2, 3, 5, 6, 12},
                        0,
                        List.of(new Integer[]{-8, 2, 6}, new Integer[]{-8, 3, 5}, new Integer[]{-6, 1, 5})
                )
        );
    }
}
