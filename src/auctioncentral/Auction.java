package auctioncentral;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Store the information of an auction .
 * 
 * @author You Lin
 * @version 12 May 2012
 */
public class Auction
{
  /**
   * The auction name.
   */
  private String my_name;
  
  /**
   * Name of contract person.
   */
  private final String my_contract_name;

  /**
   * Phone number of contract person.
   */
  private final String my_contract_phone;
  
  /**
   * Name of AuctionCentral intake person.
   */
  private final String my_ac_intaker;
  
  
  /**
   * The start time of the auction.
   */
  private final Calendar my_start_time;

  /**
   * The time of the duration.
   */
  private final Calendar my_end_time;
  
  /**
   * The expected number of items.
   */
  private final String my_item_number;
  
  /**
   * The comments.
   */
  private final String my_comments;
 
  /**
   * The item list.
   */
  private final List<Item> my_items = new ArrayList<Item>();
  
  /**
   * 
   */
  private final String my_npo;
  
  
  /**
   
   *@param the_contract_name The name of contract person.
   *@param the_contract_phone The phone number of contract person.
   *@param the_time The start time of the auction.
   *@param the_end_time The end time of auction. 
   *@param the_item_number The expected number of items.
   *@param the_comments The comment of the auction.
   *@param the_npo The name of NPO
   */
  public Auction(final String the_contract_name, 
                 final String the_contract_phone,final String the_ac_intaker, final Calendar the_start_time, 
                 final Calendar the_end_time, final String the_item_number, 
                 final String the_comments, final String the_npo)
  { 
    my_contract_name = the_contract_name;
    my_contract_phone = the_contract_phone;
    my_ac_intaker = the_ac_intaker;
    my_start_time = the_start_time;
    my_end_time = the_end_time;
    my_item_number = the_item_number;
    my_comments = the_comments;
    my_npo = the_npo;
    my_name = my_npo + "-" + 
        Integer.toString(my_start_time.get(Calendar.MONTH) + 1) + "-" +
        Integer.toString(my_start_time.get(Calendar.DAY_OF_MONTH)).toString() + "-" + 
        Integer.toString(my_start_time.get(Calendar.YEAR)).toString();
  }
  
 /**
  * @return my_name. The format of auction name is "NPOname-month-date-year"
  */
  public String getName()
  {
    return my_name;
  }
  
  /**
   * @return my_contract_name.
   */
  public String getContactName()
  {
    return my_contract_name;
  }
    
  /**
   * @return my_contract_phone.
   */
  public String getContactPhoneNumber()
  {
    return my_contract_phone; 
  }
  
  /**
   * 
   * @return my_ac_intaker.
   */
  public String getACIntaker()
  {
    return my_ac_intaker;
    
  }
 
  
  /**
   * 
   * @return my_start_time.
   */
  public Calendar getStartTime()
  {
    return my_start_time;
  }
  
  /**
   * @return my_end_time.
   */
  public Calendar getEndTime()
  {
    return my_end_time;
  }
  
  /**
   * @return my_item_number.
   */
  public String getExpectedNumberOfItems()
  {
    return my_item_number;
  }
  
  /**
   * @return my_comments;
   */
  public String getComments()
  {
    return my_comments;
  }
  
  /**
   * @return my_npo;
   */
  public String getNPO()
  {
    return my_npo;
  }
  
  /**
   * 
   * @return my_items.
   */
  public List<Item> getItems()
  {
    return my_items;
  }
  
  /**
   * @param the_item An Item.
   * @return boolean.
   */
  public boolean addItem(final Item the_item)
  {
    if (my_items.contains(the_item))
    {
      return false; 
    }
    else
    {        
      my_items.add(the_item);
      return true;
    }
  }
  
  
  /**
   * Returns true if the passed in object is a auction for the same name,
   * else false.
   * 
   * @param the_other The object to which to compare.
   * @return true if the passed in object is a auction for the same name and,
   * else false.
   */
  @Override
  public boolean equals(final Object the_other)
  {
    return ((Auction) the_other).getName() == my_name;
  }

  @Override
  public int hashCode()
  {
    return toString().hashCode();
  }
  
  @Override
  public String toString()
  {
    return my_name;
  }
}