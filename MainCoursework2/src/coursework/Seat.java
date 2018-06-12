package coursework;

public class Seat {
	
	//Variables
	private int seatNo;
	private boolean seatFirstClass;
	private boolean window;
	private boolean aisle;
	private boolean table;
	private boolean forward;
	private boolean easeOfAccess;
	private String eMail;
	
	//Constructor for Seat object.
	public Seat(int seatNo, boolean seatFirstClass, boolean window, boolean aisle, boolean table, 
				boolean forward, boolean easeOfAccess, String eMail) 
	{
		this.seatNo = seatNo;
		this.seatFirstClass = seatFirstClass;
		this.window = window;
		this.aisle = aisle;
		this.table = table;
		this.forward = forward;
		this.easeOfAccess = easeOfAccess;
		this.eMail = eMail;
	}

	//----GETTER AND SETTER METHODS----
	public int getSeatNo() {
		return seatNo;
	}

	public boolean isSeatFirstClass() {
		return seatFirstClass;
	}

	public boolean isWindow() {
		return window;
	}

	public boolean isAisle() {
		return aisle;
	}

	public boolean isTable() {
		return table;
	}

	public boolean isForward() {
		return forward;
	}

	public boolean isEaseOfAccess() {
		return easeOfAccess;
	}

	public String geteMail() {
		return eMail;
	}
	
	public void reserve(String eMail) {
		this.eMail = eMail;
	}
	
	//Default value for the email in the seats file.
	public void cancel(String eMail)
	{
		this.eMail = "X";
	}
	
	//----FORMATTING AND CHECKING METHODS----
	//Neatly formats the output of the seat data to the screen.
	public String toString() {
		String result = "Seat Number: " + seatNo + 
						" | First Class: " + seatFirstClass + 
						" | Window: " + window + 
						" | Aisle: " + aisle + 
						" | Table: " + table + 
						" | Forward: " + forward + 
						" | Ease of Access: " + easeOfAccess + 
						" | eMail: " + eMail;
				return result;
	}
	
	//Checks if the seat matches the userSeat in the bookSeat() method in the booking class.
	public boolean equalToBooking(Seat s) {
		if (this.seatFirstClass == s.seatFirstClass && this.window == s.window && this.aisle == s.aisle &&
			this.table == s.table &&  this.forward == s.forward && this.easeOfAccess == s.easeOfAccess)
			return true;
		else
			return false;
	}
	
	//Checks to see if the seat number matches the userSeatNo in the cancelSeat methods in the booking class.
	public boolean equalToCancel(Seat s)
	{
		if(this.seatNo == s.seatNo)
			return true;
		else
			return false;
	}
	
	//Checks to see if the email length is greater than 1, confirming whether or not it is reserved.
	public boolean alreadyReserved() {
		if (eMail.length() == 1)
			return false;
		else
			return true;
	}
}