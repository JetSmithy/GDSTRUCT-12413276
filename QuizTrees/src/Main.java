class Node {

    private int data;// stores the value inside this node
    private Node leftChild; // reference to the left child
    private Node rightChild;  // reference to the right child

    // Constructor: creates a new node with a given data value
    public Node(int data) {
        this.data = data;//set the value for this node
        this.leftChild = null; // left child starts as null
        this.rightChild = null; //right child starts as null
    }

    // Returns the value stored in this node
    public int getData() {
        return data;
    }

    public Node getLeftChild() {
        return leftChild;
    }

    public Node getRightChild() {
        return rightChild;
    }

    // Inserts a new integer into the BST following the BST rules
    public void insert(int value) {

        if (value == data) { //check if value is duplicate
            return; //do nothing if duplicate
        }

        if (value < data) {//if value is smaller → go left

            if (leftChild == null) { //if no left child exists
                leftChild = new Node(value); //create a new node here
            } else {
                leftChild.insert(value); //otherwise continue recursion left
            }
        }

        else {// value is greater → go right

            if (rightChild == null) {   //if no right child exists
                rightChild = new Node(value); //create a new node
            } else {
                rightChild.insert(value); //otherwise continue recursion right
            }
        }
    }

    // In-order traversal ascending order
    public void inOrderTraverse() {

        if (leftChild != null) { //if left child exists
            leftChild.inOrderTraverse(); //visit the left subtree first
        }

        System.out.println("Traversed: " + this);// print current node

        if (rightChild != null) { // if right child exists
            rightChild.inOrderTraverse();// visit the right subtree next
        }
    }

    //Needed to traversal but descending
    public void inOrderTraverseDescending() {

        if (rightChild != null) { // visit right subtree first
            rightChild.inOrderTraverseDescending();
        }

        System.out.println("Traversed: " + this); // print current node

        if (leftChild != null) {// then visit left subtree
            leftChild.inOrderTraverseDescending();
        }
    }
    // Searches for a value in the BST
    public Node get(int value) {

        if (value == data) { // found the value
            return this;// return this node
        }

        if (value < data && leftChild != null) { // if value is smaller
            return leftChild.get(value);// search left subtree
        }
        if (value > data && rightChild != null) { // if value is larger
            return rightChild.get(value);// search right subtree
        }
        return null;// value not found
    }

    // Converts node to a readable string format
    @Override
    public String toString() {
        return "Node{data=" + data + '}'; // prints node data
    }
}

// Tree class represents the entire Binary Search Tree structure
class Tree {

    private Node root;  // root node of the BST

    // Inserts a new value into the tree
    public void insert(int value) {
        if (root == null) { //if tree is empty
            root = new Node(value);//new value becomes the root
        } else {
            root.insert(value);//otherwise insert using Node logic
        }
    }

    // Runs the in-order traversal (ascending)
    public void inOrderTraverse() {
        if (root != null) { //only traverse if tree isn't empty
            root.inOrderTraverse();
        }
    }

    //Runs in-order traversal in descending order
    public void traverseInOrderDescending() {
        if (root != null) { // only traverse if tree isn't empty
            root.inOrderTraverseDescending();
        }
    }

    // Searches for a node with the given value
    public Node get(int value) {
        if (root == null) {// empty tree → nothing to find
            return null;
        }
        return root.get(value);// use Node search logic
    }
    // Finds the minimum value in the BST (leftmost node)
    public Node getMin() {

        if (root == null) return null;//no nodes in the tree
        Node current = root;//start from the root
        while (current.getLeftChild() != null) { //keep going left
            current = current.getLeftChild();
        }
        return current; //smallest node found
    }

    //Finds the maximum value
    public Node getMax() {

        if (root == null) return null;//no nodes in tree
        Node current = root;//start from root
        while (current.getRightChild() != null) {//keep going right
            current = current.getRightChild();
        }
        return current; //largest node found
    }
}

//Main class that runs the entire program
public class Main {
    public static void main(String[] args) {
        Tree bst = new Tree();//create a new Binary Search Tree

        //Inserting he values into the tree one by one
        bst.insert(25);
        bst.insert(20);
        bst.insert(15);
        bst.insert(27);
        bst.insert(30);
        bst.insert(29);
        bst.insert(26);
        bst.insert(22);
        bst.insert(32);

        // Prints out the ascending order traversal
        System.out.println("Ascending");
        bst.inOrderTraverse();

        //Prints out the descending order traversal
        System.out.println("\nDescending");
        bst.traverseInOrderDescending();

        //show thee minimum value in the tree
        System.out.println("\nMinimum value: " + bst.getMin());

        //show the maximum value in the tree
        System.out.println("Maximum value: " + bst.getMax());

        //try to search for a value that doesn't exist
        int value = 100; //value to search for
        System.out.println("\nSearching for " + value + ": " + bst.get(value));
    }
}