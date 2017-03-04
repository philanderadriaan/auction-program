package auctioncentral;
import java.awt.Image;

/**
 * Stores item information.
 * 
 * @author Phil Adriaan
 * @version 1
 */
public class Item
{
  /**
   * Item ID.
   */
  private final int my_id;
  /**
   * Name of item.
   */
  private final String my_name;
  /**
   * Quantity of item.
   */
  private final int my_quantity;
  /**
   * Starting price for a bid.
   */
  private final double my_min_bid;
  /**
   * Name of donor.
   */
  private final String my_donor;
  /**
   * Approximate size of item.
   */
  private final Size my_size;
  /**
   * Address of storage.
   */
  private final String my_storage;
  /**
   * Condition of item.
   */
  private final Condition my_condition;
  /**
   * Additional comments.
   */
  private final String my_comment;
  /**
   * Picture of item.
   */
  private final Image my_photo;

  /**
   * Highest bidder.
   */
  private Bidder my_high_bidder;

  /**
   * Highest current bid.
   */
  private double my_high_bid = -1;

  /**
   * Creates a new item.
   * 
   * Pre: the_id must be unique.
   * 
   * Post: Exact same items are equal. Similar but different items are not
   * equal.
   * 
   * 
   * @param the_id Item ID.
   * @param the_name Name of item.
   * @param the_quantity Quantity of item.
   * @param the_min_bid Starting price for a bid.
   * @param the_donor Name of donor.
   * @param the_size Approximate size of item.
   * @param the_storage Address of storage.
   * @param the_condition Condition of item.
   * @param the_comment Additional comments.
   * @param the_photo Picture of item.
   */
  public Item(final int the_id, final String the_name, final int the_quantity,
              final double the_min_bid, final String the_donor, final Size the_size,
              final String the_storage, final Condition the_condition,
              final String the_comment, final Image the_photo)
  {
    my_id = the_id;
    my_name = the_name;
    my_quantity = the_quantity;
    my_min_bid = the_min_bid;
    my_donor = the_donor;
    my_size = the_size;
    my_storage = the_storage;
    my_condition = the_condition;
    my_comment = the_comment;
    my_photo = the_photo;
  }

  /**
   * 
   * @return Item ID.
   */
  public int getId()
  {
    return my_id;
  }

  /**
   * 
   * @return Name of item.
   */
  public String getName()
  {
    return my_name;
  }

  /**
   * 
   * @return Quantity of item.
   */
  public int getQuantity()
  {
    return my_quantity;
  }

  /**
   * 
   * @return Starting price for a bid.
   */
  public double getMinBid()
  {
    return my_min_bid;
  }

  /**
   * 
   * @return Name of donor.
   */
  public String getDonor()
  {
    return my_donor;
  }

  /**
   * 
   * @return Approximate size of item.
   */
  public Size getSize()
  {
    return my_size;
  }

  /**
   * 
   * @return Address of storage.
   */
  public String getStorage()
  {
    return my_storage;
  }

  /**
   * 
   * @return Condition of item.
   */
  public Condition getCondition()
  {
    return my_condition;
  }

  /**
   * 
   * @return Additional comments.
   */
  public String getComment()
  {
    return my_comment;
  }

  /**
   * 
   * @return Picture of item.
   */
  public Image getPhoto()
  {
    return my_photo;
  }

  /**
   * 
   * @return Highest bidder.
   */
  public Bidder getHighBidder()
  {
    return my_high_bidder;
  }

  /**
   * 
   * @return Highest bid.
   */
  public double getHighBid()
  {
    return my_high_bid;
  }

  /**
   * Adds a bid and the bidder information into the item. The information will
   * be stored if the bidder got the highest bid. Otherwise, it will be ignored.
   * 
   * Pre: the_bidder != null && the_bid >= 0.
   * 
   * Post: getHighBidder().equals(the_bidder) && getHighBid() == the_bid if
   * getHighBid() < 0 && the_bid >= getMinBid() || getHighBid() >= 0 && the_bid
   * > getHighBid().
   * 
   * @param the_bidder Bidder to be added.
   * @param the_bid Bid to be added.
   * @return whether the bid is added or not.
   */
  public boolean addBid(final Bidder the_bidder, final double the_bid)
  {
    if (my_high_bid < 0 && the_bid >= my_min_bid || my_high_bid >= 0 && the_bid > my_high_bid)
    {
      my_high_bidder = the_bidder;
      my_high_bid = the_bid;
      return true;
    }
    else
    {
      return false;
    }
  }

  /**
   * Pre: the_other.getClass() == Item.
   * 
   * Post: True if they are the same item, false if they are not.
   */
  @Override
  public boolean equals(final Object the_other)
  {
    return ((Item) the_other).getId() == my_id;
  }

  @Override
  public int hashCode()
  {
    return toString().hashCode();
  }

  @Override
  public String toString()
  {
    final StringBuilder builder = new StringBuilder();
    if (my_quantity == 1)
    {
      builder.append('a');
    }
    else
    {
      builder.append(my_quantity);
    }
    builder.append(' ');
    builder.append(my_name);
    if (my_quantity > 1)
    {
      if (Character.isUpperCase(my_name.charAt(my_name.length() - 1)) ||
          my_name.charAt(my_name.length() - 1) == 's')
      {
        builder.append('\'');
      }
      builder.append('s');
    }
    return builder.toString();
  }
}
