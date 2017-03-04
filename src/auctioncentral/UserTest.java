package auctioncentral;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * 
 * @author Phil Adriaan
 * @version 1
 */
public class UserTest
{
  /**
   * First user.
   */
  private User my_user_1;
  /**
   * Second user.
   */
  private User my_user_2;

  /**
   * Creates users. The bidder type will be handled in BidderTest.java.
   */
  @Before
  public void setUp()
  {
    my_user_1 = new User("Juan", UserType.ACE);
    my_user_2 = new User("Diaz", UserType.NPO);
  }

  /**
   * Test output.
   */
  @Test
  public void testOutput()
  {
    assertEquals("Juan is an Auction Central Employee", my_user_1.toString());
    assertEquals("Diaz is a NPO Staff Member", my_user_2.toString());
  }

  /**
   * Test equals.
   */
  @Test
  public void testEquals()
  {
    assertEquals(my_user_1, new User("Juan", UserType.ACE));
    assertFalse(my_user_1.equals(my_user_2));
  }
}
