import java.util.Scanner;
import java.io.*;
import java.time.LocalDate;

public class Journal{
	
	
	public static void writeJournal(String input) throws FileNotFoundException{
		LocalDate today = LocalDate.now();
		String dayofweek = getDay(today.getDayOfWeek().getValue());
		String month = getMonth(today.getMonth().getValue());
		String filename =  today.getDayOfMonth()+ sliceMonth(month) + today.getYear() + ".txt";
		File file = new File(filename);
		PrintWriter pw = new PrintWriter(file);
		String dateLine = dayofweek + ", " + today.getDayOfMonth() + " " + month + " " + today.getYear();
		pw.println(binaryOutput(dateLine));
		pw.print(binaryOutput(input));
		pw.close();
	}
	
	public static String getDay(int dNum){
		String day;
		if(dNum == 1){day = "Monday";}
		else if(dNum == 2){day = "Tuesday";}
		else if(dNum == 3){day = "Wednesday";}
		else if(dNum == 4){day = "Thursday";}
		else if(dNum == 5){day = "Friday";}
		else if(dNum == 6){day = "Saturday";}
		else {day = "Sunday";}
		return day;
	}
	
	public static String getMonth(int mNum){
		String month = "";
		switch(mNum){
			case 1: month = "January"; break;
			case 2: month = "February"; break;
			case 3: month = "March"; break;
			case 4: month = "April"; break;
			case 5: month = "May"; break;
			case 6: month = "June"; break;
			case 7: month = "July"; break;
			case 8: month = "August"; break;
			case 9: month = "Septemeber"; break;
			case 10: month = "October"; break;
			case 11: month = "November"; break;
			case 12: month = "December"; break;
        }
		return month;
	}
	
	public static String sliceMonth(String month){
		if(month.length() > 5){
			month = month.substring(0,3);
		}
		return month;
	}	

	public static void readJournal(String filename) throws FileNotFoundException{
		File file = new File(filename);
		Scanner sc = new Scanner(file);
		while(sc.hasNext()){
			System.out.println(readableOutput(sc.nextLine()));
		}
	    sc.close();
	}		
		
	public static String integerToBinary(int ascii){
		String rev = "";
		while(ascii > 0){
			rev+= ascii%2;
			ascii/= 2;
		}
		while(rev.length() < 9){
			rev+= '0';
		}
		String binary = "";
		for(int j=rev.length()-1; j>=0; j--){
			binary+= rev.charAt(j);
		}	
		return binary;
	}
	
	public static int binaryToInteger(String binary){
		long bin = Long.parseLong(binary);
		int i=0;
		int decimal = 0;
		while(bin > 0){
			decimal+= (bin%10)*(Math.pow(2,i));
			i++;
			bin/=10;
		}
		return decimal;
	}
	
	public static String binaryOutput(String input){
		int i=0;
		String output = "";
		while(i < input.length()){
			output+= integerToBinary((int)input.charAt(i));
			i++;
		}
		return output;
	}
	
	public static String displayTxtList(){
		String path = "C:\\Users\\adity\\OneDrive\\Desktop\\JournalProg";
		File folder = new File(path);
		File[] files = folder.listFiles();
		String list = "";
		for(File file: files){
			if(file.getName().endsWith(".txt")){
				list+= file.getName() + "\n";
			}
		}
		return list;
	}
	
	public static String readableOutput(String input){
		String binary;
		String output = "";
		int i=0;
		while(i < input.length()){
			binary = input.substring(i,i+9);
			output+= (char)binaryToInteger(binary);
			i+=9;
		}
		return output;
	}
	
	public static void printn(char ch, int n){
		for(int i=0; i<n; i++){
			System.out.print(ch);
		}
	}
		
		
	public static void main(String[] args){
		/**
		System.out.print("Enter string: ");
		String input = sc.nextLine();
		String binaryOut = binaryOutput(input);
		System.out.println(binaryOut);
		String readableOut = readableOutput(binaryOut);
		System.out.println(readableOut);
		**/
		Scanner sc = new Scanner(System.in);
		Scanner sc2 = new Scanner(System.in);
		int opt = 0;
		
			do{
				try{
					System.out.println("\n1.Read Journal");
					System.out.println("2.Write Journal");
					System.out.println("3.Quit");
					System.out.print("\nSelect an option: ");
					opt = sc.nextInt();
					if(opt == 1){
						System.out.println("Displaying all journal entries. Select option to display: ");
						String[] list = displayTxtList().split("\n");
						for(int i=0; i<list.length; i++){
							System.out.println(i+1 + "." + list[i]);
						}
						int opt2 = sc.nextInt();
						System.out.println();
						printn('_',172);
						readJournal(list[opt2-1]);
						printn('_',172);
					}
					else if (opt == 2){
						System.out.println("Write journal content: ");
						String input = sc2.nextLine();
						writeJournal(input);
					}	
					else if (opt == 3){
						System.exit(0);
					}
					else{
						System.out.println("Invalid option!");
					}
				}
				catch(FileNotFoundException e){System.out.println("Journal with specified date not found");}
			}while(opt!=3);
	}
}
	