package application;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import dao.SauceNamesDao;
import entity.SauceNames;

public class Menu {
  
  private SauceNamesDao sauceNamesDao = new SauceNamesDao();
  private Scanner scanner = new Scanner(System.in);
  private List<String> options = Arrays.asList("Display Sauces", "Display a Sauce", "Create Sauce", "Delete Sauce");
  public void start() {
    String selection = "";
    
    do {
      printMenu();
      selection = scanner.nextLine();
      
      try {
      if (selection.equals("1")) {
        displaySauceNames();
      } else if (selection.equals("2")) {
        displaySauceName();
      } else if (selection.equals("3")) {
        createSauceNames();
      }else if (selection.equals("4")) {
        deleteSauceNames();
      }
      } catch (SQLException e) {
        e.printStackTrace();
      }
      
      System.out.println("Press enter to continue...");
      scanner.nextLine();
    } while (!selection.equals("-1"));
  }
  
  private void printMenu() {
    System.out.println("Select an Option:\n--------------------------");
    for(int i = 0; i < options.size(); i++) {
      System.out.println(i+1 + ") " + options.get(i));
    }
  }
  private void displaySauceNames() throws SQLException {
    List<SauceNames> sauceNames = sauceNamesDao.getSauceNames();
    for (SauceNames sauceName : sauceNames) {
      System.out.println(sauceName.getId() + ": " + sauceName.getName());
    }
  }
  private void displaySauceName() throws SQLException {
    System.out.println("Enter sauce id: ");
    int id = Integer.parseInt(scanner.nextLine());
    SauceNames sauce = sauceNamesDao.getSauceNamesById(id);
    System.out.println(sauce.getId() + ": " + sauce.getName());
    
  }
  private void createSauceNames() throws SQLException {
    System.out.print("Enter new sauce name:");
    String sauceName = scanner.nextLine();
    sauceNamesDao.createSauceNames(sauceName);
  }
  private void deleteSauceNames() throws SQLException {
    System.out.print("Enter sauce name id to delete:");
    int id = Integer.parseInt(scanner.nextLine());
    sauceNamesDao.deleteSauceNamesById(id);
  }
}
