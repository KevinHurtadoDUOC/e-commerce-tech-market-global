create table login(
    id_login bigint auto_increment primary key,
    usuario varchar(50) not null,
    contrasena varchar(255) not null,
    ultimo_acceso date not null
)