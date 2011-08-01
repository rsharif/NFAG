/**
 * Copyright 2011 Rizwan Sharif
 */
package com.rizwan.ui;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.rizwan.helper.FacebookField;
import com.rizwan.helper.FacebookHomePage;
import com.rizwan.helper.FacebookLoginPage;
import com.rizwan.helper.ShareWith;
import com.thoughtworks.selenium.DefaultSelenium;
 

/**
 * @author Rizwan Sharif
 *
 */

public class FacebookTest {
	
	private  DefaultSelenium selenium;
	private  FacebookLoginPage loginPage;
	private  FacebookHomePage homePage;
	@BeforeClass
	public  void setup(){
		Properties properties = new Properties();
		try {
		    properties.load(this.getClass().getClassLoader().getSystemResourceAsStream("app.properties"));
		} catch (IOException e) {
		
		}
		
		String seleniumUrl = properties.getProperty("slRcUrl","localhost");
		int seleniumPort = Integer.parseInt(properties.getProperty("slRcPort","4444"));
		String seleniumCommand= properties.getProperty("slRcStCm","*firefox");
		
		selenium=new DefaultSelenium(seleniumUrl, seleniumPort , seleniumCommand,"http://facebook.com/");
		selenium.start();
	}
	@Test
	public void homePageTest() throws Exception{
		loginPage = new FacebookLoginPage(selenium);
		loginPage.open();
		assertTrue(loginPage.isLoginPageActive());
	}
	@Test(dependsOnMethods={"homePageTest"})
	public void loginTest() throws Exception {
		homePage=loginPage.loginByPressingEnterKey("nfagtester@gmail.com", "12345678" );
		assertTrue(homePage.isHomePageActive());
	}
	
	@Test(dependsOnMethods={"loginTest"})
	public void updateStatusTest() throws Exception{
		String status  = "Sharing Test Status with Friends Of Friends only at "+(new Date ()).toString();
		homePage.updateStatus(status, ShareWith.FRIENDS_OF_FRIENDS);
		assertEquals(status , homePage.getTopStreamItem());
		assertEquals(ShareWith.FRIENDS_OF_FRIENDS,homePage.getPrivacyOfNthPost(1));
		
	}
	@Test(dependsOnMethods={"updateStatusTest"})
	public void logoutTest() throws Exception{	
		homePage.logout(FacebookField.LOGOUT_BUTTON_LOCATER);
		assertFalse(homePage.isHomePageActive());
		assertTrue(loginPage.isLoginPageActive());	
		
	}
	@AfterClass
	public  void destroy(){
		selenium.stop();
	}
}
