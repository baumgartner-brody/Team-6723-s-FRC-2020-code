package frc.robot.commands;

import frc.robot.*;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class full_shooter extends CommandGroup {

    public full_shooter() {
        requires(Robot.indexer);
        requires(Robot.shooter);
        // Add Commands here:
        // e.g. addSequential(new Command1());
        // addSequential(new Command2());
        // these will run in order.

        // NOTE: first is percent speed, second is desired time.
        // The shooter will spin at -90% for 5.1 seconds 

        // Shoots all five balls
        addParallel(new shooter(-0.9, 5.1));
            for (int i = 0; i < 5; i++) {
                addSequential(new shooterwarmup());
                addSequential(new indexer(1, 0.3));
            }

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
