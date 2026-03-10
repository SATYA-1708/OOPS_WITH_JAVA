import java.util.HashMap;

public class CheckoutService {

    HashMap<String, Student> studentMap;
    AssetStore store;

    public CheckoutService(HashMap<String, Student> studentMap, AssetStore store) {
        this.studentMap = studentMap;
        this.store = store;
    }

    public String checkout(CheckoutRequest req)
            throws IllegalArgumentException, IllegalStateException, SecurityException, NullPointerException {

        ValidationUtil.validateUid(req.uid);
        ValidationUtil.validateAssetId(req.assetId);
        ValidationUtil.validateHours(req.hoursRequested);

        Student s = studentMap.get(req.uid);
        if (s == null) {
            throw new NullPointerException("Student not found");
        }

        Asset a = store.findAsset(req.assetId);

        if (s.fineAmount > 0) {
            throw new IllegalStateException("Pending fine");
        }

        if (s.currentBorrowCount >= 2) {
            throw new IllegalStateException("Borrow limit reached");
        }

        if (!a.available) {
            throw new IllegalStateException("Asset not available");
        }

        if (a.securityLevel == 3 && !s.uid.startsWith("KRG")) {
            throw new SecurityException("Restricted asset");
        }

        int hrs = req.hoursRequested;

        if (hrs == 6) {
            System.out.println("Note: Max duration selected. Return strictly on time.");
        }

        if (a.assetName.contains("Cable") && hrs > 3) {
            hrs = 3;
            System.out.println("Policy applied: Cables can be issued max 3 hours. Updated to 3.");
        }

        store.markBorrowed(a);
        s.currentBorrowCount++;

        String receipt = "TXN-20260221-" + a.assetId + "-" + s.uid;
        return receipt;
    }
}