package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.*;

public class StraightDrive extends Command {

    private boolean finished = false;
    private int specified_revs;
    private double specified_speed;
    private final Timer timer = new Timer();
    private final double timeout = 10.0; // The most time this command should ever take to run is 10

    // Takes in a desired speed and a desired number of revolutions
    public StraightDrive(double speed, int revs) {
        requires(Robot.drivetrain);
        RobotMap.Mutton.reset();
        specified_revs = revs;
        specified_speed = speed;
        timer.reset();
        timer.start();
        setTimeout(timeout);
    }

    protected void initialize() {
        timer.reset();
        timer.start();
        setTimeout(timeout);
    }

    protected void execute() {
        /** This runs off the reading from the left encoder to determine completion, but to drive straight
         *  it gives the side lagging by one revolution a very small boost and the non-lagging side an even 
         *  smaller, half-strength boost. Will we ever build a robot that drives straight? Probably not, but
         *  if adjusted right this command can hide that. 
         */
        if (((RobotMap.LfrontMotor.getSelectedSensorPosition(0) / 4096) != specified_revs) && timer.get() < timeout) { // works forward & backwards
            if ((RobotMap.LfrontMotor.getSelectedSensorPosition(0) / 4096) > (RobotMap.RfrontMotor.getSelectedSensorPosition(0) / 4096)) {
                // Right side is lagging
                Robot.drivetrain.tankDrive((-specified_speed) + (Robot.offset_speed / 4), -specified_speed - (Robot.offset_speed / 8));
            } else if ((RobotMap.LfrontMotor.getSelectedSensorPosition(0) / 4096) < (RobotMap.RfrontMotor.getSelectedSensorPosition(0) / 4096)) {
                // Left side is lagging
                Robot.drivetrain.tankDrive(-specified_speed - (Robot.offset_speed / 8), (-specified_speed) + (Robot.offset_speed / 4));
            } else {
                // No lag
                Robot.drivetrain.tankDrive(-specified_speed, -specified_speed);
            }
        } else {
            finished = true; 
        }
    }

    protected boolean isFinished() {
        return finished;
    }

    protected void end() {

    }

    protected void interrupted() {
        end(); // If limit switches cancel auto, get rid of this
    }

}