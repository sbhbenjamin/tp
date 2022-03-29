package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.tag.TagContainsKeywordsPredicate;
import seedu.address.model.task.DeadlineInRangePredicate;
import seedu.address.model.task.NameContainsKeywordsPredicate;

/**
 * Finds and lists all tasks in task list whose name or tag contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all tasks whose names or tags contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers. "
            + "If a range of date is specified, only the tasks with a deadline that falls within the range "
            + "will be displayed.\n"
            + "Parameters (must contain at least one keyword): "
            + "[" + PREFIX_NAME + "NAME_KEYWORD] "
            + "[" + PREFIX_TAG + "TAG_KEYWORD] "
            + "[" + PREFIX_START + "YYYY-MM-DD] "
            + "[" + PREFIX_END + "YYYY-MM-DD]\n"
            + "Example: " + COMMAND_WORD
            + " tutorial n/assignment t/test t/CS2103T start/2022-03-23 end/2022-03-28";

    /** Predicate that tests whether task name contains any given keyword */
    private final NameContainsKeywordsPredicate namePredicate;

    /** Predicate that tests whether tag contains any given keywords */
    private final TagContainsKeywordsPredicate tagPredicate;

    /** Predicate that tests whether the deadline of a task is within the given date range */
    private final DeadlineInRangePredicate deadlinePredicate;

    /**
     * Constructor for FindCommand.
     *
     * @param namePredicate predicate that tests whether task name contains any given keywords
     * @param tagPredicate predicate that tests whether tag contains any given keywords
     * @param deadlinePredicate predicate that tests whether the deadline is in the range
     */
    public FindCommand(NameContainsKeywordsPredicate namePredicate,
                       TagContainsKeywordsPredicate tagPredicate,
                       DeadlineInRangePredicate deadlinePredicate) {

        this.namePredicate = namePredicate;
        this.tagPredicate = tagPredicate;
        this.deadlinePredicate = deadlinePredicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTaskList(namePredicate.and(tagPredicate).and(deadlinePredicate));
        return new CommandResult(
                String.format(Messages.MESSAGE_TASKS_LISTED_OVERVIEW, model.getFilteredTaskList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && namePredicate.equals(((FindCommand) other).namePredicate)
                && tagPredicate.equals(((FindCommand) other).tagPredicate)
                && deadlinePredicate.equals(((FindCommand) other).deadlinePredicate));
    }
}
