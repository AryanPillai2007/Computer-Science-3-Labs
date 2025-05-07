import java.util.*;
import java.util.stream.*;
import javax.swing.*;
//
public class Functional {
    public static void main(String[] args) {

        //0
        List<Integer> nums = new ArrayList<>(Arrays.asList(1, 2, 3));
        List<String> words = new ArrayList<>(Arrays.asList("hello", "and", "goodbye"));

        //1
        /// Lambda version
        nums.forEach(n -> System.out.println(n));
        /// Method reference version
        nums.forEach(System.out::println);

        //2
        /// Removes even numbers
        nums.removeIf(n -> n % 2 == 0);
        System.out.println("After removing evens: " + nums);

        //3
        long oddCount = nums.stream()
                .filter(n -> n % 2 != 0)
                .count();
        System.out.println("Odd count: " + oddCount);

        //4
        List<String> aWords = words.stream()
                .filter(s -> s.startsWith("a"))
                .collect(Collectors.toList());
        aWords.forEach(System.out::println);

        //5
        List<Integer> doubled = nums.stream()
                .map(n -> n * 2)
                .collect(Collectors.toList());
        System.out.println("Doubled: " + doubled);

        //6
        List<Integer> doubledBat = nums.stream()
                .map(n -> n * 2)
                .collect(Collectors.toList());
        System.out.println("Doubled (CodingBat style): " + doubledBat);

        //7
        List<Double> prices = new ArrayList<>(Arrays.asList(10.0,20.0,30.0));
        List<Double> taxedPrices = prices.stream()
                /// 12% Tax
                .map(p -> p * 1.12)
                .collect(Collectors.toList());
        System.out.println("Prices with tax: " + taxedPrices);

        //8
        int total = Arrays.asList(1,2,3,4,5).stream()
                .reduce(0, (a, b) -> a + b);
        System.out.println("Total sum: " + total);

        //9
        double totalTaxed = prices.stream()
                /// 12% Tax
                .map(p -> p * 1.12)
                .reduce(0.0, (a, b) -> a + b);
        System.out.println("Total with tax: " + totalTaxed);

        //10
        Optional<Integer> maxVal = nums.stream()
                .max((a, b) -> Integer.compare(a, b));
        System.out.println("Max value: " + maxVal.orElse(-1));

        //11
        JButton button = new JButton("Click here!!");
        JFrame frame = new JFrame("Button test!!");

        button.addActionListener(e -> System.out.println("Button clicked!!!!"));

        frame.setSize(200, 200);
        frame.add(button);
        frame.setVisible(true);

        //12
        List<Person> users = new ArrayList<>();
        users.add(new Person("Sarah", 40));
        users.add(new Person("Peter", 50));
        users.add(new Person("Lucy", 60));
        users.add(new Person("Albert", 20));
        users.add(new Person("Charlie", 30));

        int oldest = users.stream()
                .mapToInt(Person::getAge)
                .max()
                .orElse(-1);
        System.out.println("Oldest age: " + oldest);
    }

    static class Person {
        String name;
        int age;

        Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return this.name + ", " + this.age;
        }

        int getAge() {
            return this.age;
        }
    }
}
