/** 
 * Contact class: represents a phone/email contact. The Contact class should implement the DatabaseType interface.
 * @author: Tyler Youk 
 */


import java.util.*;

public class Contact implements DatabaseType<Contact>{
  
  /** 
   * Four instance fields of type String (splitting up name into first and last).
   * Comparator will still read name from start of first name to end of last name 
   */
  private String first, last, phone, email; 
  
  /** 
    * Basic Constructor for Contact
    * @param none
    */
  public Contact(){
    this.first = null;
    this.last = null;
    this.phone = null;
    this.email = null;}
  
  /** 
    * Constructor with all parameters
    * @param first: String first name
    * @param last: String last name
    * @param phone: String phone number
    * @param email: String email
    */
  public Contact(String first, String last, String phone, String email){
    this.first = first;
    this.last = last;
    this.phone = phone;
    this.email = email; }

  /** 
    * Getter methods: to retrieve data
    * @return: String of desired variable
    */
  public String getFirst(){return this.first;}
  public String getLast(){return this.last;}
  public String getPhone(){return this.phone;}
  public String getEmail(){return this.email;}
  
  /** 
   * Setter methods: to set data
   * @param: String of variable that will replace the previous field
   */
  public void setFirst(String first){this.first = first;}
  public void setLast(String last){this.last = last;}
  public void setPhone(String phone){this.phone = phone;}
  public void setEmail(String email){this.email = email;}

  /** 
   * toString method: overrides the method of Object
   * @return: String of all fields in the following format: "last, first. phone, email"
   */
  @Override
  public String toString(){
    return last + ", " + first + ". " + phone + ", " + email;}
  
  /** 
   * equals method: overrides the method of Object. Two contacts should be equal if all fields are equal.
   * @param Object otherObject: Object to check against
   * @return boolean based on if all fields are equals 
   */
  @Override
  public boolean equals(Object otherObject) {
    if (otherObject == null) {return false;}
    else if (getClass() != otherObject.getClass()){return false;}
    else{
      Contact otherContact = (Contact) otherObject;
      return (first.equals(otherContact.first) &&
              last.equals(otherContact.last) &&
              phone.equals(otherContact.phone) &&
              email.equals(otherContact.email));}
    }
  
  /** 
   * Comparator getComparatorByTrait(String trait): Compares Contact based on Trait
   * @param String trait: String of the following: name, phone, and email
   * @return Comparator<Contact>: Compares the two Contacts by the trait
   */
  public Comparator<Contact> getComparatorByTrait(String trait){
    if(trait.equals("name")){
      return new CompareName();
    }  //if first name equals input 
   else if(trait.equals("phone")){
     return new ComparePhone();
   }  //if last name equals input
   else if(trait.equals("email")){
     return new CompareEmail();
   }  //if first name equals input 
   else {return null;}
  }
  
  /** 
   * Nested Class: Helper classes for the getComparatorByTrait method
   * @author: Tyler Youk
   */
  class CompareName implements Comparator<Contact>{
    public CompareName(){}
    public int compare(Contact a, Contact b){
    String a_name = a.getFirst();
    String b_name = b.getFirst();
    return a_name.compareTo(b_name) ;}}
  
  class CompareEmail implements Comparator<Contact>{
    public CompareEmail(){}
    public int compare(Contact a, Contact b){
      return a.getEmail().compareTo(b.getEmail());}}
  
  class ComparePhone implements Comparator<Contact>{
    public ComparePhone(){}
    public int compare(Contact a, Contact b){
      return a.getPhone().compareTo(b.getPhone());}}
  
}




