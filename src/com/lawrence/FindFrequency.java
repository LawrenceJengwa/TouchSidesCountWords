package com.lawrence;

import java.io.File;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FindFrequency {

    HashMap<String, Integer> map = new HashMap<String, Integer>();

    public static void main(String[] args){

        FindFrequency findFrequency = new FindFrequency();

        Scanner INPUT_TEXT = findFrequency.readFile();

        findFrequency.analyse(INPUT_TEXT);

        findFrequency.showResults();

    }

    /**
     * logic to count the occurences of words matched by REGEX in a scanner that
     * loaded some text
     *
     * @param scanner
     *            the scanner holding the text
     */
    public void analyse(Scanner scanner) {

        String pattern = "[a-zA-Z'-]+";
        Pattern r = Pattern.compile(pattern);

        while (scanner.hasNext()) {
            // read next word
            String Stringcandidate = scanner.next();

            // see if pattern matches (boolean find)
            Matcher matcher = r.matcher(Stringcandidate);
            if (matcher.find()) {
                String matchedWord = matcher.group();
                //System.out.println(matchedWord); //check what is matched
                this.addWord(matchedWord);

            }

        }
        scanner.close();// Close your Scanner.
    }

    /**
     * adds a word to the <word,count> Map if the word is new, a new entry is
     * created, otherwise the count of this word is incremented
     */
    public void addWord(String matchedWord) {

        if (map.containsKey(matchedWord)) {
            // increment occurrence
            int occurrence = map.get(matchedWord);
            occurrence++;
            map.put(matchedWord, occurrence);
        } else {
            // add word and set occurrence to 1
            map.put(matchedWord, 1);
        }

    }

    /**
     * reads a file from disk and returns a scanner to analyse it
     *
     * @return the file from disk as scanner
     */
    public Scanner readFile() {

        Scanner scanner = null;

        //use that for reading a file from disk
          try { scanner = new Scanner(new
                  File("C:\\Users\\WarAndPeace.txt")).useDelimiter(" ");
          } catch (Exception e) {
          e.printStackTrace(); }


       // scanner = new Scanner("auto bush trumped her tomato in the petunia auto");

        return scanner;
    }

    /**
     * prints the matched words and their occurrences
     * in a readable way
     */
    public void showResults() {

        for (HashMap.Entry<String, Integer> matchedWord : map.entrySet()) {
            int occurrence = matchedWord.getValue();
            System.out.print("\"" + matchedWord.getKey() + "\" appears " + occurrence);
            if (occurrence > 1) {
                System.out.print(" times\n");
            } else {
                System.out.print(" time\n");
            }
        }

        // or as the new Java 8 lambda expression
        // map.forEach((word,occurrence)->System.out.println("\"" + word + "\"
        // appears " + occurrence + " times"));
    }
}

// DONE seperate reading a file, analysing the file and
// word-frequency-counting-logic in different
// methods
// Done implement <word,count> Map and logic to add new and known(to the map)
