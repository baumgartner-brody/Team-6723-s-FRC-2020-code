// RobotBuilder Version: 2.0

/** Do not put any code or make any change in
 * the blocks indicating autogenerated code or it will be lost on an
 * update. Deleting the comments indicating the section will prevent
 * it from being updated in the future.
*/

package frc.robot.commands;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.*;


public class flip_drivetrain extends Command {

    public flip_drivetrain() {

        requires(Robot.drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        Robot.oi.direction *= -1;
    }

    // Making this boolean return true makes this command run for one tick then shut off automatically. 
    // "An Anthony Welch™ Idea"
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {

    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	
    	end(); 
    }   
}