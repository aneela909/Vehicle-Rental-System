import java.awt.List;
import java.io.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Car {

    String regNo;
    String model;
    String type;
    String seatingCapacity;

    public Car(String regNo, String model, String type, String seatingCapacity) {
        this.regNo = regNo;
        this.model = model;
        this.type = type;
        this.seatingCapacity = seatingCapacity;
    }

    public boolean addCar() {
        File file = new File("C:\\Users\\Desktop\\BookingSystemFiles\\Cars.txt");//Cars file path

        if (isCarExists(regNo)) {
            JOptionPane.showMessageDialog(null, "Car with this registration number already exists!", "Error", JOptionPane.ERROR_MESSAGE);

            return false;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(regNo + "," + model + "," + type + "," + seatingCapacity);
            writer.newLine();
            return true;
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error while saving car information.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    private boolean isCarExists(String regNo) {
        File file = new File("C:\\Users\\Desktop\\BookingSystemFiles\\Cars.txt");//Cars file path

        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader reader = new BufferedReader(fileReader);
            String line;
            while ((line = reader.readLine()) != null) {
                String[] carData = line.split(",");
                String savedRegNo = carData[0];

                if (savedRegNo.equals(regNo)) {
                    return true;
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error while checking car information.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        return false;
    }

    public static boolean deleteCar(String regNo) {

        File file = new File("C:\\Users\\Desktop\\BookingSystemFiles\\Cars.txt");//Cars file path

        ArrayList<String> updatedData = new ArrayList<>();

        boolean carDeleted = false;

        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader reader = new BufferedReader(fileReader);
            String line;

            while ((line = reader.readLine()) != null) {
                String[] carDetails = line.split(",");
                String currentRegNo = carDetails[0];
                if (currentRegNo.equals(regNo)) {
                    carDeleted = true;
                } else {
                    updatedData.add(line);
                }
            }

            if (carDeleted) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                    for (String carData : updatedData) {
                        writer.write(carData);
                        writer.newLine();

                    }

                    JOptionPane.showMessageDialog(null, "Car deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Car with this registration number not found!", "Error", JOptionPane.ERROR_MESSAGE);

            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error occurred while deleting the car.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return carDeleted;
    }

}
