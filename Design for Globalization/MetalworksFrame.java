/*
 * @(#)MetalworksFrame.java	1.24 05/11/17
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
 * @(#)MetalworksFrame.java	1.24 05/11/17
 */

import java.awt.*;
import java.io.*;
import java.lang.reflect.Array;
import java.text.Collator;
import java.util.Arrays;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Vector;
import java.awt.event.*;
import java.beans.*;
import javax.swing.*;
import javax.swing.border.*;

import javax.swing.plaf.metal.*;

/**
 * This is the main container frame for the Metalworks demo app
 * 
 * @version 1.24 11/17/05
 * @author Steve Wilson
 */
public class MetalworksFrame extends JFrame {

	JMenuBar menuBar;
	JDesktopPane desktop;
	JInternalFrame toolPalette;
	JCheckBoxMenuItem showToolPaletteMenuItem;
	Vector<JMenu> menus = new Vector<JMenu>();
	Vector<JMenuItem> menu_items = new Vector<JMenuItem>();

	static final Integer DOCLAYER = new Integer(5);
	static final Integer TOOLLAYER = new Integer(6);
	static final Integer HELPLAYER = new Integer(7);

	// static final String ABOUTMSG =
	// "Metalworks \n \nAn application written to show off the Java Look & Feel. \n \nWritten by the JavaSoft Look & Feel Team \n  Michael Albers\n  Tom Santos\n  Jeff Shapiro\n  Steve Wilson";

	public MetalworksFrame() {
		super(Messages.getString("MetalworksFrame.METAL_WORKS")); //$NON-NLS-1$
		final int inset = 50;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(inset, inset, screenSize.width - inset * 2, screenSize.height
				- inset * 2);
		buildContent();
		buildMenus();
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				quit();
			}
		});
		UIManager.addPropertyChangeListener(new UISwitchListener(
				(JComponent) getRootPane()));
	}

	Hashtable hash_table = null;
	Vector themes_objects = null;
	JMenu themeMenu = null;

	protected void buildMenus() {
		menuBar = new JMenuBar();
		menuBar.setOpaque(true);
		JMenu file = buildFileMenu();
		file.setName("FILE_MENU"); //$NON-NLS-1$
		menus.add(file);
		JMenu edit = buildEditMenu();
		edit.setName("EDIT_MENU"); //$NON-NLS-1$
		menus.add(edit);
		JMenu views = buildViewsMenu();
		views.setName("VIEWS_MENU"); //$NON-NLS-1$
		menus.add(views);
		JMenu speed = buildSpeedMenu();
		speed.setName("SPEED_DRAG_MENU"); //$NON-NLS-1$
		menus.add(speed);
		JMenu languages = buildLanguagesMenu();
		languages.setName("LANGUAGES_MENU"); //$NON-NLS-1$
		menus.add(languages);
		JMenu help = buildHelpMenu();
		help.setName("HELP_MENU"); //$NON-NLS-1$
		menus.add(help);

		// load a theme from a text file
		MetalTheme myTheme = null;
		try {
			InputStream istream = getClass().getResourceAsStream(
					Messages.getString("MetalworksFrame.MY_THEME")); //$NON-NLS-1$
			myTheme = new PropertiesMetalTheme(istream);
		} catch (NullPointerException e) {
			System.out.println(e);
		}

		hash_table = new Hashtable();

		hash_table.put(Messages.getString("OCEAN_THEMES"), new OceanThemeEx()); //$NON-NLS-1$
		hash_table.put(Messages.getString("DEFAULT_THEMES"), //$NON-NLS-1$
				new DefaultMetalThemeEx());
		hash_table.put(Messages.getString("GREEN_METAL_THEMES"), //$NON-NLS-1$
				new GreenMetalTheme());
		hash_table.put(Messages.getString("AQUA_THEMES"), new AquaMetalTheme()); //$NON-NLS-1$
		hash_table.put(Messages.getString("KHAKI_SANDSTONE_THEMES"), //$NON-NLS-1$
				new KhakiMetalTheme());
		hash_table.put(Messages.getString("PRESENTATION_DEMO_THEMES"), //$NON-NLS-1$
				new DemoMetalTheme());
		hash_table.put(Messages.getString("CONTRAST_THEMES"), //$NON-NLS-1$
				new ContrastMetalTheme());
		hash_table.put(Messages.getString("LOW_VISION_THEMES"), //$NON-NLS-1$
				new BigContrastMetalTheme());
		hash_table.put(Messages.getString("CHARCOL_THEMES"), myTheme); //$NON-NLS-1$

		themes_objects = new Vector();

		themes_objects.add(Messages.getString("OCEAN_THEMES")); //$NON-NLS-1$
		themes_objects.add(Messages.getString("DEFAULT_THEMES")); //$NON-NLS-1$
		themes_objects.add(Messages.getString("GREEN_METAL_THEMES")); //$NON-NLS-1$
		themes_objects.add(Messages.getString("AQUA_THEMES")); //$NON-NLS-1$
		themes_objects.add(Messages.getString("KHAKI_SANDSTONE_THEMES")); //$NON-NLS-1$
		themes_objects.add(Messages.getString("PRESENTATION_DEMO_THEMES")); //$NON-NLS-1$
		themes_objects.add(Messages.getString("CONTRAST_THEMES")); //$NON-NLS-1$
		themes_objects.add(Messages.getString("LOW_VISION_THEMES")); //$NON-NLS-1$
		themes_objects.add(Messages.getString("CHARCOL_THEMES")); //$NON-NLS-1$

		Collator collator = Collator.getInstance(Locale.getDefault());
		Collections.sort(themes_objects, collator);

		MetalTheme[] themes = new MetalTheme[themes_objects.size()];

		for (int i = 0; i < themes.length; ++i) {
			themes[i] = (MetalTheme) hash_table
					.get(themes_objects.elementAt(i));
		}

		// themes.
		// put the themes in a menu
		themeMenu = new MetalThemeMenu(Messages.getString("THEMES_MENU"), //$NON-NLS-1$
				themes);

		menuBar.add(file);
		menuBar.add(edit);
		menuBar.add(views);
		menuBar.add(themeMenu);
		menuBar.add(speed);
		menuBar.add(languages);
		menuBar.add(help);
		setJMenuBar(menuBar);
	}

	protected JMenu buildFileMenu() {
		JMenu file = new JMenu(Messages.getString("FILE_MENU")); //$NON-NLS-1$
		JMenuItem newWin = new JMenuItem(Messages.getString("NEW_MENU")); //$NON-NLS-1$
		newWin.setName("NEW_MENU"); //$NON-NLS-1$
		menu_items.add(newWin);
		JMenuItem open = new JMenuItem(Messages.getString("OPEN_MENU")); //$NON-NLS-1$
		open.setName("OPEN_MENU"); //$NON-NLS-1$
		menu_items.add(open);
		JMenuItem quit = new JMenuItem(Messages.getString("QUIT_MENU")); //$NON-NLS-1$
		quit.setName("QUIT_MENU"); //$NON-NLS-1$
		menu_items.add(quit);

		newWin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newDocument();
			}
		});

		open.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openDocument();
			}
		});

		quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quit();
			}
		});

		file.add(newWin);
		file.add(open);
		file.addSeparator();
		file.add(quit);
		return file;
	}

	protected JMenu buildLanguagesMenu() {

		JMenu languages = new JMenu(Messages.getString("LANGUAGES_MENU")); //$NON-NLS-1$
		languages.setName("LANGUAGES_MENU"); //$NON-NLS-1$
		menus.add(languages);

		JMenuItem language1 = new JMenuItem(Messages.getString("LANGUAGE_1")); //$NON-NLS-1$
		language1.setName("LANGUAGE_1"); //$NON-NLS-1$
		menu_items.add(language1);

		JMenuItem language2 = new JMenuItem(Messages.getString("LANGUAGE_2")); //$NON-NLS-1$
		language2.setName("LANGUAGE_2"); //$NON-NLS-1$
		menu_items.add(language2);

		language1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chooseLanguage_1();
			}
		});

		language2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chooseLanguage_2();
			}
		});

		languages.add(language1);
		languages.add(language2);
		return languages;

	}

	/*
	 * This method is called when locale/language is changed.
	 * 
	 */
	void loadTheme() {
		//Reload the menus.
		for (int i = 0; i < menus.size(); ++i) {
			menus.elementAt(i).setText(
					Messages.getString(menus.elementAt(i).getName()));
		}

		//reload the sub menus.
		for (int i = 0; i < menu_items.size(); ++i) {
			menu_items.elementAt(i).setText(
					Messages.getString(menu_items.elementAt(i).getName()));
		}

		//Reload the themes listed under Theme menu.
		// load a theme from a text file
		MetalTheme myTheme = null;
		try {
			InputStream istream = getClass().getResourceAsStream(
					Messages.getString("MetalworksFrame.MY_THEME")); //$NON-NLS-1$
			myTheme = new PropertiesMetalTheme(istream);
		} catch (NullPointerException e) {
			System.out.println(e);
		}

		hash_table.clear();
		hash_table.put(Messages.getString("OCEAN_THEMES"), new OceanThemeEx()); //$NON-NLS-1$
		hash_table.put(Messages.getString("DEFAULT_THEMES"), //$NON-NLS-1$
				new DefaultMetalThemeEx());
		hash_table.put(Messages.getString("GREEN_METAL_THEMES"), //$NON-NLS-1$
				new GreenMetalTheme());
		hash_table.put(Messages.getString("AQUA_THEMES"), new AquaMetalTheme()); //$NON-NLS-1$
		hash_table.put(Messages.getString("KHAKI_SANDSTONE_THEMES"), //$NON-NLS-1$
				new KhakiMetalTheme());
		hash_table.put(Messages.getString("PRESENTATION_DEMO_THEMES"), //$NON-NLS-1$
				new DemoMetalTheme());
		hash_table.put(Messages.getString("CONTRAST_THEMES"), //$NON-NLS-1$
				new ContrastMetalTheme());
		hash_table.put(Messages.getString("LOW_VISION_THEMES"), //$NON-NLS-1$
				new BigContrastMetalTheme());
		hash_table.put(Messages.getString("CHARCOL_THEMES"), myTheme); //$NON-NLS-1$

		themes_objects.clear();
		themes_objects.add(Messages.getString("OCEAN_THEMES")); //$NON-NLS-1$
		themes_objects.add(Messages.getString("DEFAULT_THEMES")); //$NON-NLS-1$
		themes_objects.add(Messages.getString("GREEN_METAL_THEMES")); //$NON-NLS-1$
		themes_objects.add(Messages.getString("AQUA_THEMES")); //$NON-NLS-1$
		themes_objects.add(Messages.getString("KHAKI_SANDSTONE_THEMES")); //$NON-NLS-1$
		themes_objects.add(Messages.getString("PRESENTATION_DEMO_THEMES")); //$NON-NLS-1$
		themes_objects.add(Messages.getString("CONTRAST_THEMES")); //$NON-NLS-1$
		themes_objects.add(Messages.getString("LOW_VISION_THEMES")); //$NON-NLS-1$
		themes_objects.add(Messages.getString("CHARCOL_THEMES")); //$NON-NLS-1$

		Collator collator = Collator.getInstance(Locale.getDefault());
		Collections.sort(themes_objects, collator);

		MetalTheme[] themes = new MetalTheme[themes_objects.size()];

		for (int i = 0; i < themes.length; ++i) {
			themes[i] = (MetalTheme) hash_table
					.get(themes_objects.elementAt(i));
		}

		//First remove the old locale's themes menus and then add the new locale's themes menus.
		int index = menuBar.getComponentIndex(themeMenu);
		menuBar.remove(index);
		themeMenu = new MetalThemeMenu(Messages.getString("THEMES_MENU"), //$NON-NLS-1$
				themes);

		menuBar.add(themeMenu, index);

	}

	public void chooseLanguage_1() {
		Locale oldLocale = Locale.getDefault();
		Messages.setDefaultLocale(Enums.ENGLISH);

		if (!oldLocale.equals(Locale.getDefault()))
			loadTheme();
	}

	public void chooseLanguage_2() {
		Locale oldLocale = Locale.getDefault();
		Messages.setDefaultLocale(Enums.FRENCH);
		if (!oldLocale.equals(Locale.getDefault()))
			loadTheme();
	}

	protected JMenu buildEditMenu() {
		JMenu edit = new JMenu(Messages.getString("EDIT_MENU")); //$NON-NLS-1$
		edit.setName("EDIT_MENU"); //$NON-NLS-1$
		menus.add(edit);

		JMenuItem undo = new JMenuItem(Messages.getString("UNDO_MENU")); //$NON-NLS-1$
		undo.setName("UNDO_MENU"); //$NON-NLS-1$
		menu_items.add(undo);
		JMenuItem copy = new JMenuItem(Messages.getString("COPY_MENU")); //$NON-NLS-1$
		copy.setName("COPY_MENU"); //$NON-NLS-1$
		menu_items.add(copy);
		JMenuItem cut = new JMenuItem(Messages.getString("CUT_MENU")); //$NON-NLS-1$
		copy.setName("CUT_MENU"); //$NON-NLS-1$
		menu_items.add(copy);
		JMenuItem paste = new JMenuItem(Messages.getString("PASTE_MENU")); //$NON-NLS-1$
		paste.setName("PASTE_MENU"); //$NON-NLS-1$
		menu_items.add(paste);
		JMenuItem prefs = new JMenuItem(Messages.getString("PREFERENCES_MENU"));// "Preferences..."); //$NON-NLS-1$
		prefs.setName("PREFERENCES_MENU"); //$NON-NLS-1$
		menu_items.add(prefs);

		undo.setEnabled(false);
		copy.setEnabled(false);
		cut.setEnabled(false);
		paste.setEnabled(false);

		prefs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openPrefsWindow();
			}
		});

		edit.add(undo);
		edit.addSeparator();
		edit.add(cut);
		edit.add(copy);
		edit.add(paste);
		edit.addSeparator();
		edit.add(prefs);
		return edit;
	}

	protected JMenu buildViewsMenu() {
		JMenu views = new JMenu(Messages.getString("VIEWS_MENU")); //$NON-NLS-1$
		views.setName("VIEWS_MENU"); //$NON-NLS-1$
		menus.add(views);

		JMenuItem inBox = new JMenuItem(Messages.getString("OPEN_INBOX_MENU"));// "Open In-Box"); //$NON-NLS-1$
		inBox.setName("OPEN_INBOX_MENU"); //$NON-NLS-1$
		menu_items.add(inBox);
		JMenuItem outBox = new JMenuItem(Messages.getString("OPEN_OUTBOX_MENU"));// "Open Out-Box"); //$NON-NLS-1$
		outBox.setName("OPEN_OUTBOX_MENU"); //$NON-NLS-1$
		menu_items.add(outBox);
		outBox.setEnabled(false);

		inBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openInBox();
			}
		});

		views.add(inBox);
		views.add(outBox);
		return views;
	}

	protected JMenu buildSpeedMenu() {

		JMenu speed = new JMenu(Messages.getString("SPEED_DRAG_MENU")); //$NON-NLS-1$
		speed.setName("SPEED_DRAG_MENU"); //$NON-NLS-1$
		menus.add(speed);

		JRadioButtonMenuItem live = new JRadioButtonMenuItem(
				Messages.getString("LIVE_MENU")); //$NON-NLS-1$
		live.setName("LIVE_MENU"); //$NON-NLS-1$
		menu_items.add(live);

		JRadioButtonMenuItem outline = new JRadioButtonMenuItem(
				Messages.getString("OUTLINE_MENU"));// "Outline"); //$NON-NLS-1$
		outline.setName("OUTLINE_MENU"); //$NON-NLS-1$
		menu_items.add(outline);

		JRadioButtonMenuItem slow = new JRadioButtonMenuItem(
				Messages.getString("OLD_AND_SLOW_MENU"));// "Old and Slow"); //$NON-NLS-1$
		slow.setName("OLD_AND_SLOW_MENU"); //$NON-NLS-1$
		menu_items.add(slow);

		ButtonGroup group = new ButtonGroup();

		group.add(live);
		group.add(outline);
		group.add(slow);

		live.setSelected(true);

		slow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// for right now I'm saying if you set the mode
				// to something other than a specified mode
				// it will revert to the old way
				// This is mostly for comparison's sake
				desktop.setDragMode(-1);
			}
		});

		live.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				desktop.setDragMode(JDesktopPane.LIVE_DRAG_MODE);
			}
		});

		outline.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				desktop.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);
			}
		});

		speed.add(live);
		speed.add(outline);
		speed.add(slow);

		return speed;
	}

	protected JMenu buildHelpMenu() {
		JMenu help = new JMenu(Messages.getString("HELP_MENU")); //$NON-NLS-1$
		help.setName("HELP_MENU"); //$NON-NLS-1$
		menus.add(help);

		JMenuItem about = new JMenuItem(Messages.getString("ABOUT_MENU")); //$NON-NLS-1$
		about.setName("ABOUT_MENU"); //$NON-NLS-1$
		menu_items.add(about);

		JMenuItem openHelp = new JMenuItem(Messages.getString("OPEN_HELP_MENU")); //$NON-NLS-1$
		openHelp.setName("OPEN_HELP_MENU"); //$NON-NLS-1$
		menu_items.add(openHelp);

		about.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showAboutBox();
			}
		});

		openHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openHelpWindow();
			}
		});

		help.add(about);
		help.add(openHelp);

		return help;
	}

	protected void buildContent() {
		desktop = new JDesktopPane();
		getContentPane().add(desktop);
	}

	public void quit() {
		System.exit(0);
	}

	public void newDocument() {
		JInternalFrame doc = new MetalworksDocumentFrame();
		desktop.add(doc, DOCLAYER);
		try {
			doc.setVisible(true);
			doc.setSelected(true);
		} catch (java.beans.PropertyVetoException e2) {
		}
	}

	public void openDocument() {

		UIManager.put("FileChooser.lookInLabelText", //$NON-NLS-1$
				Messages.getString("lOOK_In_LABEL_TEXT")); //$NON-NLS-1$
		UIManager.put("FileChooser.filesOfTypeLabelText", //$NON-NLS-1$
				Messages.getString("FILES_OF_TYPE_LABEL_TEXT")); //$NON-NLS-1$
		UIManager.put("FileChooser.fileNameLabelText", //$NON-NLS-1$
				Messages.getString("FILES_NAME_LABEL_TEXT")); //$NON-NLS-1$
		UIManager.put("FileChooser.cancelButtonText", //$NON-NLS-1$
				Messages.getString("CANCEL_BUTTON_TEXT")); //$NON-NLS-1$

		JFileChooser chooser = new JFileChooser();
		chooser.setApproveButtonText(Messages.getString("OK_BUTTON_TEXT")); //$NON-NLS-1$
		chooser.setDialogTitle(Messages.getString("FILE_CHOOSER_TITLE")); //$NON-NLS-1$

		chooser.showOpenDialog(this);
	}

	public void openHelpWindow() {
		JInternalFrame help = new MetalworksHelp();
		desktop.add(help, HELPLAYER);
		try {
			help.setVisible(true);
			help.setSelected(true);
		} catch (java.beans.PropertyVetoException e2) {
		}
	}

	public void showAboutBox() {
		JOptionPane.showMessageDialog(this, Messages.getString("ABOUTMSG")); //$NON-NLS-1$
	}

	public void openPrefsWindow() {
		MetalworksPrefs dialog = new MetalworksPrefs(this);
		dialog.show();

	}

	public void openInBox() {
		JInternalFrame doc = new MetalworksInBox();
		desktop.add(doc, DOCLAYER);
		try {
			doc.setVisible(true);
			doc.setSelected(true);
		} catch (java.beans.PropertyVetoException e2) {
		}
	}
}
