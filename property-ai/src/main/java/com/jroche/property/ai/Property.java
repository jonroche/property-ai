package com.jroche.property.ai;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Property {

	private @Id @GeneratedValue Long id;

	private String date;
	private Date dateOfSale;
	private String address;
	private String postCode;
	private County county;
	private int price;
	private boolean fullMarketPrice;
	private boolean vatExclusive;
	private String propertyDescription;
	private String propertySizeDescription;

	private Property() {
	}

	public Property(final String[] parameters) throws ParseException {
		this.date = parameters[0];
		this.dateOfSale = getDate(parameters[0]);
		this.address = parameters[1];
		this.postCode = parameters[2];
		this.county = getCounty(parameters[3]);
		this.price = getPrice(parameters[4]);
		this.fullMarketPrice = getBoolean(parameters[5]);
		this.vatExclusive = getBoolean(parameters[6]);
		this.propertyDescription = parameters[7];
		this.propertySizeDescription = parameters[8];
	}

	public Property(String dateOfSale, String address, String postCode, String county, String price,
			String fullMarketPrice, String vatExclusive, String propertyDescription, String propertySizeDescription)
			throws ParseException {
		this.date = dateOfSale;
		this.dateOfSale = getDate(dateOfSale);
		this.address = address;
		this.postCode = postCode;
		this.county = getCounty(county);
		this.price = getPrice(price);
		this.fullMarketPrice = getBoolean(fullMarketPrice);
		this.vatExclusive = getBoolean(vatExclusive);
		this.propertyDescription = propertyDescription;
		this.propertySizeDescription = propertySizeDescription;
	}

	private boolean getBoolean(String value) {
		return value.toLowerCase().equals("yes");
	}

	private County getCounty(String county) {
		return County.getCounty(county);
	}

	private int getPrice(String price) {
		String p = price.replaceAll(",", "").replaceAll("€", "");
		return Double.valueOf(p).intValue();
	}

	private Date getDate(String dateOfSale) throws ParseException {
		return new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH).parse(dateOfSale);
	}

}
