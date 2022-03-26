package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TASKS;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.Parser;
import seedu.address.model.Model;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.CompletionStatus;
import seedu.address.model.task.Deadline;
import seedu.address.model.task.Description;
import seedu.address.model.task.Name;
import seedu.address.model.task.Priority;
import seedu.address.model.task.Task;

/**
 * Marks a task identified using it's displayed index from the task list as completed.
 */
public class MarkCommand extends Command {

    public static final String COMMAND_WORD = "mark";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks the task identified by the index number used in the displayed task list as completed.\n"
            + "Parameters: INDEX[ES] (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1, " + COMMAND_WORD + " 1 2 3";

    public static final String MESSAGE_TASK_ALREADY_COMPLETED = "This task is already marked as complete.";

    private final ArrayList<Index> targetIndexes;

    /**
     * Constructor for marking multiple indexes
     * @param targetIndexes an {@code ArrayList<Index>} which stores each index to be marked
     */
    public MarkCommand(ArrayList<Index> targetIndexes) {
        this.targetIndexes = targetIndexes;
    }

    /**
     * Creates a marked iteration of the Task provided.
     * @param task task to be copied.
     * @return marked task.
     */
    private static Task createMarkedTask(Task task) {
        Name name = task.getName();
        Description description = task.getDescription();
        CompletionStatus completionStatus = new CompletionStatus("true");
        Deadline deadline = task.getDeadline();
        Priority priority = task.getPriority();
        Set<Tag> tags = task.getTags();

        Task markedTask = new Task(name, description, completionStatus, deadline, priority, tags);
        return markedTask;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> lastShownList = model.getFilteredTaskList();
        List<Task> markedTasks = new ArrayList<>();
        List<Index> alreadyMarkedIndexes = new ArrayList<>();
        List<Index> outOfBoundsIndexes = new ArrayList<>();


        // to mark each index in the task list individually.
        for (int i = 0; i < targetIndexes.size(); i++) {
            Index index = targetIndexes.get(i);

            if (isValidIndex(index, lastShownList) == 1) { //index marked already
                alreadyMarkedIndexes.add(index);
            } else if (isValidIndex(index, lastShownList) == -1) { //index out of bounds
                outOfBoundsIndexes.add(index);
            } else { //valid index
                Task taskToMark = lastShownList.get(index.getZeroBased());
                Task markedTask = createMarkedTask(taskToMark);
                markedTasks.add(markedTask);

                model.strictSetTask(taskToMark, markedTask);
                model.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
            }
        }
        return result(markedTasks, alreadyMarkedIndexes, outOfBoundsIndexes);
    }

    /**
     * Converts the list of successfully marked tasks into a string to be returned to the user
     * @param markedTasks
     * @return
     */
    private String markedTasksToString(List<Task> markedTasks) {
        String str = "Successfully Marked Tasks: \n";
        for (int i = 0; i < markedTasks.size(); i++) {
            str += (i + 1) + ". " + markedTasks.get(i) + "\n";
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
                || (other instanceof MarkCommand // instanceof handles nulls
                && targetIndexes.equals(((MarkCommand) other).targetIndexes)); // state check
    }

    /**
     * Checks if index is a valid index to mark.
     * If index > size of targetIndexes, return -1.
     * If index is already marked, return 1.
     * If index is a valid index, return 0.
     *
     * @return an int representing validity of the index
     */
    private int isValidIndex(Index index, List<Task> taskList) {
        if (index.getZeroBased() > taskList.size() - 1 || index.getZeroBased() < 0) { //index out of bounds
            return -1;
        } else if (taskList.get(index.getZeroBased()).getCompletionStatus().toString().equals("true")) {
            //already marked index
            return 1;
        } else { //valid index
            return 0;
        }
    }

    /**
     * Processes the lists containing the results of the marking of indexes and returns the result to the user.
     * @param markedTasks
     * @param alreadyMarkedIndexes
     * @param outOfBoundsIndexes
     * @return CommandResult
     * @throws CommandException
     */
    private CommandResult result(List<Task> markedTasks, List<Index> alreadyMarkedIndexes,
                                 List<Index> outOfBoundsIndexes) throws CommandException{
        if (!markedTasks.isEmpty()) { //have successfully marked tasks
            if (!alreadyMarkedIndexes.isEmpty() && !outOfBoundsIndexes.isEmpty()) {
                //successfully marked tasks, already marked indexes and out of bound indexes
                throw new CommandException("Index " + indexesToString(outOfBoundsIndexes) + ": "
                        + Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX + "\n"
                        + "Index " + indexesToString(alreadyMarkedIndexes) + " :"
                        + MESSAGE_TASK_ALREADY_COMPLETED + "\n" + markedTasksToString(markedTasks));

            } else if (!alreadyMarkedIndexes.isEmpty() && outOfBoundsIndexes.isEmpty()) {
                //successfully marked tasks and already marked indexes
                throw new CommandException("Index " + indexesToString(alreadyMarkedIndexes) + ": "
                        + MESSAGE_TASK_ALREADY_COMPLETED + "\n" + markedTasksToString(markedTasks));

            } else if (alreadyMarkedIndexes.isEmpty() && !outOfBoundsIndexes.isEmpty()) {
                //successfully marked tasks and out of bound indexes
                throw new CommandException("Index " + indexesToString(outOfBoundsIndexes) + ": "
                        + Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX + "\n"
                        + markedTasksToString(markedTasks));

            } else if (alreadyMarkedIndexes.isEmpty() && outOfBoundsIndexes.isEmpty()) {
                //successfully marked tasks only
                return new CommandResult(markedTasksToString(markedTasks));
            }
        } else { //no successfully marked tasks
            if (!alreadyMarkedIndexes.isEmpty() && !outOfBoundsIndexes.isEmpty()) {
                //already marked indexes and out of bounds indexes
                throw new CommandException("Index " + indexesToString(outOfBoundsIndexes) + ": "
                        + Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX + "\n"
                        + "Index " + indexesToString(alreadyMarkedIndexes) + " :"
                        + MESSAGE_TASK_ALREADY_COMPLETED);

            } else if (!alreadyMarkedIndexes.isEmpty() && outOfBoundsIndexes.isEmpty()) {
                //already marked indexes
                throw new CommandException("Index " + indexesToString(alreadyMarkedIndexes) + ": "
                        + MESSAGE_TASK_ALREADY_COMPLETED);

            } else if (alreadyMarkedIndexes.isEmpty() && !outOfBoundsIndexes.isEmpty()) {
                //out of bounds indexes
                throw new CommandException("Index " + indexesToString(outOfBoundsIndexes) + ": "
                        + Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
            }
        }
        return null;
    }
}
