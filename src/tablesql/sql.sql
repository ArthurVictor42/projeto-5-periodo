create table usuario(
                        id_usuario bigint primary key auto_increment,
                        email varchar(50) not null,
                        senha varchar(255) not null,
                        data_cadastro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);



create table localizacao(
                            id_localizacao bigint primary key auto_increment,
                            cidade varchar(35) not null,
                            bairro varchar(40) not null,
                            regiao varchar(40) not null,
                            proximidade varchar(10) not null
);

create table caracteristica_imovel(
                                      id_caracteristica bigint primary key auto_increment,
                                      metragem VARCHAR(20) NOT NULL,
                                      vagas_garagens int,
                                      numeros_suite int not null,
                                      numeros_salas int not null,
                                      numeros_quartos int not null,
                                      numeros_banheiros int not null,
                                      possui_piscina varchar(10),
                                      possui_area_gourmet varchar(10),
                                      tipo_imovel varchar(30) not null,
                                      idade_imovel int not null,
                                      estado_conservacao varchar(25) not null
);


create table negociacao(
                           id_negociacao bigint primary key auto_increment,
                           finalidade varchar(40) not null,
                           condominio varchar(10) not null,
                           valor decimal(10,2) not null
);


create table imovel(
                       id_imovel bigint not null primary key auto_increment,
                       id_localizacao bigint not null,
                       id_caracteristica bigint not null,
                       id_negociacao bigint not null,
                       foreign key (id_localizacao) references localizacao(id_localizacao),
                       foreign key (id_caracteristica) references caracteristica_imovel(id_caracteristica),
                       foreign key (id_negociacao) references negociacao(id_negociacao)
);