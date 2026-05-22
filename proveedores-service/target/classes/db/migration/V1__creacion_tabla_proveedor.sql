create table proveedor(
    rut_proveedor bigint primary key,
    dv_proveedor char(1) not null,
    nombre_proveedor varchar(100) not null,
    correo_proveedor varchar(100) not null,
    telefono_proveedor int not null
)
