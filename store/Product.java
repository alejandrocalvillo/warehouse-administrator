package Project;
import java.util.Scanner;
public class Product implements MyProject {
	private String name;
	private char category;
	private boolean isCountable;
	private String measurementUnit;
	private String brand;
	private static Scanner input;

	public Product(String name, char category, boolean isCountable, String measurementUnit, String brand) {
		this.name = name;
		this.category = category;
		this.isCountable = isCountable;
		this.measurementUnit = measurementUnit;
		this.brand = brand;
	}

	public Product() {
		// void
	}

	/* Getters and Setters of all the variables previously defined */
	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getBrand() {
		return this.brand;
	}

	public void setName(String myName) {
		this.name = myName;
	}

	public String getName() {
		return this.name;
	}

	public void setCategory(char myCategory) {
		this.category = myCategory;
	}

	public char getCategory() {
		return this.category;
	}

	public void setIsCountable(boolean count) {
		this.isCountable = count;
	}

	public boolean getIsCountable() {
		return this.isCountable;
	}

	public void setMeasurementUnit(String myMeasure) {
		this.measurementUnit = myMeasure;
	}

	public String getMeasurementUnit() {
		return this.measurementUnit;
	}

	public void set(String[] data) {
		setName(data[0]);
		if (data[1].charAt(0) == 'f' || data[1].charAt(0) == 's' || data[1].charAt(0) == 'e'
				|| data[1].charAt(0) == 'm') {
			setCategory(data[1].charAt(0));
		} else {
			System.err.println("Error when creating " + this.getName()
					+ ". Not accepted type of data. Please, change the data.\n");
			return;
		}
		boolean aux;
		aux = Boolean.parseBoolean(data[2]);
		setIsCountable(aux);
		setMeasurementUnit(data[3]);
	}

	public String toString() {
		return "Name: " + getName() + "\\|Category:  " + getCategory() + "\\|Countable: " + getIsCountable()
				+ "\\|Measurement unit: " + getMeasurementUnit();
	}

	@Override
	public void print() {
		// TODO Auto-generated method stub
		System.out.println(this.toString());

	}

	@Override
	public void writeToFile() {
		// TODO Auto-generated method stub

	}
	public Product readFromStdio() {
		System.out.println("Please, introduce the name: ");
		input = new Scanner(System.in);
		this.setName(input.next());
		System.out.println("Please, introduce the category: ");
		input = new Scanner(System.in);
		this.setCategory(input.next().charAt(0));
		System.out.println("Please, introduce if it is countable: ");
		input = new Scanner(System.in);
		this.setIsCountable(input.nextBoolean());
		System.out.println("Please, introduce the measurement unit: ");
		input = new Scanner(System.in);
		this.setMeasurementUnit(input.next());
		System.out.println("Please, introduce the brand name: ");
		input = new Scanner(System.in);
		this.setBrand(input.next());
		return this;
	}

}
