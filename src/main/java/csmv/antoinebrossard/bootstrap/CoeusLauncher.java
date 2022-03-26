package csmv.antoinebrossard.bootstrap;

import csmv.antoinebrossard.Coeus;
import csmv.antoinebrossard.inject.DefaultModule;
import edu.wpi.first.wpilibj.RobotBase;
import me.yushust.inject.Injector;

/**
 * Launcher class utilised for the bootstrap
 * of the coeus robot
 */
public final class CoeusLauncher {

  private CoeusLauncher() {
    throw new UnsupportedOperationException("Cannot instantiate this class!");
  }

  public static void main(String[] args) {
    System.out.println("INITIALIZING BOOTSTRAP");
    Coeus coeus = new Coeus();
    Injector.create(new DefaultModule()).injectMembers(coeus);
    RobotBase.startRobot(() -> coeus);
  }
}