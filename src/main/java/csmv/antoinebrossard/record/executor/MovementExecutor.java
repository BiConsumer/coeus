package csmv.antoinebrossard.record.executor;

import csmv.antoinebrossard.record.RecordExecutor;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.interfaces.Gyro;

import javax.inject.Inject;

public class MovementExecutor implements RecordExecutor {

    private @Inject MecanumDrive mecanumDrive;
    private @Inject Gyro gyroscope;

    @Override
    public void execute(String[] parameters) {
        mecanumDrive.driveCartesian(
                Double.parseDouble(parameters[0]),
                Double.parseDouble(parameters[1]),
                Double.parseDouble(parameters[2]),
                gyroscope.getAngle()
        );
    }
}