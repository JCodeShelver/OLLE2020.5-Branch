// FRC Team 3770 - BlitzCreek - OLLE 2021
// Autonomous Targeting and Shooting Command
// Autonomous routine requiring multiple 
// segments & turns in sequence. Drive 
// forward two seconds and stop.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

// Import Commands
import frc.robot.commands.DriveSegment;
import frc.robot.commands.DriveTurn;

// Import Subsystems
import frc.robot.subsystems.DriveSystem;
import frc.robot.subsystems.FrontIntake;
import frc.robot.subsystems.GyroPID;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.VisionPID;

// Import Constants
import frc.robot.Constants;

public class AutonTargetAndShootSystem extends ParallelDeadlineGroup
{
    // --------------------------------------------------------------------------
    // Constructor
    public AutonTargetAndShootSystem(DriveSystem d, Shooter s, VisionPID v, int BallCount)
    {
        super(new AutonShooting(s, v, BallCount), new DriveAlignToTarget(d, v));           
    }
}
