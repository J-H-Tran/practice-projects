package org.app.manager.library.util;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DatabaseUtilTest {

    @Test
    @DisplayName("Test getConnection()")
    public void testGetConnection() {
        try (Connection conn = DatabaseUtil.getConnection()) {
            assertNotNull(conn, "Connection should not be null.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
