// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package csmv.antoinebrossard;

import csmv.antoinebrossard.annotation.Port;
import csmv.antoinebrossard.button.ButtonAction;
import csmv.antoinebrossard.controller.CameraController;
import csmv.antoinebrossard.record.*;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.interfaces.Gyro;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

public class Coeus extends TimedRobot {

  private @Inject @Port(Port.Value.JOYSTICK) Joystick joystick;
  private @Inject @Port(Port.Value.TEAM_SWITCH) DigitalInput teamSwitch;
  private @Inject @Named("grip") DoubleSolenoid gripSolenoid;
  private @Inject @Named("slide") DoubleSolenoid slideSolenoid;
  private @Inject @Named("saveDirectory") File saveDirectory;

  private @Inject MecanumDrive mecanumDrive;
  private @Inject Gyro gyroscope;

  private @Inject Recorder recorder;
  private @Inject RecordRunner recordRunner;
  private @Inject RecordDeserializer recordDeserializer;

  private @Inject CameraController cameraController;
  private @Inject Map<Integer, ButtonAction> buttonActions;

  private long startMillis;
  private List<Record> recordList;

  @Override
  public void robotInit() {
    System.out.println("INITIALIZING");

    cameraController.start();
    gripSolenoid.set(DoubleSolenoid.Value.kForward);
    slideSolenoid.set(DoubleSolenoid.Value.kReverse);
  }

  @Override
  public void autonomousInit() {
    File autonomousFile = new File(
            saveDirectory.getPath()
                    + "/"
                    + (teamSwitch.get() ? "right.flux" : "left.flux")
    );

    if (!autonomousFile.exists()) {
      return;
    }

    try {
      recordRunner.play(recordDeserializer.deserialize(autonomousFile));
    } catch (FileNotFoundException exception) {
      exception.printStackTrace();
    }
  }

  @Override
  public void autonomousPeriodic() {
    recordRunner.next();
  }

  @Override
  public void teleopPeriodic() {
    double sensitivity = Math.max(0.01, Math.min(1, (-joystick.getThrottle()+1)/2));

    double ySpeed = -joystick.getY() * sensitivity;
    double xSpeed = joystick.getX() * sensitivity;
    double zRotation = joystick.getZ() * sensitivity;

    mecanumDrive.driveCartesian(
            ySpeed,
            xSpeed,
            zRotation,
            gyroscope.getAngle()
    );

    recorder.push(
            "movement",
            Double.toString(ySpeed),
            Double.toString(xSpeed),
            Double.toString(zRotation)
    );

    for (Map.Entry<Integer, ButtonAction> buttonEntry : buttonActions.entrySet()) {
      if (joystick.getRawButtonPressed(buttonEntry.getKey())) {
        buttonEntry.getValue().pressed();
      }
    }

    recorder.tick();
  }
}