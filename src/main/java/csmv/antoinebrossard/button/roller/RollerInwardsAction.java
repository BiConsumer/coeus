package csmv.antoinebrossard.button.roller;

import csmv.antoinebrossard.button.ButtonAction;
import csmv.antoinebrossard.controller.RollerController;

import javax.inject.Inject;

public class RollerInwardsAction implements ButtonAction {

    private @Inject RollerController rollerController;

    @Override
    public void pressed() {
        if (!rollerController.isActivated()) {
            rollerController.rotateInwards();
        } else {
            rollerController.still();
        }
    }
}