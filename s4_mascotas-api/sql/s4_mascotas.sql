delete from MascotaEntity;
delete from ArticuloEntity;
delete from EventoEntity;

insert into ArticuloEntity (id, titulo, tema, contenido, resumen) values (100, 'Lo bueno de tener una mascota en casa', 'SALUD', 'Sin duda, las mascotas se han convertido en un miembro más de la familia. Cada día son más valoradas, pues más allá de brindar diversión y compañía.', 'La presencia de una mascota en el hogar puede mejorar la calidad de vida y contribuir a mejorar la salud física y mental de las personas.');
insert into ArticuloEntity (id, titulo, tema, contenido, resumen) values (200, 'Premios saludables que puedes darle a tu perro', 'CIUDADO', 'Los expertos en educación canina, tanto la vertiente más tradicional como la que aboga por una educación en positivo, son partidarios de los premios para perros.', 'La obesidad y el sobrepeso canino afectan a un porcentaje muy elevado de mascotas, por lo que debemos prestar especial atención a su alimentación. También a los premios que les ofrecemos: mejor caseros y saludables.');

insert into EventoEntity (id, nombre, descripcion, imagen, fechaInicio, fechaFin) values (100, 'Feria canina', 'Todo lo que tu mejor amigo necesita en un solo lugar', 'https://www.lanetanoticias.com/wp-content/uploads/2017/07/images-1.jpg', '04/20/2019', '04/24/2019');
insert into EventoEntity (id, nombre, descripcion, imagen, fechaInicio, fechaFin) values (200, 'Peluqueria gratis', 'Acercate al centro comercial Atlanttis y dale a tu mascota un tratamiento de peluqueria gratis', 'https://www.cimformacion.com/blog/wp-content/uploads/perro-en-la-peluqueria-canina.jpg', '05/15/2019', '05/16/2019');

insert into MascotaEntity (id, nombre, tipo, raza, descripcion) values (100, 'Max', 'PERRO', 'Labrador', 'Max es un labrador de 4 años');
insert into MascotaEntity (id, nombre, tipo, raza, descripcion) values (200, 'Lola', 'PERRO', 'Boston Terrier', 'Lola es una perra de dos años');
insert into MascotaEntity (id, nombre, tipo, raza, descripcion) values (300, 'Mia', 'GATO', 'Criollo', 'Mia es una gatita de 5 años');