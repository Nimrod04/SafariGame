package model;

import java.util.List;

public class SafariPark {
    private List<Entity> entities;
    private List<Plant> plants;
    private List<WaterBody> waterSources;
    private List<Jeep> jeeps;
    private List<Tourist> tourists;

    public void addEntity(Entity entity) { entities.add(entity); }
    public void removeEntity(Entity entity) { entities.remove(entity); }
    public void addWaterSource(WaterBody water) { waterSources.add(water); }
    public void addJeep(Jeep jeep) { jeeps.add(jeep); }
    public void addTourist(Tourist tourist) { tourists.add(tourist); }
}
