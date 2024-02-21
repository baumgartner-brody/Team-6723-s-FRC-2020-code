package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.*;

// This command can be a good tool to make turns in auto. It takes in a speed for each side of the 
// drivetrain and runs for a specified amount of time. 
public class Turn extends Command {

    private double left_speed;
    private double timeout;
    private double right_speed;
    private final Timer timer = new Timer();

    public Turn(double Lspeed, double Rspeed, double time_in) {
    	requires(Robot.drivetrain);
        left_speed = Lspeed;
        right_speed = Rspeed;
        timeout = time_in;
    	}

    protected void initialize() {
        timer.reset();
        timer.start();
        setTimeout(timeout);
    }

    protected void execute() {
        if (timer.get() < timeout) {
            Robot.drivetrain.tankDrive(left_speed, right_speed);
        }

    }

    protected boolean isFinished() {
        return isTimedOut();
    }

    protected void end() {

    }

    protected void interrupted() {
        end();
    }

}