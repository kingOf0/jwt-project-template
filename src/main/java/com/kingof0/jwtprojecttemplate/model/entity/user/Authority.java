package com.kingof0.jwtprojecttemplate.model.entity.user;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Getter
public enum Authority implements GrantedAuthority {

    //------------ADMINISTRATION------------
    ADMINISTRATOR(0),
    //------------ADMINISTRATION------------


    //------------MANAGEMENT------------
    //Employee
    VIEW_EMPLOYEE(10),
    CREATE_EMPLOYEE(10),
    MODIFY_EMPLOYEE(10),
    DELETE_EMPLOYEE(10),
    //OffDay
    VIEW_OFF_DAY(20),
    CREATE_OFF_DAY(20),
    MODIFY_OFF_DAY(20),
    DELETE_OFF_DAY(20),
    //Shift
    VIEW_SHIFT(30),
    CREATE_SHIFT(30),
    MODIFY_SHIFT(30),
    DELETE_SHIFT(30),
    //Shift Variant
    SHIFT_VARIANT_VIEW(31),
    SHIFT_VARIANT_CREATE(31),
    SHIFT_VARIANT_MODIFY(31),
    SHIFT_VARIANT_DELETE(31),
    //Shift PI
    SHIFT_PI_CREATE(32),
    //Shift Group
    SHIFT_GROUP_VIEW(33),
    SHIFT_GROUP_CREATE(33),
    SHIFT_GROUP_UPDATE(33),
    SHIFT_GROUP_DELETE(33),

    //Stock
    VIEW_STOCK(20),
    CREATE_STOCK(20),
    MODIFY_STOCK(20),
    DELETE_STOCK(20),
    //StockRecord
    VIEW_STOCK_RECORD(25),
    //StoreAudit
    VIEW_STORE_AUDIT(30),
    CREATE_STORE_AUDIT(30),
    MODIFY_STORE_AUDIT(30),
    DELETE_STORE_AUDIT(30),
    //StoreAuditQuestion
    VIEW_STORE_AUDIT_QUESTION(31),
    CREATE_STORE_AUDIT_QUESTION(31),
    MODIFY_STORE_AUDIT_QUESTION(31),
    DELETE_STORE_AUDIT_QUESTION(31),
    //MallAudit
    VIEW_MALL_AUDIT(40),
    CREATE_MALL_AUDIT(40),
    MODIFY_MALL_AUDIT(40),
    DELETE_MALL_AUDIT(40),
    //Advertisement
    VIEW_ADVERTISEMENT(50),
    CREATE_ADVERTISEMENT(50),
    MODIFY_ADVERTISEMENT(50),
    DELETE_ADVERTISEMENT(50),
    //RentedAdvertisement
    VIEW_RENTED_ADVERTISEMENT(60),
    CREATE_RENTED_ADVERTISEMENT(60),
    MODIFY_RENTED_ADVERTISEMENT(60),
    DELETE_RENTED_ADVERTISEMENT(60),
    //Storage
    VIEW_STORAGE(60),
    CREATE_STORAGE(60),
    MODIFY_STORAGE(60),
    DELETE_STORAGE(60),
    //RentedStorage
    VIEW_RENTED_STORAGE(70),
    CREATE_RENTED_STORAGE(70),
    MODIFY_RENTED_STORAGE(70),
    DELETE_RENTED_STORAGE(70),
    //Report
    VIEW_REPORT(70),
    CREATE_REPORT(70),
    MODIFY_REPORT(70),
    DELETE_REPORT(70),
    //------------MANAGEMENT------------


    //------------Danışma------------
    VIEW_SURVEY(80),
    CREATE_SURVEY(80),
    MODIFY_SURVEY(80),
    DELETE_SURVEY(80),

    //------------Muhasabe------------
    VIEW_BILL(90),
    CREATE_BILL(90),
    //StoreRecord
    VIEW_STORE_RECORD(100),
    CREATE_STORE_RECORD(100),
    MODIFY_STORE_RECORD(100),
    DELETE_STORE_RECORD(100),
    //Consume
    VIEW_CONSUME(110),
    CREATE_CONSUME(110),
    MODIFY_CONSUME(110),
    DELETE_CONSUME(110),


    APPROVE_TICKET_ADMINISTRATION(120),
    APPROVE_TICKET_OPERATION(120),
    APPROVE_TICKET_MANAGEMENT(120),
    APPROVE_TICKET_TECHNICAL(120),
    APPROVE_TICKET(120),
    VIEW_TICKET(120),
    CREATE_TICKET(120),
    DELETE_TICKET_OTHERS(120),
    MODIFY_TICKET_OTHERS(120),

    VIEW_USERS(130),
    MODIFY_USER(130),
    DELETE_USER(130),
    CREATE_USER(130),

    VIEW_STORE(140),
    CREATE_STORE(140),
    MODIFY_STORE(140),
    DELETE_STORE(140),

    //StoreEquipment
    VIEW_STORE_EQUIPMENT(150),
    CREATE_STORE_EQUIPMENT(150),
    MODIFY_STORE_EQUIPMENT(150),
    DELETE_STORE_EQUIPMENT(150),

    //Template
    VIEW_TEMPLATE(160),
    DOWNLOAD_TEMPLATE(160),
    UPLOAD_TEMPLATE(160),

    //Config
    VIEW_CONFIG(170),
    MODIFY_CONFIG(170),
    DELETE_CONFIG(170),
    CREATE_CONFIG(170),

    //Notification
    PUSH_USER_NOTIFICATION(180),
    PUSH_DEPARTMENT_NOTIFICATION(180),
    PUSH_ALL_NOTIFICATION(180),

    ;
    private final Integer group;


    Authority(Integer group) {
        this.group = group;
    }

    @Override
    public String getAuthority() {
        return name();
    }

    public SimpleGrantedAuthority getSimpleGrantedAuthority() {
        return new SimpleGrantedAuthority(name());
    }

}