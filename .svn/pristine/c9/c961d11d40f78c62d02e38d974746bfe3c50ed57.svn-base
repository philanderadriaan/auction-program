package auctioncentral;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * The test cases for the Bidder class. Each test method tests an input
 * partition for the method it tests. The partitions may not be true
 * partitions--that is, there may be some overlap. The method name
 * should adequately describe the partition.
 * 
 * @author Tyson Nottingham
 * @version 20 May 2012
 * 
 */
public class BidderTest
{
  public Item my_item_a;
  public Bidder my_bidder_a;
  public Bidder my_bidder_b;
  
  @Before
  public void setup()
  {
    my_item_a =
        new Item(1, "Item A", 1, 10.00, "Donor A", Size.MEDIUM, "Storage A",
                 Condition.ACCEPTABLE, "No comment", null);
    my_bidder_a = new Bidder("Bidder A");
    my_bidder_b = new Bidder("Bidder B");
  }
 
  @Test
  public void testConstructor_BidListIsEmpty()
  {
    assertTrue("Bidder should have no bids", my_bidder_a.getBids().isEmpty());
  }
  
  @Test
  public void testPlaceBid_LessThanMin()
  {
    assertFalse("Placing less than min should return false",
                my_bidder_a.placeBid(my_item_a, 9.99));
    assertFalse("Less than min bid should not be added to list",
                my_bidder_a.getBids().contains(new Bid(my_item_a, 9.99)));
  }
  
  @Test
  public void testPlaceBid_FirstBidGreaterThanMin()
  {
    assertTrue("First bid greater than min should return true",
               my_bidder_a.placeBid(my_item_a, 10.00));
    assertTrue("First bid greater than min should be added to list",
               my_bidder_a.getBids().contains(new Bid(my_item_a, 10.00)));
  }
  
  @Test
  public void testPlaceBid_TwoBidsOnSameItem()
  {
    my_bidder_a.placeBid(my_item_a, 10.00);
    assertFalse("Placing more than one high bid on same item should return" +
                " false", my_bidder_a.placeBid(my_item_a, 10.05));
    assertFalse("Second bid on same item should not be added to list",
                my_bidder_a.getBids().contains(new Bid(my_item_a, 10.05)));
  }
  
  @Test
  public void testPlaceBid_TwoBidsOnSeparateItems()
  {
    my_bidder_a.placeBid(my_item_a, 10.00);
    final Item item_b =
        new Item(2, "Item B", 1, 50.00, "Donor B", Size.MEDIUM, "Storage B",
                 Condition.ACCEPTABLE, "No comment", null);
    assertTrue("High bid on separate item should return true",
               my_bidder_a.placeBid(item_b, 100));
    assertTrue("High bid on separate item should be added to list",
               my_bidder_a.getBids().contains(new Bid(item_b, 100)));
  }
  
  @Test
  public void testPlaceBid_HighBidFromSecondBidder()
  {
    my_bidder_a.placeBid(my_item_a, 10.00);
    assertTrue("Higher bid from another bidder should return true",
               my_bidder_b.placeBid(my_item_a, 10.01));
    assertTrue("Higher bid from another bidder should be added to his list",
               my_bidder_b.getBids().contains(new Bid(my_item_a, 10.01)));
  }
  
  @Test
  public void testPlaceBid_LessThanHighBidFromSecondBidder()
  {
    my_bidder_a.placeBid(my_item_a, 100);
    assertFalse("Lower than highest bid from another bidder should return" +
                "false", my_bidder_b.placeBid(my_item_a, 75));
    assertTrue("Lower than highest bid but greater than min bid" +
               " from another bidder should be added to list",
               my_bidder_b.getBids().contains(new Bid(my_item_a, 75)));
  }
}
