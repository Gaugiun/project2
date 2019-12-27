package com.baidu.mall.typehandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(Object.class)
public class String2ObjectTypeHandler implements TypeHandler<Object> {
    @Override
    public void setParameter(PreparedStatement preparedStatement, int index, Object object, JdbcType jdbcType) throws SQLException {
        ObjectMapper objectMapper = new ObjectMapper();
        //这是存入过程
        String s = null;
        try {
            s = objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        preparedStatement.setString(index,s);
    }
    /*
    * 取出字符串数据，并且封装成Object对象的过程
    * */
    @Override
    public Object getResult(ResultSet resultSet, String column) throws SQLException {
        String result = resultSet.getString(column);
        return transfer(result);
    }

    @Override
    public Object getResult(ResultSet resultSet, int index) throws SQLException {
        String result = resultSet.getString(index);
        return transfer(result);
    }

    @Override
    public Object getResult(CallableStatement callableStatement, int index) throws SQLException {
        String result = callableStatement.getString(index);
        return transfer(result);
    }

    private Object transfer(String result){
        ObjectMapper objectMapper = new ObjectMapper();
        Object object = new Object();
        try {
            object = objectMapper.readValue(result, Object.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return object;
    }
}
