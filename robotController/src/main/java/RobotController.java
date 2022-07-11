import java.util.Arrays;

public class RobotController {

    private final char[] commands;
    private final int xWide;
    private final int yDeep;
    private final int xStart;
    private final int yStart;
    private final char direction;

    public RobotController(char[] commands, int xWide, int yDeep, int xStart, int yStart, char direction) {
        this.commands = commands;
        this.xWide = xWide;
        this.yDeep = yDeep;
        this.xStart = xStart;
        this.yStart = yStart;
        this.direction = direction;
    }

    public char[] getCommands() {
        return commands;
    }

    public int getxWide() {
        return xWide;
    }

    public int getyDeep() {
        return yDeep;
    }

    public int getxStart() {
        return xStart;
    }

    public int getyStart() {
        return yStart;
    }

    public char getDirection() {
        return direction;
    }

    @Override
    public String toString() {
        return "RobotController{" +
                "commands=" + Arrays.toString(commands) +
                ", xWide=" + xWide +
                ", yDeep=" + yDeep +
                ", xStart=" + xStart +
                ", yStart=" + yStart +
                ", direction=" + direction +
                '}';
    }
}
