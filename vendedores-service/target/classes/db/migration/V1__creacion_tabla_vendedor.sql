create table vendedor(
    id_vendedor bigint auto_increment primary key,
    nombre_vendedor varchar(100) not null,
    sueldo_vendedor int not null,
    id_sucursal bigint not null,
    id_login bigint not null
)
