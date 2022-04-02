package csmv.antoinebrossard.button.record;

import csmv.antoinebrossard.button.ButtonAction;
import csmv.antoinebrossard.record.Recorder;

import javax.inject.Inject;

public class RecordSaveAction implements ButtonAction {

    private @Inject Recorder recorder;

    @Override
    public void pressed() {
        System.out.println("SAVING RECORDING");
        recorder.save();
    }
}