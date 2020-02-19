package funsets

import org.junit._

/**
 * This class is a test suite for the methods in object FunSets.
 *
 * To run this test suite, start "sbt" then run the "test" command.
 */
class FunSetSuite {

  import FunSets._

  @Test def `contains is implemented`: Unit = {
    assert(contains(x => true, 100))
  }

  /**
   * When writing tests, one would often like to re-use certain values for multiple
   * tests. For instance, we would like to create an Int-set and have multiple test
   * about it.
   *
   * Instead of copy-pasting the code for creating the set into every test, we can
   * store it in the test class using a val:
   *
   *   val s1 = singletonSet(1)
   *
   * However, what happens if the method "singletonSet" has a bug and crashes? Then
   * the test methods are not even executed, because creating an instance of the
   * test class fails!
   *
   * Therefore, we put the shared values into a separate trait (traits are like
   * abstract classes), and create an instance inside each test method.
   *
   */

  trait TestSets {
    val s1 = singletonSet(1)
    val s2 = singletonSet(2)
    val s3 = singletonSet(3)
  }

  /**
   * This test is currently disabled (by using @Ignore) because the method
   * "singletonSet" is not yet implemented and the test would fail.
   *
   * Once you finish your implementation of "singletonSet", remvoe the
   * @Ignore annotation.
   */
  @Test def `singleton set one contains one`: Unit = {

    /**
     * We create a new instance of the "TestSets" trait, this gives us access
     * to the values "s1" to "s3".
     */
    new TestSets {
      /**
       * The string argument of "assert" is a message that is printed in case
       * the test fails. This helps identifying which assertion failed.
       */
      assert(contains(s1, 1), "Singleton")
    }
  }

  @Test def `union contains all elements of each set`: Unit = {
    new TestSets {
      val s = union(s1, s2)
      assert(contains(s, 1), "Union 1")
      assert(contains(s, 2), "Union 2")
      assert(!contains(s, 3), "Union 3")
    }
  }

  @Test def `intersect test`: Unit = {
    new TestSets {
      val s = intersect(s1, s1)
      val t = intersect(s1, s2)
      assert(contains(s, 1), "Intersection 1")
      assert(!contains(t, 1), "Intersection 2")

      val u = union(s1, s2)
      val v = intersect(s1, u)
      assert(contains(v, 1), "Intersect 1")
      assert(!contains(v, 2), "Intersect 2")
    }
  }

  @Test def `diff test`: Unit = {
    new TestSets {
      val s = diff(s1, s1)
      val t = diff(s1, union(s2, s3))
      assert(!contains(s, 1), "Diff 1")
      assert(contains(t, 1), "Diff 2")
    }
  }

  @Test def `filter test`: Unit = {
    new TestSets {
      val s = filter(s1, s1)
      val t = filter(s1, union(s2, s3))
      assert(contains(s, 1), "Filter 1")
      assert(!contains(t, 1), "Filter 2")

      val set = union(s1, s2)
      val v = filter(set, x => x == 1)
      assert(contains(v, 1), "filter 1")
      assert(!contains(v, 2), "filter 2")
      assert(!contains(v, 1000), "filter 3")
    }
  }

  @Test def `forall test`: Unit = {
    new TestSets {
      assert(forall(s1, x => true)) // should be true no matter the set
      assert(forall(s1, x => x == 1))
      assert(!forall(union(s1, s2), x => x == 1))
    }
  }

  @Test def `exists test`: Unit = {
    new TestSets {
      assert(exists(union(s1, s2), x => x == 1))
      assert(!exists(union(s1, s2), x => x == 3))
    }
  }

  @Test def `map test`: Unit = {
    new TestSets {
      val set = union(union(s1, s2), s3)
      val s = map(set, x => x * x)
      assert(s(1))
      assert(s(4))
      assert(s(9))
      assert(!s(10))
      assert(!s(-1))
    }
  }

  @Rule def individualTestTimeout = new org.junit.rules.Timeout(10 * 1000)
}
