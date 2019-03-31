Server is up and running

Client starts up 
	- connects to Server		- Server generates a client ID
								- Server increases client counter. 
								- Server sends client counter to client
	- client registers and stores unique ID
								- Server creates a ConcurrentHashMap - UserID / Path of Current Location

Client sends a command
	- client sends a command, awaits a response

