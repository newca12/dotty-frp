package demos.transaction

import scala.collection.mutable.ListBuffer

class DottyTransaction {
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

object RunDottyTransaction {
type Transactional[T] = given DottyTransaction => T
def thisTransaction: Transactional[DottyTransaction] = implicitly[DottyTransaction]

  def f1(x: Int): Transactional[Int] = {
    thisTransaction.println(s"first step: $x")
    f2(x + 1)
  }
  def f2(x: Int): Transactional[Int] = {
    thisTransaction.println(s"second step: $x")
    f3(x * x)
  }
  def f3(x: Int): Transactional[Int] = {
    thisTransaction.println(s"third step: $x")
    if (x % 2 != 0) thisTransaction.abort()
    x
  }

    def transaction[T](op: Transactional[T]) = {
    implicit val trans: DottyTransaction = new DottyTransaction
    op
    trans.commit()
  }
  def main(args: Array[String]) = {
    transaction {
      val res = f1(args.length)
      println(if (thisTransaction.isAborted) "aborted" else s"result: $res")
    }
  }
}
