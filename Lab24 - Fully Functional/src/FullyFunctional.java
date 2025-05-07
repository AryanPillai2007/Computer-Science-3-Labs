import java.io.IOException;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.*;
import javax.swing.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

public class FullyFunctional {
    public static void main(String[] args) throws IOException {

        //0 & 1
        List<Integer> nums = new ArrayList<>(Arrays.asList(1, 2, 3));
        List<String> list = new ArrayList<>(Arrays.asList("hello", "and", "goodbye"));

        //2
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your three names: ");
        List<String> names = Stream
                .generate(sc::nextLine)
                .limit(3)
                .map(String::toUpperCase)
                .collect(Collectors.toList());

                System.out.println("Uppercased Names:" + names);







        //3
        String joined = names.stream().collect(Collectors.joining(", "));
        System.out.println("Joined: " + joined);

        //4
        Integer[] arr = {5, 2, 9, 1};
        Arrays.sort(arr, (a, b) -> b - a);
        System.out.println("Sorted descending: " + Arrays.toString(arr));

        //5
        int n = 5;
        IntStream.range(0, n).forEach(i -> System.out.println("Hello!"));

        //6
        //TODO

        //7
        int number = 29;
        boolean isPrime = number > 1 && IntStream.range(2, number).noneMatch(i -> number % i == 0);
        System.out.println(number + " is prime? " + isPrime);

        //8
        List<Integer> randList = new Random().ints(1, 101)
                .limit(10)
                .boxed()
                .collect(Collectors.toList());
        System.out.println("Random list: " + randList);

        //9
        List<Integer> uniqueRand = new Random().ints(1, 101)
                .distinct()
                .limit(10)
                .boxed()
                .collect(Collectors.toList());
        System.out.println("Unique randoms: " + uniqueRand);


        //10
        int[] arr2 = {3, 6, 1, 9};
        int[] sortedDesc = Arrays.stream(arr2)
                .boxed()
                .sorted(Comparator.reverseOrder())
                .mapToInt(Integer::intValue)
                .toArray();
        System.out.println("Sorted int[] descending: " + Arrays.toString(sortedDesc));


        //11
        new Thread(() -> {
            while (true) {
                System.out.println("Thread running");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    break;
                }
            }
        }).start();

        //12
        //DONE

        //13
        List<String> mapped = IntStream.range(1, 6)
                .mapToObj(i -> "Num: " + i)
                .collect(Collectors.toList());
        System.out.println("Mapped to String: " + mapped);

        //15
        String input = "a1b2c3";
        int sum = Arrays.stream(input.split("\\D+"))
                .filter(s -> !s.isEmpty())
                .mapToInt(Integer::parseInt)
                .sum();
        System.out.println("Sum of numbers in string: " + sum);

        //16
        Map<String, Integer> prices = new HashMap<>();
        prices.put("Computer", 2000);
        prices.put("Sandwich", 5);
        prices.put("Coffee", 2);
        prices.put("Car", 30000);
        prices.put("Pencil", 1);

        Map<String, Integer> sortedPrices = prices.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
        System.out.println("Sorted map by value: " + sortedPrices);

        //17
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        int evenSum = sumIf(numbers, n1 -> n1 % 2 == 0);
        int gtThreeSum = sumIf(numbers, n1 -> n1 > 3);
        System.out.println("Sum of evens: " + evenSum);
        System.out.println("Sum > 3: " + gtThreeSum);
    }

    public static int sumIf(List<Integer> numbers, Predicate<Integer> condition) {
        return numbers.stream()
                .filter(condition)
                .mapToInt(Integer::intValue)
                .sum();
    }
}