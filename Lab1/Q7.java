public class Q7 {
    public static void main(String[] args) {
        long currentTime =System.currentTimeMillis();
        long todayTime = currentTime % 86400000;
        long hours = (todayTime/3600000);
        long minutes = (todayTime/60000)%60;
        long seconds = (todayTime/1000)%60;
        System.out.println("Current time is "+hours+":"+minutes+":"+seconds);
    }
}
