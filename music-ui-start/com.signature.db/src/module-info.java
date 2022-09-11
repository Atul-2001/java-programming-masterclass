module com.signature.db {
    requires java.sql;
    requires sqlite.jdbc;
    requires transitive com.signature.common;

    exports com.signature.db;
}