package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.*;

public class winch extends Command {

    public double running_speed;

    public winch(double speed) { // Declare subsystem dependencies

        requires(Robot.winch);
        running_speed = speed;

    }

    protected void initialize() {
        
    }

    protected void execute() {

        Robot.winch.run(running_speed);

    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {

        Robot.winch.end();

    }

    protected void interrupted() {
        end();
    }

}