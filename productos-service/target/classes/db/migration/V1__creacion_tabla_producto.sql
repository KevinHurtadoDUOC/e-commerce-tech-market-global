create table producto(
    id_producto bigint auto_increment primary key,
    nombre_producto varchar(100) not null,
    precio_producto int not null,
    descripcion_producto varchar(255) not null,
    id_sucursal bigint not null,
    id_bodega bigint not null,
    rut_proveedor bigint not null
)
