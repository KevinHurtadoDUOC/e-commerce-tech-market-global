create table detalle_venta
(
    id_detalle_venta bigint auto_increment primary key,
    id_producto bigint not null,
    cantidad int not null,
    precio_unitario int not null,
    subtotal int not null,
    id_venta bigint not null
)