package group0153.conferencesystem.config;

import org.hibernate.dialect.Dialect;
import org.hibernate.dialect.function.SQLFunctionTemplate;
import org.hibernate.dialect.function.StandardSQLFunction;
import org.hibernate.dialect.function.VarArgsSQLFunction;
import org.hibernate.type.StringType;

import java.sql.Types;

/**
 * A class facilitating the interaction of the program with the database
 */
public class SQLiteDialect extends Dialect {

    /**
     * Construct an instance of SQLiteDialect and set all of the column types and functions
     */
    public SQLiteDialect() {
        registerColumnType(Types.BIT, "integer");
        registerColumnType(Types.TINYINT, "tinyint");
        registerColumnType(Types.SMALLINT, "smallint");
        registerColumnType(Types.INTEGER, "integer");
        registerColumnType(Types.BIGINT, "bigint");
        registerColumnType(Types.FLOAT, "float");
        registerColumnType(Types.REAL, "real");
        registerColumnType(Types.DOUBLE, "double");
        registerColumnType(Types.NUMERIC, "numeric");
        registerColumnType(Types.DECIMAL, "decimal");
        registerColumnType(Types.CHAR, "char");
        registerColumnType(Types.VARCHAR, "varchar");
        registerColumnType(Types.LONGVARCHAR, "longvarchar");
        registerColumnType(Types.DATE, "date");
        registerColumnType(Types.TIME, "time");
        registerColumnType(Types.TIMESTAMP, "timestamp");
        registerColumnType(Types.BINARY, "blob");
        registerColumnType(Types.VARBINARY, "blob");
        registerColumnType(Types.LONGVARBINARY, "blob");
        registerColumnType(Types.BLOB, "blob");
        registerColumnType(Types.CLOB, "clob");
        registerColumnType(Types.BOOLEAN, "integer");

        registerFunction("concat", new VarArgsSQLFunction(StringType.INSTANCE, "", "||", ""));
        registerFunction("mod", new SQLFunctionTemplate(StringType.INSTANCE, "?1 % ?2"));
        registerFunction("substr", new StandardSQLFunction("substr", StringType.INSTANCE));
        registerFunction("substring", new StandardSQLFunction("substr", StringType.INSTANCE));
    }

    /**
     * Get whether the database supports identity column key generation
     *
     * @return boolean representing whether the database supports identity column key generation
     */
    public boolean supportsIdentityColumns() {
        return true;
    }

    /**
     * Get whether this SQLiteDialect instance has a separate identity data type  or an Identity clause added to
     * the data type
     *
     * @return boolean representing whether this SQLiteDialect instance has a separate identity data type (false) or an
     * Identity clause added to the data type (true)
     */
    public boolean hasDataTypeInIdentityColumn() {
        return false;
    }

    /**
     * Get a string representing the identity column
     *
     * @return String representation of identity column
     */
    public String getIdentityColumnString() {
        return "integer";
    }

    /**
     * Get SELECT command applicable to retrieve last identity value generated
     *
     * @return String SELECT command that can be used to retrieve last generated identify value
     */
    public String getIdentitySelectString() {
        return "select last_insert_rowid()";
    }

    /**
     * Get whether this SQLiteDialect instance can handle putting a limit on query results with an SQL clause
     *
     * @return boolean representing if SQLiteDialect instance is able to handle having a limit on query results with
     * an SQL clause
     */
    public boolean supportsLimit() {
        return true;
    }

    /**
     * Applies and returns a modified query that has a limiting clause applied
     *
     * @param query     String SQL query that is to have the limit clause applied
     * @param hasOffset boolean whether the query requests an offset
     * @return the modified String query with the limiting clause applied
     */
    protected String getLimitString(String query, boolean hasOffset) {
        return new StringBuffer(query.length() + 20).append(query).append(hasOffset ? " limit ? offset ?" : " limit ?")
                .toString();
    }

    /**
     * Get whether this SQLiteDialect instance supports temporary tables
     *
     * @return boolean representing whether this SQLiteDialect instance supports temporary tables
     */
    public boolean supportsTemporaryTables() {
        return true;
    }

    /**
     * Get command to create a new temporary table
     *
     * @return String command used to create a temporary table
     */
    public String getCreateTemporaryTableString() {
        return "create temporary table if not exists";
    }

    /**
     * Get if temporary table must be dropped after use
     *
     * @return boolean representing whether temporary table should be dropped after it is used
     */
    public boolean dropTemporaryTableAfterUse() {
        return false;
    }

    public boolean supportsCurrentTimestampSelection() {
        return true;
    }

    public boolean isCurrentTimestampSelectStringCallable() {
        return false;
    }

    public String getCurrentTimestampSelectString() {
        return "select current_timestamp";
    }

    public boolean supportsUnionAll() {
        return true;
    }

    public boolean hasAlterTable() {
        return false;
    }

    public boolean dropConstraints() {
        return false;
    }

    public String getAddColumnString() {
        return "add column";
    }

    public String getForUpdateString() {
        return "";
    }

    public boolean supportsOuterJoinForUpdate() {
        return false;
    }

    public String getDropForeignKeyString() {
        throw new UnsupportedOperationException("No drop foreign key syntax supported by SQLiteDialect");
    }

    public String getAddForeignKeyConstraintString(String constraintName, String[] foreignKey, String referencedTable,
                                                   String[] primaryKey, boolean referencesPrimaryKey) {
        throw new UnsupportedOperationException("No add foreign key syntax supported by SQLiteDialect");
    }

    public String getAddPrimaryKeyConstraintString(String constraintName) {
        throw new UnsupportedOperationException("No add primary key syntax supported by SQLiteDialect");
    }

    public boolean supportsIfExistsBeforeTableName() {
        return true;
    }

    public boolean supportsCascadeDelete() {
        return false;
    }
}
