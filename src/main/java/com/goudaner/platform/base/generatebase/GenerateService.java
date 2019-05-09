package com.goudaner.platform.base.generatebase;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by emmet on 17-3-29.
 * desc:
 */
public class GenerateService {

    //生成Service类
    public static void generate(String entityName, String entityPackage, String mapperPackage, String servicePackage, String servicePath) throws IOException {
        StringBuffer sb = new StringBuffer();
        sb.append("package ").append(servicePackage).append(";\r\n").append("\r\n");

        sb.append("import com.goudaner.platform.base.SyMapperUtil;\r\n");
        sb.append("import ").append(entityPackage).append(".").append(entityName).append(";\r\n\r\n");
        sb.append("import ").append(mapperPackage).append(".").append(entityName).append("Mapper;\r\n");
        sb.append("import org.springframework.stereotype.Service;\r\n");
        sb.append("import org.springframework.transaction.annotation.Transactional;\r\n");
        sb.append("import tk.mybatis.mapper.entity.Example;\r\n\r\n");
        sb.append("import javax.annotation.Resource;\r\n");
        sb.append("import java.util.List;\r\n\r\n");

        sb.append("@Service\r\n");
        sb.append("@Transactional\r\n");
        sb.append("public class ").append(entityName).append("Service {\r\n");
        sb.append("\t@Resource private ").append(entityName).append("Mapper ").append("mapper;\r\n\r\n");
        sb.append("\tpublic Integer add").append(entityName).append("(").append(entityName).append(" ").append(initial(entityName)).append(") {\r\n");
        sb.append("\t\treturn mapper.insertSelective(").append(initial(entityName)).append(");\r\n");
        sb.append("\t}\r\n\r\n");
        sb.append("\tpublic Integer modify").append(entityName).append("(").append(entityName).append(" ").append(initial(entityName)).append(", String... fieldStrs) {\r\n");
        sb.append("\t\tExample example = SyMapperUtil.generateExample(").append(initial(entityName)).append(", fieldStrs);\r\n");
        sb.append("\t\treturn mapper.updateByExampleSelective(").append(initial(entityName)).append(", example);\r\n");
        sb.append("\t}\r\n\r\n");
        sb.append("\tpublic ").append(entityName).append(" get").append(entityName).append("(").append(entityName).append(" ").append(initial(entityName)).append(") {\r\n");
        sb.append("\t\treturn mapper.selectOne(").append(initial(entityName)).append(");\r\n");
        sb.append("\t}\r\n\r\n");
        sb.append("\tpublic List<").append(entityName).append("> get").append(entityName).append("s(").append(entityName).append(" ").append(initial(entityName)).append(") {\r\n");
        sb.append("\t\treturn mapper.select(").append(initial(entityName)).append(");\r\n");
        sb.append("\t}\r\n\r\n");
        sb.append("}\r\n");
        String content = sb.toString();
        System.out.println(content);
        FileWriter fw = new FileWriter(servicePath + entityName + "Service.java");
        PrintWriter pw = new PrintWriter(fw);
        pw.println(content);
        pw.flush();
        pw.close();
    }

    //把输入字符串的首字母改成小写
    private static String initial(String str) {
        char[] ch = str.toCharArray();
        if (ch[0] >= 'A' && ch[0] <= 'Z') ch[0] = (char) (ch[0] + 32);
        return new String(ch);
    }

}
