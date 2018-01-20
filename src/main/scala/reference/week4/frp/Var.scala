package reference.week4.frp

//A Var is a Signal (aka Cell/Rx) which can be changed manually via assignment.
class Var[T](expr: => T) extends Signal[T](expr) {
  //trick to allow public access to parent protected method
  override def update(expr: => T): Unit = super.update(expr)
}

object Var {
  def apply[T](expr: => T) = new Var(expr)
}
