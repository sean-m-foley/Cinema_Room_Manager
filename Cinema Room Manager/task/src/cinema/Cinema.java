package cinema;

import java.util.Scanner;

public class Cinema {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int numRows = 0;  // we have to initialize them
        int numSeats = 0; // to zero to avoid compiler warnings

        try { // these can possibly make errors if numbers are not input
            System.out.println("\nEnter the number of rows:");
            numRows = s.nextInt();

            System.out.println("Enter the number of seats in each row:");
            numSeats = s.nextInt();
        } catch (Exception e) {
            System.out.println("Invalid input: You need to enter numbers for the rows and seats!");
            System.out.println("Error info: " + e);
            System.exit(1);
        }

        char[][] cinema = new char[numRows][numSeats];
        for (int i = 0; i < numRows; i++) { // initialize
            for (int j = 0; j < numSeats; j++) {
                cinema[i][j] = 'S';
            }
        }

        while (true) {
            printMenu();
            int choice;

            try {
                choice = s.nextInt();
            } catch (Exception e) {
                System.out.println("You have to enter a valid choice: 1 - Show , 2 - Buy, or 0 - Exit");
                continue;
            }

            switch (choice) {
                case 1 -> printCinemaTable(cinema, numRows, numSeats);
                case 2 -> {
                    int purchaseRow = 0;  // zero initialized to avoid compiler warnings
                    int purchaseSeat = 0; // "

                    while (true) {
                        // get the seat we're trying to buy
                        try {
                            System.out.println("\nEnter a row number:");
                            purchaseRow = s.nextInt();
                            System.out.println("Enter a seat number in that row:");
                            purchaseSeat = s.nextInt();
                        } catch (Exception e) {
                            System.out.println("You have to enter the row and seat number for seat you wish to purchase");
                            System.out.println("Error info: " + e);
                            System.exit(1);
                        }

                        // check the input range
                        if ((purchaseRow - 1 >= numRows) || (purchaseSeat - 1 >= numSeats)) {
                            System.out.println("\nWrong input!");
                            // gonna have to do it again
                            continue;
                        }

                        // buy the ticket, check if the ticket was already bought.
                        if (!buyTicket(cinema, purchaseRow - 1, purchaseSeat - 1)) {
                            System.out.println("\nThat ticket has already been purchased!");
                            continue; // you have to re-ask for the input
                        }

                        // get ticket prices
                        int ticketPrice = getTicketPrice(numRows, numSeats, purchaseRow - 1);
                        System.out.println("Ticket price: $" + ticketPrice);

                        break; // you got the input so you don't need to loop again.
                    }
                }
                case 3 -> printStatS(cinema, numRows, numSeats);
                case 0 -> { return; } // JetBrains test engine doesn't understand
                                      // "System.exit(0);" like it should.
            }
        }
    }

    static void printCinemaTable(char [][] cinema, int numRows, int numSeats) {
        // draw the first table.
        System.out.println("\nCinema:");
        System.out.print("  ");
        for (int i = 0; i < numSeats; i++) {
            // top column headers
            System.out.print((i + 1) + " ");
        }
        System.out.println();

        for (int i = 0; i < numRows; i++) {
            // print the whole rest of the table
            System.out.print((i + 1) + " ");
            for (int j = 0; j < numSeats; j++) {
                System.out.print(cinema[i][j] + " ");
            }
            System.out.println();
        }
    }

    static int getTicketPrice(int numRows, int numSeats, int selectedRow) {
        if (numRows * numSeats <= 60) {
            return 10;
        } else {
            return selectedRow >= (numRows >> 1) ? 8 : 10;
        }
    }

    static boolean buyTicket(char [][] cinema, int selectedRow, int selectedSeat) {
        if (cinema[selectedRow][selectedSeat] == 'S') {
            cinema[selectedRow][selectedSeat] = 'B';
            return true;
        }
        return false; // this only happens if we can't buy a ticket.
    }

    static void printStatS(char [][] cinema, int numRows, int numSeats) {
        // prints the number of purchased tickets and does a bunch of calculations;
        int numberOfPurchasedTickets = 0;
        int totalNumberOfSeats = 0;
        int allTicketsSoldPrice = 0;
        int currentTicketsSoldPrice = 0;

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numSeats; j++) {
                if (cinema[i][j] == 'B') {
                    numberOfPurchasedTickets++;
                    currentTicketsSoldPrice += getTicketPrice(numRows, numSeats, i);
                }
                totalNumberOfSeats++;
                allTicketsSoldPrice += getTicketPrice(numRows, numSeats, i);
            }
        }

        System.out.println("\nNumber of purchased tickets: " + numberOfPurchasedTickets);
        System.out.printf("Percentage: %.2f%%\n", (float) numberOfPurchasedTickets / totalNumberOfSeats * 100);
        System.out.println("Current income: $" + currentTicketsSoldPrice);
        System.out.println("Total income: $" + allTicketsSoldPrice);
    }
    static void printMenu() {
        System.out.println("\n1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
    }
}