# ejercicios
	El ejerecicio SpatialCalendar se compone de 3 modulos maven, implementando Spring Boot  para la estructura REST, y separando en un modulo core la problemàtica concreta.
        Tecnologìa utilizada, Maven, Spring Boot, Junit, REST, H2 JPA, Mongo (solo para ejemplificar convivencia).
	Se han tomado decisiones de criterio, tanto para facilitar la prueba del codigo como los test de manera online.

	Dado que el sistema es estable de no màs de 360 estados, determinados por la velocidad del menor planeta. Se utiliza dicha restricciòn como elemento de optimizaciòn.

	populacion    					->  POST 	/populationJob?dia=X 	0 <= X < MAX_LONG  (Solo se podrà popular datos que no existan en el sistema. Se ha realizado una secuanciacion,simple no escalable)
	eliminacion de datos de la base de datos 	->  DELETE	/system			      (Una vez eliminados, se puede volver a popular)

	consulta de calculo de clima			->  GET		/clima?dia=X		0 <= X < MAX_LONG
	consulta de reprotes segùn los datos generados  ->  GET		/report?dia=X		0 <= X < MAX_LONG ( Los dìas son transformados en años)
