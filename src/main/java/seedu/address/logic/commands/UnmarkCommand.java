package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TASKS;

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
 * Marks a task identified using it's displayed index from the task list as uncompleted.
 */
public class UnmarkCommand extends Command {

    public static final String COMMAND_WORD = "unmark";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks the task identified by the index number used in the displayed task list as uncompleted.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_UNMARK_TASK_SUCCESS = "Uncompleted Task: %1$s";
    public static final String MESSAGE_TASK_ALREADY_UNCOMPLETED = "This task is already marked as incomplete.";

    private final Index targetIndex;

    public UnmarkCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    /**
     * Creates a unmarked iteration of the Task provided.
     * @param task task to be copied.
     * @return marked task.
     */
    private static Task createUnmarkedTask(Task task) {
        Name name = task.getName();
        Description description = task.getDescription();
        CompletionStatus completionStatus = new CompletionStatus("false");
        Deadline deadline = task.getDeadline();
        Set<Tag> tags = task.getTags();

        Task unmarkedTask = new Task(name, description, completionStatus, deadline, tags);
        return unmarkedTask;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> lastShownList = model.getFilteredTaskList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }
        Task taskToUnmark = lastShownList.get(targetIndex.getZeroBased());
        Task unmarkedTask = createUnmarkedTask(taskToUnmark);

        if (taskToUnmark.equals(unmarkedTask)) {
            throw new CommandException(MESSAGE_TASK_ALREADY_UNCOMPLETED);
        }

        model.strictSetTask(taskToUnmark, unmarkedTask);
        model.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
        return new CommandResult(String.format(MESSAGE_UNMARK_TASK_SUCCESS, unmarkedTask));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UnmarkCommand // instanceof handles nulls
                && targetIndex.equals(((UnmarkCommand) other).targetIndex)); // state check
    }
}
