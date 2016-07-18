package kr.ac.sungkyul.network.test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;

public class TCPClient {
	private static final String SERVER_IP= "220.67.115.235";
	private static final int SERVER_PORT = 1000;

	public static void main(String[] args) {
		Socket socket = null;
		try {
			
		// 1. 소켓 생성
		socket  = new Socket();
		
		// 2. 서버 연결
		InetSocketAddress serverSocketAddress
			= new InetSocketAddress(SERVER_IP, SERVER_PORT);
		socket.connect(serverSocketAddress);
		
		// 3. IOStream 받아오기
		InputStream is = socket.getInputStream();
		OutputStream os = socket.getOutputStream();
		
		String data = "hello world\n"; // 보낼땐 바이트로 보내야지
		os.write(data.getBytes("UTF-8"));
		
		//5. 데이터 읽기
		byte[] buffer = new byte[256];
		int readBytes = is.read(buffer);
		if(readBytes <= -1) { // 서버가 연결을 끊음
			System.out.println("[client] closed by server");
			return;
		}
		
		data = new String(buffer, 0, readBytes, "UTF-8"); // 0부터 길이까지 스트링으로 바꾸기
		System.out.println("[client] received :"+data);
		
		}catch (SocketException e){
			System.out.println("비정상적으로 서버로 부터 연결이 끊어졌다.");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try{
			if(socket != null && socket.isClosed() == false ){
				socket.close();
			}
			}catch(IOException e){
				e.printStackTrace();
			}
		}

	}

}
