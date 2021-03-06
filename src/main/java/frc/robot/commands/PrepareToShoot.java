// FRC Team 3770 - BlitzCreek - OLLE 2020.5
// Prepare to Shoot command
// Prepares shooter motor for shooting.
// Motor speed set using vision feedback.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.XboxController;

// Import Subsystems
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.VisionPID;

// Import Constants
import frc.robot.Constants;

public class PrepareToShoot extends CommandBase
{
  // Set vars
  private final Shooter   shooterSystem;
  private final VisionPID visionPID;

  public Joystick leftStick, rightStick;
  public XboxController controller;

  public boolean ballInPlace, RPMGood, shooterPistonUp, XGood;
  public double Distance, RPM;

  // ----------------------------------------------------------------------------
  // Constructor:  Capture time and motor level for straight drive
  public PrepareToShoot(Shooter s, VisionPID v)
  {
    // Capture references to existing robot subsystems.  Define them as requirements.
    shooterSystem   = s;
    visionPID       = v;
    
    addRequirements(shooterSystem);
  }

  // ----------------------------------------------------------------------------
  // Initialization
  public void initialize() 
  {
    visionPID.enable();

    RPMGood    = false;
    XGood      = false;
    
    Distance   = 0;
    RPM        = 0;

    controller = new XboxController(Constants.XBOX_CONTROLLER_USB_PORT);

    leftStick = new Joystick(Constants.LEFT_STICK_USB_PORT);
    rightStick = new Joystick(Constants.RIGHT_STICK_USB_PORT);
  }
  
  // --------------------------------------------------------------------------
  // 
  public void execute() 
  {
    Distance = yToDistanceFormula(visionPID.getYValue());
    SmartDashboard.putNumber("Distance from Target", Distance);

   // RPM = distanceToRPMFormula(Distance);
    
    if (rightStick.getRawButton(5))
      RPM = 1000;
    else
      RPM = 3700;

    RPM += leftStick.getRawAxis(3) * 200;

    shooterSystem.setSetPoint(RPM);
    shooterSystem.spinToSetPoint();

    visionPID.LEDon();
    Constants.shooterSystemActive = true;
    shooterSystem.updateBallInShooter();
    visionPID.getVisionData();

    if (Math.abs(visionPID.getOutput()) <= 0.05)
      XGood = true;
    else
      XGood = false;

    if (Math.abs(shooterSystem.getSetPoint() - shooterSystem.getRPM()) <= 100)
      RPMGood = true;
    else
      RPMGood = false;
  
    if (controller.getBumper(Hand.kRight))
    {
      System.out.println("Firing");
      shooterSystem.shootBall();
      //shooterPistonUp = true;
    }
    else
    {
      System.out.println("Lowering");
      shooterSystem.lowerShootingPiston();
      //shooterPistonUp = false;
    }
  }
  
  // --------------------------------------------------------------------------
  // 
  public boolean isFinished() 
  {
    return false;
  }
  
  // --------------------------------------------------------------------------
  // Convert distance away to RPM
  private double distanceToRPMFormula(double d)
  {
    return -3700;
  }
  
  // --------------------------------------------------------------------------
  // Convert Limelight's Y to distance away
  private double yToDistanceFormula(double y)
  {
    //Bar in robo Room
    //return 128 - 5.96 * (y) + 0.172 * (y * y);

    //Actual target on test frame
    return 90.2 - 1.33 * y + 0.213 * y * y;
  }

  // --------------------------------------------------------------------------
  //
  @Override 
  public void end(boolean interrupted)      
  { 
    shooterSystem.stop();
    Constants.shooterSystemActive = false;
    visionPID.LEDoff();
  }

  // --------------------------------------------------------------------------
  //
  protected void interrupted() 
  {
    shooterSystem.stop();
  }
}