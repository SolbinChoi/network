package kr.ac.sungkyul.network.echo;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class EchoServer {
	private final static int SERVER_PORT = 1000;

	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		try {
			// 1. 서버 소켓 생성
			serverSocket = new ServerSocket();

			// 2. 바인딩 (서버의 아이피주소와 포트를!)
			InetAddress inetAddress = InetAddress.getLocalHost(); // 동적으로 세팅하기
			String serverAddress = inetAddress.getHostAddress();
			InetSocketAddress inetSocketAddress = new InetSocketAddress(serverAddress, SERVER_PORT);

			serverSocket.bind(inetSocketAddress); // 바인딩 시키기
			System.out.println("[서버] bind:" + serverAddress + " : " + SERVER_PORT);

			// 3. accept 클라이언트로 부터 연결(요청) 대기
			Socket socket = serverSocket.accept(); // blocking (밑으로 내려가지않고 기다리고
													// 있는 것, 연결되면 다음 라인으로 빠져나옴)

			// 4. 연결성공
			InetSocketAddress remoteAddress = (InetSocketAddress) socket.getRemoteSocketAddress();
			String remoteHostAddress = remoteAddress.getAddress().getHostAddress();
			int remotePort = remoteAddress.getPort();
			System.out.println("[server] 연결 성공 from" + remoteAddress.getAddress().getHostAddress() + " : "
					+ remoteAddress.getPort()); // ip와 port정보
			try {
				// 5. IOStream 받아오기
				InputStream is = socket.getInputStream();
				OutputStream os = socket.getOutputStream();
				
				 while(true){
					 
				
				// 6. 데이터 읽기 -> 7번까지 무한 루프로 돌리고 break;
				byte[] buffer = new byte[256];
				int readbytes = is.read(buffer);
				if (readbytes <= -1) { // -1이면 클라이언트가 소켓열어서 close를 한거다. 명시적으로 끊은
										// 것으로 정상 종료.
					System.out.println("[server] closed by client");
					return; // 프로그램이 돌 수 없는 상황이기 때문에
				}

				// 버퍼안에 있는 데이터 읽어오기
				String data = new String(buffer, 0, readbytes, "utf-8");
				System.out.println("[server] received : " + data);
				
				if("quit".equals(data)){
					break;
				}
				// 7. 데이터 쓰기
				os.write(buffer); // string에서 가져오고 싶으면 data.getBytes("utf-8");
				 }
			} catch (SocketException e) {
				System.out.println("[server] 비정상적으로 클라이언트가 연결을 끊었습니다.");
			} catch (IOException e) { // 새글을 쓰고 읽을 때
				e.printStackTrace();
			} finally {
				// 8. 데이터 통신 소켓 닫기(자원 정리)
				if (socket != null && socket.isClosed() == false) {
					socket.close();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				// 9.서버 소켓 닫기
				if (serverSocket != null && serverSocket.isClosed() == false) {
					serverSocket.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
