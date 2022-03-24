package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPLETION_STATUS_TRUE;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showTaskAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_TASK;
import static seedu.address.testutil.TypicalTasks.getTypicalTaskList;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.task.Task;
import seedu.address.testutil.TaskBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code MarkCommand}.
 */
public class MarkCommandTest {

    private Model model = new ModelManager(getTypicalTaskList(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Task taskToMark = model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        Task markedTask = new TaskBuilder(taskToMark).withCompletionStatus(VALID_COMPLETION_STATUS_TRUE).build();
        MarkCommand markCommand = new MarkCommand(INDEX_FIRST_TASK);

        String expectedMessage = String.format(MarkCommand.MESSAGE_MARK_TASK_SUCCESS, markedTask);

        ModelManager expectedModel = new ModelManager(model.getTaskList(), new UserPrefs());
        expectedModel.strictSetTask(model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased()), markedTask);

        assertCommandSuccess(markCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTaskList().size() + 1);
        MarkCommand markCommand = new MarkCommand(outOfBoundIndex);

        assertCommandFailure(markCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validCompletedTaskUnfilteredList_throwsCommandException() {
        // INDEX SECOND TASK: default completion status set to true
        Task taskToMark = model.getFilteredTaskList().get(INDEX_SECOND_TASK.getZeroBased());
        assertEquals(taskToMark.getCompletionStatus().value, "true");

        MarkCommand markCommand = new MarkCommand(INDEX_SECOND_TASK);

        assertCommandFailure(markCommand, model, MarkCommand.MESSAGE_TASK_ALREADY_COMPLETED);
    }


    @Test
    public void execute_validIndexFilteredList_success() {
        showTaskAtIndex(model, INDEX_FIRST_TASK);

        Task taskToMark = model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        Task markedTask = new TaskBuilder(taskToMark).withCompletionStatus(VALID_COMPLETION_STATUS_TRUE).build();
        MarkCommand markCommand = new MarkCommand(INDEX_FIRST_TASK);

        String expectedMessage = String.format(MarkCommand.MESSAGE_MARK_TASK_SUCCESS, markedTask);

        ModelManager expectedModel = new ModelManager(model.getTaskList(), new UserPrefs());
        expectedModel.strictSetTask(model.getFilteredTaskList().get(0), markedTask);

        assertCommandSuccess(markCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showTaskAtIndex(model, INDEX_FIRST_TASK);

        Index outOfBoundIndex = INDEX_SECOND_TASK;
        // ensures that outOfBoundIndex is still in bounds of task list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getTaskList().getTaskList().size());

        MarkCommand markCommand = new MarkCommand(outOfBoundIndex);

        assertCommandFailure(markCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }


    @Test
    public void equals() {
        MarkCommand markFirstCommand = new MarkCommand(INDEX_FIRST_TASK);
        MarkCommand markSecondCommand = new MarkCommand(INDEX_SECOND_TASK);

        // same object -> returns true
        assertTrue(markFirstCommand.equals(markFirstCommand));

        // same values -> returns true
        MarkCommand markFirstCommandCopy = new MarkCommand(INDEX_FIRST_TASK);
        assertTrue(markFirstCommand.equals(markFirstCommandCopy));

        // different types -> returns false
        assertFalse(markFirstCommand.equals(1));

        // null -> returns false
        assertFalse(markFirstCommand.equals(null));

        // different task -> returns false
        assertFalse(markFirstCommand.equals(markSecondCommand));
    }

}
