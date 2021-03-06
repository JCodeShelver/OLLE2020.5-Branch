// FRC Team 3770 - BlitzCreek - OLLE 2020.5
// Autonomous (Simple) Command
// A basic Autonomous routine: drive forward
// for two seconds and stop.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.Timer;

// Import Subsystems
import frc.robot.subsystems.DriveSystem;

public class AutonSimple extends CommandBase
{
  // Set vars
  private final DriveSystem driveSystem;   // Reference to drive system object 
  private Timer driveTimer = new Timer();

  private double DRIVE_TIME = 0.5;    // Duration of action

  // ----------------------------------------------------------------------------
  // Constructor
  public AutonSimple(DriveSystem d)
  {
    driveSystem = d;

    addRequirements(driveSystem);
  }

  // ----------------------------------------------------------------------------
  // Initialization
  @Override
  public void initialize() 
  { 
    driveSystem.zeroEncoder();
    driveTimer.reset();
    driveTimer.start();
  }

  // ----------------------------------------------------------------------------
  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute()
  {
    if (driveTimer.get() < DRIVE_TIME)
      driveSystem.drive(0.3, 0.3);
    else
      driveSystem.drive(0.0, 0.0);
  }

  // ----------------------------------------------------------------------------
  // Return true when timer reaches designated target time.
  @Override
  public boolean isFinished() 
  {
    if (driveTimer.get() >= DRIVE_TIME)
      return true;
    else
      return false;
  }
}
