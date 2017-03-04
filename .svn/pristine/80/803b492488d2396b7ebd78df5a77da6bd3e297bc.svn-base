package auctioncentral;
import java.text.NumberFormat;

/**
 * The class representing a bid on an auction item.
 * 
 * @author Tyson Nottingham
 * @version 28 May 2012
 *
 */
public class Bid
{
  /**
   * For formatting the bid amount into a currency string.
   */
  private static final NumberFormat FORMATTER = NumberFormat.getCurrencyInstance();
  
  private final Item my_item;

  private final double my_bid_amount;
  
  public Bid(final Item the_item, final double the_bid_amount)
  {
    my_item = the_item;
    my_bid_amount = the_bid_amount;
  }

  public Item getItem()
  {
    return my_item;
  }

  public double getAmount()
  {
    return my_bid_amount;
  }

  /*
   * post: ((the_other != null) && (the_other.getClass() == getClass()) &&
   *        (getItem().equals(((Bidder) the_other).getItem())) &&
   *        (getAmount() == ((Bidder) the_other).getAmount())) ==>
   *        \result == true
   *        &&
   *       ((the_other == null) || (the_other.getClass() != getClass()) ||
   *        (!getItem().equals(((Bidder) the_other).getItem())) ||
   *        (getAmount() != ((Bidder) the_other).getAmount())) ==>
   *        \result == false
   */
  /**
   * Returns true if the passed in object is a Bid for the same item and for
   * the same amount, else false.
   */
  public boolean equals(final Object the_other)
  {
    if (this == the_other)
    {
      return true;
    }
    else if ((the_other != null) && (the_other.getClass() == getClass()))
    {
      final Bid other = (Bid) the_other;
      return (my_item.equals(other.my_item)) &&
             (my_bid_amount == other.my_bid_amount);
    }

    return false;
  }
  
  @Override
  public int hashCode()
  {
    return toString().hashCode();
  }
  
  @Override
  public String toString()
  {
    return FORMATTER.format(my_bid_amount) + " on " + my_item;
  }
}
