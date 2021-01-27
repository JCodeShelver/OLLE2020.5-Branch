// FRC Team 3770 - BlitzCreek - OLLE 2021
// Pneumatic Manager Command
// Manages MOST OF THE PNEMUATICS in one
// file. It does not manage the Shooter
// or Conveyor pneumatics. 

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

// Import Subsystems
import frc.robot.subsystems.FrontIntake;
import frc.robot.subsystems.Shooter;

// Import Constants
import frc.robot.Constants;

public class PneumaticManager extends CommandBase 
{
  // Set vars
  private final FrontIntake frontIntake;   
  
  private Constants.IntakeMovementActions actionCode;

  // ----------------------------------------------------------------------------
  // Constructor
  public PneumaticManager(FrontIntake i, Constants.IntakeMovementActions code) 
  {
    frontIntake = i;
    actionCode = code;
  }

  // ----------------------------------------------------------------------------
  // Initialization
  @Override
  public void initialize() 
  {

  }

  // ----------------------------------------------------------------------------
  // Manages Pneumatics
  @Override
  public void execute() 
  {
    if (actionCode == Constants.IntakeMovementActions.TOGGLE_INTAKE_UP_DOWN)
    {
      if (frontIntake.isOut())
        frontIntake.pullUp();
      else
        frontIntake.pushDown();
      SmartDashboard.putBoolean("Intake Pneumatic", frontIntake.isOut());
    }

    // if (actionCode == Constants.IntakeMovementActions.SHOOTER_FIRE_PISTON)
    // {
    //   if (shooter.isShooterPistonDown())
    //     shooter.shootBall();
    //   else
    //     shooter.lowerShootingPiston();
    //   SmartDashboard.putBoolean("Shooter Piston Down", shooter.isShooterPistonDown());
    // }
  }
  
  // ----------------------------------------------------------------------------
  // This commands is only needed to do one thing. Once done, it removes itself from the schedule.
  @Override
  public boolean isFinished() 
  {
    return true;
  }
}