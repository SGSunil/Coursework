import javax.swing.plaf.metal.DefaultMetalTheme;

/**
 * This class describes a theme using "steel" colors.
 * 
 * @version 1.11 11/17/05
 * @author Sunil Mandhan
 */
public class DefaultMetalThemeEx extends DefaultMetalTheme {

	public String getName() {
		return Messages.getString("DEFAULT_THEMES");
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getName();
	}

}
