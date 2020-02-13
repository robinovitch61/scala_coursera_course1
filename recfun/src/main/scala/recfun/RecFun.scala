package recfun

object RecFun extends RecFunInterface {

  def main(args: Array[String]): Unit = {
    println("Pascal's Triangle")
    for (row <- 0 to 20) {
      for (col <- 0 to row)
        print(s"${pascal(col, row)} ")
      println()
    }

    balance("())(".toList)
  }

  /**
   * Exercise 1
   */
  def pascal(c: Int, r: Int): Int = {
    if (c == 0 || r == c) 1
    else pascal(c, r - 1) + pascal(c - 1, r - 1)
  }

  /**
   * Exercise 2
   */
  def balance(chars: List[Char]): Boolean = {
    def isBalanced(chars: List[Char], count: Int): Int = {
      if (chars.isEmpty || count < 0) count
      else if (chars.head == '(') isBalanced(chars.tail, count + 1)
      else if (chars.head == ')') isBalanced(chars.tail, count - 1)
      else isBalanced(chars.tail, count)
    }
    isBalanced(chars, 0) == 0
  }

  /**
   * Exercise 3
   */
  def countChange(money: Int, coins: List[Int]): Int = {
    if (money == 0) 1 // 1 way to give change for no money, kind of weird intuitively
    else if (money < 0 || coins.isEmpty) 0 // this path of coins exceeded the required change
    else (
      countChange(money, coins.tail) // can I eliminate this coin option and still make change?
      + countChange(money - coins.head, coins) // can I make change by using this coin?
    )
  }
}
