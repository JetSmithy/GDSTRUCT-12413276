public class Main {
    public static void main(String[] args) {
        // Create a new LinkedList object
        LinkedList list = new LinkedList();

        // Add some numbers to the list
        list.add(5);
        list.add(15);
        list.add(25);

        // Print all elements
        list.printList(); // Output: 5 15 25
        System.out.println("Size: " + list.size()); // Shows current size

        // Remove the first element (5)
        list.removeFirst();
        list.printList(); // Output: 15 25

        System.out.println("Contains 15? " + list.contains(15)); // Check if list contains 15
        System.out.println("Index of 25: " + list.indexOf(25)); // Get the index of 25
        System.out.println("Size: " + list.size()); // Print the size again
    }
}
// LinkedList class to manage a chain of connected Nodes
class LinkedList {
    // Node class represents each element in the linked list
    private class Node {
        int data; // stores the node's value
        Node next; // reference to the next node

        Node(int d) { // constructor to set data
            data = d;
        }
    }

    private Node head;   // points to the first node in the list
    private int count = 0; // keeps track of how many nodes are in the list
    // Adds a new element to the end of the list
    public void add(int data) {
        Node newNode = new Node(data); // create a new node with the given data

        if (head == null) {
            // If the list is empty, the new node becomes the head
            head = newNode;
        } else {
            // Otherwise, find the last node and attach the new node
            Node cur = head;
            while (cur.next != null) // move until the last node
                cur = cur.next;
            cur.next = newNode;  // link the last node to the new one
        }

        count++; // increase the size counter
    }
    // Removes the first element in the list
    public void removeFirst() {
        if (head != null) { // only if list is not empty
            head = head.next; // move the head pointer to the next node
            count--; // decrease the size counter
        }
    }
    // Returns how many elements are in the list
    public int size() {
        return count; // simply return the counter
    }
    // Checks if a given value exists in the list
    public boolean contains(int val) {
        // Start from the head and look through each node
        for (Node cur = head; cur != null; cur = cur.next)
            if (cur.data == val) // if found, return true
                return true;
        return false; // if not found, return false
    }
    // Returns the index (position) of a given value, or -1 if not found
    public int indexOf(int val) {
        int i = 0; // index counter
        for (Node cur = head; cur != null; cur = cur.next, i++)
            if (cur.data == val) // if found, return its index
                return i;
        return -1; // value not found
    }
    // Prints all elements of the list on one line
    public void printList() {
        for (Node cur = head; cur != null; cur = cur.next)
            System.out.print(cur.data + " "); // print each node's value
        System.out.println(); // new line after printing all values
    }
}
