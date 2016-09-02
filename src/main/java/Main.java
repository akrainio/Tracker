/**
 * Created by Andrei on 9/1/2016.
 */
public class Main {
    public static void main(String[] args) {
        StringGenerator generator = new StringGenerator();
        String a = generator.generate(10);
        String b = generator.generate(10);
        System.out.println("Generated the following pair: \n[" + a + "]\n[" + b + "]");
        a = generator.generate(999);
        b = generator.generate(999);
        System.out.println("Generated the following pair: \n[" + a + "]\n[" + b + "]");
    }
}
