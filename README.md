
![tearnlogo](https://i.ibb.co/n6MG2v6/tearn-1080x640-1.png)
## Tearn: Teach and learn.

**Tearn: Teach & learn** es una aplicación gratuita desarrollada para dispositivos android y tablets.

 Tearn es una aplicación que nace de la necesidad de tutorías académicas especializadas de una manera más directa y cómoda en modalidades virtuales y presenciales.

Además, Tearn cuenta con una amplia selección de categorías, temas y cursos para las diferentes necesidades de los usuarios.
## Contenido del manual técnico

- [Lanzamientos](#id1)
	- [Descargar](#id2)
	- [Requerimientos técnicos](#id3)
	- [Api](#id4)
-  [Aspectos Generales](#id5)
	- [Objetivos](#id6)
	- [Software utilizado](#id7)
-  [Modelo utilizado](#id8)
- [Prototipos de la aplicación](#id9)
- [Preguntas y respuestas](#id10)
- [Colaboradores](#id11)
- [Usuarios de prueba](#id12)
- [Enlace del video de presentación](#id13)
- [Enlace de ClickUp](#id14)
- [Licencia de código](#id15)

## Lanzamientos<a name="id1"></a>

### Descargar<a name="id2"></a>
Para descargar Tearn, haz click en el siguiente enlace para obtener el paquete instalador: [Tearn: Teach and Learn APK](./Resources/Tearn-1.0.0.apk?raw=true)

### Requerimientos técnicos<a name="id3"></a>
Es importante tener en cuenta las especificaciones mínimas de hardware y software para la correcta instalación y ejecución de la aplicación.
- Requerimientos de hardware 
	- Procesador: Quad core o superior.
	- Memoria RAM: 2gb o superior 1.2. 
- Requerimientos de software:
	-  Sistema Operativo: Android 5.0 o superior

### Api<a name="id4"></a>
La api que desarrollamos para poder realizar Tearn, se realizo con Mongo Atlas, un sistemas de base de datos orientados a documentos y códigos abiertos; además se utilizo el estilo arquitectónico RESTful api.

Para utilizar nuestra api, que se encuentra alojada de manera remota en [Heroku con este dominio](https://tearn.herokuapp.com/api/v1/)

- **.env**
Para el funcionamiento local de la API, hacer uso de las siguientes variables de entorno:
```javascript
MONGO_URI=mongodb+srv://tearnbackend:vPhn1oDoR2geuf44@cluster0.34it1.mongodb.net/Tearn_Rest?retryWrites=true&w=majority

MONGO_URI_TEST=mongodb+srv://tearnbackend:vPhn1oDoR2geuf44@cluster0.34it1.mongodb.net/tearn-test?retryWrites=true&w=majority

BASE_URL=https://tearn.herokuapp.com/api/v1/

ROOT_URL=https://tearn.herokuapp.com/

BASE_IMG=https://tearn.herokuapp.com/resources/images/

PORT=3001

URL_CATEGORIES=https://tearn.herokuapp.com/api/v1/category

URL_SUBJECTS=https://tearn.herokuapp.com/api/v1/subject

URL_COURSES=https://tearn.herokuapp.com/api/v1/course

URL_HOME=https://tearn.herokuapp.com/api/v1/home

URL_SEARCH=https://tearn.herokuapp.com/api/v1/search?pattern=

TOKEN_KEY=dfsalklnñlaksdfjnas
```
-**Rutas dentro de la aplicación**
## PETICION PARA CATEGORIAS

#### Url base: https://tearn.herokuapp.com/api/v1/category

  

## POST

Campo | Descripcion

------|------------

**name** | Nombre de la categoría

**description** | Breve descripción de la categoría

**imgUrl** | Imagen local

  

### Ejemplo

```javascript

{

"name": "Ciencias jurídicas",

"description": "Aplicación constante del estudio del ordenamiento jurídico y su aplicación en la sociedad",

"imgUrl": "https://tearn.herokuapp.com/resources/images/Discussion_Class.png"

}

```

#### Respuesta

  

```javascript

{

"error": false,

"message": "Category was created"

}

```

  

## GET

## Obtener todas las categorias

####  Esta petición no necesita parametros

### Respuesta

```javascript

{

"error": false,

"count": 6,

"results": [

{

"id":  "60e633e40272da001588f3f2",

"name":  "Humanidades",

"imgUrl":  "https://tearn.herokuapp.com/resources/images/Library.png",

"url":  "https://tearn.herokuapp.com/api/v1/category/60e633e40272da001588f3f2"

},

{

"id":  "60e634e40272da001588f3f3",

"name":  "Ingeniería y arquitectura",

"imgUrl":  "https://tearn.herokuapp.com/resources/images/Square_root.png",

"url":  "https://tearn.herokuapp.com/api/v1/category/60e634e40272da001588f3f3"

},

{

"id":  "60e635b70272da001588f3f4",

"name":  "Bellas artes",

"imgUrl":  "https://tearn.herokuapp.com/resources/images/Theatre.png",

"url":  "https://tearn.herokuapp.com/api/v1/category/60e635b70272da001588f3f4"

},

{

"id":  "60e636a60272da001588f3f5",

"name":  "Ciencias de la salud",

"imgUrl":  "https://tearn.herokuapp.com/resources/images/Biology.png",

"url":  "https://tearn.herokuapp.com/api/v1/category/60e636a60272da001588f3f5"

},

{

"id":  "60e639bd0272da001588f3f6",

"name":  "Contabilidad y finanzas",

"imgUrl":  "https://tearn.herokuapp.com/resources/images/Mommentum.png",

"url":  "https://tearn.herokuapp.com/api/v1/category/60e639bd0272da001588f3f6"

},

{

"id":  "60e639c20272da001588f3f7",

"name":  "Ciencias jurídicas",

"imgUrl":  "https://tearn.herokuapp.com/resources/images/Discussion_Class.png",

"url":  "https://tearn.herokuapp.com/api/v1/category/60e639c20272da001588f3f7"

}

]

}

```

  

## GET

## Obtener una categoría

  

Params | Descripción

------|------------

**id** | Id de la categoría

  

### Ejemplo

### nueva url: https://tearn.herokuapp.com/api/v1/category/60e636a60272da001588f3f5

### Respuesta

```javascript

{

"error": false,

"id": "60e636a60272da001588f3f5",

"name": "Ciencias de la salud",

"url": "https://tearn.herokuapp.com/api/v1/category/60e636a60272da001588f3f5",

"imgUrl": "https://tearn.herokuapp.com/resources/images/Biology.png",

"description": "Disciplinas relacionadas con la protección, el fomento y la restauración de la salud.",

"subjectCount": 4,

"subjects": [

{

"courses": [

{

"_id":  "60e646780272da001588f433",

"name":  "Anatomía II",

"url":  "https://tearn.herokuapp.com/api/v1/course/60e646780272da001588f433"

},

{

"_id":  "60e646700272da001588f432",

"name":  "Anatomía I",

"url":  "https://tearn.herokuapp.com/api/v1/course/60e646700272da001588f432"

}

],

"_id":  "60e63d870272da001588f405",

"name":  "Anatomía",

"url":  "https://tearn.herokuapp.com/api/v1/subject/60e63d870272da001588f405"

},

{

"courses": [

{

"_id":  "60e646f70272da001588f435",

"name":  "Fisiología II",

"url":  "https://tearn.herokuapp.com/api/v1/course/60e646f70272da001588f435"

},

{

"_id":  "60e646f10272da001588f434",

"name":  "Fisiología I",

"url":  "https://tearn.herokuapp.com/api/v1/course/60e646f10272da001588f434"

}

],

"_id":  "60e63da50272da001588f406",

"name":  "Fisioloía",

"url":  "https://tearn.herokuapp.com/api/v1/subject/60e63da50272da001588f406"

},

{

"courses": [

{

"_id":  "60e6474d0272da001588f438",

"name":  "Patología III",

"url":  "https://tearn.herokuapp.com/api/v1/course/60e6474d0272da001588f438"

},

{

"_id":  "60e647460272da001588f437",

"name":  "Patología II",

"url":  "https://tearn.herokuapp.com/api/v1/course/60e647460272da001588f437"

},

{

"_id":  "60e6473f0272da001588f436",

"name":  "Patología I",

"url":  "https://tearn.herokuapp.com/api/v1/course/60e6473f0272da001588f436"

}

],

"_id":  "60e63db90272da001588f407",

"name":  "Patología",

"url":  "https://tearn.herokuapp.com/api/v1/subject/60e63db90272da001588f407"

},

{

"courses": [

{

"_id":  "60e6479c0272da001588f43a",

"name":  "Estomatología II",

"url":  "https://tearn.herokuapp.com/api/v1/course/60e6479c0272da001588f43a"

},

{

"_id":  "60e647970272da001588f439",

"name":  "Estomatología I",

"url":  "https://tearn.herokuapp.com/api/v1/course/60e647970272da001588f439"

}

],

"_id":  "60e63e090272da001588f408",

"name":  "Estomatología",

"url":  "https://tearn.herokuapp.com/api/v1/subject/60e63e090272da001588f408"

}

]

}

```

## SUBJECT URL

#### Url: https://tearn.herokuapp.com/api/v1/subject

  

## POST

Field | Description

------|------------

name | nombre de la materia

categoryId | id de la categoría a la que pertenece

  

### Ejemplo

```javascript

{

"name": "Derecho mercantil",

"categoryId": "60e639c20272da001588f3f7"

}

```

### Respuesta

  

```javascript

{

"error": false,

"message": "Subject was created"

}

```

  
  

## GET ALL SUBJECT

  

### Estas rutas no necesitan headers ni body

### Respuesta

```javascript

{

"error": false,

"subjectCount": 24,

"results": [

{

"id":  "60e63aeb0272da001588f3f8",

"name":  "Filosofía",

"url":  "https://tearn.herokuapp.com/api/v1/subject/60e63aeb0272da001588f3f8",

"categoryId":  "60e633e40272da001588f3f2",

"courses": []

},

{

"id":  "60e63b050272da001588f3f9",

"name":  "Psicología",

"url":  "https://tearn.herokuapp.com/api/v1/subject/60e63b050272da001588f3f9",

"categoryId":  "60e633e40272da001588f3f2",

"courses": []

}

}

```

  
  

## GET ONE SUBJECT

  

Field | Description

------|------------

id | el id de la materia a buscar

  

### new url: https://tearn.herokuapp.com/api/v1/subject/60e63eff0272da001588f40e

### Respuesta

```javascript

{

"error": false,

"id": "60e63eff0272da001588f40e",

"url": "https://tearn.herokuapp.com/api/v1/subject/60e63eff0272da001588f40e",

"name": "Derecho penal",

"categoryId": "60e639c20272da001588f3f7",

"courseCount": 3,

"courses": [

{

"tutors": [],

"_id":  "60e6496a0272da001588f44a",

"name":  "Derecho penal III",

"subjectId":  "60e63eff0272da001588f40e",

"url":  "https://tearn.herokuapp.com/api/v1/course/60e6496a0272da001588f44a",

"imgUrl":  "https://tearn.herokuapp.com/resources/images/Open_Book.png",

"__v":  0

}

}

```

  

# PETICION PARA CURSOS

### Url base: https://tearn.herokuapp.com/api/v1/course

  

## POST

Campo | Descripción

------|------------

**name** | Nombre del curso

**subjectId** | Id de la materia a la que pertenece el curso

**imgUrl** | Imagen local

  

### Ejemplo

```javascript

{

"name": "Derecho mercantil I",

"subjectId": "60e63f1e0272da001588f410",

"imgUrl": "https://tearn.herokuapp.com/resources/images/Open_Book.png"

}

```

### Respuesta

  

```javascript

{

"error": false,

"message": "Course was created"

}

```

  

## GET

## Obtener todos los cursos

  

### Esta petición no necesita parametros

### Ejemplo

### Respuesta

```javascript

{

"error": false,

"results": [

{

"id":  "60e640640272da001588f411",

"name":  "Filosofía I",

"url":  "https://tearn.herokuapp.com/api/v1/course/60e640640272da001588f411",

"imgUrl":  "https://tearn.herokuapp.com/resources/images/Library.png",

"subjectId":  "60e63aeb0272da001588f3f8",

"tutors": []

},

{

"id":  "60e6406b0272da001588f412",

"name":  "Filosofía II",

"url":  "https://tearn.herokuapp.com/api/v1/course/60e6406b0272da001588f412",

"imgUrl":  "https://tearn.herokuapp.com/resources/images/Library.png",

"subjectId":  "60e63aeb0272da001588f3f8",

"tutors": [

{

"preferences": [

"60e639bd0272da001588f3f6",

"60e636a60272da001588f3f5",

"60e634e40272da001588f3f3"

],

"favTutors": [],

"subjectsId": [

"60e63b050272da001588f3f9",

"60e63ba60272da001588f3fd",

"60e63bf10272da001588f3ff"

],

"coursesId": [

"60e6406b0272da001588f412",

"60e640e70272da001588f415",

"60e641fe0272da001588f419",

"60e643950272da001588f423",

"60e6450e0272da001588f42c"

],

"languages": [

"Ingles"

],

"commentaries": [],

"availability": [

"Virtual",

"Presencial"

],

"reports": [],

"_id":  "60e66c7dd275d9001591a5ed",

"username":  "HENRY ALEXANDER CORTEZ AMAYA",

"email":  "00095119@uca.edu.sv",

"imgUrl":  "https://lh3.googleusercontent.com/a-/AOh14GgsMEsSyw0Hho7ztawqGgsdQyPDI5QD4cY2H36f=s96-c",

"isTutor": true,

"url":  "https://tearn.herokuapp.com/api/v1/user/60e66c7dd275d9001591a5ed",

"__v":  0,

"active": true,

"description":  "Soy una persona que le gusta enseñar aquellas materias dificiles para ayudar a las personas que les cuesta.",

"dot":  "12/02/2000",

"fullName":  "Henry Alexander Cortez Amaya ",

"puntuation":  0,

"responseTime":  "1h 30min",

"urlCommentaries":  "https://tearn.herokuapp.com/api/v1/commentary/tutors/60e66c7dd275d9001591a5ed",

"urlTutor":  "https://tearn.herokuapp.com/api/v1/user/tutor/60e66c7dd275d9001591a5ed"

}

]

}

}

```

  

## GET

## Obtener un curso

  

Parametro | Description

------|------------

**id** | id del curso

  

### Ejemplo

### nueva url: https://tearn.herokuapp.com/api/v1/course/60e640d00272da001588f413

### Respuesta

```javascript

{

"error": false,

"name": "Psicología I",

"url": "https://tearn.herokuapp.com/api/v1/course/60e640d00272da001588f413",

"imgUrl": "https://tearn.herokuapp.com/resources/images/Library.png",

"subjectId": "60e63b050272da001588f3f9",

"tutorsCount": 0,

"tutors": []

}

```

  

# Búsquedas

  

## QUERY

## Filtrado de búsquedas

  

Query | Description

------|------------

**pattern** | Patrón de lo que se desea mostrar

  

Url base: https://tearn.herokuapp.com/api/v1/search/?pattern=ca

  
  

### Respuesta

```javascript

{

"error": false,

"coursesCount": 3,

"tutorsCount": 3,

"courses": [

{

"id":  "60e642eb0272da001588f41e",

"name":  "Cálculo I",

"url":  "https://tearn.herokuapp.com/api/v1/course/60e642eb0272da001588f41e",

"tutorsCount":  0

},

{

"id":  "60e642f00272da001588f41f",

"name":  "Cálculo II",

"url":  "https://tearn.herokuapp.com/api/v1/course/60e642f00272da001588f41f",

"tutorsCount":  1

},

{

"id":  "60e642f70272da001588f420",

"name":  "Cálculo III",

"url":  "https://tearn.herokuapp.com/api/v1/course/60e642f70272da001588f420",

"tutorsCount":  2

}

],

"tutors": [

{

"id":  "60e65806d275d9001591a5e8",

"username":  "Carlos Cortez",

"imgUrl":  "https://lh3.googleusercontent.com/a-/AOh14GhYT-dr9V7yRluF0EwyY7t6xB-1dwyJ7a_eOXc5=s96-c",

"url":  "https://tearn.herokuapp.com/api/v1/user/60e65806d275d9001591a5e8",

"subjects": [

"Cálculo"

],

"puntuation":  3

},

{

"id":  "60e6758bd275d9001591a5f3",

"username":  "Carlos Cortez",

"imgUrl":  "https://lh3.googleusercontent.com/a/AATXAJyFyrHcySnrxyaqUvV8kC2d8eMDYFxD54YyUzYl=s96-c",

"url":  "https://tearn.herokuapp.com/api/v1/user/60e6758bd275d9001591a5f3",

"subjects": [

"Cálculo"

],

"puntuation":  0

},

{

"id":  "60e67d57d275d9001591a5f7",

"username":  "Carlos Roberto Cortez Iraheta",

"imgUrl":  "https://lh3.googleusercontent.com/a/AATXAJy63rULmX8Fmg_6lx8IUC8uEFsVwZlHZS_t0kx7=s96-c",

"url":  "https://tearn.herokuapp.com/api/v1/user/60e67d57d275d9001591a5f7",

"subjects": [

"Cálculo"

],

"puntuation":  0

}

]

}

```

  

# HOME

  

## GET

## Busqueda de recomendaciones

  

Params | Descripción

------|------------

**id** | Id del usuario a obtener

  

####  **Nota**: Es opcional el id

  

Url base: https://tearn.herokuapp.com/api/v1/home/60aff9e2be471114bc9abee3

  
  

### Respuesta

```javascript

{

"error": false,

"tutorCount": 6,

"subjectCount": 7,

"courseCount": 7,

"subjects": [

{

"id":  "60e63aeb0272da001588f3f8",

"name":  "Filosofía",

"url":  "https://tearn.herokuapp.com/api/v1/subject/60e63aeb0272da001588f3f8",

"courseCount":  2

},

{

"id":  "60e63b050272da001588f3f9",

"name":  "Psicología",

"url":  "https://tearn.herokuapp.com/api/v1/subject/60e63b050272da001588f3f9",

"courseCount":  3

},

{

"id":  "60e63b140272da001588f3fa",

"name":  "Teología",

"url":  "https://tearn.herokuapp.com/api/v1/subject/60e63b140272da001588f3fa",

"courseCount":  4

},

{

"id":  "60e63b730272da001588f3fb",

"name":  "Idiomas",

"url":  "https://tearn.herokuapp.com/api/v1/subject/60e63b730272da001588f3fb",

"courseCount":  4

},

{

"id":  "60e63b9a0272da001588f3fc",

"name":  "Cálculo",

"url":  "https://tearn.herokuapp.com/api/v1/subject/60e63b9a0272da001588f3fc",

"courseCount":  3

},

{

"id":  "60e63ba60272da001588f3fd",

"name":  "Física",

"url":  "https://tearn.herokuapp.com/api/v1/subject/60e63ba60272da001588f3fd",

"courseCount":  3

},

{

"id":  "60e63bb00272da001588f3fe",

"name":  "Química",

"url":  "https://tearn.herokuapp.com/api/v1/subject/60e63bb00272da001588f3fe",

"courseCount":  3

}

],

"tutors": [

{

"id":  "60e64a6c0272da001588f44e",

"fullName":  "Henry Alex Cortez",

"imgUrl":  "https://lh3.googleusercontent.com/a/AATXAJzn-cD3rPMUB7ju79_dcVJRwgsWTDQJyeXIhkCt=s96-c",

"puntuation":  4,

"url":  "https://tearn.herokuapp.com/api/v1/user/60e64a6c0272da001588f44e",

"subjects": [

"Teología"

]

},

{

"id":  "60e65514d275d9001591a5e7",

"fullName":  "Manuel de Jesús Hernández Benítez ",

"imgUrl":  "https://lh3.googleusercontent.com/a-/AOh14GjDm9zVMud1FKJ6_uUYt1M9VBv5jsTaWgsI8jbc_Q=s96-c",

"puntuation":  4,

"url":  "https://tearn.herokuapp.com/api/v1/user/60e65514d275d9001591a5e7",

"subjects": [

"Idiomas",

"Física"

]

},

{

"id":  "60e65806d275d9001591a5e8",

"fullName":  "Carlos Roberto Cortez",

"imgUrl":  "https://lh3.googleusercontent.com/a-/AOh14GhYT-dr9V7yRluF0EwyY7t6xB-1dwyJ7a_eOXc5=s96-c",

"puntuation":  3,

"url":  "https://tearn.herokuapp.com/api/v1/user/60e65806d275d9001591a5e8",

"subjects": [

"Cálculo"

]

},

{

"id":  "60e66c7dd275d9001591a5ed",

"fullName":  "Henry Alexander Cortez Amaya ",

"imgUrl":  "https://lh3.googleusercontent.com/a-/AOh14GgsMEsSyw0Hho7ztawqGgsdQyPDI5QD4cY2H36f=s96-c",

"puntuation":  0,

"url":  "https://tearn.herokuapp.com/api/v1/user/60e66c7dd275d9001591a5ed",

"subjects": [

"Psicología",

"Física",

"Manufactura"

]

},

{

"id":  "60e6758bd275d9001591a5f3",

"fullName":  "Carlos Cortez",

"imgUrl":  "https://lh3.googleusercontent.com/a/AATXAJyFyrHcySnrxyaqUvV8kC2d8eMDYFxD54YyUzYl=s96-c",

"puntuation":  0,

"url":  "https://tearn.herokuapp.com/api/v1/user/60e6758bd275d9001591a5f3",

"subjects": [

"Cálculo"

]

},

{

"id":  "60e67d57d275d9001591a5f7",

"fullName":  "CElos Coetez",

"imgUrl":  "https://lh3.googleusercontent.com/a/AATXAJy63rULmX8Fmg_6lx8IUC8uEFsVwZlHZS_t0kx7=s96-c",

"puntuation":  0,

"url":  "https://tearn.herokuapp.com/api/v1/user/60e67d57d275d9001591a5f7",

"subjects": [

"Cálculo"

]

}

],

"courses": [

{

"id":  "60e640640272da001588f411",

"name":  "Filosofía I",

"tutorCount":  0

},

{

"id":  "60e6406b0272da001588f412",

"name":  "Filosofía II",

"tutorCount":  1

},

{

"id":  "60e640d00272da001588f413",

"name":  "Psicología I",

"tutorCount":  0

},

{

"id":  "60e640db0272da001588f414",

"name":  "Psicología II",

"tutorCount":  0

},

{

"id":  "60e640e70272da001588f415",

"name":  "Psicología III",

"tutorCount":  1

},

{

"id":  "60e641600272da001588f416",

"name":  "Eclesiología",

"tutorCount":  0

},

{

"id":  "60e641bd0272da001588f417",

"name":  "Teología I",

"tutorCount":  0

}

]

}

```

  

# LOGIN

  

## POST

## Guardado de un usuario

  

Field | Descripción

------|------------

**username** | Nombre de usuario

**email** | Correo del usuario

**imgUrl** | Url de la imagen de la persona

  

Url base: https://tearn.herokuapp.com/api/v1/user/login

  

### Ejemplo

```javascript

{

"username": "Isabela Canela",

"email": "icanela@gmail.com",

"imgUrl": "https://web.whatsapp.com/pp?e=https%3A%2F%2Fpps.whatsapp.net%2Fv%2Ft61.24694-24%2F168202815_804238177153360_6266324713044465516_n.jpg%3Fccb%3D11-4%26oh%3Db1de114f7bb05137f0bf1dce5251f5d5%26oe%3D60B53DDA&t=l&u=50378403316%40c.us&i=1621062695&n=hdlVdu5uMC2b7BLYTjpYymQ7enR0ynFsgJ%2F75TRZCYQ%3D"

}

```

  

### Respuesta

```javascript

{

"error": false,

"accessToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjYwYzJlYzlkNDBhNTA5MDZlYTM5NDQ4YiIsInVzZXJuYW1lIjoiSXNhYmVsYSBDYW5lbGEiLCJlbWFpbCI6ImljYW5lbGFAZ21haWwuY29tIiwiaW1nVXJsIjoiaHR0cHM6Ly93ZWIud2hhdHNhcHAuY29tL3BwP2U9aHR0cHMlM0ElMkYlMkZwcHMud2hhdHNhcHAubmV0JTJGdiUyRnQ2MS4yNDY5NC0yNCUyRjE2ODIwMjgxNV84MDQyMzgxNzcxNTMzNjBfNjI2NjMyNDcxMzA0NDQ2NTUxNl9uLmpwZyUzRmNjYiUzRDExLTQlMjZvaCUzRGIxZGUxMTRmN2JiMDUxMzdmMGJmMWRjZTUyNTFmNWQ1JTI2b2UlM0Q2MEI1M0REQSZ0PWwmdT01MDM3ODQwMzMxNiU0MGMudXMmaT0xNjIxMDYyNjk1Jm49aGRsVmR1NXVNQzJiN0JMWVRqcFl5bVE3ZW5SMHluRnNnSiUyRjc1VFJaQ1lRJTNEIiwiaXNUdXRvciI6dHJ1ZSwiaWF0IjoxNjIzNjU5ODgwfQ.Vg2meFk1VScP_m3Ylk4oIpyNHbWLknPVubRXHOsUPaA",

"id": "60c2ec9d40a50906ea39448b",

"username": "Isabela Canela",

"email": "icanela@gmail.com",

"imgUrl": "https://web.whatsapp.com/pp?e=https%3A%2F%2Fpps.whatsapp.net%2Fv%2Ft61.24694-24%2F168202815_804238177153360_6266324713044465516_n.jpg%3Fccb%3D11-4%26oh%3Db1de114f7bb05137f0bf1dce5251f5d5%26oe%3D60B53DDA&t=l&u=50378403316%40c.us&i=1621062695&n=hdlVdu5uMC2b7BLYTjpYymQ7enR0ynFsgJ%2F75TRZCYQ%3D",

"isTutor": true,

"fullName": "Isabella Canela Rodriguez",

"preferences": [

"60aff6834c0d1712b07ca442"

],

"favTutors": [

"60aff9e2be471114bc9abee8"

]

}

```

  

# COMMENTARIOS

  

## POST

## Agregar un comentario

  

Field | Descripción

------|------------

**author** | Nombre de autor del comentario

**descriptio** | Descripcion del comentario

**puntuation** | Puntuación de un commentario

**adressedId** | Id del tutor

  

Url base: https://tearn.herokuapp.com/api/v1/commentary

  

### Ejemplo

```javascript

{

"author": "60aff9e2be471114bc9abee8",

"description": "Era mejor en smash",

"puntuation": 4,

"adressedId": "60c2ec9d40a50906ea39448b"

}

```

  

### Respuesta

```javascript

{

"error": false,

"message": "The commentary was created"

}

```

## GET

  

## Obtener los comentarios de un tutor

  

Params | Description

------|------------

**id** | Id del tutor

  

### Ejemplo

### nueva url: https://tearn.herokuapp.com/api/v1/commentary/tutors/60aff9e2be471114bc9abee8

### Respuesta

```javascript

{

"error": false,

"count": 2,

"results": [

{

"author":  {

"username":  "Carlos Cortez",

"imgUrl":  "https://lh3.googleusercontent.com/a-/AOh14GhYT-dr9V7yRluF0EwyY7t6xB-1dwyJ7a_eOXc5=s96-c"

},

"description":  "Recomiendo mucho sus clases",

"puntuation":  4

},

{

"author":  {

"username":  "Carlos Roberto Cortez Iraheta",

"imgUrl":  "https://lh3.googleusercontent.com/a/AATXAJy63rULmX8Fmg_6lx8IUC8uEFsVwZlHZS_t0kx7=s96-c"

},

"description":  "hola buena clase",

"puntuation":  4

}

]

}

```
Para mayor información acerca de la API, descargar e importar el archivo [API](./Resources/Insomnia_2021-07-08.yaml?raw=true) en Insomnia.

**Nota: si no se descarga el archivo ir al [siguiente enlace](https://github.com/UCASV/proyecto-plagiacodigos/blob/master/Resources/Insomnia_2021-07-08.yaml) para copiar el contenido en un archivo .yaml e importarlo a Insomnia**

## Aspectos Generales<a name="id5"></a>
La aplicación está destinada a ofrecer a las personas una experiencia de aprendizaje sencilla y amigable referente a una gran variedad de temas categorizados en los que el usuario podrá consultar y comunicarse de una manera más personal con “tutores” que le podrán ayudar a fortalecer sus conocimientos por medio de plataformas virtuales, brindando a los usuarios una manera segura de aprender desde casa.

### Objetivo<a name="id6"></a>
- Ser puente directo entre estudiantes que busquen tutorías (tutorados) con otros estudiantes o personas que desean compartir su conocimiento con los demás (tutores). Así también, recomendar temas y libros relacionados con su interés. De esta manera, el estudiante podrá comunicarse con tutores a su elección que comprendan bien la dificultad que presenta de un tema específico, concordando con él directamente para la tutoría requerida y las posibles disponibilidades de pago que este requiera.

### Softwares utilizado<a name="id7"></a>
Para desarrollar la aplicación se utilizo el lenguaje de programación [Kotlin](https://developer.android.com/kotlin?hl=es-419&gclid=CjwKCAjw_o-HBhAsEiwANqYhpwAWItSAUxS_T9zsl0gAvYDQ4o61eauzBUxeHcykzhMv7NEV9JDCehoC0jcQAvD_BwE&gclsrc=aw.ds), a su vez que el IDE (Entorno de desarrollo integrado) utilizado para la realización del proyecto es [Android Studio Code](https://developer.android.com/studio?hl=es-419&gclid=CjwKCAjw_o-HBhAsEiwANqYhp-Jrr056sOHu9HgyQjfadO4XFdsdhXgF26BqpmYPlBznugiVW5teCBoCyFMQAvD_BwE&gclsrc=aw.ds).


## Modelo Utilizado<a name="id8"></a>

El modelo MVVM fue el seleccionado para la implementación, ya que permite organizar los componentes de una forma adecuada y realizar pruebas con mayor facilidad en la aplicación.
### Fragmentos utilizados
Field | Description
 ------|------------
 FavoriteFragment| Muestra los tutores favoritos
 AvailabilityFragment| Muestra la disponibilidad para ser tutor
 DialogConfirmFragment| Cuadro de diálogo
 LandingFormFragment| Formulario inicial para ser tutor
 PersonalInformationFragment| Muestra la información principal del tutor
 PersonalInformationTwoFragment| Muestra la información secundaria del tutor 
 InformationFragment| Agrupa la información visible del tutor
 AccountFragment| Muestra la configuración de la cuenta
 DialogConfirmFragment| Cuadro de diálogo
 CategoryFragment| Muestra las categorías
 ChatsFragment| Muestra la mensajería
 CourseFragment| Muestra los cursos
 HomeFragment| Muestra el home principal de la aplicación
 OnBoardingFragment| Muestra información de la app generalizada al inicio de la app
 PreferenceFragment| Muestra las preferencias para elegir
 SearchBookFragment| Muestra la búsqueda de los libros
 SearchCourseFragment| Muestra la búsqueda de los cursos
 SearchFragment| Muestra el buscador en general
 SearchTutorFragment| Muestra la búsqueda de los tutores
 TutorProfileFragment| Muestra el perfil del tutor
 TutorReportFragment| Muestra las opciones para reportar
 TutorValorationsFragment| Muestra las puntuaciones de un tutor

### Viewmodels utilizados
Field | Description
 ------|------------
 AccountViewModel|Administra los datos de la cuenta
 CategoryViewModel|Administra las diferentes categorías en la aplicación
 HomeViewModel|Administra los datos del menú principal
 LoginViewmodel|Administra los datos de la autenticación del usuario
 OnBoardingViewModel|Administra la navegación de la información inicial de la aplicación
 PreferencesViewModel|Administra las preferencias escogidas por el usuario
 SearchViewModel|Administra las busquedas realizadas por el usuario
 TutorFavoriteViewModel|Administra los tutores favoritos que posee el usuario
 TutorProfileViewModel|Administra el perfil del tutor

### Activities

Field | Description
 ------|------------
 MainActivity|Es la principal actividad de la aplicación
 NavControllerActivity|Es la actividad que se utiliza para navegar en la aplicación
 
 
## Prototipos de la aplicación<a name="id9"></a>

Los prototipos de la aplicación fueron diseñados en la aplicación Adobe XD (Experience Design), en el cual se realizaron para tablets y teléfonos:

Tablets: [Landscape](https://xd.adobe.com/view/e177ca64-6f83-4256-af03-74105090fce0-b424/), [Portrait](https://xd.adobe.com/view/dae5a8f2-52b7-4443-ae40-f12fa5956c3b-8fb6/).
Telefono: [Landscape](https://xd.adobe.com/view/706c6040-1e9e-4f7d-9864-b50430315341-253f/), [Portrait](https://xd.adobe.com/view/cdeebe9b-cc4c-4e5f-b973-cb1de55fc9f0-98bd/).


## Preguntas y respuestas<a name="id10"></a>
- **¿Cuáles son los requerimientos para crear una cuenta en Tearn?**
	- Para crear una cuenta en la aplicación de Tearn, solamente necesitas una cuenta asociada a Google, con ello, ¡tendrás tu cuenta en cuestión de segundos!
- **¿Cómo puedo buscar a un tutor especializado en una materia?**
	- Para ello, haz click en el buscador, y obtendrás distintas maneras de realizar la búsqueda, ya sea por una categoría, por una materia o escribiendo directamente el nombre del tutor.
- **¿Puedo puntuar a un tutor?**
	- Para ello, basta con hacer click a la imagen de perfil del tutor, y en su descripción habrá un espacio para puntuar y comentar al tutor seleccionado.
- **¿Cómo puedo ser un tutor?**
	- Para poder ser tutor en Tearn, el usuario debe de ir al apartado de su cuenta, y hacer click en la opción "Conviértete en tutor", luego de eso, ¡rellena el formulario para aplicar al puesto de tutor!
- **¿Qué pasos tengo que realizar para poner como favorito a un tutor?**
	- Para realizar esto, al hacer click en el perfil del tutor, habrá un corazón, si se marca, en el menú de tu cuenta habrá un atajo para poder ver al tutor marcado.


## Colaboradores<a name="id11"></a>
Gracias a estas personas, fue posible la realización del proyecto:

- Cortez Iraheta, Carlos Roberto, 00204119 
- Cortez Amaya, Henry Alexander, 00095119 
- Hernández Benitez, Manuel De Jesús, 00094119 
- Rodriguez Miguel, Mario Josue, 00131819

## Usuarios de prueba<a name="id12"></a>
Para poder utilizar la aplicación es necesario contar con una llave, llamada "SHA-1", por favor brindar la llave SHA-1 contactar al siguiente correo: "00095119@gmail.com".

## Enlace del video de presentación<a name="id13"></a>

[Presentación TearnApp](https://youtu.be/IrEtUdcbZ4E)


## Enlace para ClickUp<a name="id14"></a>

Haz click al enlace de [ClickUp](https://sharing.clickup.com/b/h/4-14968729-2/663220382a98ae5) para acceder a nuestro muro de click up.

## Licencia de código<a name="id15"></a>
Tearn: Teach and learn esta disponible bajo la [Licencia MIT](./LICENSE).
