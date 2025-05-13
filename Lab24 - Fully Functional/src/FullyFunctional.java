import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.*;

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
        List<String> unames = Stream
                .generate(sc::nextLine)
                .limit(3)
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        System.out.println("Uppercased Names:" + unames);


        //3
        String joined = unames.stream().collect(Collectors.joining(", "));
        System.out.println("Joined: " + joined);

        //4
        Integer[] arr = {5, 2, 9, 1};
        Arrays.sort(arr, (a, b) -> b - a);
        System.out.println("Sorted descending: " + Arrays.toString(arr));

        //5
        int n = 5;
        IntStream.range(0, n)
                .forEach(i -> System.out.println("Hello!"));

        //6
        //TODO
        Files.lines(Paths.get("/Users/aryanpillai2701/Library/On Disk/Files/CS3/Computer-Science-3-Labs/Lab24 - Fully Functional/src/README.txt")).forEach(System.out::println);


        //7
        int number = 29;
        boolean isPrime = number > 1 && IntStream.range(2, number)
                .noneMatch(i -> number % i == 0);
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
        System.out.println("Sorted for descending: " + Arrays.toString(sortedDesc));


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
        })
                .start();

        //12
        //DONE

        //13
        //DONE
    }
}
