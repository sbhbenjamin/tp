package seedu.address.commons.core.keyword;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertAllFalse;
import static seedu.address.testutil.Assert.assertAllThrows;
import static seedu.address.testutil.Assert.assertAllTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalStrings.LONG_STRING_50_CHAR;
import static seedu.address.testutil.TypicalStrings.LONG_STRING_63_CHAR;
import static seedu.address.testutil.TypicalStrings.NULL_STRING;
import static seedu.address.testutil.TypicalStrings.getInvalidStringsForKeyword;
import static seedu.address.testutil.TypicalStrings.getValidStringsForKeyword;

import org.junit.jupiter.api.Test;



public class KeywordTest {

    @Test
    public void constructor_nullDescription_throwsNullPointerException() {
        // null case
        assertThrows(NullPointerException.class, () -> new Keyword(NULL_STRING));
    }

    @Test
    public void constructor_invalidKeyword_throwsIllegalArgumentException() {
        String[] invalidKeywords = getInvalidStringsForKeyword();
        assertAllThrows(IllegalArgumentException.class, Keyword::new, invalidKeywords);
    }

    @Test
    public void isValidDeadline() {

        // null case
        assertAllFalse(Keyword::isValidKeyword, NULL_STRING);

        // invalid keywords
        assertAllFalse(Keyword::isValidKeyword, getInvalidStringsForKeyword());

        // valid keywords
        assertAllTrue(Keyword::isValidKeyword, getValidStringsForKeyword());
    }

    @Test
    public void equals() {

        Keyword firstKeyword = new Keyword(LONG_STRING_63_CHAR);
        Keyword secondKeyword = new Keyword(LONG_STRING_50_CHAR);

        // same object -> returns true
        assertTrue(firstKeyword.equals(firstKeyword));

        // same values -> returns true
        Keyword firstKeywordCopy = new Keyword(LONG_STRING_63_CHAR);
        assertTrue(firstKeyword.equals(firstKeywordCopy));

        // different types -> returns false
        assertFalse(firstKeyword.equals(1));

        // null -> returns false
        assertFalse(firstKeyword.equals(null));

        // different keyword -> returns false
        assertFalse(firstKeyword.equals(secondKeyword));
    }

}
