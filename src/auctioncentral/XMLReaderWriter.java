package auctioncentral;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

/**
 * Class for writing and reading AuctionCentral data to and from disk.
 * 
 * @author Tyson Nottingham
 * @version 19 May 2012
 */
public class XMLReaderWriter
{
  // XML element names.
  private static final String USERS_STRING = "users";
  private static final String USER_STRING = "user";
  private static final String NAME_STRING = "name";
  private static final String TYPE_STRING = "type";
  private static final String PHONE_STRING = "phone_number";
  private static final String NPO_NAME_STRING = "npo_name";
  
  private static final String BIDS_STRING = "bids";
  private static final String BIDDER_STRING = "bidder";
  private static final String BID_STRING = "bid";
  private static final String AMOUNT_STRING = "amount";
  
  private static final String NEXT_ITEM_ID_STRING = "next_item_id";
  private static final String AUCTIONS_STRING = "auctions";
  private static final String AUCTION_STRING = "auction";
  private static final String CONTACT_NAME_STRING = "contact_name";
  private static final String CONTACT_PHONE_STRING = "contact_phone_number";
  private static final String AC_INTAKER_STRING = "ac_intaker";
  private static final String START_TIME_STRING = "start_time";
  private static final String END_TIME_STRING = "end_time";
  private static final String ITEM_COUNT_STRING = "expected_item_count";
  private static final String COMMENTS_STRING = "comments";
  private static final String NPO_STRING = "npo";

  private static final String YEAR_STRING = "year";
  private static final String MONTH_STRING = "month";
  private static final String DAY_STRING = "day";
  private static final String HOUR_STRING = "hour";
  private static final String MINUTE_STRING = "minute";
  private static final String SECOND_STRING = "second";
  
  private static final String ITEM_STRING = "item";
  private static final String ITEM_ID_STRING = "item_id";
  private static final String QUANTITY_STRING = "quantity";
  private static final String MIN_BID_STRING = "minimum_bid";
  private static final String DONOR_STRING = "donor";
  private static final String SIZE_STRING = "size";
  private static final String STORAGE_STRING = "storage";
  private static final String CONDITION_STRING = "condition";
  private static final String HIGH_BIDDER_STRING = "high_bidder";
  private static final String HIGH_BID_STRING = "high_bid";
  
  /**
   * Map from User name to Users. Since Users are initialized
   * separately from Items/Bids, this provides an efficient means for
   * Items/Bids to acquire references to their bidder.
   */
  private final Map<String, User> my_users = new HashMap<String, User>();
  
  /**
   * Map from Item ID to Item. Since Items are initialized separately
   * from Bids, this provides and efficient means for Bids to acquire
   * references to their Item.
   */
  private final Map<Integer, Item> my_items = new HashMap<Integer, Item>();
  
  private int my_next_item_id;
  
  private List<Auction> my_auctions = new ArrayList<Auction>();

  public Map<String, User> getUsers()
  {
    return my_users;
  }
  
  public Map<Integer, Item> getItems()
  {
    return my_items;
  }
  
  public List<Auction> getAuctions()
  {
    return my_auctions;
  }
  
  public int getNextItemID()
  {
    return my_next_item_id;
  }
  
  public void setNextItemID(final int the_next_item_id)
  {
    my_next_item_id = the_next_item_id;
  }
  
  public void setAuctions(final List<Auction> the_auctions)
  {
    my_auctions = the_auctions;
  }
  
  public void readUsersFromFile(final String the_file_name) throws JDOMException, IOException
  {
    final SAXBuilder builder = new SAXBuilder();
    final Document doc = builder.build(the_file_name);
    final List<Element> user_elements = doc.getRootElement().getChildren();
    
    readUsers(user_elements);
  }
  
  public void readUsers(final List<Element> the_user_elements)
  {
    for (final Element user_element : the_user_elements)
    {
      final String name = user_element.getChildText(NAME_STRING);
      final UserType type = UserType.valueOf(user_element.getChildText(TYPE_STRING));
      
      if (type == UserType.ACE)
      {
        my_users.put(name, new User(name, type));
      }
      else if (type == UserType.BIDDER)
      {
        my_users.put(name, new Bidder(name));
      }
      else
      {
        final String phone_number = user_element.getChildText(PHONE_STRING);
        final String npo_name = user_element.getChildText(NPO_NAME_STRING);
        my_users.put(name,  new NPOStaffMember(name, phone_number, npo_name));
      }
    }
  }
  
  public void readAuctionsFromFile(final String the_file_name)
    throws JDOMException, IOException
  {
    final SAXBuilder builder = new SAXBuilder();
    final Document doc = builder.build(the_file_name);
    final List<Element> auction_elements =
        doc.getRootElement().getChildren(AUCTION_STRING);
    my_next_item_id = Integer.parseInt(doc.getRootElement().
                                       getChildText(NEXT_ITEM_ID_STRING));
        
    readAuctions(auction_elements);
  }
  
  public void readAuctions(final List<Element> the_auction_elements)
  {
    for (final Element auction_element : the_auction_elements)
    {
      final String contact_name =
          auction_element.getChildText(CONTACT_NAME_STRING);
      final String contact_phone =
          auction_element.getChildText(CONTACT_PHONE_STRING);
      final String ac_intaker =
          auction_element.getChildText(AC_INTAKER_STRING);
      final Calendar start_time =
          readTime(auction_element.getChild(START_TIME_STRING));
      final Calendar end_time =
          readTime(auction_element.getChild(END_TIME_STRING));
      final String item_count =
          auction_element.getChildText(ITEM_COUNT_STRING);
      final String comments = auction_element.getChildText(COMMENTS_STRING);
      final String npo = auction_element.getChildText(NPO_STRING);
      
      final Auction auction = new Auction(contact_name, contact_phone, 
                                          ac_intaker, start_time, end_time, 
                                          item_count, comments, npo);
      
      readItems(auction, auction_element.getChildren(ITEM_STRING));
      
      my_auctions.add(auction);
    }
  }

  public Calendar readTime(final Element the_time_element)
  {
    return
        new GregorianCalendar(Integer.parseInt(the_time_element.
                                               getChildText(YEAR_STRING)),
                              Integer.parseInt(the_time_element.
                                               getChildText(MONTH_STRING)),
                              Integer.parseInt(the_time_element.
                                               getChildText(DAY_STRING)),
                              Integer.parseInt(the_time_element.
                                               getChildText(HOUR_STRING)),
                              Integer.parseInt(the_time_element.
                                               getChildText(MINUTE_STRING)),
                              Integer.parseInt(the_time_element.
                                               getChildText(SECOND_STRING)));
  }
  
  public void readItems(final Auction the_auction,
                        final List<Element> the_item_elements)
  {
    for (final Element item_element : the_item_elements)
    {
      final int item_id =
          Integer.parseInt(item_element.getChildText(ITEM_ID_STRING));
      final String name = item_element.getChildText(NAME_STRING);
      final int quantity =
          Integer.parseInt(item_element.getChildText(QUANTITY_STRING));
      final double min_bid =
          Double.parseDouble(item_element.getChildText(MIN_BID_STRING));
      final String donor = item_element.getChildText(DONOR_STRING);
      final Size size = Size.valueOf(item_element.getChildText(SIZE_STRING));
      final String storage = item_element.getChildText(STORAGE_STRING);
      final Condition condition =
          Condition.valueOf(item_element.getChildText(CONDITION_STRING));
      final String comment = item_element.getChildText(COMMENTS_STRING);
      
      final Item item = new Item(item_id, name, quantity, min_bid, donor,
                                 size, storage, condition, comment, null);
      
      if (item_element.getChild(HIGH_BIDDER_STRING) != null)
      {
        final Bidder bidder =
            (Bidder) my_users.
            get(item_element.getChildText(HIGH_BIDDER_STRING));
        final double bid =
            Double.parseDouble(item_element.getChildText(HIGH_BID_STRING));
        item.addBid(bidder, bid);
      }
      
      my_items.put(item_id, item);
      the_auction.addItem(item);
    }
  }

  public void readBidsFromFile(final String the_file_name) throws JDOMException, IOException
  {
    final SAXBuilder builder = new SAXBuilder();
    final Document doc = builder.build(the_file_name);
    final List<Element> bidder_elements = doc.getRootElement().getChildren();
    
    readBidders(bidder_elements);
  }

  public void readBidders(final List<Element> the_bidder_elements)
  {
    for (final Element bidder_element : the_bidder_elements)
    {
      final String bidder_name = bidder_element.getChildText(NAME_STRING);
      final List<Element> bid_elements = bidder_element.getChildren(BID_STRING);
      
      readBids(bid_elements, (Bidder) my_users.get(bidder_name));
    }
  }

  public void readBids(final List<Element> the_bid_elements, final Bidder the_bidder)
  {
    for (final Element bid_element : the_bid_elements)
    {
      final int item_id = Integer.parseInt(bid_element.getChildText(ITEM_ID_STRING));
      final double amount =
          Double.parseDouble(bid_element.getChildText(AMOUNT_STRING));
      the_bidder.addBid(new Bid(my_items.get(item_id), amount));
    }
  }

  public void writeUsersToFile(final String the_file_name) throws IOException
  {
    final Element root = new Element(USERS_STRING);
    appendUsers(new ArrayList<User>(my_users.values()), root);
    
    final Document doc = new Document(root);
    final OutputStream out = new BufferedOutputStream(new FileOutputStream(the_file_name));
    final XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
    outputter.output(doc, out);
    out.flush();
    out.close();
  }

  public void appendUsers(final List<User> the_users, final Element the_parent)
  {
    for (final User user : the_users)
    {
      final Element user_element = new Element(USER_STRING);
      
      appendChild(NAME_STRING, user.getName(), user_element);
      appendChild(TYPE_STRING, user.getType().toString(), user_element);
      
      if (user.getType() == UserType.NPO)
      {
        final NPOStaffMember n = (NPOStaffMember) user;
        appendChild(PHONE_STRING, n.getPhoneNumber(), user_element);
        appendChild(NPO_NAME_STRING, n.getNPO(), user_element);
      }
      
      the_parent.addContent(user_element);
    }
  }

  public void writeAuctionsToFile(final String the_file_name) throws IOException
  {
    final Element root = new Element(AUCTIONS_STRING);
    appendChild(NEXT_ITEM_ID_STRING, Integer.toString(my_next_item_id), root);
    appendAuctions(root);
    
    final Document doc = new Document(root);
    final OutputStream out = new BufferedOutputStream(new FileOutputStream(the_file_name));
    final XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
    outputter.output(doc, out);
    out.flush();
    out.close();
  }

  public void appendAuctions(final Element the_parent)
  {
    for (final Auction auction : my_auctions)
    {
      final Element auction_element = new Element(AUCTION_STRING);
      
      appendChild(NAME_STRING, auction.getName(), auction_element);
      appendChild(CONTACT_NAME_STRING, auction.getContactName(), auction_element);
      appendChild(CONTACT_PHONE_STRING, auction.getContactPhoneNumber(), auction_element);
      appendChild(AC_INTAKER_STRING, auction.getACIntaker(), auction_element);
      appendTime(START_TIME_STRING, auction.getStartTime(), auction_element);
      appendTime(END_TIME_STRING, auction.getEndTime(), auction_element);
      appendChild(ITEM_COUNT_STRING, auction.getExpectedNumberOfItems(),
                  auction_element);
      appendChild(COMMENTS_STRING, auction.getComments(), auction_element);
      appendChild(NPO_STRING, auction.getNPO(), auction_element);
      appendItems(auction.getItems(), auction_element);
      
      the_parent.addContent(auction_element);
    }
  }

  public void appendTime(final String the_time_element_name, final Calendar the_time,
                         final Element the_parent)
  {
    final Element time_element = new Element(the_time_element_name);
    
    appendChild(YEAR_STRING, Integer.toString(the_time.get(Calendar.YEAR)), time_element);
    appendChild(MONTH_STRING, Integer.toString(the_time.get(Calendar.MONTH)), time_element);
    appendChild(DAY_STRING, Integer.toString(the_time.get(Calendar.DAY_OF_MONTH)),
                time_element);
    appendChild(HOUR_STRING, Integer.toString(the_time.get(Calendar.HOUR_OF_DAY)),
                time_element);
    appendChild(MINUTE_STRING, Integer.toString(the_time.get(Calendar.MINUTE)), time_element);
    appendChild(SECOND_STRING, Integer.toString(the_time.get(Calendar.SECOND)), time_element);
    
    the_parent.addContent(time_element);
  }
  
  public void appendItems(final List<Item> the_items, final Element the_auction_element)
  {
    for (final Item item : the_items)
    {
      final Element item_element = new Element(ITEM_STRING);
      
      appendChild(ITEM_ID_STRING, Integer.toString(item.getId()), item_element);
      appendChild(NAME_STRING, item.getName(), item_element);
      appendChild(QUANTITY_STRING, Integer.toString(item.getQuantity()), item_element);
      appendChild(MIN_BID_STRING, Double.toString(item.getMinBid()), item_element);
      appendChild(DONOR_STRING, item.getDonor(), item_element);
      appendChild(SIZE_STRING, item.getSize().toString(), item_element);
      appendChild(STORAGE_STRING, item.getStorage(), item_element);
      appendChild(CONDITION_STRING, item.getCondition().toString(), item_element);
      appendChild(COMMENTS_STRING, item.getComment(), item_element);
      
      if (item.getHighBid() != -1)
      {
        appendChild(HIGH_BIDDER_STRING, item.getHighBidder().getName(), item_element);
        appendChild(HIGH_BID_STRING, Double.toString(item.getHighBid()), item_element);
      }
      
      the_auction_element.addContent(item_element);
    }
  }

  public void writeBidsToFile(final String the_file_name) throws IOException
  {
    final List<Bidder> bidders = new ArrayList<Bidder>();
    final Collection<User> users = my_users.values();
    
    for (final User user : users)
    {
      if (user.getType() == UserType.BIDDER)
      {
        bidders.add((Bidder) user);
      }
    }
    
    final Element root = new Element(BIDS_STRING);
    appendBidders(bidders, root);
    
    final Document doc = new Document(root);
    final OutputStream out = new BufferedOutputStream(new FileOutputStream(the_file_name));
    final XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
    outputter.output(doc, out);
    out.flush();
    out.close();
  }

  public void appendBidders(final List<Bidder> the_bidders, final Element the_parent)
  {
    for (final Bidder bidder : the_bidders)
    {
      final List<Bid> bids = bidder.getBids();
      
      if (bids.size() > 0)
      {
        final Element bidder_element = new Element(BIDDER_STRING);
        
        appendChild(NAME_STRING, bidder.getName(), bidder_element);
        appendBids(bids, bidder_element);
        
        the_parent.addContent(bidder_element);
      }
    }
  }

  public void appendBids(final List<Bid> the_bids, final Element the_parent)
  {
    for (final Bid bid : the_bids)
    {
      final Element bid_element = new Element(BID_STRING);
      
      appendChild(ITEM_ID_STRING, Integer.toString(bid.getItem().getId()), bid_element);
      appendChild(AMOUNT_STRING, Double.toString(bid.getAmount()), bid_element);
      
      the_parent.addContent(bid_element);
    }
  }

  public void appendChild(final String the_child_name, final String the_child_text, 
                          final Element the_parent)
  {
    final Element child = new Element(the_child_name);
    child.setText(the_child_text);
    the_parent.addContent(child);
  }
}
