package client_test;

import java.io.BufferedReader;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class Client_ extends Thread{
	
	private String ip;
	private int port;
	private InetAddress inet;
	private Socket socket;
	public Client_(String ip, int port) {
		this.ip = ip;
		this.port = port;
		try {
			this.inet = InetAddress.getByName(ip);
		} catch (UnknownHostException e) {
			System.err.println("IP정보를 불러오지못했습니다.");
			e.printStackTrace();
		}
		try {
			socket = new Socket(ip, port);
		} catch (IOException e) {
			System.err.println("연결중 에러발생");
			e.printStackTrace();
		}
	}
	/*
	 * 수신부
	 * @see java.lang.Thread#run()
	 */
	public void run() {
		
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			DataInput dt =ImageIO.createImageInputStream(socket.getInputStream());
			while(true) {
				String line = in.readLine();
				System.out.println(line);
			}
		}catch(Exception e) {
			System.err.println("정보를 불러오지못했습니다.");
			e.printStackTrace();
		}
	}
	public void send() {
		
		try {
			PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));

			while(true) {
				String input = JOptionPane.showInputDialog("메세지 입력");
				if(input!=null) {
					out.println(input);
					out.flush();
					System.out.println(input);
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
}
