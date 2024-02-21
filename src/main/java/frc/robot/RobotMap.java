package frc.robot;

import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import edu.wpi.first.wpilibj.Solenoid;

public class RobotMap {
    public static WPI_TalonSRX LfrontMotor;
    public static WPI_TalonSRX RfrontMotor;
    public static WPI_TalonSRX LrearMotor;
    public static WPI_TalonSRX RrearMotor;
    public static TalonSRX Intake;
    public static VictorSP Climber_motor;
    public static VictorSP Winch_motor;
    public static TalonSRX Indexer;
    public static TalonSRX Shooter;
    public static AnalogGyro Mutton;

    public static DigitalInput limit_switch0;
    public static DigitalInput limit_switch1;

    public static DifferentialDrive RobotDrive;

    public static Solenoid solenoid;

    public static void init() {

        /** This stuff should be pretty well labeled and straightforward. There is no difference
         *  between a "WPI_TalonSRX" and an actual "Talon_SRX," the drivetrain motors just have to be
         *  "WPI_TalonSRX" for the ArcadeDrive command to work. The port numbers of the Talons is 
         *  determined in the Pheonix Tuner made by CTRE. 
         *  Read more about / actually download the firmware at: 
         *  https://www.ctr-electronics.com/talon-srx.html#product_tabs_technical_resources
         */
        LfrontMotor = new WPI_TalonSRX(0);
        RfrontMotor = new WPI_TalonSRX(1); 
        LrearMotor = new WPI_TalonSRX(2);
        RrearMotor = new WPI_TalonSRX(3);
        Intake = new TalonSRX(4);
        Shooter = new TalonSRX(5);
        Indexer = new TalonSRX(6); 
        Winch_motor = new VictorSP(0);
        Climber_motor = new VictorSP(1);

        limit_switch0 = new DigitalInput(0);
        limit_switch1 = new DigitalInput(1);

        LfrontMotor.configFactoryDefault();
        LrearMotor.configFactoryDefault();
        RfrontMotor.configFactoryDefault();
        RrearMotor.configFactoryDefault();
        Intake.configFactoryDefault();
        Indexer.configFactoryDefault(); 
        Shooter.configFactoryDefault();

        LrearMotor.follow(LfrontMotor);
        RrearMotor.follow(RfrontMotor);

        LrearMotor.setInverted(false);
        RrearMotor.setInverted(true);

        LrearMotor.setInverted(InvertType.FollowMaster);
        RrearMotor.setInverted(InvertType.FollowMaster); 

        RobotDrive = new DifferentialDrive(LfrontMotor, RfrontMotor);

        RobotDrive.setSafetyEnabled(true);
        RobotDrive.setExpiration(0.1);
        RobotDrive.setMaxOutput(1);

        // © 1993 Nintendo of America 

        Mutton = new AnalogGyro(0);
        Mutton.reset();
        Mutton.calibrate();

    }
}
//https://previews.123rf.com/images/luislouro/luislouro1109/luislouro110900118/10702334-large-fitness-man-looking-at-a-big-sausage-isolated-in-white.jpg