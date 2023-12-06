// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;

public class DriveSubsystem extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */
  public DriveSubsystem() {}
  

  public TalonFX leftFrontMotor = new TalonFX(DriveConstants.leftFront);
  public TalonFX leftBackMotor = new TalonFX(DriveConstants.leftBack);
  public TalonFX rightFrontMotor = new TalonFX(DriveConstants.rightFront);
  public TalonFX rightBackMotor = new TalonFX(DriveConstants.rightBack);
  
  /**
   * Example command factory method.
   *
   * @return a command
   */
  public Command exampleMethodCommand() {
    // Inline construction of command goes here.
    // Subsystem::RunOnce implicitly requires `this` subsystem.
    return runOnce(
        () -> {
          /* one-time action goes here */
        });
  }

  public double getEncoderMeters() {
    return (leftFrontMotor.getRotorPosition().getValue() - rightFrontMotor.getRotorPosition().getValue()) / 2 * DriveConstants.kEncoderTick2Meter;
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Drive Encoder Value", getEncoderMeters());
  }

  @Override
  public void simulationPeriodic() {
  }

  public void setMotors(double leftSpeed, double rightSpeed) {
    leftFrontMotor.set(leftSpeed);
    leftBackMotor.set(leftSpeed);
    rightFrontMotor.set(-rightSpeed);
    rightBackMotor.set(-rightSpeed);
  }
}
