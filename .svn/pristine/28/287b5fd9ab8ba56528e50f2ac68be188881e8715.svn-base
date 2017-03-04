
package auctioncentral;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * UI for bidders.
 * 
 * @author Phil Adriaan
 * @version 1
 */
@SuppressWarnings("serial")
public class BidderUI extends JFrame
{
  /**
   * Number of objects per page.
   */
  private static final int OBJECTS_PER_PAGE = 25;
  /**
   * Columns per row.
   */
  private static final int COLUMNS_PER_ROW = 4;
  /**
   * All future auctions.
   */
  private final List<Auction> my_auctions;
  /**
   * Selected auction.
   */
  private Auction my_selected_auction;
  /**
   * All items in selected auction.
   */
  private List<Item> my_items;
  /**
   * Selected item.
   */
  private Item my_selected_item;
  /**
   * The bidder using this UI.
   */
  private final Bidder my_bidder;
  /**
   * Panel currently viewed.
   */
  private JPanel my_panel;
  /**
   * For entering bids.
   */
  private final JTextField my_field = new JTextField(10);
  /**
   * Current panel.
   */
  private PanelType my_type = PanelType.MAIN;

  /**
   * Creates the frame.
   */
  public BidderUI(final Bidder the_bidder, final List<Auction> the_auctions)
  {
    my_auctions = the_auctions;
    my_bidder = the_bidder;
    my_field.setHorizontalAlignment(JTextField.TRAILING);
    setMainPanel();
    refresh();
    final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    setLocation((int) screen.getWidth() / 2 - GeneralUI.WIDTH / 2, (int) screen.getHeight() /
                                                                   2 - GeneralUI.HEIGHT / 2);
    this.setResizable(false);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
  }

  /**
   * Returns current panel type.
   */
  public PanelType getPanelType()
  {
    return my_type;
  }

  /**
   * Creates panel that shares the same characteristics.
   */
  private JPanel createPanel(final String the_title)
  {
    final JPanel panel = createPanel();
    panel.setLayout(new BorderLayout());
    if (the_title != null)
    {
      setTitle("Bidder - " + the_title);
    }
    return panel;
  }

  /**
   * creates a panel.
   */
  private JPanel createPanel()
  {
    final JPanel panel = new JPanel();
    panel.setOpaque(true);
    return panel;
  }

  /**
   * Sets the current panel to view bids.
   */
  private void setBidderPanel()
  {
    final JPanel panel = createPanel("Bids");
    panel.add(navigationPanel(null, null, "Back to Main", PanelType.MAIN), BorderLayout.SOUTH);
    if (my_bidder.getBids().isEmpty())
    {
      panel.add(createLabel("You have 0 bid.", 2));
    }
    else
    {
      panel.add(createBidderList());
    }
    my_panel = panel;
  }

  /**
   * Sets the current panel to bid on items.
   */
  private void setBidPanel()
  {
    final JPanel panel = createPanel("Bid");
    panel.add(createBidPanel());
    panel.add(navigationPanel("Cancel", PanelType.ITEM, "Place Bid", PanelType.STATE),
              BorderLayout.SOUTH);
    my_panel = panel;
  }

  /**
   * Creates panel to bid on items.
   */
  private JPanel createBidPanel()
  {
    final JPanel panel = createPanel();
    panel.setLayout(new GridLayout(2, 1));
    panel.add(createLabel("Enter the amount you want to bid. (Minimum " +
                              NumberFormat.getCurrencyInstance().format(my_selected_item
                                                                            .getMinBid()) +
                              ")", 2));
    panel.add(createFieldPanel());
    return panel;
  }

  /**
   * Panel to hold the field.
   */
  private JPanel createFieldPanel()
  {
    final JPanel panel = createPanel();
    panel.setLayout(new FlowLayout(FlowLayout.CENTER));
    panel.add(createLabel("$", 2));
    my_field.setText(formatDouble(my_selected_item.getMinBid()));
    panel.add(my_field);
    return panel;
  }

  /**
   * Main panel when the user just logged in.
   */
  private void setMainPanel()
  {

    final JPanel panel = createPanel("Main");
    panel.add(createLabel("Hello " + my_bidder.getName() +
                          ", you are now signed in as a bidder.", 2));
    panel.add(navigationPanel("View Bids", PanelType.BIDDER, "Browse Auctions",
                              PanelType.AUCTION_INDEX), BorderLayout.SOUTH);
    my_panel = panel;
  }

  /**
   * Panel to show all available auctions.
   */
  private void setAuctionIndexPanel()
  {
    final JPanel panel = createPanel("Auctions Index");
    panel.add(navigationPanel("Back to Main", PanelType.MAIN, null, null), BorderLayout.SOUTH);
    panel.add(createAuctionList());
    my_panel = panel;
  }

  /**
   * Panel to show all auctions.
   */
  private JPanel createAuctionList()
  {
    final JPanel panel = createPanel();
    panel.setLayout(new GridLayout(OBJECTS_PER_PAGE + 1, 1));
    panel.add(createAuctionRow(null));
    for (Auction i : my_auctions)
    {
      if (i.getEndTime().after(Calendar.getInstance()))
      {
        panel.add(createAuctionRow(i));
      }
    }
    return panel;
  }

  /**
   * Panel to list all items.
   */
  private JPanel createItemList()
  {
    final JPanel panel = createPanel();
    panel.setLayout(new GridLayout(OBJECTS_PER_PAGE + 1, 1));
    panel.add(createItemRow(null));
    for (int i = 0; i < my_items.size(); i++)
    {
      panel.add(createItemRow(my_items.get(i)));
    }
    return panel;
  }

  /**
   * Panel to list all items that the bidder bid on.
   */
  private JPanel createBidderList()
  {
    final JPanel panel = createPanel();
    panel.setLayout(new GridLayout(OBJECTS_PER_PAGE + 1, 1));
    panel.add(createBidderRow(null));
    for (int i = 0; i < my_bidder.getBids().size(); i++)
    {
      panel.add(createBidderRow(my_bidder.getBids().get(i)));
    }
    return panel;
  }

  /**
   * Creates a row for an auction.
   */
  private JPanel createAuctionRow(final Auction the_auction)
  {
    final JPanel panel = createRow();
    if (the_auction != null)
    {
      panel.add(createLabel(the_auction.getNPO(), 1));
      panel.add(createButton("View Auction", the_auction, 1));
      panel.add(createLabel(the_auction.getStartTime().get(Calendar.MONTH) +
                                1 +
                                the_auction.getName().substring(the_auction.getName()
                                                                    .indexOf('-') + 2), 1));
      panel.add(createLabel(the_auction.getStartTime().get(Calendar.HOUR_OF_DAY) + ":" +
                            formatInt(the_auction.getStartTime().get(Calendar.MINUTE)) +
                            " - " + the_auction.getEndTime().get(Calendar.HOUR_OF_DAY) + ":" +
                            formatInt(the_auction.getEndTime().get(Calendar.MINUTE)), 1));
    }
    else
    {
      panel.add(createLabel("Host", 2));
      panel.add(new JLabel());
      panel.add(createLabel("Date (M-D-YYYY)", 2));
      panel.add(createLabel("Time (24 hr)", 2));
    }
    return panel;
  }

  /**
   * Creates row for an item.
   */
  private JPanel createItemRow(final Item the_item)
  {
    final JPanel panel = createRow();
    if (the_item != null)
    {
      panel.add(createLabel(the_item.getName(), 1));
      panel.add(createButton("View Item", the_item, 1));
      panel.add(createLabel("" + the_item.getQuantity(), 1));
      panel
          .add(createLabel(NumberFormat.getCurrencyInstance().format(the_item.getMinBid()), 1));
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

  /**
   * Creates row for items bid by the bidder.
   */
  private JPanel createBidderRow(final Bid the_bid)
  {
    final JPanel panel = createRow();
    if (the_bid != null)
    {
      panel.add(createLabel("#" + the_bid.getItem().getId(), 1));
      panel.add(createLabel(the_bid.getItem().getName(), 1));
      panel.add(createLabel(Integer.toString(the_bid.getItem().getQuantity()), 1));
      panel
          .add(createLabel(NumberFormat.getCurrencyInstance().format(the_bid.getAmount()), 1));
    }
    else
    {
      panel.add(createLabel("ID", 2));
      panel.add(createLabel("Item", 2));
      panel.add(createLabel("Quantity", 2));
      panel.add(createLabel("Bid", 2));
    }
    return panel;
  }

  /**
   * Creates a row of objects.
   */
  private JPanel createRow()
  {
    final JPanel panel = new JPanel();
    panel.setOpaque(true);
    panel.setLayout(new GridLayout(1, COLUMNS_PER_ROW));
    return panel;
  }

  /**
   * Forces an integer to display 2 digits. For displaying minutes.
   */
  private String formatInt(final int the_int)
  {
    return String.format("%02d", the_int);
  }

  /**
   * Forces a double to display 2 decimal places. For displaying cents.
   */
  private String formatDouble(final double the_double)
  {
    return String.format("%.2f", the_double);
  }

  /**
   * Panel to view auction info.
   */
  private void setAuctionPanel()
  {
    final JPanel panel = createPanel("Auction");
    panel.add(createLabel("Auction Information", 2), BorderLayout.NORTH);
    panel.add(navigationPanel("Back to Index", PanelType.AUCTION_INDEX, "Browse Items",
                              PanelType.ITEM_INDEX), BorderLayout.SOUTH);
    panel
        .add(createPanel(new String[] {"Organization :", "Contact Name :",
                             "Contact Phone Number :", "Date of Auction :", "Starting Time :",
                             "Ending Time :", "Number of Items :"},
                         new String[] {
                             my_selected_auction.getNPO(),
                             my_selected_auction.getContactName(),
                             my_selected_auction.getContactPhoneNumber(),
                             my_selected_auction.getStartTime().get(Calendar.MONTH) +
                                 1 +
                                 my_selected_auction
                                     .getName()
                                     .substring(my_selected_auction.getName().indexOf('-') + 2),
                             my_selected_auction.getStartTime().get(Calendar.HOUR_OF_DAY) +
                                 ":" +
                                 formatInt(my_selected_auction.getStartTime()
                                     .get(Calendar.MINUTE)),
                             my_selected_auction.getEndTime().get(Calendar.HOUR_OF_DAY) +
                                 ":" +
                                 formatInt(my_selected_auction.getEndTime()
                                     .get(Calendar.MINUTE)),
                             Integer.toString(my_selected_auction.getItems().size())}));
    my_panel = panel;
  }

  /**
   * Lists all available items.
   */
  private void setItemIndexPanel()
  {
    final JPanel panel = createPanel("Items Index");
    panel.add(navigationPanel("Back to Information", PanelType.AUCTION, null, null),
              BorderLayout.SOUTH);
    panel.add(createItemList());
    my_panel = panel;
  }

  /**
   * List the item only.
   */
  private void setItemPanel()
  {
    final JPanel panel = createPanel("Item");
    panel.add(createLabel("Item Information", 2), BorderLayout.NORTH);
    panel.add(navigationPanel("Back to Index", PanelType.ITEM_INDEX, "Bid on This Item",
                              PanelType.BID), BorderLayout.SOUTH);
    panel.add(createPanel(new String[] {"ID Number :", "Item Name :", "Quantity of Item :",
        "Minimum Bid :", "Donor :", "Approximate Size :", "Storage Location :",
        "Item Condition :", "Comments :"}, new String[] {"#" + my_selected_item.getId(),
        my_selected_item.getName(), Integer.toString(my_selected_item.getQuantity()),
        NumberFormat.getCurrencyInstance().format(my_selected_item.getMinBid()),
        my_selected_item.getDonor(), EnumFormat.format(my_selected_item.getSize()),
        my_selected_item.getStorage(), EnumFormat.format(my_selected_item.getCondition()),
        my_selected_item.getComment()}));
    my_panel = panel;
  }

  /**
   * Creates panels with 2 colum of strings.
   */
  private JPanel createPanel(final String[] the_left, final String[] the_right)
  {
    final JPanel panel = createPanel();
    panel.setLayout(new GridLayout(Math.max(the_left.length, the_right.length), 2));
    for (int i = 0; i < Math.max(the_left.length, the_right.length); i++)
    {
      panel.add(createLabel(the_left, i));
      panel.add(createLabel(the_right, i));
    }
    return panel;
  }

  /**
   * Creates a label from an array of string and the index.
   */
  private JLabel createLabel(final String[] the_strings, final int the_index)
  {
    if (the_index < the_strings.length)
    {
      return createLabel(the_strings[the_index], 2);
    }
    else
    {
      return new JLabel();
    }
  }

  /**
   * Holds button to navigate between panels.
   */
  private JPanel navigationPanel(final String the_left, final PanelType the_left_panel,
                                 final String the_right, final PanelType the_right_panel)
  {
    final JPanel panel = new JPanel();
    panel.setLayout(new GridLayout(1, 3));
    if (the_left == null)
    {
      panel.add(new JLabel());
    }
    else
    {
      panel.add(createButton(the_left, the_left_panel, 2));
    }
    if (Arrays.asList(new PanelType[] {PanelType.BIDDER, PanelType.MAIN,
                          PanelType.AUCTION_INDEX}).contains(my_type))
    {
      panel.add(new JLabel());
    }
    else
    {
      panel.add(createButton("Back to Main", PanelType.MAIN, 2));
    }
    if (the_right == null)
    {
      panel.add(new JLabel());
    }
    else
    {
      panel.add(createButton(the_right, the_right_panel, 2));
    }
    return panel;
  }

  /**
   * Creates font
   */
  private Font createFont(final JComponent the_component, final float the_ratio)
  {
    return the_component.getFont().deriveFont((float) the_component.getFont().getSize() *
                                                  the_ratio);
  }

  /**
   * Creates button for navigating.
   */
  private JButton createButton(final String the_name, final PanelType the_type,
                               final float the_size)
  {
    return createButton(the_name, the_type, my_selected_auction, my_selected_item, the_size);
  }

  /**
   * Creates button to view auction.
   */
  private JButton createButton(final String the_name, final Auction the_auction,
                               final float the_size)
  {
    return createButton(the_name, PanelType.AUCTION, the_auction, my_selected_item, the_size);
  }

  /**
   * Creates button to view item.
   */
  private JButton createButton(final String the_name, final Item the_item, final float the_size)
  {
    return createButton(the_name, PanelType.ITEM, my_selected_auction, the_item, the_size);
  }

  /**
   * Creates button for everything else.
   */
  public JButton createButton(final String the_name, final PanelType the_type,
                              final Auction the_auction, final Item the_item,
                              final float the_size)
  {
    JButton button = new JButton(the_name);

    button.setFocusable(false);
    button.setFont(createFont(button, the_size));
    button.setOpaque(true);
    button.addActionListener(new ActionListener()
    {
      public void actionPerformed(final ActionEvent the_event)
      {
        my_type = the_type;
        remove(my_panel);
        switch (the_type)
        {
          case MAIN:
            setMainPanel();
            break;
          case BIDDER:
            setBidderPanel();
            break;
          case AUCTION_INDEX:
            setAuctionIndexPanel();
            break;
          case AUCTION:
            my_selected_auction = the_auction;
            my_items = my_selected_auction.getItems();
            setAuctionPanel();
            break;
          case ITEM_INDEX:
            setItemIndexPanel();
            break;
          case ITEM:
            my_selected_item = the_item;
            setItemPanel();
            break;
          case BID:
            if (!my_bidder.hasBidOnItem(my_selected_item) &&
                Calendar.getInstance().after(my_selected_auction.getStartTime()) &&
                Calendar.getInstance().before(my_selected_auction.getEndTime()))
            {
              setBidPanel();
            }
            else
            {
              my_type = PanelType.STATE;
              setStatePanel();
            }
            break;
          case STATE:
            setStatePanel();
            break;
          default:
        }
        refresh();
      }
    });
    return button;
  }

  /**
   * Checks if the value in the field is a double.
   */
  private boolean isDouble()
  {
    try
    {
      Double.parseDouble(my_field.getText());
    }
    catch (NumberFormatException exception)
    {
      return false;
    }
    return true;
  }

  /**
   * Panel to show the state of the bid.
   */
  private void setStatePanel()
  {
    final JPanel panel = createPanel("Bidding Status");
    if (my_bidder.hasBidOnItem(my_selected_item))
    {
      panel.add(createLabel("You already bid on this item!", 2));
    }
    else if (Calendar.getInstance().before(my_selected_auction.getStartTime()))
    {
      panel.add(createLabel("The auction has not yet started!", 2));
    }
    else if (Calendar.getInstance().after(my_selected_auction.getEndTime()))
    {
      panel.add(createLabel("The auction has ended. You can't bid on this anymore.", 2));
    }
    else if (!isDouble())
    {
      panel.add(createLabel("Your input is invalid!", 2));
    }
    else if (Double.valueOf(my_field.getText()) < my_selected_item.getMinBid())
    {

      panel.add(createLabel("Your bid is too low!", 2));
    }
    else
    {
      my_bidder.placeBid(my_selected_item, Double.valueOf(my_field.getText()));
      panel.add(createLabel("You made a successful bid!", 2));
    }

    panel.add(navigationPanel("Back to Item", PanelType.ITEM, null, null), BorderLayout.SOUTH);

    my_panel = panel;
  }

  /**
   * Creates a centered label of custom size.
   */
  private JLabel createLabel(final String the_string, final float the_size)
  {
    JLabel label = new JLabel(the_string);
    label.setOpaque(true);
    label.setHorizontalAlignment(SwingConstants.CENTER);
    label.setFont(createFont(label, the_size));
    return label;
  }

  /**
   * Refresh to show current panel.
   */
  private void refresh()
  {
    add(my_panel);
    my_panel.setPreferredSize(new Dimension(GeneralUI.WIDTH, GeneralUI.HEIGHT));
    pack();
  }
}

/**
 * Types of panels.
 * 
 * @author Phil Adriaan
 * @version 1.
 */
enum PanelType
{
  /**
   * Main panel.
   */
  MAIN,
  /**
   * Views bids.
   */
  BIDDER,
  /**
   * Shows a list of auctions.
   */
  AUCTION_INDEX,
  /**
   * Shows information for selected auction.
   */
  AUCTION,
  /**
   * Shows list of items from an auction.
   */
  ITEM_INDEX,
  /**
   * Shows info on a selected item.
   */
  ITEM,
  /**
   * For bidding items.
   */
  BID,
  /**
   * Displays status of bid.
   */
  STATE;
}
