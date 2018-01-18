package week4.publishSubscribe

trait Subscriber {
  def handler(pub: Publisher): Unit
}
