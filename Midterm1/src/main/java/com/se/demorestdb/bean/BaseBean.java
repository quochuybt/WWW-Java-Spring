package com.se.demorestdb.bean;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class BaseBean {

    private volatile DataSource dataSource;
    public DataSource getDataSource() {
        if (dataSource==null) {
            try {
                Context env = (Context) new InitialContext().lookup("java:comp/env");
                dataSource = (DataSource) env.lookup("jdbc/quanlytintuc");
            } catch (NamingException e) {
                throw new RuntimeException(e);
            }
        }

        return dataSource;
    }
}
