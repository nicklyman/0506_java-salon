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

  public int getClientId() {
    return id;
  }

  public static List<Client> all() {
    String sql = "SELECT * FROM clients";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Client.class);
    }
  }

  @Override
  public boolean equals(Object otherClient) {
    if (!(otherClient instanceof Client)) {
      return false;
    } else {
      Client newClient = (Client) otherClient;
      return this.getClientName().equals(newClient.getClientName());
    }
  }

  public void save(){
    try (Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO clients (client_name, stylist_id) VALUES (:name, :stylist_id)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name" , this.client_name)
        .addParameter("stylist_id", this.stylist_id)
        .executeUpdate()
        .getKey();
    }
  }

  public static Client find(int id) {
    try (Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM clients WHERE id=:id";
      Client client = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Client.class);
      return client;
    }
  }

  public void update(String client_name) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE clients SET client_name = :client_name WHERE id = :id";
      con.createQuery(sql)
        .addParameter("client_name", client_name)
        .addParameter("id", this.getClientId())
        .executeUpdate();
    }
  }
}
