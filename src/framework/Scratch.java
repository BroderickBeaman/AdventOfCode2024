package framework;

public class Scratch {
    public static void main(String[] args) {
        String test = "123456";
        String left = test.substring(0, test.length() / 2);
        String right = test.substring(test.length() / 2, test.length());
        System.out.println(test);
    }
}
