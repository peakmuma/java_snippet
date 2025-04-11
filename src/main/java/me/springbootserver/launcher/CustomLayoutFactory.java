package me.springbootserver.launcher;

import org.springframework.boot.loader.tools.*;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Locale;
import java.util.jar.JarFile;

/**
 * 该类是为了自定义springboot的打包结果，将自定义的启动类打包到最终的jar里，并使用这个类作为启动类
 */
public class CustomLayoutFactory implements LayoutFactory {

    @Override
    public Layout getLayout(File source) {
        if (source == null) {
            throw new IllegalArgumentException("File must not be null");
        }
        String lowerCaseFileName = source.getName().toLowerCase(Locale.ENGLISH);
        if (lowerCaseFileName.endsWith(".jar")) {
            //如果是jar的打包方式，使用自定义的布局类
            return new CustomLayout();
        } else {
            return Layouts.forFile(source);
        }
    }

    public static class CustomLayout extends Layouts.Jar implements CustomLoaderLayout {

        private static final String NESTED_LOADER_JAR = "META-INF/loader/spring-boot-loader.jar";

        //返回自定义的main类
        @Override
        public String getLauncherClassName() {
            return "me.springbootserver.launcher.CustomSpringBootJarLauncher";
        }

        //把启动类的class文件打包到最终的jar结果里
        @Override
        public void writeLoadedClasses(LoaderClassesWriter writer) throws IOException {
            //原本springboot的启动类
            writer.writeLoaderClasses(NESTED_LOADER_JAR);
            //自定义的启动类，这个写法是把当前类所在的jar包里的所有类都打包到最终的jar里
            URL customLoaderJarUrl = getClass().getProtectionDomain().getCodeSource().getLocation();
            try {
                File jarFile = new File(customLoaderJarUrl.toURI());
                JarFile jar = new JarFile(jarFile);
//                ((JarWriter) writer).writeEntries(jar); 在spring boot 2.x 版本可以这么写，但是升级springboot版本之后就没了
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
