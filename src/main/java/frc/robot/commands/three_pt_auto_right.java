package frc.robot.commands;

import frc.robot.*;
import frc.robot.commands.auto.*;
import edu.wpi.first.wpilibj.command.CommandGroup;

/** This command was intended to be a solution for being paired with a team who also had to start 
 *  in the middle. It aims to drive the robot halfway to the goal (starting from the right side of the 
 *  middle), turn 45 degrees towards the goal, drive the rest of the way and shoot. Whether or not it 
 *  works I have no idea, I'm not sure we ever once used it. 
 */
public class three_pt_auto_right extends CommandGroup {

    public three_pt_auto_right() {
        requires(Robot.drivetrain);
        requires(Robot.indexer);
        requires(Robot.shooter);
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // DRIVE STRAIGHT HALFWAY TO THE GOAL (START FROM RIGHT SIDE)
        RobotMap.LfrontMotor.setSelectedSensorPosition(0);
        RobotMap.RrearMotor.setSelectedSensorPosition(0);
        addSequential(new StraightDrive(-0.6, 3));

        RobotMap.LfrontMotor.setSelectedSensorPosition(0);
        RobotMap.RrearMotor.setSelectedSensorPosition(0);   // TURN 45
        addSequential(new Turn(0.6, -0.6, 0.3)); // Run right side of drivetrain for .3 seconds

        // DRIVE THE REST OF THE WAY AND SHOOT
        RobotMap.LfrontMotor.setSelectedSensorPosition(0);
        RobotMap.RrearMotor.setSelectedSensorPosition(0);
        addSequential(new StraightDrive(-0.6, 3));
        addSequential(new full_shooter());

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
