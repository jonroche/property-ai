package com.jroche.property.ai;

import static com.jroche.property.ai.Province.CONNAUGHT;
import static com.jroche.property.ai.Province.LEINSTER;
import static com.jroche.property.ai.Province.MUNSTER;
import static com.jroche.property.ai.Province.ULSTER;

public enum County {

	KERRY(MUNSTER), CORK(MUNSTER), LIMERICK(MUNSTER), CLARE(MUNSTER), TIPPERARY(MUNSTER), WATERFORD(MUNSTER), DUBLIN(
			LEINSTER), KILDARE(LEINSTER), MEATH(LEINSTER), WICKLOW(LEINSTER), CARLOW(LEINSTER), KILKENNY(
					LEINSTER), LOUTH(LEINSTER), LAOIS(LEINSTER), OFFALY(LEINSTER), WESTMEATH(LEINSTER), LONGFORD(
							LEINSTER), WEXFORD(LEINSTER), MAYO(CONNAUGHT), GALWAY(CONNAUGHT), ROSCOMMON(
									CONNAUGHT), SLIGO(CONNAUGHT), LEITRIM(
											CONNAUGHT), DONEGAL(ULSTER), MONAGHAN(ULSTER), CAVAN(ULSTER);

	private final Province province;

	private County(final Province province) {
		this.province = province;
	}

	public Province getProvince() {
		return province;
	}

	public static County getCounty(String value) {
		for (County county : values()) {
			if (county.name().equalsIgnoreCase(value)) {
				return county;
			}
		}
		return null;
	}
}
