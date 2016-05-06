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
}
