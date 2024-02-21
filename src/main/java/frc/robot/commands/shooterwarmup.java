package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.RobotMap;

// Makes sure the shooter is up to speed before a ball goes in.
public class shooterwarmup extends Command {

    public boolean warm = false;

    public shooterwarmup() {
        warm = false;
    }

    protected void initialize() {

    }

    /** Before we used to use a half second delay to try and warm the shooter up,
     *  but using its encoder velocity can provide for a much more accurate way to know
     *  the robot will get all five balls in the goal. 
     */
    protected void execute() {
        if (RobotMap.Shooter.getSelectedSensorVelocity(0) < 33000) {
            warm = false;
        } else { 
            warm = true;
        }
    }

    protected boolean isFinished() {
        return warm;
    }

    protected void end() {

    }

    protected void interrupted() {
        end();
    }

}