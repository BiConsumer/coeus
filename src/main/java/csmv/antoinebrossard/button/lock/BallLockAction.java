package csmv.antoinebrossard.button.lock;

import csmv.antoinebrossard.button.ButtonAction;
import csmv.antoinebrossard.controller.BallLockController;

import javax.inject.Inject;

public class BallLockAction implements ButtonAction {

    private @Inject BallLockController ballLockController;

    @Override
    public void pressed() {
        ballLockController.toggle();
    }
}