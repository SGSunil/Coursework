import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * This class describes the logic to interact with resource bundles.
 * all the possible resource bundles are handled by this class.
 * 
 * @version 1.19
 * @author Sunil Mandhan
 */
public class Messages {
	private static final String BUNDLE_NAME = "resources.Strings"; //$NON-NLS-1$

	private static ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(
			BUNDLE_NAME, new Locale("fr", "FR"));

	private static Locale currentLocale;

	private Messages() {
	}

	//This method is used to set the defatul locale.
	public static void setDefaultLocale(Enums locale_id) {
		switch (locale_id) {
		case ENGLISH:
			currentLocale = new Locale("en", "US");
			Locale.setDefault(currentLocale);
			break;
		case FRENCH:
			currentLocale = new Locale("fr", "FR");
			Locale.setDefault(currentLocale);
			break;
		}

		RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME,
				Locale.getDefault());
	}

	//this method fetches strings (values) from the resource bundle corresponding to keys. 
	public static String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}
}
