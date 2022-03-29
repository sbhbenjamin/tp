package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.TagContainsKeywordsPredicate;
import seedu.address.model.task.Deadline;
import seedu.address.model.task.DeadlineInRangePredicate;
import seedu.address.model.task.DescriptionContainsKeywordsPredicate;
import seedu.address.model.task.NameContainsKeywordsPredicate;
import seedu.address.model.task.Priority;
import seedu.address.model.task.PriorityMatchedPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    private static final Prefix[] POSSIBLE_PREFIXES = new Prefix[] {
            PREFIX_NAME, PREFIX_TAG, PREFIX_START,
            PREFIX_END, PREFIX_DESCRIPTION, PREFIX_PRIORITY
    };

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, POSSIBLE_PREFIXES);

        if (!anyPrefixPresent(argMultimap, POSSIBLE_PREFIXES)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        Set<String> nameKeywords = ParserUtil.parseKeywords(argMultimap.getAllValues(PREFIX_NAME));
        Set<String> tagKeywords = ParserUtil.parseKeywords(argMultimap.getAllValues(PREFIX_TAG));
        Set<String> descriptionKeywords = ParserUtil.parseKeywords(argMultimap.getAllValues(PREFIX_DESCRIPTION));
        Set<Priority> prioritySet = ParserUtil.parsePriorities(argMultimap.getAllValues(PREFIX_PRIORITY));

        Deadline startDate = null;
        Deadline endDate = null;

        if (argMultimap.getValue(PREFIX_START).isPresent()) {
            startDate = ParserUtil.parseDeadline(argMultimap.getValue(PREFIX_START).get());
        }

        if (argMultimap.getValue(PREFIX_END).isPresent()) {
            endDate = ParserUtil.parseDeadline(argMultimap.getValue(PREFIX_END).get());
        }

        return new FindCommand(new NameContainsKeywordsPredicate(nameKeywords),
                new TagContainsKeywordsPredicate(tagKeywords),
                new DeadlineInRangePredicate(startDate, endDate),
                new DescriptionContainsKeywordsPredicate(descriptionKeywords),
                new PriorityMatchedPredicate(prioritySet));
    }

    /**
     * Checks whether there is at least one prefix present.
     *
     * @param argumentMultimap the {@code ArgumentMultimap} to be checked
     * @param prefixes the prefixes to be checked
     * @return true if there is any prefix present in {@code argumentMultimap}
     */
    private static boolean anyPrefixPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
