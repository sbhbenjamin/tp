package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TASKS;

import java.util.ArrayList;
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
import seedu.address.model.task.Priority;
import seedu.address.model.task.Task;

/**
 * Marks a task identified using it's displayed index from the task list as uncompleted.
 */
public class UnmarkCommand extends Command {

    public static final String COMMAND_WORD = "unmark";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks the task identified by the index number used in the displayed task list as uncompleted.\n"
            + "Parameters: INDEX[ES] (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1, " + COMMAND_WORD + " 1 2 3";

    public static final String MESSAGE_TASK_ALREADY_UNCOMPLETED = "This task is already marked as incomplete.";

    private final ArrayList<Index> targetIndexes;

    /**
     * Constructor for unmarking multiple indexes.
     * @param targetIndexes an {@code ArrayList<Index>} which stores each index to be unmarked
     */
    public UnmarkCommand(ArrayList<Index> targetIndexes) {
        this.targetIndexes = targetIndexes;
    }

    /**
     * Creates an unmarked iteration of the Task provided.
     * @param task task to be copied.
     * @return marked task.
     */
    private static Task createUnmarkedTask(Task task) {
        Name name = task.getName();
        Description description = task.getDescription();
        CompletionStatus completionStatus = new CompletionStatus("false");
        Deadline deadline = task.getDeadline();
        Priority priority = task.getPriority();
        Set<Tag> tags = task.getTags();

        Task unmarkedTask = new Task(name, description, completionStatus, deadline, priority, tags);
        return unmarkedTask;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> lastShownList = model.getFilteredTaskList();
        ArrayList<Task> unmarkedTasks = new ArrayList<>();

        for (int i = 0; i < targetIndexes.size(); i++) {
            if (targetIndexes.get(i).getZeroBased() >= lastShownList.size()) {

                // in the case where the first few inputted indexes are marked successfully, but one of the latter
                // inputted indexes throw an error. Harmonia informs the user of the index that caused the error and
                // also informs the user of the tasks that it marked successfully
                if (unmarkedTasks.size() > 1) {
                    throw new CommandException("Index " + targetIndexes.get(i).getOneBased() + " :"
                            + Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX + "\n"
                            + unmarkedTasksToString(unmarkedTasks.subList(0, unmarkedTasks.size() - 1)));
                }

                // in the case where the first inputted index is unsuccessfully marked, none of the other indexes
                // inputted after the first inputted index will be processed, and an error is thrown to inform the user
                // of the inputted index that caused the error
                else {
                    throw new CommandException("Index " + targetIndexes.get(i).getOneBased() + " :"
                            + Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
                }
            }
            Task taskToUnmark = lastShownList.get(targetIndexes.get(i).getZeroBased());
            Task unmarkedTask = createUnmarkedTask(taskToUnmark);
            unmarkedTasks.add(unmarkedTask);

            if (taskToUnmark.equals(unmarkedTask)) {
                //todo: limitation now is that if "unmark 1 2 3" and index 2 is unmarked already, then only 1 will
                //todo: be unmarked successfully, 3 will not be unmarked

                // in the case where the first few inputted indexes are marked successfully, but one of the latter
                // inputted indexes throw an error. Harmonia informs the user of the index that caused the error and
                // also informs the user of the tasks that it marked successfully
                if (unmarkedTasks.size() > 1) {
                    throw new CommandException("Index " + targetIndexes.get(i).getOneBased() + ": "
                            + MESSAGE_TASK_ALREADY_UNCOMPLETED + "\n"
                            + unmarkedTasksToString(unmarkedTasks.subList(0, unmarkedTasks.size() - 1)));
                }


                // in the case where the first inputted index is unsuccessfully marked, none of the other indexes
                // inputted after the first inputted index will be processed, and an error is thrown to inform the user
                // of the inputted index that caused the error

                else {
                    throw new CommandException("Index " + targetIndexes.get(i).getOneBased() + ": "
                            + MESSAGE_TASK_ALREADY_UNCOMPLETED);
                }
            }

            model.strictSetTask(taskToUnmark, unmarkedTask);
            model.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
        }

        return new CommandResult(unmarkedTasksToString(unmarkedTasks.subList(0, unmarkedTasks.size())));
    }

    /**
     * Converts the list of successfully unmarked tasks into a string to be returned to the user
     * @param unmarkedTasks
     * @return
     */
    private String unmarkedTasksToString(List<Task> unmarkedTasks) {
        String str = "Uncompleted Tasks: \n";
        for (int i = 0; i < unmarkedTasks.size(); i++) {
            str += (i + 1) + ". " + unmarkedTasks.get(i) + "\n";
        }
        return str;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UnmarkCommand // instanceof handles nulls
                && targetIndexes.equals(((UnmarkCommand) other).targetIndexes)); // state check
    }
}
