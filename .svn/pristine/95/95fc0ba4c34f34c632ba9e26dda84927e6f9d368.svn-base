package auctioncentral;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * 
 * @author Phil Adriaan .
 * @version 1
 */
public class ItemTest
{
  /**
   * Item to bid on.
   */
  private Item my_item;

  /**
   * First bidder to bid.
   */
  private Bidder my_bidder_1;

  /**
   * Second bidder to bid.
   */
  private Bidder my_bidder_2;

  /**
   * Initialize an items and bidders.
   */
  @Before
  public void setUp()
  {
    my_item =
        new Item(81, "Kawasaki Ninja 250R", 10, 10000.00, "Anonymous", Size.LARGE,
                 "Classified", Condition.NEW, "4th gen", null);
    my_bidder_1 = new Bidder("Juan");
    my_bidder_2 = new Bidder("Diaz");
  }

  /**
   * Tests string output.
   */
  @Test
  public void testString()
  {
    assertEquals("10 Kawasaki Ninja 250R's", my_item.toString());
  }

  /**
   * Tests equal. Exact same items are equal. Similar but different items are
   * not equal.
   */
  @Test
  public void testEqual()
  {
    assertTrue(my_item.equals(my_item));
    assertFalse(new Item(18, "Kawasaki Ninja 250R", 10, 10000.00, "Anonymous", Size.LARGE,
                         "Classified", Condition.NEW, "4th gen", null).equals(my_item));
  }

  /**
   * No bid happens.
   */
  @Test
  public void testNoBid()
  {
    assertEquals(null, my_item.getHighBidder());
    assertTrue(-1.00 == my_item.getHighBid());
  }

  /**
   * A person bids below minimum bid.
   */
  @Test
  public void testBelowMinimumBid()
  {
    my_item.addBid(my_bidder_1, 9999.99);
    assertEquals(null, my_item.getHighBidder());
    assertTrue(-1.00 == my_item.getHighBid());
  }

  /**
   * A person bids exactly the minimum bid.
   */
  @Test
  public void testExactMinimumBid()
  {
    my_item.addBid(my_bidder_1, 10000);
    assertEquals("Juan", my_item.getHighBidder().getName());
    assertTrue(10000.00 == my_item.getHighBid());
  }

  /**
   * A person bids above minimum bid.
   */
  @Test
  public void testAboveMinimumBid()
  {
    my_item.addBid(my_bidder_1, 10000.01);
    assertEquals("Juan", my_item.getHighBidder().getName());
    assertTrue(10000.01 == my_item.getHighBid());
  }

  /**
   * A person bids above minimum bid, then a second person bids below the first
   * person's bid.
   */
  @Test
  public void testLowBid()
  {
    my_item.addBid(my_bidder_1, 30000);
    my_item.addBid(my_bidder_2, 20000);
    assertEquals("Juan", my_item.getHighBidder().getName());
    assertTrue(30000.00 == my_item.getHighBid());
  }

  /**
   * A person bids above minimum bid, then a second person bids exactly the
   * first person's bid.
   */
  @Test
  public void testSameBid()
  {
    my_item.addBid(my_bidder_1, 30000);
    my_item.addBid(my_bidder_2, 30000);
    assertEquals("Juan", my_item.getHighBidder().getName());
    assertTrue(30000.00 == my_item.getHighBid());
  }

  /**
   * A person bids above minimum bid, then a second person bids above the first
   * person's bid.
   */
  @Test
  public void testHighBid()
  {
    my_item.addBid(my_bidder_1, 10000);
    my_item.addBid(my_bidder_2, 40000);
    assertEquals("Diaz", my_item.getHighBidder().getName());
    assertTrue(40000.00 == my_item.getHighBid());
  }

}
