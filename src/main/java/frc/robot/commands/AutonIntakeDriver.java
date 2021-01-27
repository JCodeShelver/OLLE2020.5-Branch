// FRC Team 3770 - BlitzCreek - OLLE 2021
// Autonomous Intake Driver Command
// Manages the Intake mechanism during the
// Autonomous period.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

// Import Subsystems
import frc.robot.subsystems.FrontIntake;

// Import Constants
import frc.robot.Constants;

public class AutonIntakeDriver extends CommandBase 
{
  // Set vars
  private final FrontIntake frontIntake;   
  private double input;

  // ----------------------------------------------------------------------------
  // Constructor
  public AutonIntakeDriver(FrontIntake i, double in) 
  {
    frontIntake = i;
    input = in;

    addRequirements(frontIntake);
  }

  // ----------------------------------------------------------------------------
  // Initialization
  @Override
  public void initialize() 
  { 
    
  }

  // ----------------------------------------------------------------------------
  // If the frontIntake is deployed, then set the motors to the input, otherwise keep the motors off.
  @Override
  public void execute()
  {
    if (frontIntake.isOut())
    {
      System.out.println(input);
      frontIntake.driveIntakeMotors(input);
    }
    else
      frontIntake.driveIntakeMotors(0.0);
  }

  // ----------------------------------------------------------------------------
  // This is a default command, so always return false.
  @Override
  public boolean isFinished() 
  {
    return false;
  }
}