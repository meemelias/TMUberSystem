import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
// Name: Meem Elias
// StuedentID: 500783098
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Iterator;


// import Driver.Status;

/*
 * 
 * This class contains the main logic of the system.
 * 
 *  It keeps track of all users, drivers and service requests (RIDE or DELIVERY)
 * 
 */
public class TMUberSystemManager
{
  private ArrayList<User>   users;
  
  private HashMap<String,User> UserMap;
  private ArrayList<Driver> drivers;

  private ArrayList<TMUberService> serviceRequests; 
  // private Queue<TMUberService>[] serviceRequests2;
  private Queue<TMUberService>[] serviceRequests2; 


  public double totalRevenue; // Total revenues accumulated via rides and deliveries
  
  // Rates per city block
  private static final double DELIVERYRATE = 1.2;
  private static final double RIDERATE = 1.5;
  // Portion of a ride/delivery cost paid to the driver
  private static final double PAYRATE = 0.1;

  //These variables are used to generate user account and driver ids
  int userAccountId = 900;
  int driverId = 700;

  
  public TMUberSystemManager()
  {
    // users   = new ArrayList<User>();
    UserMap = new HashMap<String,User>();
    drivers = new ArrayList<Driver>();
    serviceRequests = new ArrayList<TMUberService>(); 
    serviceRequests2 = new LinkedList[4];
    for (int i = 0; i < 4; i++) {
      serviceRequests2[i] = new LinkedList<TMUberService>();
    }
    users = new ArrayList<>(UserMap.values());
    
    // TMUberRegistered.loadPreregisteredUsers(users);
    // TMUberRegistered.loadPreregisteredDrivers(drivers);
    
    totalRevenue = 0;

  }

  // General string variable used to store an error message when something is invalid 
  // (e.g. user does not exist, invalid address etc.)  
  // The methods below will set this errMsg string and then return false
  String errMsg = null;

  public String getErrorMessage()
  {
    return errMsg;
  }
  public ArrayList<User> getuserList(){
    ArrayList<User> userList = new ArrayList<>(UserMap.values());
    return userList;
  }
  
  // Given user account id, find user in list of users
  // Return null if not found
  public User getUser(String accountId)
  {
    
    User user = UserMap.get(accountId);

    // Fill in the code
    return user;
    
  }
  
  // Check for duplicate user
  private boolean userExists(User user)
  { 
    // Fill in the code
    for (User i : getuserList()) {
      if (i.equals(user)) {
        return true; 
      }
      
    }

    return false;
  }
  
 // Check for duplicate driver
 private boolean driverExists(Driver driver)
 {
   // Fill in the code
   for (Driver i : drivers) {
    if (i.equals(driver)) {
      return true;
      
    }
    
   }
   return false;
 }
  
  // Given a user, check if user ride/delivery request already exists in service requests
  private boolean existingRequest(TMUberService req)
  {
    User user = req.getUser();
    for (TMUberService i : serviceRequests) {
      if (i.getUser().equals(user)) {
        return true;
        
      }

      
    }
    // Fill in the code
    return false;
  }

  // Calculate the cost of a ride or of a delivery based on distance 
  private double getDeliveryCost(int distance)
  {
    return distance * DELIVERYRATE;
  }

  private double getRideCost(int distance)
  {
    return distance * RIDERATE;
  }
  // private double getDriverPay(int distance){
  //   return distance
  // }

  // Go through all drivers and see if one is available
  // Choose the first available driver
  // Return null if no available driver
  private Driver getAvailableDriver()
  {
    // Fill in the code
    for (Driver driver : drivers) {
      if (driver.getStatus()==Driver.Status.AVAILABLE) {
        return driver;
        
      }
      
    }
    return null;
  }

  // Print Information (printInfo()) about all registered users in the system
  public void   listAllUsers()
  {
    System.out.println();
    Collection <User> values = UserMap.values();
    ArrayList<User> valuesList = new ArrayList<>(values);
    Collections.sort(valuesList, new UseridComparator());
    for (int i = 0; i < valuesList.size(); i++)
    {
      int index = i + 1;
      System.out.printf("%-2s. ", index);
      valuesList.get(i).printInfo();
      System.out.println(); 
    }
  }
  public void listbyName()
  {
    Collection <User> values = UserMap.values();
    ArrayList<User> valuesList = new ArrayList<>(values);
    Collections.sort(valuesList, new NameComparator());
    for (int i = 0; i < valuesList.size(); i++)
    {
      int index = i + 1;
      System.out.printf("%-2s. ", index);
      valuesList.get(i).printInfo();
      System.out.println(); 
    }

  }
  public void listbyWallet()
  {
    Collection <User> values = UserMap.values();
    ArrayList<User> valuesList = new ArrayList<>(values);
    Collections.sort(valuesList, new UserWalletComparator());
    for (int i = 0; i < valuesList.size(); i++)
    {
      int index = i + 1;
      System.out.printf("%-2s. ", index);
      valuesList.get(i).printInfo();
      System.out.println(); 
    }


  }

  // Print Information (printInfo()) about all registered drivers in the system
  public void listAllDrivers()
  {
    // Fill in the code
    System.out.println();
    for (int i = 0; i < drivers.size(); i++) {
      int index = i + 1;
      System.out.printf("%-2s. ", index);
      drivers.get(i).printInfo();
      System.out.println();
    }
  }

  // Print Information (printInfo()) about all current service requests
  public void listAllServiceRequests()
  {
    // Fill in the code
    String dash = " ----------------------------------------------------";
    // System.out.println();
    // for (int i = 0; i < serviceRequests.size(); i++) {
    //   int index = i + 1;
    //   System.out.printf("%-2s. %-2s", index,dash);
    //   serviceRequests.get(i).printInfo();
    //   System.out.println();
    // }
    System.out.println("ZONE 0");
    String lines = "------ ";
    System.out.println(lines);
    System.out.println(lines);
    Queue<TMUberService> q1 = serviceRequests2[0];
    Queue<TMUberService> q2 = serviceRequests2[1];
    Queue<TMUberService> q3 = serviceRequests2[2];
    Queue<TMUberService> q4 = serviceRequests2[3];
    int index = 1;
    for(TMUberService service : q1){
      System.out.printf("%-2s. %-2s", index,dash);
      index ++;
      service.printInfo();
      System.out.println();
      
      }
  System.out.println();
  System.out.println();  
      
  System.out.println("ZONE 1");

  System.out.println(lines);
  System.out.println((lines));
  index = 1;
  for(TMUberService service : q2){
    System.out.printf("%-2s. %-2s", index,dash);
    index ++;
    service.printInfo();
    System.out.println();
    
    }
  System.out.println();
  System.out.println();  
  System.out.println("ZONE 2");

  System.out.println(lines);
  System.out.println((lines));
  index = 1;
  for(TMUberService service : q3){
    System.out.printf("%-2s. %-2s", index,dash);
    index ++;
    service.printInfo();
    System.out.println();
    
    }
  System.out.println();
  System.out.println();  
  System.out.println("ZONE 3");
  // String lines = "------ ";
  System.out.println(lines);
  System.out.println((lines));
  index = 1;
  for(TMUberService service : q4){
      System.out.printf("%-2s. %-2s", index,dash);
      index ++;
      service.printInfo();
      System.out.println();
      
      }


      
      
      
     
  }

  // Add a new user to the system
  public void registerNewUser(String name, String address, double wallet) throws RuntimeException
  {
    // Fill in the code. Before creating a new user, check paramters for validity
    // See the assignment document for list of possible erros that might apply
    // Write the code like (for example):
    // if (address is *not* valid)
    // {
    //    set errMsg string variable to "Invalid Address "
    //    return false
    // }
    // If all parameter checks pass then create and add new user to array list users
    // Make sure you check if this user doesn't already exist!
    if (name.equals("") || name == null) {
      throw new UserErrorException("Invalid name");
      
    }
    if (!CityMap.validAddress(address)) {
      throw new UserErrorException("Invalid address");

      
    }
    if (wallet < 0) {
      throw new UserErrorException("Invalid wallet");
      
    }
    // Creating account Id
    String userId = String.valueOf(userAccountId) + String.valueOf(getuserList().size());
    User newuser = new User(userId, name, address, wallet);
    for (User i : getuserList()) { 
      if (i.equals(newuser)) {
        throw new UserErrorException("User Already Exists in System");
      }
      
    }
   
    UserMap.put(userId, newuser);
    
    
    
  }

  // Add a new driver to the system
  public void registerNewDriver(String name, String carModel, String carLicencePlate, String address) throws RuntimeException
  {
    // Fill in the code - see the assignment document for error conditions
    // that might apply. See comments above in registerNewUser
    if (name.equals("") || name == null) {
      throw new DriverException("Invalid Name");
      
    }

    if (carModel.equals("") || carModel == null) {
      throw new DriverException("Incorrect Car Model");
      
      
    }
    if (carLicencePlate.equals("") || carLicencePlate == null) {
      throw new DriverException("Incorrect Car License");

      
    }
    if (address.equals("") || address == null || (!CityMap.validAddress(address))) {
      throw new DriverException("Inavlid address");
      
      
    }
    String driverid = String.valueOf(driverId) + String.valueOf(drivers.size());

    Driver newDriver = new Driver(driverid, name, carModel, carLicencePlate, address);
    if (driverExists(newDriver)) {
      throw new DriverException("Driver already exists");
      
      
    }
    drivers.add(newDriver);
    
  }

  // Request a ride. User wallet will be reduced when drop off happens
  public void requestRide(String accountId, String from, String to) throws RuntimeException
  {
    // Check for valid parameters
	  // Use the account id to find the user object in the list of users
    // Get the distance for this ride
    // Note: distance must be > 1 city block!
    // Find an available driver
    // Create the TMUberRide object
    // Check if existing ride request for this user - only one ride request per user at a time!
    // Change driver status
    // Add the ride request to the list of requests
    // Increment the number of rides for this user
    User user = getUser(accountId);
    if (user == null) {
       throw new UserRequestRideError("User account not Found");
      
    }
    if (!(CityMap.validAddress(from) && CityMap.validAddress(to))) {
      throw new UserRequestRideError("Invalid Request Address");

      
    }
    int distance = CityMap.getDistance(from, to);
    if (distance <= 1) {
      throw new UserRequestRideError("Insuffiecient Distance");

      
    }
    
    double ridecost = getRideCost(distance);
    if (user.getWallet() < ridecost) {
      throw new UserRequestRideError("User account not Found");

    }
    TMUberRide ride = new TMUberRide(from, to, user, distance, ridecost);
    int zone = CityMap.getCityZone(from);
    Queue<TMUberService> q = serviceRequests2[zone];
    for (TMUberService tmUberService : q) {
      if (ride.equals(tmUberService)) {
        throw new UserRequestRideError("Ride already exists");

        
      }
      
    }
    q.add(ride);
  
    user.addRide();
    System.out.printf("RIDE for: %-15s From: %-15s To: %-15s",user.getName(), from, to);


    
  }

  // Request a food delivery. User wallet will be reduced when drop off happens
  public void requestDelivery(String accountId, String from, String to, String restaurant, String foodOrderId) throws RuntimeException
  {
    // See the comments above and use them as a guide
    // For deliveries, an existing delivery has the same user, restaurant and food order id
    // Increment the number of deliveries the user has had
    User user = getUser(accountId);
    if (user == null) {
      throw new UserRequestDeliveryError("User not valid");
      
    }
    if (!(CityMap.validAddress(from) && CityMap.validAddress(to))) {
      throw new UserRequestDeliveryError("Address not valid");

      
    }
    int distance = CityMap.getDistance(from, to);
    if (distance <= 1) {
      throw new UserRequestDeliveryError("Insufficient distance");

      
    }

    double deliveryCost = getDeliveryCost(distance);
    if (user.getWallet() < deliveryCost) {
      throw new UserRequestDeliveryError("Insufficient Funds");

      
    }

    TMUberDelivery delivery = new TMUberDelivery(from, to, user, distance, deliveryCost, restaurant, foodOrderId);
    int zone = CityMap.getCityZone(from);
    Queue<TMUberService> q = serviceRequests2[zone];
    for (TMUberService service: q) {
      if (delivery.equals(service)) {
        throw new UserRequestDeliveryError("Delivery Requests already exists");
  
        
       }
      
    }
    
    q.add(delivery);
    user.addDelivery();
    System.out.printf("DELIVERY for: %-15s From: %-15s To: %-15s",user.getName(), from, to);

    
  }


  // Cancel an existing service request. 
  // parameter int request is the index in the serviceRequests array list

  // For both canecelling and dropOff, I made sure to check if type is ride or delivery

  public void cancelServiceRequest(int request,int zone) throws RuntimeException
  {
    // Check if valid request #
    // Remove request from list
    // Also decrement number of rides or number of deliveries for this user
    // since this ride/delivery wasn't completed
    if (!(zone == 0 ||zone == 1 ||zone == 2 || zone == 3) ) {
      throw new ServiceCancelError("Invalid Zone Number");
      
    }
    Queue<TMUberService> q = serviceRequests2[zone];
    if (q.isEmpty()) {
      throw new ServiceCancelError("There are no requests in " + zone);

    }
    Iterator<TMUberService> iter = q.iterator();
    TMUberService service = null;
    int i = 0;
  
    if (request > 0 && request <= q.size()) {
      for (TMUberService tmUberService : q) {
        if (i == request -1) {
          service = tmUberService;
          break;
          
        }
        i++;
        
      }
      int j = 0;
      while (iter.hasNext()) {
        TMUberService service1 = iter.next();
        j++;
        if (j ==request) {
          iter.remove();
          break;
          
        }
    }
    User user = service.getUser();
    if (service.getServiceType().equalsIgnoreCase("ride")) {
      user.minusRide();
      
    }
    else if (service.getServiceType().equalsIgnoreCase("delivery")) {
      user.miunsDelivery();
      
    }
    return;
     
    }
    throw new ServiceCancelError("Invalid request");
    
  }
  
  

  public void setUsers(ArrayList<User> userList){
    for (User user : userList) {
      String id = user.getAccountId();
      UserMap.put(id, user);
      
    }
  }
  public void setDrivers(ArrayList<Driver> driverList){
    for (Driver driver : driverList) {
      drivers.add(driver);

      
    }
  }
  public void pickup(String driverId) throws RuntimeException{
    Driver driver = null;
    boolean flag = false;
    for (Driver personDriver : drivers) {
      if (personDriver.getId().equals(driverId)) {
        driver = personDriver;
        flag = true;
        
      }
    }
    if (!flag) {
      throw new PickupException("Invalid driverId");
    }
    if (driver.getStatus() == Driver.Status.DRIVING) {
      throw new PickupException("Drivers is not available");
      
    }
    int zone = CityMap.getCityZone(driver.getAddress());
    Queue<TMUberService> q = serviceRequests2[zone];
    if (q.isEmpty()) {
      throw new PickupException("No service request in zone " + zone);

      
    }
    TMUberService remove = q.remove();
    driver.setService(remove);
    driver.setStatus(Driver.Status.DRIVING);
    driver.setAddress(remove.getFrom());
    System.out.println("Driver " + driverId + " Picking Up in Zone " + driver.getZone());
  }


  public void dropoff2(String driverId) throws RuntimeException{
    Driver driver = null;
    boolean flag = false;
    for (Driver personDriver : drivers) {
      if (personDriver.getId().equals(driverId)) {
        driver = personDriver;
        flag = true;
        
      }
    }
    if (!flag) {
      throw new DriverdropOffException("Invalid DriverId");
    }

    if (driver.getStatus() == Driver.Status.AVAILABLE) {
      throw new DriverdropOffException("Driver Currently Doesnt Have a Order");
      
    }
    TMUberService service = driver.getService();
    String type = service.getServiceType();
    User user = service.getUser();
    if (type.equals("RIDE")) {
      double cost = getRideCost(service.getDistance());
      totalRevenue += cost;
      double driverPay = cost *PAYRATE;
      double wallet = driver.getWallet() + driverPay;
      driver.setWallet(wallet);
      totalRevenue -= driverPay;
      driver.setStatus(Driver.Status.AVAILABLE);
      user.setWallet((user.getWallet() - cost));

      
    }
    else if (type.equals("DELIVERY")) {
      double cost = getDeliveryCost(service.getDistance());
      totalRevenue += cost;
      double driverPay = cost *PAYRATE;
      double wallet = driver.getWallet() + driverPay;
      driver.setWallet(wallet);
      totalRevenue -= driverPay;
      driver.setStatus(Driver.Status.AVAILABLE);
      user.setWallet((user.getWallet() - cost));
      
    }
    driver.changeServicetoNull();
    driver.setAddress(service.getTo());
    driver.setZone(CityMap.getCityZone(service.getTo()));
    System.out.println("Driver " + driverId + " Dropping Off");

  }

  
  public  void driveTo(String driverId, String address) throws RuntimeException{
    Driver driver = null;
    boolean flag = false;
    for (Driver personDriver : drivers) {
      if (personDriver.getId().equals(driverId) ) {
        driver = personDriver;
        flag = true;
        
      }
    }
    if (!flag) {
      throw new driveToException("Invalid driverID");
    }
    //Check valid address also
    if (driver.getStatus() == Driver.Status.DRIVING) {
      throw new driveToException("Driver not available");
      
    }
    driver.setAddress(address);
    driver.setZone(CityMap.getCityZone(address));
    System.out.println("Driver " + driverId + " Now in Zone " + driver.getZone());


  }

 
  // Helper class for method sortByUserName
  private class NameComparator implements Comparator<User>
  {
    public int compare(User a, User b){
      String first = a.getName();
      String second = b.getName();

      return first.compareTo(second);

    } 
    
  }

 
  private class UserWalletComparator implements Comparator<User>
  {
    public int compare(User a, User b){
      double first = a.getWallet();
      double second = b.getWallet();
      if (first > second) {
        return 1;
        
      }
      else if (first < second) {
        return -1;
        
      }
      return 0;

    }  
  
  }
  private class UseridComparator implements Comparator<User>{
    public int compare(User a, User b){
      // return a.getAccountId().compareTo(b.getAccountId());
      return Integer.parseInt(a.getAccountId())-(Integer.parseInt(b.getAccountId()));

    }
  }

  // Sort trips (rides or deliveries) by distance
  // Then list all current service requests
  public void sortByDistance()
  {
    Collections.sort(serviceRequests, new DistanceComparator());
    listAllServiceRequests();
    
  }
  private class DistanceComparator implements Comparator<TMUberService>
  {
    public int compare(TMUberService a,TMUberService b){
      
      if (a.getDistance() > b.getDistance()) {
        return 1;
        
      }
      else if ( a.getDistance() < b.getDistance()) {
        return -1;
        
      }
      return 0;

    }  
  
  }

}
final class DriverException extends RuntimeException
{ 
  public DriverException(){}
  public DriverException(String message){
    super(message);
  }
  
}
final class PickupException extends RuntimeException
{
  public PickupException(){}
  public PickupException(String message){
    super(message);

  }
}
final class driveToException extends RuntimeException
{
  public driveToException(){}
  public driveToException(String message){
    super(message);
  }
}
final class DriverdropOffException extends RuntimeException
{
  public DriverdropOffException(){}
  public DriverdropOffException(String message){
    super(message);
  }
}

final class UserErrorException extends RuntimeException
{
  public UserErrorException(){}
  public UserErrorException(String message){
    super(message);
  }
}
final class UserRequestRideError extends RuntimeException
{
  public UserRequestRideError(){}
  public UserRequestRideError(String message){
    super(message);
  }
}
final class UserRequestDeliveryError extends RuntimeException
{
  public UserRequestDeliveryError(){}
  public UserRequestDeliveryError(String message){
    super(message);
  }
}
final class ServiceCancelError extends RuntimeException
{
  public ServiceCancelError(){}
  public ServiceCancelError(String message){
    super(message);
  }
}

