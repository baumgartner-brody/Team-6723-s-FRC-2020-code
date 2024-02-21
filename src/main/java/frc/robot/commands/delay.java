package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import edu.wpi.first.wpilibj.Timer;

public class delay extends Command {
    private final Timer timer = new Timer();
    private double timeout;

    public delay(double time_in) {
            timeout = time_in;
            timer.reset();
            timer.start();
            setTimeout(timeout);
    	}

    protected void initialize() {

        timer.reset();
        timer.start();
        setTimeout(timeout);
        System.out.println("delay is " + timeout);

    }

    protected void execute() {
        // Good for debugging and logging.
        SmartDashboard.putNumber("timer in delay(command):", timer.get());
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