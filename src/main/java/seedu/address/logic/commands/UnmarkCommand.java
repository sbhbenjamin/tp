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
     * @return unmarked task.
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
        List<Task> unmarkedTasks = new ArrayList<>();
        List<Index> alreadyUnmarkedIndexes = new ArrayList<>();
        List<Index> outOfBoundsIndexes = new ArrayList<>();


        // to unmark each index in the task list individually.
        for (int i = 0; i < targetIndexes.size(); i++) {
            Index index = targetIndexes.get(i);

            if (isValidIndex(index, lastShownList) == 1) { //index unmarked already
                alreadyUnmarkedIndexes.add(index);
            } else if (isValidIndex(index, lastShownList) == -1) { //index out of bounds
                outOfBoundsIndexes.add(index);
            } else { //valid index
                Task taskToUnmark = lastShownList.get(index.getZeroBased());
                Task unmarkedTask = createUnmarkedTask(taskToUnmark);
                unmarkedTasks.add(unmarkedTask);

                model.strictSetTask(taskToUnmark, unmarkedTask);
                model.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
            }
        }
        return result(unmarkedTasks, alreadyUnmarkedIndexes, outOfBoundsIndexes);

    }

    /**
     * Converts the list of successfully unmarked tasks into a string to be returned to the user
     * @param unmarkedTasks
     * @return
     */
    private String unmarkedTasksToString(List<Task> unmarkedTasks) {
        String str = "Successfully Unmarked Tasks: \n";
        for (int i = 0; i < unmarkedTasks.size(); i++) {
            str += (i + 1) + ". " + unmarkedTasks.get(i) + "\n";
        }
        return str;
    }

    /**
     * Converts a list of indexes to a string to be returned to the user.
     * @param indexes
     * @return
     */
    private String indexesToString(List<Index> indexes) {
        String str = "" + indexes.get(0).getOneBased();
        for (int i = 1; i < indexes.size(); i++) {
            int index = indexes.get(i).getOneBased();
            if (indexes.size() > 1 && i == indexes.size() - 1) {
                str += " and " + index;
            } else {
                str += ", " + index;
            }
        }
        return str;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UnmarkCommand // instanceof handles nulls
                && targetIndexes.equals(((UnmarkCommand) other).targetIndexes)); // state check
    }

    /**
     * Checks if index is a valid index to unmark.
     * If index > size of targetIndexes, return -1.
     * If index is already unmarked, return 1.
     * If index is a valid index, return 0.
     *
     * @return an int representing validity of the index
     */
    private int isValidIndex(Index index, List<Task> taskList) {
        if (index.getZeroBased() > taskList.size() - 1 || index.getZeroBased() < 0) { //index out of bounds
            return -1;
        } else if (taskList.get(index.getZeroBased()).getCompletionStatus().toString().equals("false")) {
            //already unmarked index
            return 1;
        } else { //valid index
            return 0;
        }
    }

    /**
     * Processes the lists containing the results of the unmarking of indexes and returns the result to the user.
     * @param unmarkedTasks
     * @param alreadyUnmarkedIndexes
     * @param outOfBoundsIndexes
     * @return CommandResult
     * @throws CommandException
     */
    private CommandResult result(List<Task> unmarkedTasks, List<Index> alreadyUnmarkedIndexes,
                                 List<Index> outOfBoundsIndexes) throws CommandException{
        if (!unmarkedTasks.isEmpty()) { //have successfully unmarked tasks
            if (!alreadyUnmarkedIndexes.isEmpty() && !outOfBoundsIndexes.isEmpty()) {
                //successfully unmarked tasks, already unmarked indexes and out of bound indexes
                throw new CommandException("Index " + indexesToString(outOfBoundsIndexes) + ": "
                        + Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX + "\n"
                        + "Index " + indexesToString(alreadyUnmarkedIndexes) + " :"
                        + MESSAGE_TASK_ALREADY_UNCOMPLETED + "\n" + unmarkedTasksToString(unmarkedTasks));

            } else if (!alreadyUnmarkedIndexes.isEmpty() && outOfBoundsIndexes.isEmpty()) {
                //successfully unmarked tasks and already unmarked indexes
                throw new CommandException("Index " + indexesToString(alreadyUnmarkedIndexes) + ": "
                        + MESSAGE_TASK_ALREADY_UNCOMPLETED + "\n" + unmarkedTasksToString(unmarkedTasks));

            } else if (alreadyUnmarkedIndexes.isEmpty() && !outOfBoundsIndexes.isEmpty()) {
                //successfully unmarked tasks and out of bound indexes
                throw new CommandException("Index " + indexesToString(outOfBoundsIndexes) + ": "
                        + Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX + "\n"
                        + unmarkedTasksToString(unmarkedTasks));

            } else if (alreadyUnmarkedIndexes.isEmpty() && outOfBoundsIndexes.isEmpty()) {
                //successfully unmarked tasks only
                return new CommandResult(unmarkedTasksToString(unmarkedTasks));
            }
        } else { //no successfully unmarked tasks
            if (!alreadyUnmarkedIndexes.isEmpty() && !outOfBoundsIndexes.isEmpty()) {
                //already unmarked indexes and out of bounds indexes
                throw new CommandException("Index " + indexesToString(outOfBoundsIndexes) + ": "
                        + Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX + "\n"
                        + "Index " + indexesToString(alreadyUnmarkedIndexes) + " :"
                        + MESSAGE_TASK_ALREADY_UNCOMPLETED);

            } else if (!alreadyUnmarkedIndexes.isEmpty() && outOfBoundsIndexes.isEmpty()) {
                //already unmarked indexes
                throw new CommandException("Index " + indexesToString(alreadyUnmarkedIndexes) + ": "
                        + MESSAGE_TASK_ALREADY_UNCOMPLETED);

            } else if (alreadyUnmarkedIndexes.isEmpty() && !outOfBoundsIndexes.isEmpty()) {
                //out of bounds indexes
                throw new CommandException("Index " + indexesToString(outOfBoundsIndexes) + ": "
                        + Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
            }
        }
        return null;
    }
}
