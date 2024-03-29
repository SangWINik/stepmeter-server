package com.maxosoft.stepmeter.web.controller;

import com.maxosoft.stepmeter.db.ConnectionFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@RestController
public class TestController {

    @GetMapping(value = "/")
    public ResponseEntity testNoPath() {
        return ResponseEntity.ok("no path");
    }

    @GetMapping(value = "/test")
    public ResponseEntity test() {
        return ResponseEntity.ok("test");
    }

    @GetMapping(value = "/db")
    public ResponseEntity testDb() {
        List<String> rows = new ArrayList<>();
        try {
            Connection connection = ConnectionFactory.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM testtable");
            while (rs.next()) {
                rows.add(rs.getString("textcolumn"));
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(rows);
    }
}
