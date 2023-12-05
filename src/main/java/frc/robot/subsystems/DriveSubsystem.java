// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;

public class DriveSubsystem extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */
  public DriveSubsystem() {}
  
  public WPI_TalonFX leftFrontMotor = new WPI_TalonFX(DriveConstants.leftFront);
  public WPI_TalonFX leftBackMotor = new WPI_TalonFX(DriveConstants.leftBack);
  public WPI_TalonFX rightFrontMotor = new WPI_TalonFX(DriveConstants.rightFront);
  public WPI_TalonFX rightBackMotor = new WPI_TalonFX(DriveConstants.rightBack);
  
  /**
   * Example command factory method.
   *
   * @return a command
   */
  public CommandBase exampleMethodCommand() {
    // Inline construction of command goes here.
    // Subsystem::RunOnce implicitly requires `this` subsystem.
    return runOnce(
        () -> {
          /* one-time action goes here */
        });
  }

  public double getEncoderMeters() {
    return (leftFrontMotor.getSelectedSensorPosition() - rightFrontMotor.getSelectedSensorPosition()) / 2 * DriveConstants.kEncoderTick2Meter;
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
