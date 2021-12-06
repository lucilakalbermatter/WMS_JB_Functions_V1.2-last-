# Functions

## Topics covered

Functions, arguments & parameters, scope, recursion and decorators.

## Goal achieved

By the end of the exercise you will have a better structured code that will be easier to maintain and further improve. You will also add some more features to your command line query tool.

More specifically, you will add the following features:

- Extended sessions
- List of actions in a session
- User system

## Data


For the new features you will need some new data, so that you can test your code.

Copy the files `sample/stock.json` and `sample/personnel.json` inside the `data` directory.

The `stock.json` file has a list of items from 4 different warehouses.
The `personnel.json` file has a list with the user names and passwords of the employees.


Make sure `src/main/java` is marked as a **Sources Root** folder. Copy the `Item`, `Person`, `StockRepostiory` and
the `PersonnelRepository` classes as well into your project. The old `Repository` class needs to be deleted.

The `Item` class is a **model class** that represents an item from the `stock.json`
file.

The `Person` class is a **model class** that represents a person from the `personnel.json`
file.

The `StockRepository` class contains a **static block code** that loads every entry from `stock.json`,
model it into the `Item` class and then populate the `List<Item> ITEM_LIST` with the items.

The `PersonnelRepository` class contains a **static block code** that loads every entry from `personnel.json`,
model it into the `Person` class and then populate the `List<Item> PERSON_LIST` with the persons.

> **Hint:** If you're getting an error on the `*Reposity` classes due to the classes from
> `org.json.simple`, make sure the `json-simple-1.1.1.jar` file is present on your **classpath**.




## Description

The tool you created so far in this project has become very useful to the warehouse employees, and they are requesting to have additional functionalities developed.

As we've already been using methods to write our code, we can imagine how cumbersome it would be for us to develop the tool without using methods. Think about how methods have made our code modular. Also notice how methods have helped us follow the DRY(Don't Repeat yourself) principal and the separation of concerns.  
We'll notice how the usage of methods have improved the readability and structure of the code to make it easier for us to add these requested features.

### Refactor the code

Try minimizing repeated codes with the use of methods as long as feasible.

You can chose to create additional functions for the error message and finishing the execution flow.

### New features

The warehouse management and employees have requested the following list of features:

**Extended Sessions**

Every time an employee finishes using the tool, it stops running. Very often they want to perform additional queries, so they have to execute again the tool, type again the name and then do the new operation.

They want you to add an option that, every time an operation finishes, lets them go back to the menu and chose another operation without stopping the tool.

So, whenever the execution finishes running any operation (except quit), the tool should ask the user if they want to perform another operation. If they decline, then the tool should stop running. If they accept, then the initial menu should show up again and ask to pick a choice (without asking again for the user name).

This should happen indefinitely until the user declines performing a new operation.

**List of actions in a session**

There is an additional requirement for the multiple operations in a session. At the end of the session (when the user declines performing another operation), the tool should print a list of the actions taken during that session and they should appear in the order they were performed.

Example:

```
Thank you for your visit, Example!
In this session you have:
        1. Listed 5000 items.
        2. Searched a Cheap tablet.
        3. Browsed the category Headphones.
```

> **Hint**: Each of the main functions for each one of the 3 operations should return a string with the description of the action taken.  
- Declare and initialize an empty static List of String named 'SESSION_ACTIONS'. (Note : convention of writing static string names in uppercase is widely used)  
- Add suitable string describing the action taken to this list once each action is performed.  

Steps :

1. Create a method named getTotalListedItems which returns an integer value that is the number of the total items in the list.   
Inside the listItemsByWarehouse method :   
  - Call this method(getTotalListedItems), get the number and construct appropriate string 'Listed {number} items.'. Then add this string to the static List of String i.e 'SESSION_ACTIONS'.

2. Create a method named getAppropriateeIndefiniteArticle which takes a String as an argument and returns the appropriate indefinite article "a" or "an"(String) as per the starting letter of the provided String argument. i.e returns "an" if the String starts with vowel i.e ('a', 'e', 'i', 'o', 'u') or returns "a" otherwise(starts with consonant).
This method is used to generate the appropriate string for the 2nd operation i.e Search Item and Place order.   
Inside the listItemsByWarehouse method :   
  - Call this method(getAppropriateeIndefiniteArticle), form an appropriate String 'Searched a/an itemName.'. Then add this string to the static List of String i.e 'SESSION_ACTIONS'.

3. Inside method browseByCategory :
    - form an appropriate String 'Browsed the category {categoryName}.'. Then add this string to the static List of String i.e 'SESSION_ACTIONS'.


4. Create a private method named listSessionActions which returns nothing and prints the Session summary at the end of the session. Call this method inside the quit() method before System.exit(0).
  - This method should print "In this session you have not done anything." if the user has not performed any action during the session. or print the list of actions as shown in the Example above.



**User system**

The tool has become so popular that the warehouse management has decided to let visitors use it also on the front desk. The problem is, visitors should only be allowed to list, search and browse, and they should not be able to order items straight from the tool. The placing of orders should only be allowed to employees in the list of the `personnel.json` file.

The tool should still ask the name to everyone. Only when the user wants to place an order, the tool will ask for a valid password and then will try to match the user name and password against the previous list.

> **Hint:** implement and use the isUserValid method in PersonalRepository class to validate the username and password.

If there is no employee with such name and password, it should say so and should allow the user to change the name and type again the password. This should happen indefinitely until the user succeeds authenticating and finishes the order or terminates the program.


The next time the user tries to place an order during the same session, if the user has already authenticated before, it should remember and not ask for the password again.
