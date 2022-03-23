package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_TASKS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalTasks.CS2103T_PROJECT;
import static seedu.address.testutil.TypicalTasks.CS2105_FINALS;
import static seedu.address.testutil.TypicalTasks.CS2105_MIDTERM;
import static seedu.address.testutil.TypicalTasks.MEET_ALICE;
import static seedu.address.testutil.TypicalTasks.getTypicalTaskList;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.tag.TagContainsKeywordsPredicate;
import seedu.address.model.task.NameContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalTaskList(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalTaskList(), new UserPrefs());

    @Test
    public void equals() {
        NameContainsKeywordsPredicate firstNamePredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("firstName"));
        NameContainsKeywordsPredicate secondNamePredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("secondName"));
        TagContainsKeywordsPredicate firstTagPredicate =
                new TagContainsKeywordsPredicate(Collections.singletonList("firstTag"));
        TagContainsKeywordsPredicate secondTagPredicate =
                new TagContainsKeywordsPredicate(Collections.singletonList("secondTag"));

        FindCommand findFirstCommand = new FindCommand(firstNamePredicate, firstTagPredicate);
        FindCommand findSecondCommand = new FindCommand(secondNamePredicate, secondTagPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstNamePredicate, firstTagPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different task -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noTaskFound() {
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 0);
        NameContainsKeywordsPredicate namePredicate = prepareNamePredicate(" ");
        TagContainsKeywordsPredicate tagPredicate = prepareTagPredicate(" ");
        FindCommand command = new FindCommand(namePredicate, tagPredicate);
        expectedModel.updateFilteredTaskList(namePredicate.or(tagPredicate));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredTaskList());
    }

    @Test
    public void execute_multipleNameKeywordsAndZeroTagKeywords_multipleTasksFound() {
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 2);
        NameContainsKeywordsPredicate namePredicate = prepareNamePredicate("Alice Project");
        TagContainsKeywordsPredicate tagPredicate = prepareTagPredicate(" ");
        FindCommand command = new FindCommand(namePredicate, tagPredicate);
        expectedModel.updateFilteredTaskList(namePredicate.or(tagPredicate));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CS2103T_PROJECT, MEET_ALICE), model.getFilteredTaskList());
    }

    @Test
    public void execute_zeroNameKeywordsAndMultipleTagKeywords_multipleTasksFound() {
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 3);
        NameContainsKeywordsPredicate namePredicate = prepareNamePredicate(" ");
        TagContainsKeywordsPredicate tagPredicate = prepareTagPredicate("exam CS2103T");
        FindCommand command = new FindCommand(namePredicate, tagPredicate);
        expectedModel.updateFilteredTaskList(namePredicate.or(tagPredicate));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CS2103T_PROJECT, CS2105_FINALS, CS2105_MIDTERM), model.getFilteredTaskList());
    }

    @Test
    public void execute_multipleNameKeywordsAndMultipleTagKeywords_multipleTasksFound() {
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 4);
        NameContainsKeywordsPredicate namePredicate = prepareNamePredicate("Alice Project");
        TagContainsKeywordsPredicate tagPredicate = prepareTagPredicate("exam cs2103t");
        FindCommand command = new FindCommand(namePredicate, tagPredicate);
        expectedModel.updateFilteredTaskList(namePredicate.or(tagPredicate));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CS2103T_PROJECT, CS2105_FINALS, CS2105_MIDTERM, MEET_ALICE),
                model.getFilteredTaskList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate prepareNamePredicate(String userInput) {
        return new NameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code TagContainsKeywordsPredicate}.
     */
    private TagContainsKeywordsPredicate prepareTagPredicate(String userInput) {
        return new TagContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
