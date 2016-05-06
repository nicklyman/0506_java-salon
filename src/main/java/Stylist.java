import org.sql2o.*;
import java.util.Arrays;
import java.util.List;

public class Stylist {
  private int id;
  private String stylist_name;

  public Stylist(String stylist_name) {
    this.stylist_name = stylist_name;
  }
}
