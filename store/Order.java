package Project;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Order extends ProductList {
	private static int orderID = 0;
	private Person client;
	private Person employee;
	private static Scanner input;

	public Order(ArrayList<StockableProduct> list, double totalCost, double totalPrice, double totalBenefit,
			Person client, Person employee) {
		super(list, totalCost, totalPrice, totalBenefit);
		orderID++;
		this.client = client;
		this.employee = employee;
	}

	public Order() {
		// void constructor
	}

	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public Person getClient() {
		return client;
	}

	public Person setClient(Person client) {
		this.client = client;
		return client;
	}

	public Person getEmployee() {
		return employee;
	}

	public Person setEmployee(Person employee) {
		this.employee = employee;
		return employee;
	}

	public void set(String[] data) {
		// TODO Auto-generated method stub
		// setList(data[0]);
		/* Parsear la lista a String???? */
	}

	@Override
	public void print() {
		// TODO Auto-generated method stub
		System.out.println(this.toString());
	}

	public void writeToFile(Order auxOrder) {
		String name;
		name = auxOrder.getOrderID() + "" + auxOrder.getClient().getID() + "" + auxOrder.getEmployee().getID() + ".txt";
		System.out.println(name);
		String p = "storeName/ordersToProcess/" + name;

		try {

			BufferedWriter out = new BufferedWriter(new FileWriter(p));
			for (int i = 0; i < auxOrder.getList().size() - 1; i++) {
				out.write(auxOrder.getList().get(i).toString());
				out.write(System.getProperty("line.separator"));
			}
			out.flush();
			out.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String toString() {
		return "OrderID: " + getOrderID() + "\\|List: " + getList() + "\\|Cost: " + getTotalCost() + "\\|Price: "
				+ getTotalPrice() + "\\|Benefit: " + getTotalBenefit() + "\\|Client: " + getClient() + "\\|Employee: "
				+ getEmployee();
	}

	public Order readFromStdio() {
		char aux = 'y';
		StockableProduct auxStockProd = new StockableProduct();
		StockableProduct auxStockP = new StockableProduct();
		ArrayList<StockableProduct> auxList = new ArrayList<StockableProduct>();
		Person auxPers = new Person();
		while (aux == 'y') {
			System.out.println("Please, insert the the elements of the list");
			auxStockP = auxStockProd.readFromStdio();
			auxList.add(auxStockP);
			this.setList(auxList);
			System.out.println("Would you like to add another element to the list?(y/n)");
			input = new Scanner(System.in);
			aux = input.next().charAt(0);
		}
		System.out.println("Please, insert the total cost.");
		input = new Scanner(System.in);
		setTotalCost(input.nextDouble());
		System.out.println("Please, insert the total price.");
		setTotalPrice(input.nextDouble());
		System.out.println("Please, insert the total benefit.");
		setTotalBenefit(input.nextDouble());
		System.out.println("Please, insert the employee.");
		setEmployee(auxPers.readFromStdio());
		System.out.println("Please, insert the Customer.");
		setClient(auxPers.readFromStdio());
		System.out.println("Please, insert the order ID.");
		setOrderID(input.nextInt());
		return this;
	}

	public Order readFromFile(String path) throws IOException, EmailException {
		Order auxOrder = new Order();
		Path file = Paths.get(path);
		Charset charset = Charset.forName("US-ASCII");
		BufferedReader reader = Files.newBufferedReader(file, charset);
		StringBuffer sb = new StringBuffer();
		String[] data = null;
		String line = null;
		ArrayList<StockableProduct> list = new ArrayList<StockableProduct>();
		int i = 0;
		while ((line = reader.readLine()) != null) {
			StockableProduct aux = new StockableProduct();
			data = null;
			data = line.split("\\|");
			aux.set(data);
			Person auxPerson = new Person(Integer.parseInt(data[12]), data[13], data[14], data[15]);
			Provider auxProvider = new Provider(Integer.parseInt(data[9]), data[10], data[11], auxPerson);
			aux.setProductProvider(auxProvider);
			list.add(i, aux);
			i++;
			sb.append("\n");
		}
		auxOrder.setList(list);
		auxOrder.setTotalCost(auxOrder.calculateTotalCost());
		auxOrder.setTotalPrice(auxOrder.calculateTotalPrice());
		auxOrder.setTotalBenefit(auxOrder.calculateTotalBenefit());
		if (path != null) {
			String[] info = path.split("\\\\");
			String[] totalInfo = info[2].split("_");// Probar con dos barras si no
			String[] infoEmployee = totalInfo[2].split("\\.");
			Person client = new Person();
			Person employee = new Person();
			auxOrder.setOrderID(Integer.parseInt(totalInfo[0]));
			client.setID(Integer.parseInt(totalInfo[1]));
			employee.setID(Integer.parseInt(infoEmployee[0]));
			auxOrder.setClient(client);
			auxOrder.setEmployee(employee);
		}
		return auxOrder;
	}
}