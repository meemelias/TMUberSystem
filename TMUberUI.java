import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.lang.RuntimeException;
// Name: Meem Elias
// StuedentID: 500783098

// Simulation of a Simple Command-line based Uber App 

// This system supports "ride sharing" service and a delivery service

public class TMUberUI
{
  public static void main(String[] args)
  {
    // Create the System Manager - the main system code is in here 

    TMUberSystemManager tmuber = new TMUberSystemManager();
    
    Scanner scanner = new Scanner(System.in);
    System.out.print(">");

    // Process keyboard actions
    while (scanner.hasNextLine())
    {
      String action = scanner.nextLine();

      if (action == null || action.equals("")) 
      {
        System.out.print("\n>");
        continue;
      }
      // Quit the App
      else if (action.equalsIgnoreCase("Q") || action.equalsIgnoreCase("QUIT"))
        return;
      // Print all the registered drivers
      else if (action.equalsIgnoreCase("DRIVERS"))  // List all drivers
      {
        tmuber.listAllDrivers(); 
      }
      







      // Loading userFile
      else if (action.equalsIgnoreCase("loadusers")) {
        System.out.print("User File: ");

        String filename = "";
        if (scanner.hasNextLine()) {
          filename = scanner.nextLine();
          
        }
        ArrayList<User> list = new ArrayList<>(); 
        try {
          list = TMUberRegistered.loadPreregisteredUsers2(filename);
          System.out.println("Users Loaded");
          
        } catch (FileNotFoundException e) {
          System.out.println("Users File: " + filename + " Not Found" );
          continue;
        }
        catch(IOException e){
          return;

        }
        tmuber.setUsers(list);
      }


      // Loading Drivers file
      else if (action.equalsIgnoreCase("loaddrivers")) {
        System.out.print("Driver File: ");
        String filename = "";
        if (scanner.hasNextLine()) {
          filename = scanner.nextLine();
          
        }
        ArrayList<Driver> list = new ArrayList<>(); 
        try {
          list = TMUberRegistered.loadPreregisteredDrivers(filename);
          System.out.println("Drivers Loaded");
          
        } catch (FileNotFoundException e) {
          System.out.println("Drivers File: " + filename + " Not Found");
        }
        catch(IOException e){
          return;

        }
        tmuber.setDrivers(list);
        
      }
      

      else if (action.equalsIgnoreCase("pickup")) {
        String id = "";
        System.out.print("Driver Id: ");
        if (scanner.hasNextLine()) {
          id = scanner.nextLine();
          
        }
        try{
          tmuber.pickup(id);
          

        }
        catch(PickupException e){
          System.out.println(e.getMessage());

        }
        
        
      }

      else if (action.equalsIgnoreCase("dropoff")) {
        String id = "";
        System.out.print("Driver Id: ");
        if (scanner.hasNextLine()) {
          id = scanner.nextLine();
          
        }
        try {
          tmuber.dropoff2(id);
          
          
        } catch (DriverdropOffException e) {
          
          System.out.println(e.getMessage());
        }
        
      }
      else if (action.equalsIgnoreCase("driveto")) {
        String id = "";
        System.out.print("Driver Id: ");
        if (scanner.hasNextLine()) {
          id = scanner.nextLine();
          
        }
        String address ="";
        System.out.print("Address: ");
        if (scanner.hasNextLine()) {
          address = scanner.nextLine();
          
        }
        try {
          tmuber.driveTo(id, address);

          
        } catch (driveToException e) {
          System.out.println(e.getMessage());
        }

        
      }
      






      // Print all the registered users
      else if (action.equalsIgnoreCase("USERS"))  // List all users
      {
        tmuber.listAllUsers(); 
      }
      // Print all current ride requests or delivery requests
      else if (action.equalsIgnoreCase("REQUESTS"))  // List all requests
      {
        tmuber.listAllServiceRequests(); 
      }
      // Register a new driver
      else if (action.equalsIgnoreCase("REGDRIVER")) 
      {
        String name = "";
        System.out.print("Name: ");
        try {
          if (scanner.hasNextLine())
          {
            name = scanner.nextLine();
          }
          
        } catch (DriverException e) {
          System.out.println(e.getMessage());
          
        }
       
        String carModel = "";
        System.out.print("Car Model: ");
        try{
        if (scanner.hasNextLine())
        {
          carModel = scanner.nextLine();
        }
        } catch(DriverException e){
          e.getMessage();

        }

        String license = "";
        System.out.print("Car License: ");
        if (scanner.hasNextLine())
        {
          license = scanner.nextLine();
        }
        String address = "";
        System.out.print("Address: ");
        if (scanner.hasNextLine())
        {
          address = scanner.nextLine();
        }
        try {
          tmuber.registerNewDriver(name, carModel, license, address);
          System.out.printf("Driver: %-15s Car Model: %-15s License Plate: %-10s Address: %-10s",
                  name, carModel, license, address);
        }catch (DriverException e) {
          System.out.println(e.getMessage());
        }
      }
      // Register a new user
      else if (action.equalsIgnoreCase("REGUSER")) 
      {
        String name = "";
        System.out.print("Name: ");
        if (scanner.hasNextLine())
        {
          name = scanner.nextLine();
        }
        String address = "";
        System.out.print("Address: ");
        if (scanner.hasNextLine())
        {
          address = scanner.nextLine();
        }
        double wallet = 0.0;
        System.out.print("Wallet: ");
        if (scanner.hasNextDouble())
        {
          wallet = scanner.nextDouble();
          scanner.nextLine(); // consume nl!! Only needed when mixing strings and int/double
        }
        try {
          tmuber.registerNewUser(name, address, wallet);
          System.out.printf("User: %-15s Address: %-15s Wallet: %2.2f", name, address, wallet);
        } 
        catch (UserErrorException e) {
          System.out.println(e.getMessage());
          
        }
      }
        
      // Request a ride
      else if (action.equalsIgnoreCase("REQRIDE")) 
      {
        // Get the following information from the user (on separate lines)
        // Then use the TMUberSystemManager requestRide() method properly to make a ride request
        // "User Account Id: "      (string)
        // "From Address: "         (string)
        // "To Address: "           (string)

         // Follwed the above method for inputs 
        String ID = "";
        System.out.print("User Account Id: ");
        if (scanner.hasNextLine()) {
          
          ID = scanner.nextLine();
        }
        String from = "";
        System.out.print("From Address: ");
        if (scanner.hasNextLine()) {
          from = scanner.nextLine();
          
        }
        String to = "";
        System.out.print("To Address: ");
        if (scanner.hasNextLine()) {
          to = scanner.nextLine();
          
        }
        try {
          tmuber.requestRide(ID,from,to);
          
        } catch (Exception e) {
          System.out.println(e.getMessage());
        }

    

      }
      // Request a food delivery
      else if (action.equalsIgnoreCase("REQDLVY")) 
      {
        // Get the following information from the user (on separate lines)
        // Then use the TMUberSystemManager requestDelivery() method properly to make a ride request
        // "User Account Id: "      (string)
        // "From Address: "         (string)
        // "To Address: "           (string)
        // "Restaurant: "           (string)
        // "Food Order #: "         (string)
        String ID = "";
        System.out.print("User Account Id: ");
        if (scanner.hasNextLine()){
          ID = scanner.nextLine(); 
          
        }
        String from = "";
        System.out.print("From Address: ");
        if (scanner.hasNextLine()) {
          from = scanner.nextLine();
          
        }
        String to = "";
        System.out.print("To Address: ");
        if (scanner.hasNextLine()) {
          to = scanner.nextLine();
          
        }
        String restaurant = "";
        System.out.print("Restaurant: ");
        if (scanner.hasNextLine()) {
          restaurant = scanner.nextLine();
          
        }
        String orderID = "";
        System.out.print("Food Order #: ");
        if (scanner.hasNextLine()) {
          orderID = scanner.nextLine();
          
        }
        try {
          tmuber.requestDelivery(ID, from, to, restaurant, orderID);

          
        } catch (UserRequestDeliveryError e) {
          System.out.println(e.getMessage());
        }          
        
       
      }
      // Sort users by name
      else if (action.equalsIgnoreCase("SORTBYNAME")) 
      {
        tmuber.listbyName();
      }
      // Sort users by number of ride they have had
      else if (action.equalsIgnoreCase("SORTBYWALLET")) 
      {
        tmuber.listbyWallet();
      }
      // Sort current service requests (ride or delivery) by distance
      else if (action.equalsIgnoreCase("SORTBYDIST")) 
      {
        tmuber.sortByDistance();
      }
      // Cancel a current service (ride or delivery) request
      else if (action.equalsIgnoreCase("CANCELREQ")) 
      {
        int request = -1;
        System.out.print("Request #: ");
        if (scanner.hasNextInt())
        {
          request = scanner.nextInt();
          scanner.nextLine(); // consume nl character
        }
        int zone = -1;
        System.out.print("Zone #: ");
        if (scanner.hasNextInt())
        {
          zone = scanner.nextInt();
          scanner.nextLine(); // consume nl character
        }
        try{
          tmuber.cancelServiceRequest(request,zone);  
          System.out.println("Service request #" + request + " cancelled");

        }
        catch(ServiceCancelError e){
          System.out.println(e.getMessage());

        }
        
      }

      // Get the Current Total Revenues
      else if (action.equalsIgnoreCase("REVENUES")) 
      {
        System.out.println("Total Revenue: " + tmuber.totalRevenue);
      }
      // Unit Test of Valid City Address 
      else if (action.equalsIgnoreCase("ADDR")) 
      {
        String address = "";
        System.out.print("Address: ");
        if (scanner.hasNextLine())
        {
          address = scanner.nextLine();
        }
        System.out.print(address);
        if (CityMap.validAddress(address))
          System.out.println("\nValid Address"); 
        else
          System.out.println("\nBad Address"); 
      }
      // Unit Test of CityMap Distance Method
      else if (action.equalsIgnoreCase("DIST")) 
      {
        String from = "";
        System.out.print("From: ");
        if (scanner.hasNextLine())
        {
          from = scanner.nextLine();
        }
        String to = "";
        System.out.print("To: ");
        if (scanner.hasNextLine())
        {
          to = scanner.nextLine();
        }
        System.out.print("\nFrom: " + from + " To: " + to);
        System.out.println("\nDistance: " + CityMap.getDistance(from, to) + " City Blocks");
      }
      
      System.out.print("\n>");
    }
  }
}

