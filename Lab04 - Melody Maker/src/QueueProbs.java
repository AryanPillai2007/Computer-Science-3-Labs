import java.util.*;
import java.util.LinkedList;
import java.util.Queue;


public class QueueProbs {
    
    // 3. Move even numbers to the front of the queue
    public Queue<Integer> evenFirst(Queue<Integer> nums) {
        Queue<Integer> evens = new LinkedList<>(); 
        Queue<Integer> odds = new LinkedList<>();  
        
        while (!nums.isEmpty()) {                  
            int num = nums.poll();                 
            if (num % 2 == 0) {                    
                evens.add(num);                    
            } else {                               
                odds.add(num);                     
            }                                      
        }                                          
        evens.addAll(odds);                        
        return evens;                              
    }
    
    // 4. Check if the numbers in the queue form a palidrome
    public boolean numPalindrome(Queue<Integer> nums) {
        Stack<Integer> stack = new Stack<>();      
        Queue<Integer> temp = new LinkedList<>(nums); 
        
        while (!temp.isEmpty()) {                  
            stack.push(temp.poll());               
        }                                               
        for (int num: nums) {                     
            if (stack.pop() != num) {              
                return false;                      
            }                                     
        }                                          
        return true;                               
    }
  
    // 6. Sieve: Find all prime numbers up to n
    public Stack<Integer> sieve(int n) {
        Queue<Integer> queue = new LinkedList<>(); 
        Stack<Integer> primes = new Stack<>();     
        
        for (int i = 2; i <= n; i++) {             
            queue.add(i);                          
        }                                          
        while (!queue.isEmpty()) {                 
            int prime = queue.poll();                  
            primes.push(prime);                        
          
            Queue<Integer> tempQueue = new LinkedList<>(); 
            
            while (!queue.isEmpty()) {
                int finished = queue.poll();
                if (finished % prime != 0) {
                    tempQueue.add(finished); 
                }
            }
            queue = tempQueue;                   
        }                                          
        
        return primes;                             
    }

    public static void main(String[] args) {
        Queue<Integer> nums = new LinkedList<>();
        nums.add(3);
        nums.add(5);
        nums.add(4);
        nums.add(17);
        nums.add(6);
        nums.add(83);
        nums.add(1);
        nums.add(84);
        nums.add(16);
        nums.add(37);

        QueueProbs TESTER = new QueueProbs();
        // Test 3
        Queue<Integer> evenFirstQueue = TESTER.evenFirst(nums);
        System.out.println("Even First: " + evenFirstQueue);
        
        // Test 4
        Queue<Integer> palindromeQueue = new LinkedList<>(Arrays.asList(13, 8, 17, 9, 17, 8, 13));
        System.out.println("Is Palindrome: " + TESTER.numPalindrome(palindromeQueue));
        
        // Test 5
        Stack<Integer> primes = TESTER.sieve(30);
        System.out.println("Primes up to 30:" + primes);
    }
}
