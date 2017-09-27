#BigDecimal Accuracy Tests
  
The tests in this folders, try to expose the common failures with BigDecimal usage and the related accuracy operation problems.
Is expected, that most of the test fail. The idea, is make evident what is a mistake.

The BigDecimal is the class from the JDK, which tries to fix some common accuracy issues from floating point operations. But, this class has its own problems, some unexpected ones, like the construction from the same representation in doubles and integers (can be easily fixed), produce different objects, and the equals fail. And some not easily fixed, because some runtime ArithmeticException. For example trying to do BigDecimal(1/10).divide(BigDecimal(1/n)) where n is prime ^ n mod 10 != 0 || 10 mod n != 0. 

I invite you to check where the scale and precision are tricky concepts. And the differences using methods like setScale(), scaleByPowerOfTen(), round(), etc.

## Bug Causes
A lot of bugs are introduced because a miss understand or wrong usage of BigDecimal Objects. 

### BigDecimal
#### Assignation or Construction
##### Integer implicit cast 
When an assignation is defined as '1/10', because no cast to double or double value is related to the operation, an internal cast to int is made.
##### Fractions
The initialization of fractions, via double and division of BigDecimal gives different results at the last double precision value. So, some precision strategy, should be taken.
The value obtained from a division of integers will keep the same scale related to an integer value. That is very tricky.

##### Scale & Presicion 
The construction of fractions by the use of division method is no trivial, the scale used implicit or explicitly is determinant. The precision is the amount of digits which defines the unscaled value. The Scale, is the negative exponent of 10, related to define how much numbers -from left to right- are the fractional part.
For example, 
	- BigDecimal.valueOf(1), will be a unscaled value:= 1, scale:=0, precision:=1,
	- BigDecimal.valueOf(0.1), will be a unscaled value:= 1, scale:=2, precision:=1,
	- BigDecimal.valueOf(1).setScale(3), will be a unscaled value:= 100, scale:=3, precision:=3,
 
##### Reduction & inflation
	- The reduction by round method, is limited to the precision value >= 1.
	- Other form of reduction is via the setScale, (this means, changing the negative exponent of 10 related to the fraction part)  - The behavior is similar to the round method.
	- No inflation is possible after a reduction, because the unscaled value is modified. 

#### Evaluations ==, Equals & CompareTo
The behavior between Equals and CompareTo is different. In particular with different scales. 
CompareTo fix bugs related to the use of equals. But, not fix the tolerance required to some rounding criteria.

##### Equals Problems
	- 0 != 0.0 -> fixed with compareTo
	- BigDecimal.ZERO != 0.0
	- Irrational Fractions != by Scale 

##### CompareTo
Resolves the equality comparation for the same value. Is like to trim the zeros between both objects. 
But, the use of compareTo, don't resolve the scale of irrational fractions comparations.



##See
[BigDecimal](https://docs.oracle.com/javase/7/docs/api/java/math/BigDecimal.html)

To understand the concepts behind floating point problems
https://en.wikipedia.org/wiki/Double-precision_floating-point_format
https://en.wikipedia.org/wiki/Signed_zero
https://en.wikipedia.org/wiki/Floating-point_arithmetic#Rounding_modes
https://en.wikipedia.org/wiki/Endianness