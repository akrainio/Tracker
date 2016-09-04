import java.util.Random;

public class StringGenerator {
    private Random random;
    public StringGenerator() {
        random = new Random();
    }
    public String generate(int seed, String prefix) {
        random.setSeed(seed);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(prefix + "-");
        for (int i = 0; i < 10; ++i) {
            stringBuilder.append((char) (random.nextInt(25) + 65));
        }
        return stringBuilder.toString();
    }
}
