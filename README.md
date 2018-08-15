### Ejemplo de una integración sencilla entre wicket y hibernate

1. La clase GenerateData tiene un main que crea el esquema e inserta algunos datos.
2. La clase Start levanta un servidor web en el puerto 8080 con la app (clase autogenerada por el archetype de maven)
3. La clase HomePage tiene un formulario con un botón para realizar la búsqueda de magos. Usa AJAX. Usa un compundPropertyModel con un SearchModel como modelo.
4. La clase SearchModel Es el objeto que maneja la lógica de aplicación. Invoca a la Home. Tiene un rudimentario manejo de excepciones que alcanza para el ejemplo. 
5. Hay dos versiones de la Home, una que sirve como mock (con datos random) y otra que está resuelta usando la sesión de Hibernate. Por default usa Hibernate. (MaguitoHome)
6. La clase TransactionalFilter maneja la sesión de hibernate y la transacción. Para eso está configurada que la sesión tenga un contexto de thread en el hibernate.cfg.xml
7. Las clases Maguito e Item son las clases de negocio, se mapean con annotations y tienen una relación de 1 a N. Se maneja con cascade.

 