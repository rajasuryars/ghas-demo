
_______________________________________________________

I have used a windows machine to run my project on two virtual machines.
______________________________________________________

In the first machine(say 01) do the following commands.

#mkdir Server

#cd Server/

#touch Server.java

#nano Server.java

After typing these commands you will get a text editor in the terminal. Copy the contents of Server.java then paste it into the editor and press Ctrl + O, then enter and press Ctrl + X.

#touch Makefile

#nano Makefile

After typing these commands you will get a text editor in the terminal. Copy the contents of Makefile_Server then paste it into the editor and press Ctrl + O, then enter and press Ctrl + X.

#make

#make run

After this, the output will be

java server
Listening to port: 5000
_______________________________________________________

In the second machine(say 02) do the following commands.

#mkdir Client

#cd Client/

#mkdir D1

#cd D1/

#touch F1

#touch F2

#cd ..

#touch Client.java

#nano Client.java

After typing these commands you will get a text editor in the terminal. Copy the contents of Client.java then paste it into the editor and press Ctrl + O, then enter and press Ctrl + X.

#touch Makefile

#nano Makefile

After typing these commands you will get a text editor in the terminal. Copy the contents of Makefile_Client then paste it into the editor and press Ctrl + O, then enter and press Ctrl + X.

#make

#make run

After this, you will get a prompt to Enter message. Type anything there like "Copy the files". The code will begin to send the files to the server. After sending the files it will prompt you again, type "End of Session" (you can type anything here I've given an example) to inform the server that the communication was done successfully.

_________________________________________________________
Proof of Execution:

You can check in the Server (machine-01) by using the command,

#cd D1copy/

#ls

You will see "D1copy" directory which will contain the files F1 and F2 sent by the Client.

_______________________________________________________
Output of machine-01:

java Server
Listening to port: 5000
Socket[addr=/10.176.69.33,port=38482,localport=5000] connected
Message from client: Copy the files
Message to client: D1copy folder created successfully!
Directory D1copy created successfully!
File F1 received successfully!
File F2 received successfully!
Message from client: End of Session

Output of machine-02:

java Client
Enter message: Copy the files
Message from server: D1copy folder created successfully!
Sending file F1 to server...
File F1 sent successfully!
Sending file F2 to server...
File F2 sent successfully!
Enter message: End of Session









