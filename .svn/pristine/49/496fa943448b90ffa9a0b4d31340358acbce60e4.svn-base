package auctioncentral;

import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import org.jdom2.JDOMException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

/**
 * The test cases for the XMLReaderWriter class. In the interest of time, only
 * the top level public methods are tested. This indirectly but superficially
 * tests all of the helper methods because they are called as a result of the
 * call to the top level methods.
 * 
 * Also in the interest of time, the tests do not cover all of the partitions
 * of input data, such as empty input files, or files with only one element of
 * any particular type.
 * 
 * @author Tyson Nottingham
 * @version 28 May 2012
 * 
 */
public class XMLReaderWriterTest
{
  private XMLReaderWriter my_xml;
  
  @Before
  public void setup()
  {
    my_xml = new XMLReaderWriter();
  }
  
  /**
   * Tests that readUsersFromFile() successfully reads in all users,
   * creates the users with the correct classes, and with all of the
   * correct associated data.
   */
  @Test
  public void testReadUsersFromFile()
  {
    try
    {
      my_xml.readUsersFromFile("usersTestData.xml");
      
      final Map<String, User> users = my_xml.getUsers();
      assertEquals("Size of user list is 6", 6, users.size());
      
      final User tyson = users.get("Tyson");
      assertNotNull("User 'Tyson' successfully created?", tyson);
      assertEquals("Tyson's name correct?", "Tyson", tyson.getName());
      assertEquals("Tyson's type correct?", UserType.BIDDER, tyson.getType());
      assertEquals("Tyson's class correct?", Bidder.class, tyson.getClass());
      
      final User you = users.get("You");
      assertNotNull("User 'You' successfully created?", you);
      assertEquals("You's name correct?", "You", you.getName());
      assertEquals("You's type correct?", UserType.NPO, you.getType());
      assertEquals("You's class correct?", NPOStaffMember.class,
                   you.getClass());
      assertEquals("You's phone number correct?", "(555) 555-5555",
                   ((NPOStaffMember) you).getPhoneNumber());
      assertEquals("You's NPO name correct?", "ActionAid",
                   ((NPOStaffMember) you).getNPO());
      
      final User phil = users.get("Phil");
      assertNotNull("User 'Phil' successfully created?", phil);
      assertEquals("Phil's name correct?", "Phil", phil.getName());
      assertEquals("Phil's type correct?", UserType.BIDDER, phil.getType());
      assertEquals("Phil's class correct?", Bidder.class, phil.getClass());
      
      final User art = users.get("Art Vandelay");
      assertNotNull("User 'Art Vandelay' successfully created?", art);
      assertEquals("Art's name correct?", "Art Vandelay", art.getName());
      assertEquals("Art's type correct?", UserType.BIDDER, art.getType());
      assertEquals("Art's class correct?", Bidder.class, art.getClass());
      
      final User david = users.get("David Putty");
      assertNotNull("User 'David Putty' successfully created?", david);
      assertEquals("David's name correct?", "David Putty", david.getName());
      assertEquals("David's type correct?", UserType.NPO, david.getType());
      assertEquals("David's class correct?", NPOStaffMember.class,
                   david.getClass());
      assertEquals("David's phone number correct?", "(452) 522-6643",
                   ((NPOStaffMember) david).getPhoneNumber());
      assertEquals("David's NPO name correct?", "Ask the 8-Ball",
                   ((NPOStaffMember) david).getNPO());
      
      final User joe = users.get("Joe Davola");
      assertNotNull("User 'Joe Davola' successfully created?", joe);
      assertEquals("Joe's name correct?", "Joe Davola", joe.getName());
      assertEquals("Joe's type correct?", UserType.ACE, joe.getType());
      assertEquals("Joe's class correct?", User.class, joe.getClass());
    }
    catch (final JDOMException e)
    {
      fail("JDOMException");
    }
    catch (final IOException e)
    {
      fail("IOException");
    }
  }
  
  /**
   * Tests that readAuctionFromFile() successfully reads in all auctions and
   * items, and with all of the correct associated data.
   */
  @Test
  public void testReadAuctionsFromFile()
  {
    try
    {
      my_xml.readUsersFromFile("usersTestData.xml");
      my_xml.readAuctionsFromFile("auctionsTestData.xml");
      
      final List<Auction> auctions = my_xml.getAuctions();
      assertEquals("Size of auction list is 2", 2, auctions.size());
      
      //Test that the auctions were correctly initialized.
      final Auction auction1 = auctions.get(0);
      assertEquals("1st auction contact name",
                   "Sophia", auction1.getContactName());
      assertEquals("1st auction contact phone",
                   "425-111-2012", auction1.getContactPhoneNumber());
      assertEquals("1st auction start time",
                   new GregorianCalendar(2012, 5, 15, 9, 30, 0),
                   auction1.getStartTime());
      assertEquals("1st auction end time",
                   new GregorianCalendar(2012, 5, 15, 12, 30, 0),
                   auction1.getEndTime());
      assertEquals("1st auction expected item count", "12",
                   auction1.getExpectedNumberOfItems());
      assertEquals("1st auction comments", "To help poor people",
                   auction1.getComments());
      assertEquals("1st auction npo name", "ActionAid",
                   auction1.getNPO());
      
      final Auction auction2 = auctions.get(1);
      assertEquals("2nd auction contact name", "David",
                   auction2.getContactName());
      assertEquals("2nd auction contact phone", "504-987-0000",
                   auction2.getContactPhoneNumber());
      assertEquals("2nd auction start time",
                   new GregorianCalendar(2012, 5, 16, 14, 30, 0),
                   auction2.getStartTime());
      assertEquals("2nd auction end time",
                   new GregorianCalendar(2012, 5, 16, 16, 30, 0),
                   auction2.getEndTime());
      assertEquals("2nd auction expected item count", "14",
                   auction2.getExpectedNumberOfItems());
      assertEquals("2nd auction comments", "", auction2.getComments());
      assertEquals("2nd auction npo name", "YouthHelp",
                   auction2.getNPO());
      
      
      // Test that the items for each auction were correctly initialized.
      final List<Item> auction1_items = auction1.getItems();
      assertEquals("1st auction contains 2 items", 2,
                   auction1_items.size());
      
      final Item item1 = auction1_items.get(0);
      assertEquals("1st item id", 81, item1.getId());
      assertEquals("1st item name", "Kawasaki Ninja 250R", item1.getName());
      assertEquals("1st item quantity", 10, item1.getQuantity());
      assertEquals("1st item minimum bid", 10000.0, item1.getMinBid(), 0);
      assertEquals("1st item donor", "Anonymous", item1.getDonor());
      assertEquals("1st item size", Size.LARGE, item1.getSize());
      assertEquals("1st item storage", "Classified", item1.getStorage());
      assertEquals("1st item condition", Condition.NEW, item1.getCondition());
      assertEquals("1st item comments", "4th gen", item1.getComment());
      assertEquals("1st item high bidder", new Bidder("Phil"),
                   item1.getHighBidder());
      assertEquals("1st item high bid", 10000.01, item1.getHighBid(), 0);
      
      final Item item2 = auction1_items.get(1);
      assertEquals("2nd item id", 82, item2.getId());
      assertEquals("2nd item name", "iPhone", item2.getName());
      assertEquals("2nd item quantity", 1, item2.getQuantity());
      assertEquals("2nd item minimum bid", 200.0, item2.getMinBid(), 0);
      assertEquals("2nd item donor", "Anonymous", item2.getDonor());
      assertEquals("2nd item size", Size.SMALL, item2.getSize());
      assertEquals("2nd item storage", "Classified", item2.getStorage());
      assertEquals("2nd item condition", Condition.NEW, item2.getCondition());
      assertEquals("2nd item comments", "2nd gen", item2.getComment());
      assertEquals("2nd item high bidder", new Bidder("Tyson"),
                   item2.getHighBidder());
      assertEquals("2nd item high bid", 250.0, item2.getHighBid(), 0);
      
      
      final List<Item> auction2_items = auction2.getItems();
      assertEquals("2nd auction contains 1 item", 1,
                   auction2_items.size());
      
      final Item item3 = auction2_items.get(0);
      assertEquals("3rd item id", 12, item3.getId());
      assertEquals("3rd item name", "Nikon Coolpix S4000", item3.getName());
      assertEquals("3rd item quantity", 1, item3.getQuantity());
      assertEquals("3rd item minimum bid", 100.0, item3.getMinBid(), 0);
      assertEquals("3rd item donor", "Bob Sacamano", item3.getDonor());
      assertEquals("3rd item size", Size.SMALL, item3.getSize());
      assertEquals("3rd item storage", "Storage A", item3.getStorage());
      assertEquals("3rd item condition", Condition.LIKE_NEW,
                   item3.getCondition());
      assertEquals("3rd item comments", "Red", item3.getComment());
      assertEquals("3rd item high bidder", new Bidder("Tyson"),
                   item3.getHighBidder());
      assertEquals("3rd item high bid", 300.0, item3.getHighBid(), 0);
    }
    catch (final JDOMException e)
    {
      fail("JDOMException");
    }
    catch (final IOException e)
    {
      fail("IOException");
    }
  }
  
  /**
   * Tests that readBidsFromFile() successfully adds the bids to the bidders,
   * and that the highest bids are correctly recorded in the items.
   */
  @Test
  public void testReadBidsFromFile()
  {
    try
    {
      my_xml.readUsersFromFile("usersTestData.xml");
      my_xml.readAuctionsFromFile("auctionsTestDataForBidsTestData.xml");
      my_xml.readBidsFromFile("bidsTestData.xml");
      
      final Map<Integer, Item> items = my_xml.getItems();
      final Map<String, User> users = my_xml.getUsers();
      
      // Test that Tyson's bids were added and set correctly.
      final Bidder tyson = (Bidder) users.get("Tyson");
      final List<Bid> tysons_bids = tyson.getBids();
      final Bid tysons_bid_1 = tysons_bids.get(0);
      assertEquals("Tyson's 1st bid on correct item", items.get(81),
                   tysons_bid_1.getItem());
      assertEquals("Tyson's bid has correct amount", 10000.0,
                   tysons_bid_1.getAmount(), 0);
      
      final Bid tysons_bid_2 = tysons_bids.get(1);
      assertEquals("Tyson's 2nd bid on correct item", items.get(82),
                   tysons_bid_2.getItem());
      assertEquals("Tyson's 2nd bid has correct amount", 250.0,
                   tysons_bid_2.getAmount(), 0);
      
      final Bid tysons_bid_3 = tysons_bids.get(2);
      assertEquals("Tyson's 3rd bid on correct item", items.get(12),
                   tysons_bid_3.getItem());
      assertEquals("Tyson's 3rd bid has correct amount", 300,
                   tysons_bid_3.getAmount(), 0);
      
      final Bid tysons_bid_4 = tysons_bids.get(3);
      assertEquals("Tyson's 4th bid on correct item", items.get(83),
                   tysons_bid_4.getItem());
      assertEquals("Tyson's 4th bid has correct amount", 300,
                   tysons_bid_4.getAmount(), 0);
      
      // Test that Phil's bids were added and set correctly.
      final Bidder phil = (Bidder) users.get("Phil");
      final List<Bid> phils_bids = phil.getBids();
      final Bid phils_bid_1 = phils_bids.get(0);
      assertEquals("Phil's 1st bid on correct item", items.get(81),
                   phils_bid_1.getItem());
      assertEquals("Phil's bid has correct amount", 10000.01,
                   phils_bid_1.getAmount(), 0);
      
      final Bid phils_bid_2 = phils_bids.get(1);
      assertEquals("Phil's 2nd bid on correct item", items.get(82),
                   phils_bid_2.getItem());
      assertEquals("Phil's 2nd bid has correct amount", 200.0,
                   phils_bid_2.getAmount(), 0);
      
      final Bid phils_bid_3 = phils_bids.get(2);
      assertEquals("Phil's 3rd bid on correct item", items.get(90),
                   phils_bid_3.getItem());
      assertEquals("Phil's 3rd bid has correct amount", 1001,
                   phils_bid_3.getAmount(), 0);
      
      final Bid phils_bid_4 = phils_bids.get(3);
      assertEquals("Phil's 4th bid on correct item", items.get(83),
                   phils_bid_4.getItem());
      assertEquals("Phil's 4th bid has correct amount", 299,
                   phils_bid_4.getAmount(), 0);
      
      // Check that items have correct highest bid amount and bidder.
      final Item item81 = items.get(81);
      assertEquals("Item 81 has correct highest bid amount",
                   10000.01, item81.getHighBid(), 0);
      assertEquals("Item 81 has correct highest bidder",
                   phil, item81.getHighBidder());
      
      final Item item82 = items.get(82);
      assertEquals("Item 82 has correct highest bid amount",
                   250, item82.getHighBid(), 0);
      assertEquals("Item 82 has correct highest bidder",
                   tyson, item82.getHighBidder());
      
      final Item item12 = items.get(12);
      assertEquals("Item 12 has correct highest bid amount",
                   300.0, item12.getHighBid(), 0);
      assertEquals("Item 12 has correct highest bidder",
                   tyson, item12.getHighBidder());
      
      final Item item83 = items.get(83);
      assertEquals("Item 83 has correct highest bid amount",
                   300, item83.getHighBid(), 0);
      assertEquals("Item 83 has correct highest bidder",
                   tyson, item83.getHighBidder());
      
      final Item item90 = items.get(90);
      assertEquals("Item 83 has correct highest bid amount",
                   1001.0, item90.getHighBid(), 0);
      assertEquals("Item 83 has correct highest bidder",
                   phil, item90.getHighBidder());
    }
    catch (final JDOMException e)
    {
      fail("JDOMException");
    }
    catch (final IOException e)
    {
      fail("IOException");
    }
  }
}
