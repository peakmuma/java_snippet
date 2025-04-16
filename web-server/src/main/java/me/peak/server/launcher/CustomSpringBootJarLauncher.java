package me.peak.server.launcher;

import org.springframework.boot.loader.JarLauncher;

import java.net.URL;

public class CustomSpringBootJarLauncher extends JarLauncher {


    @Override
    protected ClassLoader createClassLoader(URL[] urls) throws Exception {
        // 自定义类加载器
//        return new FastURLClassLoader(urls, this.getClass().getClassLoader());
        return null;
    }

    public static void main(String[] args) throws Exception {
        new CustomSpringBootJarLauncher().launch(args);
    }

}
