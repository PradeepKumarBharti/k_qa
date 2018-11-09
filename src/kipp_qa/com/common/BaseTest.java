package kipp_qa.com.common;

import java.io.File;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

public class BaseTest {
	static public IOSDriver<IOSElement> app = null;

	static private DesiredCapabilities cap = new DesiredCapabilities();
	static private String URL = "https://0.0.0.0:4723/wd/hub";

	@SuppressWarnings("deprecation")
	public static IOSDriver<IOSElement> initIOSDriver() {
		startAppiumServer();
		try {
			cap.setCapability("app", "/Users/pbharti/Downloads/ipa_ipa/p/k-qa-v3.app");
			cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
			cap.setCapability(IOSMobileCapabilityType.SIMPLE_ISVISIBLE_CHECK, "true");
			cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, "11.4");
			cap.setCapability(MobileCapabilityType.PLATFORM, "iOS");
			cap.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone X");
			cap.setCapability("wdaLocalPort", 8100);
			cap.setCapability("noReset", true);
			cap.setCapability("newCommandTimeout", 60 * 600);
			cap.setCapability("--session-override", true);
			cap.setCapability("--show-ios-log", true);
			System.out.println("\n Launching the app in device*************************\n");
			app = new IOSDriver<IOSElement>(new URL(URL), cap);
			Thread.sleep(9000);
			app.manage().timeouts().implicitlyWait(5000, TimeUnit.SECONDS);
		} catch (Exception e) {
			System.out.println("Exception " + e.getMessage());
		}
		return app;
	}

	public static void startAppiumServer() {
		AppiumServiceBuilder builder = new AppiumServiceBuilder();

		builder.withAppiumJS(
				new File("/Applications/Appium.app/Contents/Resources/app/node_modules/appium/lib/main.js"))
				.usingDriverExecutable(new File("/usr/local/bin/node"))
				.withArgument(GeneralServerFlag.LOG_LEVEL, "info").usingAnyFreePort();
		AppiumDriverLocalService appiumService = builder.build();
		appiumService.start();

		String appiumServiceUrl = appiumService.getUrl().toString();
		System.out.println("Appium Service Address : - " + appiumServiceUrl);
	}
}
