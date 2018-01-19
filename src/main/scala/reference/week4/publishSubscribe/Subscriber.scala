package reference.week4.publishSubscribe

trait Subscriber {
  def handler(pub: Publisher): Unit
}
