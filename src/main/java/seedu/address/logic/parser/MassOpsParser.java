package seedu.address.logic.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

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
    public static List<Index> massOpProcessor(String args) throws ParseException {
        List<String> stringIndexes = new ArrayList<>(Arrays.asList(args.trim().split(" ")));
        List<Index> indexes = new ArrayList<>();


        for (int i = 0; i < stringIndexes.size(); i++) {
            Index index = ParserUtil.parseIndex(stringIndexes.get(i));
            indexes.add(index);
        }

        return indexes;
    }

    /**
     * Sorts the indexes in ascending order.
     * @param indexes
     * @return a {@code List<Indexes>} sorted in ascending order.
     */
    public static List<Index> sortInAsc(List<Index> indexes) {
        List<Index> ascIndexes = new ArrayList<>();
        TreeMap<Integer, Index> sorted = new TreeMap<>();

        for (int i = 0; i < indexes.size(); i++) {
            Index index = indexes.get(i);
            sorted.put(index.getOneBased(), index);
        }

        SortedSet<Integer> keys = new TreeSet<>(sorted.keySet());

        for (Integer key : keys) {
            Index value = sorted.get(key);
            ascIndexes.add(value);
        }

        return ascIndexes;
    }

    /**
     * Sorts the indexes in descending order.
     * @param indexes
     * @return a {@code List<Indexes>} sorted in descending order.
     */
    public static List<Index> sortInDesc(List<Index> indexes) {
        List<Index> descIndexes = new ArrayList<>();
        TreeMap<Integer, Index> sorted = new TreeMap<>();

        for (int i = 0; i < indexes.size(); i++) {
            Index index = indexes.get(i);
            sorted.put(index.getOneBased(), index);
        }

        SortedSet<Integer> keys = new TreeSet<>(Collections.reverseOrder());
        keys.addAll(sorted.keySet());

        for (Integer key : keys) {
            Index value = sorted.get(key);
            descIndexes.add(value);
        }

        return descIndexes;
    }
}
