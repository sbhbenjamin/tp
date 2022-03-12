package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.CompletionStatus;
import seedu.address.model.task.Deadline;
import seedu.address.model.task.Description;
import seedu.address.model.task.Name;
import seedu.address.model.task.Task;

/**
 * Marks a task identified using it's displayed index from the task list as completed.
 */
public class MarkCommand extends Command {

    public static final String COMMAND_WORD = "mark";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks the task identified by the index number used in the displayed task list as completed.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_MARK_TASK_SUCCESS = "Completed Task: %1$s";
    public static final String MESSAGE_TASK_ALREADY_COMPLETED = "This task is already marked as complete.";

    private final Index targetIndex;

    public MarkCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    /**
     * Creates a marked iteration of the Task provided.
     * @param task task to be copied.
     * @return marked task.
     */
    private Task createMarkedTask(Task task) {
        Name name = task.getName();
        Description description = task.getDescription();
        CompletionStatus completionStatus = new CompletionStatus("true");
        Deadline deadline = task.getDeadline();
        Set<Tag> tags = task.getTags();

        Task markedTask = new Task(name, description, completionStatus, deadline, tags);
        return markedTask;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> lastShownList = model.getFilteredTaskList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }
        Task taskToMark = lastShownList.get(targetIndex.getZeroBased());
        Task markedTask = createMarkedTask(taskToMark);

        if (model.hasTask(markedTask)) {
            throw new CommandException(MESSAGE_TASK_ALREADY_COMPLETED);
        }

        model.markTask(taskToMark, markedTask);
        return new CommandResult(String.format(MESSAGE_MARK_TASK_SUCCESS, taskToMark));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MarkCommand // instanceof handles nulls
                && targetIndex.equals(((MarkCommand) other).targetIndex)); // state check
    }
}
