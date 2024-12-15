import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;


public class Admin {

    String FName, LName, UName, Password, Contact, Address;

    public Admin(String fName, String lName, String uName, String password, String contact, String address) {
        this.FName = fName;
        this.LName = lName;
        this.UName = uName;
        this.Password = password;
        this.Contact = contact;
        this.Address = address;

        saveToFile();
    }

    private void saveToFile() {
        File file = new File("C:\\Users\Desktop\\BookingSystemFiles\\Admin.txt");//Admins file path

        try {
            FileWriter fileWriter = new FileWriter(file, true);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            // Write the data in CSV format
            writer.write(this.FName + "," + this.LName + "," + this.UName + "," + this.Password + "," + this.Contact + "," + this.Address);
            writer.newLine();
            writer.close();

            JOptionPane.showMessageDialog(null, "Data saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
     JOptionPane.showMessageDialog(null, "An error occurred while saving the data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

    }
}
