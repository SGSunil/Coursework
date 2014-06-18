import javax.swing.plaf.metal.DefaultMetalTheme;
import javax.swing.plaf.metal.OceanTheme;

/**
 * This class describes a theme using "ocean" colors.
 * 
 * @version 1.11 11/17/05
 * @author Sunil Mandhan
 */
public class OceanThemeEx extends OceanTheme {

	public String getName() {
		return Messages.getString("OCEAN_THEMES");
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getName();
	}
}
