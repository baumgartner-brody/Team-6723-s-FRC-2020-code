package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.*;

public class OI {
    public Joystick joystick;
    public double direction = 1; // pos by default
    public JoystickButton button2;
    public JoystickButton button5;
    public JoystickButton button7;
    public JoystickButton button9;
    public JoystickButton button10;
    public JoystickButton button11;
    public JoystickButton button12;
    public Command indexer = new indexer(0.75, 0.1);
    public boolean auto_index = true;
    public boolean shooter_running = false;
    public int time_pressed = 0;

    public OI() {

        joystick = new Joystick(0); 

        button2 = new JoystickButton(joystick, 2);
        button2.toggleWhenPressed(new flip_drivetrain());

        button5 = new JoystickButton(joystick, 5);
        button5.whileHeld(new winch(1));

        button7 = new JoystickButton(joystick, 7);
        button7.toggleWhenPressed(new intake(-0.35));

        button9 = new JoystickButton(joystick, 9);
        button9.toggleWhenPressed(new shooter(-0.9, 10));

        button10 = new JoystickButton(joystick, 10);
        button10.toggleWhenPressed(new full_shooter()); // should be auto
        button10.toggleWhenPressed(new toggle_auto_index());

        button11 = new JoystickButton(joystick, 11);
        button11.whileHeld(new indexer(-0.7, 3)); // variables have to be retreived in Robot because OI is static
        button11.whileHeld(new intake(0.3));

        button12 = new JoystickButton(joystick, 12);
        button12.toggleWhenPressed(new indexer(0.8, 0.05));
       
    }

    // This gets the actual joystick axis value needed to drive the robot.
    public Joystick getJoystick() {
        return joystick;
    }

    public void OtherOI() {

        /** The reason auto-indexer is turned off during these three events is because
         *  button 11 controls the reverse intake, shooter_running is whether or not the shooter is 
         *  running and button12 is the manual index button. 
         */
        if (button11.get() || shooter_running || button12.get()) {
              auto_index = false; // don't run switches on way out OR when shooting OR manually indexing
        } else {
            auto_index = true;
        }

        SmartDashboard.putBoolean("LIMIT SWITCHES ARE:", auto_index);
        SmartDashboard.putBoolean("shooter running", shooter_running);
        SmartDashboard.putNumber("Time switches pressed", time_pressed);

        // Auto indexing with limit switches

        //*****************************************************************************/
        /* The first thing to test is whether or not the auto-indexing system is on.  */
        /* Second you test if "time_pressed" is 0. "time_pressed" will increment when */
        /* the limit switches are being pressed / held down, making it not 0. Then    */
        /* when both limit switches return false, it will reset back to 0, allowing   */
        /* the auto-indexer to run again. This prevents jams that would otherwise     */
        /* cause the switches to get stuck open and index all five balls all the way  */
        /*                                                                            */
        /*                          "An Anthony Welch™ Idea"                          */
        /*                                  © 1998                                    */
        /*                                                                            */
        //*****************************************************************************/

        if (auto_index) {
            if (time_pressed == 0) { // Only run the index if the limit switches are not stuck? (test)
                if ((RobotMap.limit_switch0.get() || RobotMap.limit_switch1.get()) && !indexer.isRunning()) {
                    time_pressed += 1; // Increment time pressed
                    indexer.start();
                }
            } else if (!RobotMap.limit_switch0.get() && !RobotMap.limit_switch1.get()) {
                time_pressed = 0;
            }
        }

        /** Runs climber based on POV (little joystick) value
         *  The POV works by returning an angle between 0-360 in 45 degree increments
         *  I put a range of 135 degrees for up and down because it's hard to get the POV position exact 
         *  while driving */        
        if (joystick.getPOV() == 315.0 || joystick.getPOV() == 0.0 || joystick.getPOV() == 45.0) {
            Robot.climber.run(0.5);
          } else if (joystick.getPOV() <= 225.0 && joystick.getPOV() >= 135.0) {
            Robot.climber.run(-0.2);
          } else {
              Robot.climber.end();
          }
    }}