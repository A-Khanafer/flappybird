package utility;

public class PhysicsTool {

    public final static double GRAVITY = 9.8;

    public static double yPositionEquation(double ticks, double initialYPosition, double verticalVelocity) {
        return (initialYPosition + verticalVelocity*ticks + (GRAVITY/2)*ticks*ticks);
    }

    public static double verticalVelocityEquation(double ticks, double initialVerticalVelocity){
        return (initialVerticalVelocity + GRAVITY*ticks);
    }

}

