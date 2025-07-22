package tests;

import base.TestBase;
import pages.ItemPage;
import pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ItemTests extends TestBase {

    @Test(priority = 1)
    public void createNewItem() {
        ItemPage itemPage = new ItemPage(driver);
        itemPage.goTo();
        String itemName = "Test Item";
        itemPage.createItem(itemName);
        Assert.assertTrue(itemPage.isItemPresent(itemName), "New item should be present after creation");
    }

    @Test(priority = 2)
    public void editExistingItem() {
        ItemPage itemPage = new ItemPage(driver);
        itemPage.goTo();
        String originalName = "Test Item";
        String updatedName = "Updated Test Item";
        itemPage.editItem(originalName, updatedName);
        Assert.assertTrue(itemPage.isItemPresent(updatedName), "Item should be updated");
    }

    @Test(priority = 3)
    public void deleteItem() {
        ItemPage itemPage = new ItemPage(driver);
        itemPage.goTo();
        String itemName = "Updated Test Item";
        itemPage.deleteItem(itemName);
        Assert.assertFalse(itemPage.isItemPresent(itemName), "Item should be deleted");
    }
}