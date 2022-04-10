package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertAllFalse;
import static seedu.address.testutil.Assert.assertAllTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalStrings.NULL_STRING;
import static seedu.address.testutil.TypicalStrings.getInvalidStringsForDescription;
import static seedu.address.testutil.TypicalStrings.getValidStringsForDescription;

import org.junit.jupiter.api.Test;

public class DescriptionTest {
    @Test
    public void constructor_invalidDescription_throwsIllegalArgumentException() {

        // null description: throw NullPointerException
        assertThrows(NullPointerException.class, () -> new Description(NULL_STRING));

        // other cases: throw IllegalArgumentException
        String[] invalidStrings = getInvalidStringsForDescription();
        for (String s : invalidStrings) {
            assertThrows(IllegalArgumentException.class, () -> new Description(s));
        }
    }

    @Test
    public void isValidDescription() {

        // null
        assertFalse(Description.isValidDescription(NULL_STRING));

        // invalid descriptions

        assertAllFalse(Description::isValidDescription, getInvalidStringsForDescription());

        // valid descriptions
        assertAllTrue(Description::isValidDescription, getValidStringsForDescription());
    }
}
