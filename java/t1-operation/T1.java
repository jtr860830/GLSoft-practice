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
		// operations
		String[] hexMap = {
				"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F"
		};
		int mask = 0b1111;
		var result = new String();
		for (; n > 0; n >>= 4) {
			result = hexMap[n & mask] + result;
		}
		System.out.println("Hexadecimal number is: " + result);
	}
}
