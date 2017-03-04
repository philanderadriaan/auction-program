package auctioncentral;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;


import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;


/**
 * @author You Lin
 * @version May 29, 2012
 */

public class NPOStaffMemberUI extends JFrame
{
  /**
   * The auction list which includes all auctions.
   */
  private List<Auction> my_auction_list;
  /**
   * the current panel.
   */
  private JPanel my_panel;
  private NPOStaffMember my_npomember;
  private Auction my_auction;
  private Item my_item;
  private int my_item_id;
  private JButton my_button;
  private static final int ten = 10;
  private static final int twenty = 20;
  private List<Item> my_item_list; 
  private ButtonType my_type;
 
  private JTextField my_item_name;
  private JTextField my_item_quantity;
  private JTextField my_item_min_bid;
  private JTextField my_item_donor;
  private JTextField my_item_storage;
  
  private JComboBox<String> my_item_size; 
  private JComboBox<String> my_item_condition; 
  private JTextArea my_item_comments; 
  
  private JTextField my_item_image_path;
  private Image my_item_image;
  private String[] my_item_label = new String[11] ;
  
  private String[] my_auction_label;
  
  private JTextField my_contact_person = new JTextField(20);
  private JTextField my_contact_phone = new JTextField(20);
  private JTextField my_ac_intaker = new JTextField(10);
  private JComboBox<String> my_auction_date;
  private JComboBox<String> my_auction_month;
  private JComboBox<String> my_auction_year;
  private JComboBox<String> my_auction_start_time;
  private JComboBox<String> my_auction_end_time;
  private JTextField my_auction_item_numbers = new JTextField(5);
  private JTextArea my_auction_comments = new JTextArea(5,20);
  
  private int my_select_day;
  private int my_select_month;
  private int my_select_year;
  private int my_select_start;
  private int my_select_end;
  
  private int my_current_day;
  private int my_current_month;
  private int my_current_year;
  private int my_current_start;
  private int my_current_end;
  
  
  
  
  /**
   * The constructor of class NPOStaffMember. 
   * @param the_member
   * @param the_auction
   */
  public NPOStaffMemberUI(final int the_item_id, final NPOStaffMember the_NPOmember, final List<Auction> the_auction_list)
  {
    my_npomember = the_NPOmember;
    my_auction_list = the_auction_list;
    my_item_id = the_item_id;
    setAuctionPanel();
    refresh();
    final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    setLocation((int) screen.getWidth() / 2 - GeneralUI.WIDTH / 2, (int) screen.getHeight() /
                                                                   2 - GeneralUI.HEIGHT / 2);
    this.setResizable(false);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
  }
  
  /**
   * 
   * @return int my item id
   */
  public int getId()
  {
    return my_item_id;
  }
  
  /**
   * the NPOStaffMember home page which will show 
   * the auction if the use add it before.
   */
  private void setAuctionPanel()
  {  
    final JPanel panel = new JPanel(new GridBagLayout());
    setTitle("NPOStaffMember's Home");
    final GridBagConstraints location = new GridBagConstraints();
    
    JLabel welcome = new JLabel(my_npomember.getName() + ", welcome to NPOStaffMember's Home.");
    location.gridx = 0;
    location.gridy = 0;
    location.insets = new Insets(ten, ten, ten, ten);
    welcome.setFont(createFont(welcome, 1.5));
    panel.add(welcome, location);
    
    final int index = checkAuction();
    
    label();
    nowtime();
    
    //check there is no the_npo auction or the_npo auction is one year ago.
    if (index == -1)
    {
      location.gridx = 0;
      location.gridy = 1;
      panel.add(isInput(), location);
    }
    else if (checkAuction_year(my_auction_list.get(index).getStartTime()))
    {
      location.gridx = 0;
      location.gridy = 1;
      panel.add(isInput(), location);
    }
    else if (Calendar.getInstance().compareTo(my_auction_list.get(index).getEndTime()) < 0)
    { 
      my_auction = my_auction_list.get(index);
      my_item_list = my_auction.getItems();
      
      location.gridx = 0;
      location.gridy = 1;
      JLabel label = new JLabel("The Auction " + "\"" + my_auction.getName() + "\"" + 
          " is coming");
      label.setFont(createFont(label, 1.2));
      panel.add(label, location);

      location.gridy = 2;
      panel.add(setAuctionDetailPanel(), location);  
      
      my_button = createButton("Detail of Item List", ButtonType.Auction_detail, -1);
      location.gridy = 3;
      location.insets = new Insets(ten, ten, ten, ten);
      panel.add(my_button, location);

    } 
    my_panel = panel;   
  }
  
  /**
   * It is a part of NOPStaffMember' home page
   * It will determine there is already 25 auctions.
   * @return a JPanel  
   */
  private JPanel isInput()
  {
    final JPanel panel = new JPanel(new GridBagLayout());
    final GridBagConstraints location = new GridBagConstraints();
    int count = 0;
    for(Auction i : my_auction_list)
    {
      if (i.getEndTime().after(Calendar.getInstance()))
      {
        count++;
      }   
    }
    
    if(count > 24)
    {
      JLabel status = new JLabel("The auction schedule is full, please add your auction later"); 
      location.gridx = 0;
      location.gridy = 1;
      location.insets = new Insets(ten, ten, ten, ten);
      status.setFont(createFont(status, 1.2));
      panel.add(status, location);  
    }
    else
    {
      JLabel status = new JLabel("There is no Auction in your schedule"); 
      location.gridx = 0;
      location.gridy = 1;
      location.insets = new Insets(ten, ten, ten, ten);
      status.setFont(createFont(status, 1.2));
      panel.add(status, location);  
      
      my_button = createButton("Add a New Auction", ButtonType.Add_new_auction,-1); 
      location.gridy = 2;
      location.insets = new Insets(ten, ten, ten, ten);
      panel.add(my_button, location);
    }
    
    return panel;
  }
  
  
  /**
   * auction and item panels' labels.
   */
  private void label()
  {
    my_auction_label = new String[]{"Auction Information", "Auction Name : ", 
        "Contact's Person Name: (required)", 
        "Contact's Phone: (required)",
        "AuctionCenteral Intake Person: ",
        "Auction Date: (required)", 
        "Auction Start Time: (required)",
        "Auction End Time: (required)", 
        "Anticipated number of items: ", "Comments: "};
    
    my_item_label = new String[]{"Auction Name : ", "Item ID", 
        "Item Name (required) : ", 
        "Item Quantity (required) : ", 
        "Minimum Starting Bid (required) : ", 
        "Donor: ", "Size: ",
        "Storage: ", "Condition: ", 
        "Comments: ", "Photo:(attach) "};
    
  }
  
  /**
   * check whether the npo has create an auction before. 
   * @return the index of the auction in the auction list 
   * if the npo has create an auction before.Otherwise return -1.
   */
  private int checkAuction()
  {
    for (int i = 0; i < my_auction_list.size(); i++)
    {
      if (my_auction_list.get(i).getNPO().equals(my_npomember.getNPO()))
      {
        return i;
      }   
    }    
    return -1;
  }
  
 
 /**
  * check the the date of existed auction's is before one year or not.
  * @param the_date
  * @return true if the date of existed auction's is before one year, otherwise false;
  */
  public boolean checkAuction_year(Calendar the_date)
  {
    final Calendar auction_time = new GregorianCalendar();
    final int current_d = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
    final int current_y = Calendar.getInstance().get(Calendar.YEAR);
    final int current_m = Calendar.getInstance().get(Calendar.MONTH) + 1;
    
    auction_time.set(current_y - 1, current_m, current_d, 0, 0, 0);
    
    return the_date.before(auction_time);
  }
    
  
  /**
   * Input Auction information panel. 
   */
  private void setAuctionInformPanel()
  {
    final JPanel panel = new JPanel(new GridBagLayout());
    setTitle("Auction Information Input Page");
    final GridBagConstraints location = new GridBagConstraints();
       
    location.gridx = 0;
    location.gridy = 0;
    panel.add(auctionFormMain(), location);
        
        
    location.gridx = 1;
    location.gridy = 2;
    panel.add(createButton("Submit", ButtonType.Submit_auction,-1), location);
    
    location.gridx = 2;
    location.gridy = 2;
    location.insets= new Insets(ten, ten, ten, ten);
    panel.add(createButton("Cancel", ButtonType.NPOHome, -1), location);
    
    my_panel = panel;
  }
  
  /**
   * A part of Input Auction information panel.
   * @return A part of Input Auction information panel.
   */
  private JPanel auctionFormMain()  
  {
    final JPanel panel = new JPanel(new GridBagLayout());
    final GridBagConstraints location = new GridBagConstraints();
  
    for (int i = 0; i < ten; i++)
    {
      location.gridx = 0;
      location.gridy = i;
      location.insets = new Insets(ten, ten, ten, ten);
      JLabel label = new JLabel(my_auction_label[i]);
      label.setFont(createFont(label, 1.2));
      panel.add(label, location);         
    }
    
    location.gridx = 1; 
    location.gridy = 2; // contact person
    panel.add(my_contact_person, location);
   
    location.gridy = 3; // contact phone number
    panel.add(my_contact_phone, location);

    
    location.gridy = 4; //AuctionCentral intake person   
    panel.add(my_ac_intaker, location);
 
    location.gridy = 5; // date
    panel.add(timePanel(), location);
   
    location.gridy = 6; //time
    final String[] time1 = {"9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00"};
    my_auction_start_time = new JComboBox<String>(time1);
    panel.add(my_auction_start_time, location);
  
    location.gridy = 7;
    final String[] time2 = {"10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00"};
    my_auction_end_time = new JComboBox<String>(time2);
    panel.add(my_auction_end_time, location);
    
    location.gridy = 8;
    panel.add(my_auction_item_numbers, location);
    
    location.gridy = 9;
    panel.add(my_auction_comments, location);
    
    return panel; 
  }
  
  /**
   * A part of Input Auction information panel.
   * @return time panel.
   */
  private JPanel timePanel()
  {
    final JPanel time_panel = new JPanel(new GridLayout(2, 3, 10, 10));
    
    time_panel.add(new JLabel("  Month"));
    time_panel.add(new JLabel("  Date"));
    time_panel.add(new JLabel("  Year"));
    
    final String[] month = {"1", "2", "3", "4", "5", "6", 
                            "7", "8", "9", "10", "11", "12"};
    my_auction_month = new JComboBox<String>(month);
    time_panel.add(my_auction_month);
    
    final String[] date = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11",
                           "12",  "13", "14", "15", "16", "17", "18", "19", "20", 
                           "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
    my_auction_date = new JComboBox<String>(date);
    time_panel.add(my_auction_date);
    
    final String[] year = {"2012", "2013"};
    my_auction_year = new JComboBox<String>(year);
    time_panel.add(my_auction_year);
    
    return time_panel;
  }
  
  /**
   * get input information from the input auction panel.
   */
  private boolean setAuction()
  {
    final String contact_name = my_contact_person.getText().trim();
    if(contact_name.length() == 0)
    {
      JOptionPane.showMessageDialog(null, "Please enter contact person name",
                                    "Auction Central", JOptionPane.ERROR_MESSAGE);
      return false;
      
    }
    
    final String contact_phone = my_contact_phone.getText().trim();
    if(contact_phone.length() == 0)
    {
      JOptionPane.showMessageDialog(null, "Please enter contact person phone number",
                                    "Auction Central", JOptionPane.ERROR_MESSAGE);
      return false;
      
    }
    selectedtime();
    if (my_select_start >= my_select_end)
    {
      JOptionPane.showMessageDialog(null, "Start time can't be later then end time",
                                    "Auction Central", JOptionPane.ERROR_MESSAGE);
      return false;
    }
    
    if(my_current_month > my_select_month | (my_current_month == my_select_month && my_current_day > my_select_day))
    {
      JOptionPane.showMessageDialog(null, "The date has passed.",
                                    "Auction Central", JOptionPane.ERROR_MESSAGE);
      return false;
      
    }
    
    if(my_select_month - (my_current_month) > 3 | ((my_select_month - (my_current_month)) == 3 && (my_select_day > my_current_day)))
    {
      JOptionPane.showMessageDialog(null, "Auction only can be scheduled in three months.",
                                    "Auction Central", JOptionPane.ERROR_MESSAGE);
      return false;
    }
    
    if(WrongDayCheck())
    {
      
      JOptionPane.showMessageDialog(null, "No that day",
                                    "Auction Central", JOptionPane.ERROR_MESSAGE);
      return false;
      
    }
    
    if(limitation() == false)
    {
      return false;
    }
    
    final Calendar auction_time_start = new GregorianCalendar();
    auction_time_start.set(my_select_year, my_select_month-1, my_select_day, my_select_start, 0, 0);
    
    final Calendar auction_time_end = new GregorianCalendar();
    auction_time_end.set(my_select_year, my_select_month-1, my_select_day, my_select_end, 0, 0);
    
   
    final String item_number = my_auction_item_numbers.getText().trim();
    
    
    final Auction auction = new Auction(my_contact_person.getText().trim(), 
                                   my_contact_phone.getText().trim(), 
                                   my_ac_intaker.getText().trim(),
                                   auction_time_start, auction_time_end, 
                                   item_number, my_auction_comments.getText().trim(), 
                                   my_npomember.getNPO());
    
    my_auction = auction;
    my_auction_list.add(auction);
    return true;
  }
  
  /**
   * helper method to get current time.
   */
  private void nowtime()
  {
    my_current_month = Calendar.getInstance().get(Calendar.MONTH) + 1;
    my_current_day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
    my_current_day = Calendar.getInstance().get(Calendar.YEAR);
    
  }
  
  /**
   * helper method to get selected time.
   */
  private void selectedtime()
  {
    my_select_day = my_auction_date.getSelectedIndex() + 1;
    my_select_month = my_auction_month.getSelectedIndex() + 1;
    my_select_year = my_auction_year.getSelectedIndex() + 2012;

    my_select_start = my_auction_start_time.getSelectedIndex() + 9;
    
    my_select_end = my_auction_end_time.getSelectedIndex() + ten;
    
  }
  
  /**
   * helper method to check the user choose in 28 day for Feb, in 30 day for April,June,Sep,and Nov.
   * @return true if it is wrong day.otherwise false.
   */
  private boolean WrongDayCheck()
  {
    if((my_select_month == 2 && my_select_day > 28))
    {
      return true;
    }
    if(my_select_month == 4 && my_select_day == 31)
    {
      return true;
    }
    if(my_select_month == 6 && my_select_day == 31)
    {
      return true;
    }
    if(my_select_month == 9 && my_select_day == 31)
    {
      return true;
    }
    if(my_select_month == 11 && my_select_day == 31)
    {
      return true;
    }
    
    return false;
  }
  
  /**
   * Some of business rules check.
   * @return false if violate the business rule.Otherwise true.
   */
  private boolean limitation()
  {
    final Calendar auction_time = new GregorianCalendar();
    auction_time.set(my_select_year, my_select_month-1, my_select_day, 0, 0, 0);
    
    if(areLessThanAuctionsInSevenDayPeriod(auction_time) == false)
    {
      JOptionPane.showMessageDialog(null, "Auctions in 7 day period are full",
                                    "Auction Central", JOptionPane.ERROR_MESSAGE);
      return false;
    }
    
    for(int i = 0; i < my_auction_list.size(); i++)
    {
      int auction_freq = 0;
      
      int not_end_time = my_auction_list.get(i).getEndTime().get(Calendar.HOUR_OF_DAY) + 2;
      int not_start_time = my_auction_list.get(i).getStartTime().get(Calendar.HOUR_OF_DAY)-2;
      
      if(my_current_month == my_select_month 
          && my_current_day == my_select_day )
      {
        
        auction_freq++;
        if(auction_freq >=2)
        {
          JOptionPane.showMessageDialog(null, "The auction schedule of this day is full",
                                        "Auction Central", JOptionPane.ERROR_MESSAGE);
          return false;
           
        }
        else if((my_select_start < not_end_time && my_select_start > not_start_time) |
            (my_select_end > not_start_time && my_select_end < not_end_time))
        {
          
          JOptionPane.showMessageDialog(null, "from " + (my_auction_list.get(i).getStartTime().get(Calendar.HOUR_OF_DAY) - 2) +
                                        " to "+ (my_auction_list.get(i).getEndTime().get(Calendar.HOUR_OF_DAY) + 2) + 
                                        " is not available ", "Auction Central", JOptionPane.ERROR_MESSAGE);
          return false;
          
        }
      }
      
    }
    
    return true;
    
  }
  
  /**
   * Help method for Method limitation().
   * @param the_auction_date
   * @return true if less then 5 auction in any 7 days.
   */
  public boolean areLessThanAuctionsInSevenDayPeriod(final Calendar the_auction_date)
  {
    final Calendar first_day_in_seven_day_period =
        (Calendar) the_auction_date.clone();
    first_day_in_seven_day_period.add(Calendar.DAY_OF_MONTH, -4);

    final Calendar last_day_in_seven_day_period =
        (Calendar) the_auction_date.clone();
    last_day_in_seven_day_period.add(Calendar.DAY_OF_MONTH, 4);
    
    if (auctionsInPeriodByDays(first_day_in_seven_day_period,
                               last_day_in_seven_day_period) < 5)
    {
      return true;
    }
    
    return false;
  }
  
  /**
   * helper method for areLessThanAuctionsInSevenDayPeriod().
   * @param the_first_day_in_period
   * @param the_last_day_in_period
   * @return auctions number In 7 days period. 
   */
  public int auctionsInPeriodByDays(final Calendar the_first_day_in_period,
                                    final Calendar the_last_day_in_period)
  {
    final Calendar first_second_in_period = (Calendar) the_first_day_in_period.clone();
    first_second_in_period.set(Calendar.HOUR_OF_DAY, 0);
    first_second_in_period.set(Calendar.MINUTE, 0);
    first_second_in_period.set(Calendar.SECOND, 0);
    
    final Calendar last_second_in_period = (Calendar) the_last_day_in_period.clone();
    first_second_in_period.set(Calendar.HOUR_OF_DAY, 23);
    last_second_in_period.set(Calendar.MINUTE, 59);
    last_second_in_period.set(Calendar.SECOND, 59);

    int number_of_auctions = 0;
    for (final Auction auction : my_auction_list)
    {
      if ((auction.getStartTime().equals(first_second_in_period) ||
          (auction.getStartTime().after(first_second_in_period) &&
          auction.getStartTime().before(last_second_in_period)) ||
          auction.getStartTime().equals(last_second_in_period)) ||
          (auction.getEndTime().equals(first_second_in_period) ||
          (auction.getEndTime().after(first_second_in_period) &&
          auction.getEndTime().before(last_second_in_period)) ||
          auction.getEndTime().equals(last_second_in_period)))
      {
        number_of_auctions++;
      }
    }
    
    return number_of_auctions;
  }
  
  /**
   * Auction detail panel.
   */
  private JPanel setAuctionDetailPanel()
  {
    final JPanel panel = new JPanel(new GridBagLayout());
    final GridBagConstraints location = new GridBagConstraints();
    
    for (int i = 0; i < ten; i++)
    {
      location.gridx = 0;
      location.gridy = i;
      location.insets = new Insets(ten, ten, ten, ten);
      panel.add(new JLabel(my_auction_label[i]), location);       
    }
    
   final String[] array = new String[]{my_auction.getName(),
                                        my_auction.getContactName(), 
                                        my_auction.getContactPhoneNumber(),
                                        my_auction.getACIntaker(), "",
                                        my_auction.getStartTime().getTime().toString(),
                                        my_auction.getEndTime().getTime().toString(), 
                                        my_auction.getExpectedNumberOfItems(),
                                        my_auction.getComments()};
    
    for (int j = 0; j < 9; j++)
    {
      location.gridx = 1;
      location.gridy = j + 1;
      location.insets = new Insets(ten, ten, ten, ten);
      panel.add(new JLabel(array[j]), location);
      
    }
    
    return panel;
  }
    
  
  /**
   * Set item list panel.
   */
  private void setItemListPanel()
  {
    final JPanel panel = new JPanel(new BorderLayout());
    setTitle("Item List Page");
 
    panel.add(ItemListHead(), BorderLayout.NORTH);
    panel.add(ItemListBody(), BorderLayout.CENTER);
    my_panel = panel;
  }
  
  /**
   * A part of item list panel.
   * @return a part of Item List panel.
   */
  private JPanel ItemListBody()
  {
    final JPanel panel = new JPanel(new GridBagLayout());
    final GridBagConstraints location = new GridBagConstraints();
    
    for (int i = 0; i < my_item_list.size(); i++)
    {
      location.gridx = 0;
      location.gridy = i + 1;
      location.insets= new Insets(ten, ten, ten, ten);
      int x = my_item_list.get(i).getId();
      panel.add(createButton("Detail of Item " + x  + " : " + my_item_list.get(i).getName(),
                               ButtonType.Item_detail,  x), location);
        
    }
    
    return panel;
  }
  
  /**
   * A part of item list panel.
   * @return a part of Item List panel.
   */
  private JPanel ItemListHead()
  {
    final JPanel panel = new JPanel(new GridLayout(1, 3, 10, 10));
    
    JButton button1 = createButton("Return to NPOStaffMember Home", 
                                  ButtonType.NPO_Member_home, -1);
    button1.setFont(createFont(button1, 1.5));
    panel.add(button1);
    
    JLabel label = new JLabel("         Item List ");
    label.setFont(createFont(label, 2));
    panel.add(label);
      
    JButton button2 = createButton("Add Item", ButtonType.Add_new_item, -1);
    button2.setFont(createFont(button2, 1.5));
    panel.add(button2);
    
    return panel;
  }
 
  /**
   * Set item information panel where user can input information.
   */
  private void setItemInformPanel()
  {
    final JPanel panel1 = new JPanel(new GridBagLayout());
    setTitle("Item Information Input Page ");
    final GridBagConstraints location = new GridBagConstraints();
    
    location.gridx = 0;
    location.gridy = 0;
    panel1.add(itemFormMain(), location);
    
    location.gridx = 1;
    location.gridy = 2;
    panel1.add(createButton("Submit", ButtonType.Submit_item, my_item_list.size()), location);
    location.insets = new Insets(ten, ten, ten, ten);
    
    location.gridx = 2;
    location.gridy = 2;
    panel1.add(createButton("Cancel", ButtonType.Cancel_item, -1), location);
    
    my_panel = panel1;   
  }
  
  
  /**
   * A part of input item information panel. 
   * @return A part of input item information panel. 
   */
  private JPanel itemFormMain() 
  {
    final JPanel panel = new JPanel(new GridBagLayout());
    final GridBagConstraints location = new GridBagConstraints();
  
    for (int i = 0; i < 11; i++)
    {
      location.gridx = 0;
      location.gridy = i;
      location.insets = new Insets(ten, ten, ten, ten);
      panel.add(new JLabel(my_item_label[i]), location);         
    }
    
    location.gridx = 1;
    location.gridy = 0;
    
    panel.add(new JLabel(my_auction.getName()), location);
    
    location.gridy = 1; // item ID
    panel.add(new JLabel("# " + my_item_id), location);
    
    location.gridy = 2; // item name
    my_item_name = new JTextField(twenty);
    panel.add(my_item_name, location);
   
    location.gridy = 3; // quantity
    my_item_quantity = new JTextField(5);
    panel.add(my_item_quantity, location);
    
    location.gridy = 4; //min bid
    my_item_min_bid = new JTextField(5);
    panel.add(my_item_min_bid, location);
    
    location.gridy = 5; //Donor
    my_item_donor = new JTextField(twenty);
    panel.add(my_item_donor, location);
   
    location.gridy = 6; //size
    final String[] size = {"NONE", "EXTRA_SMALL", "SMALL", "MEDIUM", "LARGE", "EXTRA_LARGE"};
    my_item_size = new JComboBox<String>(size);
    panel.add(my_item_size, location);
   
    location.gridy = 7; // storage
    my_item_storage = new JTextField(ten);
    panel.add(my_item_storage, location);
   
    location.gridy = 8;
    final String[] condition = {"NONE", "NEW", "LIKE_NEW", "VERY_GOOD", "GOOD", "ACCEPTABLE"};
    my_item_condition = new JComboBox<String>(condition);
    panel.add(my_item_condition, location);
    
    location.gridy = 9;
    my_item_comments = new JTextArea(5, twenty);
    panel.add(my_item_comments, location);
   
    
    location.gridy = 10;
    panel.add(imageBox(), location);
    
    return panel; 
  }
  
  /**
   * Image box include load image button and box.
   * @return Image box 
   */
  private JPanel imageBox()
  {
    final JPanel panel = new JPanel(new GridBagLayout());
    final GridBagConstraints location = new GridBagConstraints();
    
    location.gridx = 0;
    location.gridy = 0;
    my_item_image_path = new JTextField(15);
    location.insets = new Insets(5, 5, 5, 5);
    panel.add(my_item_image_path, location);
    
    location.gridx = 1;
    panel.add(createButton("Load photo", ButtonType.LOAD_PHOTE, -1), location);
    
    return panel;
  }
  
  /**
   * 
   */
  private void setImagePanel()
  {
    final FileDialog image = new FileDialog(this, "Load Photo");
    image.setVisible(true);
    my_item_image_path.setText(image.getDirectory() + image.getFile());
   
  }
  
  

  /**
   * Create button with listener.
   * @param the_title
   * @param the_type
   * @param the_item_id
   * @return JButton
   */
  public JButton createButton(String the_title, 
                              final ButtonType the_type, final int the_item_id)
  {
    JButton button = new JButton(the_title);
    
    button.setOpaque(true);
    button.addActionListener(new ActionListener()
    {
      public void actionPerformed(final ActionEvent the_event)
      {
        
        my_type = the_type;
        remove(my_panel);
        switch (my_type)
        {
          
          case Add_new_auction:
            setAuctionInformPanel();
            break;
          
          case Submit_auction:// add an auction
            if(setAuction())
            {
              //setAuctionDetailPanel();
              setAuctionPanel();
            }
            break;
          
          case NPOHome:
            setAuctionPanel();
            break;
          
          case Add_new_item:  // create a new item form
            setItemInformPanel();
            break;
          
         case Submit_item:  // add a new item
            int item_id = setItem();
            if(item_id >= 0)
            {
              setItemViewPanel(item_id);
            }
            break;
         
          case Cancel_item:
            setItemListPanel();
            break;
          
          case Auction_detail:
            setItemListPanel();
            break;  
            
          case NPO_Member_home:
            setAuctionPanel();
            break;
            
          case Return_item_panel:
            setItemListPanel();
            break;
            
          case Item_detail: 
            if(the_item_id >= 0)
            {
              setItemViewPanel(the_item_id);
            }
            
            break;
            
          case LOAD_PHOTE:
            setImagePanel();
            break;
            default:
        }
        refresh();
      }
    });
    return button;
  }
  
  /**
   * Set item
   * @return item id if set successfully, otherwise return -1,
   *
   */
  private int setItem() 
  {
    int item_id = my_item_id;
    String item_name = my_item_name.getText().trim();
    if(item_name.length() == 0)
    {
      JOptionPane.showMessageDialog(null, "Please enter item name", 
                                    "Auction Central", JOptionPane.ERROR_MESSAGE);
      return -1;
    }
    
    int item_quantity; 
    
    try
    {
      item_quantity = Integer.parseInt(my_item_quantity.getText().trim());
    }
    catch(Exception e)
    {
      JOptionPane.showMessageDialog(null, "Please enter item quantity (in digit)", 
                                    "Auction Central", JOptionPane.ERROR_MESSAGE);
      return -1;

    }
    
    double item_min_bid;
    try
    {
      item_min_bid = Double.parseDouble(my_item_min_bid.getText().trim());
    }
    catch(Exception e)
    {
      JOptionPane.showMessageDialog(null, "Please enter item minimum starting bid (in digit)", 
                                    "Auction Central", JOptionPane.ERROR_MESSAGE);
      return -1;

    }
   
    String item_donor = my_item_donor.getText().trim();
    
    final String item_storge = my_item_storage.getText().trim();
    
    final String item_comments = my_item_comments.getText().trim();
  
    Image item_image = null;
    
    try
    {
      if(my_item_image_path.getText().trim().length() != 0)
      {
        File FileToRead = new File(my_item_image_path.getText());
        item_image = ImageIO.read(FileToRead);
      }
      
    }
    catch (Exception e) 
    {
      
    }
 
    my_item = new Item(item_id, item_name, item_quantity, 
                         item_min_bid, item_donor, chooseSize(), 
                         item_storge, chooseCondition(), item_comments, item_image);
    
    
    my_item_list.add(my_item);
    my_item_id++;
    
    return item_id;
  }
  
  
  /**
   * choose size
   * @return be chose size
   */
  private Size chooseSize()
  {
    Size item_size = null;
    switch (my_item_size.getSelectedIndex())
    {
      case 0:
        item_size = Size.NONE;
        break;
        
      case 1:
        item_size = Size.EXTRA_SMALL;
        break;
        
      case 2:
        item_size = Size.SMALL;
        break;
      
      case 3:
        item_size = Size.MEDIUM;
        break;
      
      case 4:
        item_size = Size.EXTRA_LARGE;
        break;
      
      case 5:
        item_size = Size.EXTRA_LARGE;
        break;
      default:
      
    }
    return item_size;
  }
  
  /**
   * choose condition
   * @return be chose condition.
   */
  private Condition chooseCondition()
  {
    Condition item_condition = null;
    switch (my_item_condition.getSelectedIndex())
    {
      case 0:
        item_condition = Condition.NONE;
        break;
        
      case 1:
        item_condition = Condition.NEW;
        break;
        
      case 2:
        item_condition = Condition.LIKE_NEW;
        break;
      
      case 3:
        item_condition = Condition.VERY_GOOD;
        break;
      
      case 4:
        item_condition = Condition.GOOD;
        break;
      
      case 5:
        item_condition = Condition.ACCEPTABLE;
        break;
      default:
      
    }
    return item_condition;
  }
  
  /**
   * set a item detail panel.
   * @param the_ID
   */
  private void setItemViewPanel(int the_ID)
  {
    final JPanel panel = new JPanel(new GridBagLayout());
    setTitle("Item Information Page");
    final GridBagConstraints location = new GridBagConstraints();
     
    location.gridx = 0;
    location.gridy = 0; 
    location.insets = new Insets(ten, ten, ten, ten);
    JButton button = createButton("Return to Item List Panel", ButtonType.Return_item_panel, -1);
    button.setFont(createFont(button, 1));
    panel.add(button, location);
   
    
    for (int i = 0; i < 11; i++)
    {
      location.gridx = 1;
      location.gridy = i;
      location.insets = new Insets(ten, ten, ten, ten);
      JLabel label = new JLabel(my_item_label[i]);
      label.setFont(createFont(label, 1.5));
      
      panel.add(label, location);       
    }
    
    int index = -1;
    
    for(int i = 0; i< my_item_list.size(); i++)
    {
      if(my_item_list.get(i).getId() == the_ID)
      {
        index = i;  
      }
      
    }
    
    String[] stringArray = new String[] {my_auction.getName(),
        "#" + my_item_list.get(index).getId(), my_item_list.get(index).getName(), 
        Integer.toString(my_item_list.get(index).getQuantity()),
        NumberFormat.getCurrencyInstance().format(my_item_list.get(index).getMinBid()),
        my_item_list.get(index).getDonor(), EnumFormat.format(my_item_list.get(index).getSize()),
        my_item_list.get(index).getStorage(), EnumFormat.format(my_item_list.get(index).getCondition()),
        my_item_list.get(index).getComment()};
    
    for(int j = 0; j < 10; j++)
    {
      location.gridx = 2;
      location.gridy = j;
      location.insets = new Insets(ten, ten, ten, ten);
      JLabel label = new JLabel(stringArray[j]);
      label.setFont(createFont(label, 1.5));
      panel.add(label, location);
      
    }
    
    my_panel = panel;
  }
  
  /**
   *  Set font
   * @param the_component
   * @param the_ratio
   * @return font
   */
  private Font createFont(final JComponent the_component, final double the_ratio)
  {
    return the_component.getFont().deriveFont((float) ((double) the_component.getFont().getSize() *
                                                  the_ratio));
  }
  
 
  
  /**
   * refresh.
   */
  private void refresh()
  {
    add(my_panel);
    my_panel.setPreferredSize(new Dimension(GeneralUI.WIDTH, GeneralUI.HEIGHT));
    pack();
  } 
  
  
  enum ButtonType
  {
    Add_new_auction,
    
    Submit_auction,
    
    NPOHome,
    
    Add_new_item,
    
    Return_item_panel,
    
    Submit_item,
    
    Cancel_item,
    
    Auction_detail,
    
    NPO_Member_home,
    
    Item_detail,
    
    LOAD_PHOTE;
    
  }
}