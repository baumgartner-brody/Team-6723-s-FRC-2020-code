package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.*;

// This command takes in a speed to run at and a time to run for. It's very easy to change but it's very
// fidgety when it comes to auto-indexing. 
public class indexer extends Command {

    public double running_speed;
    public double timeout;
    public int desired_revs;
    public boolean use_revs;
    public boolean use_time;
    private final Timer timer = new Timer();

    public indexer(double speed, double time_in) { // Declare subsystem dependencies

        requires(Robot.indexer);
        running_speed = speed;
        timeout = time_in;
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
        if (timer.get() < timeout) {
            Robot.indexer.run(running_speed);
        } else {
            end();
        }
    }

    protected boolean isFinished() {
        return isTimedOut();
    }

    protected void end() {

        Robot.indexer.end();

    }

    protected void interrupted() {
        end();
    }

}