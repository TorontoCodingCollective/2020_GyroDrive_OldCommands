package frc.robot.subsystems;

import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;
import com.torontocodingcollective.subsystem.TSubsystem;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;

/**
 *
 */
public class ColorSubsystem extends TSubsystem {

    /**
     * A Rev Color Sensor V3 object is constructed with an I2C port as a
     * parameter. The device will be automatically initialized with default
     * parameters.
     */
    private final ColorSensorV3 colorSensor = new ColorSensorV3(I2C.Port.kOnboard);

    private final ColorMatch colorMatcher = new ColorMatch();

    private final Color blueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
    private final Color greenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
    private final Color redTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
    private final Color yellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);

    public ColorSubsystem() {

    }

    @Override
    public void init() {
        colorMatcher.addColorMatch(blueTarget);
        colorMatcher.addColorMatch(greenTarget);
        colorMatcher.addColorMatch(redTarget);
        colorMatcher.addColorMatch(yellowTarget);
    }

    // Periodically update the dashboard and any PIDs or sensors
    @Override
    public void updatePeriodic() {

        ColorMatchResult match = colorMatcher.matchClosestColor(colorSensor.getColor());

        String colorString = "Unknown";

        if (match.color == blueTarget) {
            colorString = "Blue";
        } else if (match.color == redTarget) {
            colorString = "Red";
        } else if (match.color == greenTarget) {
            colorString = "Green";
        } else if (match.color == yellowTarget) {
            colorString = "Yellow";
        }

        SmartDashboard.putString("Color", colorString);
        SmartDashboard.putString("Color Sensor(R, G, B)", "" + colorSensor.getRed() + ", "
                + colorSensor.getGreen() + ", " + colorSensor.getBlue());
    }

    @Override
    protected void initDefaultCommand() {
    }

}
