package log;

import java.io.DataInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class LogReceiver extends Thread{
	private DataInputStream dataIn;
	private String log = "";
	private boolean isRunning = true;
	
	public LogReceiver() {}
	
	public LogReceiver(DataInputStream dataIn){
		this.dataIn = dataIn;
	}
	
	public void test() {
		try {
			PrintWriter out = new PrintWriter("filename.csv");
			out.println("test22");
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void saveLog(){
		System.out.println("Filename: ");
//		Scanner s = new Scanner(System.in);
//		String filename = s.nextLine();
		try {
			PrintWriter pw = new PrintWriter("test.csv");
			pw.println("Psi, Alpha, Beta\r\n");
			pw.println(log);
			pw.flush();
			pw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//s.close();
	}
	
	private void checkForUpdate(){
		try {
			log += dataIn.readUTF();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void stopRunning(){
		isRunning = false;
	}
	
	public void run(){
		while(isRunning){
			checkForUpdate();
		}
	}
}
