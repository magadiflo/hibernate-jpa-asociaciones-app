INSERT INTO clientes (id, nombre, apellido, forma_pago, creado_en, editado_en) VALUES(1, 'Martín', 'Díaz Flores', 'debito', NULL, '2022-01-06 17:24:21'),(2, 'Alicia', 'Flores', 'credito', NULL, NULL),(3, 'Tinkler', 'Díaz', 'paypal', NULL, NULL),(4, 'Raúl', 'Díaz', 'debito', NULL, NULL),(5, 'Alicia', 'Flores', 'paypal', NULL, NULL),(6, 'Hazael', 'Díaz Ardiles', 'debito', NULL, '2022-01-06 17:45:08'),(7, 'Gahella', 'Díaz Ardiles', 'credito', '2022-01-06 17:44:34', NULL);
INSERT INTO alumnos(id, nombre, apellido) VALUES(1, 'Violeta', 'Flores');
INSERT INTO alumnos(id, nombre, apellido) VALUES(2, 'Gabriel', 'Vilela');
INSERT INTO cursos(id, titulo, profesor) VALUES(1, 'Curso Java', 'Andres');
INSERT INTO cursos(id, titulo, profesor) VALUES(2, 'Curso Hibernate y JPA', 'Andres');
INSERT INTO direcciones(calle, numero) VALUES('Bruces', 156);
INSERT INTO direcciones(calle, numero) VALUES('Las Palmeras', 200);
INSERT INTO tbl_clientes_direcciones (cliente_id, direccion_id) VALUES(1, 1);
INSERT INTO tbl_clientes_direcciones (cliente_id, direccion_id) VALUES(1, 2);