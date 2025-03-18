public class MyLinkedList<T> {

//	1
   
	private ListNode head;
    private ListNode tail;
    private int size;

//    2
    private class ListNode {
        T val;
        ListNode next; 

        public ListNode(T val) {
            this.val = val;
        }
        
        @Override
        public String toString() {
            return " " + this.val;
        }
    }
    
    public MyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }
    
    public MyLinkedList(T val) {
        this.head = new ListNode(val);
        this.tail = this.head;
        this.size = 1;
    }
    
    public MyLinkedList(T... vals) {
        for (T val: vals) {
            this.add(val);
        }
    }
 
//    3
    public void add(T newVal) {
        ListNode newNode = new ListNode(newVal);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }
    
//    4
    public boolean contains(T target) {
    	ListNode listfornow = head;
        while (listfornow!=null) {
            if (listfornow.val.equals(target)) {
                return true;
            }
            listfornow = listfornow.next;
        }
        return false;
    }
    
//    5
    public T get(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("CANT BE NEGATiIVE");
        }
        ListNode listfornow = head;
        int listfornowIndex = 0;
        while (listfornow != null) {
            if (listfornowIndex == index) {
                return listfornow.val;
            }
            listfornow = listfornow.next;
            listfornowIndex++;
        }
        throw new IndexOutOfBoundsException("OUT OF BOUNDS" + index);
    }
    
//    6
    public int indexOf(T target) {
        ListNode listfornow=head;
        int index = 0;
        while (listfornow !=null) {
            if (listfornow.val == target) {
                return index;
            }
            listfornow = listfornow.next;
            index++;
        }
        return -1;
    }
    
//    7. Riddle answer: A shadow. They are there before dawn, but since at noon the sun is directly above, the shadow is gone. It is in all seasons other than winter due to the bad sunlight coverage. Shadows are present in the summer, winder, and spring. 
    
//    8
    public void set(T newVal, int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("CANT BE NEGATIVE: " + index);
        }
        ListNode listfornow = head;
        int currentIndex = 0;
        while (listfornow != null) {
            if (currentIndex == index) {
                listfornow.val = newVal;
                return;
            }
            listfornow = listfornow.next;
            currentIndex++;
        }
        throw new IndexOutOfBoundsException("OUT OF BOUNDS.: " + index);
        }
    
//    9
    public int size() {
        int count = 0;
        ListNode listfornow = head;
        while (listfornow!= null) {
            count++;
            listfornow = listfornow.next;
        }
        return count;
    }
    
    private int sizeRecursive(ListNode current9a) {
        if (current9a == null) {
            return 0;
        }
        return 1 + sizeRecursive(current9a.next);
    }
    
    public int sizeRecursive() {
        return sizeRecursive(head);
    }
    
//    10
    public boolean isEmpty() {
        return head == null;
    }
   
//    11
    public T remove(int index) {
        if (index < 0 || head == null) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }

        if (index == 0) {
            T value = head.val;
            head = head.next;
            size--;
            return value;
        }

        ListNode prev = head;
        int currentIndex = 0;

        while (prev.next != null && currentIndex < index - 1) {
            prev = prev.next;
            currentIndex++;
        }

        if (prev.next == null) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }

        ListNode toRemove = prev.next;
        T value = toRemove.val;
        prev.next = toRemove.next;
        if (toRemove == tail) {
            tail = prev;
        }
        size--;
        return value;
    }
    
//    12
    public void add(T newVal, int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Index cannot be negative: " + index);
        }

        if (index == 0) {
            ListNode newNode = new ListNode(newVal);
            newNode.next = head;
            head = newNode;
            if (tail == null) {
                tail = newNode;
            }
            size++;
            return;
        }

        ListNode prev = head;
        int currentIndex = 0;
        while (prev != null && currentIndex < index - 1) {
            prev = prev.next;
            currentIndex++;
        }

        if (prev == null) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }

        ListNode newNode = new ListNode(newVal);
        newNode.next = prev.next;
        prev.next = newNode;
        if (newNode.next == null) {
            tail = newNode;
        }
        size++;
    }
    
//    13
    @Override
    public String toString() {
        if (head == null) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("[");
        ListNode current = head;
        while (current != null) {
            sb.append(current.val);
            if (current.next != null) {
                sb.append(", ");
            }
            current = current.next;
        }
        sb.append("]");
        return sb.toString();
    }
    
    // 14. All working. 
    
    
    
    
    
    
}

