package com.apps.quantitymeasurementapp.quantity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.apps.quantitymeasurement.quantity.Quantity;
import com.apps.quantitymeasurement.units.LengthUnit;

public class LengthAdditionEquality {

	@Test
	void givenLength_WhenAdded_ShouldReturnCorrectResult() {

		Quantity<LengthUnit> q1 = new Quantity<>(5, LengthUnit.FEET);
		Quantity<LengthUnit> q2 = new Quantity<>(24, LengthUnit.INCHES);

		Quantity<LengthUnit> result = q1.add(q2);

		assertEquals(7, result.getValue());
	}
}