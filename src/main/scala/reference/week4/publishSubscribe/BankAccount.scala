package reference.week4.publishSubscribe

class BankAccount extends Publisher {
  private var balance = 0

  def currentBalance = balance

  def deposit(amount: Int) = {
    if (amount > 0) balance = balance + amount
    publish()
  }

  def withdraw(amount: Int) = if (0 < amount && amount <= balance) {
    balance = balance - amount
    publish()
  } else throw new Error("Insufficient funds")
}