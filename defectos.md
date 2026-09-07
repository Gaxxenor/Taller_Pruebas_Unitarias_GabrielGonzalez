# Reporte de Defectos - Taller Pruebas Unitarias

Al ejecutar las pruebas unitarias y de propiedades sobre el proyecto de la Registraduría, la consola arrojó 4 fallos (BUILD FAILURE) relacionados con la validación de edad en el método `registerVoter` de `Registry.java`.

### Defecto 1: Acepta edades negativas o en cero como válidas
* **Descripción:** Al probar edades inválidas (menores o iguales a 0), el sistema debería retornar `INVALID_AGE`. Sin embargo, devuelve `VALID`.
* **Evidencia en consola:** `AssertionFailedError: expected: <INVALID_AGE> but was: <VALID>`
* **Prueba que falló:** `testInvalidAgeNegativeOrZero` (Properties) y `validateRegistryResultInvalidAgeNegative` (Unit test).

### Defecto 2: Permite el registro de menores de edad
* **Descripción:** Para personas con edades entre 1 y 17 años, la Registraduría no aplica el filtro de mayoría de edad (18 años) y marca a los menores como votantes válidos.
* **Evidencia en consola:** `AssertionFailedError: expected: <INVALID_AGE> but was: <VALID>` (Ejemplo con `arg0: 1`).
* **Prueba que falló:** `testUnderageReturnsInvalidAge` (Properties) y `validateRegistryResultUnderage` (Unit test).

### Conclusión de las Pruebas
Se ejecutaron 8 pruebas en total (4 unitarias y 4 basadas en propiedades) arrojando 4 fallas. El componente presenta fallas críticas en la lógica de control de edad, permitiendo que personas sin la edad legal requerida o con datos incongruentes sean registradas satisfactoriamente.
