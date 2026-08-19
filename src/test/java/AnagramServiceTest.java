import org.beyonnex.AnagramService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class AnagramServiceTest {

    private AnagramService anagramService;

    @BeforeEach
    public void setup() {
        anagramService = new AnagramService();
        anagramService.clearAnagramIndex();
    }

    static Stream<Arguments> anagramProvider() {
        return Stream.of(
                Arguments.of("listen", "silent", true),
                Arguments.of("listen", "sillent", false),
                Arguments.of("McDonald's restaurants", "Uncle Sam's standard rot", true),
                Arguments.of("abc", "bcd", false)
        );
    }

    @ParameterizedTest
    @MethodSource("anagramProvider")
    public void should_check_for_anagram(final String input1, final String input2, final boolean expected) {
        boolean result = anagramService.runAnagramCheck(input1, input2);
        assertEquals(expected, result);
    }

    @Test
    public void should_throw_exception_for_invalid_input() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> anagramService.runAnagramCheck("1abc", "abc!")
        );
        assertEquals("Input must not contain digits or special characters.", exception.getMessage());
    }

    @Test
    public void should_list_previous_anagrams() {
        // given
        final String input = "vile";
        final Map<String, List<String>> anagramIndex = new HashMap<>();
        anagramIndex.put("eilv", List.of("live", "evil"));
        anagramService.setAnagramIndex(anagramIndex);

        // when
        final List<String> result = anagramService.runAnagramLookup(input);

        // then
        final List<String> expectedResult = List.of("live", "evil");
        assertEquals(expectedResult, result);
    }

    @Test
    public void should_return_empty_list_as_previous_anagrams() {
        // given
        final String input = "vile";
        final Map<String, List<String>> anagramIndex = new HashMap<>();
        anagramService.setAnagramIndex(anagramIndex);

        // when
        final List<String> result = anagramService.runAnagramLookup(input);

        // then
        final List<String> expectedResult = Collections.emptyList();
        assertEquals(expectedResult, result);
    }

}
