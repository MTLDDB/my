import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import javax.annotation.processing.Filer;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.tools.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;


public class test111 {
    public static void main(String[] args) throws IOException, InvocationTargetException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, InstantiationException, InterruptedException {
        MethodSpec function = MethodSpec.methodBuilder("function")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .returns(int.class)
                .addStatement("int total = 0")
                .beginControlFlow("for (int i = 0; i < 10; i++)")
                .addStatement("total += i")
                .endControlFlow()
                .addStatement("return total")
                .build();

        MethodSpec main = MethodSpec.methodBuilder("main1")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .returns(void.class)
                .addStatement("$T.out.println($S)", System.class, "Hello, JavaPoet!")
                .addStatement("$T.out.println($L)", System.class, "function()")
                .build();



        TypeSpec helloWorld = TypeSpec.classBuilder("HelloWorld")
                .addModifiers(Modifier.PUBLIC)
                .addMethod(main)
                .addMethod(function)
                .build();

        JavaFile javaFile = JavaFile.builder("Spider", helloWorld)
                .build();
        File file = new File("E:\\My\\src\\main\\java\\");
       // javaFile.writeTo(System.out);
        javaFile.writeTo(file);
        javax.tools.JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
//        StandardJavaFileManager fileManager = javaCompiler.getStandardFileManager(null, null, null);
//        Iterable iterable = fileManager.getJavaFileObjects("E:\\My\\src\\main\\java\\Spider\\Spider\\HelloWorld.java");
//        javax.tools.JavaCompiler.CompilationTask task = javaCompiler.getTask(null, fileManager, null, null, null, iterable);
//        task.call();
//        fileManager.close();
        javaCompiler.run(null,null,null,"E:\\My\\src\\main\\java\\Spider\\HelloWorld.java");

       // Thread.sleep(3*1000);
//        Class clz = Class.forName("Spider/Spider/HelloWorld");//返回与带有给定字符串名的类 或接口相关联的 Class 对象。
//        clz.getClasses();
        URL[] urls = new URL[] {new URL("file:/" + "E:\\My\\src\\main\\java\\")};
        URLClassLoader classLoader = new URLClassLoader(urls);
        Class clazz = classLoader.loadClass("Spider.HelloWorld");
        clazz.newInstance();
        System.out.println(clazz.getMethod("main1"));
        Method method=clazz.getMethod("main1");
        System.out.println(method.getReturnType());
        Object result= method.invoke(method);
        System.out.println(result);
//        Object o = clz.newInstance();
//        Method method = clz.getDeclaredMethod("main");//返回一个 Method 对象，该对象反映此 Class 对象所表示的类或接口的指定已声明方法
//        String result= (String)method.invoke(o);//静态方法第一个参数可为null,第二个参数为实际传参
//        System.out.println(result);



    }
}
