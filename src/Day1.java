public class Day1 {
    public static void main(String args[]) {
        String[] lines = new String[4];
        lines[0] = "1abc2";
        lines[1] = "pqr3stu8vwx";
        lines[2] = "a1b2c3d4e5f";
        lines[3] = "treb7uchet";

        int res = getCalibrationValue(lines);
        System.out.print(res);
    }

    private static int getCalibrationValue(String[] lines) {

        if (lines == null || lines.length == 0) {
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
