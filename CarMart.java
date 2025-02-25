import java.sql.Connection;
import java.sql.DriverManager;
import java.util.InputMismatchException;
import java.util.Scanner;

interface CarDAO{

}

class DBConnection{

    private static Connection con = null;

    private DBConnection(){

    }

    public static Connection getConnectionObject(){
        if(con == null){
            try {  
                con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/demodb", "postgres", "tiger");
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return con;
    }

}

class Car {
    private int carID;
    private String company;
    private String model;
    private int seater;
    private String fuelType;
    private String type;
    private double price;
    private boolean sold;

    // Constructor
    public Car(int carID, String company, String model, int seater, String fuelType, String type, double price, boolean sold) {
        this.carID = carID;
        this.company = company;
        this.model = model;
        this.seater = seater;
        this.fuelType = fuelType;
        this.type = type;
        this.price = price;
        this.sold = sold;
    }

    // Getters and Setters
    public int getCarID() {
        return carID;
    }

    public void setCarID(int carID) {
        this.carID = carID;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getSeater() {
        return seater;
    }

    public void setSeater(int seater) {
        this.seater = seater;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isSold() {
        return sold;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }

    // toString method for easy printing
    @Override
    public String toString() {
        return "Car{" +
                "carID=" + carID +
                ", company='" + company + '\'' +
                ", model='" + model + '\'' +
                ", seater=" + seater +
                ", fuelType='" + fuelType + '\'' +
                ", type='" + type + '\'' +
                ", price=" + price +
                ", sold=" + sold +
                '}';
    }
}

public class CarMart {

    public static void main(String[] args) {
        
        int choice = 0;

        do{
            Scanner scanner = new Scanner(System.in);

            System.out.println("----------------------------------");
            System.out.println("\nMenu CarMart");
            System.out.println("1. Add");
            System.out.println("2. Search");
            System.out.println("3. Update");
            System.out.println("4. Sold");
            System.out.println("5. Exit");

            System.out.print("\nEnter your choice: ");

            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input format");
                scanner.nextLine();
                continue;
            }
            
            System.out.println("----------------------------------"); 

            /*
            1. Add
            2. Search
            3. Update	(only price to be updated of specific car)
            4. Sold
            5. Exit
             */

        }
    }
}