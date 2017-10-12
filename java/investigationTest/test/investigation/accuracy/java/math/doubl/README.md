# Double accuracy Tests

The tests in this folder, try to expose the common failures with floating point usage and the related accuracy operation problem.
Is expected, that most of the test fail. The idea, is make evident what is a mistake.
Inside the tests you will see different values related to the tolerable precision. That means, some times the precision could be affected by the operation carry error and some times by the values involved into that operation (or function) (primes into a division). 
So precision management is not a simple and you should know what type of function and data will be involved.  

## Bug Causes
A lot of bugs are introduced because a miss understand or wrong usage of double and Double Object.

### double native
#### Assignation or Construction
##### Implicit cast to int
When an assignation is defined as '1/10', because no cast to double or double value is related to the operation, an internal cast to int is made.
##### int -0 loose sign 
This is because, before the assignation is cast to int implicitly (and integers don't has negative 0), then is converted to double. The same happends with divisions like 1/10, returning 0.
##### Negative Zero & Positive Zero
Works as expected under decimal assignation.

#### Evaluations ==, Equals & CompareTo
It is known the floating point problems related to representation, where some fractional operations has representative errors which produce values near to the expected ones,but not the precise ones.
In the tests, i use some specific values to demonstrates the problems and the error in the use == in the evaluation between floating point, and the right criteria to evaluate equality in natural floating point values.
But is important to take in mind, that the precision related to equality is related to the function and the data related to the function. So, is no a trivial solution to avoid this kinds of errors.
[ZeroFloatingPointInteger_PN_03](DoubleAccuracyAssignationInvestigationTest.java#ZeroFloatingPointInteger_PN_03)
##### Negative Zero & Positive Zero
Negative Zero == Positive Zero
[ZeroFloatingPointInteger_PN_03](DoubleAccuracyAssignationInvestigationTest.java#ZeroFloatingPointInteger_PN_03)

#### operations
All of them has some problem with some precision range related to both, the function(because the operation of error) and the data (because the representation). Outside this range, you have a false negative because the excessive precision. Which is not determinable by default, that depends on the function and the data related.

### Double Object
#### Assignation or Construction
##### Implicit cast to int
When an asignation is defined as '1/10', because no cast to double or double value is related to the operation, an internal cast to int is made. Consistent with natural doubles.
##### Double(-0) loose sign
The implicit integer conversion before the assignation is present here, like in native doubles.
Integers -0 are converted to 0 positive.
[ZeroFloatingPointInteger_PN_01](DoubleObjectAccuracyAssignationInvestigationTest.java#ZeroFloatingPointInteger_PN_01)
[ZeroFloatingPointInteger_PN_02](DoubleObjectAccuracyAssignationInvestigationTest.java#ZeroFloatingPointInteger_PN_02)
[ZeroFloatingPointInteger_PN_03](DoubleObjectAccuracyAssignationInvestigationTest.java#ZeroFloatingPointInteger_PN_03)
[ZeroFloatingPointInteger_NN](DoubleObjectAccuracyAssignationInvestigationTest.java#ZeroFloatingPointInteger_NN)

#### Evaluations ==, Equals & CompareTo
##### -0.0 equals 0.0 Fail !
This condition (-0.0 != 0.0) is tested under ==, equals and compareTo, which is consistent under Double class. But inconsistent with native doubles !

#### operations
The same problems as natural doubles. 

## See
To understand the concepts behind floating point problems

https://en.wikipedia.org/wiki/Double-precision_floating-point_format

https://en.wikipedia.org/wiki/Signed_zero

https://en.wikipedia.org/wiki/Floating-point_arithmetic#Rounding_modes

https://en.wikipedia.org/wiki/Endianness
