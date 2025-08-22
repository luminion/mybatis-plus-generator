package io.github.bootystar.mybatisplus.generator;

import io.github.bootystar.mybatisplus.generator.config.rules.DateType;

/**
 * @author booty
 */
public class Generator4Mysql {


    public static void main(String[] args) {
        String url = "jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&characterEncoding=UTF-8";
        String username = "root";
        String password = "root";
        FastGenerator
                .create(url, username, password)
                .initialize()
                .global(e -> e
                                .outputDir(System.getProperty("user.dir") + "/src/test/java")
                                .author("bootystar")
                                .dateType(DateType.SQL_PACK)
//                                .commentDate("yyyy-MM-dd HH:mm:ss")
                                .commentDate(()->"1.0.0")
                                .enableFileOverwrite()
                                .enableLombok()
                                .enableChainModel()
                                .enableSwagger()
                                .enableSpringdoc()
                                .enableCommentLink()
                                .enableCommentUUID()
                                .enableJavaxApi()
                                .enableEasyExcel()
                                .disableOpenDir()
//                                .disableQuery()
//                                .disableInsert()
//                                .disableUpdate()
//                                .disableImport()
//                                .disableExport()

                )
                .pkg(e -> e
                        .parent("com.example.test")
                        .entityQueryDTO("entity")
                        .entityInsertDTO("entity")
                        .entityUpdateDTO("entity")
                        .entityVO("entity")
                )
                .strategy(e -> e
                )
                .entity(e -> e
//                        .disable()
                                .logicDeleteColumnName("deleted")
                                .versionColumnName("version")
                )
                .mapper(e -> e
                        .disable()
                )
                .service(e -> e
                        .disable()
                )
                .controller(e -> e
                        .disable()
                )

                .execute("sys_user")
        ;
    }


}