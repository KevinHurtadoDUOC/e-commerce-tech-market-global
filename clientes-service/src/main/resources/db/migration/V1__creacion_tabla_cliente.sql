create table cliente(
    rut_cliente bigint primary key,
    dv_cliente char(1) not null,
    nombre_cliente varchar(100) not null,
    direccion_cliente varchar(255) not null,
    telefono_cliente int not null,
    id_login bigint not null
)
