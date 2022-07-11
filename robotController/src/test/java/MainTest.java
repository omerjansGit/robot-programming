import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class MainTest {

    /* Input:
     *  5 5
     *  1 2 N
     *  Command: RFRFFRFRF
     * */
    @Test
    void testFirstExample() {

        String cmd = "RFRFFRFRF";
        int x_max = 5, y_max = 5;
        int x_start = 1, y_start = 2;
        char direction = 'N';

        RobotController controller = new RobotController(cmd.toCharArray(), x_max, y_max, x_start, y_start, direction);

        String expectedOutput = "Report: 1 3 N";
        assertEquals(expectedOutput, Main.printNavigationReport(controller));
    }

    /* Input:
     *  5 5
     *  0 0 E
     *  Command: RFLFFLRF
     * */
    @Test
    void testSecondExample(){

        String cmd = "RFLFFLRF";
        int x_max = 5, y_max = 5;
        int x_start = 0, y_start = 0;
        char direction = 'E';

        RobotController controller = new RobotController(cmd.toCharArray(), x_max, y_max, x_start, y_start, direction);

        String expectedOutput = "Report: 3 1 E";
        assertEquals(expectedOutput, Main.printNavigationReport(controller));
    }

    /* Input:
     *  5 5
     *  0 0 N
     *  Command: FFFFFF
     * */
    @Test
    void testLeftMostCorner(){
        String cmd = "FFFFFF";

        int x_max = 5, y_max = 5;
        int x_start = 0, y_start = 0;
        char direction = 'N';

        RobotController controller = new RobotController(cmd.toCharArray(), x_max, y_max, x_start, y_start, direction);

        // Should not go out of the starting point
        String expectedOutput = "Report: 0 0 N";
        assertEquals(expectedOutput, Main.printNavigationReport(controller));

        // Change direction, stay in same place
         direction = 'W';
         controller = new RobotController(cmd.toCharArray(), x_max, y_max, x_start, y_start, direction);
        expectedOutput = "Report: 0 0 W";
        assertEquals(expectedOutput, Main.printNavigationReport(controller));
    }

    /* Input:
     *  5 5
     *  2 2 N
     *  Command: FFFFFF...
     * */
    @Test
    void testStartInMiddleReachYMin(){
        String cmd = "FFFFFFFFFFFF"; // After 3 F steps we should reach y = 0

        int x_max = 5, y_max = 5;
        int x_start = 2, y_start = 2;
        char direction = 'N';

        RobotController controller = new RobotController(cmd.toCharArray(), x_max, y_max, x_start, y_start, direction);

        // Should not go beyond 2, 0
        String expectedOutput = "Report: 2 0 N";
        assertEquals(expectedOutput, Main.printNavigationReport(controller));

    }

    /* Input:
     *  5 5
     *  2 2 N
     *  Command: FFFFFF...
     * */
    @Test
    void testStartInMiddleReachXMin(){
        String cmd = "FFFFFFFFFFFF"; // After 3 F steps we should reach x = 0

        int x_max = 5, y_max = 5;
        int x_start = 2, y_start = 2;
        char direction = 'W';

        RobotController controller = new RobotController(cmd.toCharArray(), x_max, y_max, x_start, y_start, direction);

        // Should not go beyond 0, 2
        String expectedOutput = "Report: 0 2 W";
        assertEquals(expectedOutput, Main.printNavigationReport(controller));

    }

    /* Input:
     *  10 10
     *  4 4 N
     *  Command: RRFFFFFF...
     * */
    @Test
    void testStartInMiddleReachYMax(){
        String cmd = "RRFFFFFFFFFFFFFFFFFFFFF"; // Turn south and move, after 5 F steps we should reach y = 9

        int x_max = 10, y_max = 10;
        int x_start = 4, y_start = 4;
        char direction = 'N';

        RobotController controller = new RobotController(cmd.toCharArray(), x_max, y_max, x_start, y_start, direction);

        // Should not go beyond 4, 9
        String expectedOutput = "Report: 4 9 S";
        assertEquals(expectedOutput, Main.printNavigationReport(controller));

    }

    /* Input:
     *  10 10
     *  4 4 N
     *  Command: RFFFFFF...
     *
     * */
    @Test
    void testStartInMiddleReachXMax(){
        String cmd = "RFFFFFFFFFFFFFFFFFFFFF"; // Turn east and move, after 5 F steps we should reach x = 9

        int x_max = 10, y_max = 10;
        int x_start = 4, y_start = 4;
        char direction = 'N';

        RobotController controller = new RobotController(cmd.toCharArray(), x_max, y_max, x_start, y_start, direction);

        // Should not go beyond 9, 4
        String expectedOutput = "Report: 9 4 E";
        assertEquals(expectedOutput, Main.printNavigationReport(controller));

    }
}