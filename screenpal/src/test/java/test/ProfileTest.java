package test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import main.BaseTest;
import main.ProfilePage;

public class ProfileTest extends BaseTest {

    private ProfilePage profilePage;

    @Override
    @BeforeClass
    public void setup() {
        super.setup();
        profilePage = new ProfilePage(driver, wait);
    }

    
    @Test(priority = 1)
    public void openProfilePage() {
        login();
        profilePage.openProfilePage();
        System.out.println("Profile page opened successfully");
    }

    @Test(priority = 2)
    public void updateDisplayName() {
        profilePage.openProfilePage();
        profilePage.updateDisplayName("hazem");
        String msg = profilePage.getSuccessMessage();
        Assert.assertTrue(msg.toLowerCase().contains("update"));
        System.out.println("Profile updated successfully");
    }

    @DataProvider(name = "invalidFirstNames")
    public Object[][] invalidFirstNames() {
        return new Object[][] {
            // { input,                        shouldShowError, keyword,           description         }
            { "",                              true,            "something wrong",  "Empty name"        },
            { "   ",                          true,            "something wrong",  "Whitespace only"   },
            { "a".repeat(256),               true,            "something wrong",  "Exceeds max length"},
            { "hazem@#$",                     true,            "something wrong",  "Special characters"},
            { "<script>alert(1)</script>",    true,            "something wrong",  "XSS injection"     },
            { "hazem123",                     false,           "",                 "Contains numbers"  },
            { "12345",                        false,           "",                 "Numbers only"      },
        };
    }

 
    @Test(
        priority = 3,
        dataProvider = "invalidFirstNames",
        description = "Verify error or acceptance behavior for invalid first name inputs"
    )
    public void updateDisplayNameWithInvalidData(
            String invalidName,
            boolean shouldShowError,
            String expectedKeyword,
            String caseDescription) {

        profilePage.openProfilePage();
        profilePage.updateDisplayName(invalidName);

        if (shouldShowError) {
            String errorMsg = profilePage.getAnyToastMessage();
            Assert.assertNotNull(errorMsg,"[" + caseDescription + "] Expected an error toast but got none");
            System.out.println("[PASS – error shown] " + caseDescription + " -> " + errorMsg.trim());
       
        } else {
         
            String errorMsg = profilePage.getErrorMessage();

            Assert.assertNull(
                errorMsg,
                "[" + caseDescription + "] Expected no error (ScreenPal accepts this), but got: " + errorMsg
            );

            System.out.println("[WARN – missing validation] " + caseDescription
                + ": ScreenPal accepted '" + invalidName + "' — no server-side validation for numbers in name.");
        }
    }

    @DataProvider(name = "rejectedInputs")
    public Object[][] rejectedInputs() {
        return new Object[][] {
            { "",                            "Empty name"        },
            { "   ",                        "Whitespace only"   },
            { "a".repeat(256),              "Exceeds max length"},
            { "hazem@#$",                   "Special characters"},
            { "<script>alert(1)</script>",  "XSS injection"     },
        };
    }

    @Test(
        priority = 4,
        dataProvider = "rejectedInputs",
        description = "Verify field value is unchanged after a rejected save"
    )
    public void invalidInputShouldNotBeSaved(String invalidName,String caseDescription) {

        profilePage.openProfilePage();
        String originalName = profilePage.getFirstNameValue();

        profilePage.updateDisplayName(invalidName);

        profilePage.openProfilePage();
        String currentName = profilePage.getFirstNameValue();

        Assert.assertEquals(currentName,originalName,
            "[" + caseDescription + "] Value should not have been saved. "+ "Expected: '" + originalName + "', but found: '" + currentName + "'"
        );

        System.out.println("[PASS] " + caseDescription
            + " -> Field unchanged: '" + currentName + "'");
    }
}