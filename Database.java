/** 
 * Database class: A database to store the data in nodes using LinkedList<T>
 * @author: Tyler Youk 
 */

import java.util.*;
import java.util.Collections;

public class Database<T extends DatabaseType<T>>{

  /** 
   * Instance Fields:
   * 1. A node (something like LLNode or DLNode): that indicates the start of the list of nodes that store the data in the database.
   * 2. A Hashtable: that uses String as the key and ArrayList as the value. The hashtable will store the different indexes available to fast sort the data.
   * 3. An int: that stores the number of elements being stored in the database
   */
 
  private LinkedList<T> list = new LinkedList<T>();
  private Hashtable<String,ArrayList<T>> indexHash = new Hashtable<String,ArrayList<T>>();
  private int numElementsInDatabase = 0;
  
  /** Constructors: */
  public Database() {
    //
  }

  /** 
   * Methods: 
   * 1. void add
   * takes an element add adds it to the database by creating a node for the data and attaching it to the list of nodes. 
   */
  public void add(T element) {
    list.add(element);
    indexHash.clear(); }

  /** 
   * 2. void delete
   * takes an element and deletes the element from the list of nodes. If the element occurs more than once in the list, all copies should be deleted. 
   */
  public void delete(T element) {
    boolean changeCheck = false;
    LinkedList<T> tempList = new LinkedList<T>();
    ListIterator<T> iter = list.listIterator();
    while(iter.hasNext()) {
      T tempElement = iter.next();
      if(tempElement.toString().equals(element.toString())) {changeCheck = true;}
      else {tempList.add(tempElement);}}
    if(changeCheck) {
      indexHash.clear();
      list = tempList;} }
  
  /** 
    * 3. int size 
    * @return the number of elements currently stored in the database (use the field and do not write a loop). 
    */
  public int size(){ return list.size(); }

  /**
   * 4. LinkedList lookupInList 
   * @return a LinkedList that contains all elements in the database that match the input value. 
   */
  public LinkedList<T> lookupInList(T element, Comparator<T> filter) { 
    ListIterator<T> iter = list.listIterator();
    LinkedList<T> returnList = new LinkedList<T>();
    while(iter.hasNext()) {
      T tempElement = iter.next();
      if(filter.compare(element,tempElement) == 0) {
        returnList.add(tempElement);}
    }
    return returnList;}
  public LinkedList<T> lookupInList(T element, String index, Comparator<T> comparator) {
    LinkedList<T> returnList = new LinkedList<T>();
    ArrayList<T> aList = new ArrayList<T>();
    return returnList;}
  
  /**
   * 5. ArrayList getList(String trait)
   * @return an index for trait exists in the hashtable, return it. 
   */
  public ArrayList<T> getList(String trait) {
    if(indexHash.get(trait) == null) {
      makeIndex(trait);
      return indexHash.get(trait);}
    else {return indexHash.get(trait);}}
  
  /** EXTRA CREDIT */ 
  /** 
   * Methods: 
   * 1. LinkedList lookupInIndex
   * @param takes a value, an ArrayList index, and a Comparator 
   * @returns a LinkedList that contains all elements of the database that match the input value. 
   */
  public LinkedList<T> lookupInIndex(T element, String index, Comparator<T> comparator) {
    ArrayList<T> aList = new ArrayList<T>();
    int intialMatch = -1;
    int firstMatch = 0;
    int lastMatch = 0;
    makeIndex(index);
    aList = indexHash.get(index);
    
    if(aList.size() == 0){
      return new LinkedList<T>();}
    intialMatch = binarySearch(element, aList, comparator);
    firstMatch = intialMatch;
    lastMatch = intialMatch;
    if(intialMatch == -1)
      return new LinkedList<T>();
    while(firstMatch > 0 && comparator.compare(aList.get(firstMatch - 1), element) == 0){
      firstMatch -= 1;
    }
    while(lastMatch < aList.size() && comparator.compare(aList.get(lastMatch), element) == 0){
      lastMatch += 1;
    }
    return arrayListPart(aList,firstMatch,lastMatch);}
  
  
  /** 
   * 2. void makeIndex(String trait)
   * Retrieve a Comparator then create an ArrayList and copy the addresses of the elements of the database into the ArrayList. 
   */
  public void makeIndex(String trait) {
    ArrayList<T> aList = new ArrayList<T>();
    ListIterator<T> iter = list.listIterator();
    while(iter.hasNext()) {
      aList.add(iter.next());}
    
    if(!list.isEmpty()) {
      Comparator<T> comparator = aList.get(0).getComparatorByTrait(trait);
      Collections.sort(aList,comparator);}
    indexHash.put(trait,aList);}
  
  /** 
   * 3. LinkedList lookup
   * @param a trait and a value
   * @return LinkedList of T with the same trait
   */
  public LinkedList<T> lookup(String trait, T element) {
    LinkedList<T> returnList = new LinkedList<T>();
    ArrayList<T> aList = new ArrayList<T>();
    try{
      return lookupInIndex(element, trait, element.getComparatorByTrait(trait));}
    catch (Exception e) {
      return lookupInList(element,element.getComparatorByTrait(trait));}}


  
  /** EXTRA HELPER METHODS */
  /** 1. int binarySearch: helper methodto find a spot where a match occurs in an array list */
  private int binarySearch(T element, ArrayList<T> aList,Comparator<T> comparator) {
    int front = 0;
    int end = aList.size() - 1;;
    while(front <= end) {
      int middle = (end + front)/2;
      int compareValue = comparator.compare(element,aList.get(middle));
      if(compareValue == 0) {
        return middle; }
      else if(compareValue > 0)
        front = middle + 1;
      else
        end = middle - 1; }
    return -1;}
  
  /** 2. getData: method made for testing purposes*/
  public LinkedList<T> getData() {
    return list;
  }
  /** 3. getIndexHash: method made for testing purposes*/
  public Hashtable<String,ArrayList<T>>  getIndexHash() {return indexHash;}
  
  /** 4 clearIndexHash: method made for testing purposes*/
  public void  clearIndexHash() {indexHash.clear();}
  
  /** 5. arrayListPart: helper method to  construct a linked list of matches for lookupInIndex*/
  private LinkedList<T> arrayListPart(ArrayList<T> aList, int start, int end) {
    LinkedList<T> returnList = new LinkedList<T>(); //creating a list filtered with elements from array list
    System.out.println("test3");
    for(int i = start; i < end; i++) {
      returnList.add(aList.get(i));}
    System.out.println("test4");
    return returnList;}
  
}

