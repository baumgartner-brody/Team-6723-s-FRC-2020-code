/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.*;
import frc.robot.subsystems.*;

/**
 * This is a demo program showing the use of OpenCV to do vision processing. The
 * image is acquired from the USB camera, then a rectangle is put on the image
 * and sent to the dashboard. OpenCV has many methods for different types of
 * processing.
 */
public class Robot extends TimedRobot {  
  
  /* These declarations are for the autonomous chooser button on the SmartDashboard
  *  If it ever doesn't show up try either running the code on another computer or 
  *  renaming the variable "autochooser" to something else
  */
  Command autonomousCommand;
  Command fiver;
  public SendableChooser<Command> autochooser;
  
  public static OI oi;
  public static Drivetrain drivetrain;
  public static Intake intake;
  public static Climber climber;
  public static Winch winch;
  public static Indexer indexer;
  public static Shooter shooter;

  /* "offset" is the difference between the left encoder's rotation reading and the right encoder's 
  *  rotation reading and offset speed is that number divided by 10. These numbers are mainly used in 
  *  the StraightDrive command. Their accuracy depends very much on the robot's mechanical integrity 
  *  and their is a very good chance StraightDrive or "offset_speed" will need tinkering with.
  */
  public static Double offset; 
  public static Double offset_speed;

  /* Simple thread to plot sensor velocity and such */
	encoder_velocity encoder_data;

  public void robotInit() {

    RobotMap.init();

    drivetrain = new Drivetrain();
    intake = new Intake();
    climber = new Climber();
    winch = new Winch();
    indexer = new Indexer();
    shooter = new Shooter();
    
    oi = new OI(); // Important that OI gets called after commands
    
    CameraServer.getInstance().startAutomaticCapture();
    CameraServer.getInstance().startAutomaticCapture(); // second camera 

    /* Declares all the options that "autochooser" has.     
     * The "fiver" variable is in charge of declaring the indexer's autonomous speed 
     * and making sure the limint switches are not able to shut off the running auto 
     * command */
    autochooser = new SendableChooser<Command>();
    autochooser.setDefaultOption("3 ball with 7 second delay", new three_pt_auto_delay());
    autochooser.addOption("3 ball auto", new three_pt_auto());
    autochooser.addOption("3 ball from right", new three_pt_auto_right());
    autochooser.addOption("3 ball & park", new three_pt_auto_park());
    autochooser.addOption("5 ball auto", new five_pt_auto());
    SmartDashboard.putData("auto chooser", autochooser);
    fiver = new intake(-0.4);

  }

  public void disabledInit() {
    Scheduler.getInstance().removeAll(); // Clears the current command (smooth trans from auto to tele)
    Scheduler.getInstance().close();
    SmartDashboard.putData("auto chooser", autochooser);
  }

  public void disabledPeriodic() {

  }

  public void autonomousInit() {
    // Starts the encoder data thread 
    encoder_data = new encoder_velocity(this);
    new Thread(encoder_data).start();
    
    // Prints which autonomous is selected - mainly for troubleshooting and diagnostics
    System.out.println("chooser.getSelected is " + autochooser.getSelected());
    autonomousCommand = (Command) autochooser.getSelected();
    autonomousCommand.start();
    
  }

  public void autonomousPeriodic() {

    // Scheduler should run selected auto command
    Scheduler.getInstance().run();
    System.out.println("autochooser = " + autochooser.getSelected());

    /** This will indicate whether or not the auto indexing system is running during autonomous.
     * Make sure it only runs while the five-ball auto command is running, because it has the ability
     * to steal the subsystem dependencies and shut auto off.  
     */
    SmartDashboard.putBoolean("five_pt_auto", fiver.isRunning()); 
    if (fiver.isRunning()) {
      oi.OtherOI();
    }

  }

  public void teleopInit() {

    // Starts encoder data thread
    encoder_data = new encoder_velocity(this);
    new Thread(encoder_data).start();
  }

  public void teleopPeriodic() {

    /** Runs AcadeDrive and displays all the variable readings on the SmartDashboard. */
    Scheduler.getInstance().run();
    SmartDashboard.putNumber("POV", oi.joystick.getPOV());
    oi.OtherOI();
    SmartDashboard.putBoolean("limit_switch0", RobotMap.limit_switch0.get());
    SmartDashboard.putBoolean("limit_switch1", RobotMap.limit_switch1.get());
    SmartDashboard.putBoolean("indexer.isRunning()", oi.indexer.isRunning());
    SmartDashboard.putData("auto chooser", autochooser);

  }

  public void testPeriodic() {
    /** I'm not exactly sure what the testPeriodic method was intended to do, but I'm pretty sure
     * you can create custom timings and manipulate field element variables (like the color wheel)
     * to emulate a match.
     */
  }
}


