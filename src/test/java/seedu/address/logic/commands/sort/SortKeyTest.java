package seedu.address.logic.commands.sort;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class SortKeyTest {

    @Test
    public void isValidSortKey() {

        // null sort label
        assertThrows(NullPointerException.class, () -> SortKey.isValidSortKey(null));

        assertFalse(SortKey.isValidSortKey("")); // empty String
        assertFalse(SortKey.isValidSortKey(" ")); // spaces only
        assertFalse(SortKey.isValidSortKey(" deadline")); // first character whitespace
        assertFalse(SortKey.isValidSortKey("123")); // digits
        assertFalse(SortKey.isValidSortKey("asc")); // valid sort order
        assertFalse(SortKey.isValidSortKey("desc")); // valid sort order
        assertFalse(SortKey.isValidSortKey("dl")); // abbreviation of deadline
        assertFalse(SortKey.isValidSortKey("p")); // abbreviation of priority
        assertFalse(SortKey.isValidSortKey("n")); // abbreviation of name

        // valid sort key
        assertTrue(SortKey.isValidSortKey("deadline"));
        assertTrue(SortKey.isValidSortKey("name"));
        assertTrue(SortKey.isValidSortKey("priority"));
    }


    @Test
    public void valueOfLabel_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> SortKey.valueOfLabel(null));
    }

    @Test
    public void valueOfLabel_invalidSortKeyLabel_throwsIllegalArgumentException() {
        String emptyLabel = "";
        assertThrows(IllegalArgumentException.class, () -> SortKey.valueOfLabel(emptyLabel));

        String spacedLabel = " ";
        assertThrows(IllegalArgumentException.class, () -> SortKey.valueOfLabel(spacedLabel));

        String undefinedSortKeyLabel = "description";
        assertThrows(IllegalArgumentException.class, () -> SortKey.valueOfLabel(undefinedSortKeyLabel));
    }

    @Test
    public void valueOfLabel_validSortKeyLabel_returnsValidSortKey() {
        String deadlineLabel = "deadline";
        assertEquals(SortKey.DEADLINE, SortKey.valueOfLabel(deadlineLabel));

        String nameLabel = "name";
        assertEquals(SortKey.NAME, SortKey.valueOfLabel(nameLabel));

        String priorityLabel = "priority";
        assertEquals(SortKey.PRIORITY, SortKey.valueOfLabel(priorityLabel));
    }

    @Test
    public void toString_validSortKey_validLabel() {
        String deadlineExpected = "deadline";
        assertEquals(SortKey.DEADLINE.toString(), deadlineExpected);

        String nameExpected = "name";
        assertEquals(SortKey.NAME.toString(), nameExpected);

        String priorityExpected = "priority";
        assertEquals(SortKey.PRIORITY.toString(), priorityExpected);
    }
}
