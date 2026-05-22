create table bodega(
    id_bodega bigint auto_increment primary key,
    direccion_bodega varchar(255) not null,
    telefono_bodega int not null,
    temperatura_bodega int not null,
    capacidad_bodega int not null
)
