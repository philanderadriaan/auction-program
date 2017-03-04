package auctioncentral;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.font.TextAttribute;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * UI for Auction Central employees.
 * 
 * @author Tyson Nottingham
 * @author Phil Adriaan --
 * View of auction information, item information, and item list inspired by Phil's BidderUI.
 * Some methods found in Bidder UI are replicated here with little modification to
 * facilitate creation of a uniform look and feel.
 * 
 * @version 28 May 2012
 */
@SuppressWarnings("serial")
public class AuctionCentralEmployeeUI extends JFrame
{
  private static final int WIDTH = 800;
  private static final int HEIGHT = 600;
  private static final int BORDER_WIDTH = 10;
  
  private static final int CALENDAR_WIDTH = 650;
  private static final int CALENDAR_HEIGHT = 480;
  
  private static final float CALENDAR_HEADING_FONT_SIZE = 14f;
  private static final int CALENDAR_ROWS = 6;
  private static final int DAYS_IN_WEEK = 7;
  
  private static final int NAVIGATION_PANEL_BUTTON_COUNT = 3;
  private static final int COLUMNS_PER_ROW = 4;
  private static final int OBJECTS_PER_PAGE = 25;
  
  private static final Insets EMPTY_INSETS = new Insets(0, 0, 0, 0);
  
  private JPanel my_current_view;
  private JPanel my_calendar_view;
  private JPanel my_auction_view;
  private JPanel my_item_list_view;
  private JPanel my_item_view;
  
  private final Map<Calendar, List<Auction>> my_auctions;
  
  public AuctionCentralEmployeeUI(final List<Auction> the_auctions)
  {
    my_auctions = initializeAuctions(the_auctions);
    my_calendar_view = new CalendarView();
    my_current_view = my_calendar_view;
    getContentPane().add(my_current_view);
    
    setTitle("Auction Central");
    setSize(WIDTH, HEIGHT);
    setLocationRelativeTo(null);
    setResizable(false);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
  }
  
  /*
   * pre: the_auctions != null
   * post: \forall Auction auction; the_auctions.contains(auction);
   *           \result.get(new GregorianCalendar(
   *             auction.getStartTime().get(Calendar.YEAR),
   *             auction.getStartTime().get(Calendar.MONTH),
   *             auction.getStartTime().get(Calendar.DAY_OF_MONTH))).
   *           contains(auction)
   */
  /**
   * @return a Map from the Calendar date (year, month, day of month) to a
   * list of Auctions starting on that date or null if no auctions start on
   * that date.
   */
  public Map<Calendar, List<Auction>> initializeAuctions(final List<Auction> the_auctions)
  {
    final Map<Calendar, List<Auction>> auctions = new HashMap<Calendar, List<Auction>>();
    
    for (final Auction auction : the_auctions)
    {
      final Calendar start_time = auction.getStartTime();
      final int year = start_time.get(Calendar.YEAR);
      final int month = start_time.get(Calendar.MONTH);
      final int day_of_month = start_time.get(Calendar.DAY_OF_MONTH);
      final Calendar start_date = new GregorianCalendar(year, month, day_of_month);
      
      if (auctions.get(start_date) == null)
      {
        auctions.put(start_date, new ArrayList<Auction>());
      }
      
      auctions.get(start_date).add(auction);
    }
    
    return auctions;
  }
  
  /**
   * Creates font.
   * 
   * @author Phil Adriaan
   */
  public Font createFont(final JComponent the_component, final float the_ratio)
  {
    return the_component.getFont().
        deriveFont((float) the_component.getFont().getSize() * the_ratio);
  }
  
  /**
   * Creates a centered label of custom size.
   * 
   * @author Phil Adriaan
   */
  public JLabel createLabel(final String the_string, final float the_size)
  {
    final JLabel label = new JLabel(the_string);
    label.setOpaque(true);
    label.setHorizontalAlignment(SwingConstants.CENTER);
    label.setFont(createFont(label, the_size));
    return label;
  }
  
  /**
   * Creates a panel that displays heading-value pairs.
   * 
   * Adapted from Phil Adriaan's similar method.
   */
  public JPanel createInfoPanel(final String[] the_headings,
                                final String[] the_values)
  {
    final JPanel info_panel =
        new JPanel(new GridLayout(the_headings.length, 2));
    
    for (int i = 0; i < the_headings.length; ++i)
    {
      info_panel.add(createLabel(the_headings[i], 2));
      info_panel.add(createLabel(the_values[i], 2));
    }
    
    return info_panel;
  }

  /**
   * Creates a panel with the specified components in a row. It is
   * intended for creating a button panel, but the argument is a 
   * JComponent array so that users of this method can pass in an
   * array containing empty JLabels where they want there to be
   * empty space in the panel.
   */
  public JPanel createNavigationPanel(final JComponent[] the_buttons)
  {
    final JPanel nav_panel = new JPanel(new GridLayout(1, the_buttons.length));
    
    for (final JComponent button : the_buttons)
    {
      button.setFont(createFont(button, 2f));
      nav_panel.add(button);
    }
    
    return nav_panel;
  }

  /**
   * Inner class for Calendar view JPanel, mostly used to organized methods
   * of containing class.
   * 
   * @author Tyson Nottingham
   */
  private class CalendarView extends JPanel implements ActionListener
  {
    private String[] month_strings =
    {
      "January", "February", "March", "April", "May", "June", "July",
      "August", "September", "October", "November", "December"
    };
    
    private int my_current_year;
    private int my_current_month;
    
    private JLabel my_today_label;
    private JLabel my_month_and_year_label;
    private JPanel my_calendar_panel;
    private JPanel[][] my_auction_cells;
    
    public CalendarView()
    {
      final Calendar today = new GregorianCalendar();
      my_current_year = today.get(Calendar.YEAR);
      my_current_month = today.get(Calendar.MONTH);
      
      my_today_label =
          new JLabel("Today is " + DateFormat.getDateInstance().
                     format(new GregorianCalendar().getTime()) + ".");
      my_today_label.setFont(my_today_label.getFont().
                             deriveFont(CALENDAR_HEADING_FONT_SIZE));
      
      my_month_and_year_label =
          new JLabel(month_strings[my_current_month] + ", " + my_current_year);
      my_month_and_year_label.setFont(my_today_label.getFont().
                                      deriveFont(CALENDAR_HEADING_FONT_SIZE));
  
      my_calendar_panel = createCalendarPanel();
      refresh();
      
      setBorder(BorderFactory.createEmptyBorder(BORDER_WIDTH, BORDER_WIDTH,
                                                BORDER_WIDTH, BORDER_WIDTH));
      setLayout(new BorderLayout());
      add(createUpperPanel(), BorderLayout.PAGE_START);
      add(createLowerPanel(), BorderLayout.CENTER);
    }
    
    public JPanel createUpperPanel()
    {
      final JPanel upper = new JPanel();
      upper.setLayout(new BoxLayout(upper, BoxLayout.PAGE_AXIS));
      
      final JPanel today_panel = new JPanel(new FlowLayout(FlowLayout.LEADING));
      today_panel.add(my_today_label);
      upper.add(today_panel);
      
      final JPanel month_and_year_panel = new JPanel();
      month_and_year_panel.add(my_month_and_year_label);
      upper.add(month_and_year_panel);
      
      return upper;
    }
    
    public JPanel createLowerPanel()
    {
      final JPanel lower = new JPanel();
      
      final JButton prev_month_button = new JButton("<<");
      prev_month_button.addActionListener(this);
      lower.add(prev_month_button);
      
      lower.add(my_calendar_panel);
      
      final JButton next_month_button = new JButton(">>");
      next_month_button.addActionListener(this);
      lower.add(next_month_button);
      
      return lower;
    }
  
    public JPanel createCalendarPanel()
    {
      final JPanel panel = new JPanel(new GridLayout(CALENDAR_ROWS, DAYS_IN_WEEK));
      panel.setBorder(BorderFactory.createTitledBorder(""));
      panel.setPreferredSize(new Dimension(CALENDAR_WIDTH, CALENDAR_HEIGHT));
      
      my_auction_cells = new JPanel[CALENDAR_ROWS][DAYS_IN_WEEK];
      
      for (int i = 0; i < CALENDAR_ROWS; ++i)
      {
        for (int j = 0; j < DAYS_IN_WEEK; ++j)
        {
          my_auction_cells[i][j] = new JPanel();
          my_auction_cells[i][j].setLayout(new BoxLayout(my_auction_cells[i][j],
                                                         BoxLayout.PAGE_AXIS));
          my_auction_cells[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
          my_auction_cells[i][j].setBackground(Color.WHITE);
          panel.add(my_auction_cells[i][j]);
        }
      }
      
      return panel;
    }
    
    public void refresh()
    {
      // If day has changed since last refresh, update today's date.
      my_today_label.setText("Today is " + DateFormat.getDateInstance().
                             format(new GregorianCalendar().getTime()) + ".");
      
      my_month_and_year_label.setText(month_strings[my_current_month] +
                                      ", " + my_current_year);
      refreshCalendar();
      validate();
      repaint();
    }
    
    public void refreshCalendar()
    {
      clearCalendar();
      
      final Calendar first_of_month =
          new GregorianCalendar(my_current_year, my_current_month, 1);
      final int day_of_week_of_first = first_of_month.get(Calendar.DAY_OF_WEEK);
      final int days_in_month = first_of_month.getActualMaximum(Calendar.DAY_OF_MONTH);
  
      // Fill calendar.
      for (int i = 1; i <= days_in_month; ++i)
      {
        final int row = (day_of_week_of_first + i - 2) / DAYS_IN_WEEK;
        final int col = (day_of_week_of_first + i - 2) % DAYS_IN_WEEK;
        
        refreshCell(row, col, i);
      }
    }
  
    public void refreshCell(final int the_row, final int the_col,
                             final int the_day_of_month)
    {
      my_auction_cells[the_row][the_col].
        add(new JLabel(Integer.toString(the_day_of_month)));
      
      final Calendar date =
          new GregorianCalendar(my_current_year, my_current_month,
                               the_day_of_month);
      final List<Auction> auctions = my_auctions.get(date);
      
      Auction first_auction = null;
      Auction second_auction = null;
      
      if (auctions != null)
      {
        first_auction = auctions.get(0);
        final HyperLink first_auction_link = new HyperLink(first_auction.getName());
        first_auction_link.setToolTipText(first_auction.getName());
        first_auction_link.addActionListener(new NavigationListener(first_auction));
        my_auction_cells[the_row][the_col].add(first_auction_link);
        
        if (auctions.size() > 1)
        {
          second_auction = auctions.get(1);
          final HyperLink second_auction_link = new HyperLink(second_auction.getName());
          second_auction_link.setToolTipText(second_auction.getName());
          second_auction_link.addActionListener(new NavigationListener(second_auction));
          my_auction_cells[the_row][the_col].add(second_auction_link);
        }
      }
      
      my_auction_cells[the_row][the_col].setBackground(Color.WHITE);
    }
  
    public void clearCalendar()
    {
      for (int i = 0; i < CALENDAR_ROWS; ++i)
      {
        for (int j = 0; j < DAYS_IN_WEEK; ++j)
        {
          my_auction_cells[i][j].removeAll();
          my_auction_cells[i][j].setBackground(my_calendar_panel.getBackground());
        }
      }
    }
  
    @Override
    public void actionPerformed(final ActionEvent the_event)
    {
      if ("<<".equals(the_event.getActionCommand()))
      {
        if (my_current_month == Calendar.JANUARY)
        {
          my_current_month = Calendar.DECEMBER;
          --my_current_year;
        }
        else
        {
          --my_current_month;
        }
      }
      else if (">>".equals(the_event.getActionCommand()))
      {
        if (my_current_month == Calendar.DECEMBER)
        {
          my_current_month = Calendar.JANUARY;
          ++my_current_year;
        }
        else
        {
          ++my_current_month;
        }
      }
      
      refresh();
    }
  }

  private class AuctionView extends JPanel
  {
    private final Auction my_auction;
    
    public AuctionView(final Auction the_auction)
    {
      my_auction = the_auction;
      setLayout(new BorderLayout());
      add(createLabel("Auction Information", 2), BorderLayout.PAGE_START);
      add(createAuctionInfoPanel(), BorderLayout.CENTER);
      add(createAuctionViewNavigationPanel(), BorderLayout.PAGE_END);
    }

    public JPanel createAuctionInfoPanel()
    {
      final DateFormat date_formatter =
          DateFormat.getDateInstance(DateFormat.SHORT);
      final DateFormat time_formatter =
          DateFormat.getTimeInstance(DateFormat.SHORT);
      
      final String[] headings =
      {
        "Organization :", "Contact Name :", "Contact Phone Number :",
        "Date of Auction :", "Starting Time :", "Ending Time :",
        "Number of Items :"
      };
      
      final String[] values =
      {
        my_auction.getNPO(), my_auction.getContactName(),
        my_auction.getContactPhoneNumber(),
        date_formatter.format(my_auction.getStartTime().getTime()),
        time_formatter.format(my_auction.getStartTime().getTime()),
        time_formatter.format(my_auction.getEndTime().getTime()),
        Integer.toString(my_auction.getItems().size())
      };

      return createInfoPanel(headings, values);
    }

    public JPanel createAuctionViewNavigationPanel()
    {
      final JComponent[] buttons = new JComponent[NAVIGATION_PANEL_BUTTON_COUNT];
      
      final JButton calendar_button = new JButton("Back to Calendar");
      calendar_button.addActionListener(new NavigationListener(my_calendar_view));
      
      final JButton items_button = new JButton("Browse Items");
      items_button.addActionListener(new NavigationListener(my_auction.getItems()));
      
      buttons[0] = calendar_button;
      buttons[1] = new JLabel();
      buttons[2] = items_button;
      
      return createNavigationPanel(buttons);
    }
  }
  
  private class ItemListView extends JPanel
  {
    private final List<Item> my_items;
    
    public ItemListView(final List<Item> the_items)
    {
      my_items = the_items;
      setLayout(new BorderLayout());
      add(createItemListPanel(), BorderLayout.CENTER);
      add(createItemNavigationPanel(), BorderLayout.PAGE_END);
    }
    
    /**
     * Panel to list all items.
     * 
     * @author Phil Adriaan
     * @author Tyson Nottingham (minor modifications)
     */
    public JPanel createItemListPanel()
    {
      final JPanel panel = new JPanel(new GridLayout(OBJECTS_PER_PAGE + 1, 1));
      panel.add(createItemRow(null));
      for (int i = 0; i < my_items.size(); i++)
      {
        panel.add(createItemRow(my_items.get(i)));
      }
      return panel;
    }
    
    /**
     * Creates a row of objects.
     * 
     * @author Phil Adriaan
     */
    public JPanel createRow()
    {
      final JPanel panel = new JPanel();
      panel.setOpaque(true);
      panel.setLayout(new GridLayout(1, COLUMNS_PER_ROW));
      return panel;
    }
    
    /**
     * Creates row for an item.
     * 
     * @author Phil Adriaan
     * @author Tyson Nottingham (minor modifications)
     */
    public JPanel createItemRow(final Item the_item)
    {
      final JPanel panel = createRow();
      if (the_item != null)
      {
        panel.add(createLabel(the_item.getName(), 1));
        
        final JButton view_item_button = new JButton("View Item");
        view_item_button.addActionListener(new NavigationListener(the_item));
        
        panel.add(view_item_button);
        panel.add(createLabel("" + the_item.getQuantity(), 1));
        panel.add(createLabel(NumberFormat.getCurrencyInstance().
                              format(the_item.getMinBid()), 1));
      }
      else
      {
        panel.add(createLabel("Item", 2));
        panel.add(new JLabel());
        panel.add(createLabel("Quantity", 2));
        panel.add(createLabel("Minimum Bid", 2));
      }
      return panel;
    }
    
    public JPanel createItemNavigationPanel()
    {
      final JComponent[] buttons = new JComponent[NAVIGATION_PANEL_BUTTON_COUNT];
      
      final JButton calendar_button = new JButton("Back to Calendar");
      calendar_button.addActionListener(new NavigationListener(my_calendar_view));
      
      final JButton items_button = new JButton("Back to Auction");
      items_button.addActionListener(new NavigationListener(my_auction_view));
      
      buttons[0] = calendar_button;
      buttons[1] = items_button;
      buttons[2] = new JLabel();
      
      return createNavigationPanel(buttons);
    }
  }
  
  private class ItemView extends JPanel
  {
    private final Item my_item;
    
    public ItemView(final Item the_item)
    {
      my_item = the_item;
      setLayout(new BorderLayout());
      add(createLabel("Item Information", 2), BorderLayout.PAGE_START);
      add(createItemInfoPanel(), BorderLayout.CENTER);
      add(createItemViewNavigationPanel(), BorderLayout.PAGE_END);
    }

    public JPanel createItemInfoPanel()
    {
      final String[] headings =
      {
        "ID Number :", "Item Name :", "Quantity of Item :",
        "Minimum Bid :", "Donor :", "Approximate Size :",
        "Storage Location :", "Item Condition :", "Comments :"
      };
      
      final String[] values =
      {
        "#" + my_item.getId(), my_item.getName(),
        Integer.toString(my_item.getQuantity()),
        NumberFormat.getCurrencyInstance().format(my_item.getMinBid()),
        my_item.getDonor(), EnumFormat.format(my_item.getSize()),
        my_item.getStorage(), EnumFormat.format(my_item.getCondition()),
        my_item.getComment()
      };

      return createInfoPanel(headings, values);
    }

    public JPanel createItemViewNavigationPanel()
    {
      final JComponent[] buttons = new JComponent[NAVIGATION_PANEL_BUTTON_COUNT];
      
      final JButton calendar_button = new JButton("Back to Calendar");
      calendar_button.addActionListener(new NavigationListener(my_calendar_view));
      
      final JButton items_button = new JButton("Back to Items");
      items_button.addActionListener(new NavigationListener(my_item_list_view));
      
      buttons[0] = calendar_button;
      buttons[1] = items_button;
      buttons[2] = new JLabel();
      
      return createNavigationPanel(buttons);
    }
  }

  private enum ViewType
  {
    CALENDAR,
    AUCTION,
    ITEM_LIST,
    ITEM
  }

  private class NavigationListener implements ActionListener
  {
    JPanel my_panel;
    Auction my_auction;
    List<Item> my_items;
    Item my_item;
    ViewType my_view_type;
    
    public NavigationListener(final JPanel the_panel)
    {
      my_panel = the_panel;
    }
    
    public NavigationListener(final Auction the_auction)
    {
      my_view_type = ViewType.AUCTION;
      my_auction = the_auction;
    }
  
    public NavigationListener(final List<Item> the_items)
    {
      my_view_type = ViewType.ITEM_LIST;
      my_items = the_items;
    }
    
    public NavigationListener(final Item the_item)
    {
      my_view_type = ViewType.ITEM;
      my_item = the_item;
    }
    
    @Override
    public void actionPerformed(final ActionEvent the_event)
    {
      final JFrame ui = AuctionCentralEmployeeUI.this;
      ui.remove(my_current_view);
      
      if (my_panel == null)
      {
        switch (my_view_type)
        {
          case AUCTION:
            my_auction_view = new AuctionView(my_auction);
            my_panel = my_auction_view;
            break;
          case ITEM_LIST:
            my_item_list_view = new ItemListView(my_items);
            my_panel = my_item_list_view;
            break;
          case ITEM:
            my_item_view = new ItemView(my_item);
            my_panel = my_item_view;
            break;
          default:
            my_panel = my_calendar_view;
            break;
        }
      }
      
      ui.add(my_panel);
      my_current_view = my_panel;
      ui.validate();
      ui.repaint();
    }
  }

  /*
   * This is potentially useful outside of this class so it should probably be
   * a separate public class.
   */
  private class HyperLink extends JButton implements MouseListener
  {
    public HyperLink(final String the_text)
    {
      super(the_text);
      setBorderPainted(false);
      setContentAreaFilled(false);
      setForeground(Color.BLUE);
      setMargin(EMPTY_INSETS);
      
      final Map<TextAttribute, Object> map = new Hashtable<TextAttribute, Object>();
      map.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
      setFont(getFont().deriveFont(map));
      
      addMouseListener(this);
    }
  
    @Override
    public void mouseEntered(final MouseEvent the_event)
    {
      setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }
  
    @Override
    public void mouseExited(final MouseEvent the_event)
    {
      setCursor(Cursor.getDefaultCursor());
    }
  
    @Override
    public void mouseClicked(final MouseEvent the_event)
    {
      // No implementation.
    }
    
    @Override
    public void mousePressed(final MouseEvent the_event)
    {
      // No implementation.
    }
  
    @Override
    public void mouseReleased(final MouseEvent the_event)
    {
      // No implementation.
    }
  }
}