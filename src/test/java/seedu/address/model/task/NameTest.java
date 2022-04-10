package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertAllFalse;
import static seedu.address.testutil.Assert.assertAllTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalStrings.NULL_STRING;
import static seedu.address.testutil.TypicalStrings.getInvalidStringsForName;
import static seedu.address.testutil.TypicalStrings.getValidStringsForName;

import org.junit.jupiter.api.Test;

public class NameTest {

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {

        // null name: throw NullPointerException
        assertThrows(NullPointerException.class, () -> new Name(NULL_STRING));

        // other cases: throw IllegalArgumentException
        String[] invalidStrings = getInvalidStringsForName();
        for (String s : invalidStrings) {
            assertThrows(IllegalArgumentException.class, () -> new Name(s));
        }
    }

    @Test
    public void isValidName() {

        // null
        assertFalse(Name.isValidName(NULL_STRING));

        // invalid names

        assertAllFalse(Name::isValidName, getInvalidStringsForName());

        // valid names
        assertAllTrue(Name::isValidName, getValidStringsForName());
    }
}
