## Some experiments with Dotty, FRP and Sodium semantics

### About

In coursera course [Functional Program Design in Scala](https://www.coursera.org/learn/progfun2) Martin Odersky show [a sample FRP implemenation](https://www.coursera.org/learn/progfun2/lecture/5lWVa/lecture-4-3-a-simple-frp-implementation).

At the end of the lecture 4.3 some ideas for improvements where given :

*There's actually another possible solution, which is much cleaner and that is to simply pass down the current caller into all the signal-valued expressions. Now if we do that explicitly, it would produce a lot of overhead. Essentially, every signal-valued expression has to have another parameter and these parameters have to be threaded through everything. But if you make the parameter implicit, then a lot of that burden can actually be avoided. So the idea is that instead of maintaining a thread-local variable, we pass its current value into a signal expression as an implicit parameter. And it's purely functional, but it currently requires still some more boiler plate than the thread-local solution because essentially expressions have to close, have to take this implicit value as a parameter. **Future versions of Scala might actually solve that problem, so we're currently tinkering with some ideas how that could be streamlined.***

[The idea was demonstrated in this talk by Martin Odersky at Devoxx 2017](https://twitter.com/odersky/status/928985957755506688)

The dotty feature is called [Implicit Function Types](http://dotty.epfl.ch/docs/reference/implicit-function-types.html).

This WIP is an attempt to implement a Sodium FRP implementation with implicit function types.