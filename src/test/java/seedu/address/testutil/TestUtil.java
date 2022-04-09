package seedu.address.testutil;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Function;

import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.task.Task;

/**
 * A utility class for test cases.
 */
public class TestUtil {
    /**
     * Common invalid test strings used.
     */
    public static final String EMPTY_STRING = "";
    public static final String WHITESPACES_ONLY_STRING = "";
    public static final String EMOJI_STRING = "☝";
    public static final String SYMBOLS_STRING = "ʕ·ᴥ·ʔ";
    public static final String PUNCTUATION_STRING = ",";
    public static final String NON_ENGLISH_STRING = "ㅁㅂㅇㅈㅊㅋㅌㅍㅎ";
    public static final String LEADING_WHITESPACE_STRING = " task";

    /**
     * Folder used for temp files created during testing. Ignored by Git.
     */
    private static final Path SANDBOX_FOLDER = Paths.get("src", "test", "data", "sandbox");

    /**
     * Test fields against invalid strings.
     *
     * @param isValidField validation check provided by each field.
     */
    public static void assertAllFalse(Function<String, Boolean> isValidField) {
        assertFalse(isValidField.apply(EMPTY_STRING));
        assertFalse(isValidField.apply(WHITESPACES_ONLY_STRING));
        assertFalse(isValidField.apply(LEADING_WHITESPACE_STRING));
        assertFalse(isValidField.apply(NON_ENGLISH_STRING));
        assertFalse(isValidField.apply(SYMBOLS_STRING));
        assertFalse(isValidField.apply(EMOJI_STRING));
        assertFalse(isValidField.apply(PUNCTUATION_STRING));
    }

    /**
     * Appends {@code fileName} to the sandbox folder path and returns the resulting path.
     * Creates the sandbox folder if it doesn't exist.
     */
    public static Path getFilePathInSandboxFolder(String fileName) {
        try {
            Files.createDirectories(SANDBOX_FOLDER);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return SANDBOX_FOLDER.resolve(fileName);
    }

    /**
     * Returns the middle index of the task in the {@code model}'s task list.
     */
    public static Index getMidIndex(Model model) {
        return Index.fromOneBased(model.getFilteredTaskList().size() / 2);
    }

    /**
     * Returns the last index of the task in the {@code model}'s task list.
     */
    public static Index getLastIndex(Model model) {
        return Index.fromOneBased(model.getFilteredTaskList().size());
    }

    /**
     * Returns the task in the {@code model}'s task list at {@code index}.
     */
    public static Task getTask(Model model, Index index) {
        return model.getFilteredTaskList().get(index.getZeroBased());
    }
}
