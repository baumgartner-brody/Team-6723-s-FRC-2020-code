package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.*;

// All this command actually does is turn the limit switch indexing off for four seconds when the 
// shooter is running. 
public class toggle_auto_index extends Command {

    private final Timer timer = new Timer();
    private double timeout = 4.0;

    public toggle_auto_index() {
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        timer.reset();
        timer.start();
        setTimeout(timeout);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if (timer.get() < timeout) {
            Robot.oi.shooter_running = true;
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
        
        // Turns the limit switch indexing back on
    	Robot.oi.shooter_running = false;
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	
    	end(); // Will never get called because ArcadeDrive is default
    }
    
} // Close ArcadeDrive Class
