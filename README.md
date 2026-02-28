# Quantity Measurement App – UC1 (Feet Equality)

### 📌 Overview

- This module checks whether two measurements given in feet are equal.
- It focuses on correct `object equality`, `safe floating-point comparison`, and clean OOP design.

### ⚙️ Use Case: UC1 – Feet Measurement Equality

- Accepts two numerical values in feet
- Compares them for equality
- Returns `true` if equal, otherwise false

### ⚙️ Key Implementation Points

- Uses a separate Feet class to represent a measurement
- Measurement value is `private` and `final` (immutable)
- `equals()` is overridden correctly
- `Double.compare()` is used instead of `==`
- Handles `null`, type mismatch, and same reference cases safely

---

# Quantity Measurement App – UC2 (Inches Equality)

### 📌 Overview

- This module checks whether two measurements given in **inches** are equal.
- It extends UC1 by supporting equality checks for inches while following the same design principles.

### ⚙️ Use Case: UC2 – Inches Measurement Equality

- Accepts two numerical values in inches
- Compares them for equality
- Returns `true` if equal, otherwise false

### ⚙️ Key Implementation Points

- Uses a separate **Inches** class to represent a measurement
- Measurement value is `private` and `final` (immutable)
- `equals()` is overridden correctly
- `Double.compare()` is used instead of `==`
- Handles `null`, type mismatch, and same reference cases safely

---

# Quantity Measurement App – UC3 (Generic Quantity Length)

### 📌 Overview

- This module refactors Feet and Inches into a **single generic Length class**.
- It eliminates code duplication by applying the **DRY (Don’t Repeat Yourself) principle**.
- Supports equality comparison **across different units** (feet ↔ inches).


### ⚙️ Use Case: UC3 – Generic Quantity Length Equality

- Accepts two numerical values along with their respective unit types
- Converts different units to a **common base unit**
- Compares values for equality
- Returns `true` if equivalent, otherwise false


### ⚙️ Key Implementation Points

- Uses a **single Length class** to represent all length measurements
- Introduces a `LengthUnit` **enum** for supported units and conversion factors
- Eliminates separate Feet and Inches classes
- Conversion logic is centralised and reusable
- Measurement value and unit are **encapsulated**
- `equals()` is overridden for **cross-unit value-based equality**
- Uses safe floating-point comparison
- Handles:

  - `null` values
  - invalid units
  - same reference checks
  - type mismatch safely
  
---

# Quantity Measurement App – UC4 (Extended Unit Support)

### 📌 Overview
 
- This module extends the generic Length class introduced in UC3 by adding support for Yards and Centimeters.
- It demonstrates how a well-designed generic solution scales to new units without code duplication.
- Supports equality comparison across `feet ↔ inches ↔ yards ↔ centimeters`.

### ⚙️ Use Case: UC4 – Extended Quantity Length Equality

- Accepts two numerical values along with their respective unit types
- Supports additional units: `YARDS` and `CENTIMETERS`
- Converts different units to a common base unit
- Compares values for equality
- Returns `true` if equivalent, otherwise `false`

### ⚙️ Key Implementation Points

- Continues using the single generic Length class
- Extends the existing LengthUnit enum with:
- YARDS `(1 yard = 3 feet)`
- CENTIMETERS `(1 cm = 0.393701 inches)`
- No changes required in Length class logic
- Conversion logic remains centralised in the enum
- Measurement value and unit stay encapsulated
- `equals()` supports cross-unit comparisons seamlessly
- Uses safe `floating-point comparison`

---

# Quantity Measurement App – UC5 (Unit-to-Unit Conversion)

### 📌 Overview

- This module extends UC4 by adding `explicit unit-to-unit conversion support` to the Quantity Measurement App.
- Instead of only `checking equality`, the `Length API` now allows `converting a measurement` from one unit to another using centralised conversion factors.
- Supports conversion across `feet ↔ inches ↔ yards ↔ centimeters`.

### ⚙️ Use Case: UC5 – Unit-to-Unit Conversion (Same Measurement Type)

- Accepts a numerical value along with a `source unit` and a `target unit`
- Supports conversion between all supported length units
- Normalises values using a `common base unit`
- Converts the normalised value into the target unit
- Returns the converted numeric value

### ⚙️ Key Implementation Points

- Continues using the same immutable `Length` class
- Reuses the existing `LengthUnit` enum with predefined conversion factors
- Conversion logic is centralised and consistent
- Supports both:
    - Static conversion using raw values
    - Instance-level conversion using `convertTo()`
- Validation ensures:
    - Units are `non-null` and valid
    - Values are finite (not NaN or infinite)
- Conversion preserves mathematical accuracy within `floating-point` tolerance
- No mutation of existing objects; conversions return new values or instances

---

# Quantity Measurement App – UC6 (Addition of Two Length Units)

### 📌 Overview

- This module enables addition operations between two length measurements.
- It supports adding lengths in the same or different units (within the length category) and returns the result in the unit of the first operand.
- For example, adding `1 foot` and `12 inches` yields `2 feet`.

### ⚙️ Use Case: UC6 – Addition of Two Length Units (potentially different units)

- Accepts two numerical values with their respective units.
- Adds them and returns the sum in the unit of the `first operand`.

### ⚙️ Key Concepts Learned

- Addition of value objects with unit conversion.
- `Immutability` and safe handling of operands.
- Normalisation to a base unit for accurate arithmetic.
- `Floating-point precision` management.
- Commutativity and identity element behaviour.
- Robust validation for null or invalid inputs.

---

# Quantity Measurement App – UC7 (Addition with Target Unit Specification)

### 📌 Overview

- This module extends UC6 by allowing the caller to explicitly specify a `target unit` for addition results.
- Instead of defaulting to the first operand’s unit, the result can be returned in any supported unit.
- Example: `1 foot` + `12 inches` with target unit `YARDS ≈ 0.667 yards`.

### ⚙️ Use Case: UC7 – Addition with Target Unit Specification

- Accepts two numerical values with their respective units and a target unit.
- Adds them and returns the sum in the `explicitly specified target unit`.

### ⚙️ Key Implementation Points (UC7 – Explicit Target Unit Addition)

- Uses the same `immutable Length class` and LengthUnit enum.
- Overloaded `add()` method:
   - UC6: `add(A, B)` → result in the first operand’s unit.
   - UC7: `add(A, B, targetUnit)` → result in explicitly specified unit.
- Private utility method handles `conversion → addition → target` unit conversion.
- Validation added: target unit must be non-null and valid.
- Preserves immutability, precision, and commutativity.
- Maintains backward compatibility with the UC6 addition.

---

# Quantity Measurement App – UC8 (Standalone LengthUnit Refactoring)

### 📌 Overview

- This module refactors the `LengthUnit enum` to a `standalone`, `top-level class` with full responsibility for unit conversions.
- QuantityLength is simplified to focus on value comparison and arithmetic, delegating all conversion logic to LengthUnit.
- The change improves cohesion, eliminates circular dependencies, and establishes a scalable pattern for `multiple measurement categories`.

### ⚙️ Use Case: UC8 – Refactoring Unit Enum to Standalone with Conversion Responsibility

- `LengthUnit` manages all conversion logic (to/from base unit).
- `QuantityLength` handles equality, addition, and arithmetic only.
- Supports all functionality from UC1–UC7 without modifying client code.

### ⚙️ Key Implementation Points

- LengthUnit handles all unit conversion logic.
- `QuantityLength` delegates conversions → focuses on comparisons/addition.
- Methods:
   - `convertToBaseUnit`(double value)
   - `convertFromBaseUnit`(double baseValue)
- Preserves immutability, precision, and commutativity.
- `Public API` unchanged → `backward compatibility`.
- Establishes a scalable design pattern for other measurement categories.

---

# Quantity Measurement App – UC9 (Weight Equality, Conversion, and Addition)

### 📌 Overview

- This module extends the Quantity Measurement App to support `weight measurements` (kilogram, gram, pound).
- It focuses on correct object equality, unit conversion, addition operations, and safe floating-point comparisons while maintaining immutability and type safety.

### ⚙️ Use Case: UC9 – Weight Measurement Equality, Conversion, and Addition

- Accepts two or more numerical values with `weight units` (kg, g, lb)
- Compares weights for equality
- Converts weights between units
- Adds two weight measurements and returns a new object

### ⚙️ Key Implementation Points

- Uses a **WeightUnit enum** for conversion responsibility (base unit: kilogram)
- Uses a **QuantityWeight class** to represent weight measurements
- Measurement value and unit are **private and final** (immutable)
- `equals()` is overridden to handle cross-unit comparisons
- `convertTo()` method normalises via the base unit
- `add()` methods support implicit (default) and explicit target unit addition
- **Double.compare()** ensures safe floating-point comparison
- Handles null, type mismatch, same reference, and category incompatibility safely
- Weight and length measurements are **distinct categories** and cannot be compared

---

# Quantity Measurement App – UC10 (Generic Quantity Class with Unit Interface)

### 📌 Overview

- This module refactors the previous category-specific Quantity classes into a single, generic `Quantity<U>` class that works with any measurement category implementing the `IMeasurable` interface.
- It eliminates code duplication, simplifies demonstration methods, and ensures type-safe operations across multiple measurement categories like length and weight.

### ⚙️ Use Case: UC10 – Generic Quantity and Multi-Category Support

- Accepts two numerical values with their respective units
- Supports equality comparison, unit conversion, and addition
- Prevents invalid cross-category comparisons (e.g., length vs. weight)
- Returns a new `Quantity` object for conversion or addition; equality returns a boolean

### ⚙️ Key Implementation Points

- Uses a single generic class: `Quantity<U extends IMeasurable>`
- Holds private final fields: `value` and `unit` (immutable)
- `IMeasurable` interface standardises unit behaviour across categories
- Enums (`LengthUnit`, `WeightUnit`) implement `IMeasurable` and encapsulate conversion logic
- `equals()` compares base unit values using `Double.compare()` and validates unit types
- `convertTo(U targetUnit)` delegates to the unit’s conversion methods and returns new instance
- `add(Quantity<U> other)` and `add(Quantity<U> other, U targetUnit)` perform arithmetic safely
- `hashCode()` and `toString()` overridden for collections and readable output
- Type safety ensured at compile-time via generics; runtime unit class checks prevent cross-category errors
- Demonstration methods in `QuantityMeasurementApp` are generic and unified for all categories

---

# Quantity Measurement App – UC11 (Volume Equality, Conversion, and Addition)

### 📌 Overview

- This module extends the Quantity Measurement Application to support **volume measurements** (litres, millilitres, gallons).
- It demonstrates equality comparison, unit conversion, and addition operations for volume, leveraging the generic `Quantity<U>` class and `IMeasurable` interface. - Volume is treated as a separate category from length and weight, validating the scalability of the generic architecture.

### ⚙️ Use Case:  UC11 – Volume Measurement Equality, Conversion, and Addition

- Accepts numerical values with their respective volume units (LITRE, MILLILITRE, GALLON)
- Compares volumes for equality
- Converts between volume units
- Adds two volume quantities, optionally specifying a target unit

### ⚙️ Key Implementation Points

- `VolumeUnit` enum implements `IMeasurable` with LITRE as the base unit
- Conversion factors: MILLILITRE = 0.001 L, GALLON ≈ 3.78541 L
- Equality uses base unit comparison with epsilon tolerance
- Generic `Quantity<U>` handles conversion and addition without modification
- Maintains type safety: volume cannot be mixed with length or weight
- Objects are immutable; addition and conversion return new instances

---

# Quantity Measurement App - UC12 (Subtraction and Division Operations on Quantity Measurements)

### 📌 Overview

- UC12 extends the Quantity Measurement Application by `adding subtraction` and `division operations` to the `generic Quantity<U> model`.
- It builds on `UC1–UC11` and enables full arithmetic manipulation while preserving immutability, type safety, and cross-unit support.

### ⚙️ Use Case: UC12 – Quantity Subtraction & Division

- Subtract two quantities of the same measurement category
- Divide two quantities to obtain a dimensionless ratio
- Support `cross-unit` arithmetic (e.g., feet ↔ inches, litre ↔ millilitre)
- Prevent `cross-category` operations (e.g., length vs weight)

### ⚙️Key Implementation Points

 - Convert operands to base unit before arithmetic
- Validate:
    - Null operands
    - Same measurement category
    - Finite numeric values
    - Division by zero
- Implicit target unit → first operand’s unit
- Explicit target unit supported
- Results rounded to two decimal places (subtraction only)

---

# Quantity Measurement App - UC13 (Centralised Arithmetic Logic to Enforce DRY in Quantity Operations)

### 📌 Overview

- UC13 refactors the arithmetic operations introduced in UC12 by centralising all shared validation, unit conversion, and base-unit arithmetic logic into private helper methods.
- This refactoring enforces the DRY (Don’t Repeat Yourself) principle, reduces code duplication, and improves maintainability, while keeping all public APIs and behaviours unchanged.

### ⚙️ Use Case: UC13 Centralised Arithmetic Logic

- Eliminate repeated logic across the add, subtract, and divide methods
- Ensure consistent validation and error handling for all arithmetic operations
- Improve readability and maintainability of arithmetic logic
- Provide a scalable foundation for future operations (multiply, modulo, etc.)
- Preserve all UC12 behaviour and existing test cases

### ⚙️ Key Implementation Points (Brief)

- Centralised validation logic in one private helper method.
- Single helper for base-unit conversion and arithmetic.
- `ArithmeticOperation` enum (ADD, SUBTRACT, DIVIDE) encapsulates operation logic.
- `add`, `subtract`, `divide` delegate to shared helpers.
- Implicit and explicit target unit behaviour preserved.
- Public APIs unchanged; UC12 tests pass as-is.
- DRY enforced, cleaner code, easier future extension.

---

# Quantity Measurement App - UC14 (Temperature Measurement with Selective Arithmetic Support)

### 📌 Overview

- UC14 extends the Quantity Measurement Application to support temperature measurements while respecting real-world arithmetic constraints.
- Unlike length, weight, and volume, temperature does not support full arithmetic.
- This use case refactors the IMeasurable interface to make arithmetic optional, enabling temperature units to support only equality and conversion while rejecting unsupported operations with clear errors.

### ⚙️ Use Case: UC14 (Temperature Measurement)

- Introduces **temperature measurement support** with unit conversion and equality
- Restricts **unsupported arithmetic operations** on temperature with clear validation
- Refactors `IMeasurable` to allow **selective operation support** while keeping existing units unchanged

### ⚙️ UC14 – Key Implementation Points

* Introduced `TemperatureUnit` (Celsius, Fahrenheit, Kelvin) with non-linear conversion formulas.
* Refactored `IMeasurable` to add `default methods` for optional arithmetic support.
* Added **SupportsArithmetic** functional interface with lambda-based capability flags.
* Non-temperature units inherit default arithmetic support (**backwards compatible**).
* Temperature explicitly **disables arithmetic** (add, subtract, divide) via overrides.
* `Quantity` validates `operation support upfront` before performing arithmetic.
* Equality and conversion work uniformly via **base-unit normalisation**.
* Cross-category comparisons remain `prohibited and type-safe`.
* Unsupported operations fail fast with **clear UnsupportedOperationException** messages.
* All **UC1–UC13 tests pass unchanged**, ensuring non-breaking evolution.

---