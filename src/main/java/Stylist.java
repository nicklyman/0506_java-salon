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

  public int getStylistId() {
    return id;
  }

  public static List<Stylist> all() {
    String sql = "SELECT id, stylist_name FROM stylists";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Stylist.class);
    }
  }

  @Override
  public boolean equals(Object otherStylist) {
    if (!(otherStylist instanceof Stylist)) {
      return false;
    } else {
      Stylist newStylist = (Stylist) otherStylist;
      return this.getStylistName().equals(newStylist.getStylistName());
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO stylists(stylist_name) VALUES (:stylist_name)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("stylist_name", this.stylist_name)
        .executeUpdate()
        .getKey();
    }
  }
}
