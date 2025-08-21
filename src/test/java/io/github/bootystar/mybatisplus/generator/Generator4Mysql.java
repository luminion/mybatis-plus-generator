package io.github.bootystar.mybatisplus.generator;

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
                                .disableOpenDir()
                                .enableFileOverwrite()
                                .outputDir(System.getProperty("user.dir") + "/src/test/java")
                            .enableSwagger()
//                            .enableSpringdoc()
                )
                .pkg(e -> e
                        .parent("com.example.test")
                )
                .strategy(e->e
                )
                .entity(e->e
//                        .disable()
                )
                .mapper(e->e
                        .disable()
                )
                .service(e->e
                        .disable()
                )
                .controller(e->e
                        .disable()
                )
                
                .execute("sys_user")
        ;
    }




}