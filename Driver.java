/*
 * 
 * This class simulates a car driver in a simple uber app 
 * 
 * Everything has been done for you except the equals() method
 */
// Name: Meem Elias
// StuedentID: 500783098
public class Driver
{
  private TMUberService service;
  private String id;
  private String name;
  private String carModel;
  private String licensePlate;
  private double wallet;
  private String type;
  private String address;
  private int zone;
  
  public static enum Status {AVAILABLE, DRIVING};
  private Status status;
    
  
  public Driver(String id, String name, String carModel, String licensePlate, String address)
  {
    this.id = id;
    this.name = name;
    this.carModel = carModel;
    this.licensePlate = licensePlate;
    this.status = Status.AVAILABLE;
    this.wallet = 0;
    this.type = "";
    this.address = address;
    this.service = null;
    this.zone = CityMap.getCityZone(address);
    
  }
  // Print Information about a driver
  public void printInfo()
  {
    
    System.out.printf("Id: %-3s Name: %-15s Car Model: %-15s License Plate: %-10s Wallet: %2.2f Status: %-10s Address: %-15s Zone: %-10s", 
                      id, name, carModel, licensePlate, wallet,this.status,address,zone);

    if (service != null ) {
      System.out.println();
      System.out.printf("From: %-5s To: %-5s",service.getFrom(),service.getTo());
                  
                        
    }
    
  }
  // Getters and Setters
  public String getType()
  {
    return type;
  }
  public String getAddress(){
    return address;
  }
  public void setAddress(String address){
    this.address = address;

  }
  public void setType(String type)
  {
    this.type = type;
  }
  public String getId()
  {
    return id;
  }
  public int getZone(){
    return zone;
  }
  public void setId(String id)
  {
    this.id = id;
  }
  public void changeServicetoNull(){
    this.service = null;
  }
  
  public String getName()
  {
    return name;
  }
  public void setName(String name)
  {
    this.name = name;
  }
  public void setService(TMUberService service){
    this.service = service;
  }
  public TMUberService getService(){
    return service;
  }
  public String getCarModel()
  {
    return carModel;
  }
  public void setCarModel(String carModel)
  {
    this.carModel = carModel;
  }
  public String getLicensePlate()
  {
    return licensePlate;
  }
  public void setLicensePlate(String licensePlate)
  {
    this.licensePlate = licensePlate;
  }
  public Status getStatus()
  {
    return status;
  }
  public void setStatus(Status status)
  {
    this.status = status;
  }
  public double getWallet()
  {
    return wallet;
  }
  public void setWallet(double wallet)
  {
    this.wallet = wallet;
  }
  public void setZone(int num){
    this.zone = num;
  }
  /*
   * Two drivers are equal if they have the same name and license plates.
   * This method is overriding the inherited method in superclass Object
   * 
   * Fill in the code 
   */
  public boolean equals(Object other)
  {
    Driver otherA = (Driver) other;
    if (this.name.equalsIgnoreCase(otherA.name) && this.licensePlate.equalsIgnoreCase(otherA.licensePlate)) {
      return true; 
    }
    return false;
  }
  
  // A driver earns a fee for every ride or delivery
  public void pay(double fee)
  {
    wallet += fee;
  }
}
