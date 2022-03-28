package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.MassOpsParser;
import seedu.address.model.Model;
import seedu.address.model.task.Task;

/**
 * Deletes a task identified using it's displayed index from the task list.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the task identified by the index number used in the displayed task list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    private final List<Index> targetIndexes;

    public DeleteCommand(List<Index> targetIndexes) {
        this.targetIndexes = MassOpsParser.sortInDesc(targetIndexes);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> lastShownList = model.getFilteredTaskList();
        List<Task> deletedTasks = new ArrayList<>();
        List<Index> deletedTasksIndexes = new ArrayList<>();
        List<Index> outOfBoundsIndexes = new ArrayList<>();

        for (int i = 0; i < targetIndexes.size(); i++) {
            Index index = targetIndexes.get(i);
            if (isValidIndex(index, lastShownList) == -1) {
                outOfBoundsIndexes.add(index);
            } else {
                Task taskToDelete = lastShownList.get(index.getZeroBased());
                model.deleteTask(taskToDelete);
                deletedTasks.add(taskToDelete);
                deletedTasksIndexes.add(index);
            }
        }
        return result(deletedTasks, deletedTasksIndexes, outOfBoundsIndexes);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && targetIndexes.equals(((DeleteCommand) other).targetIndexes)); // state check
    }

    /**
     * Converts the list of successfully deleted tasks into a string to be returned to the user
     * @param deletedTasks
     * @return
     */
    private String deletedTasksToString(List<Task> deletedTasks, List<Index> deletedTasksIndexes) {
        String str = "Successfully Deleted Tasks: \n";
        for (int i = deletedTasks.size() - 1; i >= 0; i--) {
            str += deletedTasksIndexes.get(i).getOneBased() + ". " + deletedTasks.get(i) + "\n";
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

    /**
     * Checks if index is a valid index to delete.
     * If index > size of targetIndexes, return -1.
     * If index is a valid index, return 0.
     * @return an int representing validity of the index
     */
    private int isValidIndex(Index index, List<Task> taskList) {
        if (index.getZeroBased() > taskList.size() - 1 || index.getZeroBased() < 0) { //index out of bounds
            return -1;
        } else { //valid index
            return 0;
        }
    }

    /**
     * Processes the lists containing the results of the deleting of indexes and returns the result to the user.
     * @param deletedTasks
     * @param deletedTasksIndexes
     * @param outOfBoundsIndexes
     * @return CommandResult
     * @throws CommandException
     */
    private CommandResult result(List<Task> deletedTasks, List<Index> deletedTasksIndexes,
                                 List<Index> outOfBoundsIndexes) throws CommandException {
        StringBuilder errorString = new StringBuilder();


        if (!outOfBoundsIndexes.isEmpty()) {
            errorString.append("Index " + indexesToString(outOfBoundsIndexes) + ": "
                    + Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX + "\n");
        }

        if (errorString.length() != 0) { //throw error if any
            if (!deletedTasks.isEmpty()) {
                errorString.append(deletedTasksToString(deletedTasks, deletedTasksIndexes));
            }
            throw new CommandException(errorString.toString());
        }

        return new CommandResult(deletedTasksToString(deletedTasks, deletedTasksIndexes));

    }
}
