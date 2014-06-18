/*
 * @(#)MetalworksInBox.java	1.11 05/11/17
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
 * @(#)MetalworksInBox.java	1.11 05/11/17
 */

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.tree.*;

/**
 * This is a subclass of JInternalFrame which displays a tree.
 * 
 * @version 1.11 11/17/05
 * @author Steve Wilson
 */
public class MetalworksInBox extends JInternalFrame {

	public MetalworksInBox() {
		super(
				Messages.getString("MetalworksInBox.IN_BOX"), true, true, true, true); //$NON-NLS-1$

		DefaultMutableTreeNode unread;
		DefaultMutableTreeNode personal;
		DefaultMutableTreeNode business;
		DefaultMutableTreeNode spam;

		DefaultMutableTreeNode top = new DefaultMutableTreeNode(
				Messages.getString("MAIL_BOXES"));// "Mail Boxes"); //$NON-NLS-1$

		top.add(unread = new DefaultMutableTreeNode(Messages
				.getString("UNREAD_MAIL")));// "Unread Mail")); //$NON-NLS-1$
		top.add(personal = new DefaultMutableTreeNode(Messages
				.getString("PERSONAL")));// "Personal")); //$NON-NLS-1$
		top.add(business = new DefaultMutableTreeNode(Messages
				.getString("BUSINESS")));// "Business")); //$NON-NLS-1$
		top.add(spam = new DefaultMutableTreeNode(Messages.getString("SPAM")));// "Spam")); //$NON-NLS-1$

		unread.add(new DefaultMutableTreeNode(Messages
				.getString("BUY_THAT_STUFF")));// "Buy Stuff Now")); //$NON-NLS-1$
		unread.add(new DefaultMutableTreeNode(Messages.getString("READ_ME_NOW")));// "Read Me Now")); //$NON-NLS-1$
		unread.add(new DefaultMutableTreeNode(Messages.getString("HOT_OFFER")));// "Hot Offer")); //$NON-NLS-1$
		unread.add(new DefaultMutableTreeNode(Messages
				.getString("RE_RE_THANK_YOU")));// "Re: Re: Thank You")); //$NON-NLS-1$
		unread.add(new DefaultMutableTreeNode(Messages
				.getString("FWD_GOOD_JOKE")));// "Fwd: Good Joke")); //$NON-NLS-1$

		personal.add(new DefaultMutableTreeNode(Messages.getString("HI")));// "Hi")); //$NON-NLS-1$
		personal.add(new DefaultMutableTreeNode(Messages
				.getString("GOOD_TO_HEAR")));// "Good to hear from you")); //$NON-NLS-1$
		personal.add(new DefaultMutableTreeNode(Messages
				.getString("RE_THANK_YOU")));// "Re: Thank You")); //$NON-NLS-1$

		business.add(new DefaultMutableTreeNode(Messages
				.getString("THANK_ORDER")));// "Thanks for your order")); //$NON-NLS-1$
		business.add(new DefaultMutableTreeNode(Messages
				.getString("PRICE_QUOTE")));// "Price Quote")); //$NON-NLS-1$
		business.add(new DefaultMutableTreeNode(Messages
				.getString("HERE_INVOICE")));// "Here is the invoice")); //$NON-NLS-1$
		business.add(new DefaultMutableTreeNode(Messages
				.getString("PROJECT_METAL_DELIVERED")));// "Project Metal: delivered on time")); //$NON-NLS-1$
		business.add(new DefaultMutableTreeNode(Messages
				.getString("SALARY_RAISE_APPROVED")));// "Your salary raise approved")); //$NON-NLS-1$

		spam.add(new DefaultMutableTreeNode(Messages.getString("BUY_NOW")));// "Buy Now")); //$NON-NLS-1$
		spam.add(new DefaultMutableTreeNode(Messages
				.getString("MAKE_MONEY_NOW")));// "Make $$$ Now")); //$NON-NLS-1$
		spam.add(new DefaultMutableTreeNode(Messages.getString("HOT_HOT_HOT")));// "HOT HOT HOT")); //$NON-NLS-1$
		spam.add(new DefaultMutableTreeNode(Messages.getString("BUY_NOW")));// "Buy Now")); //$NON-NLS-1$
		spam.add(new DefaultMutableTreeNode(Messages.getString("DONT_MISS")));// "Don't Miss This")); //$NON-NLS-1$
		spam.add(new DefaultMutableTreeNode(Messages
				.getString("OPPORTUNITY_IN_PRECIOUS_METAL")));// "Opportunity in Precious Metals")); //$NON-NLS-1$
		spam.add(new DefaultMutableTreeNode(Messages.getString("BUY_NOW")));// "Buy Now")); //$NON-NLS-1$
		spam.add(new DefaultMutableTreeNode(Messages.getString("LAST_CHANCE")));// "Last Chance")); //$NON-NLS-1$
		spam.add(new DefaultMutableTreeNode(Messages.getString("BUY_NOW")));// "Buy Now")); //$NON-NLS-1$
		spam.add(new DefaultMutableTreeNode(Messages
				.getString("MAKE_MONEY_NOW")));// "Make $$$ Now")); //$NON-NLS-1$
		spam.add(new DefaultMutableTreeNode(Messages.getString("HOT_TO_HANDLE")));// "To Hot To Handle")); //$NON-NLS-1$
		spam.add(new DefaultMutableTreeNode(Messages
				.getString("WAITING_FOR_CALL")));// "I'm waiting for your call")); //$NON-NLS-1$

		JTree tree = new JTree(top);
		JScrollPane treeScroller = new JScrollPane(tree);
		treeScroller.setBackground(tree.getBackground());
		setContentPane(treeScroller);
		setSize(325, 200);
		setLocation(75, 75);

	}

}
