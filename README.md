# SDF JAVA PROJECT

## Objective

- Implementation of an infinite/arbitrary-precision arithmetic library in Java using OOP concepts.
- If you are given two strings as input, you need to add support for addition, subtraction, multiplication and division, for both integer and float data types.

## Library Specification

It has a package called `arbitraryarithmetic` which consists of two classes `AInteger` and `AFloat`.

## AInteger.java / Integer class

I created a directory named `arbitraryarithmetic`.

Inside this directory, I created the file `AInteger.java`.

To make it a package, I included the statement `package arbitraryarithmetic;` at the top of the file.

### Properties or Members

- It has a protected member named **`num`** of type `String`.

### Constructors

- Default constructor `AInteger()` that initializes the instance with value 0.
- Constructor `AInteger(String num)` that initializes the instance by the number whose string representation is given by 'num'.
- Copy constructor that creates an instance of `AInteger`.

### Methods / Functions

- `parse(String num)`: A static function that returns an instance of `AInteger` class.
- `get()`: Returns the object's member which is `num`.
- `set(String num)`: Sets the object's member which is `num`.
- `remove_leading_zero(String num)`: Removes the extra unnecessary leading zeroes.
- `add(AInteger other)`: Adds integers of arbitrary length.
- `sub(AInteger other)`: Subtracts integers of arbitrary length.
- `mul(AInteger other)`: Multiplies integers of arbitrary length.
- `div(AInteger other)`: Divides integers of arbitrary length.

### Deeper into the Methods

#### `add(AInteger other)`

- Removes leading zeros from inputs.
- Handles both positive and negative integers.
- Uses digit-wise addition from unit's place with carry.
- If both negative, add magnitudes and prefix `-`.
- If one negative, convert to subtraction.
- Final result is cleaned of leading zeroes.

#### `sub(AInteger other)`

- Removes leading zeros from inputs.
- Works with both positive and negative integers.
- Compares inputs to determine sign.
- Uses digit-wise subtraction with borrow.
- If both negative, reverse operands.
- If one is negative, convert to addition.
- Result is cleaned of leading zeros.

#### `mul(AInteger other)`

- Removes leading zeros.
- Uses digit-wise multiplication logic.
- Adds partial results after each digit-wise product.
- Handles signs: result is negative if signs differ.
- Final result is cleaned of leading zeros.

#### `div(AInteger other)`

- Removes leading zeros.
- Checks for divide-by-zero.
- If dividend is 0, return 0.
- Uses long division logic.
- Handles signs.
- Result is cleaned of leading zeros.

---

## AFloat.java / Float class

I created the file `AFloat.java` inside the `arbitraryarithmetic` directory.

To include it in the same package, I added the statement `package arbitraryarithmetic;` at the top of the file.

### Properties or Members

- It has a protected member named **`num`** of type `String`.

### Constructors

- Default constructor `AFloat()` initializes instance to `0.0`.
- Constructor `AFloat(String num)` initializes from string.
- Copy constructor.

### Methods / Functions

- `parse(String num)`: Static function that returns an instance of `AFloat`.
- `get()`: Returns the member.
- `set(String num)`: Sets the member to `num`.
- `int decimal_digits()`: Returns number of decimal digits.
- `boolean decimal_point()`: Checks if number has decimal point.
- `remove_extra_zeroes(String str)`: Removes unnecessary trailing zeroes.
- `add(AFloat other)`: Adds two arbitrary-length floats.
- `sub(AFloat other)`: Subtracts two arbitrary-length floats.
- `mul(AFloat other)`: Multiplies two arbitrary-length floats.
- `div(AFloat other)`: Divides two arbitrary-length floats.
- `String truncate(String str)`: Truncates result to 30 decimal places.

### Deeper Look into Methods

#### `add(AFloat other)`

- Ensures decimal point exists in both numbers.
- Pads decimal digits for uniformity.
- Removes decimal point and performs addition via `AInteger`.
- Reinserts decimal point based on length.
- Handles sign and formats result with padding and truncation.

#### `sub(AFloat other)`

- Ensures decimal points exist.
- Pads decimal digits.
- Removes decimal and uses `AInteger.sub()`.
- Reinserts decimal based on decimal digit length.
- Handles formatting and sign.
- Truncates and cleans result.

#### `mul(AFloat other)`

- Ensures decimal points exist.
- Removes decimals and trims zeros.
- Converts to `AInteger` and multiplies.
- Decimal point added based on total decimal digits.
- Handles sign, leading zeros, and truncation.

#### `div(AFloat other)`

- Adds `.0` if needed.
- Removes decimals and trims zeros.
- Checks for zero divisor.
- Uses integer division and long division for decimal part.
- Constructs result with max 30 decimal places.
- Handles sign, formatting, and cleanup.

---

## `MyInfArith.java`

- It is a Java file which imports the `arbitraryarithmetic` package and runs test cases.
- It ensures that the input provided is either an integer or a float.
- It takes command-line arguments and performs arithmetic operations.

### Using `MyInfArith.java`

- First, compile using the command:

  ```bash
  javac MyInfArith.java arbitraryarithmetic/*.java

- Then, run the compiled java file using the command:
  ```bash
  java MyInfArith int/float add/sub/mul/div operand_1 operand_2
---

## `my_exe`

- It is a Python script used to run test cases.
- It internally uses `MyInfArith.java` to perform arithmetic operations.
- You can run the Python script using the command:

  ```bash
  ./my_exe int/float add/sub/mul/div operand1 operand2

---

## `build.xml`

- Defines a project named **"Arbitrary Arithmetic Operations"** with the default target `jar`.
- Sets both source and build directories to the **current folder**, so `.class` files are generated alongside `.java` files.
- The `compile` target compiles `arbitraryarithmetic/*.java` into the same directory.
- The `jar` target creates `aarithmetic.jar` containing all `.class` files from the current directory tree.
- The `clean` target deletes the `.jar` file and all `.class` files from the directory.
- Use the command `ant jar` to build the jar file and `ant clean` to clean up the generated files.

---

## `aarithmetic.jar`

- The `aarithmetic.jar` library can be linked to any **executable/library**.

### To use this JAR file in your Java program:

- Ensure that `aarithmetic.jar` is in the same directory as your Java file or provide the correct path to it.
- Use the following import statements at the beginning of your Java file to access the library's classes:
  ```java
  import arbitraryarithmetic.AInteger;
  import arbitraryarithmetic.AFloat;

- To compile the java File
  ```bash javac -cp .:aarithmetic.jar MyInfArith.java

- To run the code
  ```bash java -cp .:aarithmetic.jar MyInfArith int/float add/sub/mul/div operand1 operand2
