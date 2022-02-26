
import java.io.*;
import java.util.*;

public class Encrypt {

	public static void main(String[] args) throws FileNotFoundException {
		File fl = new File("C:\\Users\\yaaro\\OneDrive\\Desktop\\sampleFile.txt");
		Scanner k = new Scanner(System.in);// take the key input
		Scanner sc = new Scanner(fl);
		PrintWriter pw = new PrintWriter("C:\\Users\\yaaro\\OneDrive\\Desktop\\sampleFile2.dec");
		ArrayList<Character> ch = getarr();// the array for all chars
		String key = sc.nextLine();
		ArrayList<Character> keyarr = keyarr(key);// key array list
		System.out.println("key:" + key);
		System.out.println(keyarr.toString());
		String massage;
		int counter = 0;// to orgenaize the massage
		Queue<Integer> q = new Queue<Integer>();// to save the new massage in
		while (sc.hasNext()) {// creating the new massage
			massage = sc.nextLine();
			for (int i = 0; i < massage.length(); i++) {
				if (counter < keyarr.size()) {
					q.enqueue(((search(ch, massage.charAt(i)) + search(ch, keyarr.get(counter))) % 67)); // calculate  the new letters locations
					counter++;
					while (!(q.isEmpty())) {// saving the massage in the file
						pw.print(ch.get(q.dequeue()));
					}

				} else {
					counter = 0;
					i--;
				}
			}
			pw.println();

		}
	

		pw.close();
	}

	public static ArrayList<Character> getarr() {// set the chars i an arraylist [A,B,.....,a,b,....,1,2....,, , .,....]
		ArrayList<Character> ch = new ArrayList<Character>();
		int org = 65;
		for (int i = 0; i < 62; i++) {
			if (i < 26) {
				ch.add((char) org);
				if (i == 25) {
					org = 97;
				} else {
					org++;
				}
			} else if (i >= 26 && i < 52) {
				ch.add((char) org);
				if (i == 51) {
					org = 48;
				} else {
					org++;
				}
			} else {
				ch.add((char) org);
				org++;
			}
		}
		ch.add(',');
		ch.add('.');
		ch.add('?');
		ch.add('!');
		ch.add(' ');
		return ch;

	}

	public static ArrayList<Character> keyarr(String key) {// put the key in an arraylist to be easier to deal with
		ArrayList<Character> arr = new ArrayList<Character>();
		for (int i = 0; i < key.length(); i++) {
			arr.add(key.charAt(i));
		}
		return arr;

	}

	public static int search(ArrayList<Character> arr, char ch) {// to find the location of the needed letter as an
																	// integer
		for (int i = 0; i < arr.size(); i++) {
			if (arr.get(i).equals(ch)) {
				return i;
			}
		}
		return 0;

	}

}
