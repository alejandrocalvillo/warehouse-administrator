package Project;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	private static StoreManager lastStore = new StoreManager();

	public static void main(String[] args) throws IOException, EmailException {
		Scanner sc = new Scanner(System.in);
		StoreManager storeToCreate = new StoreManager();
		int option;
		String name;
		Order order = new Order();
		do {
			System.out.println("----------MainMenu----------");
			System.out.print("StoreInfo: ");
			System.out.println("Store name: ");
			System.out.println("Stock cost: ");
			System.out.println("Stock benefit: ");
			System.out.println();
			System.out.println();
			showLastStore(lastStore);
			System.out.println("1.-Create Store");
			System.out.println("2.-Manage Stock");
			System.out.println("3.-Manage Orders (To process)");
			System.out.println("4.-Manage Orders (Processed)");
			System.out.println("5.-Manage Clients");
			System.out.println("6.-Manage Providers");
			System.out.println("7.-Manage Employees");
			System.out.println("8.-Print Store Info");
			System.out.println("9.-Testing");
			System.out.println("0.-Exit Application");
			System.out.print("Option> ");
			option = sc.nextInt();
			switch (option) {
			case 1:
				Order auxOrder=new Order();
				ProductList stock=new ProductList();
				Node<Order> tail=new Node<Order>(auxOrder.readFromFile("storeName\\ordersToProcess\\11_505678_605678.txt"),null);
				Node<Order> first=new Node<Order>(auxOrder.readFromFile("storeName\\ordersToProcess\\10_504567_604567.txt"), tail);
				System.out.println("Please insert the name of the Store: ");
				sc = new Scanner(System.in);
				storeToCreate.setName(sc.next());
				System.out.println("Adding all the info of the Folder storeName");
				LinkedList<Order> ordersProcessed = new LinkedList<Order>();
				LinkedQueue<Order> ordersToProcess = new LinkedQueue<Order>(first,tail, 2);
				ordersProcessed.insert(auxOrder.readFromFile("storeName\\ordersProcessed\\01_506789_601234.txt"));
				ordersProcessed.insert(auxOrder.readFromFile("storeName\\ordersProcessed\\02_507890_607890.txt"));
				ordersToProcess.enqueue(auxOrder.readFromFile("storeName\\ordersToProcess\\12_501234_601234.txt"));
				ordersToProcess.enqueue(auxOrder.readFromFile("storeName\\ordersToProcess\\13_502345_602345.txt"));
				ordersToProcess.enqueue(auxOrder.readFromFile("storeName\\ordersToProcess\\14_501234_606789.txt"));
				storeToCreate.setStoreEmployees("storeName\\storeEmployees.txt");
				storeToCreate.setStoreProviders("storeName\\storeProviders.txt");
				storeToCreate.setOrdersProcessed(ordersProcessed);
				storeToCreate.setOrdersToProcess(ordersToProcess);
				storeToCreate.setStock(stock.readFromFile("storeName\\stock.txt"));
				storeToCreate.setStockCost(storeToCreate.calculateStockCost());
				storeToCreate.setStockPrice(storeToCreate.calculateStockPrice());
				storeToCreate.setStockBenefit(storeToCreate.calculateStockBenefit());
				option=10;
				lastStore=storeToCreate;
				break;

			case 2:
				System.out.println("----------Manage Stock Menu----------");
				System.out.println("++1.-Insert Stock");
				System.out.println("++2.-Remove Stock");
				System.out.println("++3.-Modify Stock");
				System.out.println("++4.-Search Stock");
				System.out.println("++0.-Exit Menu");
				System.out.print("Option> ");
				option = sc.nextInt();

				if (option == 0) {
					option = 10;
				} else if (option == 1) {
					lastStore.insert("Stock");
				} else if (option == 2) {
					lastStore.remove("Stock");
				} else if (option == 3) {
					lastStore.modify("Stock");
				} else if (option == 4) {
					lastStore.search("Stock");
				} else if (option == 5) {
					lastStore.getStock().print();
				}
				break;

			case 3:
				System.out.println("----------Manage Orders Menu (to process)----------");
				System.out.println("++1.-Insert Order");
				System.out.println("++2.-Remove Order");
				System.out.println("++3.-Modify Order");
				System.out.println("++4.-Search Order");
				System.out.println("++5.-Print");
				System.out.println("++6.-Manage orders To Process");
				System.out.println("++0.-Exit Menu");
				System.out.print("Option> ");
				option = sc.nextInt();
				if (option == 0) {
					option = 10;
				} else if (option == 1) {
					lastStore.insert("Orders To Process");
				} else if (option == 2) {
					lastStore.remove("Orders To Process");
				} else if (option == 3) {
					lastStore.modify("Orders To Process");
				} else if (option == 4) {
					lastStore.search("Orders To Process");
				} else if (option == 5) {
					lastStore.getOrdersToProcess().getTail().print();
				} else if (option == 6) {
					try {
						lastStore.processOrders();
					} catch (Exception e) {
						System.err.println("Error executing operation.");
					}
				}
				break;

			case 4:
				System.out.println("----------Manage Orders Menu (processed)----------");
				System.out.println("++1.-Insert Order");
				System.out.println("++2.-Remove Order");
				System.out.println("++3.-Modify Order");
				System.out.println("++4.-Search Order");
				System.out.println("++0.-Exit Menu");
				System.out.print("Option> ");
				option = sc.nextInt();
				if (option == 0) {
					option = 10;
				}
				if (option == 1) {

				}
				break;

			case 5:
				System.out.println("----------Manage Clients----------");
				System.out.println("++1.-Insert Client");
				System.out.println("++2.-Remove Client");
				System.out.println("++3.-Modify Client");
				System.out.println("++4.-Search Client");
				System.out.println("++5.-Print");
				System.out.println("++0.-Exit Menu");
				System.out.print("Option> ");
				option = sc.nextInt();
				if (option == 0) {
					option = 10;
				} else if (option == 1) {
					lastStore.insert("Customers");
				} else if (option == 2) {
					lastStore.remove("Customers");
				} else if (option == 3) {
					lastStore.modify("Customers");
				} else if (option == 4) {
					lastStore.search("Customers");
				} else if (option == 5) {
					lastStore.getStoreCustomers().getRoot().print();
				}
				break;

			case 6:
				System.out.println("----------Manage Providers----------");
				System.out.println("++1.-Insert Provider");
				System.out.println("++2.-Remove Provider");
				System.out.println("++3.-Modify Provider");
				System.out.println("++4.-Search Provider");
				System.out.println("++5.-Print");
				System.out.println("++0.-Exit Menu");
				System.out.print("Option> ");
				option = sc.nextInt();
				if (option == 0) {
					option = 10;
				} else if (option == 1) {
					lastStore.insert("Providers");
				} else if (option == 2) {
					lastStore.remove("Providers");
				} else if (option == 3) {
					lastStore.modify("Providers");
				} else if (option == 4) {
					lastStore.search("Providers");
				} else if (option == 5) {
					lastStore.getStoreProviders().getRoot().print();
				}
				break;

			case 7:
				System.out.println("----------Manage Employees----------");
				System.out.println("++1.-Insert Employees");
				System.out.println("++2.-Remove Employees");
				System.out.println("++3.-Modify Employees");
				System.out.println("++4.-Search Employees");
				System.out.println("++5.-Print");
				System.out.println("++0.-Exit Menu");
				System.out.print("Option> ");
				option = sc.nextInt();
				if (option == 0) {
					option = 10;
				} else if (option == 1) {
					lastStore.insert("Employee");
				} else if (option == 2) {
					lastStore.remove("Employee");
				} else if (option == 3) {
					lastStore.modify("Employee");
				} else if (option == 4) {
					lastStore.search("Employee");
				}
				 else if (option == 5) {
						lastStore.getStoreEmployees().getRoot().print();
					}
				break;
			case 8:
				showMainMenu(lastStore);

				break;
			case 9:
				break;
			}
		} while (option != 0);
		sc.close();

	}

	public static void showMainMenu(StoreManager store) {
		System.out.println("STORE BASIC INFORMATION");
		System.out.println("Store info: Store name: " + store.getName() + "\nStock cost: " + store.getStockCost()
				+ "\nStock benefit: " + store.getStockBenefit());
		lastStore = store;
	}

	public static void showLastStore(StoreManager store) {
		lastStore = store;
		System.out.println("--------MainMenu--------");
		System.out.println("Store info: Store name: " + store.getName() + "Stock cost: " + lastStore.getStockCost()
				+ "Stock benefit: " + lastStore.getStockBenefit());
	}
	public StoreManager addFromFiles() throws IOException, EmailException {//this methods adds every file
		StoreManager storeToCreate = new StoreManager();
		Order auxOrder=new Order();
		Node<Order> tail=new Node<Order>(auxOrder.readFromFile("storeName\\ordersToProcess\\11_505678_605678.txt"),null);
		Node<Order> first=new Node<Order>(auxOrder.readFromFile("storeName\\ordersToProcess\\10_504567_604567.txt"), tail);
		System.out.println("Adding all the info of the Folder StoreName");
		LinkedList<Order> ordersProcessed = new LinkedList<Order>();
		LinkedQueue<Order> ordersToProcess = new LinkedQueue<Order>(first,tail, 2);
		ordersProcessed.insert(auxOrder.readFromFile("storeName\\ordersProcessed\\01_506789_601234.txt"));
		ordersProcessed.insert(auxOrder.readFromFile("storeName\\ordersProcessed\\02_507890_607890.txt"));
		ordersToProcess.enqueue(auxOrder.readFromFile("storeName\\ordersToProcess\\12_501234_601234.txt"));
		ordersToProcess.enqueue(auxOrder.readFromFile("storeName\\ordersToProcess\\13_502345_602345.txt"));
		ordersToProcess.enqueue(auxOrder.readFromFile("storeName\\ordersToProcess\\14_501234_606789.txt"));
		storeToCreate.setStoreEmployees("storeName\\storeEmployees.txt");
		storeToCreate.setStoreProviders("storeName\\storeProviders.txt");
		storeToCreate.setOrdersProcessed(ordersProcessed);
		storeToCreate.setOrdersToProcess(ordersToProcess);
		return  storeToCreate;
		
	}
	
	
}