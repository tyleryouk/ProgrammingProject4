/** 
 * ContactDatabase class: Database that stores Contacts.  
 * Also includes main method with interactive tester.  
 * @author: Tyler Youk 
 */

import java.util.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner; 

public class ContactDatabase {

  private ArrayList<Contact> contacts; // ArrayList of contact

  /** 
    * Basic Constructor for ContactDatabase
    * @param none
    */
  ContactDatabase(){
    contacts = new ArrayList<>();}

  /** 
    * inputContact method: helper method that asks the user for inputs in order to create a Contact
    * @return: none
    */
  public void inputContact() {
    Scanner scan = new Scanner(System.in);
    System.out.print("Please enter your first name: ");
    String firstName = scan.nextLine();
    System.out.print("Please enter your last name: ");
    String lastName = scan.nextLine();
    System.out.print("Please enter your phone number: ");
    String phoneNumber = scan.nextLine();
    System.out.print("Please enter your email: ");
    String email = scan.nextLine();
    contacts.add(new Contact(firstName, lastName, phoneNumber, email));
  }
  
  /** 
    * displayAll method: helper method that displays all the Contacts in ContactDatabase 
    * @return: none
    */
  public void displayAll(){
    for (Contact contact : contacts) {
      System.out.println(contact.toString());
    }
  }
  
  /** 
    * displayMatch method: helper method that displays all Contacts with the same input (only first name for now)
    * @return: none
    */
  public void displayMatch() {
    Scanner scan = new Scanner(System.in);
    System.out.print("Please enter your first name: ");
    String firstName = scan.nextLine();
    int flag = 0;
    for (Contact contact : contacts) {
      if (contact.getFirst().equalsIgnoreCase(firstName)) {
        System.out.println(contact.toString());
        flag = 1;
      }
    }
    if (flag == 0) {System.out.println("No contact found!");}
  }
  
  /** 
    * displayMatch method: helper method that deletes all Contacts with the same input input (only first name for now)
    * @return: none
    */
  public void deleteMatch() {
    Scanner scan = new Scanner(System.in);
    System.out.print("Please enter your first name: ");
    String firstName = scan.nextLine();
    int flag = 0;
    Iterator<Contact> itr = contacts.iterator();
    while (itr.hasNext()) {
      Contact ct = itr.next();
      if (ct.getFirst().equalsIgnoreCase(firstName)){
        itr.remove();
        System.out.print("removed name");
      }
      flag = 1;
    }
    if (flag == 0) {
      System.out.println("No contact found!");
    }
  }

  /** 
   * Instance Variables for main method 
   */
  private static final int QUIT = 0; 
  private static final int ADD = 1;
  private static final int LISTALL = 2;
  private static final int SEARCH = 3;
  private static final int DELETE = 4;

  /** 
   * Main method: Interactive script to add contacts, list contacts, 
   * search for contacts to display, and search for contacts to delete
   * @return: none
   * Run 
   */
  public static void main(String[] args){
    ContactDatabase cdb = new ContactDatabase();
    Scanner scan = new Scanner(System.in);
    int choice = ADD;
    // Main menu
    while (choice != QUIT){
      System.out.println();
      System.out.println("Choose from the following:");   
      System.out.println("0) Quit");   
      System.out.println("1) Add new contact");  
      System.out.println("2) List all contacts");   
      System.out.println("3) Search contacts by keyword and display");
      System.out.println("4) Search contacts by keyword and remove"); 
      choice = scan.nextInt();
      switch (choice) {
        case ADD:
          cdb.inputContact();
          break;  
        case LISTALL:
          cdb.displayAll();
          break;       
        case SEARCH:
          cdb.displayMatch();       
          break;    
        case DELETE:
          cdb.deleteMatch();  
          break;   
      }
    }
  }
}