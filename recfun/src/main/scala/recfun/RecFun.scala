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
  def countChange(money: Int, coins: List[Int]): Int = 0
}
