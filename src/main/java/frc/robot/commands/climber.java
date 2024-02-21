package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.*;

public class climber extends Command {

    public double running_speed;

    public climber(double speed) { // Declare subsystem dependencies

        requires(Robot.climber);
        running_speed = speed;

    }

    protected void initialize() {
        
    }

    protected void execute() {

        Robot.climber.run(running_speed);

    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {

        Robot.climber.end();

    }

    protected void interrupted() {
        end();
    }

}