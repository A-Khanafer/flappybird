package utility;

public class PhysicsTool {

    public final static double GRAVITY = 17.0;

    public static double yPositionEquation(double ticks, double initialYPosition, double verticalVelocity) {
        double yPosition;
        yPosition = (initialYPosition + verticalVelocity*ticks + (GRAVITY*0.5)*ticks*ticks);
        return yPosition;
    }

}

