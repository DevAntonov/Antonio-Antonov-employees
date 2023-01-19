package TeamLongestPeriod;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        String file = AssignmentUtils.openFile();
        List<Entry> entries = AssignmentUtils.readEntriesFromCSV(file);
        List<Pair> pairs = new ArrayList<>();

        // iterating the list of entries
        for (int i = 0; i < entries.size(); i++) {
            for (int j = i + 1; j < entries.size(); j++) {
                Entry entry1 = entries.get(i);
                Entry entry2 = entries.get(j);

                // Check if project is the same and if there is overlap period
                if (entry1.getProjectID().equals(entry2.getProjectID()) && AssignmentUtils.hasOverlapPeriod(entry1, entry2)) {
                    int overlapTime = AssignmentUtils.getOverlapPeriod(entry1, entry2);
                    if (overlapTime > 0) {
                        AssignmentUtils.pairsHandling(pairs, entry1, entry2, overlapTime);
                    }
                }
            }
        }

        // Sorting the list of pairs by total overlap time in ascending order
        pairs.sort((pair1, pair2) -> pair2.getTotalOverlap() - pair1.getTotalOverlap());

        // print the pair with the longest time period to console
        System.out.println(pairs.get(0));
    }
}