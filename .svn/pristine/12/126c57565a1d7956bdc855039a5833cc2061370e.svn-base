package auctioncentral;
import java.util.Arrays;

/**
 * Stores user data.
 * 
 * @author Phil Adriaan
 * @author Tyson Nottingham (minor edits)
 * @version 1
 */
public class User
{
  /**
   * Name of user.
   */
  private final String my_name;
  /**
   * Type of user.
   */
  private final UserType my_type;

  /**
   * Creates new user.
   * 
   * @param the_name Name of user.
   * @param the_type Type of user.
   */
  public User(final String the_name, final UserType the_type)
  {
    my_name = the_name;
    my_type = the_type;
  }

  /**
   * 
   * @return Name of user.
   */
  public String getName()
  {
    return my_name;
  }

  /**
   * 
   * @return Type of user.
   */
  public UserType getType()
  {
    return my_type;
  }

  /**
   * Returns true if the other object is an instance of User with the same name
   * and user type.
   * 
   * @author Tyson Nottingham
   * @param the_other The object to which to compare.
   * @return true if the other object is an instance of User with the same name
   *         and user type.
   */
  @Override
  public boolean equals(final Object the_other)
  {
    if (this == the_other)
    {
      return true;
    }
    else if (the_other instanceof User)
    {
      final User other = (User) the_other;
      return my_name.equals(other.my_name) && my_type.equals(other.my_type);
    }
    return false;
  }

  // @author Tyson Nottingham
  @Override
  public int hashCode()
  {
    return toString().hashCode();
  }

  @Override
  public String toString()
  {
    final StringBuilder builder = new StringBuilder();
    builder.append(my_name);
    builder.append(" is a");
    if (Arrays.asList(new Character[] {'A', 'E', 'I', 'O', 'U'}).contains(my_type.toString().
                                                                          charAt(0)))
    {
      builder.append('n');
    }
    builder.append(' ');
    builder.append(EnumFormat.format(my_type));
    return builder.toString();
  }
}
