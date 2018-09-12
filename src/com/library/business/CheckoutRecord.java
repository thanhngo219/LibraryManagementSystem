package com.library.business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CheckoutRecord implements Serializable {
	private Integer checkoutId;

	private List<CheckoutEntry> checkoutEntries = new ArrayList<>();

	public Integer getCheckoutId() {
		return checkoutId;
	}

	public void setCheckoutId(Integer checkoutId) {
		this.checkoutId = checkoutId;
	}

	public List<CheckoutEntry> getCheckoutEntries() {
		return checkoutEntries;
	}

	public void setCheckoutEntries(List<CheckoutEntry> checkoutEntries) {
		this.checkoutEntries = checkoutEntries;
	}

	public void addCheckoutEntry(CheckoutEntry checkoutEntry) {
		this.checkoutEntries.add(checkoutEntry);
		checkoutEntry.getBookCopy().setAvailable(false);
	}

}
