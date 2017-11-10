# Immutability Ivestigation tests
The tests in this folders, try to expose the common failures with the immutable/mutable concepts.
Is expected, that most of the test fail. The idea, is make evident what is a mistake.

The best way to present the immutability is, showing the problems with mutability.
And the problems with immutability are related to performance in extreme situations, so that will be part of some investigation in the future.
But what appends when you mix both concepts ? Yeap, problems.


## All the objects should be immutable ?
Nop. Is better design your software (if you want the warranties which are provided by immutability) under that design pattern. But not all the concepts are immutable. For example,in the Lock used for threading synchronization, the mutability there is a key factor.
But, (and before you make some grimace) if you use immutable pattern in all your objects, well, the use of synchronization could be avoided.

 
## How to "fix" a mutable object and transform to an immutable one ?
Well, the right way to do it, as a first try, is checking the immutable capability of your mutable object:
 - Check if some public method, modifies internally the state of the object, and the return type is not the type of the object itself. Then you can't. (for example, Lock.lock(), which logically it is ok to be mutable ).
 - If the setters return type is different from the object type itself, well you need to implement a builder and a proxy pattern to isolate the setters.


### Transforming a mutable object to an immutable one.
 (WARNING: the scope of validation to this fix, is coupled to the knowledge of the team -and implementators- about the modificated behavior. So don't expect "immutate" some old library objects without troubles. All the troubles will be at runtime because of the exceptions. Formally you are changing the contract behavor.)
 
- Define the scope of problems, if they are under your direct control, it's ok.
- re-implement the contract or extending (the class) into a final class.
- override the setters throwing an exception (UnsuppoertedOperationException) when the object was "build".
- Create a builder which builds your implementation (and loose modifiable relationship with the created object).

#### Expected problems
- Exceptions will be thrown by your setters after construction -> Refactor of your code will be required.
- Loose of delegable modification or root scope, so a new immutable object can't be associated to the root -> Refactor.


## See
https://en.wikipedia.org/wiki/Immutable_object
