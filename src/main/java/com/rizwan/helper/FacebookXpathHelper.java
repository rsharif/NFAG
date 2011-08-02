package com.rizwan.helper;

public class FacebookXpathHelper {


	public final static String STATUS_UPDATE_TEXTAREA_LOCATER="//textarea[@name='xhpc_message_text']";
	public final static String LOGIN_PAGE_USERNAME_FIELD_LOCATER= "//input[@id='email']";
	public final static String LOGIN_PAGE_PASSWORD_FIELD_LOCATER= "//input[@id='pass']";
	public final static String SHARE_BUTTON_LOCATER= "//label[@class='submitBtn uiButton uiButtonConfirm uiButtonLarge']";
	public final static String LOGOUT_BUTTON_LOCATER= "//input[@value='Log Out']";
	//STATUS_LINK_AFTER_SHARE gives xpath to "Status" text written after Share: on the top of textarea to put status
	public final static String STATUS_LINK_AFTER_SHARE= "//ul[@class='uiList uiListHorizontal clearfix uiComposerAttachments']/li[2]//a";
	public final static String PRIVACY_LOCK_ICON_LOCATER="//a[@data-tooltip='Everyone']";
	public final static String PRIVACY_FRIENDS_OF_FRIENDS="//a[@class='itemAnchor']/span[text()='Friends of Friends']";
	
	public static String getNthPostXpath(int n){
		return "//ul[@id='home_stream']/li["+n+"]";
	}
	public static String getNthPostMessageBodyXpath(int n){
		return getNthPostXpath(n)+"//span[@class='messageBody']";
	}
	public static String getNthPostPrivacyControlXpath(int n){
		return getNthPostXpath(n)+"//a[@class='uiTooltip uiStreamPrivacy']//span[@class='uiTooltipText uiTooltipNoWrap']";
	}
}
