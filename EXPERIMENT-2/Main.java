import java.util.HashMap;

public class Main {

    public static void main(String[] args) {

        HashMap<String, Student> students = new HashMap<>();
        students.put("KRG20281", new Student("KRG20281", "Rahul", 0, 0));
        students.put("ABC12345", new Student("ABC12345", "Aman", 100, 0));
        students.put("KRG55555", new Student("KRG55555", "Riya", 0, 2));

        AssetStore store = new AssetStore();
        store.addAsset(new Asset("LAB-101", "HDMI Cable", true, 1));
        store.addAsset(new Asset("LAB-202", "VR Headset", true, 3));
        store.addAsset(new Asset("LAB-303", "Ethernet Cable", false, 1));
        store.addAsset(new Asset("LAB-404", "Projector", true, 2));

        CheckoutService service = new CheckoutService(students, store);

        CheckoutRequest r1 = new CheckoutRequest("KRG20281", "LAB-101", 5);
        CheckoutRequest r2 = new CheckoutRequest("KRG20281", "LAB-XYZ", 2);
        CheckoutRequest r3 = new CheckoutRequest("ABC12345", "LAB-202", 2);

        CheckoutRequest[] list = {r1, r2, r3};

        for (CheckoutRequest r : list) {
            try {
                String result = service.checkout(r);
                System.out.println("Success: " + result);
            }
            catch (IllegalArgumentException e) {
                System.out.println("Invalid Input: " + e.getMessage());
                AuditLogger.logError(e);
            }
            catch (NullPointerException e) {
                System.out.println("Not Found: " + e.getMessage());
                AuditLogger.logError(e);
            }
            catch (SecurityException e) {
                System.out.println("Security Error: " + e.getMessage());
                AuditLogger.logError(e);
            }
            catch (IllegalStateException e) {
                System.out.println("Policy Error: " + e.getMessage());
                AuditLogger.logError(e);
            }
            finally {
                AuditLogger.log("Audit: attempt finished for UID=" + r.uid + ", asset=" + r.assetId);
            }
        }
    }
}