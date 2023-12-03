import com.sun.xml.internal.rngom.digested.DOneOrMorePattern;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day1 {
    public static void main(String args[]) {
//        String[] lines = new String[4];
//        lines[0] = "1abc2";
//        lines[1] = "pqr3stu8vwx";
//        lines[2] = "a1b2c3d4e5f";
//        lines[3] = "treb7uchet";
        List<String> lines = new ArrayList<>();
        lines.add("two1nine");
        lines.add("eightwothree");
        lines.add("abcone2threexyz");
        lines.add("xtwone3four");
        lines.add("4nineeightseven2");
        lines.add("zoneight234");
        lines.add("7pqrstsixteen");

        int res = getCalibrationValue2(lines);

        try {
            List<String> input = Files.readAllLines(Paths.get("/Users/wangyijin/coding_challenge/AdventOfCodeInputDay1.txt"));
            int resInput = getCalibrationValue2(input);
            System.out.print(resInput);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    // Assume the letters are within one hundred and all lower case
    private static int getCalibrationValue2(List<String> lines) {
        if (lines == null || lines.size() == 0) {
            return -1;
        }

        Map<String, Integer> oneLetterMap = getOneLetterMap();
        Map<String, Integer> twoLetterMap = getTwoLetterMap();

        int sum = 0;
        for (String line: lines) {
            boolean metFirst = false;
            int firstNumber = 0;
            int curNumber = -1;
            int twoLetterNumber = -1;
            int firstTwoNumber = -1;
            boolean firstIsTwo = false;


            for (int i = 0; i < line.length(); i++) {

                char c = line.charAt(i);

                // find digit
                if (c >= '0' && c <= '9') {
                    curNumber = c - '0';

                    if (!metFirst) {
                        metFirst = true;
                        firstNumber = curNumber;
                    }

                } else {
                    // find letter -> digit
                    boolean findTwoDigitNumber = false;
                    for (String key : twoLetterMap.keySet()) {
                        if (line.substring(i).startsWith(key)) {
                            twoLetterNumber = twoLetterMap.get(key);
                            findTwoDigitNumber = true;
                            curNumber = twoLetterNumber % 10;

                            if (!metFirst) {
                                metFirst = true;
                                firstIsTwo = true;
                                firstTwoNumber = twoLetterNumber;
                            }

                            break;
                        }
                    }

                    if (!findTwoDigitNumber) {
                        for (String key : oneLetterMap.keySet()) {
                            if (line.substring(i).startsWith(key)) {
                                curNumber = oneLetterMap.get(key);

                                if (!metFirst) {
                                    metFirst = true;
                                    firstNumber = curNumber;
                                }
                                break;
                            }
                        }
                    }
                }

            }

            if (firstTwoNumber >= 0 && curNumber == -1) { // first: two digit number, last: N/S
                sum += firstTwoNumber;
            } else if (firstTwoNumber >= 0 && curNumber > -1) { // first: two digit number, last: one digit number
                sum += firstTwoNumber / 10  * 10 + curNumber;
            } else { // first: one digit number, last: one digit number
                sum += firstNumber * 10 + curNumber;
            }

        }

        return sum;
    }

    private static Map<String, Integer> getOneLetterMap() {
        Map<String, Integer> oneLetterMap = new HashMap<>();
        oneLetterMap.put("zero", 0);
        oneLetterMap.put("one", 1);
        oneLetterMap.put("two", 2);
        oneLetterMap.put("three", 3);
        oneLetterMap.put("four", 4);
        oneLetterMap.put("five", 5);
        oneLetterMap.put("six", 6);
        oneLetterMap.put("seven", 7);
        oneLetterMap.put("eight", 8);
        oneLetterMap.put("nine", 9);

        return oneLetterMap;
    }
    private static Map<String, Integer> getTwoLetterMap() {
        Map<String, Integer> twoLetterMap = new HashMap<>();
        twoLetterMap.put("ten", 10);
        twoLetterMap.put("eleven", 11);
        twoLetterMap.put("twelve", 12);
        twoLetterMap.put("thirteen", 13);
        twoLetterMap.put("fourteen", 14);
        twoLetterMap.put("fifteen", 15);
        twoLetterMap.put("sixteen", 16);
        twoLetterMap.put("seventeen", 17);
        twoLetterMap.put("eighteen", 18);
        twoLetterMap.put("nineteen", 19);
        twoLetterMap.put("twenty", 20);
        twoLetterMap.put("thirty", 30);
        twoLetterMap.put("forty", 40);
        twoLetterMap.put("fifty", 50);
        twoLetterMap.put("sixty", 60);
        twoLetterMap.put("seventy", 70);
        twoLetterMap.put("eighty", 80);
        twoLetterMap.put("ninety", 90);

        return twoLetterMap;
    }

    private static int getCalibrationValue1(List<String> lines) {
        if (lines == null || lines.size() == 0) {
            return -1;
        }

        int sum = 0;
        for (String line: lines) {
            boolean metFirst = false;
            int firstNumber = 0;
            int curNumber = 0;

            for (int i = 0; i < line.length(); i++) {
                char c = line.charAt(i);
                if (c >= '0' && c <= '9') {
                    curNumber = c - '0';

                    if (!metFirst) {
                        metFirst = true;
                        firstNumber = curNumber;
                    }
                }
            }

            sum += firstNumber * 10 + curNumber;
        }

        return sum;

    }
}
