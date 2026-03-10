public class AuditLogger {

    public static void log(String msg) {
        System.out.println("LOG: " + msg);
    }

    public static void logError(Exception e) {
        System.out.println("ERROR: " + e.getMessage());
    }
}