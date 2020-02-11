package recfun

object RecFun extends RecFunInterface {

  def main(args: Array[String]): Unit = {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
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
    if (c == 0) 1 else {
      if (r == 0) 0 else
        pascal(c, r - 1) + pascal(c - 1, r - 1)
    }
  }

  /**
   * Exercise 2
   */
//  def balance(chars: List[Char]): Boolean = {
//    def remove(idx: Int, list: List[Char]) =
//      list diff List(list.apply(idx))
//
//    val idxOpen = chars.indexOf('(')
//    val idxClose = chars.indexOf(')')
//    println(chars.toString, idxOpen, idxClose)
//    if (idxOpen == -1 && idxClose == -1) true else {
//      if (idxOpen == -1 || idxClose == -1) false else {
//        val newList = remove(idxOpen, chars)
//        val finalList = remove(List(0, idxClose - 1).max, newList)
//        if (finalList.length == 0) true else balance(finalList)
//      }
//    }
//  }
def balance(chars: List[Char]): Boolean = {
  true
}

  /**
   * Exercise 3
   */
  def countChange(money: Int, coins: List[Int]): Int = 0
}
