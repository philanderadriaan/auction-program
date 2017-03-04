package auctioncentral;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The class represents a user who bids on auction items.
 * 
 * @author Tyson Nottingham
 * @version 28 May 2012
 *
 */
public class Bidder extends User
{
  private final List<Bid> my_bids = new ArrayList<Bid>();

  public Bidder(final String the_name)
  {
    super(the_name, UserType.BIDDER);
  }
  
  /**
   * Returns an unmodifiable list of this Bidder's bids.
   */
  public List<Bid> getBids()
  {
    return Collections.unmodifiableList(my_bids);
  }
  
  /* 
   * pre:  the_item != null
   * post: ((the_bid_amount >= the_item.getMinBid()) &&
   *        (\forall Bid bid; getBids().contains(bid);
   *            !bid.getItem().equals(the_item))) ==>
   *       ((\result == the_item.addBid(new Bid(the_item, the_bid_amount))) &&
   *        (\exists Bid bid; getBids().contains(bid);
   *            bid.getItem().equals(the_item) && bid.getAmount() == the_amount))
   *       
   *       &&
   *       
   *       ((the_bid_amount < the_item.getMinBid()) ||
   *        (\exists Bid bid; getBids().contains(bid);
   *            bid.getItem().equals(the_item))) ==> 
   *       \result == false
   */
  /**
   * Attempts to place a bid on the item. If the bid amount is greater than or
   * equal to the item's minimum bid and this Bidder has not already bid on
   * this item, then this method adds the bid to this Bidder's list of bids.
   * 
   * @return whether or not the bid is the highest bid for the item.
   */
  public boolean placeBid(final Item the_item, final double the_bid_amount)
  {
    if (!hasBidOnItem(the_item) && the_bid_amount >= the_item.getMinBid()) 
    {
      final Bid bid = new Bid(the_item, the_bid_amount);
      my_bids.add(bid);
      return the_item.addBid(this, the_bid_amount);
    }
    
    return false;
  }

  /*
   * post: (\exists Bid bid; getBids().contains(bid);
   *           bid.getItem().equals(the_item)) ==>
   *       \result == true
   *       
   *       &&
   *       
   *       (\forall Bid bid; getBids().contains(bid);
   *           !bid.getItem().equals(the_item)) ==>
 *         \result == false
   */
  public boolean hasBidOnItem(final Item the_item)
  {
    for (final Bid bid : my_bids)
    {
      if (bid.getItem().equals(the_item))
      {
        return true;
      }
    }
    
    return false;
  }
  
  /*
   * pre: the_bid != null
   * post: getBids().contains(the_bid)
   */
  /**
   * Method for adding bids to a Bidder without actually placing the bid
   * on the item, and without checking to see if the bidder has already
   * bid on this item or if the bid is greater than or equal to the
   * item's minimum bid. Convenience method for building Bidders from
   * a data file.
   */
  public void addBid(final Bid the_bid)
  {
    my_bids.add(the_bid);
  }
}
