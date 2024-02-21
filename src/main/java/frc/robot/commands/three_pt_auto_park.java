package frc.robot.commands;

import frc.robot.*;
import frc.robot.commands.auto.StraightDrive;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class three_pt_auto_park extends CommandGroup {

    public three_pt_auto_park() {
        requires(Robot.drivetrain);
        requires(Robot.indexer);
        requires(Robot.shooter);
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        //This is just the three_pt_auto without the driving out part.

        addSequential(new StraightDrive(-0.6, 5)); // drive forward @ 60% speed for 5 revolutions ~10 ft
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
