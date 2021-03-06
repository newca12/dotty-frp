package reference.week4.frp

import scala.util.DynamicVariable

// A container for a value that change over time
class Signal[T](expr: => T) {
  import Signal._
  private var myExpr: () => T = _ // the current expression that defines the signal value
  private var myValue: T = _ // the current signal value
  private var observers: Set[Signal[_]] = Set() // the other signals that depend on the signal value
  def apply(): T = {
    observers += caller.value // add the current caller to the set of observers
    // necessary to avoid stack overflow error generated by the infinite recursion
    // S() = S() + 1 i.e. signal which depends on itself cannot be observed.
    assert(!caller.value.observers.contains(this), "cyclic signal def")
    myValue
  }

  // initalization
  update(expr)

  protected def update(expr: => T): Unit = {
    myExpr = () => expr // take the expr provided, and assign it to MyExpr
    computeValue()
  }

  protected def computeValue(): Unit = {
    val newValue = caller.withValue(this)(myExpr())
    if (myValue != newValue) {
      myValue = newValue // assign the newValue to myValue
      val obs = observers // take the observers into a local value obs
      observers = Set() // This is mandatory to clear the observer set
      obs.foreach(_.computeValue()) // do a compute value for each observer
    }
  }
}

//Sentinel object that express the fact that initially there is no caller
object NoSignal extends Signal[Nothing](???) {
  override def computeValue(): Unit = ()
}

object Signal {
  private val caller = new DynamicVariable[Signal[_]](NoSignal) // accessed by all
  def apply[T](expr: => T) = new Signal(expr)
}
