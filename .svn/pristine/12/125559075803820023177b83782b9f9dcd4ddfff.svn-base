package auctioncentral;

/**
 * Stores information of NPOStaffMember.
 * 
 * @author You Lin
 * @version 1
 */
public class NPOStaffMember extends User
{
  
 /**
  * phone number of NPOStaffMember.
  */
  private final String my_phonenumber;
  
  /**
   * the name of NPO.
   */
  private final String my_npo;
  
  /**
   * The constructor. 
   * @param the_name The user name of the NPOStaffMember.
   * @param the_phone_number The phone number of the NPOStaffMember.
   * @param the_npo The name of NPO the NPOSfaffMember belongs to.
   */
  public NPOStaffMember(final String the_name, 
                        final String the_phone_number, final String the_npo)
  {
    super(the_name, UserType.NPO);
    my_phonenumber = the_phone_number;
    my_npo = the_npo;
  }
  

  /**
   * @return phone number.
   */
  public String getPhoneNumber()
  {
    return my_phonenumber;
  }
  
  /**
   * @return NPO name.
   */
  public String getNPO()
  {
    return my_npo;
  }
  
  
}