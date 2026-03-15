package com.apps.quantitymeasurementapp.quantity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.apps.quantitymeasurement.quantity.Quantity;
import com.apps.quantitymeasurement.units.LengthUnit;

public class LengthConversionTest {

	@Test
	void givenFeet_WhenConvertedToInches_ShouldReturnCorrectValue() {

		Quantity<LengthUnit> feet = new Quantity<>(1, LengthUnit.FEET);

		Quantity<LengthUnit> inches = feet.convertTo(LengthUnit.INCHES);

		assertEquals(12, inches.getValue());
	}
}