create table sucursal(
    id_sucursal bigint auto_increment primary key,
    nombre_sucursal varchar(100) not null,
    fono_sucursal int not null,
    direccion_sucursal varchar(255) not null
)
