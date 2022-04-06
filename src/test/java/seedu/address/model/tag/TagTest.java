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
    public void constructor_invalidTagTag_throwsIllegalArgumentException() {
        String invalidTagTag = "";
        assertThrows(IllegalArgumentException.class, () -> new Tag(invalidTagTag));
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
        assertFalse(Tag.isValidTagName("Lorem ipsum dolor sit amet, consectetur adipiscinga")); // 51 characters

        // valid name
        assertTrue(Tag.isValidTagName("tutorials")); // alphabets only
        assertTrue(Tag.isValidTagName("12345")); // numbers only
        assertTrue(Tag.isValidTagName("cs3240")); // alphanumeric characters
        assertTrue(Tag.isValidTagName("Cs3240")); // with capital letters
        // contains non-alphanumeric characters
        assertTrue(Tag.isValidTagName("AI / Machine Learning"));
        assertTrue(Tag.isValidTagName("Lorem ipsum dolor sit amet, consectetur adipiscing")); // 50 characters
    }

}
