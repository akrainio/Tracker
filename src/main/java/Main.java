import java.util.Set;

public class Main {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        RequestGenerator requestGenerator = new RequestGenerator();
        Tracker<String, String> tracker = new Tracker<String, String>();
        requestGenerator.generate(tracker, 100000, 50000);
        StringGenerator stringGenerator = new StringGenerator();
        for (int i = 0; i < 500; ++i) {
            String source = stringGenerator.generate(i, "src");
            Set<String> set = tracker.requestTrack(source);
//            System.out.print(source + " |");
//            for (String key : set) {
//                System.out.print(" "+ key + " ");
//            }
//            System.out.println();
        }
        requestGenerator.printDistribution();
        System.out.println("Process took " + (System.currentTimeMillis() - startTime)/1000 + " seconds");
    }
}
