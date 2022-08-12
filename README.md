# ProgrammingProject4

Programming Project 4

I. Overview
The purpose of this project is to practice using linked lists, array lists, generic types, iterators, and comparators.

II. Code Readability (20% of your project grade)
To receive the full readability marks, your code must follow the following guideline:

All variables (fields, parameters, local variables) must be given appropriate and descriptive names.
All variable and method names must start with a lowercase letter. All class names must start with an uppercase letter.
The class body should be organized so that all the fields are at the top of the file, the constructors are next, the non-static methods next, and the static methods at the bottom with the main method last.
There should not be two statements on the same line.
All code must be properly indented (see Appendix F of the Lewis book for an example of good style). The amount of indentation is up to you, but it should be at least 2 spaces, and it must be used consistently throughout the code.
You must be consistent in your use of {, }. The closing } must be on its own line and indented the same amount as the line containing the opening {.
There must be an empty line between each method.
There must be a space separating each operator from its operands as well as a space after each comma.
There must be a comment at the top of the file that is in proper JavaDoc format and includes both your name and a description of what the class represents. The comment should include tags for the author. (See Appendix J of the Lewis book of pages 226-234 if the Evans and Flanagan book.)
There must be a comment directly above each method (including constructors) that is in proper JavaDoc format and states what task the method is doing, not how it is doing it. The comment should include tags for any parameters, return values and exceptions, and the tags should include appropriate comments that indicate the purpose of the inputs, the value returned, and the meaning of the exceptions.
There must be a comment directly above each field that, in one line, states what the field is storing.
There must be a comment either above or to the right of each non-field variable indicating what the variable is storing. Any comments placed to the right should be aligned so they start on the same column. Variables that are only used as an index in a loop do not need a comment.
There must be a comment above each loop that indicates the purpose of the loop. Ideally, the comment would consist of any preconditions (if they exist) and the subgoal for the loop iteration.
Any code that is complicated should have a short comment either above it or aligned to the right that explains the logic of the code.
III. Program Testing (20% of your project grade)
You are to create JUnit tests. If you added additional methods to the linked list class (including methods you added in a lab), you should submit a separate JUnit test class for the linked list that tests those methods. You do not have to write a JUnit tests for the methods you did not write. For the other class or classes of this project, you may submit one or multiple JUnit test classes. It is your choice.

Your report should be a short document that explains why the JUnit tests you provide thoroughly test your program. However, you should not JUnit test the main method or methods that specifically read from a file or methods that write to the screen. For these methods, your testing report should describe the tests you did to judge the correctness of your program.

IV. Java Programming (60% of your grade)
Remember that in the homework, you will be writing JUnit test cases. A strong recommendation is to write your JUnit test cases at the same time as you complete each part of the program.

Very Important Programming Rules
The main purpose of this project is to gain practice using linked lists and arrays and writing code with generic types. For the arrays, you will use the ArrayList class from the Java API. The ArrayList class "wraps" an array with helpful methods and it allows us to use generic types. You must use ArrayList explicitly as arrays. Therefore you may not use the automatic resize feature of ArrayList (where it will create a new array and copy all the data over) but you must instead set its initial capacity appropriately. Likewise, you should not insert into the middle of the ArrayList in a way that requires all the data to be shifted over.

For the linked list, you have your choice of whether you want to use the LinkedList or the DoubleLinkedList class from class or the LinkedList class from the Java API. You must use the linked list explicitly as a linked list. You may not use code that is inefficient in a linked list even if the Java API optimizes that code. Therefore, you can not access a linked list element by its index. Instead, you may use methods like adding and removing from the ends, testing if a list is empty, or using an iterator for the list. Similarly, if you are using our linked list or double linked list class, you should not use or add inefficient helper methods.

The second purpose of the homework is to use generic types appropriately. Your code should not generate any warning messages when compiled. (If you are not using DrJava, be sure to have it set so your IDE shows generic type warnings.)
What you are to do
You may add any classes or methods to the following as you see fit in order to complete the given tasks.

This class will implement a generic database. This database will let you add and delete items, look up items, and sort by different fields. The database will be not be specific to any type of data so please note, you will need to use generic types, but the generic types are not specified in the instructions. Modern databases use a special datatype called a B-tree to store the data in a way that gives both fast access and relatively quick insertion and deletion. You will learn about B-trees in EECS 233. For this project, we will use arrays (actually ArrayLists) and linked lists get a similar behavior.

The database will store its data in a list of nodes (like the LLNode or the DLNode types from our course). This way we can quickly insert and (relatively) quickly delete.

In addition, we will create indexes into the data. Each of our indexes will be an ArrayList that stores the same elements as the nodes. (We are not actually duplicating the data since only addresses are stored.) Each index will be sorted according to some trait. Then, if we want to lookup data by some trait and we have an index for the trait available, we can use a faster binary search (already implemented in the API) that can be used on sorted arrays.

In this homework, you will use Scanner, Comparator, ArrayList and Hashtable from the Java API. For the LinkedList, you may use either the one from the API or one that we developed in the course. However, the linked list you use must implement Iterable.

Create the following classes:

DatabaseType: an interface that contains one method

Comparator getComparatorByTrait(String trait) where Comparator is an interface in java.util.

Database: a class that limits the types it can store to DatabaseTypes. The database will store the data in nodes, just like a linked list. The database will also let the user create an index for the database. An index is a sorted array (or in our case, a sorted ArrayList) of the data so that searches can happen quickly. The Database class should have the following fields:

A node (something like LLNode or DLNode) that indicates the start of the list of nodes that store the data in the database.

A Hashtable that uses String as the key and ArrayList as the value. The hashtable will store the different indexes available to fast sort the data.

an int that stores the number of elements being stored in the database.

The Database should have the following methods:
void add takes an element add adds it to the database by creating a node for the data and attaching it to the list of nodes. The hashtable is cleared because all indexes stored in it are no longer valid. The add method should work very quickly, and so no traversal of the nodes is allowed.

void delete takes an element and deletes the element from the list of nodes. If the element occurs more than once in the list, all copies should be deleted. If anything is deleted, the hashtable is cleared because the indexes are no longer valid. You may use linear search (the normal for-loop search) to find the element to delete.

int size returns the number of elements currently stored in the database (use the field and do not write a loop).

LinkedList lookupInList takes a value and a Comparator and returns a LinkedList that contains all elements in the database that match the input value. Do a linear search (i.e. for loop) on the database's list of nodes, use the Comparator to identify all elements in the list of nodes that match the input value, and add those values to the LinkedList. If no such elements exists, an empty LinkedList is returned. Note that the type of the input value will match the type of the elements in the database.

ArrayList getList(String trait): If an index for trait exists in the hashtable, return it. Otherwise, call makeIndex with the trait to create the index and return that index. (Note that if the database is empty, the method should return an empty ArrayList.)

Contact: a class that represents a phone/email contact. The Contact class should implement the DatabaseType interface. A contact should have three Strings, a name, a phone number, and a e-mail. You should have the appropriate constructor as well as getter and setter methods. In addition, you should have the following methods:

String toString overriding the method of Object

boolean equals overriding the method of Object. Two contacts should be equal if all fields are equal.

Comparator getComparatorByTrait(String trait): if the trait is "name", the method should return a Comparator that compares two Contacts by the name field. If the trait is "phone", the method should return a Comparator that compares two Contacts by the phone number field. If the trait is "email", the method should return a Comparator that compares two Contacts by the e-mail field. Otherwise, the method should return null.

Extra Credit (10 pts)
Add following methods to the Database class:
LinkedList lookupInIndex takes a value, an ArrayList index, and a Comparator and returns a LinkedList that contains all elements of the database that match the input value. You may assume the ArrayList is already sorted according to the input Comparator. Do a binary search on the ArrayList using the Comparator to find one element that matches the input value. (I recommend using the binary search from the Java API Collections but you are welcome write your own.) Then, using the Comparator, find all elements in the ArrayList that matches the input value and place them into a LinkedList. (Remember, the ArrayList is sorted so you can do this quickly.) If no such elements exist, return an empty LinkedList. Note that the type of the input value will match the type stored in the database.

void makeIndex(String trait): If the database is empty, this method does nothing. Otherwise, since the database contains only elements that are DatabaseTypes, call the getComparatorByTrait(trait) method, using the trait to retrieve a Comparator. Create an ArrayList and copy the addresses of the elements of the database into the ArrayList. Sort the ArrayList using the Comparator, and store the ArrayList into the hashtable using the trait as the key. (You may use a sort from the Java API Collections or ArrayList, or you may write your own, but if you do your own sort, it must be efficient.)

LinkedList lookup takes a String trait and a value. If there exists an index in the hashtable that has a key that matches the input trait, call lookupInIndex with the given value, the ArrayList index, and the appropriate Comparator for the trait. (You can get the Comparator by calling the value's getComparatorByTrait method.). Otherwise, if there does not exist an index in the hastable for the input trait, call lookupInList with the given value and the appropriate Comparator for the trait. Return the LinkedList returned by these methods. (Note, if the database is empty, this method should return an empty LinkedList.)

Create a Contact Data Base class with following details.
ContactDatabase: a ContactDatabase is a Database that stores Contacts. The class should have the following methods:

printList: takes an Iterable and prints the contents of the Iterable using System.out. Each entry should go on its own line, and each line should be numbered, starting at 1.

main: The main method should do the following, either inside the method or in helper methods. It should create a ContactDatabase and a Scanner object that reads from System`.in. You should have a loop that reads use input using Scanner.next() or Scanner.nextInt(). The loop does the following:

If the user enters "quit", exit the program.
If the user enters "add", create a new Contact with the next three strings entered by the user, and add the Contact to the database. (For example, the user could type "add Harold 368-5877 hsc21" and a new Contact("Harold", "368-5877", "hsc21") is added to the database.)
If the user enters "listby", the next input will be the trait. Then call the getList method with the trait (assuming it is valid), and call the printList method with the returned list. (For example, the user could type "listby name" and all the contacts in the database we be listed in order of the name.)
If the user enters "find", the next two user inputs will be a trait and a value. (For example, "find name Harold" or "find email hsc21".) Create a new Contact with the given value (set the other values to null), call the lookup method with the trait and the Contact, and call printList with the returned list.
If the user enters "delete", the next input will be a number. (For example "delete 2".) Then, the element that was at that number in the last printed list is deleted from the database. (Note that you will need to remember the last list returned by either "find" or "listby".)
If the user enters "makeindex", the next input will be a trait, and the makeIndex method will be called with that trait.
If the user enters an invalid command, print an appropriate message to the user.
