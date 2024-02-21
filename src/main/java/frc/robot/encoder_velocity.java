package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class encoder_velocity implements Runnable {
  Robot robot;

  public encoder_velocity(Robot robot) {
    this.robot = robot;

    // Resets the gyro and the encoders to zero rotations/ degrees
    RobotMap.LfrontMotor.setSelectedSensorPosition(0);
    RobotMap.RrearMotor.setSelectedSensorPosition(0);
    RobotMap.Shooter.setSelectedSensorPosition(0);
    RobotMap.Mutton.reset();
  }

  public void run() {
    /**
     * Speed up network tables, this is a test project so eat up all of
     * the network possible for the purpose of this test.
     */

    while (true) {
      /* Yield for a Ms or so - this is not meant to be accurate */
      try {
        Thread.sleep(1);
      } catch (Exception e) {
        /* Do Nothing */
      }

      /* Grab the latest signal update from our 1ms frame update 
       * Calculates and logs all the encoder data. Encoders are measured in encoder units and work 
       * because only a small arc of the actual output shaft is magnetic. Using 4096 encoder units per
       * rotation, one rotation equates to about 23 inches, you may adjust this to get a cleaner number,
       * but be wary all auto routines are measured by desired encoder revolutions! 
      */
      double left_velocity = RobotMap.LfrontMotor.getSelectedSensorVelocity(0);
      double left_position = RobotMap.LfrontMotor.getSelectedSensorPosition(0) % 4096;
      double left_rotations = RobotMap.LfrontMotor.getSelectedSensorPosition(0) / 4096;
      double right_velocity = RobotMap.RrearMotor.getSelectedSensorVelocity(0);
      double right_position = RobotMap.RrearMotor.getSelectedSensorPosition(0) % 4096;
      double right_rotations = RobotMap.RrearMotor.getSelectedSensorPosition(0) / 4096;
      Robot.offset = Math.abs(left_rotations) - Math.abs(right_rotations); 
      Robot.offset_speed = Robot.offset / 10;
      
      double shooter_vel = RobotMap.Shooter.getSelectedSensorVelocity(0);
      double shooter_pos = RobotMap.Shooter.getSelectedSensorPosition(0) % 4096;
      double shooter_rots = RobotMap.Shooter.getSelectedSensorPosition(0) / 4096; 
      
      SmartDashboard.putNumber("L_vel", left_velocity);
      SmartDashboard.putNumber("L_pos", left_position);
      SmartDashboard.putNumber("L_rotations", left_rotations);
      SmartDashboard.putNumber("R_vel", right_velocity);
      SmartDashboard.putNumber("R_pos", right_position);
      SmartDashboard.putNumber("R_rotations", right_rotations);
      SmartDashboard.putNumber("shooter_velocity", shooter_vel);
      SmartDashboard.putNumber("shooter_position", shooter_pos);
      SmartDashboard.putNumber("shooter_rotations", shooter_rots); 
      SmartDashboard.putNumber("offset", Robot.offset);
      SmartDashboard.putNumber("offset_speed", Robot.offset_speed);
      SmartDashboard.putNumber("Gyro Angle", RobotMap.Mutton.getAngle());
    }
  }
}