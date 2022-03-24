package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.tag.TagContainsKeywordsPredicate;
import seedu.address.model.task.NameContainsKeywordsPredicate;

/**
 * Finds and lists all tasks in task list whose name or tag contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all tasks whose names or tags contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters (must contain at least one keyword): "
            + "[NAME_KEYWORD] "
            + "[" + PREFIX_TAG + "TAG_KEYWORD]\n"
            + "Example: " + COMMAND_WORD + " tutorial assignment t/test t/CS2103T";

    /** Predicate that tests whether task name contains any given keyword */
    private final NameContainsKeywordsPredicate namePredicate;

    /** Predicate that tests whether tag contains any given keywords */
    private final TagContainsKeywordsPredicate tagPredicate;

    /**
     * Constructor for FindCommand.
     *
     * @param namePredicate predicate that tests whether task name contains any given keywords.
     * @param tagPredicate predicate that tests whether tag contains any given keywords.
     */
    public FindCommand(NameContainsKeywordsPredicate namePredicate, TagContainsKeywordsPredicate tagPredicate) {
        this.namePredicate = namePredicate;
        this.tagPredicate = tagPredicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTaskList(namePredicate.or(tagPredicate));
        return new CommandResult(
                String.format(Messages.MESSAGE_TASKS_LISTED_OVERVIEW, model.getFilteredTaskList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && namePredicate.equals(((FindCommand) other).namePredicate)
                && tagPredicate.equals(((FindCommand) other).tagPredicate)); // state check
    }
}
