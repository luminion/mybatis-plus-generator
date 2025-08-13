package io.github.bootystar.mybatisplus.generator;

/**
 * @author booty
 */
public class Generator4Mysql {


    public static void main(String[] args) {
        String url = "jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&characterEncoding=UTF-8";
        String username = "root";
        String password = "root";
        FastAutoGenerator
                .create(url, username, password)
                .initialize()
                .globalConfig(global -> global.disableOpenDir()
                                .outputDir(System.getProperty("user.dir") + "/src/test/java")
//                            .enableSwagger()
                            .enableSpringdoc()
                )
                .packageConfig(pkg -> pkg
                        .parent("com.example.test")
                )
                .execute("sys_user")
        ;
    }




}