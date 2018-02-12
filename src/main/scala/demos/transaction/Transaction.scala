package transaction

import scala.collection.mutable.ListBuffer

class Transaction {
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

object RunTransaction {
  def transaction[T](op: Transaction => T) = {
    val trans: Transaction = new Transaction
    op(trans)
    trans.commit()
  }

  def f1(x: Int)(thisTransaction: Transaction): Int = {
    thisTransaction.println(s"first step: $x")
    f2(x + 1)(thisTransaction)
  }

  def f2(x: Int)(thisTransaction: Transaction): Int = {
    thisTransaction.println(s"second step: $x")
    f3(x * x)(thisTransaction)
  }

  def f3(x: Int)(thisTransaction: Transaction): Int = {
    thisTransaction.println(s"third step: $x")
    if (x % 2 != 0) thisTransaction.abort()
    x
  }

  def main(args: Array[String]) = {
    transaction { thisTransaction =>
      val res = f1(args.length)(thisTransaction)
      println(if (thisTransaction.isAborted) "aborted" else s"result: $res")
    }
  }

}
