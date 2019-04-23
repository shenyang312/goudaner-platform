package com.goudaner.platform.base.generatebase;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by emmet on 17-3-29.
 * desc:
 */
public class GenerateMapper {
    //生成Mapper类
    public static void generate(String entityName, String entityPackage, String mapperPackage, String mapperPath) throws IOException {
        StringBuffer sb = new StringBuffer();
        sb.append("package ").append(mapperPackage).append(";\r\n").append("\r\n");

        sb.append("import com.goudaner.platform.base.SyMapper;\r\n");
        sb.append("import ").append(entityPackage).append(".").append(entityName).append(";\r\n");
        sb.append("import org.apache.ibatis.annotations.Mapper;\r\n\r\n");

        sb.append("@Mapper\r\n");
        sb.append("public interface ").append(entityName).append("Mapper extends SyMapper<" + entityName + "> {\r\n");
        sb.append("}\r\n");
        String content = sb.toString();
        System.out.println(content);
        FileWriter fw = new FileWriter(mapperPath + entityName + "Mapper.java");
        PrintWriter pw = new PrintWriter(fw);
        pw.println(content);
        pw.flush();
        pw.close();
        fw.close();
    }
}
