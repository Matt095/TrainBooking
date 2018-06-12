package coursework;

public class WaitingListSeat {
	
	//Variables
	private int seatNo;
	private boolean seatFirstClass;
	private boolean window;
	private boolean aisle;
	private boolean table;
	private boolean forward;
	private boolean easeOfAccess;
	private String eMail;
	
	//Constructor for WaitingListSeat object.
	public WaitingListSeat(int seatNo, boolean seatFirstClass, boolean window, boolean aisle, boolean table, 
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
	
	//Default value for the email in the waiting file.
	public void cancel(String eMail)
	{
		this.eMail = "X";
	}
	
	//----FORMATTING AND CHECKING METHODS----
	//Neatly formats the output of the waitinglistseat data to the screen.
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
}