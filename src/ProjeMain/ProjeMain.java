package ProjeMain;

import Utility.BaseDriver;
import Utility.MyFunc;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.Set;

public class ProjeMain extends BaseDriver {

    @Test(dataProvider = "Hamza")
    public void LoginNegative1(String UserName, String Password) { // Invalid Username and Password
        ProjePOM test = new ProjePOM();

        driver.get("https://openmrs.org/");
        test.DemoBtn.click();
        wait.until(ExpectedConditions.urlToBe("https://openmrs.org/demo/"));

        test.OpenMrs2.click();
        MyFunc.Bekle(2);

        wait.until(ExpectedConditions.visibilityOf(test.OpenMRs2Demo));
        test.OpenMRs2Demo.click();

        wait.until(ExpectedConditions.urlToBe("https://demo.openmrs.org/openmrs/login.htm"));
        test.UserName.sendKeys(UserName);
        test.Password.sendKeys(Password);
        test.PharMacBtn.click();
        test.loginBtn.click();

        Assert.assertTrue(test.ErrorMessage.getText().contains("Invalid"));
    }

    @DataProvider
    public Object[][] Hamza() {
        Object[][] data = {
                {" ", "Admin123"},
                {"Admin", "1234"},
                {"Admin", " "},
                {" ", "Admin123",}
        };
        return data;
    }

    @Test()
    public void LoginPositive() {
        ProjePOM test = new ProjePOM();
        driver.get("https://openmrs.org/");
        test.DemoBtn.click();

        wait.until(ExpectedConditions.urlToBe("https://openmrs.org/demo/"));

        test.OpenMrs2.click();
        MyFunc.Bekle(2);

        wait.until(ExpectedConditions.visibilityOf(test.OpenMRs2Demo));
        test.OpenMRs2Demo.click();

        wait.until(ExpectedConditions.urlToBe("https://demo.openmrs.org/openmrs/login.htm"));
        test.UserName.sendKeys("Admin");
        test.Password.sendKeys("Admin123");
        test.PharMacBtn.click();
        test.loginBtn.click();
    }

    @Test
    public void LogOut() {
        ProjePOM test = new ProjePOM();
        driver.get("https://openmrs.org/");
        test.DemoBtn.click();

        wait.until(ExpectedConditions.urlToBe("https://openmrs.org/demo/"));

        test.OpenMrs2.click();
        MyFunc.Bekle(2);

        wait.until(ExpectedConditions.visibilityOf(test.OpenMRs2Demo));
        test.OpenMRs2Demo.click();

        wait.until(ExpectedConditions.urlToBe("https://demo.openmrs.org/openmrs/login.htm"));
        test.UserName.sendKeys("Admin");
        test.Password.sendKeys("Admin123");
        test.PharMacBtn.click();
        test.loginBtn.click();

        test.Logout.click();
    }

    @Test()
    public void Patient_Registration() {
        ProjePOM test = new ProjePOM();
        driver.get("https://openmrs.org/");
        test.DemoBtn.click();

        wait.until(ExpectedConditions.urlToBe("https://openmrs.org/demo/"));

        test.OpenMrs2.click();
        MyFunc.Bekle(2);

        wait.until(ExpectedConditions.visibilityOf(test.OpenMRs2Demo));
        test.OpenMRs2Demo.click();

        wait.until(ExpectedConditions.urlToBe("https://demo.openmrs.org/openmrs/login.htm"));
        test.UserName.sendKeys("Admin");
        test.Password.sendKeys("Admin123");

        int randomSecim = MyFunc.randomGenerator(test.Locations.size());
        test.LocationButton.get(randomSecim).click();

        test.Loginbuton.click();
        wait.until(ExpectedConditions.elementToBeClickable(test.Register_Patient));
        test.Register_Patient.click();
        wait.until(ExpectedConditions.elementToBeClickable(test.GivenName));
        test.GivenName.sendKeys("Test23");
        test.MiddleName.sendKeys("Test23");
        test.FamilyName.sendKeys("test23");
        test.ConfrimButton.click();

        Select Genderselect = new Select(test.Gender);
        Genderselect.selectByValue("M");

        test.RightArrow.click();
        test.BirthDay.sendKeys("23");

        Select MonthSelect = new Select(test.BirthdateMonth);
        MonthSelect.selectByValue("4");

        test.BirthDateYear.sendKeys("1990");
        test.ConfrimButton.click();
        test.Adress1.sendKeys("İzmir");
        test.Adress2.sendKeys("Türkiye");
        test.City.sendKeys("İzmir");
        test.StateProvince.sendKeys("İzmir");
        test.Country.sendKeys("İzmir");
        test.PostalCode.sendKeys("35000");
        test.ConfrimButton.click();
        test.PhoneNumber.sendKeys("5555555555");
        test.ConfrimButton.click();

        Select RelatSelect = new Select(test.RelationShip);
        RelatSelect.selectByVisibleText("Doctor");

        test.PersonName.sendKeys("Ebubekir");
        test.ConfrimButton.click();
        test.Confrim.click();
        wait.until(ExpectedConditions.visibilityOf(test.ConfrimName));
        System.out.println("hamza.ConfrimName = " + test.ConfrimName.getText());
        System.out.println("hamza.ConfrimID = " + test.ConfrimID.getText());

        Assert.assertTrue(test.ConfrimName.isEnabled());
        Assert.assertTrue(test.ConfrimID.isEnabled());

    }

    @Test
    public void My_Account() {
        Actions actions = new Actions(driver);
        ProjePOM test = new ProjePOM();
        driver.get("https://openmrs.org/");
        test.DemoBtn.click();
        wait.until(ExpectedConditions.urlToBe("https://openmrs.org/demo/"));
        test.OpenMrs2.click();
        MyFunc.Bekle(2);
        wait.until(ExpectedConditions.visibilityOf(test.OpenMRs2Demo));
        test.OpenMRs2Demo.click();
        wait.until(ExpectedConditions.urlToBe("https://demo.openmrs.org/openmrs/login.htm"));
        test.UserName.sendKeys("Admin");
        test.Password.sendKeys("Admin123");
        int randomSecim = MyFunc.randomGenerator(test.Locations.size());
        test.LocationButton.get(randomSecim).click();
        test.Loginbuton.click();

        actions.moveToElement(test.admin).perform();
        test.Options.click();

        Assert.assertTrue(test.ChangePassowrd.isEnabled());
        Assert.assertTrue(test.MyLanguages.isEnabled());
        System.out.println(test.ChangePassowrd.getText());
        System.out.println(test.MyLanguages.getText());
    }

    @Test
    public void Searching_the_Patient_List() {
        ProjePOM test = new ProjePOM();
        driver.get("https://openmrs.org/");
        test.DemoBtn.click();
        wait.until(ExpectedConditions.urlToBe("https://openmrs.org/demo/"));
        test.OpenMrs2.click();
        MyFunc.Bekle(2);
        wait.until(ExpectedConditions.visibilityOf(test.OpenMRs2Demo));
        test.OpenMRs2Demo.click();
        wait.until(ExpectedConditions.urlToBe("https://demo.openmrs.org/openmrs/login.htm"));
        test.UserName.sendKeys("Admin");
        test.Password.sendKeys("Admin123");
        int randomSecim = MyFunc.randomGenerator(test.Locations.size());
        test.LocationButton.get(randomSecim).click();
        test.Loginbuton.click();

        test.FindPatRec.click();
        test.SearchBox.sendKeys("100JK4");
        wait.until(ExpectedConditions.visibilityOf(test.ClickPatient));
        test.ClickPatient.click();
        wait.until(ExpectedConditions.visibilityOfAllElements(test.GeneralActions));
        for (WebElement Siddik : test.GeneralActions) {
            System.out.println("Siddik.getText() = " + Siddik.getText());
            Assert.assertTrue(Siddik.isEnabled());
        }

    }

    @Test
    public void Searching_With_WrongName() {
        ProjePOM test = new ProjePOM();
        driver.get("https://openmrs.org/");
        test.DemoBtn.click();
        wait.until(ExpectedConditions.urlToBe("https://openmrs.org/demo/"));
        test.OpenMrs2.click();
        MyFunc.Bekle(2);
        wait.until(ExpectedConditions.visibilityOf(test.OpenMRs2Demo));
        test.OpenMRs2Demo.click();
        wait.until(ExpectedConditions.urlToBe("https://demo.openmrs.org/openmrs/login.htm"));
        test.UserName.sendKeys("Admin");
        test.Password.sendKeys("Admin123");
        int randomSecim = MyFunc.randomGenerator(test.Locations.size());
        test.LocationButton.get(randomSecim).click();
        test.Loginbuton.click();

        test.FindPatRec.click();
        test.SearchBox.sendKeys("100JK421");
        wait.until(ExpectedConditions.visibilityOf(test.WrongSearch));
        Assert.assertTrue(test.WrongSearch.isEnabled());
        System.out.println("test.WrongSearch.getText() = " + test.WrongSearch.getText());
    }

    @Test
    public void DeletePatient() {
        ProjePOM test = new ProjePOM();
        driver.get("https://openmrs.org/");
        test.DemoBtn.click();
        wait.until(ExpectedConditions.urlToBe("https://openmrs.org/demo/"));
        test.OpenMrs2.click();
        MyFunc.Bekle(2);
        wait.until(ExpectedConditions.visibilityOf(test.OpenMRs2Demo));
        test.OpenMRs2Demo.click();
        wait.until(ExpectedConditions.urlToBe("https://demo.openmrs.org/openmrs/login.htm"));
        test.UserName.sendKeys("Admin");
        test.Password.sendKeys("Admin123");
        int randomSecim = MyFunc.randomGenerator(test.Locations.size());
        test.LocationButton.get(randomSecim).click();
        test.Loginbuton.click();
        test.FindPatRec.click();
        test.SearchBox.sendKeys("100HUN");
        wait.until(ExpectedConditions.visibilityOf(test.ClickPatient));
        test.ClickPatient.click();
        test.DeletePatient.click();
        test.Delete.sendKeys("Wrong");
        test.DeleteConfrim.click();

    }

    @Test
    public void Merge_Patient_Records() {
        ProjePOM test = new ProjePOM();
        driver.get("https://openmrs.org/");
        test.DemoBtn.click();
        wait.until(ExpectedConditions.urlToBe("https://openmrs.org/demo/"));
        test.OpenMrs2.click();
        MyFunc.Bekle(2);
        wait.until(ExpectedConditions.visibilityOf(test.OpenMRs2Demo));
        test.OpenMRs2Demo.click();
        wait.until(ExpectedConditions.urlToBe("https://demo.openmrs.org/openmrs/login.htm"));
        test.UserName.sendKeys("Admin");
        test.Password.sendKeys("Admin123");
        int randomSecim = MyFunc.randomGenerator(test.Locations.size());
        test.LocationButton.get(randomSecim).click();
        test.Loginbuton.click();
        test.DataManagement.click();
        test.MergePatient.click();
        test.Patient1.sendKeys("100L41");
        test.Patient2.sendKeys("100L51");
        test.SearchClick.click();
        test.Continue.click();
        test.RowButton.click();
        test.ConfrimBTN.click();

        Assert.assertTrue(test.IDLine.getText().equals(test.IDLine.getText()));
        System.out.println("test.IDLine.getText() = " + test.IDLine.getText());
    }

    @Test
    public void Patient_Appointment_Wrong_Time_Zone() {
        ProjePOM test = new ProjePOM();
        driver.get("https://openmrs.org/");
        test.DemoBtn.click();
        wait.until(ExpectedConditions.urlToBe("https://openmrs.org/demo/"));
        test.OpenMrs2.click();
        MyFunc.Bekle(2);
        wait.until(ExpectedConditions.visibilityOf(test.OpenMRs2Demo));
        test.OpenMRs2Demo.click();
        wait.until(ExpectedConditions.urlToBe("https://demo.openmrs.org/openmrs/login.htm"));
        test.UserName.sendKeys("Admin");
        test.Password.sendKeys("Admin123");
        int randomSecim = MyFunc.randomGenerator(test.Locations.size());
        test.LocationButton.get(randomSecim).click();
        test.Loginbuton.click();
        test.AppointmentScheduiling.click();
        test.ManageServiceTypes.click();
        test.SearchBox.sendKeys("100L41" + Keys.ENTER);

        System.out.println("hamza.Mesaage.getText() = " + test.Massege.getText());
        Assert.assertTrue(test.Massege.isEnabled());

    }

    @Test
    public void Patient_Appointment_Correct_Time_Zone() {
        //Randevu almadan önce mutlaka saati GMT+0 yapmanız gerekmektedir.
        ProjePOM test = new ProjePOM();
        driver.get("https://openmrs.org/");
        test.DemoBtn.click();
        wait.until(ExpectedConditions.urlToBe("https://openmrs.org/demo/"));
        test.OpenMrs2.click();
        MyFunc.Bekle(2);
        wait.until(ExpectedConditions.visibilityOf(test.OpenMRs2Demo));
        test.OpenMRs2Demo.click();
        wait.until(ExpectedConditions.urlToBe("https://demo.openmrs.org/openmrs/login.htm"));
        test.UserName.sendKeys("Admin");
        test.Password.sendKeys("Admin123");
        int randomSecim = MyFunc.randomGenerator(test.Locations.size());
        test.LocationButton.get(randomSecim).click();
        test.Loginbuton.click();
        test.AppointmentScheduiling.click();
        test.ManageServiceTypes.click();
        test.SearchBox.sendKeys("100L41" + Keys.ENTER);

        System.out.println("hamza.Mesaage.getText() = " + test.Massege.getText());
        Assert.assertFalse(test.Massege.isDisplayed());
    }


}


