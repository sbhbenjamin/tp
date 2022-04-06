package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TagTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Tag(null));
    }

    @Test
    public void constructor_invalidTagName_throwsIllegalArgumentException() {
        String invalidTagName = "";
        assertThrows(IllegalArgumentException.class, () -> new Tag(invalidTagName));
    }

    @Test
    public void isValidTagName() {
        // null tag name
        assertThrows(NullPointerException.class, () -> Tag.isValidTagName(null));

        assertFalse(Tag.isValidTagName("")); // empty string
        assertFalse(Tag.isValidTagName(" ")); // spaces only
        assertFalse(Tag.isValidTagName(" CS3240")); // first character whitespace
        assertFalse(Tag.isValidTagName("ㅁㅂㅇㅈㅊㅋㅌㅍㅎ")); // non-english characters
        assertFalse(Tag.isValidTagName("╲ʕ·ᴥ· ╲ʔ")); // symbols
        assertFalse(Tag.isValidTagName("☝")); // emojis
        assertFalse(Tag.isValidTagName("AI/ML")); // contains punctuations
        assertFalse(Tag.isValidTagName("AI Machine Learning")); // contains spaces
        assertFalse(Tag.isValidTagName("ExtraordinaryMachineLearningAndArtificialIntelligen")); // 51 characters

        // valid tag name
        assertTrue(Tag.isValidTagName("tutorials")); // alphabets only
        assertTrue(Tag.isValidTagName("12345")); // numbers only
        assertTrue(Tag.isValidTagName("cs3240")); // alphanumeric characters
        assertTrue(Tag.isValidTagName("Cs3240")); // with capital letters
        assertTrue(Tag.isValidTagName("ExtraordinaryMachineLearningAndArtificialIntellige")); // 50 characters
    }

}
