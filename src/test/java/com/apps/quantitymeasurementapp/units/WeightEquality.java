package com.apps.quantitymeasurementapp.units;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.apps.quantitymeasurement.quantity.Quantity;
import com.apps.quantitymeasurement.units.WeightUnit;

public class WeightEquality {

	@Test
	void givenKilogramAndGram_WhenEqual_ShouldReturnTrue() {

		Quantity<WeightUnit> kg = new Quantity<>(1, WeightUnit.KILOGRAM);
		Quantity<WeightUnit> g = new Quantity<>(1000, WeightUnit.GRAM);

		assertTrue(kg.equals(g));
	}
}