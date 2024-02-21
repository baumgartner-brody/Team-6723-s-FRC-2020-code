package frc.robot.commands;

import frc.robot.*;
import frc.robot.commands.auto.*;
import edu.wpi.first.wpilibj.command.CommandGroup;

/** Five pt auto wasn't far off during testing but it was never used because it wasn't robust like the
 *  three pt auto. It should be solid enough to pick up the two balls but driving back was tricky 
 *  because the distance is about 25 feet from the robot. It's meant to make the robot turn 90 left 
 *  after going most of the way, pull out from the wall, turn 90 right and be able to drive straight in 
 *  and shoot. It's very rusty and will need semi-major fixes if it gets taken out of retirement. 
 */
public class five_pt_auto extends CommandGroup {

    public five_pt_auto() {
        requires(Robot.drivetrain);
        requires(Robot.intake);
        requires(Robot.shooter);
        requires(Robot.indexer);
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // NOTE: first is percent speed, second is desired revs

        // Limit swtich indexing is ON

        addSequential(new StraightDrive(-0.6, 1)); // FIRST BALL
        addParallel(new StraightDrive(0.6, -7)); // drive forward @ 60% speed for 9 revolutions ~15 ft to pick up balls in trench
            addParallel(new intake(-0.4)); // TODO - auto index)
            /*addSequential(new delay(4)); // ~ 3 sec to get first ball
            addSequential(new indexer(1, 0.1)); 
            These lines are manual indexers if the auto-indexer 
            doesn't cut it */ 

        addParallel(new StraightDrive(0.6, -9)); // SECOND BALL
            addParallel(new intake(-0.4));
            /*addSequential(new delay(3));
            addSequential(new indexer(1, 0.075)); */
        
        addSequential(new StraightDrive(-0.6, 3)); // DRIVE BACK & TURN LEFT
        RobotMap.LfrontMotor.setSelectedSensorPosition(0);
        RobotMap.RrearMotor.setSelectedSensorPosition(0);   
        addSequential(new BetterTurn(0.6, 'L', 2)); // Run L drivetrain back (won't get stuck on wall)
        RobotMap.LfrontMotor.setSelectedSensorPosition(0);
        RobotMap.RrearMotor.setSelectedSensorPosition(0);

        addSequential(new StraightDrive(-0.6, 3));
        addSequential(new BetterTurn(-0.6, 'R', 2)); // Turn Right for 2 revs via right drivetrain
        RobotMap.LfrontMotor.setSelectedSensorPosition(0);
        RobotMap.RrearMotor.setSelectedSensorPosition(0);   // TURN 90

        // Drive back a little and shoot
        addSequential(new StraightDrive(-0.6, 2));
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
