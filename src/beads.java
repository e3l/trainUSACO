/*
ID: ethan_w1
LANG: JAVA
TASK: beads
 */

import java.io.*;

public class beads {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("beads.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("beads.out")));

        int numbeads = Integer.parseInt(in.readLine());
        char beads[] = in.readLine().toCharArray();

        // Set up integer that will record which bead we are scanning.
        int scanner;
        // Set up char to store color. O will be unassigned
        char color = 'o';
        //set up counter to count how many beads we'll get
        int count = 0;
        // Stop boolean
        boolean stop;
        // Set up maximum beads collected
        int maxcount = 0;

        // We iterate through all the spaces in the necklace, to see which gives us the largest amount of beads.
        for (int i = 0; i < beads.length; i++) {
            //Reset Scanner, stop, and count
            count = 0;
            scanner = i - 1;
            if (scanner < 0) {
                scanner = numbeads--;
            }
            stop = false;

            // We look at the space before the bead we're choosing. If i is 1, we look at space 0
            // Scan backwards:
            while (stop == false) {
                if (beads[scanner] == 'w') {
                    count++;
                    scanner--;
                } else if (color == 'o') {
                    color = beads[scanner];
                    scanner--;
                    count++;
                } else if (color == beads[scanner]) {
                    count++;
                    scanner--;
                } else {
                    stop = true;;
                }

                // If we are looking at the 'negative' bead, we go around the  necklace and look at the end bead
                if (scanner < 0) {
                    scanner = numbeads--;
                }
            }

            // Reset Scanner, stop, and color for count forwards
            scanner = i;
            if (scanner > 28) {
                scanner = 0;
            }
            color = 'o';
            stop = false;

            // Count forwards
            while (stop == false) {
                if (beads[scanner] == 'w') {
                    count++;
                    scanner++;
                } else if (color == 'o') {
                    color = beads[scanner];
                    scanner++;
                    count++;
                } else if (color == beads[scanner]) {
                    count++;
                    scanner++;
                } else {
                    stop = true;
                }

                // If we are looking at the 'max' bead, we go around the  necklace and look at the first bead
                if (scanner > 28) {
                    scanner = 0;
                }
            }

            // If the count is more than the previous record, we have to store the record.
            if (count > maxcount) {
                maxcount = count;
            }
        }

        out.println(maxcount);
        out.close();
    }
}
