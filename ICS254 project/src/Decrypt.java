
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Decrypt {
	public static void main(String args[]) throws FileNotFoundException {
		FileInputStream input = new FileInputStream("C:\\Users\\yaaro\\OneDrive\\Desktop\\encrypted.dec");
		Scanner kb = new Scanner(input);
		FileOutputStream output = new FileOutputStream("C:\\Users\\yaaro\\OneDrive\\Desktop\\output1.txt");
		PrintWriter pr = new PrintWriter(output);
		Scanner kb1 = new Scanner(System.in);

		int x = 0;
		String Dkey="";
		ArrayList<Character> Lan = Encrypt.getarr();// Array list contains All 67 chars
		String EEmassage ="";
		String[] Emassage= new String[6];
		while (kb.hasNext()) {
			EEmassage=EEmassage+kb.nextLine();
		}
		for(int i=0;i<EEmassage.length();i++){
			for(int k=0;k< Emassage.length;k++){
				Emassage[k]=Emassage[k]+EEmassage.charAt(i);
			}
		}
		for(int i=0;i< Emassage.length;i++) {
			for(int k=0;k< Lan.size();k++) {
				Emassage[i]=cipher(Emassage[i],Lan.get(k));
				if((int)(Frequency(Emassage[i],'E','e')/Emassage[i].length()*100)>11&&(int)(Frequency(Emassage[i],'A','a')/Emassage[i].length()*100)>7&&(int)(Frequency(Emassage[i],'E','e')/Emassage[i].length()*100)<14&&(int)(Frequency(Emassage[i],'A','a')/Emassage[i].length()*100)<9){
					Dkey=Dkey+ Lan.get(k);
					System.out.println(Dkey);
					break;
				}
			}
		}
		pr.println(Dkey);
		for (int k=0;k<Emassage[0].length();k++) {
			for (int i = 0; i < Emassage.length; i++) {
				pr.print(Emassage[i].charAt(k));
			}
		}
		pr.close();

	}
	static String cipher(String msg, Character shift){
		ArrayList<Character> Lan = Encrypt.getarr();
		String s = "";
		int len = msg.length();
		for(int x = 0; x < len; x++){
			char c = Lan.get((Lan.indexOf(msg.charAt(x)) - Lan.indexOf(shift)+67)%67);
			s+=c;
		}
		return s;
	}
	static int Frequency(String str,char ch,char ch1) {
			int frequency = 0;

			for(int i = 0; i < str.length(); i++) {
				if(ch == str.charAt(i)||ch1==str.charAt(i)) {
					++frequency;
				}
			}
			return frequency;

	}
}