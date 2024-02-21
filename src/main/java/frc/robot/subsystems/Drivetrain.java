// RobotBuilder Version: 2.0

/** Do not put any code or make any change in
 * the blocks indicating autogenerated code or it will be lost on an
 * update. Deleting the comments indicating the section will prevent
 * it from being updated in the future.
*/

package frc.robot.subsystems;

import frc.robot.*;
import frc.robot.commands.ArcadeDrive;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/* This class is used to configure the Robots Drivetrain*/

@SuppressWarnings("unused")
public class Drivetrain extends Subsystem {

    private final WPI_TalonSRX frontLMotor = RobotMap.LfrontMotor;
    private final WPI_TalonSRX frontRMotor = RobotMap.RfrontMotor;
    private final WPI_TalonSRX rearRMotor = RobotMap.RrearMotor;
    private final WPI_TalonSRX rearLMotor = RobotMap.LrearMotor;
    private final DifferentialDrive robotDrive41 = RobotMap.RobotDrive;

    public void initDefaultCommand() {

        setDefaultCommand(new ArcadeDrive());

    }

    @Deprecated

    // Takes in raw input from the joystick's axes and uses it to control the robot. 
    public void arcadeDrive(Joystick stick){
        robotDrive41.arcadeDrive(-stick.getY() * Robot.oi.direction, stick.getX()); 
    }

    public void tankDrive(Double left, Double right) {

        robotDrive41.tankDrive(-left, -right);

    } 
    
    //Change speed from neg to pos depending on motor configuration
    
    public void driveStraight(Double speed){
    	
    	robotDrive41.tankDrive(-speed, -speed);
    }
    
    
} // Close Drivetrain Class

