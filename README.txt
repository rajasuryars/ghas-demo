I have attached five files in the elearning. They are,
1. Server.java
2. Makefile_Server
3. Client.java
4. Makefile_Client
5. README

_______________________________________________________

I have used a windows machine to run my project on the dcxy machines.

Using the UTD VPN, I have connected to dc01 and dc02 using putty. Putty is a software used to make SSH connection to the machines (dc01 & dc02).

Install the Putty Software and open two windows of this software. In the first one type netid@dc01.utdallas.edu and in the other one type netid@dc02.utdallas.edu. After that, Click on open to establish connections to those machines.

NOTE: Use only dc01 and dc02 to get the desired output.

______________________________________________________

In the dc01.utdallas.edu do the following commands.

#mkdir Server

#cd Server/

#touch Server.java

#nano Server.java

After typing these commands you will get a text editor in the terminal. Copy the contents of Server.java (Attachment file) then paste it into the editor and press Ctrl + O, then enter and press Ctrl + X.

#touch Makefile

#nano Makefile

After typing these commands you will get a text editor in the terminal. Copy the contents of Makefile_Server (Attachment file) then paste it into the editor and press Ctrl + O, then enter and press Ctrl + X.

#make

#make run

After this, the output will be

java server
Listening to port: 5000
_______________________________________________________

In the dc02.utdallas.edu do the following commands.

#mkdir Client

#cd Client/

#mkdir D1

#cd D1/

#touch F1

#touch F2

#cd ..

#touch Client.java

#nano Client.java

After typing these commands you will get a text editor in the terminal. Copy the contents of Client.java (Attachment file) then paste it into the editor and press Ctrl + O, then enter and press Ctrl + X.

#touch Makefile

#nano Makefile

After typing these commands you will get a text editor in the terminal. Copy the contents of Makefile_Client (Attachment file) then paste it into the editor and press Ctrl + O, then enter and press Ctrl + X.

#make

#make run

After this, you will get a prompt to Enter message. Type anything there like "Copy the files". The code will begin to send the files to the server. After sending the files it will prompt you again, type "End of Session" (you can type anything here I've given an example) to inform the server that the communication was done successfully.

_________________________________________________________
Proof of Execution:

You can check in the Server (dc01.utdallas.edu) by using the command,

#cd D1copy/

#ls

You will see "D1copy" directory which will contain the files F1 and F2 sent by the Client.

_______________________________________________________
Output of dc01.utdallas.edu

java Server
Listening to port: 5000
Socket[addr=/10.176.69.33,port=38482,localport=5000] connected
Message from client: Copy the files
Message to client: D1copy folder created successfully!
Directory D1copy created successfully!
File F1 received successfully!
File F2 received successfully!
Message from client: End of Session

Output of dc02.utdallas.edu

java Client
Enter message: Copy the files
Message from server: D1copy folder created successfully!
Sending file F1 to server...
File F1 sent successfully!
Sending file F2 to server...
File F2 sent successfully!
Enter message: End of Session









