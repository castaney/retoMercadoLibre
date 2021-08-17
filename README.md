
# Proyecto mutante
Magneto quiere reclutar la mayor cantidad de mutantes para poder luchar contra los X-Men.

Te ha contratado a ti para que desarrolles un proyecto que detecte si un humano es mutante basándose en su secuencia de ADN.

Para eso te ha pedido crear un programa con un método o función con la siguiente firma (En alguno de los siguiente lenguajes: Java / Golang / C-C++ / Javascript (node) / Python / Ruby):
```
boolean isMutant(String[] dna); // Ejemplo Java
```

En donde recibirás como parámetro un array de Strings que representan cada fila de una tabla de (NxN) con la secuencia del ADN. Las letras de los Strings solo pueden ser (A,T,C,G), las cuales representa cada base nitrogenada del ADN.

![Gráfico](https://github.com/castaney/retoMercadoLibre/blob/main/2021-08-16_143317.png?raw=true)

Sabrás si un humano es mutante, si encuentras **más de una secuencia de cuatro letras iguales,** de forma oblicua, horizontal o vertical.

**Ejemplo (Caso mutante)**:
```
String[] dna = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
```

En este caso el llamado a la función isMutant(dna) devuelve “true”.

Desarrolla el algoritmo de la manera más eficiente posible.


# Tecnologías a usar
- Versión Java: 11
- Versión Maven: 4.0.0
- Versión Spring Boot: 2.5.3
- Versión Jacoco: 0.8.5
- Motor de BD: MySQL
- Google App Engine


# Objetivos

**Nivel 1**:
<br>Programa (en cualquier lenguaje de programación) que cumpla con el método pedido por Magneto.

**Nivel 2**:
<br>Crear una API REST, hostear esa API en un cloud computing libre (Google App Engine, Amazon AWS, etc), crear el servicio “/mutant/” en donde se pueda detectar si un humano es mutante enviando la secuencia de ADN mediante un HTTP POST con un Json el cual tenga el siguiente formato:

```
POST → /mutant/
{
“dna”:["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]
}
```
En caso de verificar un mutante, debería devolver un HTTP 200-OK, en caso contrario un 403-Forbidden.

**Nivel 3**:
<br>Anexar una base de datos, la cual guarde los ADN’s verificados con la API.
Solo 1 registro por ADN.
Exponer un servicio extra “/stats” que devuelva un Json con las estadísticas de las verificaciones de ADN: {“count_mutant_dna”:40, “count_human_dna”:100: “ratio”:0.4}

Tener en cuenta que la API puede recibir fluctuaciones agresivas de tráfico (Entre 100 y 1 millón de peticiones por segundo).

Test-Automáticos, Code coverage > 80%.


# Entregables
- Código Fuente (Para Nivel 2 y 3: En repositorio github).
- Instrucciones de cómo ejecutar el programa o la API. (Para Nivel 2 y 3: En README de github).
- URL de la API (Nivel 2 y 3).


# Instrucciones ejecución API REST

**Servicio mutant**
<br>Para ejecutar el servicio `/mutant` se debe usar el siguiente endpoint en Postman u otra herramienta:
<br>https://mercadolibreproject-323120.rj.r.appspot.com/mutant

En el body se debe usar un JSON como el siguiente:
```
["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]
```


**Servicio stats**
<br>Para ejecutar el servicio `/stats` se debe usar el siguiente endpoint en Postman u otra herramienta:
<br>https://mercadolibreproject-323120.rj.r.appspot.com/mutant/stats

Al ejecutar este servicio se obtendrá la información de la estadística de los ADNs verificados en un JSON como el siguiente:
```
{“countMutantDna”:40, “countHumanDna”:100: “ratio”:0.4}
```


# Cobertura
La siguiente imagen es el resultado de la cobertura de las pruebas unitarias utilizando la herramienta Jacoco:

![Cobertura](https://github.com/castaney/retoMercadoLibre/blob/main/image_2021_08_17T02_53_55_539Z.png?raw=true)

Para mayor detalle, ver los archivos generados en el siguiente enlace:
<br>https://github.com/castaney/retoMercadoLibre/tree/main/jacoco


# Arquitectura
La **arquitectura hexagonal** permite que una aplicación sea usada de la misma forma por usuarios, programas, pruebas automatizadas o batch scripts, y sea desarrollada y probada en aislamiento (isolated) de sus eventuales dispositivos y bases de datos en tiempo de ejecución.

Permite crear una aplicación para que funcione sin una interfaz de usuario o una base de datos de tal forma que pueda ejecutar pruebas de regresión automatizadas, trabajar aún cuando la base de datos no esté disponible y conectar aplicaciones sin la intervención del usuario.

Todo evento externo (i.e. como pedidos http) que llega a la aplicación por un puerto, es convertido, a través de un adaptador específico a la tecnología del evento externo, en una llamada a un procedimiento o un mensaje entendible por la aplicación. (Por ello) la aplicación es “felizmente” ignorante de la naturaleza de los dispositivos de entrada. Cuando la aplicación tiene algo que enviar, lo hace por un puerto a un adaptador, el cual crea las señales apropiadas, necesarias por la tecnología receptora (humana o automatizada). La aplicación tiene una interacción semántica con los adaptadores de todos sus lados, sin saber realmente la naturaleza de las cosas del otro lado de los adaptadores.

![Arquitectura hexagonal](https://github.com/castaney/retoMercadoLibre/blob/main/2021-08-16_221515.png?raw=true)

El hexágono tiene el propósito de resaltar visualmente:
<br>**(a)** La asimetría interna-externa y la naturaleza similar de los puertos, con el fin de alejarse de la imagen unidimensional de las capas y todo lo que ella evoca, y
<br>**(b)** la presencia de un número definido de diferentes puertos— 2, 3 o 4.

**Nota:**
<br>*La información sobre la arquitectura hexagonal y la imagen utilizada, para efectos de derechos de autor, fueron tomadas de la siguiente publicación:* 
<br>https://our-academy.org/posts/arquitectura-hexagonal

Para obtener más información sobre este tipo de arquitectura se recomienda consultar el siguiente enlace:
<br>https://codely.tv/blog/screencasts/arquitectura-hexagonal-ddd/


# Estructura del proyecto
Se tienen tres paquetes principales, un paquete que representa la infraestructura, otro paquete que representa la aplicación y otro que representa el modelo, donde cada capa
tiene sus correspondientes puertos o interfaces representadas a través de buses y eventos, y las interfaces propias de Spring JPA.

**Importar el proyecto**:
<br>Para importar el proyecto, se recomienda usar Maven desde la raíz del proyecto llamado proyectoMutante.

![Importar proyecto](https://github.com/castaney/retoMercadoLibre/blob/main/image_2021_08_17T03_35_21_181Z.png?raw=true)

**Pruebas unitarias**
<br>Para las pruebas unitarias se utilizó **Junit** y una base de datos embebida llamada **h2database** y **Mockito**.
