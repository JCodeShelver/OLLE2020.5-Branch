// FRC Team 3770 - BlitzCreek - OLLE 2020
// Pneumatic Manager Command
// Manages MOST OF THE PNEMUATICS in one
// file. It does not manage the Shooter
// or Conveyor pneumatics. 

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.subsystems.FrontIntake;
import frc.robot.subsystems.Shooter;

import frc.robot.Constants;

public class PneumaticManager extends CommandBase 
{
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
  //  
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
  // 
  @Override
  public boolean isFinished() 
  {
    return true;
  }
}