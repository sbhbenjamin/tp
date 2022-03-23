package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.TaskBuilder;

public class NameContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        NameContainsKeywordsPredicate firstPredicate = new NameContainsKeywordsPredicate(firstPredicateKeywordList);
        NameContainsKeywordsPredicate secondPredicate = new NameContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        NameContainsKeywordsPredicate firstPredicateCopy =
                new NameContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different task -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(
                Collections.singletonList("CS2103T"));
        assertTrue(predicate.test(new TaskBuilder().withName("CS2103T tutorial").build()));

        // Multiple keywords
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("CS2103T", "Tutorial"));
        assertTrue(predicate.test(new TaskBuilder().withName("CS2103T Tutorial").build()));

        // Only one matching keyword
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("CS2103T", "CS3240"));
        assertTrue(predicate.test(new TaskBuilder().withName("CS3240 Finals").build()));

        // Mixed-case keywords
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("TutoriaL", "cs3240"));
        assertTrue(predicate.test(new TaskBuilder().withName("CS3240 tutorial").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new TaskBuilder().withName("CS3240").build()));

        // Non-matching keyword
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("CS3240"));
        assertFalse(predicate.test(new TaskBuilder().withName("CS2103T Tutorial").build()));

        // Keywords match description, completion status, deadline and tags, but does not match name
        predicate = new NameContainsKeywordsPredicate(Arrays
                .asList("G1", "true", "2022-10-22", "CS2103T"));
        assertFalse(predicate.test(new TaskBuilder()
                .withName("Complete Tutorial")
                .withDescription("Finish 3 parts of G1")
                .withCompletionStatus("true")
                .withDeadline("2022-10-22")
                .withTags("CS2103T").build()));
    }
}
