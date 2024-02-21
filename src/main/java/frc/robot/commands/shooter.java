package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.*;

// This command runs the flywheel (shooter) at the desired speed. The bool "running" tells the 
// auto-indexers to shut off while this is running. 
public class shooter extends Command {

    private final Timer timer = new Timer();
    public double running_speed;
    public double timeout;
    public boolean running = false;

    public shooter(double speed, double time) { // Declare subsystem dependencies

        requires(Robot.shooter);
        running_speed = speed;
        timeout = time;

    }

    protected void initialize() {
        timer.reset();
        timer.start();
        setTimeout(timeout);
        
    }

    protected void execute() {
        Robot.shooter.run(running_speed);
        running = true;
    }

    protected boolean isFinished() {
        return isTimedOut();
    }

    protected void end() {

        Robot.shooter.end();
        running = false;

    }

    protected void interrupted() {
        end();
    }

}