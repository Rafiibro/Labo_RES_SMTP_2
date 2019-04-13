

Your report MUST include the following sections:

### A brief description of the project:
This program create a client application (TCP) in Java to send fake email from a list of victims. It will use the Socket API to communicate with a known SMTP server (address and port needed).

### Prerequisite
To test your fake email safely and without the risks of being blacklisted, we strongly advise yout to set up a fake SMTP server to send your fake emails. For our testing phase we used a simple email server mocker called MockMock. Mockmock is simple and freely available on GitHub and you will be able to test if outgoing emails are sent (without actually sending them) and to see what they look like. It provides a web interface that displays which emails were sent and shows you what the contents of those emails are. You can clone the repo MockMock at this link https://github.com/tweakers/MockMock.

You have two ways to use MockMock in the dkr/MockMock folder:

#### Use the .jar directly

(You can use the .jar you find with this project or download it from github yourself, compile the program with maven )

Use in a bash console the following command from the target directory :

```
java -jar YourMockMockJarNameHere.jar -p 2525 -h 8282

Options:

-p 2525  : you can change the port to be used
-h 8282  : same as before with the http port for the web interface

(without options the default ports  are 25 (for SMTP) and 8282 (the web interface))
```

Once MockMock running, navigating with a browser to **localhost:8282** should you take to the web interface.

#### Build a Docker container

If your are familiar with the Docker Image system, we provide also a DOCKERFILE to build a custom constainer with the default port value. Use the two scripts to build and run the container. Run from a bash console

´´´
./build_image.sh
./run_container.sh

´´´







 // TO DO //


Clear and simple instructions for configuring your tool and running a prank campaign. If you do a good job, an external user should be able to clone your repo, edit a couple of files and send a batch of e-mails in less than 10 minutes.

A description of your implementation: document the key aspects of your code. It is probably a good idea to start with a class diagram. Decide which classes you want to show (focus on the important ones) and describe their responsibilities in text. It is also certainly a good idea to include examples of dialogues between your client and an SMTP server (maybe you also want to include some screenshots here).
