import week4.frp.Signal
import week4.Signals.BankAccount

object accounts {

  def consolidated(accts: List[BankAccount]): Signal[Int] =
    Signal(accts.map(_.balance()).sum)

  val a = new BankAccount
  val b = new BankAccount

  val c = consolidated(List(a, b))
  c()

  a deposit 20
  c()
}