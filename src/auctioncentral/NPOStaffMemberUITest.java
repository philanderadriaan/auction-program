
package auctioncentral;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Phil Adriaan
 * @version 1.
 */
public class NPOStaffMemberUITest
{
  private Calendar my_now;
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
  private NPOStaffMemberUI my_ui;

  /**
   * Sets up the UI.
   */
  @Before
  public void setUp()
  {
    my_now = Calendar.getInstance();
    my_start.set(my_now.get(Calendar.YEAR), my_now.get(Calendar.MONTH),
                 my_now.get(Calendar.DATE) + 1, my_now.get(Calendar.HOUR),
                 my_now.get(Calendar.MINUTE));
    my_end.set(my_now.get(Calendar.YEAR), my_now.get(Calendar.MONTH),
               my_now.get(Calendar.DATE) + 1, my_now.get(Calendar.HOUR) + 1,
               my_now.get(Calendar.MINUTE));
    my_item =
        new Item(118, "Autographed Boxing Gloves", 1, 100.00, "James Toney", Size.SMALL,
                 "Old School Gym", Condition.ACCEPTABLE, "Proof Available", null);
    my_npo = new NPOStaffMember("Boss", "425-093-9678", "Room 34");
    my_auction =
        new Auction("James", "425-095-6165", "Matt", my_start, my_end, "34",
                    "To help terminally ill children", my_npo.getNPO());
    my_ui = new NPOStaffMemberUI(0, my_npo, Arrays.asList(new Auction[] {my_auction}));
  }

  @Test
  public void testCheckYear()
  {
    assertFalse(my_ui.checkAuction_year(my_start));
  }
}
