/*
 * @(#)BigContrastMetalTheme.java	1.16 05/11/17
 * 
 * Copyright (c) 2006 Sun Microsystems, Inc. All Rights Reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 
 * -Redistribution of source code must retain the above copyright notice, this
 *  list of conditions and the following disclaimer.
 * 
 * -Redistribution in binary form must reproduce the above copyright notice, 
 *  this list of conditions and the following disclaimer in the documentation
 *  and/or other materials provided with the distribution.
 * 
 * Neither the name of Sun Microsystems, Inc. or the names of contributors may 
 * be used to endorse or promote products derived from this software without 
 * specific prior written permission.
 * 
 * This software is provided "AS IS," without a warranty of any kind. ALL 
 * EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND WARRANTIES, INCLUDING
 * ANY IMPLIED WARRANTY OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE
 * OR NON-INFRINGEMENT, ARE HEREBY EXCLUDED. SUN MIDROSYSTEMS, INC. ("SUN")
 * AND ITS LICENSORS SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE
 * AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS
 * DERIVATIVES. IN NO EVENT WILL SUN OR ITS LICENSORS BE LIABLE FOR ANY LOST 
 * REVENUE, PROFIT OR DATA, OR FOR DIRECT, INDIRECT, SPECIAL, CONSEQUENTIAL, 
 * INCIDENTAL OR PUNITIVE DAMAGES, HOWEVER CAUSED AND REGARDLESS OF THE THEORY 
 * OF LIABILITY, ARISING OUT OF THE USE OF OR INABILITY TO USE THIS SOFTWARE, 
 * EVEN IF SUN HAS BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGES.
 * 
 * You acknowledge that this software is not designed, licensed or intended
 * for use in the design, construction, operation or maintenance of any
 * nuclear facility.
 */

/*
 * @(#)BigContrastMetalTheme.java	1.16 05/11/17
 */

import javax.swing.plaf.*;
import javax.swing.plaf.metal.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

/**
 * This class describes a theme using "green" colors.
 * 
 * @version 1.16 11/17/05
 * @author Steve Wilson
 */
public class BigContrastMetalTheme extends ContrastMetalTheme {

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getName();
	}

	public String getName() {
		return Messages.getString("LOW_VISION_THEMES"); //$NON-NLS-1$
	}

	private final FontUIResource controlFont = new FontUIResource("Dialog", //$NON-NLS-1$
			Font.BOLD, 24);
	private final FontUIResource systemFont = new FontUIResource("Dialog", //$NON-NLS-1$
			Font.PLAIN, 24);
	private final FontUIResource windowTitleFont = new FontUIResource("Dialog", //$NON-NLS-1$
			Font.BOLD, 24);
	private final FontUIResource userFont = new FontUIResource("SansSerif", //$NON-NLS-1$
			Font.PLAIN, 24);
	private final FontUIResource smallFont = new FontUIResource("Dialog", //$NON-NLS-1$
			Font.PLAIN, 20);

	public FontUIResource getControlTextFont() {
		return controlFont;
	}

	public FontUIResource getSystemTextFont() {
		return systemFont;
	}

	public FontUIResource getUserTextFont() {
		return userFont;
	}

	public FontUIResource getMenuTextFont() {
		return controlFont;
	}

	public FontUIResource getWindowTitleFont() {
		return windowTitleFont;
	}

	public FontUIResource getSubTextFont() {
		return smallFont;
	}

	public void addCustomEntriesToTable(UIDefaults table) {
		super.addCustomEntriesToTable(table);

		final int internalFrameIconSize = 30;
		table.put(
				Messages.getString("BigContrastMetalTheme.CLOSE_ICON"), MetalIconFactory //$NON-NLS-1$
						.getInternalFrameCloseIcon(internalFrameIconSize));
		table.put(
				Messages.getString("BigContrastMetalTheme.MAXIMIZE_ICON"), MetalIconFactory //$NON-NLS-1$
						.getInternalFrameMaximizeIcon(internalFrameIconSize));
		table.put(
				Messages.getString("BigContrastMetalTheme.ICONIFY_ICON"), MetalIconFactory //$NON-NLS-1$
						.getInternalFrameMinimizeIcon(internalFrameIconSize));
		table.put(
				Messages.getString("BigContrastMetalTheme.MINIMIZE_ICON"), MetalIconFactory //$NON-NLS-1$
						.getInternalFrameAltMaximizeIcon(internalFrameIconSize));

		Border blackLineBorder = new BorderUIResource(new MatteBorder(2, 2, 2,
				2, Color.black));
		Border textBorder = blackLineBorder;

		table.put("ToolTip.border", blackLineBorder); //$NON-NLS-1$
		table.put("TitledBorder.border", blackLineBorder); //$NON-NLS-1$

		table.put("TextField.border", textBorder); //$NON-NLS-1$
		table.put("PasswordField.border", textBorder); //$NON-NLS-1$
		table.put("TextArea.border", textBorder); //$NON-NLS-1$
		table.put("TextPane.font", textBorder); //$NON-NLS-1$

		table.put("ScrollPane.border", blackLineBorder); //$NON-NLS-1$

		table.put("ScrollBar.width", new Integer(25)); //$NON-NLS-1$

	}
}
