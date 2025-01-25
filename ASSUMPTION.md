# Assumptions for Building Controls System

## 1. Room Temperature Updates
- Room temperatures change dynamically based on a random adjustment value derived from a normal distribution. This simulates real-world variability.  
- Minor random fluctuations occur when neither heating nor cooling is enabled, ensuring room temperatures naturally "drift" over time.  
- Heating/cooling adjustments are larger on average than fluctuations to simulate the effect of active systems.

---

## 2. Recalculation on `requestedTemperature` Change
- Heating and cooling states are recalculated periodically (e.g., every 5 seconds) in the background. This creates a system where updates happen continuously rather than immediately reacting to a single change.  
- The periodic recalculations ensure consistent updates without relying on explicit user actions, better simulating a real-world automated system.

---

## 3. Heating/Cooling State Logic
- Heating or cooling is enabled only when the room temperature deviates beyond a defined tolerance range (e.g., ±0.5°C).  
- This prevents systems from overreacting to minor changes, improving energy efficiency.

---

## 4. Temperature Adjustment Dynamics
- Temperature adjustments are dynamic:
  - Heating and cooling adjustments use a normal distribution with a mean of ~0.5°C per tick and a small variance.  
  - Minor fluctuations (when no system is active) are smaller (e.g., ~0.05°C) and centered around no change, ensuring stability.

---

## 5. Room Temperature Clamping
- Room temperatures are clamped to remain close to the `requestedTemperature` (e.g., within ±1.0°C).  
- This ensures that systems work within a bounded range, reflecting practical operational constraints.

---

## 6. Periodic Updates
- Updates happen periodically (every 5 seconds in this implementation), simulating a real-world control system where states are re-evaluated at regular intervals rather than instantly responding to every change.
