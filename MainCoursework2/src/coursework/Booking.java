package coursework;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Booking {
	
	//Array initialisation and file location pointers.
	private static String SEATS = "seats.txt";
	private static String WAITINGLISTSEATS = "waiting.txt";
	private static Seat[] seats = new Seat [43];
	private static WaitingListSeat[] waitinglistseats = new WaitingListSeat[15];
	
	//Scanner for user input used throughout program.
	final static Scanner CONSOLE = new Scanner(System.in);
	
	//Variables used in cancel and seat booking methods
	static boolean userClass;
	static boolean userWindow;
	static boolean userAisle;
	static boolean userTable;
	static boolean userForward;
	static boolean userEaseOfAccess;
	static String userEmail;
	
	static int pointer;
	
	public static void main(String[] args) throws IOException
	{
		//File reading functionality.
		Scanner openSeatFile = new Scanner(new FileReader(SEATS));
		Scanner openWaitListFile = new Scanner(new FileReader(WAITINGLISTSEATS));
		
		String userChoice;
		
		pointer = 0;
		//Seat data read from seats file into array.
		while(openSeatFile.hasNext())
		{
			seats[pointer] = new Seat(openSeatFile.nextInt(), 
									  openSeatFile.nextBoolean(), 
									  openSeatFile.nextBoolean(), 
									  openSeatFile.nextBoolean(), 
									  openSeatFile.nextBoolean(), 
									  openSeatFile.nextBoolean(), 
									  openSeatFile.nextBoolean(), 
									  openSeatFile.next());
			pointer++;
		}
		
		pointer = 0;
		//Waiting list data read from seats file into array.
		while(openWaitListFile.hasNext())
		{
			waitinglistseats[pointer] = new WaitingListSeat(openWaitListFile.nextInt(), 
															openWaitListFile.nextBoolean(), 
															openWaitListFile.nextBoolean(), 
															openWaitListFile.nextBoolean(), 
															openWaitListFile.nextBoolean(), 
															openWaitListFile.nextBoolean(), 
															openWaitListFile.nextBoolean(), 
															openWaitListFile.next());
			pointer++;
		}
		//----MENU----
		//Menu repeats until user quits.
		do{
		System.out.println("---Main Menu---");
		System.out.println("[1] Reserve Seat");
		System.out.println("[2] Cancel Seat");
		System.out.println("[3] View Seat Reservations");
		System.out.println("[4] View Waiting List");
		System.out.println("[5] View Seat File Data");
		System.out.println("[0] Quit");
		userChoice = CONSOLE.next();
		
		//Main functionality.
		switch(userChoice)
		{
			case "1" :
			{
				System.out.println("---Reserving---");
				bookSeat();	
				break;
			}
			case "2" :
			{
				System.out.println("---Cancelling---");
				cancelSeat();
				break;
			}
			
			case "3" :
			{
				System.out.println("---Viewing Reserved Seats---");
				viewSeatReservations();
				break;
			}
			
			case "4" :
			{
				System.out.println("---Viewing Waiting List---");
				viewWaitingSeatReservations();
				break;
			}
			
			case "5" :
			{
				System.out.println("---Viewing Seat File---");
				viewSeatFileData();
				break;
			}
			
			case "0" :
			{
				break;
			}
			
			//Default error message if user input is incorrect while on the menu.
			default : 
			{
				if(!userChoice.equals("1")|| !userChoice.equals("2")|| !userChoice.equals("3")|| !userChoice.equals("4")|| !userChoice.equals("5")|| !userChoice.equals("0"))
				{
					System.out.println("Please choose a menu option number from those listed!");
				}
			}
		}
		
		}while(!userChoice.equals("0"));
		
		//Save changes made by user to seats file.
		PrintWriter saveSeatFile = new PrintWriter(new FileWriter(SEATS));
		for(Seat seat: seats) 
		{
			//Data saved in a neat format to seats file.
			saveSeatFile.println(seat.getSeatNo() + " " + 
					   seat.isSeatFirstClass() + " " +
					   seat.isWindow() + " " +
					   seat.isAisle() + " " +
					   seat.isTable() + " " +
					   seat.isForward() + " " +
					   seat.isEaseOfAccess() + " " +
					   seat.geteMail());
		}
		
		//Save changes made by user to waiting file.
		PrintWriter saveWaitingListFile = new PrintWriter(new FileWriter(WAITINGLISTSEATS));
		for(WaitingListSeat waitinglistseat: waitinglistseats)
		{
			//Data saved in a neat format to waiting file.
			saveWaitingListFile.println(waitinglistseat.getSeatNo() + " " + 
										waitinglistseat.isSeatFirstClass() + " " +
										waitinglistseat.isWindow() + " " +
										waitinglistseat.isAisle() + " " +
										waitinglistseat.isTable() + " " +
										waitinglistseat.isForward() + " " +
										waitinglistseat.isEaseOfAccess() + " " +
										waitinglistseat.geteMail());
		}
		
		//All files closed on exit.
		System.out.println("Exiting...Goodbye!");
		saveSeatFile.close();
		saveWaitingListFile.close();
		openSeatFile.close();
		openWaitListFile.close();
		CONSOLE.close();
	}
	
	//----MENU METHODS----
	//Menu Option 1
	public static void bookSeat() throws FileNotFoundException
	{
		//Variable used when user is asked if they want to book another seat.
		int userChoiceBooking;
		
		do{
		//Series of questions for user to determine seat to look for, reads inputs from user.
		System.out.println("Would you like a first class seat? \nAnswer true or false: ");
		userClass = CONSOLE.nextBoolean();
		
		System.out.println("Would you like a seat by a window? \nAnswer true or false:");
		userWindow = CONSOLE.nextBoolean();
		
		System.out.println("Would you like a seat an Aisle? \nAnswer true or false:");
		userAisle = CONSOLE.nextBoolean();
		
		System.out.println("Would you like a seat by a table? \nAnswer true or false:");
		userTable = CONSOLE.nextBoolean();
		
		System.out.println("Would you like a forward facing seat? \nAnswer true or false:");
		userForward = CONSOLE.nextBoolean();
		
		System.out.println("Would you like an ease of access seat? \nAnswer true or false:");
		userEaseOfAccess = CONSOLE.nextBoolean();

		System.out.println("Enter email to reserve seat:");
		userEmail = CONSOLE.next();
		
		//Error message if user inputs the incorrect email format, checks for @ and the length.
		if((!userEmail.contains("@"))||(userEmail.length() == 1))
		{
			System.out.println("Incorrect email format!");
			break;
		}
		
		//New seat object created from users choices.
		Seat userSeat = new Seat(0, userClass, userWindow, userAisle, userTable, userForward, userEaseOfAccess, "");
		
		pointer = 0;
		boolean match = false;
		while (pointer < seats.length && !match)
		{
			//Seat successfully found and reserved.	
			if(seats[pointer].equalToBooking(userSeat) && !seats[pointer].alreadyReserved()) 
			{
				System.out.println("Match " + seats[pointer].toString());
				seats[pointer].reserve(userEmail);
				match = true;
			}
			
			//Seat not yet found, prints message if the seat the pointer is at does not match the userSeat.
			else if(!seats[pointer].equalToBooking(userSeat))
			{
				System.out.println("Seat " + (pointer + 1) +" No match!");
				pointer++;
			}
			
			//Reserved seat message.
			else if(seats[pointer].equalToBooking(userSeat) && seats[pointer].alreadyReserved())
			{
				System.out.println("Seat " + (pointer + 1) + " Seat already booked!");
				pointer++;
			}
		}
		
		//Allows user to book another seat, else goes back to menu.
		System.out.println("Book another seat? \n[1] Yes\n[2] No ");
		userChoiceBooking = CONSOLE.nextInt();
		
		}while(userChoiceBooking == 1);
	}
	
	//Menu Option 2
	public static void cancelSeat()
	{
		System.out.println("Please input seat number to cancel seat: ");
		int userSeatNo = CONSOLE.nextInt();
		
		//Error message for incorrect seat number input.
		if((userSeatNo > seats.length)||(userSeatNo < 1));
		{
			System.out.println("Ensure seat Number is between the numbers 1 and 43");
		}
		
		if((userSeatNo <= seats.length)&&(userSeatNo >= 1))
		{
			
			//New seat object created taking into account the users input seat number.
			Seat userSeat = new Seat(userSeatNo, false, false, false, false, false, false, SEATS);
	
			pointer = 0;
			boolean match = false;
			while (pointer < userSeatNo && !match)
			{
				//Seat is cancelled.
				if(seats[pointer].equalToCancel(userSeat) && seats[pointer].alreadyReserved()) 
				{
					System.out.println("Match! Seat Cancelled: " + seats[pointer].toString());
					seats[pointer].cancel(userEmail);
					match = true;
				}
				
				//Seat unreserved already.
				else
					System.out.println("Seat " + (pointer + 1) + " Already Unreserved!");
					pointer++;
			}
		}
	}
	
	//Menu Option 3
	public static void viewSeatReservations()
	{
		pointer = 0;
		while (pointer < seats.length)
		{
			//Prints only the seats that are reserved to screen.
			if(seats[pointer].alreadyReserved()) 
			{
				System.out.println(seats[pointer].toString());	
			}
			//Continues through file.
			pointer++;
		}
	}
	
	//Menu Option 4
	public static void viewWaitingSeatReservations()
	{
		//Prints waiting list file data to screen.
		for (int i=0; i<waitinglistseats.length; i++) 
		{
			System.out.println(waitinglistseats[i].toString());
		}
	}
	
	//Menu Option 5
	public static void viewSeatFileData()
	{
		//Prints seats file data to screen.
		for (int i=0; i<seats.length; i++) 
		{
			System.out.println(seats[i].toString());
		}
	}
}