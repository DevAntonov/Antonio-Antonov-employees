package TeamLongestPeriod;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class AssignmentUtils {

    // This method updates existing pairs
    // and adds new pair if is not present in list of pairs
    static void pairsHandling(List<Pair> pairs, Entry entry1, Entry entry2, int overlapTime) {
        boolean flag = false;

        for (Pair pair : pairs) {
            if(isPairPresent(pair, entry1.getEmployeeID(), entry2.getEmployeeID())) {
                pair.addOverlapTime(overlapTime);
                flag = true;
            }
        }

        if (!flag) {
            pairs.add(new Pair(entry1.getEmployeeID(), entry2.getEmployeeID(), overlapTime));
        }
    }

    // This method helps to check if pair is
    // already present in the list of pairs
    static boolean isPairPresent(Pair pair, String firstEmployeeID, String secondEmployeeID) {
        return ( pair.getFirstEmployeeID().equals(firstEmployeeID)
                && pair.getSecondEmployeeID().equals(secondEmployeeID) )
                || ( pair.getFirstEmployeeID().equals(secondEmployeeID)
                && pair.getSecondEmployeeID().equals(firstEmployeeID) );
    }

    // This method calculates the overlap period
    // and returns the duration in days
    static int getOverlapPeriod(Entry entry1, Entry entry2) {
        LocalDate periodStartDate = entry1.getDateFrom().isBefore(entry2.getDateFrom()) ?
                entry2.getDateFrom() : entry1.getDateFrom();

        LocalDate periodEndDate = entry1.getDateTo().isBefore(entry2.getDateTo()) ?
                entry1.getDateTo() : entry2.getDateTo();

        return (int) Math.abs(ChronoUnit.DAYS.between(periodStartDate, periodEndDate));
    }

    // This method checks if there is overlap period
    static boolean hasOverlapPeriod(Entry entry1, Entry entry2) {

        return (entry1.getDateFrom().isBefore(entry2.getDateTo())
                || entry1.getDateFrom().isEqual(entry2.getDateTo()))
                && (entry1.getDateTo().isAfter(entry2.getDateFrom())
                || entry1.getDateTo().isEqual(entry2.getDateFrom()));
    }

    // This method reads and parses the file, and returns list of entries
    static List<Entry> readEntriesFromCSV(String file) {
        List<Entry> entries = new ArrayList<>();

        try (BufferedReader br = Files.newBufferedReader(Path.of(file))) {

            // read the first line from the text file
            String line = br.readLine();

            // loop until all lines are read
            while (line != null) {
                String[] attributes = line.split(",");

                Entry entry = Entry.createEntry(attributes);
                entries.add(entry);

                line = br.readLine();
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return entries;
    }

    // Method which opens dialog window to choose file and returns file's path
    static String openFile() {
        FileDialog dialog = new FileDialog((Frame)null, "Select File to Open");
        dialog.setFile("*.csv");
        dialog.setMode(FileDialog.LOAD);
        dialog.setVisible(true);
        String filePath = dialog.getDirectory() + dialog.getFile();
        dialog.dispose();

        return filePath;
    }
}
