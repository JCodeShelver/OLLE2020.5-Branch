// FRC Team 3770 - BlitzCreek - OLLE 2020.5
// Autonomous Pick-up Balls Command
// Autonomous routine requiring multiple
// segments & turns in sequence.

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

public class AutoPickUpBalls extends ParallelDeadlineGroup
{
    // Constructor
    public AutoPickUpBalls(DriveSystem d, GyroPID g, FrontIntake i, double power, double distance, double angle)
    {
        super(new DriveSegment(d, g, power, distance, angle), new AutonIntakeDriver(i, 0.75));           
    }
}
