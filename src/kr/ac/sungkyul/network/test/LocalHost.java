package kr.ac.sungkyul.network.test;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class LocalHost {

	public static void main(String[] args) {
		try {
			InetAddress inetAddress = InetAddress.getLocalHost(); // 내로컬호스트
			String hostname = inetAddress.getHostName(); // 호스트이름
			String hostAddress = inetAddress.getHostAddress(); // ip 
			byte[] addresses = inetAddress.getAddress(); // 한바이트씩 나오게
			
			
			System.out.println("Hostname:" + hostname);
			System.out.println("Host Address:" + hostAddress);
			
			for(int i=0; i<addresses.length; i++){
				System.out.print(addresses[i] & 0x000000ff); // 마이너스부호가 나오니까 캐스팅해주기
				if(i<addresses.length - 1){
					System.out.print(".");
				}
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

}
