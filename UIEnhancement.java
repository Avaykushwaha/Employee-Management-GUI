public class UIEnhancement {
    public static void progressBar(String message, int speed) {
        System.out.print(message + " [");
        for (int i = 0; i <= 20; i++) {
            try {
                Thread.sleep(speed); // speed of progress
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.print(GREEN + "█" + RESET);

        }
        System.out.println("] Done!");
    }


    // ANSI Colors
    public static final String RESET = "\u001B[0m";
    public static final String GREEN = "\u001B[32m";
    public static final String RED = "\u001B[31m";
    public static final String CYAN = "\u001B[36m";
    public static final String YELLOW = "\u001B[33m";

}
