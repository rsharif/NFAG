/**
 * Copyright 2011 Rizwan Sharif
 */
package com.rizwan.integration;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.rizwan.helper.FacebookHelper;
import com.rizwan.helper.FacebookXpathHelper;
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
		    properties.load(this.getClass().getClassLoader().getSystemResourceAsStream("selenium.properties"));
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
		homePage=loginPage.loginByPressingEnterKey(FacebookHelper.LOGIN_TEST_USERNAME, FacebookHelper.LOGIN_TEST_PASSWORD );
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
		homePage.logout(FacebookXpathHelper.LOGOUT_BUTTON_LOCATER);
		assertFalse(homePage.isHomePageActive());
		assertTrue(loginPage.isLoginPageActive());	
		
	}
	@AfterClass
	public  void destroy(){
		selenium.stop();
	}
}
