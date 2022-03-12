package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.TaskList;
import seedu.address.model.task.Task;

/**
 * A utility class containing a list of {@code Task} objects to be used in tests.
 */
public class TypicalTasks {

    public static final Task CS2105Midterm = new TaskBuilder().withName("CS2105 Midterm")
            .withCompletionStatus("false").withDeadline("2022-03-03")
            .withDescription("Lecture 1-5, Tutorial 1-4")
            .withTags("CS2105", "exam").build();
    public static final Task CS2105Finals = new TaskBuilder().withName("CS2105 Finals")
            .withCompletionStatus("false")
            .withDeadline("2022-11-11").withDescription("All lectures and tutorials")
            .withTags("CS2105", "exam").build();
    public static final Task CS2105Tutorial = new TaskBuilder().withName("CS2105 Tutorial")
            .withDescription("Week 9 Tutorial").withDeadline("2022-03-16").withCompletionStatus("false")
            .withTags("CS2105").build();
    public static final Task CS2103TProject = new TaskBuilder().withName("CS2103T Project").withDescription("V1.2b completion")
            .withDeadline("2022-03-13").withCompletionStatus("false").withTags("CS2103T").build();
    public static final Task MeetAlice = new TaskBuilder().withName("Meet Alice").withDescription("Meeting Alice at NUS")
            .withDeadline("2022-04-01").withCompletionStatus("false").build();
    public static final Task BuyArtSupplies = new TaskBuilder().withName("Buy Art Supplies")
            .withDescription("Buy Art supplies for art project")
            .withDeadline("2022-03-21").withCompletionStatus("false").build();
    public static final Task ApplyInternship = new TaskBuilder().withName("Apply Internship").withDescription("Apply for opening at Shopee")
            .withDeadline("2022-03-24").withCompletionStatus("false").build();

    // Manually added
    public static final Task CallBob = new TaskBuilder().withName("Call Bob").withDescription("Call Bob to discuss when to meet")
            .withDeadline("2022-03-05").withCompletionStatus("false").build();
    public static final Task CS2107Assignment = new TaskBuilder().withName("CS2107 Assignment").withDescription("Assignment 2")
            .withDeadline("2022-03-20").withCompletionStatus("false").build();

    // Manually added - Task's details found in {@code CommandTestUtil} //TODO: edit according to CommandTestUtil
    public static final Task AMY = new TaskBuilder().withName(VALID_NAME_AMY).withDescription(VALID_DESCRIPTION_AMY)
            .withDeadline(VALID_DEADLINE_AMY).withCompletionStatus(VALID_COMPLETION_STATUS_AMY).withTags(VALID_TAG_FRIEND).build();
    public static final Task BOB = new TaskBuilder().withName(VALID_NAME_BOB).withDescription(VALID_DESCRIPTION_BOB)
            .withDeadline(VALID_DEADLINE_BOB).withCompletionStatus(VALID_COMPLETION_STATUS_BOB).withTags(VALID_TAG_TUT, VALID_TAG_FRIEND)
            .build();

    public static final String KEYWORD_MATCHING_NUS = "NUS"; // A keyword that matches MEIER

    private TypicalTasks() {} // prevents instantiation

    /**
     * Returns an {@code TaskList} with all the typical Tasks.
     */
    public static TaskList getTypicalTaskList() {
        TaskList tl = new TaskList();
        for (Task Task : getTypicalTasks()) {
            tl.addTask(Task);
        }
        return tl;
    }

    public static List<Task> getTypicalTasks() {
        return new ArrayList<>(Arrays.asList(CS2103TProject, CS2105Finals, CS2105Midterm, CS2105Tutorial, MeetAlice));
    }
}
