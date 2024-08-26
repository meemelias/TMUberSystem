import java.util.Arrays;
import java.util.Scanner;
// Name: Meem Elias
// StuedentID: 500783098

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
//
// Distance in city blocks between (3, 4) and (7, 5) is then == 5 city blocks
// i.e. (7 - 3) + (5 - 4) 

public class CityMap
{
  // Checks for string consisting of all digits
  // An easier solution would use String method matches()
  private static boolean allDigits(String s)
  {
    for (int i = 0; i < s.length(); i++)
      if (!Character.isDigit(s.charAt(i)))
        return false;
    return  true;
  }

  // Get all parts of address string
  // An easier solution would use String method split()
  // Other solutions are possible - you may replace this code if you wish
  private static String[] getParts(String address)
  {
    String parts[] = new String[3];
    
    if (address == null || address.length() == 0)
    {
      parts = new String[0];
      return parts;
    }
    int numParts = 0;
    Scanner sc = new Scanner(address);
    while (sc.hasNext())
    {
      if (numParts >= 3)
        parts = Arrays.copyOf(parts, parts.length+1);

      parts[numParts] = sc.next();
      numParts++;
    }
    if (numParts == 1)
      parts = Arrays.copyOf(parts, 1);
    else if (numParts == 2)
      parts = Arrays.copyOf(parts, 2);
    return parts;
  }
  // Since valid address is 3 Parts. I made 3 different methods for each of the 3 parts. 
  private static boolean CheckFirstPart(String first){
    if (allDigits(first) && first.length() ==2) {
      return true;
      
      
    }
    return false;

  }
  // Part 2: Must be 'n'th or 1st or 2nd or 3rd (e.g. where n => 1...9)
  public static boolean CheckSecondPart(String second){
    if (second.length() != 3) {
      return false;
      
    }
    char first = second.charAt(0);
    char sec = second.charAt(1);
    char third = second.charAt(2);

    if (!(Character.isDigit(first) && first - 48 != 0)) {
      return false;
      
    }
    if (first == '1' && sec == 's' && third == 't')  {
      return true;
      
    }
    if (first == '2' && sec == 'n' && third == 'd')  {
      return true;
      
    }
    if (first == '3' && sec == 'r' && third == 'd')  {
      return true;
      
    }
    if ((first == '4' || first == '5' || first == '6' || first == '7' || first == '8' || first == '9' ) && sec == 't' && third == 'h')  {
      return true;
      
    }
    return false;

  }

  private static boolean CheckThirdPart(String third){
    if (third.length() == 6 && third.equalsIgnoreCase("street") ||
    third.length() == 6 && third.equalsIgnoreCase("avenue") ) {
      return true;
      
    }
    return false;

  }

  // Checks for a valid address
  public static boolean validAddress(String address)
  {
    // Fill in the code
    // Make use of the helper methods above if you wish
    // There are quite a few error conditions to check for 
    // e.g. number of parts != 3
    String arr[] = getParts(address);
    if (arr.length != 3) {
      return false;
      
    }
    // If all three helper functions gives true, then it is a valid address
    else if (CheckFirstPart(arr[0]) && CheckSecondPart(arr[1]) && CheckThirdPart(arr[2])) {
      return true;
      
    }
    return false;
  }

  // Computes the city block coordinates from an address string
  // returns an int array of size 2. e.g. [3, 4] 
  // where 3 is the avenue and 4 the street
  // See comments at the top for a more detailed explanation
  public static int[] getCityBlock(String address)
  {
    int[] block = {-1, -1};
    if (!validAddress(address)) {
      return block;
      
    }
    String[] arr = CityMap.getParts(address);
 

    if (arr[2].equalsIgnoreCase("Street")) {
      block[0] = Character.getNumericValue(arr[1].charAt(0));
      block[1] = Character.getNumericValue(arr[0].charAt(0));
      
    }
    else if (arr[2].equalsIgnoreCase("Avenue")) {
      block[0] = Character.getNumericValue(arr[0].charAt(0));
      block[1] = Character.getNumericValue(arr[1].charAt(0));
      
    }

    // Fill in the code
    return block;
  }
  
  // Calculates the distance in city blocks between the 'from' address and 'to' address
  // Hint: be careful not to generate negative distances
  
  // This skeleton version generates a random distance
  // If you do not want to attempt this method, you may use this default code
  public static int getDistance(String from, String to)
  {
    // Fill in the code or use this default code below. If you use
    // the default code then you are not eligible for any marks for this part
    
 
    int[] fromArr = getCityBlock(from);
    int[] toArr = getCityBlock(to);
    int answer = 0;
   
    int firstPart = 0;
    if (fromArr[0] > toArr[0]) {
      firstPart = fromArr[0] - toArr[0];
      
    }
    else if (fromArr[0] < toArr[0]) {
      firstPart = toArr[0] - fromArr[0];
      
    }
    int secondPart = 0;
    if (fromArr[1] > toArr[1]) {
      secondPart = fromArr[1] - toArr[1];
      
    }
    else if (fromArr[1] < toArr[1]) {
      secondPart = toArr[1] - fromArr[1];
      
    }
    answer = firstPart + secondPart;

    return answer;
  }


  public static int getCityZone(String address){
    if (!validAddress(address)) {
      return -1;
      
    }

    
    String FirstPart = address.split(" ")[0];
    String SecondPart = address.split(" ")[1];
    String ThirdPart = address.split(" ")[2];
    if (ThirdPart.equalsIgnoreCase("avenue")) {
      int digit1 = Integer.parseInt(SecondPart.substring(0,1));
      int digit2 = Integer.parseInt(FirstPart.substring(0,1));
      
      if ((digit1 == 1 || digit1 == 2 || digit1 == 3 || digit1 == 4 || digit1 == 5) && (digit2 == 6 || digit2 == 7 || digit2 == 8 || digit2 == 9 ) ) {
        return 0;
        
      }
      if ((digit1 == 1 || digit1 == 2 || digit1 == 3 || digit1 == 4 || digit1 == 5) && (digit2 == 1 || digit2 == 2 || digit2 == 3 || digit2 == 4 || digit2 ==5 ) ) {
        return 3;
        
      }
      else if ((digit1 == 6 || digit1 == 7 || digit1 == 8 || digit1 == 9) && (digit2 == 1 || digit2 == 2 || digit2 == 3 || digit2 == 4 || digit2 == 5 )) {
        return 2;
      }
      else if ((digit1 == 6 || digit1 == 7 || digit1 == 8 || digit1 == 9) && (digit2 == 6 ||digit2 == 7 || digit2 == 8 || digit2 == 9 )) {

        return 1;
      }
      
    }

    if (ThirdPart.equalsIgnoreCase("street")) {
      int digit1 = Integer.parseInt(SecondPart.substring(0,1));
      int digit2 = Integer.parseInt(FirstPart.substring(0,1));
      if ((digit1 == 1 || digit1 == 2 || digit1 == 3 || digit1 == 4 || digit1 == 5) && (digit2 == 1 || digit2 == 2 || digit2 == 3 || digit2 == 4 || digit2 ==5 ) ) {
        return 3;

      }
      else if((digit1 == 1 || digit1 == 2 || digit1 == 3 || digit1 == 4 || digit1 == 5) && (digit2 == 6 || digit2 == 7 || digit2 == 8 || digit2 == 9) ) {
        return 2;

      }
      else if ((digit1 == 6 || digit1 == 7 || digit1 == 8 || digit1 == 9  ) && (digit2 == 6 || digit2 == 7 || digit2 == 8 || digit2 == 9)) {
        return 1;
      }
      else if ((digit1 == 6 || digit1 == 7 || digit1 == 8 || digit1 == 9  ) && (digit2 == 1 || digit2 == 2 || digit2 == 3 || digit2 == 4 || digit2 == 5)) {
        return 0;
      }


      
    }

    return -1;
    
  }
}
