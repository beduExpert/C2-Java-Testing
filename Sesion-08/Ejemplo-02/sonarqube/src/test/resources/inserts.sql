/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
-- INSERT INTO `acciones_sistema` VALUES (100,'El usuario user1 asignó la ruta para la fecha 2018-12-21 al usuario user5','2018-12-20 19:21:35',1),(101,'El usuario user3 aprobó el reporte de tipo NO_ENCONTRADO del establecimiento con id 11','2018-12-28 15:46:30',3),(102,'El usuario user3 descartó el reporte de tipo NO_ENCONTRADO del establecimiento con id 11','2018-12-28 16:53:52',3),(103,'El usuario user3 descartó el reporte de tipo NO_ENCONTRADO del establecimiento con id 11','2018-12-28 16:57:10',3);

-- INSERT INTO `bitacora_jornada` VALUES (1,'2018-12-20 14:05:43','2018-12-21 01:35:23',10.00,4),(2,'2018-12-14 22:29:28','2018-12-14 22:29:28',22.00,2),(3,'2018-12-14 22:29:28','2018-12-14 22:29:28',30.00,3),(4,'2018-12-14 22:29:29','2018-12-14 22:29:29',50.00,4);

-- INSERT INTO `establecimiento` VALUES (1,'establecimiento 1','Plaza de la constitución S/N','referencia1','558877442','Juan perez',1,1,1,1,19.4319716,-99.1334254),(2,'estab2','Plaza de la república S/N','referencia2','876554214','Ramiro gonzalez',1,1,2,1,19.4362082,-99.1546293),(3,'estab3','Zacatecas 36','referencia2','175496527','Pepe trujillo',1,1,3,3,19.4162015,-99.1560368),(4,'estab4','calle3 numero3','referencia2','175496527','Ramon A.',1,1,1,1,19.4330550,-99.1413662),(5,'estab5','calle1 numero1','referencia1','558877442','Esteban N.',1,1,1,2,19.4321915,-99.1418425),(6,'estab6','calle2 numero3','referencia2','876554214','Juan C L.',1,1,1,3,19.4356338,-99.1495107),(7,'estab7','calle3 numero3','referencia2','175496527','Luis S.',1,1,1,1,19.4151418,-99.1321895),(8,'estab8','calle3 numero3','referencia2','175496527','Lola M.',1,1,1,2,19.4151418,-99.1321895),(9,'estab9','calle1 numero1','referencia1','558877442','Juana Garcia',1,1,1,3,19.4321915,-99.1418425),(10,'estab10','calle2 numero3','referencia2','876554214','Erika G.',1,1,1,1,19.4356338,-99.1495107),(11,'estab11','calle3 numero3','referencia2','175496527','Hugo K.',1,1,1,2,19.4151418,-99.1321895),(12,'estab12','calle3 numero3','referencia2','175496527','Pedro P.',1,1,1,3,19.4151418,-99.1321895),(13,'estab13','calle2 numero3','referencia2','876554214','Pablo Marmol',1,1,1,1,19.4356338,-99.1495107),(14,'estab14','calle3 numero3','referencia2','175496527','Rodrigo H.',1,1,1,2,19.4151418,-99.1321895),(15,'estab15','calle3 numero3','referencia2','175496527','David G.',1,1,1,3,19.4151418,-99.1321895),(16,'estab16','calle1 numero1','referencia1','558877442','Francisco A.',1,1,1,1,19.4321915,-99.1418425);

-- INSERT INTO `estatus` VALUES (1,'ACTIVO'),(2,'PENDIENTE'),(3,'INACTIVO');

-- INSERT INTO `id_generator` VALUES ('acciones_sistema',104),('bitacora_jornada',100),('cuestionario',100),('establecimiento',100),('km_vigencia',100),('mensaje',101),('reporte',100),('ruta_asignada',101),('usuario',100),('visita',100),('zona',100);

-- INSERT INTO `km_vigencia` VALUES (1,'2017-01-01',20),(2,'2018-01-01',25),(3,'2019-01-01',30);

-- INSERT INTO `mensaje` VALUES (100,'Hola','2018-12-22 01:20:45',0,3,2);

-- INSERT INTO `perfil` VALUES (1,'Administrador Marchand'),(2,'Operativo Marchand'),(3,'Administrador Rocket Delivery'),(4,'Promotor');

-- INSERT INTO `ruta_asignada` VALUES (1,'2018-11-23','2018-12-20',4),(2,'2018-11-24','2018-12-18',4),(3,'2018-11-25','2018-12-19',4),(100,'2018-12-20','2018-12-21',5);

-- INSERT INTO `ruta_programada` VALUES (1,1),(2,1),(3,1),(2,2),(1,2),(4,100);

-- INSERT INTO `tipo_establecimiento` VALUES (1,'papeleria'),(2,'abarrotes');

-- INSERT INTO `tipo_reporte` VALUES (1,'NUEVO'),(2,'NO_ENCONTRADO');

INSERT INTO `usuario` VALUES (1,'user1','$2a$10$FXbDbf4IHIFcw4/kDZY6VOCspqVkOA9CXeJDfqh3DBfBE/VrFp3pq','Edgar','Ramos','Rambaud',1),(2,'user2','$2a$10$1kPVO9lt80QkW90ifPOygu4Gu4suU9o9.0ieEWBp67n.dHb1mZG5u','nombre2','apaterno2','amaterno2',2),(3,'user3','$2a$10$1kPVO9lt80QkW90ifPOygu4Gu4suU9o9.0ieEWBp67n.dHb1mZG5u','nombre3','apaterno3','amaterno3',3),(4,'user4','$2a$10$1kPVO9lt80QkW90ifPOygu4Gu4suU9o9.0ieEWBp67n.dHb1mZG5u','nombre4','apaterno4','amaterno4',4),(5,'user5','$2a$10$1kPVO9lt80QkW90ifPOygu4Gu4suU9o9.0ieEWBp67n.dHb1mZG5u','nombre5','apaterno5','amaterno5',4),(6,'user6','$2a$10$1kPVO9lt80QkW90ifPOygu4Gu4suU9o9.0ieEWBp67n.dHb1mZG5u','nombre6','apaterno6','amaterno6',4),(7,'user7','$2a$10$1kPVO9lt80QkW90ifPOygu4Gu4suU9o9.0ieEWBp67n.dHb1mZG5u','nombre7','apaterno7','amaterno7',4),(9,'user9','$2a$10$1kPVO9lt80QkW90ifPOygu4Gu4suU9o9.0ieEWBp67n.dHb1mZG5u','nombre9','apaterno9','amaterno9',4),(10,'user10','$2a$10$1kPVO9lt80QkW90ifPOygu4Gu4suU9o9.0ieEWBp67n.dHb1mZG5u','nombre10','apaterno10','amaterno10',4),(11,'user11','$2a$10$QHS9SLw4Kg2sVIVVoemeqOSSS89eP6pEzOWK7TdDcicsf80Lug6hy','Pedro','Ramos','Romo',4);
INSERT INTO `zona` VALUES (1,'zona1'),(2,'zona2'),(3,'zona3'),(4,'zona4'),(5,'zona5');
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
