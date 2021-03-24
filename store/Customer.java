package Project;
import java.util.Scanner;
public class Customer extends Person implements MyProject {
	private int loyaltyCardNumber;
	private int loyaltyCardPoints;
	private boolean isLoyaltyStatusFrequent;
	private static Scanner input;
	public Customer(int id, String firstName, String lastName, String email, int loyaltyCardNumber,
			int loyaltyCardPoints, boolean isLoyaltyStatusFrequent) throws NegativeNumberException, EmailException {

		super(id, firstName, lastName, email);
		try {
			super.setEmail(email);
			this.getEmail().indexOf("@");
		} catch (EmailException exception) {
			System.err.print("Wrong email address. Must contain \"@\".");
		}
		if (loyaltyCardNumber >= 0) {
			this.loyaltyCardNumber = loyaltyCardNumber;
		} else {
			throw new NegativeNumberException("Error with card number");
		}
		this.loyaltyCardPoints = loyaltyCardPoints;
		this.isLoyaltyStatusFrequent = isLoyaltyStatusFrequent;
	}

	public Customer() {
		// Void constructor.
	}

	public void setId(int id) {
		setId(id);
	}

	public int getId() {
		return getId();
	}

	public void setFirstName(String firstName) {
		setFirstName(firstName);
	}

	public String getFirstName() {
		return getFirstName();
	}

	public void setLastName(String lastName) {
		setLastName(lastName);
	}

	public String getLastName() {
		return getLastName();
	}

	public void setEmail(String email) {
		setEmail(email);
		}

	public String getEmail() throws EmailException {
		if (this.getEmail().indexOf("@") > 0) {
			return getEmail();
		} else {
			throw new EmailException("");
		}
	}

	public void setLoyaltyCardNumber(int loyaltyNumber) {
		this.loyaltyCardNumber = loyaltyNumber;
	}

	public int getLoyaltyCardNumber() {
		return this.loyaltyCardNumber;
	}

	public void setLoyaltyCardPoints(int loyaltyCardPoints) {
		this.loyaltyCardPoints = loyaltyCardPoints;
	}

	public int getLoyaltyCardPoints() {
		return this.loyaltyCardPoints;
	}

	public void setIsLoyaltyStatusFrequent(boolean isLoyaltyStatusFrequent) {
		this.isLoyaltyStatusFrequent = isLoyaltyStatusFrequent;
	}

	public boolean getIsLoyaltyStatusFrequent() {
		return this.isLoyaltyStatusFrequent;
	}

	public void set(String[] data) throws IllegalArgumentException {
		if (data.length != 7) {
			throw new IllegalArgumentException("Illegal argument in data.");
		}
		setId(Integer.parseInt(data[0]));
		setFirstName(data[1]);
		setLastName(data[2]);
		setEmail(data[3]);
		setLoyaltyCardNumber(Integer.parseInt(data[4]));
		setLoyaltyCardPoints(Integer.parseInt(data[5]));
		setIsLoyaltyStatusFrequent(Boolean.parseBoolean(data[6]));
	}
	public Customer readFromStdio() {
		input = new Scanner(System.in);
		System.out.println("Please, introduce the First name: ");
		setFirstName(input.next());
		input = new Scanner(System.in);
		System.out.println("Please, introduce the Last name: ");
		setLastName(input.next());
		input = new Scanner(System.in);
		System.out.println("Please, introduce the email: ");
		setEmail(input.next());
		input = new Scanner(System.in);
		System.out.println("Please, introduce the ID: ");
		setId(input.nextInt());
		input = new Scanner(System.in);
		System.out.println("Please, introduce the loyalty card number: ");
		setLoyaltyCardNumber(input.nextInt());
		input = new Scanner(System.in);
		System.out.println("Please, introduce the loyalty card points: ");
		setLoyaltyCardPoints(input.nextInt());
		input = new Scanner(System.in);
		System.out.println("Please, introduce if the loyalty status is frequent: ");
		setIsLoyaltyStatusFrequent(input.nextBoolean());
		return this;
	}

}
