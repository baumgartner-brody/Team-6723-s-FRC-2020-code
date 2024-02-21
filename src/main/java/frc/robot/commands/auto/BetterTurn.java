package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.*;

// This command will spin one side of the drivetrain only for a desired amount of revolutions.
public class BetterTurn extends Command {

    private double running_speed;
    private int revs;
    private char active_side;

    public BetterTurn(double speed, char side, int revs_in) {
    	requires(Robot.drivetrain);
        running_speed = speed;
        revs = revs_in;
        active_side = side;
    	}

    protected void initialize() {
    }

    protected void execute() {
        if (active_side == 'L' && (RobotMap.LfrontMotor.getSelectedSensorPosition(0) / 4096) != revs) {
            Robot.drivetrain.tankDrive(running_speed, 0.0);
        } else if (active_side == 'R' && (RobotMap.RfrontMotor.getSelectedSensorPosition(0) / 4096) != revs) {
            Robot.drivetrain.tankDrive(0.0, running_speed);
        }

    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {

    }

    protected void interrupted() {
        end();
    }

}