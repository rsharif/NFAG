package com.rizwan.helper;

import com.thoughtworks.selenium.DefaultSelenium;

public class FacebookHomePage {
	private DefaultSelenium selenium;
	
	public FacebookHomePage(DefaultSelenium selenium){
		this.selenium = selenium;
	}
	
	public FacebookHomePage updateStatus(String status , ShareWith privacy ) throws Exception{
		selenium.click(FacebookXpathHelper.STATUS_LINK_AFTER_SHARE);
		selenium.waitForCondition(" selenium.isVisible(\""+FacebookXpathHelper.SHARE_BUTTON_LOCATER+"\"); ", FacebookHelper.DEFAULT_SELENIUM_WAIT);
		selenium.type(FacebookXpathHelper.STATUS_UPDATE_TEXTAREA_LOCATER, status);
		this.setPrivacyToPost(privacy);
		selenium.click(FacebookXpathHelper.SHARE_BUTTON_LOCATER);		
		selenium.waitForCondition("selenium.isElementPresent(\""+FacebookXpathHelper.getNthPostMessageBodyXpath(1)+"\"); ", FacebookHelper.DEFAULT_SELENIUM_WAIT);
		selenium.waitForCondition("var value=selenium.getText(\""+FacebookXpathHelper.getNthPostMessageBodyXpath(1)+"\");  value.match('"+status+"')", FacebookHelper.DEFAULT_SELENIUM_WAIT);
		return this;
	}
	protected void setPrivacyToPost(ShareWith privacy){
		selenium.click(FacebookXpathHelper.PRIVACY_LOCK_ICON_LOCATER);
		switch(privacy)
		{
			case FRIENDS_OF_FRIENDS :
				selenium.click(FacebookXpathHelper.PRIVACY_FRIENDS_OF_FRIENDS);
				break;
			case EVERYONE :
				//TODO
				break;
			case FRIENDS_ONLY :
				//TODO
				break;
			case CUSTOMIZE :
				//TODO
				break;
			default:
				//TODO
		}
	}
	public boolean isHomePageActive(){
		return selenium.isTextPresent("John Keats");
	}
	public ShareWith getPrivacyOfNthPost(int n){
		
		String privacy = selenium.getText(FacebookXpathHelper.getNthPostPrivacyControlXpath(n));
		if(privacy.equalsIgnoreCase("Friends of Friends")){
			return ShareWith.FRIENDS_OF_FRIENDS;
		}
		else if(privacy.equals("Friends Only")){
			return ShareWith.FRIENDS_ONLY;
		}
		else
		return ShareWith.EVERYONE;
	}
	public String getTopStreamItem(){
		return this.getNthStreamMessage(1);
	}
	public String getNthStreamMessage(int n){
		return selenium.getText(FacebookXpathHelper.getNthPostMessageBodyXpath(n));
	}
	
	public void logout(String logoutButtonLocator){
		selenium.click(logoutButtonLocator);
		selenium.waitForPageToLoad(FacebookHelper.DEFAULT_SELENIUM_WAIT);
	}
}
