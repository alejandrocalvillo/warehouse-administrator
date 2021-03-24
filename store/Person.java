package Project;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class Person implements Comparable {
	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private static Scanner input;

	public Person(int id, String firstName, String lastName, String email) throws EmailException {
		
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		try {
			setEmail(email);
		}catch (EmailException e){
			System.err.print("Email address is not correct");
		}
	}

	public Person() {

	}

	public void setID(int id) {
		this.id = id;
	}

	public int getID() {
		return this.id;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setEmail(String email) throws EmailException{
		if(email.contains("@")) {
			this.email=email;
		}else {
			throw new EmailException("");
		}
	}

	public String getEmail() throws EmailException {
		if (this.getEmail().indexOf("@") > 0) {
			return getEmail();
		} else {
			throw new EmailException("");
		}
	}

	
	public void set(String[] data) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		if (data.length != 4) {
			throw new IllegalArgumentException("The arguments number is not the proper one.");
		}
		int aux;
		aux = Integer.parseInt(data[0]);
		setID(aux);
		setFirstName(data[1]);
		setLastName(data[2]);
		
		try {
			getEmail();
			setEmail(data[3]);
		}catch(EmailException e) {
			System.err.println("Wrong email address");
		}
	}
	public Person readFromStdio() {
		System.out.println("Please, insert the ID. ");
		input= new Scanner(System.in);
		setID(input.nextInt());
		System.out.println("Please, insert the first name. ");
		input= new Scanner(System.in);
		setFirstName(input.next());
		System.out.println("Please, insert the last name. ");
		input= new Scanner(System.in);
		setLastName(input.next());
		System.out.println("Please, insert the email address. ");
		input= new Scanner(System.in);
		try {
		setEmail(input.next());
		}catch(EmailException e) {
			System.err.println("An error ocurred.");
		}
		return this;
	}

	
	public void print() {
		// TODO Auto-generated method stub
		System.out.println(this.toString());
	}

	public void writeToFile(String file) {
		// TODO Auto-generated method stub
		BufferedWriter out = null;
		try {
			if (file != null) {
				out = new BufferedWriter(new FileWriter(file, true));
				out.write(toString());
				out.flush();
				out.close();
			}

		} catch (IOException exc) {
			exc.printStackTrace();
			System.exit(-1);
		}
	}

	public String toString() {
        String s = "";
        try {
            s = getFirstName() + "|" + getLastName() + "|" + getEmail();
        } catch (EmailException e) {
            new EmailException("Error.");
        }
        return s;
    }


	
	public void writeToFile() {
		// TODO Auto-generated method stub

	}

	@Override
	public int compareTo(Object o) {
		if(o.equals((Person)this)) {
			return 0;
		}else {
			return 1;
		}
	}
	public boolean equals(Person p) {
		if(p.id==this.id) {
			return true;
		}else {
			return false;
		}
	}

}
