import java.util.Scanner;

class T2 {
	public static void main(String[] args) {
		// helper messages and get user's input
		System.out.println("Josh's Java");
		System.out.println("Enter how many pattern THEN enter how many string");
		System.out.println("pattern: xxyz and String [red red blue green] then true");
		System.out.println("pattern: xxyz and String [red blue blue red] then false");
		System.out.println("Enter Pattern with no-space (eg. XYXYZZ): ");
		var input = new Scanner(System.in);
		String ptn = input.nextLine().replaceAll("\\s", "");
		System.out.println("Enter String with space: ");
		String str = input.nextLine().trim();
		input.close();
		System.out.println("Your pattern is: " + ptn);
		System.out.println("Your string is: " + str);

		// operations
		String[] strs = str.split(" ");
		var pattern = Pattern.createAndVerify(ptn, strs);
		System.out.println("String and Pattern are matched ? " + pattern.getIsMatched());
		if (!pattern.getIsMatched())
			return;
		pattern.printAsTree();
	}
}
