import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;


class DBConnection{
    
    private static Connection con = null;
    
    private DBConnection(){

    }
    
    public static Connection getConnectionObject(){
        if(con == null){
            try {  
                Class.forName("org.postgresql.Driver");
                con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/demodb", "postgres", "tiger");
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return con;
    }

    public static void closeConnection() {
        if (con != null) {
            try {
                con.close();
                con = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
}

interface CarDAO{
    public void addRecord(Car c);
}

class CarRecord implements CarDAO{
    public void addRecord(Car c){
        
        String sql = "INSERT INTO CarMart (Company, Model, Seater, FuelType, Type, Price, Sold) VALUES (?, ?, ?, ?, ?, ?, ?)";
        

        try(Connection con = DBConnection.getConnectionObject();
            PreparedStatement pstmt = con.prepareStatement(sql);
        )
        {
            pstmt.setString(1,c.getCompany());
            pstmt.setString(2, c.getModel());
            pstmt.setInt(3, c.getSeater());
            pstmt.setString(4, c.getFuelType());
            pstmt.setString(5, c.getType());
            pstmt.setDouble(6, c.getPrice());
            pstmt.setBoolean(7, c.isSold());

            // Execute the insert
            int rowsAffected = pstmt.executeUpdate();
            System.out.println(rowsAffected + " row(s) inserted successfully.");

        }catch (SQLException e) {
            e.printStackTrace();
        }
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

    // Constructor for inserting a new car (No CarID)
    public Car(String company, String model, int seater, String fuelType, String type, double price, boolean sold) {
        this.company = company;
        this.model = model;
        this.seater = seater;
        this.fuelType = fuelType;
        this.type = type;
        this.price = price;
        this.sold = sold;
    }

    // Constructor for retrieving form DB
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
            scanner.nextLine(); // Consume the newline character
            
            System.out.println("----------------------------------"); 

            switch(choice){
                case 1->{
                    

                    System.out.print("Enter Company: ");
                    String company = scanner.nextLine();
                    

                    System.out.print("Enter Model: ");
                    String model = scanner.nextLine();

                    System.out.print("Enter Seater Capacity: ");
                    int seater = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character

                    System.out.print("Enter Fuel Type: ");
                    String fuelType = scanner.nextLine();

                    System.out.print("Enter Type (Sedan/SUV/etc.): ");
                    String type = scanner.nextLine();

                    System.out.print("Enter Price: ");
                    double price = scanner.nextDouble();
                    scanner.nextLine(); // Consume the newline character

                    Car car = new Car(company, model, seater, fuelType, type, price, false);

                    // Add car record to the database
                    CarDAO carDAO = new CarRecord();
                    carDAO.addRecord(car);
                    
                }

                case 2->{

                }

                case 3->{

                }

                case 4->{

                }

                case 5->{
                    scanner.close();
                    System.out.println("Exiting the code");
                }

                default ->{
                    System.out.println("Check your choice");
                }
            }

        } while (choice !=5);
    }
}