package config;

import entity.Enumhttp;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//@MappedJdbcTypes(JdbcType.VARCHAR)
public class ExampleTypeHandler extends BaseTypeHandler<Enumhttp> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Enumhttp parameter, JdbcType jdbcType) throws SQLException
    {
        ps.setString(i, parameter.toString());
    }
    @Override
    public Enumhttp getNullableResult(ResultSet rs, String columnName) throws SQLException
    {
        return Enumhttp.valueOf(rs.getString(columnName));
    }
    @Override
    public Enumhttp getNullableResult(ResultSet resultSet, int columnIndex)throws SQLException{
        return Enumhttp.valueOf(resultSet.getString(columnIndex));
    }
    @Override
    public Enumhttp getNullableResult(CallableStatement callableStatement, int columnIndex) throws SQLException{
        return Enumhttp.valueOf(callableStatement.getString(columnIndex));
    }
}
