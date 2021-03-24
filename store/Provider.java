package Project;

import java.util.Scanner;

public class Provider /* implements MyProject */ implements Comparable {
	private int vat;
	private String name;
	private String taxAddress;
	private Person contactPerson;
	private static Scanner input;

	public Provider(int vat, String name, String taxAddress, Person contactPerson) {
		this.vat = vat;
		this.name = name;
		this.taxAddress = taxAddress;
		this.contactPerson = contactPerson;
	}

	public Provider() {
		// Void constructor.
	}

	public void setVat(int vat) {
		this.vat = vat;
	}

	public int getVat() {
		return this.vat;
	}

	public void setName(String name) {
		this.name = name;

	}

	public String getName() {
		return this.name;
	}

	public void setTaxAddress(String taxAddress) {
		this.taxAddress = taxAddress;
	}

	public String getTaxAddress() {
		return this.taxAddress;
	}

	public void setContactPerson(Person person) {
		this.contactPerson = person;
	}

	public Person getContactPerson() {
		return this.contactPerson;
	}

	public void set(String[] data) {
		// TODO Auto-generated method stub
		setVat(Integer.parseInt(data[0]));
		setName(data[1]);
		setTaxAddress(data[2]);
		Person auxPerson = new Person();
		try {
			auxPerson = new Person(Integer.parseInt(data[3]), data[4], data[5], data[6]);
		} catch (EmailException e) {
			System.err.println("Email error.");
		}
		setContactPerson(auxPerson);
	}

	public void print() {
		// TODO Auto-generated method stub
		System.out.println(this.toString());
	}

	public void writeToFile() {
		// TODO Auto-generated method stub
		String auxString = this.toString();

	}

	public Provider readFromStdio() {
		System.out.println("Please, insert the VAT. ");
		input = new Scanner(System.in);
		setVat((input.next().charAt(0)));
		System.out.println("Please, insert the name). ");
		input = new Scanner(System.in);
		setName(input.next());
		System.out.println("Please, insert the tax address. ");
		input = new Scanner(System.in);
		setTaxAddress(input.next());
		System.out.println("Please, insert the contact person. ");
		Person aux = new Person();
		setContactPerson(aux.readFromStdio());
		return this;
	}

	@Override
	public String toString() {
		return getVat() + "|" + getName() + "|" + getTaxAddress() + "|" + getContactPerson().toString();
	}

	@Override
	public int compareTo(Object o) {
		if (o.equals((Provider) this)) {

			return 0;
		} else {
			return 1;
		}
	}

	public boolean equals(Provider p) {
		if (p.vat == this.vat) {
			return true;
		} else {
			return false;
		}
	}

}
