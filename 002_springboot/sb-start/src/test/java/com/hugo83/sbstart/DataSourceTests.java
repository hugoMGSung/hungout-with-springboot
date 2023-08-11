package com.hugo83.sbstart;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class DataSourceTests {
    @Autowired
    private DataSource dataSource;

    @Test
    public void testConnection() throws SQLException {
        @Cleanup
        Connection conn = dataSource.getConnection();
        log.info(conn);

        Assertions.assertNotNull(conn);
    } 
}