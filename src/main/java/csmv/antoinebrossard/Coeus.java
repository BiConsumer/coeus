// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package csmv.antoinebrossard;

import com.kauailabs.navx.frc.AHRS;
import csmv.antoinebrossard.annotation.Channel;
import csmv.antoinebrossard.controller.RollerController;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.motorcontrol.PWMVictorSPX;

import javax.inject.Inject;

public class Coeus extends TimedRobot {

  private @Inject @Channel(Channel.Value.WHEEL_FRONT_RIGHT) PWMVictorSPX wheelFrontRightMotor;
  private @Inject @Channel(Channel.Value.WHEEL_BACK_RIGHT) PWMVictorSPX wheelBackRightMotor;
  private @Inject @Channel(Channel.Value.WHEEL_FRONT_LEFT) PWMVictorSPX wheelFrontLeftMotor;
  private @Inject @Channel(Channel.Value.WHEEL_BACK_LEFT) PWMVictorSPX wheelBackLeftMotor;
  private @Inject @Channel(Channel.Value.JOYSTICK) Joystick joystick;
  private @Inject RollerController rollerController;
  private @Inject AHRS gyroscope;

  private final DoubleSolenoid gripSolenoid = new DoubleSolenoid(
          0,
          PneumaticsModuleType.CTREPCM,
          0,
          4
  );

  private final DoubleSolenoid slideSolenoid = new DoubleSolenoid(
          0,
          PneumaticsModuleType.CTREPCM,
          1,
          5
  );

  private MecanumDrive mecanumDrive;

  @Override
  public void robotInit() {
    System.out.println("INITIALIZING");

    wheelFrontRightMotor.setInverted(true);
    wheelBackRightMotor.setInverted(true);

    gripSolenoid.set(DoubleSolenoid.Value.kReverse);
    slideSolenoid.set(DoubleSolenoid.Value.kReverse);

    mecanumDrive = new MecanumDrive(
            wheelFrontLeftMotor,
            wheelBackLeftMotor,
            wheelFrontRightMotor,
            wheelBackRightMotor
    );
  }

  @Override
  public void teleopPeriodic() {
    double slider = Math.max(0.01, Math.min(1, (-joystick.getThrottle()+1)/2));
    System.out.println(slider);

    mecanumDrive.driveCartesian(
            -joystick.getY() * slider,
            joystick.getX() * slider,
            joystick.getZ() * slider,
            gyroscope.getAngle()
    );

    if (joystick.getRawButton(1)) {
      rollerController.rotateInwards();
    } else if (joystick.getRawButton(2)) {
      rollerController.rotateOutwards();
    } else {
      rollerController.still();
    }

    if (joystick.getRawButtonPressed(7)) {
      System.out.println("GRIP SOLENOID");
      gripSolenoid.toggle();
    }

    if (joystick.getRawButtonPressed(8)) {
      System.out.println("SLIDE SOLENOID");
      slideSolenoid.toggle();
    }

    if (joystick.getRawButtonPressed(9)) {
      System.out.println("TURNING OFF SOLENOIDS");
      gripSolenoid.set(DoubleSolenoid.Value.kOff);
      slideSolenoid.set(DoubleSolenoid.Value.kOff);
    }
  }
}