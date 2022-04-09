package seedu.address.logic.commands.sort;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class SortOrderTest {

    @Test
    public void isValidSortOrder() {

        // null sort label
        assertThrows(NullPointerException.class, () -> SortOrder.isValidSortOrder(null));

        assertFalse(SortOrder.isValidSortOrder("")); // empty String
        assertFalse(SortOrder.isValidSortOrder(" ")); // spaces only
        assertFalse(SortOrder.isValidSortOrder(" asc")); // first character whitespace
        assertFalse(SortOrder.isValidSortOrder("123")); // digits
        assertFalse(SortOrder.isValidSortOrder("ascending")); // expanded abbreviation of asc
        assertFalse(SortOrder.isValidSortOrder("descending")); // expanded abbreviation of desc
        assertFalse(SortOrder.isValidSortOrder("deadline")); // valid sort key
        assertFalse(SortOrder.isValidSortOrder("name")); // valid sort key
        assertFalse(SortOrder.isValidSortOrder("priority")); // valid sort key

        // valid sort order
        assertTrue(SortOrder.isValidSortOrder("asc"));
        assertTrue(SortOrder.isValidSortOrder("desc"));
    }


    @Test
    public void valueOfLabel_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> SortOrder.valueOfLabel(null));
    }

    @Test
    public void valueOfLabel_invalidSortOrderLabel_throwsIllegalArgumentException() {
        String emptyLabel = "";
        assertThrows(IllegalArgumentException.class, () -> SortOrder.valueOfLabel(emptyLabel));

        String spacedLabel = " ";
        assertThrows(IllegalArgumentException.class, () -> SortOrder.valueOfLabel(spacedLabel));

        String undefinedSortOrderLabel = "highest";
        assertThrows(IllegalArgumentException.class, () -> SortOrder.valueOfLabel(undefinedSortOrderLabel));
    }

    @Test
    public void valueOfLabel_validSortOrderLabel_returnsValidSortOrder() {
        String ascendingLabel = "asc";
        assertEquals(SortOrder.ASCENDING, SortOrder.valueOfLabel(ascendingLabel));

        String descendingLabel = "desc";
        assertEquals(SortOrder.DESCENDING, SortOrder.valueOfLabel(descendingLabel));
    }

    @Test
    public void toString_validSortKey_validLabel() {
        String ascendingExpected = "ascending";
        assertEquals(SortOrder.ASCENDING.toString(), ascendingExpected);

        String descendingExpected = "descending";
        assertEquals(SortOrder.DESCENDING.toString(), descendingExpected);
    }
}
