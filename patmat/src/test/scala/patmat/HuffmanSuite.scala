package patmat

import org.junit._
import org.junit.Assert.assertEquals

class HuffmanSuite {
  import Huffman._

  trait TestTrees {
    val t1 = Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5)
    val t2 = Fork(Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5), Leaf('d',4), List('a','b','d'), 9)
  }


  @Test def `weight of a larger tree (10pts)`: Unit =
    new TestTrees {
      assertEquals(5, weight(t1))
    }


  @Test def `chars of a larger tree (10pts)`: Unit =
    new TestTrees {
      assertEquals(List('a','b','d'), chars(t2))
    }

  @Test def `times of a char list`: Unit =
    assertEquals(times(List('a', 'b', 'a')), List(('a', 2), ('b', 1)))

  @Test def `string2chars hello world`: Unit =
    assertEquals(List('h', 'e', 'l', 'l', 'o', ',', ' ', 'w', 'o', 'r', 'l', 'd'), string2Chars("hello, world"))

  @Test def `make ordered leaf list for some frequency table (15pts)`: Unit =
    assertEquals(List(Leaf('e',1), Leaf('t',2), Leaf('x',3)), makeOrderedLeafList(List(('t', 2), ('e', 1), ('x', 3))))

  @Test def `test singleton`: Unit = {
    assert(singleton(List(Leaf('a', 1))))
    assert(!singleton(List(Leaf('a', 1), Leaf('b', 1))))
  }

  @Test def `combine of some leaf list (15pts)`: Unit = {
    val leaflist = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4))
    assertEquals(List(Fork(Leaf('e',1),Leaf('t',2),List('e', 't'),3), Leaf('x',4)), combine(leaflist))
  }

  @Test def `test until`: Unit = {
    val leaflist = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4))
    assertEquals(until(singleton, combine)(leaflist), List(Fork(Fork(Leaf('e',1),Leaf('t',2),List('e', 't'),3),Leaf('x',4),List('e', 't', 'x'),7)))
  }

  @Test def `test createCodeTree`: Unit = {
    val charList = List('a', 'a', 'b', 'c')
    assertEquals(createCodeTree(charList), Fork(Leaf('c',1),Fork(Leaf('a',2),Leaf('b',1),List('a', 'b'),3),List('c', 'a', 'b'),4))
  }

  @Test def `test simple decode`: Unit = {
    val bitList = List(0, 1, 0)
    val charList = List('a', 'a', 'b')
    assertEquals(decode(createCodeTree(charList), bitList), List('b', 'a', 'b'))

  }

  @Test def `decode and encode a very short text should be identity (10pts)`: Unit =
    new TestTrees {
      assertEquals("ab".toList, decode(t1, encode(t1)("ab".toList)))
    }

  @Rule def individualTestTimeout = new org.junit.rules.Timeout(10 * 1000)
}
