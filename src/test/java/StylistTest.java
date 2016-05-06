import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;

public class StylistTest {

  @Before
  public void setUp() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/hair_salon_test", null, null);
  }

  @After
  public void tearDown() {
    try(Connection con = DB.sql2o.open()) {
      String deleteStylistsQuery = "DELETE FROM stylists *;";
      String deleteClientsQuery = "DELETE FROM clients *;";
      con.createQuery(deleteStylistsQuery).executeUpdate();
      con.createQuery(deleteClientsQuery).executeUpdate();
    }
  }

  @Test
  public void Stylist_instantiatesCorrectly_true() {
    Stylist testStylist = new Stylist("Joe");
    assertEquals(true, testStylist instanceof Stylist);
  }

  @Test
  public void Stylist_instantiatesWithStylistName_String() {
    Stylist testStylist = new Stylist("Joe");
    assertEquals("Joe", testStylist.getStylistName());
  }

  @Test
  public void all_StylistListEmptyAtFirst() {
    assertEquals(Stylist.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfNamesAreSame() {
    Stylist firstStylist = new Stylist("Joe");
    Stylist secondStylist = new Stylist("Joe");
    assertTrue(firstStylist.equals(secondStylist));
  }

  @Test
  public void save_assignsIdToObject() {
    Stylist testStylist = new Stylist("Joe");
    testStylist.save();
    Stylist savedStylist = Stylist.all().get(0);
    assertEquals(testStylist.getStylistId(), savedStylist.getStylistId());
  }
}
