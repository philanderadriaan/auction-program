
package auctioncentral;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import org.jdom2.JDOMException;

/**
 * Program launcher and login UI.
 * 
 * @author Tyson Nottingham
 * @version 28 May 2012
 */
public final class GeneralUI
{
  public static final int WIDTH = 800;
  public static final int HEIGHT = 600;

  private static final File USERS_FILE = new File("users.xml");
  private static final File AUCTIONS_FILE = new File("auctions.xml");
  private static final File BIDS_FILE = new File("bids.xml");

  private NPOStaffMemberUI my_npo_ui;
  
  /**
   * Map from User name to User to assist login process.
   */
  private final Map<String, User> my_users;

  private final List<Auction> my_auctions;

  private final XMLReaderWriter my_xml = new XMLReaderWriter();

  public GeneralUI() throws JDOMException, IOException
  {
    if (USERS_FILE.exists())
    {
      my_xml.readUsersFromFile(USERS_FILE.getAbsolutePath());

      if (AUCTIONS_FILE.exists())
      {
        my_xml.readAuctionsFromFile(AUCTIONS_FILE.getAbsolutePath());

        if (BIDS_FILE.exists())
        {
          my_xml.readBidsFromFile(BIDS_FILE.getAbsolutePath());
        }
      }
    }
    my_auctions = my_xml.getAuctions();
    my_users = my_xml.getUsers();
  }

  /**
   * Shows the login dialog.
   * 
   * @return the User that has logged in or null if the dialog was cancelled.
   */
  public User login()
  {
    String user_name;
    User user = null;
    
    do
    {
      user_name = 
          JOptionPane.showInputDialog(null, "User name:", "Auction Central Login",
                                      JOptionPane.PLAIN_MESSAGE);
      
      // Unless the user clicked the cancel button, check to see if the name
      // is associated with a registered user.
      if (user_name != null)
      {
        user = my_users.get(user_name);
        
        // If that user name was registered in the system...
        if (user == null)
        {
          JOptionPane.showMessageDialog(null, "User not found.",
                                        "Auction Central", JOptionPane.ERROR_MESSAGE);
        }
      }
    }
    // Until the user hits cancel or logs in successfully.
    while (user_name != null && user == null);
    
    return user;
  }
    
  public void showUI(final User the_user)
  {
    final UserType type = the_user.getType();
    
    if (type == UserType.ACE)
    {
      new AuctionCentralEmployeeUI(my_auctions);
    }
    else if (type == UserType.BIDDER)
    {
      final BidderUI ui = new BidderUI((Bidder) the_user, my_auctions);
      ui.addWindowListener(new ACWindowListener(UserType.BIDDER));
    }
    else if (type == UserType.NPO)
    {
      my_npo_ui =
          new NPOStaffMemberUI(my_xml.getNextItemID(),
                               (NPOStaffMember) the_user,  my_auctions);
      my_npo_ui.addWindowListener(new ACWindowListener(UserType.NPO));
    }
  }

  public static void main(final String[] the_args)
  {
    try
    {
      final GeneralUI gui = new GeneralUI();
      final User user = gui.login();
      
      if (user != null)
      {
        gui.showUI(user);
      }
    }
    catch (final IOException e)
    {
      System.err.println("Error reading from Auction Central " +
                         "data files. Program shutting down.");
    }
    catch (final JDOMException e)
    {
      System.err.println("Error parsing Auction Central " +
                         "data files. Program shutting down.");
    }
  }
  
  private class ACWindowListener extends WindowAdapter
  {
    final UserType my_user_type;
    
    public ACWindowListener(final UserType the_user_type)
    {
      my_user_type = the_user_type;
    }

    public void windowClosing(final WindowEvent the_event)
    {
      try
      {
        if (my_user_type == UserType.NPO)
        {
          my_xml.setNextItemID(my_npo_ui.getId());
        }
        
        my_xml.writeAuctionsToFile(AUCTIONS_FILE.getAbsolutePath());
        my_xml.writeBidsToFile(BIDS_FILE.getAbsolutePath());
      }
      catch (final IOException e)
      {
        System.err.println("Error writing Auction Central data to file.");
      }
    }
  }
}
