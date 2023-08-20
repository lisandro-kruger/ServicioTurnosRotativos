# HU 001 CrearEmpleado.
Para dar de alta un empleado se debe ingresar como datos obligatorio: nombre, apellido, email, fecha de nacimiento y fecha de ingreso.
Antes de guardar los datos del empleado se deben validar los siguientes requerimientos: 

1) Nombre, apellido, email, fecha de nacimiento y fecha de ingreso son datos obligatorios si no se completa algunos de estos campos se mostrará un mensaje, por ejemplo: “el campo nombre es obligatorio”.

2) La fecha de ingreso y la fecha de nacimiento no pueden ser posteriores al día de la fecha.

3) El email debe cumplir el formato estándar. Por ejemplo: “*****@gmail.com”.

4) Solo se permiten letras en el campo nombre y apellido, no se pueden ingresar números o caracteres especiales.

5) La edad del empleado no puede ser menor a 18 años.

Una vez validado los siguientes requerimientos anteriores y paso previo a persistir los datos se debe verificar o controlar que un empleado tenga el mismo número de documento o la misma dirección de email ya que si esto ocurre se produce un conflicto y muestra un mensaje informando dicho error, por ejemplo “ya existe un empleado con el mismo número de documento ingresado”.


# HU 002 ObtenerEmpleados
Cuándo se invoca la operación de obtener empleados correctamente, se retorna la lista completa de todos los empleados para consultar sus datos. Sí la lista de empleados registrados es igual a cero se retorna a la lista vacía.


# HU 003 ObtenerInformacionEmpleado
Cuando se invoca es operación de obtener un empleado  correctamente, se retorna la información del empleado correspondiente en el caso de que el ID provisto en la ruta variable, no coincide con el de un empleado existente, el sistema muestra: “ no se encontró el empleado con ID: {idEmpleado}”

# HU 005 Obtener Conceptos Laborales 
Permite obtener la lista de todos los conceptos laborales, cada concepto está compuesto por un nombre, si es o no laborable (true o false), horas_mínimo y horas_máximo.
Si los valores de hora_mínimo y hora_máximo poseen valores nulos, no se incluyen en la lista de conceptos.

# HU 008 Eliminar Empleado
Permite eliminar un empleado previamente dado de alta.
Cuándo se invoca la operación de eliminar empleado correctamente muestro un mensaje por consola “El empleado fue eliminado con éxito” ya que no es posible mostrar el mensaje de otra forma como en la aplicación Postman por el tipo de Status Code que retorna en este caso 204 NO CONTENT.
Cuando se invoca la operación de eliminar empleados y el ID provisto en la ruta variable,  no coincide con el de un empleado existente el sistema muestra: “No se encontró el empleado con ID: {idEmpleado}”