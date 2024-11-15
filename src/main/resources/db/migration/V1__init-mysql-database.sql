
    drop table if exists customer;

    drop table if exists drink;

    create table customer (
        version integer,
        created_date datetime(6),
        last_modified_date datetime(6),
        id varchar(36) not null,
        customer_name varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table drink (
        drink_style tinyint not null check (drink_style between 0 and 10),
        price decimal(38,2) not null,
        quantity_on_hand integer,
        version integer,
        created_date datetime(6),
        update_date datetime(6),
        id varchar(36) not null,
        drink_name varchar(50) not null,
        upc varchar(255) not null,
        primary key (id)
    ) engine=InnoDB;
