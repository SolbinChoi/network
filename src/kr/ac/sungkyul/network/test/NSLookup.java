package kr.ac.sungkyul.network.test;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class NSLookup {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		while(true){
			
		try {
			
			
			System.out.println(">>");
			String host = scanner.nextLine();
			if("quit".equals(host)){
				break;
			}
			InetAddress[] inetAddresses = InetAddress.getAllByName(host); // 배열안에는 네이버의 정보가 담겨
			for(InetAddress inetAddress : inetAddresses){
				System.out.println(inetAddress.getHostAddress());
				}
			
			
		} catch (UnknownHostException e) {
			System.out.println("unkown host");
			//			e.printStackTrace();
			}
		}
		scanner.close();
	}

}
