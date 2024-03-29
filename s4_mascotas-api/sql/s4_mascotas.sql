
delete from ArticuloEntity;
delete from EventoEntity;
delete from MASCOTAENADOPCIONENTITY_USUARIOENTITY;
delete from ClasificadoEntity;
delete from CalificacionEntity;
delete from MascotaExtraviadaEntity;
delete from RecompensaEntity;
delete from MascotaEnAdopcionEntity;
delete from MascotaEntity;
delete from UsuarioEntity;

insert into UsuarioEntity (id, contrasenha, correo, nombre, usuario, telefono, dayBirth, monthBirth, yearBirth,rol,rutaImagen) values (100, '12345', 'pc@gmail.com', 'Pablo Cruz', 'pccruz', '31263729472', '04', '05', '1998','ADMIN','https://www.skintransform.co.uk/wp-content/uploads/2016/10/Men-Face6.jpg');
insert into UsuarioEntity (id, contrasenha, correo, nombre, usuario, telefono, dayBirth, monthBirth, yearBirth,rol,rutaImagen) values (200, '2929', 'ct@gmail.com', 'Carolina Tobón', 'ctobon', '3126876572', '10', '07', '1978','NORMAL','https://www.skintransform.co.uk/wp-content/uploads/2016/10/Women-Face3.jpg');
insert into UsuarioEntity (id, contrasenha, correo, nombre, usuario, telefono, dayBirth, monthBirth, yearBirth,rol,rutaImagen) values (300, 'holahola', 'tp@gmail.com', 'Tomás Paramo', 'tomip', '31263728754', '30', '08', '1988','NORMAL','https://www.skintransform.co.uk/wp-content/uploads/2016/10/Men-Face5.jpg');

insert into ArticuloEntity (id, titulo, tema, contenido, resumen, autor_id) values (100, 'Lo bueno de tener una mascota en casa', 'SALUD', 'Sin duda, las mascotas se han convertido en un miembro más de la familia. Cada día son más valoradas, pues más allá de brindar diversión y compañía.', 'La presencia de una mascota en el hogar puede mejorar la calidad de vida y contribuir a mejorar la salud física y mental de las personas.', 200);
insert into ArticuloEntity (id, titulo, tema, contenido, resumen, autor_id) values (200, 'Premios saludables que puedes darle a tu perro', 'CUIDADO', 'Los expertos en educación canina, tanto la vertiente más tradicional como la que aboga por una educación en positivo, son partidarios de los premios para perros.', 'La obesidad y el sobrepeso canino afectan a un porcentaje muy elevado de mascotas, por lo que debemos prestar especial atención a su alimentación. También a los premios que les ofrecemos: mejor caseros y saludables.', 300);

insert into EventoEntity (id, nombre, descripcion, imagen, fechaInicio, fechaFin, organizador_id) values (100, 'Feria canina', 'Todo lo que tu mejor amigo necesita en un solo lugar', 'https://www.lanetanoticias.com/wp-content/uploads/2017/07/images-1.jpg', '04/20/2019', '04/24/2019', 100);
insert into EventoEntity (id, nombre, descripcion, imagen, fechaInicio, fechaFin, organizador_id) values (200, 'Peluqueria gratis', 'Acercate al centro comercial Atlanttis y dale a tu mascota un tratamiento de peluqueria gratis', 'https://www.cimformacion.com/blog/wp-content/uploads/perro-en-la-peluqueria-canina.jpg', '05/15/2019', '05/16/2019', 200);

insert into ClasificadoEntity (id, nombre, contenido, enlace, autor_id) values (100, 'Veterinaria 24 horas', 'Servicios de veterinaria a domicilio 24 horas.', 'vetdomicilio.com.co', 200);
insert into ClasificadoEntity (id, nombre, contenido, enlace, autor_id) values (200, 'Entrenamiento canino', 'Servicio de entrenamiento canino y adiestramiento por alguno de nuestros expertos.', 'entrenandoando.com.co', 300);

insert into MascotaEntity (id, nombre, tipo, raza, descripcion) values (100, 'Max', 'PERRO', 'Labrador', 'Max es un labrador de 4 años');
insert into MascotaEntity (id, nombre, tipo, raza, descripcion) values (200, 'Lola', 'PERRO', 'Boston Terrier', 'Lola es una perra de dos años');
insert into MascotaEntity (id, nombre, tipo, raza, descripcion) values (300, 'Mia', 'GATO', 'Criollo', 'Mia es una gatita de 5 años');

insert into MascotaEnAdopcionEntity (id, adoptada, fechaFinal, fechaInicio, pasado, razonAdopcion, duenio_id, mascota_id) values (100 , 0, '04/20/2019' , '04/15/2019', 'La encontramos en la calle hace 3 años y nos enamoramos de ella', 'mi esposa se murió y me pone muy triste ver a mi mascota' , 100 , 100);
insert into MascotaEnAdopcionEntity (id, adoptada, fechaFinal, fechaInicio, pasado, razonAdopcion, duenio_id, mascota_id) values (200 , 1, '05/20/2019' , '05/15/2019', 'Siempre ha sido una mascota muy inquieta y la queremos así como es', 'nos tenemos que mudar a un apartamento más pequeño y queremos que sea libre' , 200 , 200);
insert into MascotaEnAdopcionEntity (id, adoptada, fechaFinal, fechaInicio, pasado, razonAdopcion, duenio_id, mascota_id) values (300 , 1, '06/20/2019' , '06/15/2019', 'Come mucho y es muy inquieta', 'no me gustó tener mascota' , 300 , 300);

insert into CalificacionEntity (id, calificacion, comentario, procesoMascotaEnAdopcion_id) values (100, 5 , 'Excelente servicio, el proceso fue muy confiable y el dueño muy amable',300);
insert into CalificacionEntity (id, calificacion, comentario, procesoMascotaEnAdopcion_id) values (200, 4 , 'Me enamoré de la mascota en el instante que la adopté, ¡ahora soy muy feliz!',200);

insert into MASCOTAENADOPCIONENTITY_USUARIOENTITY values (200,100);
insert into MASCOTAENADOPCIONENTITY_USUARIOENTITY values (300,100);
--faltan foreign keys
insert into MascotaExtraviadaEntity(id, direccion, ciudad, estado) values (100,'cra91#792','Santiago de Chile','PENDIENTE');
insert into MascotaExtraviadaEntity(id, direccion, ciudad, estado) values (200,'cra20#895','Estocolmo','PENDIENTE');
insert into MascotaExtraviadaEntity(id, direccion, ciudad, estado) values (300,'cra92#893','Bogotá','PENDIENTE');

--faltan foreign keys
insert into RecompensaEntity(id, medioDePago, valor, estado) values (100,'efectivo', 1500.0, 'PENDIENTE');
insert into RecompensaEntity(id, medioDePago, valor, estado) values (200,'efectivo', 2500.0, 'PENDIENTE');
insert into RecompensaEntity(id, medioDePago, valor, estado) values (300,'efectivo', 4500.0, 'PENDIENTE');
