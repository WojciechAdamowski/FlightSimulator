# Flight Simulator

This is a flight simulator. The program allows you to create a crew who will be on board the aircraft, set the language 
of the aircraft, and also check whether the aircraft is ready for flight. Requirements for a checked flight is:
* There must be 2 pilots - no matter what kind
* There must be 3 flight attendants who know the language of the flight
* No more than 200 passengers and no less than 100 may enter the plane

### Prerequisites

What things you need to install the software and how to install them

        JAVA (Ubuntu instalation) - sudo apt-get install oracle-java11-installer-local
        JDK (Ubuntu instaltion) - sudo apt install openjdk-11-jdk 

**How to install**

* 1.Download ZIP repository
* 2.Unzip the files to the directory where you want to store the program 

**Manual install**

* 3.Open terminal on your device
* 4.Go to "src" folder it's in unzip files
* 5.Run these commands(You must be in "src" folder!):

        javac exceptionsForFlight/*.java
        javac flight/enums/*.java
        javac flight/items/*.java
        javac flight/passengers/*.java
        javac flight/employees/*.java
        javac flight/*.java
        javac flightManagament/*.java


* 6.Run command `java flightManagament.FlightManagement`

**Auto installation**

* 3.Open project on you programming environment tool for JAVA(for instance IntelliJ IDEA)
* 4.Run main method which is `FlightManagement.java` 


**Built With**

* [Maven](https://maven.apache.org/) - Dependency Management
* [IntelliJ IDEA](https://www.jetbrains.com/idea/) - Tool