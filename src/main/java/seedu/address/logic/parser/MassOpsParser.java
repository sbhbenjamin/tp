package seedu.address.logic.parser;

import java.util.ArrayList;
import java.util.Arrays;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses through the second part of the user input (after the command) and determine which indexes are to be called in
 * the mass operation.
 */
public class MassOpsParser {
    /**
     * Parses through the second part of user input to process what indexes are called.
     *
     * @param args string of indexes to perform mass ops on
     * @return a {@code ArrayList<Index>} with the indexes to be performing mass ops on
     * @throws ParseException
     */
    public static ArrayList<Index> massOpProcessor(String args) throws ParseException {
        ArrayList<String> stringIndexes = new ArrayList<>(Arrays.asList(args.trim().split(" ")));
        ArrayList<Index> indexes = new ArrayList<>();


        for (int i = 0; i < stringIndexes.size(); i++) {
            Index index = ParserUtil.parseIndex(stringIndexes.get(i));
            indexes.add(index);
        }

        return indexes;
    }
}
