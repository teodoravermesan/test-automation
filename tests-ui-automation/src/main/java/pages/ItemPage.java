package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ItemPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By newItemInput = By.id("newItem");
    private By createButton = By.id("createItemBtn");
    private By itemsList = By.id("itemsList");

    public ItemPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void goTo() {
        driver.get("http://localhost:3000/items");
    }

    public void createItem(String itemName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(newItemInput)).clear();
        driver.findElement(newItemInput).sendKeys(itemName);
        driver.findElement(createButton).click();
        wait.until(ExpectedConditions.textToBePresentInElementLocated(itemsList, itemName));
    }

    public void editItem(String oldName, String newName) {
        By itemEditButton = By.xpath("//li[contains(text(),'" + oldName + "')]//button[contains(@class,'edit')]");
        By itemInput = By.xpath("//li[contains(text(),'" + oldName + "')]//input[contains(@class,'edit-input')]");
        By saveButton = By.xpath("//li[contains(text(),'" + oldName + "')]//button[contains(@class,'save')]");

        wait.until(ExpectedConditions.elementToBeClickable(itemEditButton)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(itemInput)).clear();
        driver.findElement(itemInput).sendKeys(newName);
        driver.findElement(saveButton).click();

        wait.until(ExpectedConditions.textToBePresentInElementLocated(itemsList, newName));
    }

    public void deleteItem(String itemName) {
        By deleteButton = By.xpath("//li[contains(text(),'" + itemName + "')]//button[contains(@class,'delete')]");
        wait.until(ExpectedConditions.elementToBeClickable(deleteButton)).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//li[contains(text(),'" + itemName + "')]")));
    }

    public boolean isItemPresent(String itemName) {
        return !driver.findElements(By.xpath("//li[contains(text(),'" + itemName + "')]")).isEmpty();
    }
}
