package reference.week4.frp

//This (global state) is superseeded by scala.util.DynamicVariables (thread-local state)
class StackableVariable[T](init: T) {
  private var values: List[T] = List(init)
  def value: T = values.head //the current value is the head of the list
  def withValue[R](newValue: T)(op: => R): R = {
    values = newValue :: values
    try op finally values = values.tail
  }
}
