package model;

import java.util.List;

public class SurveillanceSystem {
    private List<Camera> cameras;

    public void deployCamera(Camera camera) {
        cameras.add(camera);
    }
}
