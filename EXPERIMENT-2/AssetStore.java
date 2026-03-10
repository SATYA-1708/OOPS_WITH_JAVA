import java.util.HashMap;

public class AssetStore {

    HashMap<String, Asset> map = new HashMap<>();

    public void addAsset(Asset a) {
        map.put(a.assetId, a);
    }

    public Asset findAsset(String assetId) {
        Asset a = map.get(assetId);
        if (a == null) {
            throw new NullPointerException("Asset not found: " + assetId);
        }
        return a;
    }

    public void markBorrowed(Asset a) {
        if (!a.available) {
            throw new IllegalStateException("Asset already borrowed");
        }
        a.available = false;
    }
}