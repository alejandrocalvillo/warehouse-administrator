package Project;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class StoreManager {
	private String name;
	private double stockCost;
	private double stockBenefit;
	private double stockPrice;
	private ProductList stock;
	private LinkedQueue<Order> ordersToProcess;
	private LinkedList<Order> ordersProcessed;
	private LBSTree<Person> storeCustomers;
	private LBSTree<Provider> storeProviders;
	private LBSTree<Person> storeEmployees;
	private String[] storeDatainfo;
	private static Scanner input;

	public StoreManager(String name, double stockCost, double stockBenefit, ProductList stock,
			LBSTree<Person> storeCustomers, LinkedQueue<Order> ordersToProcess, LinkedList<Order> ordersProcessed,
			LBSTree<Provider> storeProviders, LBSTree<Person> storeEmployees, String[] storeDataInfo) {
		this.name = name;
		this.stockCost = stockCost;
		this.stockBenefit = stockBenefit;
		this.stock = stock;
		this.ordersToProcess = ordersToProcess;
		this.ordersProcessed = ordersProcessed;
		this.storeCustomers = storeCustomers;
		this.storeProviders = storeProviders;
		this.storeEmployees = storeEmployees;
		this.storeDatainfo = storeDataInfo;
	}

	public StoreManager(String name, String stock, String ordersToProcess, String ordersProcessed,
			String storeCustomers, String storeProviders, String storeEmployees) {
		this.name = name;
		String auxArray[] = {name, stock, ordersToProcess, ordersProcessed, storeCustomers, storeProviders,storeEmployees};
		this.set(auxArray);
		this.stockPrice=this.calculateStockPrice();
		this.stockCost = this.calculateStockCost();
		this.stockBenefit = this.calculateStockBenefit();
	}

	public double calculateStockCost() {
		return this.stock.calculateTotalCost();
	}

	public double calculateStockBenefit() {
		return this.calculateStockBenefit();
	}
	public double calculateStockPrice() {
		return this.stockBenefit-this.stockCost;
	}
	public void setStockPrice(double price) {
        this.stockPrice=price;
    }
	
	/*
	 * public StoreManager(String name, String stock, String ordersToProcess,String
	 * ordersProcessed, String storeCustomers, String storeProviders, String
	 * storeEmployees) { this.name=name; this.stock=stock; t }
	 */

	public StoreManager() {
		// Void constructor.
	}

	public void setStoreCustomers(LBSTree<Person> customers) {
		this.storeCustomers = customers;
	}

	public LBSTree<Person> getStoreCustomers() {
		return this.storeCustomers;
	}

	public void setStoreDataInfo(String[] info) {
		this.storeDatainfo = info;
	}

	public String[] getStoreDataInfo() {
		return this.storeDatainfo;
	}

	public void setStoreEmployees(LBSTree<Person> employees) {
		this.storeEmployees = employees;
	}
	
	public void setStoreEmployees(String path) throws IOException, EmailException {
		Path file= Paths.get(path);
		Charset charset = Charset.forName("US-ASCII");
		BufferedReader reader= Files.newBufferedReader(file, charset);
		String[] data = null;
		String line=null;
		LBSNode<Person> auxEmployee;
		LBSTree<Person> auxTree=new LBSTree<Person>();
		LBSTree<Person> auxTree2=new LBSTree<Person>();
		int key=0;
		while ((line=reader.readLine())!=null) {
			 data=line.split("\\|");
			 Person employee=new Person(Integer.parseInt(data[0]), data[1],data[2],data[3]);
			 if (auxTree.isEmpty()) {
				 auxEmployee=new LBSNode<Person>(key, employee, new LBSTree<Person>(), new LBSTree<Person>());
				 auxTree.setRoot(auxEmployee);
			 }else {
				 if(key%2==0) {
					 auxEmployee=new LBSNode<Person>(key, employee, new LBSTree<Person>(), new LBSTree<Person>());
					 auxTree2.setRoot(auxEmployee);
					 auxTree.getRoot().setRight(auxTree2);
				 }else {
					 auxEmployee=new LBSNode<Person>(key, employee, new LBSTree<Person>(), new LBSTree<Person>());
					 auxTree2.setRoot(auxEmployee);
					 auxTree.getRoot().setLeft(auxTree2);
				 }
				 
				 
			 }
			 key++;
			 }
			 setStoreEmployees(auxTree);
	}

	public LBSTree<Person> getStoreEmployees() {
		return this.storeEmployees;
	}

	public void setStoreProviders(LBSTree<Provider> provider) {
		this.storeProviders = provider;
	}
	
	public void setStoreProviders(String path) throws IOException, EmailException {
		Path file= Paths.get(path);
		Charset charset = Charset.forName("US-ASCII");
		BufferedReader reader= Files.newBufferedReader(file, charset);
		String[] data = null;
		String line=null;
		LBSNode<Provider> auxProvider;
		LBSTree<Provider> auxTree=new LBSTree<Provider>();
		LBSTree<Provider> auxTree2=new LBSTree<Provider>();
		int key=0;
		while ((line=reader.readLine())!=null) {
			 data=line.split("\\|");
			 Person employee=new Person(Integer.parseInt(data[3]), data[4],data[5],data[6]);
			 Provider provid=new Provider(Integer.parseInt(data[0]), data[1],data[2], employee);
			 if (auxTree.isEmpty()) {
				 auxProvider=new LBSNode<Provider>(key, provid, new LBSTree<Provider>(), new LBSTree<Provider>());
				 auxTree.setRoot(auxProvider);
			 }else {
				 if(key%2==0) {
					 auxProvider=new LBSNode<Provider>(key, provid, new LBSTree<Provider>(), new LBSTree<Provider>());
					 auxTree2.setRoot(auxProvider);
					 auxTree.getRoot().setRight(auxTree2);
				 }else {
					 auxProvider=new LBSNode<Provider>(key, provid, new LBSTree<Provider>(), new LBSTree<Provider>());
					 auxTree2.setRoot(auxProvider);
					 auxTree.getRoot().setLeft(auxTree2);
				 } 
			 }
			 key++;
			 }
			 setStoreProviders(auxTree);
	}

	public LBSTree<Provider> getStoreProviders() {
		return this.storeProviders;
	}

	public void setOrdersProcessed(LinkedList<Order> orders) {
		this.ordersProcessed = orders;
	}
	
	public void setOrdersProcessed(String file) throws EmailException, IOException {
		String content = Files.readString(Paths.get(file));
		this.storeDatainfo[3]=content;
		String[] strContent=content.split("|");
		StockableProduct aux;
		Provider auxProvider;
		Person auxPerson;
		int n=0;
		for(int i=0; i==strContent.length;i++) {
			auxPerson=new Person(Integer.parseInt(strContent[i+12]), strContent[i+13],strContent[i+14],strContent[i+15]);
			auxProvider=new Provider (Integer.parseInt(strContent[i+9]), strContent[i+10],strContent[i+11],auxPerson);
			aux= new StockableProduct (strContent[i], strContent[i+1].charAt(0),Boolean.parseBoolean(strContent[i+2]), strContent[i+3],strContent[i+4], Integer.parseInt(strContent[i+5]), Integer.parseInt(strContent[i+6]),Double.parseDouble(strContent[i+6]),Double.parseDouble(strContent[i+7]), auxProvider );
			/**/
			i=i+16;//Because Provider has 7 atributes
			n++;
		};
		
	}

	public LinkedList<Order> getOrdersProcessed() {
		return this.ordersProcessed;
	}

	public void setOrdersToProcess(LinkedQueue<Order> orders) {
		this.ordersToProcess = orders;
	}
	
	public void setOrdersToProceed(String file) throws IOException, EmailException {
		String content = Files.readString(Paths.get(file));
		this.storeDatainfo[2]=content;
		String[] strContent=content.split("|");
		StockableProduct aux;
		Provider auxProvider;
		Person auxPerson;
		int n=0;
		for(int i=0; i==strContent.length;i++) {
			auxPerson=new Person(Integer.parseInt(strContent[i+12]), strContent[i+13],strContent[i+14],strContent[i+15]);
			auxProvider=new Provider (Integer.parseInt(strContent[i+9]), strContent[i+10],strContent[i+11],auxPerson);
			aux= new StockableProduct (strContent[i], strContent[i+1].charAt(0),Boolean.parseBoolean(strContent[i+2]), strContent[i+3],strContent[i+4], Integer.parseInt(strContent[i+5]), Integer.parseInt(strContent[i+6]),Double.parseDouble(strContent[i+6]),Double.parseDouble(strContent[i+7]), auxProvider );
			/*this.ordersToProcess.enqueue(aux);*/
			i=i+16;//Because Provider has 7 atributes
			n++;
		};
	}


	public LinkedQueue<Order> getOrdersToProcess() {
		return this.ordersToProcess;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setStockCost(double cost) {
		this.stockCost = cost;
	}

	public double getStockCost() {
		return this.stockCost;
	}

	public void setStockBenefit(double benefit) {
		this.stockBenefit = benefit;
	}

	public double getStockBenefit() {
		return this.stockBenefit;
	}

	public void setStock(ProductList stock) {
		this.stock = stock;
	}

	public ProductList getStock() {
		return this.stock;
	}

	public void showMainMenu() {
		System.out.println("--------MainMenu--------");
		System.out.println("Store info: Store name: " + this.name + "Stock cost: " + this.stockCost + "Stock benefit: "
				+ this.stockBenefit);
	}

	public void set(String[] data) {
		setName(data[0]);
		setStockCost(Double.parseDouble(data[1]));
		setStockBenefit(Double.parseDouble(data[2]));
		/* setProductList(Arrays.asList(data[3])); */

	}

	public void print() {
		// TODO Auto-generated method stub
		System.out.println(this.toString());
	}

	public void writeToFile() {
		/************
		 * Since we're implementing the MyProject interface, we must include this method
		 * in this class, but to not cause any problems we'll make it void and it'll be
		 * empty.
		 ***********/

	}

	public StoreManager readfromStdio() {
		System.out.println("Please, introduce the name: ");
		input = new Scanner(System.in);
		setName(input.next());
		System.out.println("Please, introduce the stock cost");
		input = new Scanner(System.in);
		setStockCost(input.nextDouble());
		System.out.println("Please, introduce the stock benefit");
		input = new Scanner(System.in);
		setStockBenefit(input.nextDouble());
		char a = 'y';
		ProductList aux = new ProductList();
		Order auxOrder = new Order();
		Person p = new Person();
		Provider pro = new Provider();
		Person emp = new Person();
		this.setStock(aux.readFromStdio());
		a = 'y';
		while (a == 'y') {
			System.out.println("Please, insert the data for adding an Order to process to the queue. ");
			this.ordersToProcess.enqueue(auxOrder.readFromStdio());
			System.out.println("Would you like to add another one?(y/n) ");
			input = new Scanner(System.in);
			a = input.next().charAt(0);
		}
		a = 'y';
		while (a == 'y') {
			System.out.println("Please, insert the data for adding an order already processed to the list");
			this.ordersProcessed.insert(auxOrder.readFromStdio());
			System.out.println("Would you like to add another one?(y/n) ");
			input = new Scanner(System.in);
			a = input.next().charAt(0);
		}
		a = 'y';
		while (a == 'y') {
			System.out.println("Please, insert the data for storing a customer to the tree.");
			this.storeCustomers.insert((Comparable) this.storeCustomers.getRoot(), p.readFromStdio());
			System.out.println("Would you like to add another one?(y/n) ");
			input = new Scanner(System.in);
			a = input.next().charAt(0);
		}
		a = 'y';
		while (a == 'y') {
			System.out.println("Please, insert the data for storing a Provider to the tree.");
			this.storeProviders.insert((Comparable) this.storeProviders.getRoot(), pro.readFromStdio());
			System.out.println("Would you like to add another one?(y/n) ");
			input = new Scanner(System.in);
			a = input.next().charAt(0);
		}
		a = 'y';
		while (a == 'y') {
			System.out.println("Please, insert the data for storing a customer to the tree.");
			this.storeCustomers.insert((Comparable) this.storeEmployees.getRoot(), emp.readFromStdio());
			System.out.println("Would you like to add another one?(y/n) ");
			input = new Scanner(System.in);
			a = input.next().charAt(0);
		}

		return this;
	}

	public String toString() {
		return "Name: " + this.getName() + "\\| Stock: " + this.getStockBenefit() + "\\|Orders To Process: "
				+ this.ordersToProcess.getTail().getInfo() + "\\|Orders processed: "
				+ this.ordersProcessed.getFirst().getInfo() + "\\|Customers stored: " + this.storeCustomers.toString()
				+ this.storeProviders.toString() + this.storeEmployees.toString();

	}

	public void insert(String context) {
		System.out.println("Inserting " + context);
		char a = 'y';
		switch (context) {
		case "Stock":
			StockableProduct auxSP = new StockableProduct();
			this.stock.getList().set(this.stock.getList().size() - 1, auxSP.readFromStdio());
			break;
		case "Orders To Process":
			
			while (a == 'y') {
				Order orderToProcess = new Order();
				Order inputOrder =orderToProcess.readFromStdio();
				LinkedQueue<Order> auxQ = new LinkedQueue<Order>();
				auxQ.enqueue(inputOrder);
				this.setOrdersToProcess(auxQ);
				System.out.println("Would you like to add another one?(y/n) ");
				input = new Scanner(System.in);
				a = input.next().charAt(0);
			}
			break;
		case "Orders Processed":
			while (a == 'y') {
				Node<Order> orderProcessed = new Node<Order>();
				orderProcessed.setInfo(orderProcessed.getInfo().readFromStdio());
				this.getOrdersToProcess().getTop().setNext(orderProcessed);
				System.out.println("Would you like to add another one?(y/n) ");
				input = new Scanner(System.in);
				a = input.next().charAt(0);
			}
			break;
		case "Customers":
			Person customerToInsert = new Person();
			while (a == 'y') {
				customerToInsert.readFromStdio();
				this.storeCustomers.insert((Comparable) this.storeCustomers.getRoot(),
						customerToInsert);
				System.out.println("Would you like to add another one?(y/n) ");
				input = new Scanner(System.in);
				a = input.next().charAt(0);
			}
			break;
		case "Providers":
			Provider providerToInsert = new Provider();
			while (a == 'y') {
				providerToInsert.readFromStdio();
				this.storeProviders.insert((Comparable) this.storeProviders.getRoot(),
						providerToInsert);
				System.out.println("Would you like to add another one?(y/n) ");
				input = new Scanner(System.in);
				a = input.next().charAt(0);

			}
			break;
		case "Employees":
			Person employee = new Person();
			while (a == 'y') {
				employee.readFromStdio();
				this.storeEmployees.insert((Comparable) this.storeEmployees.getRoot(), employee);
				System.out.println("Would you like to add another one?(y/n) ");
				input = new Scanner(System.in);
				a = input.next().charAt(0);
			}
			break;
		}

	}

	
	public Object remove(String context) {
		System.out.println("Removing " + context);
		Object objToReturn = new Object();
		System.out.println("please, introduce a valid identification");
		int inputID = 0;
		StockableProduct aux = new StockableProduct();
		Scanner input = new Scanner(System.in);
		try {
			inputID = input.nextInt();
		} catch (Exception e) {
			System.err.print("Error. Not a valid identification inserted.");
		}
		// Implementation for the ProductList stock.
		switch (context) {
		case "Stock":
			for (int i = 0; i < this.stock.getList().size(); i++) {
				if (inputID == this.stock.getList().get(i).getProductID()) {
					aux = this.stock.getList().get(this.stock.getList().size() - 1);
					this.stock.getList().set(this.stock.getList().size() - 1, null);
					objToReturn = aux;
				}
			}
			break;
		// Implementation for the LinkedQueue<StockableProduct> ordersToProcess
		case ("Order To Process"):
			Node<Order> auxOrdersToProcess = new Node<Order>();
			auxOrdersToProcess = this.ordersToProcess.getTop();
			Node<Order> auxToReturn = new Node<Order>();
			Node<Order> auxPrior = new Node<Order>();
			while (auxOrdersToProcess.getNext() != null) {
				if (inputID == auxOrdersToProcess.getInfo().getOrderID()) {
					auxToReturn = auxOrdersToProcess;
					auxPrior.getNext().setInfo(null);
					auxPrior.getNext().setNext(null);
					auxPrior.setNext(auxToReturn.getNext());
					objToReturn = auxToReturn;
				} else {
					auxPrior = auxOrdersToProcess;
					auxOrdersToProcess = auxOrdersToProcess.getNext();
				}
			}
			break;
		// Implementation for the LinkedList<StockableProduct> ordersProcessed.
		case ("Orders processed"):
			Node<Order> auxOrdersProcessed = new Node<Order>();
			auxOrdersToProcess = this.ordersToProcess.getTop();
			Node<Order> auxReturn = new Node<Order>();
			Node<Order> auxPriorTo = new Node<Order>();
			while (auxOrdersProcessed.getNext() != null) {
				if (inputID == auxOrdersProcessed.getInfo().getOrderID()) {
					auxReturn = auxOrdersProcessed;
					auxPriorTo.getNext().setInfo(null);
					auxPriorTo.getNext().setNext(null);
					auxPriorTo.setNext(auxReturn.getNext());
					objToReturn = auxReturn;
				} else {
					auxPriorTo = auxOrdersProcessed;
					auxOrdersProcessed = auxOrdersProcessed.getNext();
				}
			}
			break;
		}

		return objToReturn;

	}

	public Object search(String context) {
		System.out.println("Inserting " + context);
		int auxID = 0;
		Node<Order> auxOrder = new Node<Order>();
		switch (context) {
		case "Stock":
			System.out.println("Please, insert the ID number.");
			input = new Scanner(System.in);
			auxID = input.nextInt();
			for (int i = 0; i < this.stock.getList().size() - 1; i++) {
				if (this.stock.getList().get(i).getProductID() == auxID) {
					return this.stock.getList().get(i);
				}
			}

			break;
		case "Orders To Process":
			auxOrder = this.ordersToProcess.getTop();
			System.out.println("Please, insert the ID number.");
			input = new Scanner(System.in);
			auxID = input.nextInt();
			while (auxOrder != null) {
				if (auxID == auxOrder.getInfo().getOrderID()) {
					return auxOrder;
				} else {
					auxOrder = auxOrder.getNext();
				}

			}
			break;
		case "OrdersProcessed":
			System.out.println("Please, insert the ID number.");
			input = new Scanner(System.in);
			auxID = input.nextInt();
			auxOrder = this.ordersProcessed.getFirst();
			while (auxOrder != null) {
				if (auxID == auxOrder.getInfo().getOrderID()) {
					return auxOrder;
				} else {
					auxOrder = auxOrder.getNext();
				}
			}

			break;
		case "Customers":
			System.out.println("Please, insert the ID number.");
			input = new Scanner(System.in);
			auxID = input.nextInt();
			this.storeCustomers.search(auxID);
			break;
		case "Employees":
			System.out.println("Please, insert the ID number.");
			input = new Scanner(System.in);
			auxID = input.nextInt();
			this.storeEmployees.search(auxID);
			break;
		case "Providers":
			System.out.println("Please, insert the ID number.");
			input = new Scanner(System.in);
			auxID = input.nextInt();
			this.storeProviders.search(auxID);
			break;
		}
		return null;

	}

	public void modify(String context) {
		if (context.equals("Employees") || context.equals("Customers") || context.equals("Providers")) {
			System.err.println(context + ": modyfing [Customers/Providers/Employees] from Store is not allowed");
		} else {
			int auxID = 0;
			System.out.println("Please, insert an ID number. ");
			input = new Scanner(System.in);
			auxID = input.nextInt();
			int counter = 0;
			int numberOfNode = 0;
			System.out.println("Modyfing " + context);
			switch (context) {
			case "Stock":
				this.stock.search(auxID).readFromStdio();
				break;
			case "Orders To Process":
				Node<Order> auxOrdersToProcess = new Node<Order>();
				auxOrdersToProcess = this.ordersToProcess.getTop();
				Node<Order> auxCounter = new Node<Order>();
				auxOrdersToProcess = this.ordersToProcess.getTop();
				LinkedQueue<Order> auxOrderQueue = new LinkedQueue<Order>();
				/* First, we look for the desired node and get the its necessary data */
				while (auxOrdersToProcess != null) {
					if (auxOrdersToProcess.getInfo().getOrderID() == auxID) {
						auxOrdersToProcess.getInfo().readFromStdio();
						auxOrdersToProcess.setInfo(auxOrdersToProcess.getInfo());
						numberOfNode++;
						break;
					} else {
						auxOrdersToProcess = auxOrdersToProcess.getNext();
						numberOfNode++;
					}
				}
				auxCounter = this.ordersToProcess.getTop();
				/*
				 * Second, we copy the queue to an auxiliary queue, except for the node we
				 * modified; instead of it, we'll copy to the list auxOrdersToProcess (the
				 * modified node).
				 */
				while (auxCounter.getNext() != null) {
					if (numberOfNode == counter) {
						auxOrderQueue.enqueue(auxOrdersToProcess.getInfo());
					} else {
						auxOrderQueue.enqueue(auxCounter.getInfo());
					}

				}
				/* Finally, we set our auxiliary queue as our ordersToProcess queue. */
				this.setOrdersToProcess(auxOrderQueue);
				break;
			case "Orders Processed":
				for (int i = 0; i < this.ordersProcessed.size; i++) {
					if (auxID == this.ordersProcessed.get(i).getOrderID()) {
						this.ordersProcessed.get(i).readFromStdio();
					}
				}
				break;
			}
		}
	}

	public void processOrders() throws Exception {
		Node<Order> auxQ = this.ordersToProcess.getTop();
		/*
		 * First we check if we have to throw any exception in order to not operate with
		 * the stock list if there's anything wrong.
		 */
		while (auxQ != null) {
			for (int i = 0; i < this.stock.getList().size(); i++) {
				for (int j = 0; j < auxQ.getInfo().getList().size(); j++) {
					if (this.stock.getList().get(i).getProductID() == auxQ.getInfo().getList().get(j).getProductID()) {
						if (this.storeEmployees.search(auxQ.getInfo().getEmployee()).equals(null)) {
							System.err.println("Error. Not existing Employee. ");
							throw new Exception();
						}
						if (this.storeProviders.search(auxQ.getInfo().getList().get(j).getProductProvider())
								.equals(null)) {
							System.err.println("Error. Not existing Provider.");
							throw new Exception();
						}
					}
					auxQ = auxQ.getNext();
				}
			}
		}
		auxQ = this.ordersToProcess.getTop();
		/*
		 * Secondly, we go back to the top of the orders queue and start to get the
		 * orders done.
		 */
		while (auxQ != null) {
			for (int i = 0; i < this.stock.getList().size(); i++) {
				for (int j = 0; j < auxQ.getInfo().getList().size(); j++) {
					if (this.stock.getList().get(i).getProductID() == auxQ.getInfo().getList().get(j).getProductID()) {
						try {
							this.stock.getList().get(i).setNumUnits(this.stock.getList().get(i).getNumUnits()
									- auxQ.getInfo().getList().get(j).getNumUnits());
						} catch (Exception e) {
							System.err.println("Not allowed operation, The number of units must be positive or zero.");
						}
						if (this.stock.getList().get(i).getNumUnits() == 0) {
							this.stock.getList().remove(i);
						}
						if (this.storeCustomers.search(auxQ.getInfo().getClient()).equals(null)) {
							this.storeCustomers.insert((Comparable) this.storeCustomers.getRoot(),
									auxQ.getInfo().getClient());
						}
					}

					this.calculateStockCost();
					this.calculateStockBenefit();
					this.ordersToProcess.dequeue();
					this.ordersProcessed.insert(auxQ.getInfo(), this.ordersToProcess.size() - 1);
				}
			}
		}

	}

	public static ArrayList<Order> readOrdersFromDirectory(String directory) {
		ArrayList<Order> result = new ArrayList<Order>();
		File file = new File(directory);
		File[] fileList = null;
		/*if (file.isDirectory()) {
			fileList = file.listFiles();
			for (int i = 0; i < file.length(); i++) {
				Order aux = Order.readFromFile(fileList[i].getName());
				result.add(aux);
			}
		} else {
			System.err.print("Not valid directory");
		}*/
		return result;
	}

}