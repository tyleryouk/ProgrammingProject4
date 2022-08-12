/** 
 * DatabaseTester class: Tester for both Database and DatabaseType
 * @author: Tyler Youk 
 */

import java.util.*;
import java.util.ArrayList;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.LinkedList;



public class DatabaseTester{
  
  @Test
  public void testDatabaseMethods(){
    
    
    Database<Contact> poolDatabase = new Database<Contact>(); //Building a database of Contacts
    Contact tyler = new Contact("Tyler","Youk","7388","l@gmail.com");
    Contact runn = new Contact("Runn","Youk","7388","l@gmail.com");
    Contact morph = new Contact("Morph","Ju","790","morph@gmail.com");
    Contact ocean = new Contact("Ocean","Tu","790","trap@gmail.com");
    
    /** Testing: 1. add method */
    poolDatabase.add(tyler); //Adding one element in Database
    LinkedList<Contact> poolLinkedList = poolDatabase.getData(); //Creating a LinkedList of one contact
    assertEquals("[Youk, Tyler. 7388, l@gmail.com]", poolLinkedList.toString()); //Test 1 (Using LinkedList API toString method)
    assertEquals(1.0, poolLinkedList.size(), 0.1); //Test 1 (Using LinkedList API size method)
    poolDatabase.add(morph); //Adding a second element to Database
    poolLinkedList = poolDatabase.getData(); //Updating LinkedList to two contacts
    assertEquals("[Youk, Tyler. 7388, l@gmail.com, Ju, Morph. 790, morph@gmail.com]", poolLinkedList.toString()); //Test many 
    assertEquals(2.0, poolLinkedList.size(), 0.1); //Test many 
    
    /** Testing: 2. void delete: takes an element and deletes the element from the list of nodes. If the element occurs more than once in the list, all copies should be deleted. 
    * If anything is deleted, the hashtable is cleared because the indexes are no longer valid. You may use linear search (the normal for-loop search) to find the element to delete. */
    poolDatabase.delete(morph); //back to one element in LinkedList, deleting morph, only Tyler left
    poolLinkedList = poolDatabase.getData(); //creating a LinkedList of Contacts based on the poolDatabase of Contacts
    assertEquals("[Youk, Tyler. 7388, l@gmail.com]", poolLinkedList.toString()); //Test 1 
    assertEquals(1.0, poolLinkedList.size(), 0.1); //Test 1
    poolDatabase.delete(tyler); //No elements in Database
    poolLinkedList = poolDatabase.getData(); //No elements in LinkedList
    assertEquals("[]", poolLinkedList.toString()); //Test 0
    assertEquals(0.0, poolLinkedList.size(), 0.1); //Test 0
    
    /**Testing: 3. int size: returns the number of elements currently stored in the database (use the field and do not write a loop). */
    poolDatabase.delete(tyler);
    assertEquals(0, poolDatabase.size(), 0.1); //Test 0
    poolDatabase.add(tyler);
    assertEquals(1, poolDatabase.size(), 0.1); //Test 1
    poolDatabase.add(runn);
    poolDatabase.add(morph); //size now 3
    assertEquals(3, poolDatabase.size(), 0.1); //Test many
    
    /**Testing: 4. LinkedList lookupInList: takes a value and a Comparator and returns a LinkedList that contains all elements in the database that match the input value. 
      * Do a linear search (i.e. for loop) on the database's list of nodes, use the Comparator to identify all elements in the list of nodes that match the input value, and add those values to the LinkedList. 
      * If no such elements exists, an empty LinkedList is returned. Note that the type of the input value will match the type of the elements in the database. */
    LinkedList<Contact> poolLinkedList2;
    poolLinkedList2 = poolDatabase.lookupInList(tyler, tyler.getComparatorByTrait("name")); //which other people have the same name as Contact tyler 
    assertEquals("[Youk, Tyler. 7388, l@gmail.com]",poolLinkedList2.toString()); //no one else has the same name as Contact tyler
    Contact tyler2 = new Contact("Tyler","Youk","73889","none");
    poolDatabase.add(tyler2); //adding a new contact with the same name
    poolLinkedList2 = poolDatabase.lookupInList(tyler, tyler.getComparatorByTrait("name")); //which other people have the same name as Contact tyler 
    assertEquals("[Youk, Tyler. 7388, l@gmail.com, Youk, Tyler. 73889, none]",poolLinkedList2.toString()); //tyler2 and tyler have the same name
    poolLinkedList2 = poolDatabase.lookupInList(tyler, tyler.getComparatorByTrait("email")); //which other people have the same email as Contact tyler 
    assertEquals("[Youk, Tyler. 7388, l@gmail.com, Youk, Runn. 7388, l@gmail.com]",poolLinkedList2.toString()); //runn and tyler have the same email
    poolLinkedList2 = poolDatabase.lookupInList(tyler, tyler.getComparatorByTrait("phone")); //which other people have the same phone as Contact tyler 
    assertEquals("[Youk, Tyler. 7388, l@gmail.com, Youk, Runn. 7388, l@gmail.com]",poolLinkedList2.toString()); //runn and tyler have the same phone number
    
    //extra tests
    LinkedList<Contact> poolLinkedList3; //NOTE poolDatabase currently has 4 elements: tyler, morph, runn, and tyler2
    poolLinkedList3 = poolDatabase.lookupInList(morph, morph.getComparatorByTrait("name")); //which other people have the same name as Contact morph
    assertEquals("[Ju, Morph. 790, morph@gmail.com]",poolLinkedList3.toString()); //no other people have the same name
    poolLinkedList3 = poolDatabase.lookupInList(morph, morph.getComparatorByTrait("email")); //which other people have the same name as Contact morph
    assertEquals("[Ju, Morph. 790, morph@gmail.com]",poolLinkedList3.toString()); //no other people have the same email
    poolLinkedList3 = poolDatabase.lookupInList(morph, morph.getComparatorByTrait("phone")); //which other people have the same name as Contact morph
    assertEquals("[Ju, Morph. 790, morph@gmail.com]",poolLinkedList3.toString()); //no other people have the same phone
    poolDatabase.add(ocean); //Morph was lonely, so we added a new Contact Ocean who has a similar trait
    poolLinkedList3 = poolDatabase.lookupInList(morph, morph.getComparatorByTrait("phone")); //which other people have the same name as Contact morph
    assertEquals("[Ju, Morph. 790, morph@gmail.com, Tu, Ocean. 790, trap@gmail.com]",poolLinkedList3.toString()); //ocean amd morph have the same phone number

    
    /** Testing: 5. ArrayList getList(String trait): If an index for trait exists in the hashtable, return it. 
      * Otherwise, call makeIndex with the trait to create the index and return that index. 
      * (Note that if the database is empty, the method should return an empty ArrayList.) */
    Database<Contact> emptyDatabase = new Database<Contact>(); //Building a database of Contacts
    ArrayList<Contact> emptyArrayList; 
    emptyArrayList = emptyDatabase.getList("name");
    assertEquals("[]",emptyArrayList.toString()); //"(Note that if the database is empty, the method should return an empty ArrayList.)" Test 0
    
    Contact noName = new Contact(null, null, "89", "0"); //adding contact with no name
    emptyDatabase.add(noName);
    emptyArrayList = emptyDatabase.getList("name");
    assertEquals("[null, null. 89, 0]",emptyArrayList.toString()); //Created an index with one Contact
    
    ArrayList<Contact> poolArrayList; //NOTE poolDatabase currently has 5 elements: tyler, morph, runn, tyler2, and ocean
    assertEquals(5, poolDatabase.size(), 0.1); //Confirming number of elements in poolDatabase
    poolArrayList = poolDatabase.getList("name"); //should return an index of PoolDatabase in alphabetical order by name
    assertEquals("[Ju, Morph. 790, morph@gmail.com, Tu, Ocean. 790, trap@gmail.com, Youk, Runn. 7388, l@gmail.com, Youk, Tyler. 7388, l@gmail.com, Youk, Tyler. 73889, none]", poolArrayList.toString()); 
    poolArrayList = poolDatabase.getList("email"); //should return an index of PoolDatabase in alphabetical order by email
    assertEquals("[Youk, Tyler. 7388, l@gmail.com, Youk, Runn. 7388, l@gmail.com, Ju, Morph. 790, morph@gmail.com, Youk, Tyler. 73889, none, Tu, Ocean. 790, trap@gmail.com]", poolArrayList.toString());
    poolArrayList = poolDatabase.getList("phone"); //should return an index of PoolDatabase sorted by phone number (7388, 7388, 73889, 790, 790) (790 is last since we are comparing digit by digit, 600 would be first and 800 would be last);
    assertEquals("[Youk, Tyler. 7388, l@gmail.com, Youk, Runn. 7388, l@gmail.com, Youk, Tyler. 73889, none, Ju, Morph. 790, morph@gmail.com, Tu, Ocean. 790, trap@gmail.com]", poolArrayList.toString());
    
    /** EXTRA CREDIT Testing: 
      * 1. LinkedList lookupInIndex(T element, String index, Comparator<T> comparator): 
      * takes a value, an ArrayList index, and a Comparator and returns a LinkedList that contains all elements of the database that match the input value */
    LinkedList<Contact> extraCreditList = new LinkedList<Contact>(); //creating a LinkedList of poolDatabase (NOTE: currently has 4 elements: tyler, morph, runn, tyler2, and ocean)
    assertEquals(5, poolDatabase.size(), 0.1); //Confirming number of elements in poolDatabase
    extraCreditList = poolDatabase.lookupInIndex(tyler, "name", tyler.getComparatorByTrait("name")); //Should return a LinkedList of all elements with the name tyler
    assertEquals("[Youk, Tyler. 7388, l@gmail.com, Youk, Tyler. 73889, none]",extraCreditList.toString()); //Same name as Tyler: Tyler & Tyler2
    extraCreditList = poolDatabase.lookupInIndex(tyler, "phone", tyler.getComparatorByTrait("phone")); 
    assertEquals("[Youk, Tyler. 7388, l@gmail.com, Youk, Runn. 7388, l@gmail.com]",extraCreditList.toString()); //Same phone number as Tyler: Tyler & Runn
    extraCreditList = poolDatabase.lookupInIndex(morph, "phone", morph.getComparatorByTrait("phone")); 
    assertEquals("[Ju, Morph. 790, morph@gmail.com, Tu, Ocean. 790, trap@gmail.com]",extraCreditList.toString()); //Same Phone numbers as Morph: Morph and Ocean
    extraCreditList = poolDatabase.lookupInIndex(morph, "email", morph.getComparatorByTrait("email")); 
    assertEquals("[Ju, Morph. 790, morph@gmail.com]",extraCreditList.toString()); //Same email as Morph: Morph 
    
    /** 
     * Testing: 2. void makeIndex(String trait): 
     * @return none database contains only elements that are DatabaseTypes, call the getComparatorByTrait(trait) method, using the trait to retrieve a Comparator. 
      * Create an ArrayList and copy the addresses of the elements of the database into the ArrayList. Sort the ArrayList using the Comparator, and store the ArrayList into the hashtable using the trait as the key. */
    Database<Contact> extraCreditDatabase = new Database<Contact>(); // makeIndex is used in lookupInList
    Hashtable<String,ArrayList<Contact>> ourHash = new Hashtable<String,ArrayList<Contact>>(); //Will mirror extraCreditDatabase's Hashtable
    /** makeIndex only changes a Database's Hashtable */
    
    extraCreditDatabase.makeIndex("name");
    assertEquals(0, extraCreditDatabase.size()); //Test 0
    extraCreditDatabase.makeIndex("name");
    ourHash = extraCreditDatabase.getIndexHash(); 
    assertEquals("{name=[]}", ourHash.toString()); //Test 0
    
    Contact carrot = new Contact("Carrot", "Junior", "8083339933", "rondo@gmail.com");
    extraCreditDatabase.add(carrot);
    assertEquals(1, extraCreditDatabase.size()); //Test 1
    extraCreditDatabase.makeIndex("name"); //Running makeIndex method which will update the Database's Index Hash by name
    ourHash = extraCreditDatabase.getIndexHash(); //Updating ourHash with the Database's Index Hash
    assertEquals("{name=[Junior, Carrot. 8083339933, rondo@gmail.com]}", ourHash.toString()); //Test 1
    extraCreditDatabase.clearIndexHash(); //Clearing Index Hash
    extraCreditDatabase.makeIndex("phone"); 
    ourHash = extraCreditDatabase.getIndexHash(); //Updating ourHash with the Database's newly sorted Index Hash by phone number
    assertEquals("{phone=[Junior, Carrot. 8083339933, rondo@gmail.com]}", ourHash.toString()); //Test 1

    Contact crayon = new Contact("Crayon", "Pons", "7083339933", "levi@gmail.com");  
    Contact myron = new Contact("Myron","Raynes","7034493393","snondo@yahoo.com");
    Contact lore = new Contact("Lore","Craynes","7034493393","snondo@yahoo.com");
    Contact trom = new Contact("Trom","Flaurs","9999999999","fronto@yahoo.com");
    extraCreditDatabase.add(crayon);
    assertEquals(2, extraCreditDatabase.size()); //Test many
    extraCreditDatabase.clearIndexHash(); //Clearing Index Hash
    extraCreditDatabase.makeIndex("phone"); 
    ourHash = extraCreditDatabase.getIndexHash(); //Updating ourHash with the Database's newly sorted Index Hash by phone number
    assertEquals("{phone=[Pons, Crayon. 7083339933, levi@gmail.com, Junior, Carrot. 8083339933, rondo@gmail.com]}", ourHash.toString()); //Correctly sorted via Phone Number
    extraCreditDatabase.add(myron);
    extraCreditDatabase.add(lore);
    extraCreditDatabase.add(trom); //adding three more contacts to truly Test Many
    assertEquals(5, extraCreditDatabase.size()); //A true Test Many
    extraCreditDatabase.clearIndexHash(); //Clearing Index Hash
    extraCreditDatabase.makeIndex("phone"); 
    ourHash = extraCreditDatabase.getIndexHash(); //Updating ourHash with the Database's newly sorted Index Hash by phone number
    assertEquals("{phone=[Raynes, Myron. 7034493393, snondo@yahoo.com, Craynes, Lore. 7034493393, snondo@yahoo.com, Pons, Crayon. 7083339933, levi@gmail.com, Junior, Carrot. 8083339933, rondo@gmail.com, Flaurs, Trom. 9999999999, fronto@yahoo.com]}"
                   , ourHash.toString()); //Correctly sorted via Phone Number
    extraCreditDatabase.clearIndexHash(); 
    extraCreditDatabase.makeIndex("name"); 
    ourHash = extraCreditDatabase.getIndexHash(); 
    assertEquals("{name=[Junior, Carrot. 8083339933, rondo@gmail.com, Pons, Crayon. 7083339933, levi@gmail.com, Craynes, Lore. 7034493393, snondo@yahoo.com, Raynes, Myron. 7034493393, snondo@yahoo.com, Flaurs, Trom. 9999999999, fronto@yahoo.com]}"
                   , ourHash.toString()); //Correctly sorted via Name (First Name compared first, then last name)
    extraCreditDatabase.clearIndexHash();
    extraCreditDatabase.makeIndex("email"); 
    ourHash = extraCreditDatabase.getIndexHash(); 
    assertEquals("{email=[Flaurs, Trom. 9999999999, fronto@yahoo.com, Pons, Crayon. 7083339933, levi@gmail.com, Junior, Carrot. 8083339933, rondo@gmail.com, Raynes, Myron. 7034493393, snondo@yahoo.com, Craynes, Lore. 7034493393, snondo@yahoo.com]}"
                   , ourHash.toString()); //Correctly sorted via Email (First Name compared first, then last name)
    
   /** Testing: 3.LinkedList lookup: takes a String trait and a value. */
    LinkedList<Contact> comeRunUpLL = new LinkedList<Contact>();
    Database<Contact> runners = new Database<Contact>();
    Contact daveMoney = new Contact("DaveMoney", "Cruise", "8237778390", "noemail@gmail.com");  
    Contact lookDown = new Contact("Unique", "Morton", "0003893987", "tons@mail.com");  
    Contact realCurrency = new Contact("Currency", "None", "8237778390", "noemail@gmail.com");  
    Contact realCurrencyTwo = new Contact("Currency", "None", "8237778390", "noemail@gmail.com"); 
    
    comeRunUpLL = runners.lookup("name", daveMoney); //will return an empty LinkedList, since there are no Contacts in Database
    assertEquals("[]",comeRunUpLL.toString()); 
                 //Test 0: no Contacts
    
    runners.add(daveMoney);
    runners.add(lookDown);
    runners.add(realCurrency);
    runners.add(realCurrencyTwo);
    comeRunUpLL = runners.lookup("name", daveMoney); //will return a LinkedList of all Contacts with the same name as daveMoney 
    assertEquals("[Cruise, DaveMoney. 8237778390, noemail@gmail.com]",comeRunUpLL.toString()); 
                 //Test 1: daveMoney has the same name as daveMoney
    comeRunUpLL = runners.lookup("phone", daveMoney); 
    assertEquals("[Cruise, DaveMoney. 8237778390, noemail@gmail.com, None, Currency. 8237778390, noemail@gmail.com, None, Currency. 8237778390, noemail@gmail.com]",comeRunUpLL.toString()); 
                 //Test many: daveMoney has the same phone as daveMoney, realCurrency, and realCurrency2
    comeRunUpLL = runners.lookup("phone", realCurrency); 
    assertEquals("[Cruise, DaveMoney. 8237778390, noemail@gmail.com, None, Currency. 8237778390, noemail@gmail.com, None, Currency. 8237778390, noemail@gmail.com]",comeRunUpLL.toString()); 
                 //Test many: realCurrency has the same phone as daveMoney, realCurrency, and realCurrency2
    comeRunUpLL = runners.lookup("email", lookDown); 
    assertEquals("[Morton, Unique. 0003893987, tons@mail.com]",comeRunUpLL.toString()); 
                 //Test 1: lookDown has the same email as lookDown
    comeRunUpLL = runners.lookup("email", realCurrencyTwo); 
    assertEquals("[Cruise, DaveMoney. 8237778390, noemail@gmail.com, None, Currency. 8237778390, noemail@gmail.com, None, Currency. 8237778390, noemail@gmail.com]",comeRunUpLL.toString()); 
                 //Test many: lookDown has the same email as lookDown
  }
  
  @Test
  public void testDatabaseType(){
     /** 
      * Testing: Comparator getComparatorByTrait(String trait) 
      * where Comparator is an interface in java.util. 
      * @return: a comparator that accuraterly compares Contacts by a specific trait
      */

    
    //test Comparator<Contact> function
    Contact simpOne = new Contact("simp", "nope", "0013390053", "simp@gmail.com");
    Contact simpTwo = new Contact("simp", "nope", "0013390054", "simp@gmail.com");
    Contact simpTwoExtra = new Contact("Ha", "extra", "0013390054", "simpextra@gmail.com");
    Contact simpThree = new Contact("simp", "nope", "9240083943", "simp@gmail.com"); //all "Simps" have the same Contact fields, only with different phone numbers

    Comparator<Contact> simpNameComparator = simpOne.getComparatorByTrait("name"); //returns a comparator that compares names
    Comparator<Contact> simpPhoneComparator = simpOne.getComparatorByTrait("phone"); //returns a comparator that compares names
    Comparator<Contact> simpEmailComparator = simpOne.getComparatorByTrait("email"); //returns a comparator that compares names
    LinkedList<Contact> simpLL = new LinkedList<Contact>();
    Database<Contact> simpDatabase = new Database<Contact>();
    simpDatabase.add(simpOne);
    simpDatabase.add(simpTwo);
    simpDatabase.add(simpTwoExtra);
    simpDatabase.add(simpThree);
    
    Database<Contact> emptyDatabase = new Database<Contact>();
    LinkedList<Contact> emptyLL = new LinkedList<Contact>();
    emptyLL = emptyDatabase.lookupInIndex(simpOne, "name", simpNameComparator);
    assertEquals("[]", emptyLL.toString());
                //Test 0: no Contacts to test Comparator with, therefore returns empty LinkedList (ensuring it runs without error)
    
    simpLL = simpDatabase.lookupInIndex(simpOne, "name", simpNameComparator);
    assertEquals("[nope, simp. 0013390053, simp@gmail.com, nope, simp. 0013390054, simp@gmail.com, nope, simp. 9240083943, simp@gmail.com]", simpLL.toString());
                //Test many: simpOne has the same name as simpOne, simpTwo, and simpThree
    simpLL = simpDatabase.lookupInIndex(simpOne, "phone", simpPhoneComparator);
    assertEquals("[nope, simp. 0013390053, simp@gmail.com]", simpLL.toString());
                //Test 1: simpOne has the same phone as simpOne
    simpLL = simpDatabase.lookupInIndex(simpOne, "email", simpEmailComparator);
    assertEquals("[nope, simp. 0013390053, simp@gmail.com, nope, simp. 0013390054, simp@gmail.com, nope, simp. 9240083943, simp@gmail.com]", simpLL.toString());
                //Test many: simpOne has the same name as simpOne, simpTwo and simpThree

  }
 
}
