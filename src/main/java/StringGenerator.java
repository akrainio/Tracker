import java.util.Random;

public class StringGenerator {
    private Random random;
    public StringGenerator() {
        random = new Random();
    }
    public String generate(int seed) {
        random.setSeed(seed);
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 100; ++i) {
            stringBuilder.append((char) (random.nextInt(25) + 65));
        }
        return stringBuilder.toString();
    }
}
