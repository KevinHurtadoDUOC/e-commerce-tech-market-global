create table venta
(
    id_venta bigint auto_increment primary key,
    fecha_venta date not null,
    monto_total int not null,
    id_vendedor bigint not null,
    id_cliente bigint not null,
    id_sucursal bigint not null
)