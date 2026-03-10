public class ValidationUtil {

    public static void validateUid(String uid) {
        if (uid == null || uid.contains(" ") || uid.length() < 8 || uid.length() > 12) {
            throw new IllegalArgumentException("Invalid UID");
        }
    }

    public static void validateAssetId(String assetId) {
        if (assetId == null || !assetId.startsWith("LAB-")) {
            throw new IllegalArgumentException("Invalid Asset ID");
        }
        String num = assetId.substring(4);
        if (!num.matches("\\d+")) {
            throw new IllegalArgumentException("Invalid Asset ID");
        }
    }

    public static void validateHours(int hrs) {
        if (hrs < 1 || hrs > 6) {
            throw new IllegalArgumentException("Invalid Hours");
        }
    }
}