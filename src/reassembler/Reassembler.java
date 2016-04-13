package reassembler;

import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;

public class Reassembler {
	
	public static void main(String[] args) {
		String[][] data = new String[20][20];
		try {
			File file = new File(args[0]);
			FileInputStream fis = new FileInputStream(file);
			Scanner scanner = new Scanner(fis);
			int x, y;
			String temp;
			// read the file and put them into data array
			while (scanner.hasNext()) {
				String[] string = scanner.next().replaceAll("[^0-9]", " ").split(" ");
				x = Integer.valueOf(string[0]);
				y = Integer.valueOf(string[1]);
				temp = scanner.next();
				data[x][y] = temp;
			}
			scanner.close();
			// just print them
			for (int i = 1; i <= 15; i++) {
				for (int j = 1; j <= 15; j++) {
					if (j == 1) {
						if (data[i][j] == null) System.out.print("___");
						else System.out.print(data[i][j]);
					} else {
						if (data[i][j] == null) System.out.print(" ___");
						else System.out.print(" " + data[i][j]);
					}
				}
				System.out.println();
			}
		} catch (Exception e) {
			System.out.println("usage: java -jar Reassembler.jar output.txt");
			return;
		}
	}
	
}
