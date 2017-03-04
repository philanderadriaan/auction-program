
package auctioncentral;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JButton;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests the UI.
 * 
 * @author Phil Adriaan
 * @version 1
 */
public class BidderUITest
{
  /**
   * Start time
   */
  final Calendar my_start = new GregorianCalendar();
  /**
   * End Time
   */
  final Calendar my_end = new GregorianCalendar();
  /**
   * Auction.
   */
  private Auction my_auction;
  /**
   * Item.
   */
  private Item my_item;
  /**
   * NPO
   */
  private NPOStaffMember my_npo;
  /**
   * User interface.
   */
  private BidderUI my_ui;
  /**
   * Button for testing.
   */
  private JButton my_button;

  /**
   * Sets up everything.
   */
  @Before
  public void setUp()
  {
    my_start.set(2013, 0, 0, 0, 0, 0);
    my_end.set(2012, 0, 0, 0, 0, 0);
    my_item =
        new Item(118, "Autographed Boxing Gloves", 1, 100.00, "James Toney", Size.SMALL,
                 "My Basement", Condition.ACCEPTABLE, "Proof Available", null);
    my_npo = new NPOStaffMember("Boss", "425-093-9678", "Auction18");
    my_auction =
        new Auction("James", "425-095-6165","Matt", my_start, my_end, "12",
                    "To help terminally ill children", my_npo.getNPO());
    my_ui = new BidderUI(new Bidder("Rick"), Arrays.asList(new Auction[] {my_auction}));
  }

  /**
   * Test original panel.
   */
  @Test
  public void testNoButton()
  {
    assertEquals(PanelType.MAIN, my_ui.getPanelType());
  }

  /**
   * Creates button but don't click.
   */
  @Test
  public void testNoClick()
  {
    my_button = my_ui.createButton("Auction Index", PanelType.AUCTION_INDEX, null, null, 1);
    assertEquals(PanelType.MAIN, my_ui.getPanelType());
  }

  /**
   * Creates button and click.
   */
  @Test
  public void testClick()
  {
    my_button = my_ui.createButton("Auction Index", PanelType.AUCTION_INDEX, null, null, 1);
    my_button.doClick();
    assertEquals(PanelType.AUCTION_INDEX, my_ui.getPanelType());
  }
}
