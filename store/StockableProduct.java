package Project;

import java.util.Scanner;

public class StockableProduct extends Product implements Comparable {
	private static int productId;
	private int numUnits;
	private double costPerUnit;
	private double pricePerUnit;
	private Provider productProvider;
	private static Scanner input;

	public StockableProduct(String name, char category, boolean isCountable, String measurementUnit, String brand,
			int productId, int numUnits, double costPerUnit, double pricePerUnit, Provider productProvider) {
		super(name, category, isCountable, measurementUnit, brand);
		productId++;
		this.numUnits = numUnits;
		this.costPerUnit = costPerUnit;
		this.pricePerUnit = pricePerUnit;
		this.productProvider = productProvider;
	}

	public StockableProduct() {
		// Void constructor.
	}

	public void setProductProvider(Provider p) {
		this.productProvider = p;
	}

	public Provider getProductProvider() {
		return this.productProvider;
	}

	// Setters and Getters.
	public static void setProductId(int id) {
		productId = id;
	}

	public int getProductID() {
		return productId;
	}

	public void setNumUnits(int number) throws Exception {
		if (number >= 0) {
			this.numUnits = number;
		} else {
			throw new Exception();
		}
	}

	public int getNumUnits() {
		return this.numUnits;
	}

	public void setCostPerUnit(double cost) {
		this.costPerUnit = cost;
	}

	public double getCostPerUnit() {
		return this.costPerUnit;
	}

	public void setPricePerUnit(double price) {
		this.pricePerUnit = price;
	}

	public double getPricePerUnit() {
		return this.pricePerUnit;
	}

	public void set(String data[]) {
		setName(data[1]);
		if (data[3].charAt(0) == 'f' || data[3].charAt(0) == 's' || data[3].charAt(0) == 'e'
				|| data[3].charAt(0) == 'm') {
			setCategory(data[3].charAt(0));
		} else {
			System.err.println("Error when creating " + this.getName()
					+ ". Not accepted type of data. Please, change the data.\n");
			return;
		}
		boolean aux;
		aux = Boolean.parseBoolean(data[4]);
		setIsCountable(aux);
		setMeasurementUnit(data[5]);
		int auxInt;
		double auxDouble;
		auxInt = Integer.parseInt(data[0]);
		setProductId(auxInt);
		auxInt = Integer.parseInt(data[6]);
		try {
			setNumUnits(auxInt);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		auxDouble = Double.parseDouble(data[7]);
		setCostPerUnit(auxDouble);
		auxDouble = Double.parseDouble(data[8]);
		setPricePerUnit(auxDouble);
	}

	public String toString() {
		return getProductID() + "|" + getName() + "|" + getCategory() + "|" + getIsCountable() + "|"
				+ getMeasurementUnit() + "|" + getProductID() + "|" + getNumUnits() + "|" + getCostPerUnit() + " |"
				+ getPricePerUnit() + "|" + getProductProvider().toString();
	}

	public void print() {
		System.out.println(this.toString());
	}

	public void writeToFile() {

	}

	public StockableProduct readFromStdio() {
		System.out.println("Please, insert the name. ");
		input = new Scanner(System.in);
		this.setName(input.next());
		System.out.println("Please, insert the brand. ");
		input = new Scanner(System.in);
		this.setBrand(input.next());
		System.out.println("Please, insert the category. ");
		input = new Scanner(System.in);
		this.setCategory(input.next().charAt(0));
		System.out.println("Please, insert if it is countable (true/false). ");
		input = new Scanner(System.in);
		this.setIsCountable(input.nextBoolean());
		System.out.println("Please, insert the measurement unit. ");
		input = new Scanner(System.in);
		this.setMeasurementUnit(input.next());
		System.out.println("Please, insert the product ID. ");
		input = new Scanner(System.in);
		setProductId(input.nextInt());
		System.out.println("Please, insert the cost per unit. ");
		input = new Scanner(System.in);
		this.setCostPerUnit((double) input.nextDouble());
		System.out.println("Please, insert the price per unit. ");
		input = new Scanner(System.in);
		this.setPricePerUnit(input.nextDouble());
		System.out.println("Please, insert the number of units. ");
		input = new Scanner(System.in);
		try {
			this.setNumUnits(input.nextInt());
		} catch (Exception e) {
			System.err.println("ERROR. Negative units are not allowed");
		}

		System.out.println("Please, insert the provider. ");
		Provider aux = new Provider();
		this.setProductProvider(aux.readFromStdio());
		return this;
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		if (o.equals((StockableProduct) this)) {
			return 0;
		} else {
			return 1;
		}

	}

	// @Override
	public boolean equals(StockableProduct s) {
		if (this.getProductID() == s.getProductID()) {
			return true;
		} else {
			return false;
		}
	}

}
