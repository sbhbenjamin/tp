package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_DESCRIPTION = new Prefix("d/");
    public static final Prefix PREFIX_DEADLINE = new Prefix("dl/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");


    /* Definition for other fields */
    public static final Prefix PREFIX_DATE_START = new Prefix("start/");
    public static final Prefix PREFIX_DATE_END = new Prefix("end/");
    public static final Prefix PREFIX_DATE_FROM = new Prefix("from/");  // Equivalent to "start/"
    public static final Prefix PREFIX_DATE_TO = new Prefix("to/");      // Equivalent to "end/"
}
