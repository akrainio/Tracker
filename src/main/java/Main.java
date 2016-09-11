import java.util.Set;

public class Main {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        RequestGenerator requestGenerator = new RequestGenerator();
        DependencyTracker<String, String> tracker = new DBTracker();
        requestGenerator.generate(tracker, 2000, 1000);
        StringGenerator stringGenerator = new StringGenerator();
        for (int i = 0; i < 500; ++i) {
            String source = stringGenerator.generate(i, "src");
            Set<String> set = tracker.getRequests(source);
        }
        requestGenerator.printDistribution();
        System.out.println("Process took " + (System.currentTimeMillis() - startTime)/1000 + " seconds");
        tracker.clear();
    }
}
