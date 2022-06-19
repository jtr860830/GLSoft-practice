import java.util.Scanner;

public class T1 {
	public static void main(String[] args) {
		// helper messages
		System.out.println("Josh's JAVA test");
		System.out.print("Input a number: ");
		// get input
		var scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		scanner.close();
		// output hex number
		System.out.printf("%s: %x", "Hexadecimal number is", n);
	}
}
