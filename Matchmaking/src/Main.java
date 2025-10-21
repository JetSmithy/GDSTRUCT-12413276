import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);//for Enter input
        Random rand = new Random();//for randomness
        ArrayQueue queue = new ArrayQueue(50);//queue with max size of 50

        int totalGames = 0;//track completed games

        System.out.println("Matchmaking Simulation");

        while (totalGames < 10) {//run until 10 games start
            int x = rand.nextInt(7) + 1;//random players joining (1–7)
            System.out.println("\nTurn: " + x + " new players joining matchmaking...");

            for (int i = 0; i < x; i++) {
                int num = rand.nextInt(1000);//random number 0–999
                String username = "Player_" + num;//create username
                queue.enqueue(username);//add player to queue
                System.out.println(username + " has joined the queue.");//show join
            }

            System.out.println("\nCurrent queue size: " + queue.size());//show queue size

            if (queue.size() >= 5) {//enough players for a match
                System.out.println("Starting a new game with 5 players:");
                for (int i = 0; i < 5; i++) {
                    String player = queue.dequeue();//remove player
                    System.out.println(" - " + player);//list participants
                }
                totalGames++;//increment number of games
                System.out.println("Game #" + totalGames + " has started!");
            } else {
                System.out.println("Not enough players yet. Need at least 5.");//wait message
            }

            System.out.println("\nPress Enter to proceed to next turn...");
            sc.nextLine();//wait for user input
        }

        System.out.println("\nAll 10 games have been successfully created!");
        sc.close();//close scanner
    }
}


//ArrayQueue Class
class ArrayQueue {
    private String[] queue;//holds players
    private int front;//index of first element
    private int rear;//index of last element
    private int size;//number of elements
    private int capacity;//max capacity

    public ArrayQueue(int capacity) {
        this.capacity = capacity;//store capacity
        queue = new String[capacity];//create array
        front = 0;//start index
        rear = -1;//no elements yet
        size = 0;//empty
    }

    public void enqueue(String player) {
        if (size == capacity) {//check full
            System.out.println("Queue is full! Cannot add " + player);//warning
            return;
        }
        rear = (rear + 1) % capacity;//move rear circularly
        queue[rear] = player;//store player
        size++;//increment count
    }

    public String dequeue() {
        if (isEmpty()) return null;//nothing to remove
        String player = queue[front];//get player
        front = (front + 1) % capacity;//move front circularly
        size--;//reduce size
        return player;//return dequeued player
    }

    public boolean isEmpty() {
        return size == 0;//true if empty
    }

    public int size() {
        return size;
    }
}
