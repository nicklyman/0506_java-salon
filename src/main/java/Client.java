import org.sql2o.*;
import java.util.Arrays;
import java.util.List;

public class Client {
  private int id;
  private String client_name;
  private int stylist_id;

  public Client(String client_name, int stylist_id) {
    this.client_name = client_name;
    this.stylist_id = stylist_id;
  }

  public String getClientName() {
    return client_name;
  }

  public static List<Client> all() {
    String sql = "SELECT * FROM clients";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Client.class);
    }
  }
}
