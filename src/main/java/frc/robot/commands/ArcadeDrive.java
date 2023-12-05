// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.DriveSubsystem;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class ArcadeDrive extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final DriveSubsystem driveSubsystem;
  private final Supplier<Double> speedFunction;
  private final Supplier<Double> turnFunction;

  /**
   * Creates a new ArcadeDRIVE.
   *
   * @param subsystem The subsystem used by this command.
   */
  public ArcadeDrive(DriveSubsystem subsystem,Supplier<Double> speedFunction,Supplier<Double> turnFunction )  {
    driveSubsystem = subsystem;
    this.speedFunction = speedFunction;
    this.turnFunction = turnFunction;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }
  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    System.out.println("ArcadeDrive start");
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double realTimeSpeed = speedFunction.get();
    double realTimeTurn = turnFunction.get();

    double left = realTimeSpeed + realTimeTurn;
    double right = realTimeSpeed - realTimeTurn;
    driveSubsystem.setMotors(left, right);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    System.out.println("ArcadeDrive End");
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
