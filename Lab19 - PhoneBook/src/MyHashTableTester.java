import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class) // run methods in numerical order
public class MyHashTableTester {

    static final String PATH = ""; // change if your data files are stored in another directory

    static MyHashTable<Person, Integer> table;

    @BeforeAll
    public static void setUp() {
        table = new MyHashTable<>();
    }

    @Test
    @Order(0)
    public void test00_putNoPreviousValue() {
        assertTrue(table.put(new Person("Mary"), 80) == null, "Adding Mary should return null");
    }

    @Test
    @Order(1)
    public void test01_putWithPreviousValue() {
        assertTrue(table.put(new Person("Mary"), 50) == 80, "Replacing Mary should return previously mapped value (80)");
        assertTrue(table.get(new Person("Mary")) == 50, "Should return Mary's new age (50)");
    }

    @Test
    @Order(2)
    public void test02_getNoCollision() {
        Person jim = new Person("Jim");
        table.put(jim, 20);
        assertTrue(20 == table.get(jim), "Jim should be mapped to 20");
    }

    @Test
    @Order(3)
    public void test03_getDoesntExist() {
        assertEquals(null, table.get(new Person("Bob")), "Bob doesn't exist in map (return null)");
    }

    @Test
    @Order(4)
    public void test04_getWithCollision() {
        assertTrue("Siblings".hashCode() == new Person("Siblings").hashCode(), "Your Person.java needs to use the String class' hashCode method");
        table.put(new Person("Siblings"), 60);
        table.put(new Person("Teheran"), 70);
        assertTrue(70 == table.get(new Person("Teheran")), "Should return value of second item in linked list (70)");
    }

    @Test
    @Order(5)
    public void test05_removeExistsNoCollision() {
        Person charlie = new Person("Charlie");
        table.put(charlie, 30);
        assertTrue(table.remove(charlie) == 30, "Removing Charlie returns mapped value (30)");
        assertTrue(table.get(charlie) == null, "Getting Charlie should now return null");
    }

    @Test
    @Order(6)
    public void test06_removeDoesntExistNoCollision() {
        assertTrue(table.remove(new Person("Bob")) == null, "Attempting to remove Bob should return null");
    }

    @Test
    @Order(7)
    public void test07_removeExistsWithCollisionFirstElementFirst() {
        assertTrue(table.remove(new Person("Siblings")) == 60, "Removing first item in collision list should return 60");
        assertTrue(table.remove(new Person("Siblings")) == null, "Now Siblings should not exist in table");
        assertTrue(table.get(new Person("Teheran")) != null, "Teheran should still be in table");
    }

    @Test
    @Order(8)
    public void test08_removeExistsWithCollisionSecondElementFirst() {
        table = new MyHashTable<>();
        table.put(new Person("Siblings"), 60);
        table.put(new Person("Teheran"), 70);
        assertTrue(table.remove(new Person("Teheran")) == 70, "Removing second item in collision list should return 70");
        assertTrue(table.remove(new Person("Teheran")) == null, "Now Teheran should not exist in table");
        assertTrue(table.get(new Person("Siblings")) != null, "Siblings should still be in table");
    }

    @Test
    @Order(9)
    public void test09_removeBothCollidingElements() {
        table.put(new Person("Siblings"), 60);
        table.put(new Person("Teheran"), 70);
        assertTrue(table.remove(new Person("Teheran")) == 70, "Removing second item in collision list should return 70");
        assertTrue(table.remove(new Person("Siblings")) == 60, "Removing first item in collision list should return 60");
    }

    @Test
    @Order(10)
    public void test10_removeDoesntExistWithCollision() {
        table = new MyHashTable<>();
        table.put(new Person("Siblings"), 60);
        assertTrue(table.remove(new Person("Teheran")) == null, "Should not find the Teheran person");
    }

    @Test
    @Order(11)
    public void test11_testSizeEmpty() {
        table = new MyHashTable<>();
        assertTrue(table.size() == 0, "Size should be 0");
    }

    @Test
    @Order(12)
    public void test12_testSizeOnePut() {
        table.put(new Person("Jill"), 90);
        assertTrue(table.size() == 1, "Size should be 1");
        table.put(new Person("Jill"), 100);
        assertTrue(table.size() == 1, "Size should STILL be 1");
    }

    @Test
    @Order(13)
    public void test13_testSizeOnePutOneRemove() {
        table.put(new Person("Jill"), 90);
        table.remove(new Person("Jill"));
        assertTrue(table.size() == 0, "Size should be 0");
    }

    @Test
    @Order(14)
    public void test14_testSizeMultiplePuts() {
        table.put(new Person("Jill"), 90);
        table.put(new Person("Ronnie"), 100);
        table.put(new Person("Westyn"), 110);
        assertTrue(table.size() == 3, "Size should be 3");
    }
}