package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditTaskDescriptor;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.TagContainsKeywordsPredicate;
import seedu.address.model.task.DeadlineInRangePredicate;
import seedu.address.model.task.NameContainsKeywordsPredicate;
import seedu.address.model.task.Task;
import seedu.address.testutil.EditTaskDescriptorBuilder;
import seedu.address.testutil.TaskBuilder;
import seedu.address.testutil.TaskUtil;

public class HarmoniaParserTest {

    private final HarmoniaParser parser = new HarmoniaParser();

    @Test
    public void parseCommand_add() throws Exception {
        Task task = new TaskBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(TaskUtil.getAddCommand(task));
        assertEquals(new AddCommand(task), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_TASK.getOneBased());
        assertEquals(new DeleteCommand(INDEX_FIRST_TASK), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Task task = new TaskBuilder().build();
        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder(task).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_TASK.getOneBased() + " " + TaskUtil.getEditTaskDescriptorDetails(descriptor));
        assertEquals(new EditCommand(INDEX_FIRST_TASK, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> nameKeywords = Arrays.asList("foo", "bar", "baz");
        List<String> tagKeywords = Arrays.asList("tag1", "tag2");
        List<String> tagKeywordsWithPrefix = tagKeywords.stream().map(k -> PREFIX_TAG + k).collect(Collectors.toList());
        List<String> nameKeywordsWithPrefix = nameKeywords.stream().map(k -> PREFIX_NAME + k)
                .collect(Collectors.toList());
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " "
                        + nameKeywordsWithPrefix.stream().collect(Collectors.joining(" ")) + " "
                        + tagKeywordsWithPrefix.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindCommand(new NameContainsKeywordsPredicate(new HashSet<>(nameKeywords)),
                new TagContainsKeywordsPredicate(new HashSet<>(tagKeywords)),
                new DeadlineInRangePredicate(null, null)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        ListCommand listTasksCommand = (ListCommand) parser.parseCommand(ListCommand.COMMAND_WORD);
        ListCommand listTagsCommand = (ListCommand) parser.parseCommand(ListCommand.COMMAND_WORD
                + " " + PREFIX_TAG);
        assertEquals(new ListCommand(false), listTasksCommand);
        assertEquals(new ListCommand(true), listTagsCommand);
    }

    //    @Test
    //    public void parseCommand_mark() throws Exception {
    //        MarkCommand command = (MarkCommand) parser.parseCommand(
    //                MarkCommand.COMMAND_WORD + " " + INDEX_FIRST_TASK.getOneBased());
    //        assertEquals(new MarkCommand(INDEX_FIRST_TASK), command);
    //    }
    //
    //    @Test
    //    public void parseCommand_unmark() throws Exception {
    //        UnmarkCommand command = (UnmarkCommand) parser.parseCommand(
    //                UnmarkCommand.COMMAND_WORD + " " + INDEX_SECOND_TASK.getOneBased());
    //        assertEquals(new UnmarkCommand(INDEX_SECOND_TASK), command);
    //    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), (
            ) -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
