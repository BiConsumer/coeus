package csmv.antoinebrossard.controller;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.CvSink;
import edu.wpi.first.cscore.CvSource;
import edu.wpi.first.cscore.UsbCamera;
import org.opencv.core.Mat;

import javax.inject.Singleton;

@Singleton
public class CameraController {

    public void start() {
        Thread cameraRunner = new Thread(() -> {
            UsbCamera camera = CameraServer.startAutomaticCapture();
            camera.setResolution(640, 480);

            CvSink cvSink = CameraServer.getVideo();
            CvSource outputStream = CameraServer.putVideo("Inverse", 640, 480);
            Mat mat = new Mat();

            while (!Thread.interrupted()) {
                if (cvSink.grabFrame(mat) == 0) {
                    outputStream.notifyError(cvSink.getError());
                    continue;
                }

                outputStream.putFrame(mat.inv());
            }
        });

        cameraRunner.setDaemon(true);
        cameraRunner.start();
    }
}