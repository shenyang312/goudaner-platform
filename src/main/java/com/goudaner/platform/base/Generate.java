package com.goudaner.platform.base;

import com.goudaner.platform.base.generatebase.GenerateEntity;
import com.goudaner.platform.base.generatebase.GenerateMapper;
import com.goudaner.platform.base.generatebase.GenerateService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by emmet on 17-3-29.
 * desc:
 */
public class Generate {
    private static List<String> colnames = new ArrayList<>();
    private static List<String> colTypes = new ArrayList<>();
    private static List<String> colComment = new ArrayList<>();
    private static Boolean importDate = false;
    private static Boolean importBigDecimal = false;

    /**
     *
     * @param host 数据库IP
     * @param port 数据库端口号
     * @param database 数据库名
     * @param username 数据库用户名
     * @param password 数据库密码
     * @param tableName 表名
     * @param entityName 实体类名
     * @param basePackage 实体类包
     * @param entityPath 实体类路径
     * @throws Exception
     */
    public static void generate(String host, String port, String database, String username, String password, String tableName, String entityName, String basePackage,
                                String entityPath, String mapperPath, String servicePath) throws Exception {
        String entityPackage = basePackage + ".entity";
        String mapperPackage = basePackage + ".mapper";
        String servicePackage = basePackage + ".service";
        //读取表结构
        initTable(host, port, database, username, password, tableName);
        //生成实体类
        GenerateEntity.generate(entityName, entityPackage, entityPath, database, tableName, colnames, colTypes, colComment, importDate, importBigDecimal);
        ///生成Mapper
        GenerateMapper.generate(entityName, entityPackage, mapperPackage, mapperPath);
        //生成Service
        GenerateService.generate(entityName, entityPackage, mapperPackage, servicePackage, servicePath);

    }


    public static void main(String[] args)throws Exception {
        String basePackage = "com.goudaner.platform";
        String basePath = "/Users/ks/IdeaProjects/platform/";
        String entityPath = basePath + "src/main/java/com/goudaner/platform/entity/";
        String mapperPath = basePath + "src/main/java/com/goudaner/platform/mapper/";
        String servicePath = basePath + "src/main/java/com/goudaner/platform/service/";
//     String basePath = "E:\\workspace\\zmkj-bms-cloud\\";
        generate("localhost", "3306", "goudaner", "root", "sy123456",
                "gd_order_merch","GdOrderMerch", basePackage, entityPath, mapperPath, servicePath);
    }

    public static void generateEntity(String host, String port, String database, String username, String password, String tableName, String entityName, String basePackage, String basePath) throws Exception {
        String entityPackage = basePackage + ".entity";
        String entityPath = basePath + "/entity/";
        //读取表结构
        initTable(host, port, database, username, password, tableName);
        //生成实体类
        GenerateEntity.generate(entityName, entityPackage, entityPath, database, tableName, colnames, colTypes, colComment, importDate, importBigDecimal);

    }

    private static void initTable(String host, String port, String database, String username, String password, String tableName) throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        String url = String.format("jdbc:mysql://%s:%s/%s", host, port, database);
        Connection conn = DriverManager.getConnection(url, username, password);
        String strsql = "SELECT COLUMN_NAME, DATA_TYPE, COLUMN_KEY, COLUMN_COMMENT  FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA = '" + database + "' AND TABLE_NAME = '" + tableName + "'"; //读一行记录;
        PreparedStatement pstmt = conn.prepareStatement(strsql);
        ResultSet result = pstmt.executeQuery();
        while (result.next()) {
            colnames.add(result.getString(1));
            colTypes.add(result.getString(2));
            colComment.add(result.getString(4));
            if ("date".equals(result.getString(2)) || "datetime".equals(result.getString(2)) || "timestamp".equals(result.getString(2))) importDate = true;
            if ("decimal".equals(result.getString(2))) importBigDecimal = true;
        }
        if(conn!=null) conn.close();
    }
}
