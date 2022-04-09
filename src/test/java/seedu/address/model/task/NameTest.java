package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertAllFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TestUtil.EMOJI_STRING;
import static seedu.address.testutil.TestUtil.EMPTY_STRING;
import static seedu.address.testutil.TestUtil.LEADING_WHITESPACE_STRING;
import static seedu.address.testutil.TestUtil.NON_ENGLISH_STRING;
import static seedu.address.testutil.TestUtil.PUNCTUATION_STRING;
import static seedu.address.testutil.TestUtil.SYMBOLS_STRING;
import static seedu.address.testutil.TestUtil.WHITESPACES_ONLY_STRING;

import org.junit.jupiter.api.Test;

public class NameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Name(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new Name(invalidName));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> Name.isValidName(null));

        // invalid name
        String veryLongName = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, "
                + "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis "
                + "nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure "
                + "dolor em";
        assertAllFalse(Name::isValidName, veryLongName, EMPTY_STRING, WHITESPACES_ONLY_STRING, EMOJI_STRING,
                SYMBOLS_STRING, PUNCTUATION_STRING, NON_ENGLISH_STRING, LEADING_WHITESPACE_STRING);

        // valid name
        assertTrue(Name.isValidName("complete tutorials")); // alphabets only
        assertTrue(Name.isValidName("12345")); // numbers only
        assertTrue(Name.isValidName("complete cs3240 milestones")); // alphanumeric characters
        assertTrue(Name.isValidName("Complete Tutorials")); // with capital letters
        // contains non-alphanumeric characters
        assertTrue(Name.isValidName("Complete CS3240 Milestones, Tutorials and Lectures"));
        assertTrue(Name.isValidName("Lorem ipsum dolor sit amet, consectetur adipiscing elit, "
                + "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis "
                + "nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure "
                + "dolor e")); // 255 characters
    }
}
