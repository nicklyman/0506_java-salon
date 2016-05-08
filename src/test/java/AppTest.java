import org.sql2o.*; // for DB support
import org.junit.*; // for @Before and @After
import org.fluentlenium.adapter.FluentTest;
import org.junit.ClassRule;
import org.junit.Test;
import static org.junit.Assert.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import static org.assertj.core.api.Assertions.assertThat;
import static org.fluentlenium.core.filter.FilterConstructor.*;

public class AppTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();

  @Override
  public WebDriver getDefaultDriver() {
    return webDriver;
  }

  @ClassRule
  public static ServerRule server = new ServerRule();

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void rootTest() {
    goTo("http://localhost:4567/");
    assertThat(pageSource()).contains("Find the best hair stylists in town!");
    assertThat(pageSource()).contains("View the List of Stylists");
    assertThat(pageSource()).contains("Add a New Stylist");
  }

  @Test
  public void stylistIsCreatedTest() {
    goTo("http://localhost:4567/");
    click("a", withText("Add a New Stylist"));
    fill("#stylist_name").with("Joe");
    submit(".btn");
    assertThat(pageSource()).contains("The new stylist has been added.");
  }

  @Test
  public void stylistIsDisplayedTest() {
    goTo("http://localhost:4567/stylists/new");
    fill("#stylist_name").with("Joe");
    submit(".btn");
    click("a", withText("View the List of Stylists"));
    assertThat(pageSource()).contains("Joe");
  }

  @Test
  public void stylistShowPageDisplaysStylistName() {
    goTo("http://localhost:4567/stylists/new");
    fill("#stylist_name").with("Joe");
    submit(".btn");
    click("a", withText("View the List of Stylists"));
    click("a", withText("Joe"));
    assertThat(pageSource()).contains("Joe");
  }

  @Test
  public void stylistClientsFormIsDisplayed() {
    goTo("http://localhost:4567/stylists/new");
    fill("#stylist_name").with("Joe");
    submit(".btn");
    click("a", withText("View the List of Stylists"));
    click("a", withText("Joe"));
    click("a", withText("Add a New Client"));
    assertThat(pageSource()).contains("Add a new client for Joe");
  }

  @Test
  public void clientIsAddedAndDisplayed() {
    goTo("http://localhost:4567/stylists/new");
    fill("#stylist_name").with("Joe");
    submit(".btn");
    click("a", withText("View the List of Stylists"));
    click("a", withText("Joe"));
    click("a", withText("Add a New Client"));
    fill("#client_name").with("Sara");
    submit(".btn");
    click("a", withText("View the List of Stylists"));
    click("a", withText("Joe"));
    assertThat(pageSource()).contains("Sara");
  }

  @Test
  public void stylistUpdate() {
    Stylist testStylist = new Stylist("Joe");
    testStylist.save();
    String stylistPath = String.format("http://localhost:4567/stylists/%d", testStylist.getStylistId());
    goTo(stylistPath);
    fill("#stylist_name").with("Joey");
    submit("#update_stylist");
    assertThat(pageSource()).contains("Joey");
  }

  @Test
  public void stylistDelete() {
    Stylist testStylist = new Stylist("Joe");
    testStylist.save();
    String stylistPath = String.format("http://localhost:4567/stylists/%d", testStylist.getStylistId());
    goTo(stylistPath);
    submit("#delete_stylist");
    assertEquals(0, Stylist.all().size());
    assertThat(pageSource()).contains("The stylist has been removed from the directory.");
  }

  // @Test
  // public void clientUpdate() {
  //   Stylist testStylist = new Stylist("Chopper");
  //   testStylist.save();
  //   Client testClient = new Client("Jean", testStylist.getStylistId());
  //   testClient.save();
  //   String clientPath = String.format("http://localhost:4567/stylists/%d/clients/%d", testStylist.getStylistId(), testClient.getClientId());
  //   goTo(clientPath);
  //   fill("#client_name").with("Betty-Jean");
  //   submit("#update_client");
  //   assertThat(pageSource()).contains("Betty-Jean");
  // }

  // @Test
  // public void clientDelete() {
  //   Stylist testStylist = new Stylist("Chopper");
  //   testStylist.save();
  //   Client testClient = new Client("Jane", testStylist.getStylistId());
  //   testClient.save();
  //   String clientPath = String.format("http://localhost:4567/categories/%d/clients/%d", testStylist.getStylistId(), testClient.getClientId());
  //   goTo(clientPath);
  //   submit("#delete_client");
  //   assertEquals(0, Client.all().size());
  // }
}
