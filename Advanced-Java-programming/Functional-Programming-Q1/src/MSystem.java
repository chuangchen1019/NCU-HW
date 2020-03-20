import java.util.Scanner;
import java.util.function.Predicate;
import java.util.function.Function;
import java.util.stream.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.ArrayList;

public class MSystem {
	
	private String choice;
	private int flag;
	private Scanner input = new Scanner(System.in);
	private Invoice[] invoices = { 	new Invoice(83, "Electric sander", 7, 57.98),new Invoice(24, "Power saw", 18, 99.99), 
							new Invoice(7, "Sledge hammer", 11, 21.50), new Invoice(77, "Hammer", 76, 11.99),
							new Invoice(39, "Lawn mower", 3, 79.50), new Invoice(68, "Screwdriver", 106, 6.99),
							new Invoice(56, "Jig saw", 21, 11.00),new Invoice(3, "Wrench", 34, 7.50),
							new Invoice(45, "Wrench", 13, 7.50),new Invoice(22, "Hammer", 47, 11.99)};

	private List<Invoice> list = Arrays.asList(invoices);
	private int min,max;
	
	public MSystem() {

		do{
			System.out.print("Welcom to invoices management system.\nFunctions: Report/Select\nChoice(-1 to exit):");
			choice = input.next();
			
			//read the options
			if(choice.compareTo("-1") == 0){
				flag = -1;
				System.exit(0);	
			}
			else if(choice.compareTo("report") == 0 || choice.compareTo("Report") == 0){
				flag = 1;
			}
			else if(choice.compareTo("select") == 0 || choice.compareTo("Select") == 0){
				flag = 2;
			}
			else{
				System.out.println("Wrong input/Enter again");
			}
			

			//Report
			if(flag == 1){
	
				System.out.println();
				System.out.println("Invoices group by description:");
				
				//sort the list of data
				list.sort(Comparator.comparingDouble(Invoice::getBalance));
				
				//print data
				list.stream()
				.map(e -> String.format("Description: %-17s Invoice amount: %10.2f",e.getPartDescription(),e.getBalance()))
				.forEach(System.out::println);
				
				flag = 0;
	
			}
			//Select
			else if(flag == 2) {
				
				//read input
				System.out.print("Input the range to show (min,max):");
				String range = input.next();
				String[] token = range.split(",");
				
				//parse string to integer
				min = Integer.parseInt(token[0]);
				max = Integer.parseInt(token[1]);
				
				System.out.printf("Invoices mapped to description and invoice amount for invoices in the range %d - %d \n",min,max);
				System.out.println();
			
				//sort the list
				list.sort(Comparator.comparingDouble(Invoice::getBalance));
				
				//use predicate to get the range
				Predicate<Invoice> findRange = e -> (e.getBalance() <= max && e.getBalance() >= min);
				
				//output the stream
				list.stream()
					.filter(findRange)
					.map(e -> String.format("Description: %-17s Invoice amount: %10.2f",e.getPartDescription(),e.getBalance()))
					.forEach(System.out::println);		
			
				flag = 0;
			}
			
			System.out.println();
			
		}while(flag != -1);
	}

}
