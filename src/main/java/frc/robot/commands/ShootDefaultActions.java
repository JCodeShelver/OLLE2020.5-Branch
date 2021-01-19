// FRC Team 3770 - BlitzCreek - OLLE 2020
// Shooter (Default Actions) Command
// Manages shooter mechanism when not
// explicitly used.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.VisionPID;

import frc.robot.Constants;

public class ShootDefaultActions extends CommandBase
{
    // Robot object referencess required for this action
    private final Shooter    shooter;
    private final VisionPID  visionPID;
    public double            Distance, RPM;

    // --------------------------------------------------------------------------
    // Constructor
    public ShootDefaultActions(Shooter s, VisionPID v)
    {
        // Capture references to existing robot subsystems.  Define them as requirements.
        shooter     = s;
        visionPID   = v;

        addRequirements(shooter, visionPID);
    }

    // --------------------------------------------------------------------------
    // Initialization
    public void initialize() 
    {
        
    }
    
    // --------------------------------------------------------------------------
    // 
    public void execute() 
    {
        Constants.EndgameEnabled = false;

        shooter.updateBallInShooter();
        shooter.stop();

        visionPID.LEDoff();
        // spinner.motorOff();
    }
    
    // --------------------------------------------------------------------------
    // 
    public boolean isFinished() 
    {
        return false;
    }
}