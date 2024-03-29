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
public class Shooter extends Subsystem {

    private final TalonSRX shooter = RobotMap.Shooter;

    // Put methods for controlling this subsystem here. Call these from Commands.

    public void initDefaultCommand() {

    }

    public void run(double speed) {

        shooter.set(ControlMode.PercentOutput, speed);

    }
    
    public void end() {

        shooter.set(ControlMode.PercentOutput, 0);

    } 
} 

