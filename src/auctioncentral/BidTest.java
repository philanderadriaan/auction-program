package auctioncentral;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * The test cases for the Bid class. Each test method tests an input
 * partition for the method it tests. The partitions may not be true
 * partitions--that is, there may be some overlap. The method name
 * should adequately describe the partition.
 * 
 * @author Tyson Nottingham
 * @version 20 May 2012
 * 
 */
public class BidTest
{
  private Item my_item_a;
  private Item my_item_a_copy;
  private Item my_item_b;
  private Bid my_bid_a;
  private Bid my_bid_a_copy;
  private Bid my_bid_a_with_item_copy;
  private Bid my_bid_b;
  private Bid my_bid_c;
  
  /*
   * This shouldn't be necessary since I don't change the objects in the
   * tests, but just to be on the safe side...
   */
  @Before
  public void setup()
  {
    my_item_a =
        new Item(1, "Item A", 1, 1.00, "Donor A", Size.MEDIUM, "Storage A",
                 Condition.ACCEPTABLE, "No comment", null);
    my_item_a_copy =
        new Item(1, "Item A", 1, 1.00, "Donor A", Size.MEDIUM, "Storage A",
                 Condition.ACCEPTABLE, "No comment", null);
    my_item_b =
        new Item(2, "Item B", 1, 1.00, "Donor B", Size.MEDIUM, "Storage B",
                 Condition.ACCEPTABLE, "No comment", null);
    
    my_bid_a = new Bid(my_item_a, 100.00);
    my_bid_a_copy = new Bid(my_item_a, 100.00);
    my_bid_a_with_item_copy = new Bid(my_item_a_copy, 100.00);
    my_bid_b = new Bid(my_item_b, 100.00);
    my_bid_c = new Bid(my_item_a, 50.00);
  }
  
  @Test
  public void testEquals_WithNull()
  {
    assertFalse("equals() with null argument", my_bid_a.equals(null));
  }
  
  @Test
  public void testEquals_WithUnequalItem()
  {
    assertFalse("equals() with unequal item", my_bid_a.equals(my_bid_b));
  }
  
  @Test
  public void testEquals_WithUnequalBidAmount()
  {
    assertFalse("equals() with unequal bid amount", my_bid_a.equals(my_bid_c));
  }
  
  @Test
  public void testEquals_WithUnequalItemAndBidAmount()
  {
    assertFalse("equals() with unequal item and bid amount",
                my_bid_b.equals(my_bid_c));
  }
  
  @Test
  public void testEquals_WithIdentity()
  {
    assertTrue("equals() with identity", my_bid_a.equals(my_bid_a));
  }
  
  @Test
  public void testEquals_WithCopy()
  {
    assertTrue("equals() with copy", my_bid_a.equals(my_bid_a_copy));
  }
  
  @Test
  public void testEquals_WithCopyContainingItemCopy()
  {
    assertTrue("equals() with bid with copy of same item",
               my_bid_a.equals(my_bid_a_with_item_copy));
  }
}
