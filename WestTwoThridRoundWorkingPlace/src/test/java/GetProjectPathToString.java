import java.io.File;
import java.io.IOException;
import java.util.*;
public class GetProjectPathToString {
    public static void main(String[] args) {
        /**
//         * 第一种：获取类加载的根路径  D:\Work\IdeaProjects\HelloVelocity\target\classes
//         */
//        File f = new File(ControllerUtils.class.getClass().getResource("/").getPath());
//        System.out.println("第一种：获取类加载的根路径");
//        System.out.println(f);
//        // 获取当前类的所在工程路径; 如果不加“/”获取当前类的加载目录  D:\IdeaProjects\cdby_wan\WebRoot\WEB-INF\classes\test\com
//        File f2 = new File(ControllerUtils.class.getClass().getResource("").getPath());
//        System.out.println(f2);
//        /**
//         * 第二种：获取项目路径 D:\Work\IdeaProjects\HelloVelocity

        File directory = new File("");// 参数为空
        String courseFile = null; // Tomcat环境中运行，会获取Tomcat安装目录的bin目录，不推荐使用
        try {
            courseFile = directory.getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("第二种：获取项目路径");
        System.out.println(courseFile);
        /**
//         * 第三种： /D:/Work/IdeaProjects/HelloVelocity/target/classes/
//         */
//        String path = ControllerUtils.class.getClassLoader().getResource("").getPath();
//        System.out.println("第三种：");
//        System.out.println(path);
        /**
         * 第四种： D:\Work\IdeaProjects\HelloVelocity
         * 结果： C:\Documents and Settings\Administrator\workspace\projectName
         * 获取当前工程路径
         */
        System.out.println("第四种：");
        System.out.println(System.getProperty("user.dir"));
        /**
         * 第五种：  获取所有的类路径 包括jar包的路径
         */
        System.out.println(System.getProperty("java.class.path"));
    }
}
