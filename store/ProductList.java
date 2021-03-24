package Project;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ProductList implements MyProject {
	private ArrayList<StockableProduct> list;
	private double totalCost;
	private double totalPrice;
	private double totalBenefit;
	private static Scanner input;

	public ProductList(ArrayList<StockableProduct> list, double totalCost, double totalPrice, double totalBenefit) {
		this.setList(list);
		this.setTotalCost(this.calculateTotalCost());
		this.setTotalPrice(this.calculateTotalPrice());
		this.setTotalBenefit(this.calculateTotalBenefit());
	}

	public ProductList() {
		// Void constructor.
	}

	public void setList(ArrayList<StockableProduct> listToSet) {
		this.list = listToSet;
	}

	public List<StockableProduct> getList() {
		return this.list;
	}

	public void setTotalCost(double cost) {
		this.totalCost = cost;
	}

	public double getTotalCost() {
		return this.totalCost;
	}

	public void setTotalPrice(double price) {
		this.totalPrice = price;
	}

	public double getTotalPrice() {
		return this.totalPrice;
	}

	public void setTotalBenefit(double benefit) {
		this.totalBenefit = benefit;
	}

	public double getTotalBenefit() {
		return this.totalBenefit;
	}

	@Override
	public void set(String[] data) {
        StockableProduct aux=new StockableProduct();
        aux.setName(data[0]);
        ArrayList<StockableProduct> strList=new ArrayList<StockableProduct>();/*Y si introducimos directamente data[0] en vez de pasarlo por lista?*/
        strList=Arrays.asList(aux);
        setList(strList);
        setTotalCost(Double.parseDouble(data[1]));
        setTotalPrice(Double.parseDouble(data[2]));
        setTotalBenefit(Double.parseDouble(data[3]));
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

	public String toString() {
		String auxString = "";
		for (int i = 0; i < this.list.size(); i++) {
			auxString = auxString.concat(this.list.get(i).getName()) + "\n";
			/* string creado para almacenar todos los productos de la lista */
		}
		return "List: " + auxString + "\\|Cost: " + getTotalCost() + "\\|Price: " + getTotalPrice() + "\\|Benefit: "
				+ getTotalBenefit();
	}

	public double calculateTotalCost() {
		double cost = 0;
		for (int i = 0; i < this.list.size(); i++) {
			cost += this.list.get(i).getCostPerUnit() * this.list.get(i).getNumUnits();
		}
		return cost;
	}

	public double calculateTotalBenefit() {
		double benefit = 0;
		benefit = this.totalPrice-this.totalCost;
		return benefit;
	}

	public double calculateTotalPrice() {
		double price = 0;
		for (int i = 0; i < this.list.size(); i++) {
			price += this.list.get(i).getPricePerUnit() * this.list.get(i).getNumUnits();
		}
		return price;
	}

	public Product mostExpensiveProduct() {
		Product aux = new Product();
		aux = this.list.get(0);
		for (int i = 0; i < this.list.size(); i++) {
			for (int j = 0; j < this.list.size(); j++) {
				if (this.list.get(j).getPricePerUnit() > this.list.get(i).getPricePerUnit()) {
					aux = this.list.get(j);
				}
			}
		}
		return aux;
	}

	public Product cheapestProduct() {
		Product aux = new Product();
		aux = this.list.get(0);
		for (int i = 0; i < this.list.size(); i++) {
			for (int j = 0; j < this.list.size(); j++) {
				if (this.list.get(j).getPricePerUnit() < this.list.get(i).getPricePerUnit()) {
					aux = this.list.get(j);
				}
			}
		}
		return aux;
	}

	public ProductList readFromStdio() {
		
		char a = 'y';
		while ('y' == a) {
			StockableProduct aux = new StockableProduct();
			System.out.println("Please introduce the Stockable Product you want to add to the list: ");
			list.add(aux.readFromStdio());
			System.out.println("Would you like to add another one?: (y/n)");
			input = new Scanner(System.in);
			a = input.next().charAt(0);
		}
		System.out.println("Please, introduce the total cost: ");
		setTotalCost(input.nextDouble());
		System.out.println("Please, introduce the total price: ");
		setTotalPrice(input.nextDouble());
		System.out.println("Please, introduce the total benefit: ");
		setTotalBenefit(input.nextDouble());
		return this;
	}
	
	public ProductList readFromFile(String path) throws IOException, EmailException {
		ProductList products=new ProductList();
		ArrayList<StockableProduct> auxList=new ArrayList<StockableProduct>(); 
		Path file= Paths.get(path);
		Charset charset = Charset.forName("US-ASCII");
		BufferedReader reader= Files.newBufferedReader(file, charset);
		StringBuffer sb=new StringBuffer();
		String[] data = null;
		String line=null;
		while ((line=reader.readLine())!=null) {
			 StockableProduct aux=new StockableProduct();
			 data=line.split("\\|");
			 aux.set(data);
			 Person auxPerson=new Person(Integer.parseInt(data[12]), data[13],data[14],data[15]);
			 Provider auxProvider=new Provider (Integer.parseInt(data[9]), data[10],data[11],auxPerson);
			 aux.setProductProvider(auxProvider);
			 sb.append("\n");
			 auxList.add(aux);
		}
		products.setList(auxList);
		return products;
	}

	public int indexOf(int productID) {
		StockableProduct aux = this.getList().get(0);
		int count = 0;
		while (!aux.equals(this.getList().get(this.getList().size() - 1))) {
			if (aux.getProductID() == productID) {
				return count;
			} else {
				count++;
				aux = this.getList().get(count);
			}
		}
		if (aux.getProductID() == productID) {
			return count;
		} else {
			System.err.print("This product does not belong to this list");
			return -1;
		}
	}

	public StockableProduct search(int productID) {
		StockableProduct aux = this.getList().get(0);
		int count = 0;
		while (!aux.equals(this.getList().get(this.getList().size() - 1))) {
			if (aux.getProductID() == productID) {
				return aux;
			} else {
				count++;
				aux = this.getList().get(count);
			}
		}
		if (aux.getProductID() == productID) {
			return aux;
		} else {
			System.err.print("This product does not belong to this list");
			return null;
		}
	}

	public void insert(StockableProduct other) throws Exception {
		if (this.indexOf(other.getProductID()) == -1) {
			this.getList().add(other);
		} else {
			StockableProduct existentProduct = this.search(other.getProductID());
			existentProduct.setNumUnits(existentProduct.getNumUnits() + other.getNumUnits());
		}
	}

	public StockableProduct remove(int productID, int numUnits) throws Exception {
		if (this.search(productID) == null) {
			return null;
		} else {
			StockableProduct existentProduct = this.search(productID);
			if (existentProduct.getNumUnits() - numUnits <= 0) {
				this.getList().remove(existentProduct);
			} else {
				existentProduct.setNumUnits(existentProduct.getNumUnits() - numUnits);
				this.search(productID).setNumUnits(existentProduct.getNumUnits());
			}
			return this.search(productID);
		}
	}

	public void modify(int productID) {
		if (this.search(productID) == null) {
			return;
		} else {
			this.search(productID).readFromStdio();
		}

	}

}