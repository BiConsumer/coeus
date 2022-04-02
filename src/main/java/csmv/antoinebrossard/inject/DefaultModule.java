package csmv.antoinebrossard.inject;

import com.kauailabs.navx.frc.AHRS;
import csmv.antoinebrossard.annotation.Port;
import csmv.antoinebrossard.button.ButtonModule;
import csmv.antoinebrossard.record.RecordModule;
import csmv.antoinebrossard.util.PortBinder;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.motorcontrol.PWMVictorSPX;
import me.yushust.inject.AbstractModule;
import me.yushust.inject.Provides;

import javax.inject.Singleton;
import java.io.File;

public class DefaultModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(DoubleSolenoid.class).named("grip").toInstance(new DoubleSolenoid(
                0,
                PneumaticsModuleType.CTREPCM,
                0,
                4
        ));

        bind(DoubleSolenoid.class).named("slide").toInstance( new DoubleSolenoid(
                0,
                PneumaticsModuleType.CTREPCM,
                1,
                5
        ));

        bind(File.class).named("saveDirectory").toInstance(new File("/home/lvuser/fluxfiles"));
        bind(Gyro.class).to(AHRS.class).singleton();

        try {
            PortBinder.bind(binder());
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        install(new ButtonModule());
        install(new RecordModule());
    }

    @Provides @Singleton
    public MecanumDrive provideMecanumDrive(
            @Port(Port.Value.WHEEL_FRONT_RIGHT) PWMVictorSPX wheelFrontRightMotor,
            @Port(Port.Value.WHEEL_BACK_RIGHT) PWMVictorSPX wheelBackRightMotor,
            @Port(Port.Value.WHEEL_FRONT_LEFT) PWMVictorSPX wheelFrontLeftMotor,
            @Port(Port.Value.WHEEL_BACK_LEFT) PWMVictorSPX wheelBackLeftMotor
    ) {
        wheelFrontRightMotor.setInverted(true);
        wheelBackRightMotor.setInverted(true);

        return new MecanumDrive(
                wheelFrontLeftMotor,
                wheelBackLeftMotor,
                wheelFrontRightMotor,
                wheelBackRightMotor
        );
    }
}