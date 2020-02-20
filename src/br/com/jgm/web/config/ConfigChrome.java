package br.com.jgm.web.config;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.github.bonigarcia.wdm.WebDriverManager;



public class ConfigChrome {

	private ChromeDriver wd = new ChromeDriver(configuracoesChrome());

	public ChromeOptions configuracoesChrome() {

		ChromeOptions options = new ChromeOptions();
		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();

		chromePrefs.put("safebrowsing.enabled", true);
		chromePrefs.put("download.directory_upgrade", true);
		chromePrefs.put("download.prompt_for_download", false);
		chromePrefs.put("download.default_directory", "C:\\Users\\walter.marques\\Documents\\RETENCOES\\NFTS");
		chromePrefs.put("useAutomationExtension", false);

		options.addArguments("--disable-extensions");
		options.addArguments("download.default_directory", "C:\\Users\\walter.marques\\Documents\\RETENCOES\\NFTS");
		options.addArguments("--safebrowsing-disable-download-protection");
		options.addArguments("safebrowsing-disable-extension-blacklist");
		options.addArguments("--ignore-certificate-errors");
		options.addArguments("--disable-web-security");
		options.setExperimentalOption("prefs", chromePrefs);

		DesiredCapabilities cap = DesiredCapabilities.chrome();
		cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		cap.setCapability(ChromeOptions.CAPABILITY, options);

		return options;
	}

	public ChromeDriver chromeDriver() throws InterruptedException {

		WebDriverManager.chromedriver().setup();
		wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);

		return wd;
	}

}
