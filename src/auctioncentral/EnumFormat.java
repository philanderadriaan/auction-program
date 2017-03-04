package auctioncentral;
/**
 * Utility classes to format Enums into readable String.
 * 
 * @author Phil Adriaan
 * @version 1
 */
public final class EnumFormat
{
  /**
   * Prevents instantiation.
   */
  private EnumFormat()
  {
  }

  /**
   * Formats Size Enums. For example: Size.EXTRA_LARGE will return
   * "Extra Large".
   * 
   * Pre: Size Enum.
   * 
   * Post: Formatted String of Size Enum.
   * 
   * @param the_enum Size Enum.
   * @return Formatted String of Size Enum.
   */
  public static String format(final Size the_enum)
  {
    return format(the_enum.toString());
  }

  /**
   * Formats Condition Enums. For example: Condition.VERY_GOOD will return
   * "Very Good".
   * 
   * Pre: Condition Enum.
   * 
   * Post: Formatted String of Condition Enum.
   * 
   * @param the_enum Condition Enum.
   * @return Formatted String of Condition Enum.
   */
  public static String format(final Condition the_enum)
  {
    return format(the_enum.toString());
  }

  /**
   * Formats UserType Enums. For example: UserType.ACE will return
   * "Auction Central Employee".
   * 
   * Pre: UserType Enum.
   * 
   * Post: Formatted String of UserType Enum.
   * 
   * @param the_enum UserType Enum.
   * @return Formatted String of UserType Enum.
   */
  public static String format(final UserType the_enum)
  {
    switch (the_enum)
    {
      case ACE:
        return "Auction Central Employee";
      case NPO:
        return "NPO Staff Member";
      default:
        return format(the_enum.toString());
    }
  }

  /**
   * Formats the String version of the Enum.
   * 
   * @param the_enum_string String version of Enum.
   * @return Formatted String of Enum.
   */
  private static String format(final String the_enum_string)
  {
    boolean caps_lock = true;
    final char[] char_array = the_enum_string.toCharArray();
    for (int i = 0; i < char_array.length; i++)
    {
      if (char_array[i] == '_')
      {
        char_array[i] = ' ';
        caps_lock = true;
      }
      else if (caps_lock)
      {
        caps_lock = false;
      }
      else
      {
        char_array[i] = Character.toLowerCase(char_array[i]);
      }
    }
    return new String(char_array);
  }
}
