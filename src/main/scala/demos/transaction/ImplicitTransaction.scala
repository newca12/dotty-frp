package demos.transaction

import scala.collection.mutable.ListBuffer

class ImplicitTransaction {
  private val log              = new ListBuffer[String]
  def println(s: String): Unit = log += s

  private var aborted   = false
  private var committed = false

  def abort(): Unit = { aborted = true }
  def isAborted     = aborted

  def commit(): Unit =
    if (!aborted && !committed) {
      Console.println("******* log ********")
      log.foreach(Console.println)
      committed = true
    }
}

object RunImplicitTransaction {
  def transaction[T](op: ImplicitTransaction => T) = {
    val trans: ImplicitTransaction = new ImplicitTransaction
    op(trans)
    trans.commit()
  }

  def f1(x: Int)(implicit thisTransaction: ImplicitTransaction): Int = {
    thisTransaction.println(s"first step: $x")
    f2(x + 1)
  }

  def f2(x: Int)(implicit thisTransaction: ImplicitTransaction): Int = {
    thisTransaction.println(s"second step: $x")
    f3(x * x)
  }

  def f3(x: Int)(implicit thisTransaction: ImplicitTransaction): Int = {
    thisTransaction.println(s"third step: $x")
    if (x % 2 != 0) thisTransaction.abort()
    x
  }

  def main(args: Array[String]) = {
    transaction { implicit thisTransaction =>
      val res = f1(args.length)
      println(if (thisTransaction.isAborted) "aborted" else s"result: $res")
    }
  }

}
