Background:
This program is a proof-of-concept program that is hosted/downloaded on an internal Apache web server. The downloaded file can then be ran as client software. The inclusion of the Apache server satisfies the component requiring the software to be accessible from a browser (Firefox). The client software was written in Java utilizing the JavaFX library.
__________________________________________________________
Requirements:
OS: Ubuntu Gnome 16.04.2 (64-bit)
Privileges: Root privileges for installation of services
__________________________________________________________
Installation and Executing:
1. Copy gui.jar, index.html and setup.sh to the target system to the same folder (/tmp is OK)
2. cd to that folder and run setup.sh as root user** (this configures javafx, apache, and other setup)
3. Visit localhost:80 in a browser (Firefox) to download the client software
4. Extract gui.jar from gui.tar.gz
5. Run "sudo java -jar gui.jar" with privileges that allow writing to /tmp (use sudo for ease of running in this scenario) (/tmp was chosen for reasons stated in Client Questions section)

**
sudo chmod +x setup.sh
sudo ./setup.sh