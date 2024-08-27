A CLI application that mimics the typical user action of the Uber App

- Please Complile and run the TMUberUI.java file on command line
- You must first load the drivers and users from the drivers.txt and users.txt file.
- Command to load users --> 'loadusers' then enter 'users.txt'
- Command to load drivers --> 'loaddrivers' then enter 'drivers.txt'
- When entering the address, make sure it follows the rules mentioned below:
  // The city consists of a grid of 9 X 9 City Blocks

  // Streets are east-west (1st street to 9th street)
  // Avenues are north-south (1st avenue to 9th avenue)
  
  // Example 1 of Interpreting an address:  "34 4th Street"
  // A valid address *always* has 3 parts.
  // Part 1: Street/Avenue residence numbers are always 2 digits (e.g. 34).
  // Part 2: Must be 'n'th or 1st or 2nd or 3rd (e.g. where n => 1...9)
  // Part 3: Must be "Street" or "Avenue" (case insensitive)
  
  // Use the first digit of the residence number (e.g. 3 of the number 34) to determine the avenue.
  // For distance calculation you need to identify the the specific city block - in this example 
  // it is city block (3, 4) (3rd avenue and 4th street)
  
  // Example 2 of Interpreting an address:  "51 7th Avenue"
  // Use the first digit of the residence number (i.e. 5 of the number 51) to determine street.
  // For distance calculation you need to identify the the specific city block - 
  // in this example it is city block (7, 5) (7th avenue and 5th street)
- Here are the rest of commands:
- 'requests' --> show all the currents pending requests by its zone
- 'users' --> shows the currenly registered users
- 'drivers' --> shows the currenly registered drivers in the system
- 'reqride' --> takes in user account id, from address and to address.
- 'reqdlvy' --> takes in user account id, from address, to address, restaurent name and food order number
- 'pickup' --> Enter driverid, available driver will pick up the order in their existing zone
- 'dropoff' --> Enter driverid, if the the driver currenly has a pickup, it will dropoff the order.
- 'driveto' --> Enter driverId and address. Changes to location of the driver to the input address
   
  
