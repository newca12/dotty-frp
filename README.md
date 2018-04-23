## Some experiments with Dotty, FRP and Sodium semantics

### About

In coursera course [Functional Program Design in Scala](https://www.coursera.org/learn/progfun2) Martin Odersky show [a sample FRP implementation](https://www.coursera.org/learn/progfun2/lecture/5lWVa/lecture-4-3-a-simple-frp-implementation). You can see the summary [here](https://github.com/rohgar/scala-design-2/wiki/A-Simple-FRP-Implementation).

At the end of the lecture 4.3 some ideas for improvements where given :

*There's actually another possible solution, which is much cleaner and that is to simply pass down the current caller into all the signal-valued expressions. Now if we do that explicitly, it would produce a lot of overhead. Essentially, every signal-valued expression has to have another parameter and these parameters have to be threaded through everything. But if you make the parameter implicit, then a lot of that burden can actually be avoided. So the idea is that instead of maintaining a thread-local variable, we pass its current value into a signal expression as an [implicit parameter](https://stackoverflow.com/questions/39928480/how-to-implement-frp-using-implicit-parameter). And it's purely functional, but it currently requires still some more boiler plate than the thread-local solution because essentially expressions have to close, have to take this implicit value as a parameter. **Future versions of Scala might actually solve that problem, so we're currently tinkering with some ideas how that could be streamlined.***

The idea was demonstrated in this [talk *"Plain Functional Programming"*](https://twitter.com/odersky/status/928985957755506688) by Martin Odersky at Devoxx 2017

This dotty feature is called [Implicit Function Types](http://dotty.epfl.ch/docs/reference/implicit-function-types.html).

This WIP is an attempt to implement a [Sodium FRP implementation](https://github.com/SodiumFRP) with implicit function types.

### A taste of "Implicit Function Types"

* [Comparative demo](https://github.com/newca12/dotty-frp/tree/master/src/main/scala/demos/transaction) from the transaction use case mentioned in the [announce blog post](https://www.scala-lang.org/blog/2016/12/07/implicit-function-types.html) by Martin Odersky (with a [Reddit discussion](https://www.reddit.com/r/scala/comments/5h1d07/implicit_function_types_by_martin_odersky/)).
* [Example code](https://github.com/lampepfl/dotty-example-project/blob/master/src/main/scala/ImplicitFunctions.scala) in dotty-example-project.

### Resources
* The [paper *"Simplicitly: Foundations and Applications of Implicit Function Types"*](https://infoscience.epfl.ch/record/229878/files/simplicitly_1.pdf) and some [rants](https://twitter.com/edmund_noble/status/954739200577982464) from Edmund A. Noble.
* Most recent [talk *"Simplicitly: Foundations and Applications of Implicit FunctionTypes"*](https://www.youtube.com/watch?v=9Wp_riP8LQw) by Martin Odersky at POPL 2018
