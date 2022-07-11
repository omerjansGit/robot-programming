import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        // Driver code
        RobotController controller = readInput();

        System.out.println(printNavigationReport(controller));
    }

    /*
     * Reads input and returns a controller object based on that input.
     *   Input example:
     *    5 5
     *    1 2 N
     *    RFRFFRFRF
     *
     * */
    static RobotController readInput() throws IOException {
        char direction;
        int xWide, yDeep, xStart, yStart;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String lines = br.readLine();
        String[] strs = lines.trim().split("\\s+");
        xWide = Integer.parseInt(strs[0]);
        yDeep = Integer.parseInt(strs[1]);

        lines = br.readLine();
        strs = lines.trim().split("\\s+");
        xStart = Integer.parseInt(strs[0]);
        yStart = Integer.parseInt(strs[1]);
        direction = strs[2].charAt(0);

        // Dont allow 0 or negative size field
        if (xWide <= 0 || yDeep <= 0) {
            throw new IllegalArgumentException("Enter valid room size.");
        }
        if (xStart >= xWide || yStart >= yDeep) {
            throw new IllegalArgumentException("Starting coordinates cannot be outside the room!");
        }

        lines = br.readLine();
        char[] commands = new char[lines.length()];
        for (int i = 0; i < lines.length(); i++) {
            commands[i] = lines.charAt(i);
        }

        return new RobotController(commands, xWide, yDeep, xStart, yStart, direction);
    }

    /*
     *  Print the report based on the properties of the robot controller.
     *  Right direction I interpret as 90-degrees rotation clockwise, and
     *  left direction I interpret as 90-degrees rotation anti-clockwise.
     *  E.g.
     *  If current direction is N then 'L' changes direction to W and 'R' to E,
     *  if current direction is E then 'L' changes direction to N and 'R' to S,
     *  etc.
     * */
    static String printNavigationReport(RobotController controller) {

        // Assume default direction is N (0)
        int d = controller.getDirection() == 'N' ? 0 : controller.getDirection() == 'E' ? 1 :
                controller.getDirection() == 'S' ? 2 : controller.getDirection() == 'W' ? 3 : 0;

        int currentX = controller.getxStart();
        int currentY = controller.getyStart();

        for (int i = 0; i < controller.getCommands().length; i++) {
            if (controller.getCommands()[i] == 'R') {
                d = (d + 1) % 4;
            } else if (controller.getCommands()[i] == 'L') {
                d = (4 + d - 1) % 4;
            } else if (controller.getCommands()[i] == 'F') {
                // Change x and y coordinates according to current direction.
                if (d == 0) {
                    if (currentY > 0) { // Boundary check
                        currentY--;
                    }
                } else if (d == 1) {
                    if (currentX < controller.getxWide() - 1) { // Boundary check
                        currentX++;
                    }
                } else if (d == 2) {
                    if (currentY < controller.getyDeep() - 1) { // Boundary check
                        currentY++;
                    }
                } else if (d == 3) {
                    if (currentX > 0) { // Boundary check
                        currentX--;
                    }
                }
            }

        }
        char direction = d == 0 ? 'N' : d == 1 ? 'E' : d == 2 ? 'S' : d == 3 ? 'W' : 'N';
        return "Report: " + (currentX) + " " + (currentY) + " " + direction;
    }

}
