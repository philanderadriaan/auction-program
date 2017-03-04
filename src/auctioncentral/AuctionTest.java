package auctioncentral;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


/**
 * Test the Auction class.
 * @author You Lin
 * @author Tyson Nottingham (3 test methods)
 * @version May 29, 2012
 */
public class AuctionTest
{
 
  /**
   * auction a.
   */
  private Auction my_auction_a;
  
  /**
   * auction b.
   */
  private Auction my_auction_b;
  
  /**
   * item a.
   */
  private Item my_item_a;
  
  /**
   * item b.
   */
  private Item my_item_b;
  
  /**
   * setup the test fields.
   */
  @Before
  public void setup()
  {
    final Calendar auction_time_a = new GregorianCalendar();
    final Calendar auction_time_b = new GregorianCalendar();
    final Calendar auction_time_c = new GregorianCalendar();
    final Calendar auction_time_d = new GregorianCalendar();
    auction_time_a.set(2012, Calendar.MAY, 15, 9, 30, 0);
    auction_time_b.set(2012, Calendar.MAY, 15, 9, 30, 0);
    auction_time_c.set(2012, Calendar.MAY, 16, 14, 30, 0);
    auction_time_d.set(2012, Calendar.MAY, 16, 16, 30, 0);

    final NPOStaffMember npo_name_a = new NPOStaffMember("July", "206-123-4567", "ActionAid");
    final NPOStaffMember npo_name_b = new NPOStaffMember("Sam", "206-000-1111", "YonthHelp");
  
    my_auction_a = new Auction("Sophia", "425-111-2012","Chris", auction_time_a,
                                        auction_time_b, "12", "To help poor people", 
                                        npo_name_a.getNPO());
    
    my_auction_b = new Auction("David", "504-987-0000","Matt",  auction_time_c, 
                                          auction_time_d, "12", "To help Teenager", 
                                          npo_name_b.getNPO());
    
    my_item_a = new Item(10, "Item F", 2, 10.00, "xx", Size.MEDIUM,
                                 "Storage yy", Condition.GOOD, "No comment", null);
    my_item_b = new Item(15, "Item E", 3, 50.00, "xx", Size.LARGE,
                         "Storage xy", Condition.GOOD, "No comment", null);
  }
  
  /**
   * Test the Auction.equal(final Object the_other).
   */
  @Test
  public void testAuctionEqual()
  {
    assertTrue("The two auctions are the same.", my_auction_a.equals(my_auction_a)); 
    
    assertFalse("The two auctions are not the same.", my_auction_a.equals(my_auction_b));
    
    
  }
  
  /**
   * Test Auction.addItem(final the_item).
   */
  @Test
  public void testItemEqual()
  {
    assertTrue("Add Item successfully, return true", my_auction_a.addItem(my_item_b)); 
    
    assertFalse("Add Item unsuccessfully, return false", my_auction_a.addItem(my_item_b)); 
    
  }
  
  /**
   * Test auction name.
   */
  @Test
  public void testAuctionName()
  {
    assertEquals("ActionAid-5-15-2012", my_auction_a.getName());
  }
  
  /**
   * Test contact name.
   */
  @Test
  public void testContactName()
  {
    assertEquals("Sophia", my_auction_a.getContactName());
  }
  
  /**
   * Test contact phone number.
   */
  @Test
  public void testContactPhoneNumber()
  {
    assertEquals("425-111-2012", my_auction_a.getContactPhoneNumber());
  }
  
  /**
   * Test Expected Number Of Items.
   */
  @Test
  public void testExpectedNumberOfItems()
  {
    assertEquals("12", my_auction_a.getExpectedNumberOfItems());
  }
  
  /**
   * Test NPO name.
   */
  @Test
  public void testNPO()
  {
    assertEquals("ActionAid", my_auction_a.getNPO());
  }

  /*
   * @author Tyson Nottinghan
   */
  @Test
  public void testAddItem_OneItem()
  {
    assertTrue("Successful add returns true", my_auction_a.addItem(my_item_a));
    assertTrue("Item successfully added to auction's list",
               my_auction_a.getItems().contains(my_item_a));
    assertEquals("Auction contains only one item", 1,
                 my_auction_a.getItems().size());
  }
  
  /*
   * @author Tyson Nottinghan
   */
  @Test
  public void testAddItem_SameItemTwice()
  {
    my_auction_a.addItem(my_item_a);
    assertFalse("Second add unsuccessful, returns false",
                my_auction_a.addItem(my_item_a));
    assertEquals("Auction contains only one item", 1,
                 my_auction_a.getItems().size());
  }
  
  /*
   * @author Tyson Nottinghan
   */
  @Test
  public void testAddItem_MultipleItems()
  {
    assertTrue("Successful 1st add returns true",
               my_auction_a.addItem(my_item_a));
    assertTrue("Item successfully added to auction's list",
               my_auction_a.getItems().contains(my_item_a));
    assertEquals("Auction contains only one item", 1,
                 my_auction_a.getItems().size());
    
    assertTrue("Successful 2nd add returns true",
               my_auction_a.addItem(my_item_b));
    assertTrue("Item successfully added to auction's list",
               my_auction_a.getItems().contains(my_item_b));
    assertEquals("Auction contains only two items", 2,
                 my_auction_a.getItems().size());
    
    final Item item = new Item(134, "Item X", 2, 123.00, "gg", Size.MEDIUM,
                               "Storage C1", Condition.VERY_GOOD, "No comment",
                               null);
    
    assertTrue("Successful 3rd add returns true",
               my_auction_a.addItem(item));
    assertTrue("Item successfully added to auction's list",
               my_auction_a.getItems().contains(item));
    assertEquals("Auction contains only three items", 3,
                 my_auction_a.getItems().size());
    
    assertFalse("Unsuccessful 4th add returns false",
               my_auction_a.addItem(item));
    assertEquals("Auction contains only three items", 3,
                 my_auction_a.getItems().size());
  }
}
