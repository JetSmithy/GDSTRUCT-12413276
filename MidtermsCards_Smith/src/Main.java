import java.util.Random; //for random number generation
import java.util.Scanner; //for user input

// Card CLASS
class Card {
    private String name; //card name (e.g., "Card 5")
    public Card(String name) { this.name = name; } //constructor assigns name
    public String getName() { return name; } //getter for card name
    public String toString() { return "Card{" + "name='" + name + '\'' + '}'; } //how the card prints
    @Override
    public boolean equals(Object o) { //check if two cards are equal
        if (o == null || getClass() != o.getClass()) return false; //ensure same class
        Card card = (Card) o; //cast object to Card
        return name.equals(card.name); //compare names
    }

    @Override
    public int hashCode() { return name.hashCode(); } //hash code based on name
}

// CardStack CLASS (Array-based Stack)
class CardStack {
    private Card[] stack; //array that holds the cards
    private int top; //points to the next empty position (top of stack)

    public CardStack(int capacity) { //constructor with capacity
        stack = new Card[capacity]; //create new array
        top = 0; //stack starts empty
    }

    public void push(Card card) { //add a card to the top of the stack
        if (top == stack.length) { //if stack is full, resize
            Card[] newStack = new Card[stack.length * 2]; //double the size
            System.arraycopy(stack, 0, newStack, 0, stack.length); //copy old to new
            stack = newStack; //update reference
        }
        stack[top++] = card; //store card and increment top
    }

    public Card pop() { //remove and return the top card
        if (isEmpty()) return null; //if no cards, return null
        return stack[--top]; //decrease top then return the card
    }

    public Card peek() { //view top card without removing
        if (isEmpty()) return null; //empty check
        return stack[top - 1]; //return top card
    }

    public boolean isEmpty() { return top == 0; } //true if stack is empty

    public int size() { return top; } //current number of cards

    public void printStack() { //display all cards
        if (isEmpty()) { System.out.println("[Empty]"); } //no cards
        else { for (int i = top - 1; i >= 0; i--) System.out.println(stack[i]); } //print from top to bottom
    }
}

// MAIN CLASS (Game Logic)
public class Main {
    public static void main(String[] args) {
        CardStack playerDeck = new CardStack(30); //main deck (starts with 30)
        CardStack playerHand = new CardStack(10); //player's hand
        CardStack discardPile = new CardStack(10); //discarded pile

        for (int i = 1; i <= 30; i++) playerDeck.push(new Card("Card " + ((i % 10) + 1))); //fill deck with 30 cards

        Scanner scanner = new Scanner(System.in); //scanner for pressing Enter
        Random random = new Random(); //random for commands

        System.out.println("=== CARD DRAW SIMULATION GAME ==="); //intro
        System.out.println("Press Enter to start..."); //wait for input
        scanner.nextLine(); //wait for Enter

        while (!playerDeck.isEmpty()) { //loop until deck is empty
            int command = random.nextInt(3); //0 = draw, 1 = discard, 2 = retrieve
            int x = random.nextInt(5) + 1; //random amount (1–5)

            System.out.println("\n------------------------------------------");

            switch (command) { //decide what to do this turn
                case 0: //Draw cards
                    System.out.println("Command: DRAW " + x + " card(s)");
                    for (int i = 0; i < x; i++) { // repeat x times
                        if (!playerDeck.isEmpty()) playerHand.push(playerDeck.pop()); //move from deck to hand
                        else { System.out.println("Deck is empty!"); break; } //stop if no cards left
                    }
                    break;

                case 1: //Discard cards
                    System.out.println("Command: DISCARD " + x + " card(s)");
                    for (int i = 0; i < x; i++) { //repeat x times
                        if (!playerHand.isEmpty()) discardPile.push(playerHand.pop()); //move from hand to discard
                        else { System.out.println("No cards in hand to discard!"); break; } //stop if none in hand
                    }
                    break;

                case 2://R cards
                    System.out.println("Command: RETRIEVE " + x + " card(s) from discard pile");
                    for (int i = 0; i < x; i++) {//repeat x times
                        if (!discardPile.isEmpty()) playerHand.push(discardPile.pop());//move from discard to hand
                        else { System.out.println("No cards in discard pile!"); break; }//stop if none
                    }
                    break;
            }

            System.out.println("\nPlayer Hand:"); //show player's cards
            playerHand.printStack(); //print hand contents

            System.out.println("\nDeck cards remaining: " + playerDeck.size()); //deck size
            System.out.println("Discard pile size: " + discardPile.size()); //discard size

            System.out.println("\nPress Enter for next turn..."); //wait for Enter
            scanner.nextLine(); //pause
        }

        System.out.println("\n=== GAME OVER ==="); //when deck empty
        System.out.println("The player deck is now empty!"); //final message
    }
}
