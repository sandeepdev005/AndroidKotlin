# Kotlin

# About
- Kotlin is a cross-platform programming language(Code can run on multiple Operating System (Windows, macOS, Linux etc))
- Kotlin is fully interoperable with Java and officially supported for android development by Google since 2017
- Kotlin is used because of its concise syntax, null safety feature , improve readability and maintainability  over java

## Kotlin Benefit 
- Interoperability with Java
  - Allow developer to write both kotlin and java code 
  - Still developer can use java libraries directly in kotlin 
- Concise and Expressive Syntax
  - Reduced Boilerplate   : Code Kotlin’s syntax is more concise compared to Java
  - Improved Productivity : data classes, extension functions, and higher-order functions, enable developers to write more expressive code
- Null Safety
- Coroutines for Asynchronous Programming
- Modern Language Features
  - lambda expressions, higher-order functions, and inline functions,Extension Functions,
- Multi-Platform Development - Kotlin Multiplatform
- Safer Code and Easier Maintenance
  - Immutability: Kotlin encourages immutability and provides data classes and other features
  - Less Error-Prone : Features like null safety, smart casts, and concise syntax contribute to writing less error-prone
 
## Feature 
- [Special Classes](#special-classes)
  - [Data Classes](#data-classes)
  - [Enum Classes](#enum-classes)
  - [Sealed Classes](#sealed-classes)
  - [Object Keyword](#object-keyword)

- [Functional]()
- [Collections]()
- [Scope Functions](#scope-functions)
  - [let](#let)
  - [run](#run)
  - [with](#with)
  - [apply](#apply)
  - [also](#also)



## Special Classes

### Data Classes
- A data class in Kotlin is specifically designed to hold data
- It automatically generates equals(), hashCode, toString(),copy() methods
- Primary constructor must have at least one parameter.
- All primary constructor parameters must be marked as val or var.
- Cannot be abstract, open, sealed, or inner.
- Ideal for storing immutable data.
- Data class doesn't have any behaviour.
  - Example code

- ```data class example 
   ```kotlin
    data class Person(val name: String, val age: Int)
  
    val person1 = Person("John", 30)
    val person2 = Person("John", 30)
    
    // Automatically generated methods
    println(person1 == person2) // true, because equals() is automatically generated
    println(person1.hashCode() == person2.hashCode()) // true
    println(person1) // Person(name=John, age=30) // toString()
    
    val person3 = person1.copy(age = 31)
    println(person3) // Person(name=John, age=31)
    
    // Destructuring declaration
    val (name, age) = person1
    println("Name: $name, Age: $age") // Name: John, Age: 30
  
   \```

### Normal Class
- A normal class in Kotlin is used to define an entity with properties,methods,secondary constructors and complex business logic.
- Does not automatically generate equals(), hashCode(), toString(), and copy() methods.
- Suitable for representing entities with behavior and logic.
  - Allows for complex hierarchies through inheritance.
    - Primary Constructor 
      - Defined in the class header. 
      - Suitable for mandatory property initialization.
      ```Example Primary Constructor 
      ```kotlin
         class Person(val name: String, var age: Int) {
        // Properties initialized in the primary constructor
        var email: String = ""

        fun displayInfo() {
            println("Name: $name, Age: $age, Email: $email")
        }
      }
      fun main() { 
      val person = Person("John", 30)
      person.email = "john.doe@example.com"
      person.displayInfo() // Output: Name: John, Age: 30, Email: john.doe@example.com
      }
      \```
      
    - Secondary Constructor  
        - It is defined within class body and prefix with constructor keyword
          - It allow different ways to instantiate the class.
          - Useful for additional logic or default values during object creation.
            - ``` Secondary Constructor Example
              ```kotlin
               class Person {
                    var name: String
                    var age: Int
                    var email: String
      
                    // Primary constructor
                    constructor(name: String, age: Int) {
                        this.name = name
                        this.age = age
                        this.email = ""
                    }
      
                    // Secondary constructor
                    constructor(name: String, age: Int, email: String) : this(name, age) {
                        this.email = email
                    }
      
                    fun displayInfo() {
                        println("Name: $name, Age: $age, Email: $email")
                    }
              }

              fun main() {
                val person1 = Person("John", 30)
                person1.displayInfo() // Output: Name: John, Age: 30, Email:

                val person2 = Person("Jane", 25, "jane.doe@example.com")
                person2.displayInfo() // Output: Name: Jane, Age: 25, Email: jane.doe@example.com
              }
              
              \```



### Enum Classes
### Sealed Classes
### Object Keyword
#### Class and Object 
- a class is a blueprint, and an object is an instance of a class
- you define a class and then create multiple instances of that class
- class and objects in kotlin work same way in most object oriented programming language.
#### Object
- Object keyword used to obtain a data type with a single implementation 
- Single means Singleton , it ensure only one instance to be created even if 2 threads try to create it.
- NO class, No constructor , Only a lazy instance (it will create only when an object is accessed).
#### Object Expression
  - No name is required for single object creation 
    - you create a single object, declare its members and access it within one function
    - Can be used for variable assignment
      - ``` Object Expression
        ```kotlin
    
        fun rentPrice(standardDays: Int, festivityDays: Int, specialDays: Int): Unit {  //1

          val dayRates = object {                                                     //2
              var standard: Int = 30 * standardDays
              var festivity: Int = 50 * festivityDays
              var special: Int = 100 * specialDays
          }

          val total = dayRates.standard + dayRates.festivity + dayRates.special       //3

          print("Total price: $$total")                                               //4
        }
        
        \```
#### Object Declaration      
- It is used for Utility functions , constants , maintaining single instance
- This object have name  object Utils{ a(), b() ....}.
- can not be used for variable assignment.
- You should use it  directly to access its members
  - Independent of any class.
    - ``` Object Expression
          ```kotlin
    
          object DoAuth {                                                 //1 
            fun takeParams(username: String, password: String) {        //2 
                println("input Auth parameters = $username:$password")
            }
          }
    
          fun main(){
              DoAuth.takeParams("foo", "qwerty")                          //3
          }
        
          \```
      
#### Companion Objects
- Companion Objects declares inside a class 
- You can call object members using ClassName.ObjectMember()
- It is similar to access the Static methods from a class in Java 
- Defines a singleton within a class.
- Useful for factory methods, constants, and static-like members that need to access the class's private members
- Companion Object can be with or without name 
  - ``` Example
            ```kotlin
    
            class User private constructor(val id: Int, private val name: String) {

                // Private method
                private fun getWelcomeMessage(): String {
                    return "Welcome, $name!"
                }
  
                // Companion object
                companion object {
                    // Factory method
                    fun createUser(id: Int, name: String): User {
                        // Accessing private constructor
                        val user = User(id, name)
                        // Accessing private method
                        println(user.getWelcomeMessage())
                        return user
                    }
                }
  
              // Public method
              fun displayUserInfo() {
                  println("User ID: $id, User Name: $name")
              }
           }

      fun main() {
        // Creating a User object using the factory method
        val user = User.createUser(1, "Alice")
        // Accessing public method
        user.displayUserInfo()
      }
            \```
- Explanation
- When a class has a private constructor, it means that objects of that class cannot be created directly from outside the class.
- useful for implementing design patterns like the Singleton pattern or controlling the creation of instances through factory methods.
- Companion object Can access private constructors and private methods of the class.






## Scope Functions
### let 
- Let can be used for scoping and null-checks.
- let executes the given block of code and returns the result of its last expression.
### run
- 
### with
- With this object perform required operation
- It takes not null object
### apply
- Let me apply few more changes to this existing object 
- Modify the existing copy ?
### also
- Along with these operation , perform these operation also
- It doesn't modify the object on which also was used 
- 

