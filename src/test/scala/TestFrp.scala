import org.junit.Test
import org.junit.Assert._

import reference.week4.frp.Var
import reference.week4.frp.Signal

class TestFrp {
  //from https://github.com/lihaoyi/scala.rx
  @Test def scalarxReadMe1(): Unit = {
     val a = Var(1)
     val b = Var(2)
     val c = Signal(a() + b())
     val d = Signal(c() * 5)
     val e = Signal(c() + 4)
     val f = Signal(d() + e() + 4)
    assertEquals(f(), 26)
    a()=3
    assertEquals(f(), 38)
  }

//from https://github.com/lihaoyi/scala.rx
  @Test def scalarxReadMe2(): Unit = {
    val a = Var(Seq(1, 2, 3))
    val b = Var(3)
    val c = Signal { b() +: a() }
    val d = Signal { c().map("omg" * _) }
    val e = Var("wtf")
    val f = Signal { (d() :+ e()).mkString }
    assertEquals(f(), "omgomgomgomgomgomgomgomgomgwtf")
    a()=Nil
    assertEquals(f(), "omgomgomgwtf")
    e() = "wtfbbq"
    assertEquals(f(), "omgomgomgwtfbbq")
  }
}