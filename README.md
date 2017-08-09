# ejercicios
El ejerecicio SpatialCalendar se compone de 3 modulos maven, implementando Spring Boot  para la estructura REST, y separando en un modulo core la problemàtica concreta.
Tecnologìa utilizada, Maven, Spring Boot, Junit, REST, H2 JPA, Mongo (solo para ejemplificar convivencia), casos de test con JUnit, tanto en el core, como REST:.
Se han tomado decisiones de criterio, tanto para facilitar la prueba del codigo como los test de manera online.

Dado que el sistema es estable de no màs de 360 estados, determinados por la velocidad del menor planeta. Se utiliza dicha restricciòn como elemento de optimizaciòn.

	populacion    					->  POST 	/populationJob?dia=X 	0 <= X < MAX_LONG  (Solo se podrà popular datos que no existan en el sistema. Se ha realizado una secuanciacion,simple no escalable)
	eliminacion de datos de la base de datos 	->  DELETE	/system			      (Una vez eliminados, se puede volver a popular)

	consulta de calculo de clima			->  GET		/clima?dia=X		0 <= X < MAX_LONG
	consulta de reprotes segùn los datos generados  ->  GET		/report?dia=X		0 <= X < MAX_LONG ( Los dìas son transformados en años)
 
Consideraciones funcionales 
      La cantidad de días por año (variable según la velocidad angular) se determinan por la menor velocidad del sistema, permitiendo maximizar la cantidad de estados del mismo.
      La cantidad de estados, una vez establecidos los planetas, es fija, por lo que se considera esa condición en la implementacion.
      Existe un punto de inflexion en el resultado del ejercicio. Si el perìmetro del triangulo se considera, punto interior o no. Se a asumido que si.( Aunque el sistema permite una modificaciòn por configuraciòn de este criterio, para ser modificado require un nuevo deploy, puesto que es una caracterìstica determinante de la cantidad de estados del sistema que una vez especificado correctamente, no volverà a ser modificada y la gestiòn dinàmica de dicha parametrizaciòn era demaciado costosa en relaciòn al tiempo asignbe) 

Consideraciones Tecnicas 
      La solución consta de 3 módulos maven, el módulo "rest" es el que contiene la implementación en spring-boot (entorno embebido). Se compila con (mvn install o ./rest/mvnw spring-boot:run). Se inicializa el sistema embembido con (./rest/mvnw spring-boot:run). (quizás lo prueben en windows y deben usar el mvnw.bat)
       Hay casos de test intensivos en el módulo Core cuyo resultado se espera sea positivo, de investigación que se ha excluido de la verificación automática por mvn puesto que se espera que den resultados negativos para justificar y mantener el aprendizaje asociado al problema.  
      
      Se consideró técnicamente a la solución como una prueba de concepto por lo que solo se buscó optimizar la relación de estados del sistema (que se calculan dinámicamente según la relación de la velocidad angular más lenta). Se implementaron 2 bases de datos en simultaneo, H2 con JPA y MongoDB, solo para establecer su presencia en simultaneo y a modo demostrativo. Solo se almacenan los estados calculados para simplificación y cacheo.

En el caso que se hubiera esperado que el job sea un proceso batch por linea de comando, se tomó la decision que utilizar un request REST para el mismo.
