package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.*;

// The speed of intake can be adjusted if the robot can't compress the balls enough, but compression 
// relies more on torque than speed. 
public class intake extends Command {

    public double running_speed;

    public intake(double speed) { // Declare subsystem dependencies

        requires(Robot.intake);
        running_speed = speed;

    }

    protected void initialize() {
        
    }

    protected void execute() {

        Robot.intake.run(running_speed);

    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {

        Robot.intake.end();

    }

    protected void interrupted() {
        end();
    }

}