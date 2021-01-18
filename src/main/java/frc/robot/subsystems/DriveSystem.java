// FRC Team 3770 - BlitzCreek - OLLE 2020
// Drive Subsystem
// Manage motors for human driving and auton
// motion.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.can.*;  
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import frc.robot.Constants;

public class DriveSystem extends SubsystemBase 
{
    private final CANSparkMax leftMotor1, leftMotor2, rightMotor1, rightMotor2;
    private final TalonSRX encoderReading;
    private double adjustedL, adjustedR, adjustedX, adjustedY;

    // --------------------------------------------------------------------------
    // Constructor
    public DriveSystem() 
    {
        // Declare/instantiate robot objects by calling constructors
        leftMotor1  = new CANSparkMax(Constants.LEFT_MOTOR1_CAN_ID, MotorType.kBrushless);   
        leftMotor2  = new CANSparkMax(Constants.LEFT_MOTOR2_CAN_ID, MotorType.kBrushless);    
        rightMotor1 = new CANSparkMax(Constants.RIGHT_MOTOR1_CAN_ID, MotorType.kBrushless);
        rightMotor2 = new CANSparkMax(Constants.RIGHT_MOTOR2_CAN_ID, MotorType.kBrushless);

        // Encoder Reading motor
        encoderReading = new TalonSRX(Constants.MOVING_MOTOR_CAN_ID);

        // Reverse rotation (polarity) of left motor set
        leftMotor1.setInverted(true);
        leftMotor2.setInverted(true);
        rightMotor1.setInverted(false);
        rightMotor2.setInverted(false);
    }
    
    // --------------------------------------------------------------------------
    // Drives the Robot with one joystick.
    public void ArcadeDrive(double rightY, double rightX)
    {
        if (rightX < 0)
            adjustedX = -Math.pow(rightX, 2);
        else
            adjustedX = Math.pow(rightX, 2);

        if (rightY < 0)
            adjustedY = -Math.pow(rightY, 2);
        else
            adjustedY = Math.pow(rightY, 2);        

        leftMotor1.set(adjustedY + adjustedX);
        leftMotor2.set(adjustedY + adjustedX);
        rightMotor1.set(adjustedY - adjustedX);
        rightMotor2.set(adjustedY - adjustedX);
    }

    // --------------------------------------------------------------------------
    // Drives the Robot using two joysticks using raw values.
    public void drive(double inL, double inR)
    {
        leftMotor1.set(inL);
        leftMotor2.set(inL);
        rightMotor1.set(inR);
        rightMotor2.set(inR); 
    }

    // ----------------------------------------------------------------------------
    // Return distance traveled in inches.
    public double getDistanceInches()
    {
        return encoderReading.getSelectedSensorPosition() / Constants.INCHES_PER_TICK;
    }
    
    // --------------------------------------------------------------------------
    // Drives the Robot with two joysticks (tank drive configuration).
    public void Quadraticdrive(double inputL, double inputR)
    {
        if (inputL < 0)
            adjustedL = -Math.pow(inputL, 2);
        else
            adjustedL = Math.pow(inputL, 2);

        if (inputR < 0)    
            adjustedR = -Math.pow(inputR, 2);
        else    
            adjustedR = Math.pow(inputR, 2);
            adjustedR /= 4;

        leftMotor1.set(adjustedL);    
        leftMotor2.set(adjustedL);
        rightMotor1.set(adjustedR);
        rightMotor2.set(adjustedR); 
    }     

    // ----------------------------------------------------------------------------
    // Reset encoders to zero.
    public void zeroEncoder()
    {
        encoderReading.setSelectedSensorPosition(0);   
    }
}
