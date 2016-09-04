import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RequestGenerator {
    private int stringCnt;
    private int distributionMonitor[] = new int[10];
    public void generate(Tracker<String, String> tracker, int calcCount, int stringCnt) {
        this.stringCnt = stringCnt;
        StringGenerator generator = new StringGenerator();
        for (int i = 0; i < calcCount; ++i) {
            Random random = new Random();
            int n = random.nextInt(10) + 1;
            int requestCount = 11 - n;
            for (int j = 0; j < requestCount; ++j) {
                tracker.addTrack(generator.generate(random.nextInt(stringCnt), "req"), listGenerator(n*n));
            }
            distributionMonitor[n - 1] += requestCount;
        }
    }
    private List<String> listGenerator(int size) {
        Random random = new Random();
        StringGenerator stringGenerator = new StringGenerator();
        List<String> list = new ArrayList<String>(size);
        for (int i = 0; i < size; ++i) {
            list.add(stringGenerator.generate(random.nextInt(stringCnt), "src"));
        }
        return list;
    }
    public void printDistribution() {
        System.out.println(distributionMonitor[0] + " tracks with 1 value");
        for (int i = 1; i < distributionMonitor.length; ++i) {
            System.out.println(distributionMonitor[i] + " tracks with " + (i + 1)*(i + 1) + " values");
        }
    }
}
