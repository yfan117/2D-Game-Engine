package Diablo;

import java.net.*;
import java.io.*;

/**
 * This example illustrates the basic method calls for connectionless
 * datagram socket.
 * @author M. L. Liu
 */
public class Sender {

// An application which sends a message using connectionless
// datagram socket.
// Three command line arguments are expected, in order: 
//    <domain name or IP address of the receiver>
//    <port number of the receiver's socket>
//    <message, a string, to send>

	 InetAddress receiverHost ;
	 int receiverPort;
	 DatagramSocket	mySocket;
	 Game game;
	 DatagramPacket datagram;
   public Sender(Game game, int portNumber){
     {
         try {
  		       receiverHost = InetAddress.getByName("localhost");
  		       receiverPort = portNumber;
  		       mySocket = new DatagramSocket(); 
  		       this.game = game;
  	
            // instantiates a datagram socket for sending the data
   	      	   
   	  
            //mySocket.close( );
         } // end try
	 catch (Exception ex) {
       ex.printStackTrace( );
	 }
      } // end else
   } // end main
   
   public void sending() throws IOException
   {
	  
	   String message = String.valueOf(game.getEntityList().get(0).x);
       byte[ ] buffer = message.getBytes( );               
       datagram = new DatagramPacket(buffer, buffer.length, 
    		   										receiverHost, receiverPort);
       mySocket.send(datagram);
       
       
       message = String.valueOf(game.getEntityList().get(0).y);
       buffer = message.getBytes( );               
       datagram = new DatagramPacket(buffer, buffer.length, 
    		   										receiverHost, receiverPort);
       mySocket.send(datagram);
   }
} // end class

