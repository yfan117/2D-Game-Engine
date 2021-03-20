package Diablo;

import java.net.*;
import java.io.*;

/**
 * This example illustrates the basic method calls for connectionless
 * datagram socket.
 * @author M. L. Liu
 */
public class Receiver {

// An application which receives a message using connectionless
// datagram socket.
// A command line argument is expected, in order: 
//    <port number of the receiver's socket>
// Note: the same port number should be specified in the
// command-line arguments for the sender.

	DatagramSocket mySocket;
	DatagramPacket datagram;
	byte[ ] buffer;
	Game game;
   public Receiver(Game game, int portNumber) throws SocketException
   {
	   	int port = portNumber;
       // final int MAX_LEN = 100; 
        mySocket = new DatagramSocket(port);  
               // instantiates a datagram socket for receiving the data
        int MAX_LEN = 1000; 
        String string = "test";
 	    buffer = new byte[MAX_LEN];   
        //buffer = new byte[string.getBytes().length];
        datagram = new DatagramPacket(buffer, MAX_LEN);
        this.game = game;
       
            //mySocket.close( );
          // end try
	
       // end else
   } // end main
   
   public void receiving() throws IOException
   {
	   
	   mySocket.receive(datagram);
	   String message = new String(buffer);

	   message = message.trim();
	   
	   game.getEntityList().get(1).clickedX = Integer.parseInt(message);
	   //System.out.println("!!!!!!!!!!!!!!!!!!!  cliked at : " +game.getEntityList().get(2).clickedX);
       
       mySocket.receive(datagram);
       message = new String(buffer);
       //System.out.println(Integer.parseInt(message));
       message = message.trim();
       game.getEntityList().get(1).clickedY = Integer.parseInt(message);

   }
} // end class
