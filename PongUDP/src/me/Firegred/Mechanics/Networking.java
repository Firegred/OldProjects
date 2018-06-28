package me.Firegred.Mechanics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Networking {

	Game g;
	InetAddress IPAddress;
	byte[] sendData;
	byte[] receiveData;
	boolean host;
	DatagramPacket sendPacket;
	DatagramSocket clientSocket; 
	boolean start;
	public Networking(Game g) throws Exception{
		this.g=g;
		IPAddress = InetAddress.getByName("192.168.1.255");
		sendData = new byte[1024];
	    receiveData = new byte[1024];
	    clientSocket = new DatagramSocket();
	    host=true;
	    start=false;
	    checkIfHost();
	    runServer();
	}
	public boolean isHost() {
		return host;
	}
	public void sendReset() {
		String reset = "RESET";
		sendData = new byte[1024];
		sendData = reset.getBytes();
		sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
	    try {
			clientSocket.send(sendPacket);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void sendScore(String P) {
		P = "SCORE " + P;
		sendData = new byte[1024];
		sendData = P.getBytes();
		sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
	    try {
			clientSocket.send(sendPacket);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void sendBall(String host, double ymult) {
		
		String P = "";
		if(this.host) {
			P = "BALL " + host + " " + ymult + " fromHOst";
		}
		else {
			P = "BALL " + host + " " + ymult + " fromNOTHOst";
		}
		sendData = new byte[1024];
		sendData = P.getBytes();
		sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
	    try {
			clientSocket.send(sendPacket);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void runServer() {
		Thread t = new Thread() {
			@Override
			public void run() {
				 DatagramSocket serverSocket = null;
				try {
					serverSocket = new DatagramSocket(9876);
				} catch (SocketException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 	byte[] receiveData = new byte[1024];
		            byte[] sendData = new byte[1024];
		            while(true)
		            {
		            	receiveData = new byte[1024];
		            	sendData = new byte[1024];
		            	DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
		                  try {
							serverSocket.receive(receivePacket);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		                  String sentence = new String( receivePacket.getData()).trim();
		                  String[] variables = sentence.split(" ");
		                  System.out.println("RECEIVED: " + sentence);
		                  if(variables[0].startsWith("SCORE")) {
		                		  if(variables[1].equals("P1")) {
		                			  g.getScore().ScoreP1();
		                		  }
		                		  if(variables[1].equals("P2")) {
		                			  g.getScore().ScoreP2();
		                		  }
		                  }
		                  else if(variables[0].startsWith("RESET")) {
		                	  if(host) {
		                		  g.getBall().resetBall();
		                	  }
		                  }
		                  else if(variables[0].startsWith("BALL")) {
		                	  if(host && variables[1].equals("forhost")) {
		                	  double ymult = Double.parseDouble(variables[2]);
		                	  g.getBall().setBallStuff(0-g.getBall().width,ymult);
		                	  }
		                	  else if(!host && variables[1].equals("notforhost")) {
		                		  double ymult = Double.parseDouble(variables[2]);
			                	  g.getBall().setBallStuff(g.getPane().getGameFrame().getWidth(),ymult);
			                	  System.out.println("I am not host. should recieve");
		                	  }
		                  }
		                  else if(variables[0].startsWith("START") && host) {
		                	  InetAddress IPAddress = receivePacket.getAddress();
		                      int port = receivePacket.getPort();
		                      String ok = "OK DAD";
		                      sendData = ok.getBytes();
		                      DatagramPacket sendPacket =
		                      new DatagramPacket(sendData, sendData.length, IPAddress, port);
		                	  try {
								serverSocket.send(sendPacket);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
		                	  start=true;
		                  }
		                 
		         }
			}
		};
		t.start();
	}
	public void checkIfHost() {
		Thread t = new Thread() {
			@Override
			public void run() {
				String ping = "START";
				sendData = ping.getBytes();
				sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
			    try {
					clientSocket.send(sendPacket);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
			    try {
					clientSocket.receive(receivePacket);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    String pong = new String(receivePacket.getData()).trim();
			    if(pong.startsWith("OK DAD")) {
			    	System.out.println("I am not host");
			    	host=false;
			    	start=true;
			    }
			    
			}
		};
		t.start();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		t.stop();
		
	}
	private void stuff() throws Exception {
		BufferedReader inFromUser =
		         new BufferedReader(new InputStreamReader(System.in));
		      DatagramSocket clientSocket = new DatagramSocket();
		      InetAddress IPAddress = InetAddress.getByName("192.168.1.255");
		      byte[] sendData = new byte[1024];
		      byte[] receiveData = new byte[1024];
		      String sentence = inFromUser.readLine();
		      sendData = sentence.getBytes();
		      DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
		      clientSocket.send(sendPacket);
		      DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
		      clientSocket.receive(receivePacket);
		      String modifiedSentence = new String(receivePacket.getData());
		      System.out.println("FROM SERVER:" + modifiedSentence);
		      clientSocket.close();
	}
}
