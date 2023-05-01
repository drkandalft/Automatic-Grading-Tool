public class John_Doe {
    public static void main(String[] args) {
    	String phoneNumber = "1522566231";
        System.out.println(print_phone_number(phoneNumber));
    }
    
    public static String print_phone_number(String phoneNumber) {
        // Remove all non-digits
        phoneNumber = phoneNumber.replaceAll("\\D+", "");
        
        // Check if the phone number is valid
        if (phoneNumber.length() != 10) {
            return "Invalid phone number.";
        }
        
        // Format the phone number
        String formattedNumber = String.format("%s-%s-%s", phoneNumber.substring(0, 3), phoneNumber.substring(3, 6), phoneNumber.substring(6));
        return formattedNumber;
    }
}