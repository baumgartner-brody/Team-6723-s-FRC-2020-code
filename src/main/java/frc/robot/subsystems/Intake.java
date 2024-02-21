// RobotBuilder Version: 2.0

/** Do not put any code or make any change in
 * the blocks indicating autogenerated code or it will be lost on an
 * update. Deleting the comments indicating the section will prevent
 * it from being updated in the future.
*/

package frc.robot.subsystems;

import frc.robot.*;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.*;


/* This class is used to configure the Robots Drivetrain*/

@SuppressWarnings("unused")
public class Intake extends Subsystem {

    private final TalonSRX intake = RobotMap.Intake;

    public void initDefaultCommand() {

    }

    public void run(double speed) {

        intake.set(ControlMode.PercentOutput, speed);

    }
    
    public void end() {

        intake.set(ControlMode.PercentOutput, 0);

    }
} 

