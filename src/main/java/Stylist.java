import org.sql2o.*;
import java.util.Arrays;
import java.util.List;

public class Stylist {
  private int id;
  private String stylist_name;

  public Stylist(String stylist_name) {
    this.stylist_name = stylist_name;
  }

  public String getStylistName() {
    return stylist_name;
  }

  public static List<Stylist> all() {
    String sql = "SELECT id, stylist_name FROM stylists";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Stylist.class);
    }
  }
}
