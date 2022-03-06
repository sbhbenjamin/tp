package seedu.address.model.task;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Task's completion status in the task list.
 * Guarantees: immutable;
 */
public class CompletionStatus {
    public final boolean value;

    /**
     * Constructs an {@code CompletionStatus}.
     *
     * @param completionStatus A valid completion status.
     */
    public CompletionStatus(Boolean completionStatus) {
        requireNonNull(completionStatus);
        value = completionStatus;
    }

    @Override
    public String toString() {
        return value ? "[X]" : "[ ]";
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CompletionStatus // instanceof handles nulls
                && value == (((CompletionStatus) other).value)); // state check
    }
}
