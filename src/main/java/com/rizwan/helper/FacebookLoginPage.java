package com.rizwan.helper;

import com.thoughtworks.selenium.DefaultSelenium;

public class FacebookLoginPage {
	private DefaultSelenium selenium;
	
	public FacebookLoginPage(DefaultSelenium selenium){
		this.selenium=selenium;
	}
	
	public FacebookHomePage loginByPressingEnterKey(String userName,String password ){
		selenium.type(FacebookField.LOGIN_PAGE_USERNAME_FIELD_LOCATER , userName);
		selenium.type(FacebookField.LOGIN_PAGE_PASSWORD_FIELD_LOCATER , password);
		selenium.keyPress(FacebookField.LOGIN_PAGE_PASSWORD_FIELD_LOCATER, "13");
		selenium.waitForPageToLoad(FacebookField.DEFAULT_SELENIUM_WAIT);		
		return new FacebookHomePage(selenium);
	}
	public boolean isLoginPageActive(){
		return selenium.isTextPresent("Keep me logged in");
	}
	public void open(){
		selenium.open("/");
	}
}
